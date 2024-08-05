package showdomilhao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class HelpService {
    
    public List<Boolean> listAvailabilities(String guestsAvailable, String plaquesAvailable, String cardsAvailable, int index) {
        List<Boolean> listHelp = new ArrayList<Boolean>();
        listHelp.add(Boolean.parseBoolean(guestsAvailable));
        listHelp.add(Boolean.parseBoolean(plaquesAvailable));
        listHelp.add(Boolean.parseBoolean(cardsAvailable));
        if (index==0) {
            listHelp.replaceAll(element -> element = true);
        }
        return listHelp;
    }
}
