package ch.project.quizme.databases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LearnSetTest {

    /**
     * Method under test: {@link LearnSet#setCreationDate()}
     */
    @Test
    void testSetCreationDate2() {
        Language language1 = mock(Language.class);
        doNothing().when(language1).setFlag(Mockito.<String>any());
        doNothing().when(language1).setId(Mockito.<Integer>any());
        doNothing().when(language1).setName(Mockito.<String>any());
        language1.setFlag("Flag");
        language1.setId(1);
        language1.setName("Name");

        LearnSet learnSet = new LearnSet();
        learnSet.setLanguage1(language1);
        learnSet.setCreationDate();
        verify(language1).setFlag(Mockito.<String>any());
        verify(language1).setId(Mockito.<Integer>any());
        verify(language1).setName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LearnSet#setLastEdited()}
     */
    @Test
    void testSetLastEdited2() {
        Language language1 = mock(Language.class);
        doNothing().when(language1).setFlag(Mockito.<String>any());
        doNothing().when(language1).setId(Mockito.<Integer>any());
        doNothing().when(language1).setName(Mockito.<String>any());
        language1.setFlag("Flag");
        language1.setId(1);
        language1.setName("Name");

        LearnSet learnSet = new LearnSet();
        learnSet.setLanguage1(language1);
        learnSet.setLastEdited();
        verify(language1).setFlag(Mockito.<String>any());
        verify(language1).setId(Mockito.<Integer>any());
        verify(language1).setName(Mockito.<String>any());
    }

    @Test
    void testConstructor() {
        LearnSet actualLearnSet = new LearnSet();
        actualLearnSet.setId(1);
        Language language1 = new Language();
        language1.setFlag("Flag");
        language1.setId(1);
        language1.setName("Name");
        actualLearnSet.setLanguage1(language1);
        Language language2 = new Language();
        language2.setFlag("Flag");
        language2.setId(1);
        language2.setName("Name");
        actualLearnSet.setLanguage2(language2);
        actualLearnSet.setName("Name");
        assertEquals(1, actualLearnSet.getId().intValue());
        assertSame(language1, actualLearnSet.getLanguage1());
        assertSame(language2, actualLearnSet.getLanguage2());
        assertEquals("Name", actualLearnSet.getName());
    }
}

