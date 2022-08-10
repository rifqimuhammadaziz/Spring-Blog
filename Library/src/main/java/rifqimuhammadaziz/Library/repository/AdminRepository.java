package rifqimuhammadaziz.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rifqimuhammadaziz.Library.dto.AdminBasicInformation;
import rifqimuhammadaziz.Library.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
//    Admin findByEmail(String email);
    Admin findByUsername(String username);
}
