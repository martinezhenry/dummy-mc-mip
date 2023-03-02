package com.hvs.dummy.mip;

import org.jpos.iso.ISOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MipApplication {
    public static void main(String[] args) throws ISOException {
        SpringApplication.run(MipApplication.class, args);
    }
}
