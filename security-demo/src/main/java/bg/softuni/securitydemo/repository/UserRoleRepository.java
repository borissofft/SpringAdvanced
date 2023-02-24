package bg.softuni.securitydemo.repository;

import java.util.Optional;

import bg.softuni.securitydemo.model.entity.UserRoleEntity;
import bg.softuni.securitydemo.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
  Optional<UserRoleEntity> findUserRoleEntityByRole(UserRoleEnum role);

}
