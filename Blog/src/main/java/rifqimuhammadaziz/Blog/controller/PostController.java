package rifqimuhammadaziz.Blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rifqimuhammadaziz.Library.dto.PostDto;
import rifqimuhammadaziz.Library.model.Post;
import rifqimuhammadaziz.Library.service.contract.PostService;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") Long id, Model model) {
        PostDto postDto = postService.findById(id);
        model.addAttribute("post", postDto);

        Long categoryId = postDto.getPostCategory().getId();
        List<Post> posts = postService.getRelatedPost(categoryId);
        model.addAttribute("posts", posts);

        return "posts/post-detail";
    }

}
