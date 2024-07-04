package uz.alex.workspace.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alex.workspace.model.MeetingsModel;
import uz.alex.workspace.service.MeetingsService;

import java.util.List;

@RestController
@RequestMapping("/meeting")
//@CrossOrigin(allowedHeaders = "*",origins = "http://localhost3000")
public class MeetingsController {
    private final MeetingsService meetingsService;

    public MeetingsController(MeetingsService meetingsService) {
        this.meetingsService = meetingsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingsModel> getMeetings(@PathVariable int id) {
        return ResponseEntity.ok(meetingsService.getMeetingById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MeetingsModel>> getAllMeetings() {
        return ResponseEntity.ok(meetingsService.getAllMeetings());
    }

    @PostMapping("/add")
    public ResponseEntity<MeetingsModel> addMeeting(@RequestBody MeetingsModel meetings) {
        return ResponseEntity.ok(meetingsService.createMeeting(meetings));
    }

    @PutMapping("/update")
    public ResponseEntity<MeetingsModel> updateMeeting(@RequestBody MeetingsModel meetings) {
        return ResponseEntity.ok(meetingsService.updateMeeting(meetings));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable int id) {
        meetingsService.deleteMeeting(id);
        return ResponseEntity.ok().build();
    }
}
