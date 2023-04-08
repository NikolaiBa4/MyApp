package borderCollieClubBulgaria.service;

import borderCollieClubBulgaria.domain.dto.binding.KennelAddDto;
import borderCollieClubBulgaria.domain.dto.view.KennelViewDto;
import borderCollieClubBulgaria.domain.entities.CountryEntity;
import borderCollieClubBulgaria.domain.entities.KennelEntity;
import borderCollieClubBulgaria.domain.enums.UserRoles;
import borderCollieClubBulgaria.exception.ObjectNotFoundException;
import borderCollieClubBulgaria.repository.KennelRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class KennelService {

    private final KennelRepository kennelRepository;

    private final CountryService countryService;


    public KennelService(KennelRepository kennelRepository, CountryService countryService) {
        this.kennelRepository = kennelRepository;
        this.countryService = countryService;
    }

    public void addKennel(KennelAddDto kennelAddDto) {

        this.kennelRepository.saveAndFlush(mapToKennelEntity(kennelAddDto));

    }

    public KennelEntity findKennel(String kennelName, String email, String countryName) {

        if (this.kennelRepository.findByName(kennelName).isPresent()) {
            return this.kennelRepository.findByName(kennelName).
                    orElseThrow(() -> new ObjectNotFoundException("Kennel with name " + kennelName + " not found"));
        }
        CountryEntity country = countryService.findCountryByName(countryName);
        return this.kennelRepository.saveAndFlush(new KennelEntity(kennelName, email, country));
    }

    public void deleteKennelById(Long id) {

        this.kennelRepository.findById(id).
                ifPresent(kennelRepository::delete);
    }

    public KennelEntity findKennelByEmail(String email) {

        return this.kennelRepository.findByEmail(email).orElse(new KennelEntity());
    }

    public KennelEntity findKennelByName(String name) {
        return this.kennelRepository.findByName(name).orElse(new KennelEntity());
    }

    public List<KennelViewDto> findAllKennels() {
        return this.kennelRepository.findAll().stream().map(this::mapToViewDto).toList();
    }

    public KennelViewDto findKennelById(Long kennelId) {

        return this.kennelRepository.findById(kennelId).map(this::mapToViewDto).
                orElseThrow(() -> new ObjectNotFoundException("Kennel with ID " + kennelId + " not found"));
    }

    public KennelViewDto mapToViewDto(KennelEntity kennel) {

        return new KennelViewDto().
                setId(kennel.getId())
                .setName(kennel.getName()).
                setEmail(kennel.getEmail()).
                setCountryName(kennel.getCountry().getName());
    }

    public KennelEntity mapToKennelEntity(KennelAddDto kennelAddDto) {

        CountryEntity country = this.countryService.findCountryByName(kennelAddDto.getCountryName());

        return new KennelEntity().
                setName(kennelAddDto.getName()).
                setEmail(kennelAddDto.getEmail()).
                setCountry(country);
    }


    public boolean isUserAdmin(UserDetails userDetails) {
        // to do
        return userDetails.getAuthorities().
                stream().
                map(GrantedAuthority::getAuthority).
                anyMatch(a -> a.equals("ROLE_" + UserRoles.ADMIN.name()));
    }


}
