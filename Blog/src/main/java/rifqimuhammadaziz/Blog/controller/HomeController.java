package rifqimuhammadaziz.Blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rifqimuhammadaziz.Library.dto.PostDto;
import rifqimuhammadaziz.Library.model.Post;
import rifqimuhammadaziz.Library.model.PostCategory;
import rifqimuhammadaziz.Library.service.contract.PostCategoryService;
import rifqimuhammadaziz.Library.service.contract.PostService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostCategoryService postCategoryService;

    @RequestMapping(value = {"/", "/home", "/posts"}, method = RequestMethod.GET)
    public String home(Model model) {
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);

        List<PostCategory> postCategories = postCategoryService.findAll();
        model.addAttribute("postCategories", postCategories);
        return "index";
    }

}
