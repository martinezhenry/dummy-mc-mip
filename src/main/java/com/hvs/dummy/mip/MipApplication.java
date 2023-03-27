package com.hvs.dummy.mip;

import org.jpos.iso.ISOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MipApplication {
    public static void main(String[] args) throws ISOException, IOException {
        SpringApplication.run(MipApplication.class, args);
    }
}
