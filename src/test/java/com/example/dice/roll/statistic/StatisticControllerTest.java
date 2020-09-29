package com.example.dice.roll.statistic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StatisticController.class)
class StatisticControllerTest {

    private final String API = "/api/dices/statistics";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticService statisticService;

    @Test
    void summariesEndpoint_expectedSuccess() throws Exception {
        this.mockMvc.perform(get(API + "/summaries"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void distributionEndpoint_expectedSuccess() throws Exception {
        this.mockMvc.perform(get(API + "/distributions?numberOfDices=3&numberOfSides=6"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void distributionEndpoint_expectedBadRequest() throws Exception {
        this.mockMvc.perform(get(API + "/distributions?numberOfDices=3&numberOfSides=3"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}