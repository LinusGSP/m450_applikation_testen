package ch.project.quizme.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ch.project.quizme.databases.Language;
import ch.project.quizme.databases.LearnSet;
import ch.project.quizme.databases.LearnWord;
import ch.project.quizme.repository.LanguageRepository;
import ch.project.quizme.repository.LearnSetRepository;
import ch.project.quizme.repository.LearnWordRepository;
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

@ContextConfiguration(classes = {LearnSetController.class})
@ExtendWith(SpringExtension.class)
class LearnSetControllerTest {
    @MockBean
    private LanguageRepository languageRepository;

    @Autowired
    private LearnSetController learnSetController;

    @MockBean
    private LearnSetRepository learnSetRepository;

    @MockBean
    private LearnWordRepository learnWordRepository;

    /**
     * Method under test: {@link LearnSetController#createLearnSet(LearnSetDTO)}
     */
    @Test
    void testCreateLearnSet() throws Exception {
        Language language = new Language();
        language.setFlag("Flag");
        language.setId(1);
        language.setName("Name");
        Optional<Language> ofResult = Optional.of(language);
        when(languageRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Language language1 = new Language();
        language1.setFlag("Flag");
        language1.setId(1);
        language1.setName("Name");

        Language language2 = new Language();
        language2.setFlag("Flag");
        language2.setId(1);
        language2.setName("Name");

        LearnSet learnSet = new LearnSet();
        learnSet.setId(1);
        learnSet.setLanguage1(language1);
        learnSet.setLanguage2(language2);
        learnSet.setName("Name");
        when(learnSetRepository.save(Mockito.<LearnSet>any())).thenReturn(learnSet);

        LearnSetDTO learnSetDTO = new LearnSetDTO();
        learnSetDTO.setLanguage1Id(2);
        learnSetDTO.setLanguage2Id(1);
        String content = (new ObjectMapper()).writeValueAsString(learnSetDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/learnset")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(learnSetController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Success: saved"));
    }

    /**
     * Method under test: {@link LearnSetController#getAllLearnSets()}
     */
    @Test
    void testGetAllLearnSets2() throws Exception {
        when(learnSetRepository.findAll()).thenReturn(mock(Iterable.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/learnset");
        requestBuilder.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(learnSetController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link LearnSetController#deleteLearnSetById(Integer)}
     */
    @Test
    void testDeleteLearnSetById() throws Exception {
        doNothing().when(learnSetRepository).deleteById(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/learnset/{id}", 1);
        MockMvcBuilders.standaloneSetup(learnSetController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Success: deleted"));
    }

    /**
     * Method under test: {@link LearnSetController#getAllLearnSets()}
     */
    @Test
    void testGetAllLearnSets() throws Exception {
        when(learnSetRepository.findAll()).thenReturn(mock(Iterable.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/learnset");
        requestBuilder.accept("https://localhost:8000");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(learnSetController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }


    /**
     * Method under test: {@link LearnSetController#deleteLearnSetById(Integer)}
     */
    @Test
    void testDeleteLearnSetById2() throws Exception {
        doNothing().when(learnSetRepository).deleteById(Mockito.<Integer>any());
        when(learnWordRepository.findByLearnSetId(Mockito.<Integer>any())).thenReturn(mock(Iterable.class));
        doNothing().when(learnWordRepository).deleteAll(Mockito.<Iterable<LearnWord>>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/learnset/{id}", 1);
        MockMvcBuilders.standaloneSetup(learnSetController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Success: deleted"));
    }

}

