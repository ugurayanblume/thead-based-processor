package com.blumeglobal.shipmentevents.processor;

import com.blumeglobal.shipmentevents.processor.service.JavaThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessorApplication implements CommandLineRunner {

    @Autowired JavaThreadService javaThreadService;
    public static void main(String[] args) {
        SpringApplication.run(ProcessorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        javaThreadService.start();
    }
}
