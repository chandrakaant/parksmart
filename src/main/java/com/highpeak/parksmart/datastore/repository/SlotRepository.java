package com.highpeak.parksmart.datastore.repository;

import com.highpeak.parksmart.datastore.model.SlotModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends PagingAndSortingRepository<SlotModel,Integer>
{
}
