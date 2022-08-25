package rifqimuhammadaziz.Library.service.implementation;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.Library.dto.AdminDto;
import rifqimuhammadaziz.Library.dto.AdminBasicInformation;
import rifqimuhammadaziz.Library.exception.NotFoundException;
import rifqimuhammadaziz.Library.model.Admin;
import rifqimuhammadaziz.Library.model.Role;
import rifqimuhammadaziz.Library.repository.AdminRepository;
import rifqimuhammadaziz.Library.repository.RoleRepository;
import rifqimuhammadaziz.Library.service.contract.AdminService;

import java.util.*;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;

    @Override
    public List<AdminBasicInformation> findAllAdminBasicInformation() {
        List<AdminBasicInformation> admins = new ArrayList<>();
        for (Admin admin : adminRepository.findAll()) {
            AdminBasicInformation details = mapperDetails(admin);
            admins.add(details);
        }
        return admins;
    }

    @Override
    public List<Admin> findAllAdmin() {
        return adminRepository.findAll();
    }

    public Admin findById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
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
            admin = mapperEntityFromDto(adminDto);
            admin.setActivated(false);
            admin.setCreatedDate(new Date());
            admin.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
        }
        return adminRepository.save(admin);
    }

    @Override
    public void enableAdminById(Long id) {
        Admin admin = adminRepository.findById(id).get();
        admin.setActivated(true);
        adminRepository.save(admin);
    }

    @Override
    public void disableAdminById(Long id) {
        Admin admin = adminRepository.findById(id).get();
        admin.setActivated(false);
        adminRepository.save(admin);
    }

    public Admin mapperEntityFromDto(AdminDto adminDto) {
        Admin admin = modelMapper.map(adminDto, Admin.class);
        return admin;
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
