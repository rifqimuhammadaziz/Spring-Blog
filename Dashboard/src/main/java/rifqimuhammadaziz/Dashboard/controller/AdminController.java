package rifqimuhammadaziz.Dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rifqimuhammadaziz.Library.dto.AdminBasicInformation;
import rifqimuhammadaziz.Library.model.Admin;
import rifqimuhammadaziz.Library.service.contract.AdminService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public String users(Principal principal, HttpSession session, Model model) {
        if (principal != null) {
            // Get Login Details
            AdminBasicInformation admin = adminService.getLoginDetails(principal.getName());
            model.addAttribute("admin", admin);

            // Find All Admins
            List<Admin> admins = adminService.findAllAdmin();
            model.addAttribute("admins", admins);
        } else {
            session.removeAttribute("username");
            return "redirect:/login";
        }
        return "users/users";
    }
}
