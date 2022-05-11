package com.hookitup.Producer.controller;

import com.hookitup.Producer.model.producer.ShotById;
import com.hookitup.Producer.service.ShotByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/shotById")
public class ShotByIdControl {

    @Autowired
    private ShotByIdService shotByIdService;

    @PreAuthorize("hasAuthority('SCOPE_CONSUMER')")
    @GetMapping
    public ResponseEntity<List<ShotById>> findAllShotById() {
        return ResponseEntity.ok(shotByIdService.findAllShotById());
    }

}
