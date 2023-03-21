package com.huang.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.Result;
import com.huang.entity.ContractProduct;
import com.huang.entity.ReviewRequest;
import com.huang.mapper.ReviewRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class ReviewRequestServiceImpl extends ServiceImpl<ReviewRequestMapper, ReviewRequest> implements ReviewRequestService {
    @Autowired
    ReviewRequestMapper reviewRequestMapper;

    @Override
    public Object reviewInformation(String reviewNo) {
        return reviewRequestMapper.selectReviewInformation();
    }

    @Override
    @Transactional
    public Object reviewResult(String reviewNo, Integer id, boolean flag) {
        try {

            ReviewRequest tempReviewRequest=reviewRequestMapper.selectOne(id).get(0);
            if(tempReviewRequest.getReviewType().equals("创建新合同")){
                tempReviewRequest.setReviewerNo(reviewNo);
                tempReviewRequest.setReviewDate(new Date(System.currentTimeMillis()));
                if(flag){
                    tempReviewRequest.setReviewResult("审核通过");
                    reviewRequestMapper.setPayPlan(tempReviewRequest.getIndexNo());
                    reviewRequestMapper.setContract(reviewNo,tempReviewRequest.getIndexNo());
                    reviewRequestMapper.setContractProduct(tempReviewRequest.getIndexNo());
                }
                else{
                    tempReviewRequest.setReviewResult("审核未通过");
                    reviewRequestMapper.deleteContract(tempReviewRequest.getIndexNo());
                    reviewRequestMapper.deletePayPlan(tempReviewRequest.getIndexNo());
                    reviewRequestMapper.deleteContractProduct(tempReviewRequest.getIndexNo());
                }
                saveOrUpdate(tempReviewRequest);
                return true;
            }
            else return "审核类型不存在";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
