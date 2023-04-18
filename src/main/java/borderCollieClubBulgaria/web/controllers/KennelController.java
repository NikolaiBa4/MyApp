package borderCollieClubBulgaria.web.controllers;

import borderCollieClubBulgaria.domain.dto.binding.KennelAddDto;
import borderCollieClubBulgaria.domain.dto.view.KennelViewDto;
import borderCollieClubBulgaria.service.KennelService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/kennels")
public class KennelController {

    private static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";
    private final KennelService kennelService;

    public KennelController(KennelService kennelService) {
        this.kennelService = kennelService;
    }

    @GetMapping("/all")
    public String allKennels(Model model) {

        List<KennelViewDto> kennels = this.kennelService.findAllKennels();

        model.addAttribute("kennels", kennels);

        return "kennels";
    }

    @GetMapping("/{id}")
    public String getKennelById(@PathVariable("id") Long kennelId,
                                @AuthenticationPrincipal UserDetails userDetails,
                                Model model) {

        model.addAttribute("kennel", kennelService.findKennelById(kennelId));
        model.addAttribute("canDelete", kennelService.isUserAdmin(userDetails));

        return "kennel-details";
    }

    @PreAuthorize("@kennelService.isUserAdmin(#userDetails)")
    @DeleteMapping("/{id}")
    public String deleteKennel(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long id) {

        kennelService.deleteKennelById(id);

        return "redirect:/kennels/all";
    }

    @GetMapping("/new")
    public String createNewKennelForm() {
        return "kennels-add";
    }

    @PostMapping("/new")
    public String createNewKennel(
            @Valid @ModelAttribute(name = "kennelAddDto") KennelAddDto kennelAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("kennelAddDto", kennelAddDto)
                    .addFlashAttribute(BINDING_RESULT_PATH + "kennelAddDto", bindingResult);

            return "redirect:/kennels/new";
        }
        kennelService.addKennel(kennelAddDto);

        return "redirect:/kennels/all";
    }

    @ModelAttribute(name = "kennelAddDto")
    public KennelAddDto initKennelAddDto() {
        return new KennelAddDto();
    }
}
