package rifqimuhammadaziz.Library.service.contract;

import rifqimuhammadaziz.Library.dto.AdminDto;
import rifqimuhammadaziz.Library.model.Admin;

public interface AdminService {
    Admin findByEmail(String email);
    Admin save(AdminDto adminDto);
}
