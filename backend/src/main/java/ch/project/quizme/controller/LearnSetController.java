package ch.project.quizme.controller;

import ch.project.quizme.databases.Language;
import ch.project.quizme.databases.LearnSet;
import ch.project.quizme.databases.LearnWord;
import ch.project.quizme.exceptions.LanguageIdenticalException;
import ch.project.quizme.exceptions.LanguageNotFoundException;
import ch.project.quizme.exceptions.LearnSetNotFoundException;
import ch.project.quizme.exceptions.LearnWordFailedToSaveException;
import ch.project.quizme.repository.LanguageRepository;
import ch.project.quizme.repository.LearnSetRepository;
import ch.project.quizme.repository.LearnWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

/**
 * This class is the controller for the LearnSet entity.
 * This class contains the methods to get, create and delete a language.
 *
 * @author Linus Schönbächler
 * @version 1.0
 * @since 2022-10-03
 */
@RestController
@RequestMapping(path = "/api/learnset")
public class LearnSetController {

    @Autowired
    private LearnSetRepository learnSetRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private LearnWordRepository learnWordRepository;

    /**
     * This method gets all learnSets in the database.
     *
     * @return All LearnSets
     */
    @GetMapping(path = "")
    public ResponseEntity<Iterable<LearnSet>> getAllLearnSets() {
        Optional<Iterable<LearnSet>> learnSets = Optional.of(learnSetRepository.findAll());
        return learnSets.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * This method gets a learnSet with a unique id.
     *
     * @param id The id of the learnSet.
     * @return The learnSet.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<LearnSet> getLearnSetById(@PathVariable("id") Integer id) {
        Optional<LearnSet> learnSet = learnSetRepository.findById(id);
        return ResponseEntity.ok(learnSet.orElseThrow(() -> new LearnSetNotFoundException(id)));
    }

    /**
     * This method creates a new learnSet.
     *
     * @return Successful.
     */
    @PostMapping(path = "")
    public ResponseEntity<String> createLearnSet(@Valid @RequestBody LearnSetDTO learnSetDTO) {
        if (Objects.equals(learnSetDTO.getLanguage1Id(), learnSetDTO.getLanguage2Id())) {
            throw new LanguageIdenticalException(learnSetDTO.getLanguage1Id(), learnSetDTO.getLanguage2Id());
        }

        try {
            LearnSet learnSet = new LearnSet();
            Language language1 = languageRepository.findById(learnSetDTO.getLanguage1Id())
                    .orElseThrow(() -> new LanguageNotFoundException(learnSetDTO.getLanguage1Id()));
            Language language2 = languageRepository.findById(learnSetDTO.getLanguage2Id())
                    .orElseThrow(() -> new LanguageNotFoundException(learnSetDTO.getLanguage2Id()));

            learnSet.setLanguage1(language1);
            learnSet.setLanguage2(language2);
            learnSetRepository.save(learnSet);
        } catch (Exception e) {
            throw new LearnWordFailedToSaveException();
        }

        return ResponseEntity.ok("Success: saved");
    }


    /**
     * This method deletes a learnSet.
     *
     * @param id The id of the deleted learnSet.
     * @return Successful.
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteLearnSetById(@PathVariable("id") Integer id) {
        try {
            Iterable<LearnWord> words = learnWordRepository.findByLearnSetId(id);
            learnWordRepository.deleteAll(words);
            learnSetRepository.deleteById(id);
        } catch (Exception e) {
            throw new LearnSetNotFoundException(id);
        }
        return ResponseEntity.ok("Success: deleted");
    }
}
