package rifqimuhammadaziz.Blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rifqimuhammadaziz.Library.dto.PostDto;
import rifqimuhammadaziz.Library.model.Post;
import rifqimuhammadaziz.Library.model.PostCategory;
import rifqimuhammadaziz.Library.service.contract.PostCategoryService;
import rifqimuhammadaziz.Library.service.contract.PostService;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostCategoryService postCategoryService;

    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") Long id, Model model) {
        PostDto postDto = postService.findById(id);
        model.addAttribute("post", postDto);

        Long categoryId = postDto.getPostCategory().getId();
        List<Post> posts = postService.getRelatedPosts(categoryId);
        model.addAttribute("posts", posts);

        List<PostCategory> postCategories = postCategoryService.findAll();
        model.addAttribute("postCategories", postCategories);

        return "posts/post-detail";
    }

}
