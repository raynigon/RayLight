package com.raynigon.raylight.model.config;

import java.net.InetAddress;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties("app.artnet")
public class ArtNetConfigProperties {

    private List<ArtNetOutputConfig> outputs;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArtNetOutputConfig {
        private String name;
        private InetAddress address;
        private int[] universes;
    }
}
