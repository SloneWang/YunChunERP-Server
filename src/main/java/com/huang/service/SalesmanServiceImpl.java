package com.huang.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.Result;
import com.huang.dto.ContractSimpleDTO;
import com.huang.entity.*;
import com.huang.mapper.SalesmanMapper;
import com.huang.vo.SaveContractVO;
import com.huang.vo.UpdateContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SalesmanServiceImpl extends ServiceImpl<SalesmanMapper, Contract> implements SalesmanService{
    @Autowired
    SalesmanMapper salesmanMapper;
    @Autowired
    PayPlanServiceImpl payPlanServiceImpl;
    @Autowired
    CustomerInformationServiceImpl customerInformationService;
    @Override
    public Result contractSimp() {

        List<ContractSimpleDTO> contractSimpAll;
        try {
            contractSimpAll = new ArrayList<>();
            for(Contract contra:list()){
                ContractSimpleDTO conTemp=new ContractSimpleDTO();
                conTemp.setId(contra.getId());
                conTemp.setContractLifecycle(contra.getContractLifecycle());
                conTemp.setContractNumber(contra.getContractNumber());
                conTemp.setCustomerCompany(contra.getCustomerCompany());
                conTemp.setCustomerName(contra.getCustomerName());
                conTemp.setCustomerPhonenum(contra.getCustomerPhonenum());
                conTemp.setEmployeeName(contra.getEmployeeName());
                conTemp.setSignDate(contra.getSignDate());
                contractSimpAll.add(conTemp);
            }
            return new Result("200","",contractSimpAll);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
     }

     @Override
     public Result contract(HttpServletResponse response,Integer id){
         try {
             List<Contract> data = salesmanMapper.selectContractById(id);
             if(data.size()!=0){
                 String pathName=data.get(0).getContractDocument();
                 if(pathName!=null&&!pathName.equals("")){
                     InputStream is =null;
                     OutputStream os=null;
                     try {
                         String fileName=pathName.substring(pathName.lastIndexOf("\\")+1);
                         response.reset();
                         response.setCharacterEncoding("UTF-8");
                         response.setContentType("multipart/form-data");
                         response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
                         File file=new File(pathName);
                         is= Files.newInputStream(file.toPath());
                         os=response.getOutputStream();
                         byte[] buffer=new byte[1024];
                         int index=0;
                         while ((index=is.read(buffer))!=-1){
                             os.write(buffer,0,index);
                             os.flush();
                         }
                     } catch (IOException e) {
                         throw new RuntimeException(e);
                     } finally {
                         if (os != null) {
                             os.close();
                         }
                         if (is != null) {
                             is.close();
                         }
                     }
                 }
                 else {return new Result("500","合同附件路径不存在",data.get(0));}
                 return new Result("200","",data.get(0));
             }
             else return new Result("204","未查询到符合条件的数据","");
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }

    @Override
     public Result productInformation(){

        List<ProductInformation> data;
        try {
            data = salesmanMapper.selectProduct();
            if(data.size()!=0) return new Result("200","",data);
            else return new Result("204","未查询到符合条件的数据","");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Result saveContract(CommonsMultipartFile file,SaveContractVO saveData) {

        //还需要合同编号重复性检查
        boolean saveDocumentFlag=false;
        try {
            Contract contractTemp = new Contract();
            contractTemp.setContractNumber(saveData.getContractNumber());
            contractTemp.setSignDate(new Date(System.currentTimeMillis()));
            contractTemp.setInvoiceType(saveData.getInvoiceType());
            if(saveData.getEmployeeNo()!=null&&!saveData.getEmployeeNo().equals("")){
                List<User> users=salesmanMapper.selectEmployeeInformation(saveData.getEmployeeNo());
                if(users.size()!=0) {
                    User user=salesmanMapper.selectEmployeeInformation(saveData.getEmployeeNo()).get(0);
                    contractTemp.setEmployeeName(user.getName());
                    contractTemp.setEmployeeNo(saveData.getEmployeeNo());
                }
                else {return new Result("415","无法找到对应编号的员工","");}
            }
            else return new Result("415","员工编号缺失，创建合同失败","");
            contractTemp.setCustomerCompany(saveData.getCustomerCompany());
            contractTemp.setCustomerName(saveData.getCustomerName());
            contractTemp.setCustomerPhonenum(saveData.getCustomerPhonenum());
            contractTemp.setSecCustomerName(saveData.getSecCustomerName());
            contractTemp.setSecCustomerPhonenum(saveData.getSecCustomerPhonenum());
            //给客户信息表输入数据
            CustomerInformation customerInformation = new CustomerInformation();
            customerInformation.setCustomerName(saveData.getCustomerName());
            customerInformation.setCustomerName2(saveData.getSecCustomerName());
            customerInformation.setCustomerPhone(saveData.getCustomerPhonenum());
            customerInformation.setCustomerPhone2(saveData.getSecCustomerPhonenum());
            customerInformation.setCompany(saveData.getCustomerCompany());
            customerInformation.setAddress(saveData.getInstallAddress());
            customerInformation.setChannel(saveData.getChannel());
            customerInformation.setCustomerJob(saveData.getCustomerJob());
            customerInformation.setIndustry(saveData.getIndustry());
            customerInformation.setContractNumber(contractTemp.getContractNumber());

            contractTemp.setContractLifecycle("合同已签订");
            contractTemp.setInstallAddress(saveData.getInstallAddress());

            contractTemp.setDeliveryMethod(saveData.getDeliveryMethod());
            contractTemp.setUpAmount(saveData.getUpAmount());
            contractTemp.setProductNumber(saveData.getProductNumber());
            contractTemp.setProductNo(saveData.getProductNo());

            //创建回款计划
            PayPlan payPlan = new PayPlan();
            payPlan.setPayCycle(saveData.getPayCycle());
            payPlan.setPayDate(saveData.getPayDate());
            payPlan.setContractNumber(saveData.getContractNumber());
            payPlan.setPayStatus("未逾期");
            payPlan.setAmountPaid(new BigDecimal("0"));
            payPlan.setBalance(new BigDecimal("0"));
            payPlan.setAmountOnce(saveData.getAmountPlan());
            payPlan.setAmountPlan(saveData.getAmountPlan());
            payPlan.setLateTimes(0);
            ProductInformation productInformation = salesmanMapper.selectOneProduct(saveData.getProductNo()).get(0);
            payPlan.setAmountNotPaid(saveData.getUpAmount().add(new BigDecimal(saveData.getProductNumber()).multiply(productInformation.getPrice())));
            payPlan.setEmployeeName(contractTemp.getEmployeeName());
            payPlan.setEmployeeNo(saveData.getEmployeeNo());
            payPlan.setPayCreateDate(new Date(System.currentTimeMillis()));

            //合同文件导入
            String uploadFileName = file.getOriginalFilename();

            File realPath = new File("D:\\Environment\\testData");
            if (!realPath.exists()) {
                if (realPath.mkdir()) return new Result("501", "未能成功创建合同存储文件夹", "");
            }
            String RandomPath= UUID.randomUUID().toString().replace("-","");

            if(uploadFileName!=null&&!uploadFileName.equals("")){
                OutputStream os = null;
                InputStream is = null;
                try {
                    String lastName=uploadFileName.substring(uploadFileName.lastIndexOf("."));
                    is = file.getInputStream();
                    os = Files.newOutputStream(new File(realPath, RandomPath+lastName).toPath());
                    int len;
                    byte[] buffer = new byte[1024];
                    while ((len = is.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                        os.flush();
                    }
                    contractTemp.setContractDocument(realPath+"\\\\"+RandomPath+lastName);
                    saveDocumentFlag=true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }finally {
                    if (os != null) {
                        os.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                }
            }

            if (!saveOrUpdate(contractTemp)){
                if(contractTemp.getContractDocument()!=null&&!contractTemp.getContractDocument().equals(""))
                {File file1=new File(contractTemp.getContractDocument());
                    if(file1.exists()) file1.delete();}
                return new Result("500","合同信息保存失败","");
            }
            else if (!payPlanServiceImpl.savePayPlan(payPlan)) {
                if(contractTemp.getContractDocument()!=null&&!contractTemp.getContractDocument().equals(""))
                {File file1=new File(contractTemp.getContractDocument());
                    if(file1.exists()) file1.delete();}
                salesmanMapper.deleteByContractNumber(contractTemp.getContractNumber());
                return new Result("500","合同信息保存失败","");
            }
            else if(!customerInformationService.saveOrUpdate(customerInformation)){
                return new Result("500","合同信息保存成功，但客户信息保存失败","");
            }
            else if (!saveDocumentFlag) {
                if(contractTemp.getContractDocument()!=null&&!contractTemp.getContractDocument().equals(""))
                {File file1=new File(contractTemp.getContractDocument());
                if(file1.exists()) file1.delete();}
                return new Result("500","合同信息保存成功，但合同文本保存失败","");
            }
            else return new Result("200","","");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Result updateContract(CommonsMultipartFile file,UpdateContractVO saveData) {

        try {
            boolean saveDocumentFlag=false;
//            boolean employeeNoFlag=false;

            Contract contractTemp = new Contract();
            contractTemp.setInvoiceType(saveData.getInvoiceType());
//            if(saveData.getEmployeeNo()!=null&&!saveData.getEmployeeNo().equals("")){
//                List<User> users=salesmanMapper.selectEmployeeInformation(saveData.getEmployeeNo());
//                if(users.size()!=0) {
//                    User user=salesmanMapper.selectEmployeeInformation(saveData.getEmployeeNo()).get(0);
//                    contractTemp.setEmployeeName(user.getName());
//                    contractTemp.setEmployeeNo(saveData.getEmployeeNo());
//                    employeeNoFlag=true;
//                }
//            }
//            else employeeNoFlag=true;
            contractTemp.setId(saveData.getId());
            contractTemp.setCustomerCompany(saveData.getCustomerCompany());
            contractTemp.setCustomerName(saveData.getCustomerName());
            contractTemp.setCustomerPhonenum(saveData.getCustomerPhonenum());
            contractTemp.setSecCustomerName(saveData.getSecCustomerName());
            contractTemp.setSecCustomerPhonenum(saveData.getSecCustomerPhonenum());
            Contract contractSelect= salesmanMapper.selectContractById(saveData.getId()).get(0);
            String orginContractNumber=contractSelect.getContractNumber();
            //给客户信息表输入数据
            CustomerInformation customerInformation=new CustomerInformation();
            customerInformation.setId(salesmanMapper.selectCustomerInformationByContractNo(orginContractNumber).get(0).getId());
            customerInformation.setCustomerName(saveData.getCustomerName());
            customerInformation.setCustomerName2(saveData.getSecCustomerName());
            customerInformation.setCustomerPhone(saveData.getCustomerPhonenum());
            customerInformation.setCustomerPhone2(saveData.getSecCustomerPhonenum());
            customerInformation.setCompany(saveData.getCustomerCompany());
            customerInformation.setAddress(saveData.getInstallAddress());
            customerInformation.setChannel(saveData.getChannel());
            customerInformation.setCustomerJob(saveData.getCustomerJob());
            customerInformation.setIndustry(saveData.getIndustry());

            contractTemp.setInstallAddress(saveData.getInstallAddress());
            contractTemp.setDeliveryMethod(saveData.getDeliveryMethod());
            contractTemp.setUpAmount(saveData.getUpAmount());
            contractTemp.setProductNumber(saveData.getProductNumber());
            contractTemp.setProductNo(saveData.getProductNo());

            contractTemp.setEstimatedInstallDate(saveData.getEstimatedInstallDate());
            contractTemp.setInstallDate(saveData.getInstallDate());
            contractTemp.setDeliveryFee(saveData.getDeliveryFee());
            contractTemp.setInstallFee(saveData.getInstallFee());

            //修改合同文本
            String uploadFileName = file.getOriginalFilename();
            if(uploadFileName!=null&&!uploadFileName.equals("")){
                OutputStream os = null;
                InputStream is = null;
                try {
                    if(new File(contractSelect.getContractDocument()).delete()){
                        is = file.getInputStream();
                        os = Files.newOutputStream(new File(contractSelect.getContractDocument()).toPath());
                        int len;
                        byte[] buffer = new byte[1024];
                        while ((len = is.read(buffer)) != -1) {
                            os.write(buffer, 0, len);
                            os.flush();
                        }
                        saveDocumentFlag=true;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }finally {
                    if (os != null) {
                        os.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                }
            }
            else {saveDocumentFlag=true;}

            //留存记录
            contractTemp.setContractNumber(contractSelect.getContractNumber());
            ContractHistory contractHistory = new ContractHistory();
            contractHistory.setModifyTime(new java.util.Date(System.currentTimeMillis()));
            contractHistory.setContractNo(contractSelect.getContractNumber());
            contractHistory.setModifyBy(saveData.getModifyBy());


            //重新计算利润率(不知道成本，目前没法算)
//        if(contractSelect.getDeliveryFee()!=null&&contractSelect.getInstallFee()!=null){
//        }

            if (!saveDocumentFlag){
                return new Result("500","合同文本及信息修改失败","");
            }
            else if(!salesmanMapper.insertContractHistory(contractHistory)){
                return new Result("500","修改记录插入失败，合同信息修改失败","");
            }
            else if (!saveOrUpdate(contractTemp)) {
                return new Result("500","合同文本修改成功，但合同信息保存失败","");
            }
            else if(!customerInformationService.saveOrUpdate(customerInformation)){
                return new Result("500","合同信息修改成功，但客户信息修改失败","");
            }
//            else if(!employeeNoFlag){
//                return new Result("500","其他信息修改成功，但员工编号修改失败","");
//            }
            else return new Result("200","","");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Result contractHistory() {
        try {
            List<ContractHistory> data=salesmanMapper.selectContractHistory();
            if(data.size()!=0) return new Result("200","",data);
            else return new Result("204","未查询到符合条件的数据","");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Result deleteContract(Integer id,String employeeNo) {
        try {
            List<Contract> contracts=salesmanMapper.selectContractById(id);
            if(contracts.size()==0){return new Result("415","不存在该合同","");}
            Contract contractTemp=contracts.get(0);
            ContractHistory contractHistory = new ContractHistory();
            contractHistory.setModifyTime(new java.util.Date(System.currentTimeMillis()));
            contractHistory.setContractNo(contractTemp.getContractNumber());
            contractHistory.setModifyBy(employeeNo);
            if(!salesmanMapper.insertContractHistory(contractHistory)){
                return new Result("500","修改记录插入失败，合同信息删除失败","");
            }
            if(!salesmanMapper.deletePayPlanByContractNumber(contractTemp.getContractNumber()))return new Result("500","回款计划删除失败","");
            if(!removeById(id))return new Result("500","合同信息删除失败","");
            if(contractTemp.getContractDocument()!=null&&!contractTemp.getContractDocument().equals("")) {
                File file1=new File(contractTemp.getContractDocument());
                if(file1.exists()){
                    if(!file1.delete())return new Result("500","合同文档删除失败","");
                }
            }
            else {return new Result("500","合同文档无记录","");}
            return new Result("200","","");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}




