package borderCollieClubBulgaria.web.controllers;

import borderCollieClubBulgaria.domain.dto.binding.DogAddDto;
import borderCollieClubBulgaria.domain.dto.view.DogViewDto;
import borderCollieClubBulgaria.service.DogService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dogs")
public class DogController {

    private static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";

    private final DogService dogService;


    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/all")
    public String getAllDogs(Model model) {
        List<DogViewDto> dogs = dogService.findAllDogs();

        model.addAttribute("dogs", dogs);

        return "dogs";
    }


    @GetMapping("/{id}")
    public String getDogById(@PathVariable("id") Long dogId,
                             @AuthenticationPrincipal UserDetails userDetails,
                             Model model) {

        model.addAttribute("dog", dogService.findDogById(dogId));
        model.addAttribute("canDelete", dogService.isOwner(userDetails, dogId));

        return "dog-details";
    }

    @PreAuthorize("@dogService.isOwner(#userDetails, #id)")
    @DeleteMapping("/{id}")
    public String deleteDog(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long id) {

        dogService.deleteDogById(id);

        return "redirect:/dogs/all";
    }

    @GetMapping("/new")
    public String createNewDogForm() {
        return "dogs-add";
    }

    @PostMapping("/new")
    public String createNewDog(
            @Valid @ModelAttribute(name = "dogAddDto") DogAddDto dogAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("dogAddDto", dogAddDto)
                    .addFlashAttribute(BINDING_RESULT_PATH + "dogAddDto", bindingResult);

            return "redirect:/dogs/new";
        }

        dogService.addDog(principal, dogAddDto);

        return "redirect:/dogs/all";
    }

    @ModelAttribute(name = "dogAddDto")
    public DogAddDto initDogAddDto() {
        return new DogAddDto();
    }


}
