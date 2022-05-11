package com.hookitup.DealHandler.cassandra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class DataStaxAstraProps {

    @Value("${datastax.astra.secure-connect-bundle}")
    private File secureConnectBundle;

    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProps astraProps) {
        return builder -> builder.withCloudSecureConnectBundle(secureConnectBundle.toPath());
    }

}
