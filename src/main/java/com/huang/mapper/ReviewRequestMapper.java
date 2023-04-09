package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
    boolean setContract(@Param("reviewerNo") String reviewerNo,@Param("contractNo") String contractNo);
    Boolean deleteContractProduct(String ContractNo);
    Boolean deleteContract(String ContractNo);
    boolean upDateContractHistory(String reviewBy,Integer id);
}
