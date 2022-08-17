package rifqimuhammadaziz.Library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "informations", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    private InformationCategory informationCategory;

    @Column(name = "content", nullable = false)
    @Lob()
    private String content;

    private LocalDateTime createdDate;
    private boolean isDeleted;
}
