package uz.alex.workspace.model;

import lombok.Data;

import java.util.Date;

@Data
public class MeetingsModel {
    private Integer id;
    private String title;
    private String description;
    private String link;
    private String startTime;
    private String endTime;
    private String status;
}
