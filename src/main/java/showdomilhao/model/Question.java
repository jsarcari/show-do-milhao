package showdomilhao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("questions")
public class Question {

    @Id
    private String idDocument;
    private int id;
    private String question;
    private String correct_answer;
    private String difficulty;
    private String category;
    private String[] incorrect_answers;
}

