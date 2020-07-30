package org.sharefiles.root.repository;

import org.sharefiles.root.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    //@Query(SELECT * FROM role )
    List<UserRole> findByRoleName(String userRole);

}
