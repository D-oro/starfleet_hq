package org.example.logbookservice.controller;

import org.example.common.model.Ship;
import org.example.logbookservice.service.LogBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LogBookControllerTest {

    @Mock
    private LogBookService logBookService;

    @InjectMocks
    private LogBookController logBookController;

    @Test
    void logShip_callsServiceAndReturnsValue() {
        Ship ship = new Ship();
        ship.setName("Enterprise");
        ship.setDestination("Vulcan");
        ship.setSpeed(6.7);
        String expectedLog = "stubbed log entry";
        when(logBookService.logShip(ship)).thenReturn(expectedLog);

        String result = logBookController.logShip(ship);

        assertEquals(expectedLog, result);
        verify(logBookService, times(1)).logShip(ship);
    }
}
