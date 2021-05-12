package com.charles.servicemonitor.controller;

import java.io.IOException;
import java.net.URI;

import com.charles.servicemonitor.entity.Unit;
import com.charles.servicemonitor.service.UnitService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("units")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @GetMapping
    public ResponseEntity<Iterable<Unit>> getAll() {
        
        return ResponseEntity.ok(unitService.getAll());

    }

    @PostMapping
    public ResponseEntity<Unit> addUnit(@RequestBody Unit unit) {
        return ResponseEntity.created(URI.create(String.valueOf(unit.getPid())))
                .body(unitService.saveUnit(unit));
    }

    @GetMapping("{name}")
    public ResponseEntity<Unit> getByName(@PathVariable("name") String name) throws RuntimeException, IOException {
        return ResponseEntity.ok(
            unitService.getUnitByName(name)
        );
    }

    // @GetMapping("/query")
    // public ResponseEntity<?> getByOwner(@RequestParam("owner") String owner) {

    //     List<Unit> unitz = unitService.getByOwner(owner);

    //     if (unitz.size() == 0) {
    //         return ResponseEntity.ok("No deployed service found for " + owner);
    //     }

    //     return ResponseEntity.ok(
    //         unitz
    //     );

    // }
    
}
