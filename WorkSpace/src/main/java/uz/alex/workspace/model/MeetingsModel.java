package uz.alex.workspace.model;

import lombok.Data;

import java.util.Date;

@Data
public class MeetingsModel {
    private Integer id;
    private String title;
    private String description;
    private String link;
    private Date startTime;
    private Date endTime;
    private String status;
}
