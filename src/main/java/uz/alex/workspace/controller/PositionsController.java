package uz.alex.workspace.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alex.workspace.model.PositionModel;
import uz.alex.workspace.service.PositionsService;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionsController {
    private final PositionsService positionsService;

    public PositionsController(PositionsService positionsService) {
        this.positionsService = positionsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionModel> getPosition(@PathVariable int id) {
        return ResponseEntity.ok(positionsService.getPosition(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PositionModel>> getAllPositions() {
        return ResponseEntity.ok(positionsService.getPositions());
    }

    @PostMapping("/add")
    public ResponseEntity<PositionModel> addPosition(@RequestBody PositionModel position) {
        return ResponseEntity.ok(positionsService.createPosition(position));
    }

    @PutMapping("/update")
    public ResponseEntity<PositionModel> updatePosition(@RequestBody PositionModel position) {
        return ResponseEntity.ok(positionsService.updatePosition(position));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable int id) {
        positionsService.deletePosition(id);
        return ResponseEntity.accepted().build();
    }

}
