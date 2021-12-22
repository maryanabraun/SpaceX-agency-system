package edu.kpi.iasa.mmsa.SpaceX.api.controller;


import edu.kpi.iasa.mmsa.SpaceX.data.model.Position;
import edu.kpi.iasa.mmsa.SpaceX.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/positions")
public class PositionController {
    private final PositionService positionService;

    @GetMapping
    public ResponseEntity<List<Position>> index(){
        return ResponseEntity.ok(positionService.getPositions());}

    @GetMapping(value = "/{id}")
    public ResponseEntity<Position> show(@PathVariable long id) {
        return ResponseEntity.ok(positionService.getPositionById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        positionService.deletePosition(id);
        return ResponseEntity.noContent().build();
    }

}
