package com.rnthd2.study_test_management.api;

import com.rnthd2.study_test_management.domain.EnglishNote;
import com.rnthd2.study_test_management.domain.NoteBookResponse;
import com.rnthd2.study_test_management.domain.TestPaperResponse;
import com.rnthd2.study_test_management.service.EnglishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestApiController {
    private final EnglishService englishService;

    @GetMapping("/api/v1/note/eng")
    public NoteBookResponse getEnglishNoteV1(){
        List<EnglishNote> notes = englishService.findEngNote();
        return englishService.noteBookResponse(notes);
    }

    @GetMapping("/api/v1/test/eng/blank/original")
    public TestPaperResponse getEnglishTestPaperV1(){
        return englishService.findTestPaperBlankOriginal();
    }

    @PostMapping("/api/v1/test/eng/download/blank/original")
    public void downloadEnglishTestPaperV1(){
        //todo
//        englishService.downloadTestPaperBlankOriginal();
    }
}
