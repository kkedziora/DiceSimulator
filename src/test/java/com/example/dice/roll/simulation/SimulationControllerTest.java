package com.example.dice.roll.simulation;

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
@WebMvcTest(SimulationController.class)
class SimulationControllerTest {

    private final String API = "/api/dices";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiceRollService diceRollService;

    @Test
    void rollDicesEndpoint_expectedSuccess() throws Exception {
        this.mockMvc.perform(get(API + "/rolls?numberOfRolls=100&numberOfDices=3&numberOfSides=6"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void rollDicesEndpoint_expectedBadRequest() throws Exception {
        this.mockMvc.perform(get(API + "/rolls?numberOfRolls=100&numberOfDices=3&numberOfSides=1"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}