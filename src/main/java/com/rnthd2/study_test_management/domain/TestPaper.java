package com.rnthd2.study_test_management.domain;

import java.time.LocalDateTime;
import java.util.List;

public class TestPaper {
    private Long id;
    private List<Test> tests;
    private LocalDateTime testTime;
    private int score;
}
