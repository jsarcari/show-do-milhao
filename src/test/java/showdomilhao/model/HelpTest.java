package showdomilhao.model;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import showdomilhao.service.HelpService;

public class HelpTest {

    @Test
    @DisplayName("Verifica se a lista de ajuda é inicializada em true no início da partida")
    void testStartGameWithHelpListEqualsTrue() {
        HelpService service = new HelpService();

        List<Boolean> list = service.listAvailabilities("false", null, null, 0);

        for (Boolean element : list) {
            Assertions.assertEquals(true, element);
        }
    }
    
}
