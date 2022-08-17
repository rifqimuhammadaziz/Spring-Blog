package rifqimuhammadaziz.Dashboard.seeder;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import rifqimuhammadaziz.Library.exception.NotFoundException;
import rifqimuhammadaziz.Library.model.Admin;
import rifqimuhammadaziz.Library.model.Post;
import rifqimuhammadaziz.Library.model.PostCategory;
import rifqimuhammadaziz.Library.model.Role;
import rifqimuhammadaziz.Library.repository.AdminRepository;
import rifqimuhammadaziz.Library.repository.PostCategoryRepository;
import rifqimuhammadaziz.Library.repository.PostRepository;
import rifqimuhammadaziz.Library.repository.RoleRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private AdminRepository adminRepository;
    private RoleRepository roleRepository;
    private PostCategoryRepository postCategoryRepository;
    private PostRepository postRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        roleSeeder();
        adminSeeder();
        postCategorySeeder();
        postSeeder();
    }

    private void adminSeeder() {
        Role role = roleRepository.findByName("ADMIN");
        if (adminRepository.count() == 0) {
            if (role != null) {
                Admin admin1 = Admin.builder()
                        .firstName("Rifqi")
                        .lastName("Muhammad Aziz")
                        .username("rifqi@gmail.com")
                        .password(passwordEncoder.encode("password"))
                        .createdDate(new Date())
                        .activated(true)
                        .roles(Arrays.asList(role))
                        .build();
                Admin admin2 = Admin.builder()
                        .firstName("Bagas")
                        .lastName("Dwi Yulianto")
                        .username("bagas@gmail.com")
                        .password(passwordEncoder.encode("password"))
                        .createdDate(new Date())
                        .activated(true)
                        .roles(Arrays.asList(role))
                        .build();
                List<Admin> admins = Arrays.asList(admin1, admin2);
                adminRepository.saveAll(admins);
            }
        }
        System.out.println("Total Admin: " + adminRepository.count());
    }

    private void roleSeeder() {
        if (roleRepository.count() == 0) {
            Role role = new Role();
            role.setName("ADMIN");
            roleRepository.save(role);
        }
        System.out.println("Total Role: " + roleRepository.count());
    }

    private void postCategorySeeder() {
        if (postCategoryRepository.count() == 0) {
            PostCategory category1 = new PostCategory("Software Engineer");
            PostCategory category2 = new PostCategory("Artificial Intelligence");
            PostCategory category3 = new PostCategory("Data Science");
            PostCategory category4 = new PostCategory("Networking");
            List<PostCategory> categories = Arrays.asList(category1, category2, category3, category4);
            postCategoryRepository.saveAll(categories);
        }
        System.out.println("Total Post Categories: " + postCategoryRepository.count());
    }

    private void postSeeder() {
        Admin admin = adminRepository.findByUsername("rifqi@gmail.com").orElseThrow(() -> new NotFoundException("Not found"));
        PostCategory category = postCategoryRepository.findById(1L).get();
        if (postRepository.count() == 0) {
            Post post1 = new Post();
            post1.setAuthor(admin.getFullName());
            post1.setTitle("Sofware Development Life Cycle (SDLC) Menggunakan Metode Waterfall");
            post1.setPostCategory(category);
            post1.setContent("Metode Waterfall merupakan pendekatan SDLC paling awal yang digunakan untuk pengembangan " +
                    "perangkat lunak. Urutan dalam Metode Waterfall bersifat serial yang dimulai dari proses perencanaan, " +
                    "analisa, desain, dan implementasi pada sistem.");
            post1.setCreatedDate(new Date());
            post1.setPublished(true);
            post1.setDeleted(false);

            Post post2 = new Post();
            post2.setAuthor(admin.getFullName());
            post2.setTitle("Sofware Development Life Cycle (SDLC) Menggunakan Metode Prototype");
            post2.setPostCategory(category);
            post2.setContent("Metode prototype adalah metode yang memungkinkan pengguna atau user memiliki gambaran awal " +
                    "tentang perangkat lunak yang akan dikembangkan, serta pengguna dapat melakukan pengujian di awal sebelum " +
                    "perangkat lunak dirilis.");
            post2.setCreatedDate(new Date());
            post2.setPublished(true);
            post2.setDeleted(false);

            List<Post> posts = Arrays.asList(post1, post2);
            postRepository.saveAll(posts);
        }
        System.out.println("Total Posts: " + postRepository.count());
    }
}
