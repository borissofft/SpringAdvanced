package bg.softuni.kidscare.service;

import bg.softuni.kidscare.model.entity.UserEntity;
import bg.softuni.kidscare.model.entity.UserRoleEntity;
import bg.softuni.kidscare.model.enums.UserRoleEnum;
import bg.softuni.kidscare.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserDetailsServiceTest {

    private final String NOT_EXISTING_NAME = "NoName";

    private ApplicationUserDetailsService toTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new ApplicationUserDetailsService(
                this.mockUserRepository
        );
    }

    @Test
    void testUserFound() {

        // ARRANGE
        UserRoleEntity testAdminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity testTeacherRole = new UserRoleEntity().setRole(UserRoleEnum.TEACHER);
        UserRoleEntity testNormalRole = new UserRoleEntity().setRole(UserRoleEnum.NORMAL);

        String EXISTING_NAME = "admin";
        UserEntity testUserEntity = new UserEntity().
                setUsername(EXISTING_NAME).
                setPassword("12345").
                setRoles(Set.of(testAdminRole, testTeacherRole, testNormalRole));


        when(mockUserRepository.findByUsername(EXISTING_NAME)).
                thenReturn(Optional.of(testUserEntity));
        // EO: ARRANGE


        // ACT
        UserDetails adminDetails =
                toTest.loadUserByUsername(EXISTING_NAME);
        // EO: ACT

        // ASSERT
        Assertions.assertNotNull(adminDetails);
        Assertions.assertEquals(EXISTING_NAME, adminDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(), adminDetails.getPassword());

        Assertions.assertEquals(3,
                adminDetails.getAuthorities().size(),
                "The authorities are supposed to be just two - ADMIN/TEACHER/NORMAL.");

        assertRole(adminDetails.getAuthorities(), "ROLE_ADMIN");
        assertRole(adminDetails.getAuthorities(), "ROLE_TEACHER");
        assertRole(adminDetails.getAuthorities(), "ROLE_NORMAL");
        // EO: ASSERT
    }

    private void assertRole(Collection<? extends GrantedAuthority> authorities,
                            String role) {
        authorities.
                stream().
                filter(a -> role.equals(a.getAuthority())).
                findAny().
                orElseThrow(() -> new AssertionFailedError("Role " + role + " not found!"));
    }


    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTING_NAME)
        );
    }
}
