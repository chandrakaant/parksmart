package com.ckp.parksmart.datastore.repository;

import com.ckp.parksmart.datastore.model.SlotModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends PagingAndSortingRepository<SlotModel,Integer>
{
    Optional<SlotModel> findBySlotIdAndSlotActiveTrue(int slotId);

    List<SlotModel> findByParkingAreaId(Integer parkingAreaId);
}
