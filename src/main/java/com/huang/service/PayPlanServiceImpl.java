package com.huang.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.Result;
import com.huang.entity.*;
import com.huang.mapper.PayPlanMapper;
import com.huang.mapper.SalesmanMapper;
import com.huang.vo.PayPlanPickVO;
import com.huang.vo.UpdatePayPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PayPlanServiceImpl extends ServiceImpl<PayPlanMapper, PayPlan> implements PayPlanService  {
    @Autowired
    PayPlanMapper payPlanMapper;
    @Autowired
    SalesmanMapper salesmanMapper;
    @Autowired
    SalesmanServiceImpl salesmanService;
    @Override
    public boolean savePayPlan(PayPlan payPlan){
        return saveOrUpdate(payPlan);
    }
    @Override
    public Object selectPayPlan(Integer id){
        try {
            List<PayPlan> data=new ArrayList<>();
            List<Contract> contracts=salesmanMapper.selectContractById(id);
            if(contracts.size()!=0){
                String contractNumber=contracts.get(0).getContractNo();
                data = payPlanMapper.selectOnePayPlan(contractNumber);
            }
            if(data.size()!=0) return data.get(0);
            else return "未查询到符合条件的数据";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object updatePayPlan(UpdatePayPlanVO payPlanData) {
        try {
            PayPlan payPlanTemp=new PayPlan();
            payPlanTemp.setId(payPlanData.getId());
            payPlanTemp.setAmountPlan(payPlanData.getAmountPlan());
            payPlanTemp.setPayCycle(payPlanData.getPayCycle());
            payPlanTemp.setAmountNotPaid(payPlanData.getAmountNotPaid());

            if(payPlanData.getEmployeeNo()!=null&&!payPlanData.getEmployeeNo().equals("")){
                List<User> users=salesmanMapper.selectEmployeeInformation(payPlanData.getEmployeeNo());
                if(users.size()!=0) {
                    payPlanTemp.setEmployeeNo(payPlanData.getEmployeeNo());
                }
                else {return "无法找到对应编号的员工";}
            }

            //改动记录
            PayplanHistory payplanHistory=new PayplanHistory();
            PayPlan payPlanSelect=payPlanMapper.selectPayPlanById(payPlanData.getId()).get(0);
            payplanHistory.setContractNo(payPlanSelect.getContractNo());
            payplanHistory.setModifyBy(payPlanData.getModifyBy());
            payplanHistory.setModifyTime(new java.util.Date(System.currentTimeMillis()));



            if (!payPlanMapper.insertPayplanHistory(payplanHistory)){
                return "修改记录插入失败，合同信息修改失败";
            }
            else if (!saveOrUpdate(payPlanTemp)) {
                return "合同信息修改失败";
            }
            else return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object payPlanHistory() {
        try {
            List<PayplanHistory> data = payPlanMapper.selectPayPlanHistory();
            if(data.size()!=0) return data;
            else return "未查询到符合条件的数据";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object payPlanPick(PayPlanPickVO payPlanPickVO) {
        if(payPlanPickVO.getScale().equals("全部") && payPlanPickVO.getStatus().equals("全部")){
            try {
                List<PayPlan> data = payPlanMapper.PayPlanSelectAllAll();
                if(data.size()!=0) return data;
                else return "未查询到符合条件的数据";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else if(payPlanPickVO.getScale().equals("全部")&& payPlanPickVO.getStatus().equals("即将逾期")){
            try {
                List<PayPlan> data = payPlanMapper.PayPlanSelectAllAbout(new Date(System.currentTimeMillis()));
                if(data.size()!=0) return data;
                else return "未查询到符合条件的数据";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else if(payPlanPickVO.getScale().equals("全部") && payPlanPickVO.getStatus().equals("已逾期")){
            try {
                List<PayPlan> data = payPlanMapper.PayPlanSelectAllLate(new BigDecimal("0"));
                if(data.size()!=0) return data;
                else return "未查询到符合条件的数据";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else if(payPlanPickVO.getScale().equals("与我相关") && payPlanPickVO.getStatus().equals("全部")){
            try {
                List<PayPlan> data = payPlanMapper.PayPlanSelectSingleAll(payPlanPickVO.getEmployeeNo());
                if(data.size()!=0) return data;
                else return "未查询到符合条件的数据";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else if(payPlanPickVO.getScale().equals("与我相关") && payPlanPickVO.getStatus().equals("即将逾期")){
            try {
                List<PayPlan> data = payPlanMapper.PayPlanSelectSingleAbout(payPlanPickVO.getEmployeeNo(),new Date(System.currentTimeMillis()));
                if(data.size()!=0) return data;
                else return "未查询到符合条件的数据";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else if(payPlanPickVO.getScale().equals("与我相关") && payPlanPickVO.getStatus().equals("已逾期")){
            try {
                List<PayPlan> data = payPlanMapper.PayPlanSelectSingleLate(payPlanPickVO.getEmployeeNo(),new BigDecimal("0"));
                if(data.size()!=0) return data;
                else return "未查询到符合条件的数据";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else return "所输入条件表述有误,查询失败";
    }

    @Override
    @Scheduled(cron = "0 0 3 * * ?")
    public void updateData(){
        List<PayPlan> allPayPlan=list();
        for(PayPlan payPl:allPayPlan){
            //检测并标记即将逾期
            Calendar tempDate =Calendar.getInstance();
            tempDate.setTime(payPl.getPayDate());
            tempDate.add(Calendar.DATE,-3);
            java.util.Date earlyDate = tempDate.getTime();
            if(earlyDate.getTime()<System.currentTimeMillis()
                    &&System.currentTimeMillis()<payPl.getPayDate().getTime()
                    &&payPl.getPayStatus().equals("未缴费")){
                //若余额足够，自动帮用户缴费
                if(payPl.getBalance().compareTo(payPl.getAmountOnce())>=0){
                    payPl.setBalance(payPl.getBalance().subtract(payPl.getAmountOnce()));
                    payPl.setPayStatus("已缴费");
                    payPl.setAmountNotPaid(payPl.getAmountNotPaid().subtract(payPl.getAmountOnce()));
                    payPl.setAmountPaid(payPl.getAmountPaid().add(payPl.getAmountOnce()));
                }
                else payPl.setPayStatus("即将逾期");
            }

            //过还款日的处理
            tempDate.add(Calendar.DATE,4);
            java.util.Date lateDate = tempDate.getTime();
            if(System.currentTimeMillis()>lateDate.getTime()){

                //重置缴费状态
                if(payPl.getPayStatus().equals("即将逾期")||payPl.getPayStatus().equals("已逾期")){
                    payPl.setLateTimes(payPl.getLateTimes()+1);
                    payPl.setAmountNotPaid(payPl.getAmountNotPaid().subtract(payPl.getAmountOnce()));
                    payPl.setBalance(payPl.getBalance().subtract(payPl.getAmountOnce()));
                    payPl.setPayStatus("已逾期");
                    payPl.setAmountOnce(payPl.getAmountPlan());
                    Calendar planDate =Calendar.getInstance();
                    planDate.setTime(payPl.getPayDate());
                    planDate.add(Calendar.MONTH,payPl.getPayCycle());
                    payPl.setPayDate(new Date(planDate.getTime().getTime()));
                    if(payPl.getAmountOnce().compareTo(payPl.getAmountNotPaid())>0){
                        payPl.setAmountOnce(payPl.getAmountNotPaid());
                    }
                }
                else if(payPl.getPayStatus().equals("已缴费")){
                    payPl.setPayStatus("未缴费");
                    payPl.setAmountOnce(payPl.getAmountPlan());
                    Calendar planDate =Calendar.getInstance();
                    planDate.setTime(payPl.getPayDate());
                    planDate.add(Calendar.MONTH,payPl.getPayCycle());
                    payPl.setPayDate(new Date(planDate.getTime().getTime()));
                    if(payPl.getAmountOnce().compareTo(payPl.getAmountNotPaid())>0){
                        payPl.setAmountOnce(payPl.getAmountNotPaid());
                    }
                }

                if(payPl.getAmountOnce().compareTo(new BigDecimal("0"))==0){
                    if(payPl.getBalance().compareTo(new BigDecimal("0"))==0) {payPl.setPayStatus("回款结束");}
                    else if(payPl.getBalance().compareTo(new BigDecimal("0"))<0){payPl.setPayStatus("欠款未还清");}
                    else {payPl.setPayStatus("回款结束，账户有余额");}
                }
            }
            saveOrUpdate(payPl);
        }
    }
}
