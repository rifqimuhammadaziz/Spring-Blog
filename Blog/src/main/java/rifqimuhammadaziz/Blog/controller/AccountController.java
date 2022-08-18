package rifqimuhammadaziz.Blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rifqimuhammadaziz.Library.model.InformationCategory;
import rifqimuhammadaziz.Library.model.PostCategory;
import rifqimuhammadaziz.Library.service.contract.InformationCategoryService;
import rifqimuhammadaziz.Library.service.contract.PostCategoryService;

import java.util.List;

@Controller
@AllArgsConstructor
public class AccountController {

    private PostCategoryService postCategoryService;
    private InformationCategoryService informationCategoryService;

    @GetMapping("/profile")
    public String profile(Model model) {
        // Get Post Categories
        List<PostCategory> postCategories = postCategoryService.findAll();
        model.addAttribute("postCategories", postCategories);

        // Get Information Categories
        List<InformationCategory> informationCategories = informationCategoryService.findAll();
        model.addAttribute("informationCategories", informationCategories);

        return "profile/profile";
    }
}
