package showdomilhao.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @JsonProperty("id")
    private int id;

    @JsonProperty("question")
    private String question;

    @JsonProperty("correct_answer")
    private String correct_answer;

    @JsonProperty("difficulty")
    private String difficulty;

    @JsonProperty("category")
    private String category;

    @JsonProperty("incorrect_answers")
    private String[] incorrect_answers;
}

