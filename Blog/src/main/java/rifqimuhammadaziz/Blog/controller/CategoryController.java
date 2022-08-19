package rifqimuhammadaziz.Blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rifqimuhammadaziz.Library.model.Information;
import rifqimuhammadaziz.Library.model.InformationCategory;
import rifqimuhammadaziz.Library.model.Post;
import rifqimuhammadaziz.Library.model.PostCategory;
import rifqimuhammadaziz.Library.service.contract.InformationCategoryService;
import rifqimuhammadaziz.Library.service.contract.InformationService;
import rifqimuhammadaziz.Library.service.contract.PostCategoryService;
import rifqimuhammadaziz.Library.service.contract.PostService;

import java.util.List;

@Controller
@AllArgsConstructor
public class CategoryController {

    private PostService postService;
    private PostCategoryService postCategoryService;
    private InformationService informationService;
    private InformationCategoryService informationCategoryService;

    @GetMapping("/posts/category/{id}")
    public String posts(@PathVariable("id") Long categoryId, Model model) {
        // Get Post Category
        PostCategory category = postCategoryService.findById(categoryId);
        model.addAttribute("category", category);

        // Get Related Posts
        List<Post> posts = postService.getPostsByCategory(categoryId);
        model.addAttribute("posts", posts);

        // Get Post Categories
        List<PostCategory> postCategories = postCategoryService.findAll();
        model.addAttribute("postCategories", postCategories);

        // Get Information Categories
        List<InformationCategory> informationCategories = informationCategoryService.findAll();
        model.addAttribute("informationCategories", informationCategories);

        return "categories/posts";
    }

    @GetMapping("/informations/category/{id}")
    public String informations(@PathVariable("id") Long categoryId, Model model) {
        // Get Information Category
        InformationCategory category = informationCategoryService.findById(categoryId);
        model.addAttribute("category", category);

        // Get Related Information
        List<Information> information = informationService.getAllInformationByCategory(categoryId);
        model.addAttribute("informations", information);

        // Get Post Categories
        List<PostCategory> postCategories = postCategoryService.findAll();
        model.addAttribute("postCategories", postCategories);

        // Get Information Categories
        List<InformationCategory> informationCategories = informationCategoryService.findAll();
        model.addAttribute("informationCategories", informationCategories);

        return "categories/informations";
    }
}
