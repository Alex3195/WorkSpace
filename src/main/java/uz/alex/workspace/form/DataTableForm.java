package uz.alex.workspace.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataTableForm implements Serializable {
    @Serial
    private static final long serialVersionUID = 422250768862371526L;

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private String error;
    public List data;

}


