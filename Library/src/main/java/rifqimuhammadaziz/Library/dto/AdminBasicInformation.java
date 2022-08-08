package rifqimuhammadaziz.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rifqimuhammadaziz.Library.model.Role;

import java.util.Collection;
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

    public String fullName() {
        return firstName + " " + lastName;
    }
}
