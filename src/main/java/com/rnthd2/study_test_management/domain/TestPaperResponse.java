package com.rnthd2.study_test_management.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TestPaperResponse {
    private long id;
    private int count;
    private List<Test> tests;
    private int score;

    // Builder 이름을 부여해서 그에 따른 책임 부여, 그에 따른 필수 인자값 명확
    @Builder(builderClassName = "ByTestPaperResponseBuilder", builderMethodName = "ByTestPaperResponseBuilder")
    public TestPaperResponse(Long id, List<Test> tests, int score) {
        Assert.notNull(id,"id is not null");
        Assert.notNull(tests,"tests is not null");
        this.id = id;
        this.count = tests.size();
        this.tests = tests;
        this.score = score;
    }

}
