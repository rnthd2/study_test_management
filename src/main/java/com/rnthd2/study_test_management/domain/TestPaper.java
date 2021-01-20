package com.rnthd2.study_test_management.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TestPaper {
    private long id;
    private List<Test> tests;
    private int count;
    private LocalDateTime testTime;
    private int score;

    // Builder 이름을 부여해서 그에 따른 책임 부여, 그에 따른 필수 인자값 명확
    @Builder(builderClassName = "ByWriteTestPaperBuilder", builderMethodName = "ByWriteTestPaperBuilder")
    public TestPaper(Long id, List<Test> tests, LocalDateTime testTime, int score) {
        Assert.notNull(id,"id is not null");
        Assert.notNull(tests,"tests is not null");
        Assert.notNull(testTime,"testTime is not null");
        this.id = id;
        this.tests = tests;
        this.count = tests.size();
        this.testTime = testTime;
        this.score = score;
    }
}
