package rifqimuhammadaziz.Dashboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import rifqimuhammadaziz.Library.dto.AdminDto;
import rifqimuhammadaziz.Library.model.Admin;
import rifqimuhammadaziz.Library.service.contract.AdminService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
public class AuthController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("admin", new AdminDto());
        return "auth/register";
    }

    @PostMapping("/do-register")
    public String register(@Valid @ModelAttribute("admin") AdminDto adminDto,
                           BindingResult result,
                           Model model,
                           HttpSession session) {
        try {
            Admin admin = adminService.findByEmail(adminDto.getEmail());
            if (admin != null) {
                model.addAttribute("admin", adminDto);
                session.setAttribute("registerFailed", "Failed to register, email has been registered. Please use another email.");
                System.out.println(admin);
                return "register";
            }
            if (adminDto.getPassword().equals(adminDto.getRetypePassword())) {
                adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                adminService.save(adminDto);
                log.info("Register Success");
                session.setAttribute("registerSuccess", "Sucessfully register new account");
                model.addAttribute("admin", adminDto);
                return "auth/login";
            } else {
                model.addAttribute("admin", adminDto);
                model.addAttribute("passwordError", "Retype Password is invalid, please check again!");
                log.warn("Retype Password Error");
                return "auth/register";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Server Error");
        }
        return "register";
    }


}
