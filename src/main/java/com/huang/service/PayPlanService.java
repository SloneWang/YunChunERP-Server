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
    Result selectPayPlan(Integer id);
    Result updatePayPlan(UpdatePayPlanVO payPlanData);
    //List<PayplanHistory>
    Result payPlanHistory();
    //List<PayPlan>
    Result payPlanPick(PayPlanPickVO payPlanPickVO);
    void updateData();

}
