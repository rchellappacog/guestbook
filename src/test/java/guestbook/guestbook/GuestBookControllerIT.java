package guestbook.guestbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("local")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GuestBookControllerIT {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    GuestBookRepository guestBookRepository;

    @Test
    void create_FetchAll() throws Exception {
        GuestBookDto guestBookDto = new GuestBookDto("peter", "nice place to visit!");
        mockMvc.perform(
                post("/guestbook")
                .content(objectMapper.writeValueAsString(guestBookDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andDo(document("AddGuestBookEntry"));

        mockMvc.perform(get("/guestbook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1))
                .andExpect(jsonPath("[0].name").value("peter"))
                .andExpect(jsonPath("[0].comment").value("nice place to visit!"))
                // Follow Up to andExpect
                .andDo(document("GuestBook", responseFields(
                        fieldWithPath("[0].name").description("Person name"),
                        fieldWithPath("[0].comment").description("Comment")
                )));

    }

    @Test
    void createMultiple_FetchAll() throws Exception {
        GuestBookDto guestBookDto1 = new GuestBookDto("peter", "nice place to visit!");
        mockMvc.perform(
                post("/guestbook")
                        .content(objectMapper.writeValueAsString(guestBookDto1))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());

        GuestBookDto guestBookDto2 = new GuestBookDto("john", "nice artifacts!");
        mockMvc.perform(
                post("/guestbook")
                        .content(objectMapper.writeValueAsString(guestBookDto2))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());

        mockMvc.perform(get("/guestbook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(2))
                .andExpect(jsonPath("[0].name").value("peter"))
                .andExpect(jsonPath("[0].comment").value("nice place to visit!"))
                .andExpect(jsonPath("[1].name").value("john"))
                .andExpect(jsonPath("[1].comment").value("nice artifacts!"));

    }
}
