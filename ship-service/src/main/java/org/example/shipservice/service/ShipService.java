package org.example.shipservice.service;

import org.example.common.model.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
public class ShipService {

    private static final Logger logger = LoggerFactory.getLogger(ShipService.class);

    private final Random random = new Random();
    private final RestTemplate restTemplate;

    private final String logBookBaseUrl;

    private final List<String> names = List.of(
            "Endeavour",
            "Voyager",
            "Odyssey",
            "Enterprise",
            "Aurora"
    );

    private final List<String> destinations = List.of(
            "Mars",
            "Jupiter",
            "Titan",
            "Europa",
            "Neptune"
    );

    public ShipService(
            RestTemplate restTemplate,
            @Value("${logbook.base-url:http://localhost:8082}") String logBookBaseUrl
    ) {
        this.restTemplate = restTemplate;
        this.logBookBaseUrl = logBookBaseUrl;
    }

    public Ship createRandomShip() {
        String name = names.get(random.nextInt(names.size()));
        String destination = destinations.get(random.nextInt(destinations.size()));
        double speed = 0.1 * (1 + random.nextInt(99)); // 0.1–9.9

        Ship ship = new Ship(name, destination, speed);

        try {
            restTemplate.postForEntity(logBookBaseUrl + "/log", ship, String.class);
            logger.info("Logged ship {} to logbook-service", ship.getName());
        } catch (RestClientException e) {
            logger.warn("Failed to log ship {} to logbook-service at {}", ship.getName(), logBookBaseUrl, e);
        }

        return ship;
    }
}
