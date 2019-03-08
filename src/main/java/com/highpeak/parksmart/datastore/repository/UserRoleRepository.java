package com.highpeak.parksmart.datastore.repository;


import com.highpeak.parksmart.datastore.model.UserRoleModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends PagingAndSortingRepository<UserRoleModel, Integer> {

    @Query(nativeQuery = true, value = "select * from parksmart.ps_user_role ur where ur.ps_user_id=:id limit 1")
    UserRoleModel findRoleIdByUserId(@Param("id") int id);

    @Query(nativeQuery = true, value = "select r.r_name from ps_user_role ur inner join ps_role r on \n" +
            "ur.ur_role_id=r_id where ur.ur_user_id=:userId")
    String getRoleByUserProfileId(@Param("userId") Integer userId);
}
