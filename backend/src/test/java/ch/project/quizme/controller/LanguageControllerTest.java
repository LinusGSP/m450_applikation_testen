package ch.project.quizme.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ch.project.quizme.databases.Language;
import ch.project.quizme.repository.LanguageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {LanguageController.class})
@ExtendWith(SpringExtension.class)
class LanguageControllerTest {
    @Autowired
    private LanguageController languageController;

    @MockBean
    private LanguageRepository languageRepository;

    /**
     * Method under test: {@link LanguageController#createLanguage(LanguageDTO)}
     */
    @Test
    void testCreateLanguage() throws Exception {
        Language language = new Language();
        language.setFlag("Flag");
        language.setId(1);
        language.setName("Name");
        when(languageRepository.save(Mockito.<Language>any())).thenReturn(language);

        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(languageDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/language")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(languageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Success: saved"));
    }

    /**
     * Method under test: {@link LanguageController#deleteLanguage(Integer)}
     */
    @Test
    void testDeleteLanguage() throws Exception {
        doNothing().when(languageRepository).deleteById(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/language/{id}", 1);
        MockMvcBuilders.standaloneSetup(languageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Success: deleted"));
    }

    /**
     * Method under test: {@link LanguageController#getAllLanguageById(Integer)}
     */
    @Test
    void testGetAllLanguageById() throws Exception {
        Language language = new Language();
        language.setFlag("Flag");
        language.setId(1);
        language.setName("Name");
        Optional<Language> ofResult = Optional.of(language);
        when(languageRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/language/{id}", 1);
        MockMvcBuilders.standaloneSetup(languageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Name\",\"flag\":\"Flag\"}"));
    }

    /**
     * Method under test: {@link LanguageController#getAllLanguages()}
     */
    @Test
    void testGetAllLanguages() throws Exception {
        when(languageRepository.findAll()).thenReturn(mock(Iterable.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/language");
        requestBuilder.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(languageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }
}

