package com.ckp.parksmart.datastore.repository;

import com.ckp.parksmart.datastore.model.ParkingModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingRepository extends PagingAndSortingRepository<ParkingModel,Integer>
{

    Optional<ParkingModel> findByVehicleIdAndParkingIsActiveTrue(int vehicleId);
    Optional<ParkingModel> findByParkingId(int parkingId);

    Optional<ParkingModel> findByParkingIdAndParkingIsActiveTrue(int parkingId);
}
