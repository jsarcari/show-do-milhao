package showdomilhao.model;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlaquesTest {
    @Test
    @DisplayName("Verifica se a soma da porcentagem das 4 placas equivale a 100")
    void testSumPercentagePlaques() {
        Plaques plaques = new Plaques();
        plaques.generateRandomValue(0, 0);
        int sum = 0;
        List<Integer> list = plaques.getList();
        for (Integer num : list) {
            sum = sum+num;
        }

        Assertions.assertEquals(100, sum);
    }

    @Test
    @DisplayName("Verifica se o primeiro elemento da lista corresponde ao maior número presente na lista")
    void testValueFirstElement() {
        Plaques plaques = new Plaques();
        plaques.generateRandomValue(0, 0);;
        List<Integer> list = plaques.getList();
        Integer max = list.stream().mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new);

        Assertions.assertEquals(max, list.get(0));
    }
}
