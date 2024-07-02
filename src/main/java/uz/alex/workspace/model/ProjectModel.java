package uz.alex.workspace.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectModel {
    private Integer id;

    private String name;

    private Integer responsibleLeaderId;

    private BigDecimal budgetSum;

    private BigDecimal spentSum;

    private BigDecimal residualSum;
}
