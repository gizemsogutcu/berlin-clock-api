package com.api.berlinclock.controller;

import com.api.berlinclock.core.APIResponse;
import com.api.berlinclock.service.BerlinClockAnotherSolutionService;
import com.api.berlinclock.service.BerlinClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BerlinClockController {

    private BerlinClockService berlinClockService;

    @Autowired
    public BerlinClockController(BerlinClockService berlinClockService) {
        this.berlinClockService = berlinClockService;
    }

    @GetMapping(path = "/berlinClock")
    public APIResponse getBerlinClock(@RequestParam(required = false) String time) throws Exception {
        return berlinClockService.getBerlinClock(time);
    }
}
