package com.highpeak.parksmart.datastore.repository;

import com.highpeak.parksmart.datastore.model.UserModel;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserModel, Integer> {

    UserModel findByEmail(String email);

    Optional<UserModel> findByEmailAndIsActiveTrue(String email);

}
