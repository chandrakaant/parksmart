package com.ckp.parksmart.datastore.repository;

import com.ckp.parksmart.datastore.model.RoleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Integer> {

    /**
     * finding role by id
     * @param roleId
     * @return
     */
    Optional<RoleEntity> findByRoleId(int roleId);

    Optional<RoleEntity> findByRoleName(String roleName);
}
