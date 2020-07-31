package org.sharefiles.root.repository;

import org.sharefiles.root.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    //@Query(SELECT * FROM role )
    List<UserRole> findByRoleName(String userRole);

}
