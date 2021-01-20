package com.rnthd2.study_test_management.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Getter
public class Test {
    private long id;
    private String question;
    private String answer;
    private boolean isRight;

    // Builder 이름을 부여해서 그에 따른 책임 부여, 그에 따른 필수 인자값 명확
    @Builder(builderClassName = "ByWriteTestBuilder", builderMethodName = "ByWriteTestBuilder")
    public Test(Long id, String question, String answer, boolean isRight) {
        Assert.notNull(id,"id is not null");
        Assert.notNull(question,"question is not null");

        this.id = id;
        this.question = question;
        this.answer = answer;
        this.isRight = isRight;
    }
}
