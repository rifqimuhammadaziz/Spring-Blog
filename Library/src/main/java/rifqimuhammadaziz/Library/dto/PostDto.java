package rifqimuhammadaziz.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rifqimuhammadaziz.Library.model.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private Category category;
    private String content;
}
