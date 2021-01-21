package com.rnthd2.study_test_management.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoteBookResponse {
    private long id;
    private int count;
    List<? extends Note> list;

    // Builder 이름을 부여해서 그에 따른 책임 부여, 그에 따른 필수 인자값 명확
    @Builder(builderClassName = "ByReadEnglishNoteResponseBuilder", builderMethodName = "ByReadEnglishNoteResponseBuilder")
    public NoteBookResponse(Long id, int count, List<? extends Note> list) {
        Assert.notNull(id, "id is not null");
        Assert.notNull(list, "list is not null");
        this.id = id;
        this.count = list.size();
        this.list = list;
    }
}
