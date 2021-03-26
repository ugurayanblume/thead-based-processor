package com.blumeglobal.shipmentevents.processor.service;

import com.blumeglobal.shipmentevents.processor.repository.RawDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class JavaThreadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaThreadService.class);

    @Autowired RawDataRepository rawDataRepository;
    @Autowired Producer producer;

    public void start() throws InterruptedException {

        // 1.Step : Make sure that data is free
        rawDataRepository.deleteAll();
        Thread.sleep(5000);
        // SEQUENTIAL PRODUCERS ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~


//        // Start with sequential data for 1.000
//        producer.createSequentialRandomRecords(1000L, 10L);
//        rawDataRepository.deleteAll();
//        Thread.sleep(5000);
//
//        // Start with sequential data for 10.000
//        producer.createSequentialRandomRecords(10000L, 10L);
//        rawDataRepository.deleteAll();
//        Thread.sleep(5000);
//
//        // Start with sequential data for 100.000
//        producer.createSequentialRandomRecords(100000L, 10L);
//        rawDataRepository.deleteAll();
//        Thread.sleep(5000);
//
//        // Start with sequential data for 1.000.000
//        producer.createSequentialRandomRecords(1000000L, 10L);
//        rawDataRepository.deleteAll();
//        Thread.sleep(5000);

        // THREAD PRODUCERS ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~

        Scanner scanner = new Scanner(System.in);

//        // Start with sequential data for 1.000
//        producer.createMultiThreadRandomRecords(1000L, 10L, 2);
//        System.out.println("Please press enter to continue");
//        scanner.nextLine();
//        rawDataRepository.deleteAll();
//        Thread.sleep(1000);
//
//        // Start with sequential data for 1.000
//        producer.createMultiThreadRandomRecords(1000L, 10L, 10);
//        System.out.println("Please press enter to continue");
//        scanner.nextLine();
//        rawDataRepository.deleteAll();
//        Thread.sleep(1000);
//
//
//        // Start with sequential data for 10.000
//        producer.createMultiThreadRandomRecords(10000L, 10L, 2);
//        System.out.println("Please press enter to continue");
//        scanner.nextLine();
//        rawDataRepository.deleteAll();
//        Thread.sleep(1000);
//
//
//        // Start with sequential data for 10.000
//        producer.createMultiThreadRandomRecords(10000L, 10L, 10);
//        System.out.println("Please press enter to continue");
//        scanner.nextLine();
//        rawDataRepository.deleteAll();
//        Thread.sleep(1000);
//
//        // Start with sequential data for 10.000
//        producer.createMultiThreadRandomRecords(10000L, 10L, 100);
//        System.out.println("Please press enter to continue");
//        scanner.nextLine();
//        rawDataRepository.deleteAll();
//        Thread.sleep(1000);
//
//        // Start with sequential data for 100.000
//        producer.createMultiThreadRandomRecords(100000L, 10L, 2);
//        System.out.println("Please press enter to continue");
//        scanner.nextLine();
//        rawDataRepository.deleteAll();
//        Thread.sleep(1000);
//
//
//        // Start with sequential data for 100.000
//        producer.createMultiThreadRandomRecords(100000L, 50L, 10, true);
//        System.out.println("Please press enter to continue");
//        scanner.nextLine();
//        rawDataRepository.deleteAll();
//        Thread.sleep(1000);
//
//        // Start with sequential data for 100.000
//        producer.createMultiThreadRandomRecords(100000L, 50L, 100,true);
//        System.out.println("Please press enter to continue");
//        scanner.nextLine();
//        rawDataRepository.deleteAll();
//        Thread.sleep(1000);
//
//        // Start with sequential data for 100.000
//        producer.createMultiThreadRandomRecords(100000L, 50L, 500,true);
//        System.out.println("Please press enter to continue");
//        scanner.nextLine();
//        rawDataRepository.deleteAll();
//        Thread.sleep(1000);
//
//        // Start with sequential data for 1.000.000
//        producer.createMultiThreadRandomRecords(1000000L, 50L, 2, true);
//        System.out.println("Please press enter to continue");
//        scanner.nextLine();
//        rawDataRepository.deleteAll();
//        Thread.sleep(1000);


        // Start with sequential data for 1.000.000
        producer.createMultiThreadRandomRecords(1000000L, 10L, 10,true);
        System.out.println("Please press enter to continue");
        scanner.nextLine();
        rawDataRepository.deleteAll();
        Thread.sleep(1000);

        // Start with sequential data for 1.000.000
        producer.createMultiThreadRandomRecords(1000000L, 50L, 100, false);
        System.out.println("Please press enter to continue");
        scanner.nextLine();
        rawDataRepository.deleteAll();
        Thread.sleep(1000);

        // Start with sequential data for 1.000.000
        producer.createMultiThreadRandomRecords(1000000L, 50L, 500, false);
        System.out.println("Please press enter to continue");
        scanner.nextLine();
        rawDataRepository.deleteAll();
        Thread.sleep(1000);

    }
}
