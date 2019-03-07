package com.highpeak.parksmart.datastore.repository;

import com.highpeak.parksmart.datastore.model.UserModel;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRespository extends PagingAndSortingRepository<UserModel, Integer> {

}
