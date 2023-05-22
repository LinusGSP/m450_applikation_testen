package ch.project.quizme.databases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LanguageTest {
    @Test
    void testConstructor() {
        Language actualLanguage = new Language();
        actualLanguage.setFlag("Flag");
        actualLanguage.setId(1);
        actualLanguage.setName("Name");
        assertEquals("Flag", actualLanguage.getFlag());
        assertEquals(1, actualLanguage.getId().intValue());
        assertEquals("Name", actualLanguage.getName());
    }
}

