package com.highpeak.parksmart.datastore.repository;

import com.highpeak.parksmart.datastore.model.VehicleModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends PagingAndSortingRepository<VehicleModel, Integer> {

    @Query(nativeQuery = true, value = "select * from parksmart.ps_vehicle_details v where v.v_number=:number and v.v_is_active=1 limit 1")
    Optional<VehicleModel> findByVehicleNumberAndIsActiveTrue(@Param("number") String number);

    Optional<VehicleModel> findByIdAndIsActiveTrue(int id);

    Optional<VehicleModel> findByUserId(int userId);
}
