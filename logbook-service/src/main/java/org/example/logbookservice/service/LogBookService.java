package org.example.logbookservice.service;

import org.example.common.model.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogBookService {

    private static final Logger logger = LoggerFactory.getLogger(LogBookService.class);

    public String logShip(Ship ship) {
        LocalDateTime now = LocalDateTime.now();
        String logBookEntry = String.format(
                "Captain's log, stardate %s. %s is heading to %s at warp speed %s.",
                now,
                ship.getName(),
                ship.getDestination(),
                ship.getSpeed()
        );
        logger.info(logBookEntry);
        return logBookEntry;
    }
}
