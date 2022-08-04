package rifqimuhammadaziz.Library.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.Library.dto.AdminDto;
import rifqimuhammadaziz.Library.model.Admin;
import rifqimuhammadaziz.Library.model.Role;
import rifqimuhammadaziz.Library.repository.AdminRepository;
import rifqimuhammadaziz.Library.repository.RoleRepository;
import rifqimuhammadaziz.Library.service.contract.AdminService;

import java.util.Arrays;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = new Admin();
        Role role = roleRepository.findByName("ADMIN");
        if (role != null) {
            admin.setFirstName(adminDto.getFirstName());
            admin.setLastName(adminDto.getLastName());
            admin.setEmail(adminDto.getEmail());
            admin.setPassword(adminDto.getPassword());
            admin.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
            return adminRepository.save(admin);
        }
        return null;
    }
}
