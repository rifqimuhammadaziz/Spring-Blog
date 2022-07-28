package rifqimuhammadaziz.Dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rifqimuhammadaziz.Library.dto.PostDto;
import rifqimuhammadaziz.Library.model.Category;
import rifqimuhammadaziz.Library.model.Post;
import rifqimuhammadaziz.Library.service.contract.CategoryService;
import rifqimuhammadaziz.Library.service.contract.PostService;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/posts")
    public String posts(Model model) {
        List<PostDto> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts/posts";
    }

    @GetMapping("/posts/create")
    public String createPostForm(Model model) {
        List<Category> categories = categoryService.findAllByActivated();
        model.addAttribute("categories", categories);
        model.addAttribute("post", new PostDto());
        return "posts/create-post";
    }

    @PostMapping("/save-post")
    public String savePost(@ModelAttribute("post") PostDto postDto, RedirectAttributes attributes) {
        try {
            postService.save(postDto);
            attributes.addFlashAttribute("success", "New post successfully created");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to create new post");
        }
        return "redirect:/posts";
    }
}
