package org.example.logbookservice.service;

import org.example.common.model.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LogBookServiceTest {

    private LogBookService logBookService;

    @BeforeEach
    void setUp() {
        logBookService = new LogBookService();
    }

    @Test
    void logShip_returnsFormattedLogEntryContainingShipDetails() {
        Ship ship = new Ship();
        ship.setName("Enterprise");
        ship.setDestination("Vulcan");
        ship.setSpeed(5.7);

        String result = logBookService.logShip(ship);

        assertThat(result).contains("Captain's log, stardate");
        assertThat(result).contains("Enterprise");
        assertThat(result).contains("Vulcan");
        assertThat(result).contains("5.7");
    }
}
