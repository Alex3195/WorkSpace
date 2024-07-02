package uz.alex.workspace.service;

import uz.alex.workspace.model.MeetingsModel;

import java.util.List;

public interface MeetingsService {
    List<MeetingsModel> getAllMeetings();

    MeetingsModel getMeetingById(int meetingId);

    MeetingsModel createMeeting(MeetingsModel meetingModel);

    MeetingsModel updateMeeting(MeetingsModel meetingModel);

    void deleteMeeting(int meetingId);
}
