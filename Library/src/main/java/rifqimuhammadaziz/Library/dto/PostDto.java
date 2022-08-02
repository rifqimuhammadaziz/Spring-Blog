package rifqimuhammadaziz.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rifqimuhammadaziz.Library.model.PostCategory;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private PostCategory postCategory;
    private String content;
    private boolean isPublished;
    private boolean isDeleted;
}
