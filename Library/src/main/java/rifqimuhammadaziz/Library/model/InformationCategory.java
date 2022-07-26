package rifqimuhammadaziz.Library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "infocategories")
public class InformationCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean isDeleted;
    private boolean isActivated;

    public InformationCategory(String name) {
        this.name = name;
        this.isDeleted = false;
        this.isActivated = true;
    }
}
