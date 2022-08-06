package rifqimuhammadaziz.Library.service.contract;

import rifqimuhammadaziz.Library.dto.AdminDto;
import rifqimuhammadaziz.Library.dto.AdminLoginDetails;
import rifqimuhammadaziz.Library.model.Admin;

public interface AdminService {
//    Admin findByEmail(String email);
    Admin findByUsername(String username);
    AdminLoginDetails getLoginDetails(String username);
    Admin save(AdminDto adminDto);
}
