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
import rifqimuhammadaziz.Library.service.contract.PostService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        List<PostDto> posts = postService.findAll();
        model.addAttribute("posts", posts);
        System.out.println(posts);
        return "index";
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") Long id, Model model) {
        PostDto postDto = postService.findById(id);
        model.addAttribute("post", postDto);

        return "posts/post-detail";
    }
}
