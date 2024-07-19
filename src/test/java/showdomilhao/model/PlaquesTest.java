package showdomilhao.model;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlaquesTest {
    @Test
    @DisplayName("Verifica se a soma da porcentagem das 4 placas equivale a 100")
    void testSumPercentagePlaques() {
        Plaques plaques = new Plaques();
        plaques.printHelp();
        int sum = 0;
        List<Integer> list = plaques.getList();
        for (Integer num : list) {
            sum = sum+num;
        }

        Assertions.assertEquals(100, sum);
    }
}
