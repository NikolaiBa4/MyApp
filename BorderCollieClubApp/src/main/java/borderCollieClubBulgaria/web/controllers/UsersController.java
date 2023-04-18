package borderCollieClubBulgaria.web.controllers;

import borderCollieClubBulgaria.domain.dto.binding.UserEditDto;
import borderCollieClubBulgaria.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/my-profile")
public class UsersController {


    private static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit")
    public String getEdit(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        model.addAttribute("userEditDto", userService.viewUserToEdit(userDetails.getUsername()));
        return "my-profile";
    }

    @PostMapping("/edit")
    public String postRegister(Model model, @AuthenticationPrincipal UserDetails userDetails
           ,@Valid @ModelAttribute(name = "userEditForm")UserEditDto userEditDto, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userEditForm", userEditDto)
                    .addFlashAttribute(BINDING_RESULT_PATH + "userEditForm", bindingResult);

            return "redirect:/my-profile/edit";
        }

        userService.editUser(userEditDto,userDetails);


        return "index";
    }

    @ModelAttribute(name = "userEditForm")
    public UserEditDto initUserEditDto() {
       return new UserEditDto();
    }

}
