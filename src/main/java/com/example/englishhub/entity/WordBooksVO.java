package com.example.englishhub.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: hahaha
 * @Date: 2024/5/12 21:05
 */

@Data
@ToString
public class WordBooksVO {

    private Integer id;
    private String name;
    private Integer learnedToday;
    private Integer toLearnToday;
    private Integer reviewedToday;
    private Integer toReviewToday;
    private Integer learnedWords;
    private Integer planStatus;
    private Integer totalWords;
    private Integer masteredWords;
    private Integer learnedDays;
    private Integer totalDays;
}
