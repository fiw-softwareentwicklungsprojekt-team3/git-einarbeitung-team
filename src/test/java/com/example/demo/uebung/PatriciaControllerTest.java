package com.example.demo.uebung;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PatriciaControllerTest {

    // MockMvc simuliert HTTP-Anfragen, ohne einen echten Server zu starten.
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnGreeting() throws Exception {
        // Ein GET-Aufruf auf /api/uebung/vorname/Test prüft, ob der Endpunkt antwortet
        // und ob im JSON eine "autorin"-Eigenschaft vorhanden ist.
        mockMvc.perform(get("/api/uebung/vorname/Test"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.autorin").exists());
    }
}