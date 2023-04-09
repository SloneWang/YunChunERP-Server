package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.ReviewRequest;

public interface ReviewRequestService extends IService<ReviewRequest> {
    Object reviewInformation(String reviewNo);
    Object reviewResult(String reviewNo,Integer id,boolean flag,String contractNo);
}
