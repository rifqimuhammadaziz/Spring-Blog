package rifqimuhammadaziz.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

    @Size(min = 3, max = 20, message = "First Name must be 3-20 characters")
    private String firstName;

    @Size(min = 3, max = 20, message = "Last Name must be 3-20 characters")
    private String lastName;

    @Email(message = "Wrong email format")
    private String username;

    @Size(min = 5, max = 20, message = "Password must be 5-20 characters")
    private String password;
    private String retypePassword;
}
