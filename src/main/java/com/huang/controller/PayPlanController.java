package com.huang.controller;

import com.huang.common.Result;
import com.huang.service.PayPlanServiceImpl;
import com.huang.vo.PayPlanPickVO;
import com.huang.vo.UpdatePayPlanVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payPlan")
public class PayPlanController {
    @Resource
    PayPlanServiceImpl payPlanService;

    @GetMapping("/selectPayPlan/{id}")
    //PayPlan
    public Result selectOnePayPlan(@PathVariable Integer id){
        return payPlanService.selectPayPlan(id);
    }

    @PostMapping("/updatePayPlan")
    public Result updatePayPlan(@RequestBody UpdatePayPlanVO updatePayPlanVO){
        return payPlanService.updatePayPlan(updatePayPlanVO);
    }

    @GetMapping("/PayPlanHistory")
    //List<PayplanHistory>
    public Result selectPayPlanHistory(){
        return payPlanService.payPlanHistory();
    }

    @PostMapping("/PayPlanPick")
    //List<PayPlan>
    public Result PayPlanPick(@RequestBody PayPlanPickVO payPlanPickVO){
        return payPlanService.payPlanPick(payPlanPickVO);
    }
}
