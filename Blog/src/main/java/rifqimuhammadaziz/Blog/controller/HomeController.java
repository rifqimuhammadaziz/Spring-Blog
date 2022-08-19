package rifqimuhammadaziz.Blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rifqimuhammadaziz.Library.dto.PostDto;
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
public class HomeController {

    private PostService postService;
    private PostCategoryService postCategoryService;
    private InformationService informationService;
    private InformationCategoryService informationCategoryService;

    @RequestMapping(value = {"/", "/posts"}, method = RequestMethod.GET)
    public String home(Model model) {
        // Get Posts
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);

        // Get Post Categories
        List<PostCategory> postCategories = postCategoryService.findAll();
        model.addAttribute("postCategories", postCategories);

        // Get Information Categories
        List<InformationCategory> informationCategories = informationCategoryService.findAll();
        model.addAttribute("informationCategories", informationCategories);

        return "index";
    }

    @RequestMapping("/home")
    public String home2(Model model) {
        // Get Posts
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);

        // Get All Information
        List<Information> information = informationService.getRecentlyInformation();
        model.addAttribute("informations", information);

        return "home";
    }

}
