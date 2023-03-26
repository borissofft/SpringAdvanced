package bg.softuni.kidscare.service;

import bg.softuni.kidscare.model.entity.UserRoleEntity;
import bg.softuni.kidscare.model.enums.UserRoleEnum;
import bg.softuni.kidscare.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Order(0)
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.dbInit();
    }


    public void dbInit() {

        if (this.userRoleRepository.count() != 0) {
            return;
        }

        List<UserRoleEntity> roles = new ArrayList<>();

        roles.add(new UserRoleEntity().setRole(UserRoleEnum.ADMIN));
        roles.add(new UserRoleEntity().setRole(UserRoleEnum.TEACHER));
        roles.add(new UserRoleEntity().setRole(UserRoleEnum.NORMAL));

        this.userRoleRepository.saveAllAndFlush(roles);

    }

}
