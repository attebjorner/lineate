package com.example.offerdaysongs.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application.properties")
class RecordingControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetAll() throws Exception
    {
        mockMvc.perform(get("/api/recordings/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].title").isString())
                .andExpect(jsonPath("$[0].version").isString())
                .andExpect(jsonPath("$[0].releaseTime").isString())
                .andExpect(jsonPath("$[0].singer").isMap())
                .andExpect(jsonPath("$[0].singer.id").isNumber())
                .andExpect(jsonPath("$[0].singer.name").isString());
    }

    @Test
    void shouldReturn204WhenRecordNotFound() throws Exception
    {
        var id = "333";
        mockMvc.perform(get("/api/recordings/" + id))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.error").isString())
                .andExpect(jsonPath("$.error").value(containsString(id)));
    }

    @Test
    void shouldGetByReleaseTime() throws Exception
    {
        var start = "1980-04-23T04:30:45.123Z";
        var end = "1995-04-23T04:30:45.123Z";
        mockMvc.perform(get("/api/recordings/release_time/").param("start", start).param("end", end))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].title").isString())
                .andExpect(jsonPath("$[0].version").isString())
                .andExpect(jsonPath("$[0].releaseTime").isString())
                .andExpect(jsonPath("$[0].singer").isMap())
                .andExpect(jsonPath("$[0].singer.id").isNumber())
                .andExpect(jsonPath("$[0].singer.name").isString());
    }

    @Test
    void shouldGetByCompanyName() throws Exception
    {
        mockMvc.perform(get("/api/recordings/company/").param("name", "The best company"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].title").isString())
                .andExpect(jsonPath("$[0].version").isString())
                .andExpect(jsonPath("$[0].releaseTime").isString())
                .andExpect(jsonPath("$[0].singer").isMap())
                .andExpect(jsonPath("$[0].singer.id").isNumber())
                .andExpect(jsonPath("$[0].singer.name").isString());
    }

    @Test
    void shouldGetPriceById() throws Exception
    {
        mockMvc.perform(get("/api/recordings/price/4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").value(is(4)))
                .andExpect(jsonPath("$.price").isNumber())
                .andExpect(jsonPath("$.price").value(is(400)));
    }
}