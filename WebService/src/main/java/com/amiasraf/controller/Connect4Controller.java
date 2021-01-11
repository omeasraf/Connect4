package com.amiasraf.controller;

import com.amiasraf.connect4.Connect4Service;
import com.amiasraf.connect4.Connect4Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Connect4Controller {
    @GetMapping("/game/connect4")
    public Connect4Status playGame(@RequestParam String moves){

        Connect4Service service = new Connect4Service();
        Connect4Status status = service.getStatus(moves);
        return status;
    }

}
