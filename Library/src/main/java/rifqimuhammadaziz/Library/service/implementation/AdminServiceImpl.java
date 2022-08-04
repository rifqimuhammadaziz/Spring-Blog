package rifqimuhammadaziz.Library.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.Library.dto.AdminDto;
import rifqimuhammadaziz.Library.model.Admin;
import rifqimuhammadaziz.Library.repository.AdminRepository;
import rifqimuhammadaziz.Library.service.contract.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin findByEmail(String email) {
        return null;
    }

    @Override
    public Admin save(AdminDto adminDto) {
        return null;
    }
}
