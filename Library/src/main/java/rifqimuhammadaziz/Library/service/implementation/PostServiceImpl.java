package rifqimuhammadaziz.Library.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.Library.dto.PostDto;
import rifqimuhammadaziz.Library.model.Post;
import rifqimuhammadaziz.Library.repository.PostRepository;
import rifqimuhammadaziz.Library.service.contract.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PostDto> findAll() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtoList = posts.stream().map(post -> mapperDto(post)).collect(Collectors.toList());

        return postDtoList;
    }

    @Override
    public PostDto findById(Long id) {
        Post post = postRepository.findById(id).get();
        PostDto postDto = mapperDto(post);
        return postDto;
    }

    @Override
    public Post save(PostDto postDto) {
        try {
            Post post = new Post();
            post.setTitle(postDto.getTitle());
            post.setPostCategory(postDto.getPostCategory());
            post.setContent(postDto.getContent());
            post.setDeleted(false);
            post.setPublished(false);
            System.out.println(post);
            return postRepository.save(post);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Post update(PostDto postDto) {
        try {
            Post currentPost = postRepository.findById(postDto.getId()).get();
            currentPost.setTitle(postDto.getTitle());
            currentPost.setPostCategory(postDto.getPostCategory());
            currentPost.setContent(postDto.getContent());
            return postRepository.save(currentPost);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        Post post = postRepository.findById(id).get();
        post.setDeleted(true);
        post.setPublished(false);
        postRepository.save(post);
    }

    @Override
    public void publishById(Long id) {
        Post post = postRepository.findById(id).get();
        post.setDeleted(false);
        post.setPublished(true);
        postRepository.save(post);
    }

    @Override
    public List<Post> getRelatedPost(Long categoryId) {
        return postRepository.getRelatedPost(categoryId);
    }

    private PostDto mapperDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    private Post mapperEntity(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }
}
