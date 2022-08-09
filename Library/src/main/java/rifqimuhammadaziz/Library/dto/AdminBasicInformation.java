package rifqimuhammadaziz.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rifqimuhammadaziz.Library.model.Role;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminBasicInformation {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Set<Role> roles;
    private Date createdDate;
    private boolean activated;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
