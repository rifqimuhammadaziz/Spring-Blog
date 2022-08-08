package rifqimuhammadaziz.Library.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.Library.dto.AdminDto;
import rifqimuhammadaziz.Library.dto.AdminBasicInformation;
import rifqimuhammadaziz.Library.model.Admin;
import rifqimuhammadaziz.Library.model.Role;
import rifqimuhammadaziz.Library.repository.AdminRepository;
import rifqimuhammadaziz.Library.repository.RoleRepository;
import rifqimuhammadaziz.Library.service.contract.AdminService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @Override
//    public Admin findByEmail(String email) {
//        Admin admin = adminRepository.findByEmail(email);
//        System.out.println(admin);
//        return admin;
//    }

    @Override
    public List<AdminBasicInformation> findAll() {
        List<AdminBasicInformation> admins = new ArrayList<>();
        for (Admin admin : adminRepository.findAll()) {
            AdminBasicInformation details = mapperDetails(admin);
            admins.add(details);
        }
        return admins;
    }

    public List<Admin> findAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public AdminBasicInformation getLoginDetails(String username) {
        AdminBasicInformation adminBasicInformation = new AdminBasicInformation();
        Admin admin = adminRepository.findByUsername(username);
        adminBasicInformation.setUsername(admin.getUsername());
        adminBasicInformation.setFirstName(admin.getFirstName());
        adminBasicInformation.setLastName(admin.getLastName());
        return adminBasicInformation;
    }

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = new Admin();
        Role role = roleRepository.findByName("ADMIN");
        if (role != null) {
            admin.setFirstName(adminDto.getFirstName());
            admin.setLastName(adminDto.getLastName());
            admin.setUsername(adminDto.getUsername());
            admin.setPassword(adminDto.getPassword());
            admin.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
        }
        return adminRepository.save(admin);
    }

    public Admin mapperEntity(AdminBasicInformation adminBasicInformation) {
        Admin admin = modelMapper.map(adminBasicInformation, Admin.class);
        return admin;
    }

    public AdminBasicInformation mapperDetails(Admin admin) {
        AdminBasicInformation adminBasicInformation = modelMapper.map(admin, AdminBasicInformation.class);
        return adminBasicInformation;
    }
}
