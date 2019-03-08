package com.highpeak.parksmart.datastore.repository;

import com.highpeak.parksmart.datastore.model.ParkingModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends PagingAndSortingRepository<ParkingModel,Integer>
{

}
