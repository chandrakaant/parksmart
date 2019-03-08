package com.highpeak.parksmart.datastore.repository;

import com.highpeak.parksmart.datastore.model.ParkingAreaModel;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingAreaRepository extends PagingAndSortingRepository<ParkingAreaModel, Integer>
{
    Optional<ParkingAreaModel> findByParkingAreaId(int parkingAreaId);

    List<ParkingAreaModel> findAll();
}
