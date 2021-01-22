package com.rnthd2.study_test_management.service;

import com.rnthd2.study_test_management.domain.EnglishNote;
import com.rnthd2.study_test_management.domain.TestPaperResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional
@Rollback(value = false)
class TestServiceTestApi {

    @Autowired
    TestApiService testApiService;

    @Test
    public void 영어노트_가져오기() throws Exception {
        //given

        //when
        final List<EnglishNote> englishTests = testApiService.findEnglishNote();

        //then
        Assertions.assertThat(englishTests);
    }

    @Test
    public void 영어시험지_가져오기() throws Exception {
        //given

        //when
        List<EnglishNote> notes = testApiService.findEnglishNote();
        notes.forEach(note -> note.setQuestion(note.getOriginal()));
        final TestPaperResponse englishTestPaper = testApiService.convertNoteToTestPaper(notes);

        //then
        Assertions.assertThat(englishTestPaper);
    }


}