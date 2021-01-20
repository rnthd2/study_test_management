package com.rnthd2.study_test_management.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.opentest4j.AssertionFailedError;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional
@Rollback(value = false)
class EnglishNoteTest {

    @Test
    void ByReadEnglishNoteBuilder() {
        EnglishNote englishNote = EnglishNote.ByReadEnglishNoteBuilder()
                .id(1L)
                .studyTime(LocalDate.now())
                .original("original")
                .translate("translate")
                .explanation("explanation")
                .build();

        assertEquals(englishNote.getOriginal(), "original");
        assertEquals(englishNote.getTranslate(), "translate");
        assertEquals(englishNote.getExplanation(), "explanation");
    }

    @Test
    void ByReadEnglishNoteBuilder_original_비어있으면_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                EnglishNote.ByReadEnglishNoteBuilder()
                        .id(1L)
                        .studyTime(LocalDate.now())
                        .explanation("explanation")
                        .translate("translate")
                        .build());
    }

    @Test
    void ByReadEnglishNoteBuilder_translate_비어있으면_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                EnglishNote.ByReadEnglishNoteBuilder()
                        .id(1L)
                        .studyTime(LocalDate.now())
                        .original("original")
                        .explanation("explanation")
                        .build());
    }
}