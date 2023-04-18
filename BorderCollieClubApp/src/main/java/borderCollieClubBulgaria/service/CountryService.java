package borderCollieClubBulgaria.service;

import borderCollieClubBulgaria.domain.entities.CountryEntity;
import borderCollieClubBulgaria.exception.ObjectNotFoundException;
import borderCollieClubBulgaria.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryEntity findByName(String countryName) {

        if (this.countryRepository.findByName(countryName).isPresent()) {
            return this.countryRepository.findByName(countryName).
                    orElseThrow(() -> new ObjectNotFoundException("Country with name " + countryName + " not found"));
        }
        return this.countryRepository.saveAndFlush(new CountryEntity(countryName));
    }
}
