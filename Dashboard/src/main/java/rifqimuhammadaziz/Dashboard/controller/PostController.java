package rifqimuhammadaziz.Dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rifqimuhammadaziz.Library.dto.PostDto;
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

    @GetMapping("/posts")
    public String posts(Model model) {
        List<PostDto> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts/posts";
    }

    @GetMapping("/posts/create")
    public String createPostForm(Model model) {
        List<PostCategory> categories = postCategoryService.findAllByActivated();
        model.addAttribute("categories", categories);
        model.addAttribute("post", new PostDto());
        return "posts/create-post";
    }

    @PostMapping("/save-post")
    public String savePost(@ModelAttribute("post") PostDto postDto, RedirectAttributes attributes) {
        try {
            postService.save(postDto);
            attributes.addFlashAttribute("createSuccess", "New post successfully created");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to create new post");
        }
        return "redirect:/posts";
    }

    @GetMapping("/update-post/{id}")
    public String updatePostForm(@PathVariable("id") Long id,
                                 Model model) {
        List<PostCategory> categories = postCategoryService.findAllByActivated();
        PostDto postDto = postService.findById(id);
        model.addAttribute("categories", categories);
        model.addAttribute("postDto", postDto);
        return "/posts/update-post";
    }

    @PostMapping("/update-post/{id}")
    public String updatePost(@PathVariable("id") Long id,
                             @ModelAttribute("postDto") PostDto postDto,
                             RedirectAttributes attributes) {
        try {
            postService.update(postDto);
            attributes.addFlashAttribute("updateSuccess", "Current post updated");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to update existing post");
        }
        return "redirect:/posts";
    }

    @RequestMapping(value = "/delete-post", method = {RequestMethod.PUT, RequestMethod.GET})
    public String deletePost(Long id, RedirectAttributes attributes) {
        try {
            postService.deleteById(id);
            attributes.addFlashAttribute("deleteSuccess", "Post successfully deleted");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("deleteFailed", "Delete post failed");
        }
        return "redirect:/posts";
    }

    @RequestMapping(value = "/publish-post", method = {RequestMethod.PUT, RequestMethod.GET})
    public String publishPost(Long id, RedirectAttributes attributes) {
        try {
            postService.publishById(id);
            attributes.addFlashAttribute("publishSuccess", "Post successfully published");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("publishFailed", "Post failed to publish");
        }
        return "redirect:/posts";
    }

    @GetMapping(value = "/posts/preview/{id}")
    public String previewPost(@PathVariable("id") Long id, Model model) {
        PostDto postDto = postService.findById(id);
        model.addAttribute("postDto", postDto);
        return "/posts/preview-post";
    }
}
