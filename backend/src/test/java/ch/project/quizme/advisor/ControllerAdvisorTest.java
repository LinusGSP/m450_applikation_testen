package ch.project.quizme.advisor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.project.quizme.exceptions.InvalidLanguageFlagException;
import ch.project.quizme.exceptions.InvalidLanguageNameException;
import ch.project.quizme.exceptions.LanguageFailedToSaveException;
import ch.project.quizme.exceptions.LanguageIdenticalException;
import ch.project.quizme.exceptions.LanguageNotFoundException;
import ch.project.quizme.exceptions.LearnSetFailedToSaveException;
import ch.project.quizme.exceptions.LearnSetNotFoundException;
import ch.project.quizme.exceptions.LearnWordFailedToSaveException;
import ch.project.quizme.exceptions.LearnWordNotFoundException;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

class ControllerAdvisorTest {
    /**
     * Method under test: {@link ControllerAdvisor#handleMissingServletRequestParameter(MissingServletRequestParameterException, HttpHeaders, HttpStatus, WebRequest)}
     */
    @Test
    void testHandleMissingServletRequestParameter() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        MissingServletRequestParameterException ex = new MissingServletRequestParameterException("Parameter Name",
                "Parameter Type");

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Object> actualHandleMissingServletRequestParameterResult = controllerAdvisor
                .handleMissingServletRequestParameter(ex, headers, HttpStatus.CONTINUE,
                        new ServletWebRequest(new MockHttpServletRequest()));
        assertEquals(1, ((Map<String, String>) actualHandleMissingServletRequestParameterResult.getBody()).size());
        assertTrue(actualHandleMissingServletRequestParameterResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleMissingServletRequestParameterResult.getStatusCode());
        assertTrue(actualHandleMissingServletRequestParameterResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ControllerAdvisor#handleMissingServletRequestParameter(MissingServletRequestParameterException, HttpHeaders, HttpStatus, WebRequest)}
     */
    @Test
    void testHandleMissingServletRequestParameter3() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        MissingServletRequestParameterException ex = new MissingServletRequestParameterException("Parameter Name",
                "Parameter Type");

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Object> actualHandleMissingServletRequestParameterResult = controllerAdvisor
                .handleMissingServletRequestParameter(ex, headers, HttpStatus.SWITCHING_PROTOCOLS,
                        new ServletWebRequest(new MockHttpServletRequest()));
        assertEquals(1, ((Map<String, String>) actualHandleMissingServletRequestParameterResult.getBody()).size());
        assertTrue(actualHandleMissingServletRequestParameterResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleMissingServletRequestParameterResult.getStatusCode());
        assertTrue(actualHandleMissingServletRequestParameterResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ControllerAdvisor#handleMissingServletRequestParameter(MissingServletRequestParameterException, HttpHeaders, HttpStatus, WebRequest)}
     */
    @Test
    void testHandleMissingServletRequestParameter4() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        MissingServletRequestParameterException ex = new MissingServletRequestParameterException("Parameter Name",
                "Parameter Type");

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Object> actualHandleMissingServletRequestParameterResult = controllerAdvisor
                .handleMissingServletRequestParameter(ex, headers, HttpStatus.PROCESSING,
                        new ServletWebRequest(new MockHttpServletRequest()));
        assertEquals(1, ((Map<String, String>) actualHandleMissingServletRequestParameterResult.getBody()).size());
        assertTrue(actualHandleMissingServletRequestParameterResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleMissingServletRequestParameterResult.getStatusCode());
        assertTrue(actualHandleMissingServletRequestParameterResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ControllerAdvisor#handleMissingServletRequestParameter(MissingServletRequestParameterException, HttpHeaders, HttpStatus, WebRequest)}
     */
    @Test
    void testHandleMissingServletRequestParameter5() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        MissingServletRequestParameterException ex = new MissingServletRequestParameterException("Parameter Name",
                "Parameter Type");

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Object> actualHandleMissingServletRequestParameterResult = controllerAdvisor
                .handleMissingServletRequestParameter(ex, headers, HttpStatus.CHECKPOINT,
                        new ServletWebRequest(new MockHttpServletRequest()));
        assertEquals(1, ((Map<String, String>) actualHandleMissingServletRequestParameterResult.getBody()).size());
        assertTrue(actualHandleMissingServletRequestParameterResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleMissingServletRequestParameterResult.getStatusCode());
        assertTrue(actualHandleMissingServletRequestParameterResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ControllerAdvisor#handleMissingServletRequestParameter(MissingServletRequestParameterException, HttpHeaders, HttpStatus, WebRequest)}
     */
    @Test
    void testHandleMissingServletRequestParameter6() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        MissingServletRequestParameterException ex = new MissingServletRequestParameterException("Parameter Name",
                "Parameter Type");

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Object> actualHandleMissingServletRequestParameterResult = controllerAdvisor
                .handleMissingServletRequestParameter(ex, headers, HttpStatus.OK,
                        new ServletWebRequest(new MockHttpServletRequest()));
        assertEquals(1, ((Map<String, String>) actualHandleMissingServletRequestParameterResult.getBody()).size());
        assertTrue(actualHandleMissingServletRequestParameterResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleMissingServletRequestParameterResult.getStatusCode());
        assertTrue(actualHandleMissingServletRequestParameterResult.getHeaders().isEmpty());
    }


    /**
     * Method under test: {@link ControllerAdvisor#handleMethodArgumentNotValid(MethodArgumentNotValidException, HttpHeaders, HttpStatus, WebRequest)}
     */
    @Test
    void testHandleMethodArgumentNotValid2() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null,
                new BindException("Target", "Object Name"));

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Object> actualHandleMethodArgumentNotValidResult = controllerAdvisor.handleMethodArgumentNotValid(
                ex, headers, HttpStatus.CONTINUE, new ServletWebRequest(new MockHttpServletRequest()));
        assertTrue(((Map<Object, Object>) actualHandleMethodArgumentNotValidResult.getBody()).isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleMethodArgumentNotValidResult.getStatusCode());
        assertTrue(actualHandleMethodArgumentNotValidResult.getHeaders().isEmpty());
    }



    /**
     * Method under test: {@link ControllerAdvisor#handleLanguageFailedToSaveException(LanguageFailedToSaveException)}
     */
    @Test
    void testHandleLanguageFailedToSaveException() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        ResponseEntity<Object> actualHandleLanguageFailedToSaveExceptionResult = controllerAdvisor
                .handleLanguageFailedToSaveException(new LanguageFailedToSaveException("Name"));
        assertEquals("The Language with name:'Name' could not be saved.",
                actualHandleLanguageFailedToSaveExceptionResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleLanguageFailedToSaveExceptionResult.getStatusCode());
        assertTrue(actualHandleLanguageFailedToSaveExceptionResult.getHeaders().isEmpty());
    }


