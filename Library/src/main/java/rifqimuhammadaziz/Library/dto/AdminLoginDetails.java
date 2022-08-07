package rifqimuhammadaziz.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginDetails {
    private String firstName;
    private String lastName;
    private String username;

    public String fullName() {
        return firstName + " " + lastName;
    }
}
