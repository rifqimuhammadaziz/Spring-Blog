package rifqimuhammadaziz.Library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "postcategories",
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class PostCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private boolean isDeleted;
    private boolean isActivated;

    public PostCategory(String name) {
        this.name = name;
        this.isDeleted = false;
        this.isActivated = true;
    }
}
