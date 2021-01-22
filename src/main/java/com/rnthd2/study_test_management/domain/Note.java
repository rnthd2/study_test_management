package com.rnthd2.study_test_management.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Getter
public class Note {
    private long id;
    private LocalDate studyTime;

    public Note() {
    }

    // Builder 이름을 부여해서 그에 따른 책임 부여, 그에 따른 필수 인자값 명확
    @Builder(builderClassName = "ByReadNoteBuilder", builderMethodName = "ByReadNoteBuilder")
    public Note(Long id, LocalDate studyTime, String question) {
        Assert.notNull(id,"id is not null");
        Assert.notNull(studyTime,"studyTime is not null");
        this.id = id;
        this.studyTime = studyTime;
    }
}
