package uz.alex.workspace.model;

import lombok.Data;
import uz.alex.workspace.entity.Positions;

@Data
public class EmployeeModel {
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Positions position;
}
