package bg.softuni.kidscare.repository;

import bg.softuni.kidscare.model.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {



}
