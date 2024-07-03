package uz.alex.workspace.service.impl;

import org.springframework.stereotype.Service;
import uz.alex.workspace.constants.DataStatusEnum;
import uz.alex.workspace.entity.Meetings;
import uz.alex.workspace.model.MeetingsModel;
import uz.alex.workspace.repositories.MeetingsRepository;
import uz.alex.workspace.service.MeetingsService;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingsServiceImpl implements MeetingsService {
    private final MeetingsRepository meetingsRepository;

    public MeetingsServiceImpl(MeetingsRepository meetingsRepository) {
        this.meetingsRepository = meetingsRepository;
    }

    @Override
    public List<MeetingsModel> getAllMeetings() {
        List<Meetings> meetings = meetingsRepository.findAllActiveMeetings();
        return meetings.stream().map(this::entityToModel).toList();
    }

    @Override
    public MeetingsModel getMeetingById(int meetingId) {
        return entityToModel(meetingsRepository.getReferenceById(meetingId));
    }

    @Override
    public MeetingsModel createMeeting(MeetingsModel meetingModel) {
        return entityToModel(meetingsRepository.save(modelToEntity(meetingModel)));
    }

    @Override
    public MeetingsModel updateMeeting(MeetingsModel meetingModel) {
        return entityToModel(meetingsRepository.save(modelToEntity(meetingModel)));
    }

    @Override
    public void deleteMeeting(int meetingId) {
        Optional<Meetings> meeting = meetingsRepository.findById(meetingId);
        meeting.ifPresent(x -> {
            x.setDataStatus(DataStatusEnum.DELETED.name());
            meetingsRepository.save(x);
        });


    }

    private MeetingsModel entityToModel(Meetings meeting) {
        MeetingsModel meetingModel = new MeetingsModel();
        meetingModel.setId(meeting.getId());
        meetingModel.setTitle(meeting.getTitle());
        meetingModel.setDescription(meeting.getDescription());
        meetingModel.setLink(meeting.getLink());
        meetingModel.setStartTime(meeting.getStartTime());
        meetingModel.setEndTime(meeting.getEndTime());
        meetingModel.setStatus(meeting.getStatus());
        return meetingModel;
    }

    private Meetings modelToEntity(MeetingsModel meetingModel) {
        Meetings meeting = new Meetings();
        meeting.setId(meetingModel.getId());
        meeting.setTitle(meetingModel.getTitle());
        meeting.setDescription(meetingModel.getDescription());
        meeting.setLink(meetingModel.getLink());
        meeting.setStartTime(meetingModel.getStartTime());
        meeting.setEndTime(meetingModel.getEndTime());
        meeting.setStatus(meetingModel.getStatus());
        if (meetingModel.getId() != null)
            meeting.setDataStatus(DataStatusEnum.UPDATED.name());
        else meeting.setDataStatus(DataStatusEnum.CREATED.name());
        return meeting;
    }
}
