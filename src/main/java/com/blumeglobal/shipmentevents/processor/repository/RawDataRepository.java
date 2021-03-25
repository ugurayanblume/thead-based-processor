package com.blumeglobal.shipmentevents.processor.repository;

import com.blumeglobal.shipmentevents.processor.model.dto.RawData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawDataRepository extends CrudRepository<RawData, Long> {
}
