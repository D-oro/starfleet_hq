package org.example.shipservice.service;

import org.example.common.model.Ship;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ShipServiceTest {

    @Test
    void createRandomShip_returnsShipAndAttemptsLog() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        ShipService service = new ShipService(restTemplate, "http://localhost:8082");

        Ship ship = service.createRandomShip();

        assertThat(ship.getName()).isNotBlank();
        assertThat(ship.getDestination()).isNotBlank();
        assertThat(ship.getSpeed()).isGreaterThan(0.0).isLessThan(10.0);

        ArgumentCaptor<Ship> captor = ArgumentCaptor.forClass(Ship.class);
        verify(restTemplate).postForEntity(
                org.mockito.ArgumentMatchers.eq("http://localhost:8082/log"),
                captor.capture(),
                org.mockito.ArgumentMatchers.eq(String.class)
        );
        assertThat(captor.getValue().getName()).isEqualTo(ship.getName());
    }
}
