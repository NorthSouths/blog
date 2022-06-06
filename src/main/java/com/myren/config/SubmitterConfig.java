package com.myren.config;

import com.myren.submit.HDUSubmitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SubmitterConfig {
    @Bean
    public HDUSubmitter hduSubmitter1() throws IOException {
        return new HDUSubmitter("250225923","kazike123.");
    }
}
