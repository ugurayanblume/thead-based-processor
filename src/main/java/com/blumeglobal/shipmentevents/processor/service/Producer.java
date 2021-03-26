package com.blumeglobal.shipmentevents.processor.service;

import com.blumeglobal.shipmentevents.processor.enums.DataSizeEnum;
import com.blumeglobal.shipmentevents.processor.enums.StateEnum;
import com.blumeglobal.shipmentevents.processor.model.dto.RawData;
import com.blumeglobal.shipmentevents.processor.repository.RawDataRepository;
import com.blumeglobal.shipmentevents.processor.utils.DataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Autowired RawDataRepository rawDataRepository;

    public void createSequentialRandomRecords(Long rows, Long size){

        LOGGER.info("----------------------------------------------------------------------------");
        LOGGER.info("Create Sequential Random Records Started " + new Date().toString());
        Instant start = Instant.now();

        for (Long i = 0L; i < rows; i += size){
            saveData(i, i + size);
        }

        Instant end = Instant.now();
        LOGGER.info("Create Sequential Random Records Ends " + new Date().toString());
        LOGGER.info("Time difference is :" + ( end.toEpochMilli() - start.toEpochMilli() )/1000.0 + " secs for " + rows + " records");
    }

    public void createMultiThreadRandomRecords(Long rows, Long size, Integer threads, Boolean isBulSave) throws InterruptedException {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threads);

        LOGGER.info("----------------------------------------------------------------------------");
        LOGGER.info("Create Thread Based Random Records Started " + new Date().toString());
        Instant start = Instant.now();

        for (Long i = 0L; i < rows; i += size) {
            final Long r = i;
            if (isBulSave) {
                executor.execute(() -> saveData(r, r + size));
            } else {
                executor.execute(() -> saveBulkData(r, r + size));
            }

        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        Instant end = Instant.now();
        LOGGER.info("Create Thread Based Random Records Ends " + new Date().toString());
        LOGGER.info("Time difference is :" + ( end.toEpochMilli() - start.toEpochMilli() )/1000.0 + " secs for " + rows + " records and " + threads + " threads");
    }

    private void saveBulkData(Long rowStartId, Long rowEndId){
        List<RawData> rawDataList = new ArrayList<>();
        for (Long i = rowStartId; i < rowEndId; i++) {
            rawDataList.add(
                    RawData.builder()
                            .id(i)
                            .data(DataUtils.getData(DataSizeEnum.values()[Integer.parseInt(String.valueOf(i % 3))]))
                            .createdDate(System.currentTimeMillis())
                            .createdDateInStr(new Date().toString())
                            .state(StateEnum.UNPROCESSED)
                            .build()
            );
        }
        rawDataRepository.saveAll(rawDataList);
    }

    private void saveData(Long rowStartId, Long rowEndId){
        for (Long i = rowStartId; i < rowEndId; i++) {
            rawDataRepository.save(
                    RawData.builder()
                            .id(i)
                            .data(DataUtils.getData(DataSizeEnum.values()[Integer.parseInt(String.valueOf(i % 3))]))
                            .createdDate(System.currentTimeMillis())
                            .createdDateInStr(new Date().toString())
                            .state(StateEnum.UNPROCESSED)
                            .build()
            );
        }
    }
}
