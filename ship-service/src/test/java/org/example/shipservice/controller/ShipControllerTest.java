package org.example.shipservice.controller;

import org.example.common.model.Ship;
import org.example.shipservice.service.ShipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShipController.class)
class ShipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipService shipService;

    @Test
    void getShip_returnsShipFromService() throws Exception {
        Ship ship = new Ship("Voyager", "Europa", 7.5);
        when(shipService.createRandomShip()).thenReturn(ship);

        mockMvc.perform(get("/ship").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Voyager"))
                .andExpect(jsonPath("$.destination").value("Europa"))
                .andExpect(jsonPath("$.speed").value(7.5));
    }
}

