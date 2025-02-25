package com.miniproject.sockettools.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.sockettools.service.PortCheckerService;

@RestController
@RequestMapping("api/port")
public class PortCheckerController {
    private final PortCheckerService portCheckerService;

    public PortCheckerController(PortCheckerService portCheckerService) {
        this.portCheckerService = portCheckerService;
    }

    @GetMapping("/check")
    public List<Integer> checkAvailablePorts() {
        return portCheckerService.getAvailablePorts();
    }
}
