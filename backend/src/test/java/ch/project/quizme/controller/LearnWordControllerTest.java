package ch.project.quizme.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ch.project.quizme.databases.Language;
import ch.project.quizme.databases.LearnSet;
import ch.project.quizme.databases.LearnWord;
import ch.project.quizme.exceptions.LearnWordFailedToSaveException;
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

@ContextConfiguration(classes = {LearnWordController.class})
@ExtendWith(SpringExtension.class)
class LearnWordControllerTest {
    @MockBean
    private LearnSetRepository learnSetRepository;

    @Autowired
    private LearnWordController learnWordController;

    @MockBean
    private LearnWordRepository learnWordRepository;

    /**
     * Method under test: {@link LearnWordController#createNewWords(Iterable)}
     */
    @Test
    void testCreateNewWords() {

        assertThrows(LearnWordFailedToSaveException.class,
                () -> (new LearnWordController()).createNewWords(mock(Iterable.class)));
    }

    /**
     * Method under test: {@link LearnWordController#createNewWord(LearnWordDTO)}
     */
    @Test
    void testCreateNewWord() throws Exception {
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
        Optional<LearnSet> ofResult = Optional.of(learnSet);

        Language language12 = new Language();
        language12.setFlag("Flag");
        language12.setId(1);
        language12.setName("Name");

        Language language22 = new Language();
        language22.setFlag("Flag");
        language22.setId(1);
        language22.setName("Name");

        LearnSet learnSet2 = new LearnSet();
        learnSet2.setId(1);
        learnSet2.setLanguage1(language12);
        learnSet2.setLanguage2(language22);
        learnSet2.setName("Name");
        when(learnSetRepository.save(Mockito.<LearnSet>any())).thenReturn(learnSet2);
        when(learnSetRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        LearnWord learnWord = new LearnWord();
        learnWord.setId(1);
        learnWord.setLearnSetId(1);
        learnWord.setTranslation("Translation");
        learnWord.setWord("Word");
        when(learnWordRepository.save(Mockito.<LearnWord>any())).thenReturn(learnWord);

        LearnWordDTO learnWordDTO = new LearnWordDTO();
        learnWordDTO.setLearnSetId(1);
        learnWordDTO.setWord2("Word2");
        String content = (new ObjectMapper()).writeValueAsString(learnWordDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/word")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(learnWordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"translation\":\"Translation\",\"word\":\"Word\",\"learnSetId\":1}"));
    }

    /**
     * Method under test: {@link LearnWordController#deleteWord(Integer)}
     */
    @Test
    void testDeleteWord() throws Exception {
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
        Optional<LearnSet> ofResult = Optional.of(learnSet);

        Language language12 = new Language();
        language12.setFlag("Flag");
        language12.setId(1);
        language12.setName("Name");

        Language language22 = new Language();
        language22.setFlag("Flag");
        language22.setId(1);
        language22.setName("Name");

        LearnSet learnSet2 = new LearnSet();
        learnSet2.setId(1);
        learnSet2.setLanguage1(language12);
        learnSet2.setLanguage2(language22);
        learnSet2.setName("Name");
        when(learnSetRepository.save(Mockito.<LearnSet>any())).thenReturn(learnSet2);
        when(learnSetRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        LearnWord learnWord = new LearnWord();
        learnWord.setId(1);
        learnWord.setLearnSetId(1);
        learnWord.setTranslation("Translation");
        learnWord.setWord("Word");
        Optional<LearnWord> ofResult2 = Optional.of(learnWord);
        doNothing().when(learnWordRepository).deleteById(Mockito.<Integer>any());
        when(learnWordRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/word/{id}", 1);
        MockMvcBuilders.standaloneSetup(learnWordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Success: deleted"));
    }

    /**
     * Method under test: {@link LearnWordController#getAllWords()}
     */
    @Test
    void testGetAllWords() throws Exception {
        when(learnWordRepository.findAll()).thenReturn(mock(Iterable.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/word");
        requestBuilder.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(learnWordController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link LearnWordController#getLearnSetWords(Integer)}
     */
    @Test
    void testGetLearnSetWords() throws Exception {
        when(learnWordRepository.findByLearnSetId(Mockito.<Integer>any())).thenReturn(mock(Iterable.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/word/set/{id}", "Uri Variables",
                "Uri Variables");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(learnWordController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link LearnWordController#getLearnSetWords(Integer)}
     */
    @Test
    void testGetLearnSetWords2() throws Exception {
        when(learnWordRepository.findByLearnSetId(Mockito.<Integer>any())).thenReturn(mock(Iterable.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/word/set/{id}", 1);
        requestBuilder.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(learnWordController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link LearnWordController#getWord(Integer)}
     */
    @Test
    void testGetWord() throws Exception {
        LearnWord learnWord = new LearnWord();
        learnWord.setId(1);
        learnWord.setLearnSetId(1);
        learnWord.setTranslation("Translation");
        learnWord.setWord("Word");
        Optional<LearnWord> ofResult = Optional.of(learnWord);
        when(learnWordRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/word/{id}", 1);
        MockMvcBuilders.standaloneSetup(learnWordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"translation\":\"Translation\",\"word\":\"Word\",\"learnSetId\":1}"));
    }

    /**
     * Method under test: {@link LearnWordController#updateWord(LearnWordUpdateDTO)}
     */
    @Test
    void testUpdateWord() throws Exception {
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
        Optional<LearnSet> ofResult = Optional.of(learnSet);

        Language language12 = new Language();
        language12.setFlag("Flag");
        language12.setId(1);
        language12.setName("Name");

        Language language22 = new Language();
        language22.setFlag("Flag");
        language22.setId(1);
        language22.setName("Name");

        LearnSet learnSet2 = new LearnSet();
        learnSet2.setId(1);
        learnSet2.setLanguage1(language12);
        learnSet2.setLanguage2(language22);
        learnSet2.setName("Name");
        when(learnSetRepository.save(Mockito.<LearnSet>any())).thenReturn(learnSet2);
        when(learnSetRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        LearnWord learnWord = new LearnWord();
        learnWord.setId(1);
        learnWord.setLearnSetId(1);
        learnWord.setTranslation("Translation");
        learnWord.setWord("Word");
        Optional<LearnWord> ofResult2 = Optional.of(learnWord);

        LearnWord learnWord2 = new LearnWord();
        learnWord2.setId(1);
        learnWord2.setLearnSetId(1);
        learnWord2.setTranslation("Translation");
        learnWord2.setWord("Word");
        when(learnWordRepository.save(Mockito.<LearnWord>any())).thenReturn(learnWord2);
        when(learnWordRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);

        LearnWordUpdateDTO learnWordUpdateDTO = new LearnWordUpdateDTO();
        learnWordUpdateDTO.setId(1);
        learnWordUpdateDTO.setTranslation("Translation");
        learnWordUpdateDTO.setWord("Word");
        String content = (new ObjectMapper()).writeValueAsString(learnWordUpdateDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/word")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(learnWordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"translation\":\"Translation\",\"word\":\"Word\",\"learnSetId\":1}"));
    }
}

