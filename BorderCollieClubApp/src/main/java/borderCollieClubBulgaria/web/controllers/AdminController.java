package borderCollieClubBulgaria.web.controllers;

import borderCollieClubBulgaria.domain.dto.view.UserViewDto;
import borderCollieClubBulgaria.service.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pages/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PreAuthorize("@adminService.isUserAdmin(#userDetails)")
    @GetMapping("/allUsers")
    public String allUsers(@AuthenticationPrincipal UserDetails userDetails,
                           Model model) {

        List<UserViewDto> allUsers = this.adminService.findAllUsers();

        model.addAttribute("users", allUsers);


        return "users";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long userId,
                              @AuthenticationPrincipal UserDetails userDetails,
                              Model model) {

        model.addAttribute("user", this.adminService.findUserById(userId));
        model.addAttribute("canDelete", this.adminService.isUserAdmin(userDetails));

        return "user-details";
    }

    @PreAuthorize("@adminService.isUserAdmin(#userDetails)")
    @DeleteMapping("/{id}")
    public String deleteUser(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long id) {

        this.adminService.deleteUserById(id);

        return "redirect:/pages/admins/allUsers";
    }


}
