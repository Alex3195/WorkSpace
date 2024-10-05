package uz.alex.workspace.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForm implements Serializable {
    @Serial
    private static final long serialVersionUID = -2069131671088646133L;

    private int column;
    private String dir;
}


