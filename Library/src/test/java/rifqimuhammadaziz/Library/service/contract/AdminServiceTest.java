package rifqimuhammadaziz.Library.service.contract;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import rifqimuhammadaziz.Library.model.Admin;
import rifqimuhammadaziz.Library.repository.AdminRepository;
import rifqimuhammadaziz.Library.repository.RoleRepository;
import rifqimuhammadaziz.Library.service.implementation.AdminServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.BDDMockito.given;


class AdminServiceTest {

    @Mock private AdminRepository adminRepository;
    @Mock private RoleRepository roleRepository;
    @Mock private ModelMapper modelMapper;

    private AdminServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new AdminServiceImpl(
                adminRepository,
                roleRepository,
                modelMapper);
    }

    @Test
    void canFindAdminByUsername() {
        // Given
        List<Admin> admins = List.of(
                new Admin(
                        1L,
                        "Rifqi",
                        "Muhammad Aziz",
                        "rifqimuhammadaziz@gmail.com",
                        "password",
                        new Date(),
                        true,
                        null,
                        Arrays.asList(roleRepository.findByName("ADMIN")
                        )
                ),
                new Admin(
                        2L,
                        "Fiqi",
                        "Arifianto",
                        "fiqiarifianto@gmail.com",
                        "password",
                        new Date(),
                        true,
                        null,
                        Arrays.asList(roleRepository.findByName("ADMIN")
                        )
                ),
                new Admin(
                        3L,
                        "Dimas Putra",
                        "Wicaksana",
                        "dimasputra@gmail.com",
                        "password",
                        new Date(),
                        true,
                        null,
                        Arrays.asList(roleRepository.findByName("ADMIN")
                        )
                )
        );
        given(adminRepository.findAll()).willReturn(admins);

        // When
        System.out.println(adminRepository.findAll());
        Admin actual = underTest.findByUsername("dimasputra@gmail.com");

        // Then
//        var expected = "rifqi@gmail.com";
//        assertThat(actual.getUsername()).isEqualTo(expected);
    }
}