package rifqimuhammadaziz.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rifqimuhammadaziz.Library.model.InformationCategory;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationDto {
    private Long id;
    private String author;
    private String title;
    private InformationCategory informationCategory;
    private String content;
    private LocalDateTime createdDate;
    private boolean isDeleted;
}
