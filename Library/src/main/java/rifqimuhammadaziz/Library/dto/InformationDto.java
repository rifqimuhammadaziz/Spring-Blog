package rifqimuhammadaziz.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rifqimuhammadaziz.Library.model.InformationCategory;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationDto {
    private Long id;
    private String title;
    private InformationCategory informationCategory;
    private String content;
    private boolean isDeleted;
}
