package com.huang.controller;

import com.huang.common.Result;

import com.huang.service.ReviewRequestServiceImpl;
import com.huang.vo.PayPlanPickVO;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Resource
    ReviewRequestServiceImpl reviewRequestService;

    @GetMapping("/selectReviewInformation/{reviewerNo}")
    public Object selectReviewInformation(@PathVariable String reviewerNo){
        try {
            return reviewRequestService.reviewInformation(reviewerNo);
        } catch (Exception e) {
            return e.toString();
        }
    }

    @GetMapping("/reviewRequest/{reviewerNo}/{id}/{flag}/{contractNo}")
    public Object reviewRequest(@PathVariable String reviewerNo,@PathVariable Integer id,@PathVariable boolean flag,@PathVariable String contractNo){
        try {
            return reviewRequestService.reviewResult(reviewerNo,id,flag,contractNo);
        } catch (Exception e) {
            return e.toString();
        }
    }

}
