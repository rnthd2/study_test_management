package com.rnthd2.study_test_management.api;

import com.rnthd2.study_test_management.domain.EnglishNote;
import com.rnthd2.study_test_management.service.EnglishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestApiController {
    private final EnglishService englishService;

    @GetMapping("/api/v1/note/eng")
    public List<EnglishNote> getEnglishNoteV1(){
        return englishService.findEngNote();
    }
}