    /**
     * Method under test: {@link ControllerAdvisor#handleLanguageIdenticalException(LanguageIdenticalException)}
     */
    @Test
    void testHandleLanguageIdenticalException() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        ResponseEntity<Object> actualHandleLanguageIdenticalExceptionResult = controllerAdvisor
                .handleLanguageIdenticalException(new LanguageIdenticalException(1, 1));
        assertEquals("Cant enter the same id twice. IDs provided: 1, 1",
                actualHandleLanguageIdenticalExceptionResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleLanguageIdenticalExceptionResult.getStatusCode());
        assertTrue(actualHandleLanguageIdenticalExceptionResult.getHeaders().isEmpty());
    }


    /**
     * Method under test: {@link ControllerAdvisor#handleLanguageNotFoundException(LanguageNotFoundException)}
     */
    @Test
    void testHandleLanguageNotFoundException() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        ResponseEntity<Object> actualHandleLanguageNotFoundExceptionResult = controllerAdvisor
                .handleLanguageNotFoundException(new LanguageNotFoundException(1));
        assertEquals("The Language with id= 1 could not be found", actualHandleLanguageNotFoundExceptionResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleLanguageNotFoundExceptionResult.getStatusCode());
        assertTrue(actualHandleLanguageNotFoundExceptionResult.getHeaders().isEmpty());
    }



    /**
     * Method under test: {@link ControllerAdvisor#handleInvalidLanguageFlagException(InvalidLanguageFlagException)}
     */
    @Test
    void testHandleInvalidLanguageFlagException() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        ResponseEntity<Object> actualHandleInvalidLanguageFlagExceptionResult = controllerAdvisor
                .handleInvalidLanguageFlagException(new InvalidLanguageFlagException());
        assertEquals("Invalid Flag", actualHandleInvalidLanguageFlagExceptionResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleInvalidLanguageFlagExceptionResult.getStatusCode());
        assertTrue(actualHandleInvalidLanguageFlagExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ControllerAdvisor#handleInvalidLanguageNameException(InvalidLanguageNameException)}
     */
    @Test
    void testHandleInvalidLanguageNameException() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        ResponseEntity<Object> actualHandleInvalidLanguageNameExceptionResult = controllerAdvisor
                .handleInvalidLanguageNameException(new InvalidLanguageNameException());
        assertEquals("Invalid Name", actualHandleInvalidLanguageNameExceptionResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleInvalidLanguageNameExceptionResult.getStatusCode());
        assertTrue(actualHandleInvalidLanguageNameExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ControllerAdvisor#handleLearnSetFailedToSaveException(LearnSetFailedToSaveException)}
     */
    @Test
    void testHandleLearnSetFailedToSaveException() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        ResponseEntity<Object> actualHandleLearnSetFailedToSaveExceptionResult = controllerAdvisor
                .handleLearnSetFailedToSaveException(new LearnSetFailedToSaveException("Name"));
        assertEquals("The LearnSet with name: 'Name' could not be saved.",
                actualHandleLearnSetFailedToSaveExceptionResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleLearnSetFailedToSaveExceptionResult.getStatusCode());
        assertTrue(actualHandleLearnSetFailedToSaveExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ControllerAdvisor#handleLearnSetNotFoundException(LearnSetNotFoundException)}
     */
    @Test
    void testHandleLearnSetNotFoundException() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        ResponseEntity<Object> actualHandleLearnSetNotFoundExceptionResult = controllerAdvisor
                .handleLearnSetNotFoundException(new LearnSetNotFoundException(1));
        assertEquals("Could not find LearnSet with id=1", actualHandleLearnSetNotFoundExceptionResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleLearnSetNotFoundExceptionResult.getStatusCode());
        assertTrue(actualHandleLearnSetNotFoundExceptionResult.getHeaders().isEmpty());
    }


    /**
     * Method under test: {@link ControllerAdvisor#handleWordFailedToSaveException(LearnWordFailedToSaveException)}
     */
    @Test
    void testHandleWordFailedToSaveException() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        ResponseEntity<Object> actualHandleWordFailedToSaveExceptionResult = controllerAdvisor
                .handleWordFailedToSaveException(new LearnWordFailedToSaveException());
        assertEquals("Failed to save words", actualHandleWordFailedToSaveExceptionResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleWordFailedToSaveExceptionResult.getStatusCode());
        assertTrue(actualHandleWordFailedToSaveExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ControllerAdvisor#handleWordNotFoundException(LearnWordNotFoundException)}
     */
    @Test
    void testHandleWordNotFoundException() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        ResponseEntity<Object> actualHandleWordNotFoundExceptionResult = controllerAdvisor
                .handleWordNotFoundException(new LearnWordNotFoundException(1));
        assertEquals("Word with id=1 could not be found", actualHandleWordNotFoundExceptionResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleWordNotFoundExceptionResult.getStatusCode());
        assertTrue(actualHandleWordNotFoundExceptionResult.getHeaders().isEmpty());
    }


}

