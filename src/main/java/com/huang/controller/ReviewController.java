package com.huang.controller;

import com.huang.common.Result;
import com.huang.service.PayPlanServiceImpl;
import com.huang.service.ReviewRequestServiceImpl;
import com.huang.vo.PayPlanPickVO;
import com.huang.vo.UpdatePayPlanVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Resource
    ReviewRequestServiceImpl reviewRequestService;

    @GetMapping("/selectReviewInformation/{reviewerNo}")
    public Object selectReviewInformation(@PathVariable String reviewerNo){
        return reviewRequestService.reviewInformation(reviewerNo);
    }

    @GetMapping("/reviewRequest/{reviewerNo}/{id}/{flag}")
    public Object reviewRequest(@PathVariable String reviewerNo,@PathVariable Integer id,@PathVariable boolean flag){
        return reviewRequestService.reviewResult(reviewerNo,id,flag);
    }

}
