package rifqimuhammadaziz.Library.service.contract;

import rifqimuhammadaziz.Library.dto.AdminDto;
import rifqimuhammadaziz.Library.dto.AdminBasicInformation;
import rifqimuhammadaziz.Library.model.Admin;

import java.util.List;

public interface AdminService {
//    Admin findByEmail(String email);
    List<AdminBasicInformation> findAll();
    Admin findByUsername(String username);
    AdminBasicInformation getLoginDetails(String username);
    Admin save(AdminDto adminDto);
    void enableById(Long id);
    void disableById(Long id);
}
