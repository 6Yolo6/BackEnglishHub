package com.example.englishhub.controller;

import com.example.englishhub.entity.LearningPlan;
import com.example.englishhub.entity.WordReview;
import com.example.englishhub.service.WordReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hahaha
 * @Date: 2024/4/8 15:13
 */

@RestController
@RequestMapping("/wordReview")
public class WordReviewController {

    @Autowired
    private WordReviewService wordReviewService;

    @Autowired
    private HttpServletRequest request;


}
