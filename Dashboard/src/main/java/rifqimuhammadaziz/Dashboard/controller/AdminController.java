package rifqimuhammadaziz.Dashboard.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rifqimuhammadaziz.Library.dto.AdminBasicInformation;
import rifqimuhammadaziz.Library.service.contract.AdminService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;

    @GetMapping("/users")
    public String users(Principal principal, HttpSession session, Model model) {
        if (principal != null) {
            // Get Login Details
            AdminBasicInformation admin = adminService.getLoginDetails(principal.getName());
            model.addAttribute("admin", admin);

            // Find All Admins
            List<AdminBasicInformation> admins = adminService.findAllAdminBasicInformation();
            model.addAttribute("admins", admins);
        } else {
            session.removeAttribute("username");
            return "redirect:/login";
        }
        return "users/users";
    }

    @RequestMapping(value = "/users/enable", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enable(Long id, RedirectAttributes attributes) {
        try {
            adminService.enableAdminById(id);
            attributes.addFlashAttribute("enableSuccess", "User Successfully Enabled.");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("enableFailed", "Failed to Enable User.");
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/disable", method = {RequestMethod.PUT, RequestMethod.GET})
    public String disable(Long id, RedirectAttributes attributes) {
        try {
            adminService.disableAdminById(id);
            attributes.addFlashAttribute("disableSuccess", "User Successfully Disabled.");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("enableFailed", "Failed to Disable User.");
        }
        return "redirect:/users";
    }
}
