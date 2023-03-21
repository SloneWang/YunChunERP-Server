package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.Result;
import com.huang.entity.PayPlan;
import com.huang.entity.PayplanHistory;
import com.huang.vo.PayPlanPickVO;
import com.huang.vo.UpdatePayPlanVO;

import java.util.List;

public interface PayPlanService extends IService<PayPlan> {
    boolean savePayPlan(PayPlan payPlan);
    //PayPlan
    Object selectPayPlan(Integer id);
    Object updatePayPlan(UpdatePayPlanVO payPlanData);
    //List<PayplanHistory>
    Object payPlanHistory();
    //List<PayPlan>
    Object payPlanPick(PayPlanPickVO payPlanPickVO);
    void updateData();

}
