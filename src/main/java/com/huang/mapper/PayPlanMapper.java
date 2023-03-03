package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Mapper
@Repository
public interface PayPlanMapper extends BaseMapper<PayPlan> {
    List<PayPlan> selectOnePayPlan(String contractNumber);
    List<PayPlan> selectPayPlanById(Integer id);
    List<PayplanHistory> selectPayPlanHistory();
    List<PayPlan> PayPlanSelectAllAll();
    List<PayPlan> PayPlanSelectAllAbout(Date now);
    List<PayPlan> PayPlanSelectAllLate(BigDecimal ZERO);
    List<PayPlan> PayPlanSelectSingleAll(String employeeNo);
    List<PayPlan> PayPlanSelectSingleAbout(String employeeNo,Date now);
    List<PayPlan> PayPlanSelectSingleLate(String employeeNo,BigDecimal ZERO);
    boolean insertPayplanHistory(PayplanHistory payplanHistory);

}