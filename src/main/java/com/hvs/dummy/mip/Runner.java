package com.hvs.dummy.mip;

import lombok.extern.slf4j.Slf4j;
import org.jpos.q2.Q2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Runner implements CommandLineRunner {

    private Q2 server;


    @Override
    public void run(String... args) throws Exception {
        log.info("running dummy mip");
        /*String deployPath = Objects.requireNonNull(
                this.getClass().getClassLoader().getResource("deploy")
        ).getPath();*/
        server = new Q2("./deploy");
        server.start();
    }
}
