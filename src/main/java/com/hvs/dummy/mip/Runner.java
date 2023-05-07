package com.hvs.dummy.mip;

import lombok.extern.slf4j.Slf4j;
import org.jpos.q2.Q2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
@Slf4j
public class Runner implements CommandLineRunner {

    private Q2 server;


    @Override
    public void run(String... args) throws Exception {
        log.info("running dummy mip");
        var deployPath = Files.createTempDirectory("tmp-deploy");
        deployPath.toFile().deleteOnExit();

        var deployResource = new ClassPathResource("deploy");
        try (Stream<Path> stream = Files.walk(Paths.get(deployResource.getPath())) ) {
            stream.forEach(source -> {
                var destination = Paths.get(deployPath.toString(), source.getFileName().toString());
                try {
                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });
        }

        System.out.println(deployPath);
        server = new Q2(deployPath.toString());
        server.start();
    }
}
