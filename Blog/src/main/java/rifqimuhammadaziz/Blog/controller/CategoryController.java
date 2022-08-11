package rifqimuhammadaziz.Blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rifqimuhammadaziz.Library.model.Post;
import rifqimuhammadaziz.Library.model.PostCategory;
import rifqimuhammadaziz.Library.service.contract.PostCategoryService;
import rifqimuhammadaziz.Library.service.contract.PostService;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostCategoryService postCategoryService;

    @GetMapping("/posts/category/{id}")
    public String posts(@PathVariable("id") Long categoryId, Model model) {
        PostCategory category = postCategoryService.findById(categoryId);
        model.addAttribute("category", category);

        List<Post> posts = postService.getPostsByCategory(categoryId);
        model.addAttribute("posts", posts);

        return "categories/posts";
    }
}
