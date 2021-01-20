package com.rnthd2.study_test_management.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.List;

@Getter
public class TestPaperRequest {
    private long id;
    private List<Long> wrongIds;
    private int score;

    // Builder 이름을 부여해서 그에 따른 책임 부여, 그에 따른 필수 인자값 명확
    @Builder(builderClassName = "ByTestPaperRequestBuilder", builderMethodName = "ByTestPaperRequestBuilder")
    public TestPaperRequest(long id, List<Long> wrongIds, int score) {
        Assert.notNull(id,"id is not null");
        Assert.notNull(score,"score is not null");
        this.id = id;
        this.wrongIds = wrongIds;
        this.score = score;
    }
}
