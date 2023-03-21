package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.Contract;
import com.huang.entity.ContractProduct;
import com.huang.entity.PayPlan;
import com.huang.entity.ReviewRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReviewRequestMapper extends BaseMapper<ReviewRequest> {
    List<ReviewRequest> selectReviewInformation();
    List<ReviewRequest> selectOne(Integer id);
    boolean setContractProduct(String ContractNo);
    boolean setContract(@Param("reviewerNo") String reviewerNo,@Param("contractNo") String contractNo);
    boolean setPayPlan(String ContractNo);
    Boolean deleteContractProduct(String ContractNo);
    Boolean deleteContract(String ContractNo);
    Boolean deletePayPlan(String ContractNo);
}
