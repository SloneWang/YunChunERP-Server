package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.dto.HeatUserDTO;
import com.huang.entity.*;

import com.huang.mapper.HeatUserMapper;
import com.huang.vo.HeatPayUserAmountsVO;
import com.huang.vo.HeatSaveUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HeatUserServiceImpl extends ServiceImpl<HeatUserMapper, HeatUser> implements HeatUserService{
    @Autowired
    HeatUserMapper heatUserMapper;
//    @Autowired
//    HeatAuthMapper heatAuthMapper;
    @Override
    @Scheduled(cron = "0 0 3 * * ?")
    public void updateData(){
        HeatSys heatSys=heatUserMapper.selectSysPara().get(0);
        List<HeatUser> allHeatUser=list();
        for(HeatUser heatUs:allHeatUser){
            if(System.currentTimeMillis()>heatUs.getDate().getTime()){
                //重置缴费状态
                if(heatUs.getPayStatus().equals("未缴费")){
                    heatUs.setFailPay(heatUs.getFailPay()+1);
                    heatUs.setUserBalance(heatUs.getUserBalance().subtract(heatUs.getPayAmount()));
                }
                heatUs.setPayStatus("未缴费");

                //重置缴费金额
                heatUs.setPayAmount(heatSys.getUnitPrice().multiply(heatUs.getHeatArea()).multiply(new BigDecimal(Integer.toString(heatSys.getPayCycle()))));

                //重置缴费日期
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(heatUs.getDate());
                rightNow.add(Calendar.MONTH,heatSys.getPayCycle());
                heatUs.setDate(new java.sql.Date(rightNow.getTime().getTime()));

                //若余额足够，自动帮用户缴费
                if(heatUs.getUserBalance().compareTo(heatUs.getPayAmount())>=0){
                    heatUs.setUserBalance(heatUs.getUserBalance().subtract(heatUs.getPayAmount()));
                    heatUs.setPayStatus("已缴费");
                }



            }
            //计算滞纳金
            if(heatUs.getUserBalance().compareTo(new BigDecimal("0"))<0){
                boolean flag=false;
                Date nowDate=new Date(System.currentTimeMillis());
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(nowDate);
                cal1.set(Calendar.HOUR_OF_DAY, 0);
                cal1.set(Calendar.MINUTE, 0);
                cal1.set(Calendar.SECOND, 0);
                cal1.set(Calendar.MILLISECOND, 0);
                for(int i=1;i<25;i++){
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(heatUs.getDate());
                    cal2.add(Calendar.MONTH,-i);
                    cal2.set(Calendar.HOUR_OF_DAY, 0);
                    cal2.set(Calendar.MINUTE, 0);
                    cal2.set(Calendar.SECOND, 0);
                    cal2.set(Calendar.MILLISECOND, 0);
                    if(cal1.getTime().equals(cal2.getTime())){
                        flag=true;
                        break;
                    }
                }
                if(flag) {
                    heatUs.setUserBalance(heatUs.getUserBalance().multiply(heatSys.getInterestRate().divide(new BigDecimal("100"),5,BigDecimal.ROUND_UNNECESSARY).add(new BigDecimal("1"))));
                }
            }




//            HeatUserSave heatUserSave=new HeatUserSave();
//            heatUserSave.setId(heatUs.getId());
//            heatUserSave.setHeatArea(heatUs.getHeatArea());
//            heatUserSave.setUserBalance(heatUs.getUserBalance());
//            heatUserSave.setAccount(heatUs.getAccount());
//            heatUserSave.setName(heatUs.getName());
//            heatUserSave.setDate(new java.sql.Date(heatUs.getDate().getTime()));
//            heatUserSave.setFailPay(heatUs.getFailPay());
//            heatUserSave.setDistrict(heatUs.getDistrict());
//            heatUserSave.setPhone(heatUs.getPhone());
//            heatUserSave.setPayAmount(heatUs.getPayAmount());
//            heatUserSave.setPayStatus(heatUs.getPayStatus());
            heatUserMapper.upDateUser(heatUs);

        }

    }

    @Override
    public Boolean saveUser( HeatSaveUserVO heatUser) {
        boolean flag=false;
        HeatSys heatSys=heatUserMapper.selectSysPara().get(0);
        List<HeatAuth> heatAdminAuth=heatUserMapper.selectUserAuth(heatUser.getUsername());
        List<HeatAuth> heatAdminAuthDis=heatUserMapper.selectUserAdminAuth(heatUser.getUsername());
        if(heatAdminAuth.size()!=0){
            for(HeatAuth heatAu:heatAdminAuth){
                //判断操作人权限是否足够
                if(heatUser.getDistrict().startsWith(heatAu.getCustomAuthority())){flag=true;break;}
            }
        }
        if(heatAdminAuthDis.size()!=0){
            for(HeatAuth heatAu:heatAdminAuthDis){
                //判断操作人权限是否足够
                if(heatUser.getDistrict().startsWith(heatAu.getAdminAuthority())){flag=true;break;}
            }
        }
        if(flag){
            if(heatUserMapper.ifUserExist(heatUser.getDistrict(),heatUser.getAccount()).size()==1){
                return heatUserMapper.upDateUserLimited(heatUser);
            }
            else {
                HeatUser heatUser1=new HeatUser();
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(new Date(System.currentTimeMillis()));
                rightNow.add(Calendar.MONTH, heatSys.getPayCycle());
                heatUser1.setDate(new java.sql.Date(rightNow.getTime().getTime()));
                heatUser1.setHeatArea(heatUser.getHeatArea());
                heatUser1.setUserBalance(new BigDecimal("0"));
                heatUser1.setAccount(heatUser.getAccount());
                heatUser1.setDistrict(heatUser.getDistrict());
                heatUser1.setPayAmount((heatSys.getUnitPrice().multiply(heatUser.getHeatArea())).multiply(new BigDecimal(Integer.toString(heatSys.getPayCycle()))));
                heatUser1.setName(heatUser.getName());
                heatUser1.setPayStatus("未缴费");
                heatUser1.setFailPay(0);
                heatUser1.setPhone(heatUser.getPhone());
                return heatUserMapper.insertUser(heatUser1);}
        }
        else {
            return false;
        }
    }

    @Override
    public List<HeatUserDTO> selectUsers(String username) {
        List<HeatAuth> heatAdminAuth=heatUserMapper.selectUserAuth(username);
        List<HeatAuth> heatAdminAuthDis=heatUserMapper.selectUserAdminAuth(username);
        List<HeatUser> heatUsers=new ArrayList<>();
        List<HeatUserDTO> heatUserDTOS=new ArrayList<>();
        HeatSys heatSys=heatUserMapper.selectSysPara().get(0);

        for(HeatAuth heatAu:heatAdminAuth){
            heatUsers.addAll(heatUserMapper.selectUserByAuth(heatAu.getCustomAuthority()));
        }
        for(HeatAuth heatAu:heatAdminAuthDis){
            heatUsers.addAll(heatUserMapper.selectUserByAdminAuth(heatAu.getAdminAuthority()));
        }
        //判断重复并去掉
        if(heatUsers.size()!=0) {
            for (int i = 0; i < heatUsers.size(); i++) {
                for (int j = i + 1; j < heatUsers.size(); j++) {
                    if (heatUsers.get(i).getId()==heatUsers.get(j).getId()) {
                        heatUsers.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }

        for(HeatUser heatUs:heatUsers){
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(heatUs.getDate());
            HeatUserDTO heatUserDTO=new HeatUserDTO();
            heatUserDTO.setHeatArea(heatUs.getHeatArea());
            heatUserDTO.setAccount(heatUs.getAccount());
            heatUserDTO.setDistrict(heatUs.getDistrict());
            heatUserDTO.setName(heatUs.getName());
            heatUserDTO.setPhone(heatUs.getPhone());
            heatUserDTO.setPayStatus(heatUs.getPayStatus());
            heatUserDTO.setPayDate(rightNow.getTime());
            heatUserDTO.setPayAmount(heatUs.getPayAmount());
            heatUserDTO.setUserBalance(heatUs.getUserBalance());
            heatUserDTO.setId(heatUs.getId());
            if(heatUs.getFailPay()>=heatSys.getLateTimes())heatUserDTO.setUserStatus("多次欠缴");
            else if (heatUs.getFailPay()>0) heatUserDTO.setUserStatus("欠缴");
            else heatUserDTO.setUserStatus("信用良好");
            heatUserDTOS.add(heatUserDTO);
        }

        return heatUserDTOS;
    }

    @Override
    public Boolean payUserAmounts(HeatPayUserAmountsVO heatPayUserAmountsVO) {
        QueryWrapper<HeatUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",heatPayUserAmountsVO.getId());
        HeatUser heatUser=getOne(queryWrapper);
        HeatRecord heatRecord=new HeatRecord();
        heatRecord.setAmount(heatPayUserAmountsVO.getPayAmount());
        heatRecord.setCollector(heatPayUserAmountsVO.getUsername());
        heatRecord.setAccount(heatUser.getAccount());
        heatRecord.setDate(new Date(System.currentTimeMillis()));
        heatRecord.setDistrict(heatUser.getDistrict());
        if(heatUserMapper.insertRecord(heatRecord)==1){
           heatUser.setUserBalance(heatUser.getUserBalance().add(heatPayUserAmountsVO.getPayAmount()));

           if(heatUser.getUserBalance().compareTo(heatUser.getPayAmount()) >= 0){
               heatUser.setUserBalance(heatUser.getUserBalance().subtract(heatUser.getPayAmount()));
               heatUser.setFailPay(0);
               heatUser.setPayStatus("已缴费");
           }
           else if(heatUser.getUserBalance().compareTo(new BigDecimal("0")) >= 0){
               heatUser.setFailPay(0);
           }
           return heatUserMapper.upDateUser(heatUser);}
        else return false;
    }

    @Override
    public  Boolean deleteUser(int id) {
        if(removeById(id)) return true;
        else return false;
    }

    @Override
    public List<HeatRecord> selectHeatRecords(int id) {
        QueryWrapper<HeatUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        HeatUser heatUser=getOne(queryWrapper);
        return heatUserMapper.selectRecordByUser(heatUser.getDistrict(),heatUser.getAccount());
    }
}
