package rifqimuhammadaziz.Dashboard.controller;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rifqimuhammadaziz.Library.dto.AdminDto;
import rifqimuhammadaziz.Library.model.Admin;
import rifqimuhammadaziz.Library.service.contract.AdminService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
@Slf4j
public class AuthController {

    private AdminService adminService;
    private BCryptPasswordEncoder passwordEncoder;
    private Environment env;

    @GetMapping("/login")
    public String login(Model model, Authentication authentication) {
        if (authentication != null) {
            Admin admin = adminService.findByUsername(authentication.getName());
            System.out.println(admin);
            System.out.println(authentication.getPrincipal());
            return "redirect:/";
        } else {
            model.addAttribute("admin", new AdminDto());
            return "auth/login";
        }
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
                           HttpSession session,
                           RedirectAttributes attributes) {
        try {
            // Validate Field
            if (result.hasErrors()) {
                model.addAttribute("admin", adminDto);
                result.toString();
                return "auth/register";
            }

            // Check User Exists
            boolean admin = adminService.existsByUsername(adminDto.getUsername());
            if (admin) {
                model.addAttribute("admin", adminDto);
                attributes.addFlashAttribute("registerFailed", env.getProperty("register.failed"));
                log.error("Failed to register, email has been registered");
                return "redirect:/register";
            }

            // Check Equals Password
            if (adminDto.getPassword().equals(adminDto.getRetypePassword())) {
                adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                adminService.save(adminDto);
                log.info("Register Success");
                attributes.addFlashAttribute("registerSuccess", "Successfully register new account.");
                model.addAttribute("admin", adminDto);
                return "redirect:/login";
            } else {
                model.addAttribute("admin", adminDto);
                attributes.addFlashAttribute("passwordError", "Retype Password is invalid, please check again!");
                log.warn("Retype Password Error");
                return "redirect:/register";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Server Error");
        }
        return "auth/login";
    }


}
