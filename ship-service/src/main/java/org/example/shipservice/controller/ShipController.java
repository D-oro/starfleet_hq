package org.example.shipservice.controller;

import org.example.common.model.Ship;
import org.example.shipservice.service.ShipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShipController {

    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/ship")
    public Ship getShip() {
        return shipService.createRandomShip();
    }
}
