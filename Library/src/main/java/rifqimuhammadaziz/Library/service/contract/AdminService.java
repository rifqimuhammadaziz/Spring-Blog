package rifqimuhammadaziz.Library.service.contract;

import rifqimuhammadaziz.Library.dto.AdminDto;
import rifqimuhammadaziz.Library.dto.AdminBasicInformation;
import rifqimuhammadaziz.Library.model.Admin;

import java.util.List;

public interface AdminService {
//    Admin findByEmail(String email);
    List<AdminBasicInformation> findAllAdminBasicInformation();
    List<Admin> findAllAdmin();
    Admin findByUsername(String username);
    AdminBasicInformation getLoginDetails(String username);
    Admin save(AdminDto adminDto);
    void enableAdminById(Long id);
    void disableAdminById(Long id);
}
