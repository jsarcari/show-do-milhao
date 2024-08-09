package showdomilhao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    private String name;
    private Boolean stop = false;
    private int canSkip = 3;
    private String category;
}
