package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.ReviewRequest;

import java.util.List;

public interface ReviewRequestService extends IService<ReviewRequest> {
    List<ReviewRequest> reviewInformation(String reviewNo);
    boolean reviewResult(String reviewNo,Integer id,boolean flag,String contractNo);
}
