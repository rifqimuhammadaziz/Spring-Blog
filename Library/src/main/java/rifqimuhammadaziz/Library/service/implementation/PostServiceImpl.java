package rifqimuhammadaziz.Library.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import rifqimuhammadaziz.Library.dto.PostDto;
import rifqimuhammadaziz.Library.model.Post;
import rifqimuhammadaziz.Library.repository.PostRepository;
import rifqimuhammadaziz.Library.service.contract.PostService;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public Post save(PostDto postDto) {
        Post post = mapperEntity(postDto);
        return postRepository.save(post);
    }

    @Override
    public Post update(PostDto postDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
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
