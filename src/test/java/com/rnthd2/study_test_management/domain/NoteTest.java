package com.rnthd2.study_test_management.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional
@Rollback(value = false)
class NoteTest {

    @Test
    void ByReadNoteBuilder() throws Exception {
        Note note = Note.ByReadNoteBuilder()
                .id(1L)
                .studyTime(LocalDate.now())
                .build();

        assertEquals(note.getId(), 1L);
        assertEquals(note.getStudyTime(), LocalDate.now());
    }

    @Test
    void ByReadNoteBuilder_id_비어있으면_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                Note.ByReadNoteBuilder()
                        .studyTime(LocalDate.now())
                        .build());
    }

    @Test
    void ByReadNoteBuilder_studyTime_비어있으면_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                Note.ByReadNoteBuilder()
                        .id(1L)
                        .build());
    }
        
}