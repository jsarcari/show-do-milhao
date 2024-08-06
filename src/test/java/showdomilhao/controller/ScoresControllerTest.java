package showdomilhao.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import showdomilhao.service.ScoresService;

@SpringBootTest
@AutoConfigureMockMvc
public class ScoresControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScoresService service;

    @Test
    void testViewScores() throws Exception {

        var response = mvc.perform(
            get("/scores")).andExpect(view().name("scores.jsp")).andReturn().getResponse();
    
            Assertions.assertEquals(200, response.getStatus());
    }

}
