package uz.alex.workspace.service.impl;

import org.springframework.stereotype.Service;
import uz.alex.workspace.model.MeetingsModel;
import uz.alex.workspace.repositories.MeetingsRepository;
import uz.alex.workspace.service.MeetingsService;

import java.util.List;

@Service
public class MeetingsServiceImpl implements MeetingsService {
    private final MeetingsRepository meetingsRepository;

    public MeetingsServiceImpl(MeetingsRepository meetingsRepository) {
        this.meetingsRepository = meetingsRepository;
    }

    @Override
    public List<MeetingsModel> getAllMeetings() {
        return List.of();
    }

    @Override
    public MeetingsModel getMeetingById(int meetingId) {
        return null;
    }

    @Override
    public MeetingsModel createMeeting(MeetingsModel meetingModel) {
        return null;
    }

    @Override
    public MeetingsModel updateMeeting(MeetingsModel meetingModel) {
        return null;
    }

    @Override
    public void deleteMeeting(int meetingId) {

    }
}
