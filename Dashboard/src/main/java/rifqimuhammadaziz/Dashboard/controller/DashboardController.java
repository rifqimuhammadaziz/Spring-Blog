package rifqimuhammadaziz.Dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rifqimuhammadaziz.Library.dto.AdminBasicInformation;
import rifqimuhammadaziz.Library.service.contract.AdminService;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class DashboardController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String dashboard(Principal principal, HttpSession session, Model model) {
        if (principal != null) {
            AdminBasicInformation admin = adminService.getLoginDetails(principal.getName());
            model.addAttribute("admin", admin);

            session.setAttribute("username", principal.getName());
        } else {
            session.removeAttribute("username");
            return "redirect:/login";
        }
        return "index";
    }
}
