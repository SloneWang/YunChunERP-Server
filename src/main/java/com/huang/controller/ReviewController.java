package com.huang.controller;


import com.huang.entity.ReviewRequest;
import com.huang.service.ReviewRequestServiceImpl;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Resource
    ReviewRequestServiceImpl reviewRequestService;

    @GetMapping("/selectReviewInformation/{reviewerNo}")
    public List<ReviewRequest> selectReviewInformation(@PathVariable String reviewerNo){
        try {
            return reviewRequestService.reviewInformation(reviewerNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/reviewRequest/{reviewerNo}/{id}/{flag}/{contractNo}")
    public boolean reviewRequest(@PathVariable String reviewerNo,@PathVariable Integer id,@PathVariable boolean flag,@PathVariable String contractNo){
        try {
            return reviewRequestService.reviewResult(reviewerNo,id,flag,contractNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
