package rifqimuhammadaziz.Blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rifqimuhammadaziz.Library.dto.PostDto;
import rifqimuhammadaziz.Library.model.InformationCategory;
import rifqimuhammadaziz.Library.model.Post;
import rifqimuhammadaziz.Library.model.PostCategory;
import rifqimuhammadaziz.Library.service.contract.InformationCategoryService;
import rifqimuhammadaziz.Library.service.contract.PostCategoryService;
import rifqimuhammadaziz.Library.service.contract.PostService;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class PostController {

    private PostService postService;
    private PostCategoryService postCategoryService;
    private InformationCategoryService informationCategoryService;

    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") Long id, Model model) {
        // Get Post By ID
        PostDto postDto = postService.findById(id);
        model.addAttribute("post", postDto);

        // Get Related Posts
        Long categoryId = postDto.getPostCategory().getId();
        List<Post> posts = postService.getRelatedPosts(categoryId);
        model.addAttribute("posts", posts);

        // Get Information Categories
        List<PostCategory> postCategories = postCategoryService.findAll();
        model.addAttribute("postCategories", postCategories);

        return "posts/post-detail";
    }

    @GetMapping("/posts/{pageNo}")
    public String postsPage(@PathVariable("pageNo") int pageNo, Model model, Principal principal) {
        // Get Post Pages
        Page<Post> posts = postService.pagePosts(pageNo);
        model.addAttribute("size", posts.getSize());
        model.addAttribute("totalPages", posts.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("posts", posts);

        // Get Post Categories
        List<PostCategory> postCategories = postCategoryService.findAll();
        model.addAttribute("postCategories", postCategories);

        // Get Information Categories
        List<InformationCategory> informationCategories = informationCategoryService.findAll();
        model.addAttribute("informationCategories", informationCategories);


        return "posts/posts";
    }

}
