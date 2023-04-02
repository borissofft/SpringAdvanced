package bg.softuni.kidscare.service;

import bg.softuni.kidscare.model.binding.UserApproveBindingModel;
import bg.softuni.kidscare.model.entity.UserEntity;
import bg.softuni.kidscare.model.entity.UserRoleEntity;
import bg.softuni.kidscare.model.enums.UserRequestTypeEnum;
import bg.softuni.kidscare.model.enums.UserRoleEnum;
import bg.softuni.kidscare.model.service.UserRegisterServiceModel;
import bg.softuni.kidscare.repository.UserRepository;
import bg.softuni.kidscare.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private UserRoleRepository mockUserRoleRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private ModelMapper mockModelMapper;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityArgumentCaptor;

    private UserService toTest;


    @BeforeEach
    void setUp() {
        this.toTest = new UserService(
                this.mockUserRepository,
                this.mockUserRoleRepository,
                this.mockPasswordEncoder,
                this.mockModelMapper
        );
    }

    @Test
    void testUserRegistrationAdmin_AdminGetAllRoles() {

        // ARRANGE

        String username = "user1";
        String email = "user1@gmail.com";
        String testPassword = "12345";
        String encodedPassword = "encoded_password";
//        UserRequestTypeEnum requestedType = UserRequestTypeEnum.Педагог;

        UserRoleEntity testAdminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity testTeacherRole = new UserRoleEntity().setRole(UserRoleEnum.TEACHER);
        UserRoleEntity testNormalRole = new UserRoleEntity().setRole(UserRoleEnum.NORMAL);
        List<UserRoleEntity> expectedRoles = new ArrayList<>(List.of(testAdminRole, testTeacherRole, testNormalRole));

        UserRegisterServiceModel testServiceModel = new UserRegisterServiceModel()
                .setUsername(username)
                .setEmail(email)
                .setPassword(testPassword);
//                .setRequestedType(requestedType);

        when(this.mockUserRepository.count()).thenReturn(0L);
        when(this.mockPasswordEncoder.encode(testServiceModel.getPassword())).thenReturn(encodedPassword);
        when(this.mockUserRoleRepository.findAll()).thenReturn(expectedRoles);


        //ACT
        this.toTest.registerUser(testServiceModel);

        //ASSERT
        verify(this.mockUserRepository).save(any());
        verify(this.mockUserRepository).save(this.userEntityArgumentCaptor.capture());

        UserEntity admin = this.userEntityArgumentCaptor.getValue();

        assertEquals(testServiceModel.getEmail(), admin.getEmail());
        assertEquals(encodedPassword, admin.getPassword());
        assertEquals(new HashSet<>(Set.of(testAdminRole, testTeacherRole, testNormalRole)), admin.getRoles());

    }


    @Test
    void testUserRegistrationClient_ClientGetNormalRole() {

        String username = "client";
        String email = "client@gmail.com";
        String testPassword = "12345";
        String encodedPassword = "encoded_password";
        UserRequestTypeEnum requestedType = UserRequestTypeEnum.Клиент;

        UserRegisterServiceModel testServiceModel = new UserRegisterServiceModel()
                .setUsername(username)
                .setEmail(email)
                .setPassword(testPassword)
                .setRequestedType(requestedType);

        when(this.mockModelMapper.map(testServiceModel, UserEntity.class)).thenReturn(new UserEntity()
                .setUsername(username)
                .setEmail(email)
                .setPassword(testPassword)
                .setRequestedType(requestedType));

        UserRoleEntity testNormalRole = new UserRoleEntity().setRole(UserRoleEnum.NORMAL);

        when(this.mockUserRepository.count()).thenReturn(1L);
        when(this.mockPasswordEncoder.encode(testServiceModel.getPassword())).thenReturn(encodedPassword);
        when(this.mockUserRoleRepository.findByRole((UserRoleEnum.NORMAL))).thenReturn(testNormalRole);

        this.toTest.registerUser(testServiceModel);

        verify(this.mockUserRepository).save(this.userEntityArgumentCaptor.capture());

        UserEntity client = this.userEntityArgumentCaptor.getValue();

        assertEquals(testServiceModel.getEmail(), client.getEmail());
        assertEquals(encodedPassword, client.getPassword());
        assertEquals((new HashSet<>(Collections.singleton(testNormalRole))), client.getRoles());
    }


//    @Test
//    void testUserApprove_Success() {
//
//        String searched = "client";
//
//        when(this.mockUserRepository.findByUsername(searched)).thenReturn()
//
//        UserApproveBindingModel testUser = new UserApproveBindingModel()
//                .setUsername(searched);
//
//        this.toTest.approveUser(testUser);
//    }
}
