package com.ckp.parksmart.datastore.repository;

import   com.ckp.parksmart.datastore.model.ParkingAreaModel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingAreaRepository extends PagingAndSortingRepository<ParkingAreaModel, Integer>
{

    List<ParkingAreaModel> findAll();

    @Query(value="select * from ps_parking_area where parking_area_id =:id and parking_area_is_active = true",nativeQuery = true)
    Optional<ParkingAreaModel> findByParkingAreaIdAndParkingAreaIsActiveTrue(@Param("id") int parkingAreaId);

    List<ParkingAreaModel> findByParkingAreaIsActiveTrue();
}
