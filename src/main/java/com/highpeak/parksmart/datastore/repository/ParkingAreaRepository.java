package com.highpeak.parksmart.datastore.repository;

import com.highpeak.parksmart.datastore.model.ParkingAreaModel;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingAreaRepository extends PagingAndSortingRepository<ParkingAreaModel, Integer>
{
}
