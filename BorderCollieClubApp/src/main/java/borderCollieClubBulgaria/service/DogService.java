package borderCollieClubBulgaria.service;

import borderCollieClubBulgaria.domain.dto.binding.DogAddDto;
import borderCollieClubBulgaria.domain.dto.view.DogViewDto;
import borderCollieClubBulgaria.domain.entities.*;
import borderCollieClubBulgaria.exception.ObjectNotFoundException;
import borderCollieClubBulgaria.repository.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import borderCollieClubBulgaria.domain.enums.UserRoles;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class DogService {

    private final DogRepository dogRepository;

    private final KennelService kennelService;

    private final UserService userService;

    public DogService(DogRepository dogRepository,
                      KennelService kennelService,
                      UserService userService) {
        this.dogRepository = dogRepository;
        this.kennelService = kennelService;
        ;
        this.userService = userService;
    }

    public void addDog(Principal principal, DogAddDto dogAddDto) {

        this.dogRepository.saveAndFlush(mapToDogEntity(principal, dogAddDto));

    }


    public DogEntity findByDogName(String dogName) {

        return this.dogRepository.findByName(dogName).orElse(new DogEntity());
    }

    public List<DogViewDto> findAllDogs() {

        return this.dogRepository.findAll().stream().map(this::mapToDogDto).toList();

    }

    public void increaseAgeOfDogs() {

        List<DogEntity> dogs = this.dogRepository.findAll();

        for (DogEntity dog : dogs) {
            dog.setAge(dog.getAge() + 1);
            this.dogRepository.saveAndFlush(dog);
        }
    }

    public void deleteDogById(Long id) {
        this.dogRepository.findById(id).
                ifPresent(dogRepository::delete);
    }

    public DogViewDto findDogById(Long dogId) {

        return this.dogRepository.findById(dogId).map(this::mapToDogDto).
                orElseThrow(() -> new ObjectNotFoundException("Dog with ID " + dogId + " not found"));
    }

    public DogViewDto mapToDogDto(DogEntity dogEntity) {

        return new DogViewDto().
                setId(dogEntity.getId()).
                setAge(dogEntity.getAge()).
                setKennelCountry(dogEntity.getKennel().getCountry().getName()).
                setName(dogEntity.getName()).
                setGender(dogEntity.getGender()).
                setKennelName(dogEntity.getKennel().getName()).
                setOwnerName(dogEntity.getOwner().getUsername());

    }

    public DogEntity mapToDogEntity(Principal principal, DogAddDto dogAddDto) {


        return new DogEntity()
                .setName(dogAddDto.getName()).
                setAge(dogAddDto.getAge()).
                setGender(dogAddDto.getGender()).
                setAge(dogAddDto.getAge()).
                setKennel(this.kennelService.
                        findKennel(dogAddDto.getKennelName(),
                                dogAddDto.getKennelEmail(),
                                dogAddDto.getKennelCountry())).
                setOwner(this.userService.findByUsername(principal.getName()));
    }

    public boolean isOwner(UserDetails userDetails, Long id) {

        if (id == null || userDetails == null) {
            return false;
        }

        var dog = this.dogRepository.
                findById(id).
                orElse(null);

        if (dog == null) {
            return false;
        }

        return userDetails.getUsername().equals(dog.getOwner().getUsername()) ||
                isUserAdmin(userDetails);
    }

    private boolean isUserAdmin(UserDetails userDetails) {
        // to do
        return userDetails.getAuthorities().
                stream().
                map(GrantedAuthority::getAuthority).
                anyMatch(a -> a.equals("ROLE_" + UserRoles.ADMIN.name()));
    }
}
