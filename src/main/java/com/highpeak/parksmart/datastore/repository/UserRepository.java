package com.highpeak.parksmart.datastore.repository;

import com.highpeak.parksmart.datastore.model.UserModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserModel, Integer> {

    UserModel findByEmail(String email);

    /**
     * finds user by id
     * @param id
     * @return
     */
    Optional<UserModel> findById(int id);

    /**
     * find user by user id and active status true
     * @param id user id
     * @return optional user model
     */
    Optional<UserModel> findByIdAndIsActiveTrue(int id);


    Optional<UserModel> findByEmailAndIsActiveTrue(String email);

}
