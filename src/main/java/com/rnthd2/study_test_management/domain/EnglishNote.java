package com.rnthd2.study_test_management.domain;

import lombok.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnglishNote extends Note {
    private String original;
    private String translate;
    private String explanation;

    // Builder 이름을 부여해서 그에 따른 책임 부여, 그에 따른 필수 인자값 명확
    @Builder(builderClassName = "ByReadEnglishNoteBuilder", builderMethodName = "ByReadEnglishNoteBuilder")
    public EnglishNote(Long id, LocalDate studyTime, String question, String original, String translate, String explanation) {
        super(id, studyTime, question);
        Assert.notNull(original,"original is not null");
        Assert.notNull(translate,"translate is not null");
        this.original = original;
        this.translate = translate;
        this.explanation = explanation;
    }

    public static class EnglishNotesToOrginalTestsConverter implements Converter<List<EnglishNote>, List<Test>>{
        @Override
        public List<Test> convert(List<EnglishNote> englishNotes) {
            List<Test> tests = new ArrayList<>();
            englishNotes.forEach(englishNote -> {
                Test test = Test.ByWriteTestBuilder()
                        .id(englishNote.getId())
                        .question(englishNote.getOriginal())
                        .build();
                tests.add(test);
            });
            return tests;
        }
    }
}
