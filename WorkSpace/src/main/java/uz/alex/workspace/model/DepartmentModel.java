package uz.alex.workspace.model;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class DepartmentModel {
    private Integer id;
    private String name;
    private String description;
}
