package com.scd.tech_agent.controller;

import com.scd.tech_agent.entity.Position;
import com.scd.tech_agent.service.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public record PositionController(PositionService positionServ) {

    @GetMapping("/positions")
    public ResponseEntity<List<Position>> getPositionList() {
        return new ResponseEntity<>(positionServ.getPositionList(), HttpStatus.OK);
    }

    @GetMapping("/department/{deptId}/positions")
    public ResponseEntity<List<Position>> getPositionListByDepartment(@PathVariable(value = "deptId") Integer deptId) {
        return new ResponseEntity<>(positionServ.getPositionListByDepartment(deptId), HttpStatus.OK);
    }

    @PostMapping("/positions")
    public ResponseEntity<Position> addPosition(@Valid @RequestBody Position dataRequest) {
        return new ResponseEntity<>(positionServ.addPosition(dataRequest), HttpStatus.CREATED);
    }

    @PutMapping("/positions/{postnId}")
    public ResponseEntity<Position> updatePosition(@PathVariable(value = "postnId") Integer postnId,
                                                   @Valid @RequestBody Position dataRequest) {
        return new ResponseEntity<>(positionServ.updatePosition(postnId, dataRequest), HttpStatus.OK);
    }

    @DeleteMapping("/positions/{postnId}")
    public ResponseEntity<Map<String, String>> deletePosition(@PathVariable(value = "postnId") Integer postnId) {
        return new ResponseEntity<>(positionServ.deletePosition(postnId), HttpStatus.OK);
    }

}
