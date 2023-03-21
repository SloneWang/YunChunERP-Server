package com.huang.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.Result;
import com.huang.dto.AllContractDTO;
import com.huang.dto.ContractSimpleDTO;
import com.huang.entity.*;
import com.huang.mapper.CustomerInformationMapper;
import com.huang.mapper.SalesmanMapper;
import com.huang.vo.SaveContractVO;
import com.huang.vo.UpdateContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
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
    @Autowired
    CustomerInformationMapper customerInformationMapper;
    @Autowired
    ReviewRequestServiceImpl reviewRequestService;
    @Override
    @Transactional
    public Object contractSimp() {

        List<ContractSimpleDTO> contractSimpAll;
        try {
            contractSimpAll = new ArrayList<>();
            List<Contract> allContracts=salesmanMapper.selectAllContract();
            for(Contract contra:allContracts){
                ContractSimpleDTO conTemp=new ContractSimpleDTO();
                conTemp.setId(contra.getId());
                conTemp.setContractNumber(contra.getContractNo());
                CustomerInformation tempCustomerInformation=customerInformationMapper.selectOneCustomer(contra.getMemberNo()).get(0);
                conTemp.setCustomerName(tempCustomerInformation.getCustomerName());
                conTemp.setCustomerPhonenum(tempCustomerInformation.getCustomerPhone());
                conTemp.setCustomerCompany(tempCustomerInformation.getCompany());
                conTemp.setSignDate(contra.getSignDate());
                conTemp.setEmployeeName(salesmanMapper.selectEmployeeInformation(contra.getEmployeeNo()).get(0).getName());
                conTemp.setContractLifecycle(contra.getContractLifecycle());
                contractSimpAll.add(conTemp);
            }
            return contractSimpAll;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
     }

     @Override
     @Transactional
     public Object contract(HttpServletResponse response,Integer id){
         try {
             AllContractDTO allContractDTO=new AllContractDTO();
             List<Contract> data = salesmanMapper.selectContractById(id);
             if(data.size()!=0){
                 Contract tempContract=data.get(0);
                 allContractDTO.setId(id);
                 allContractDTO.setContractNumber(tempContract.getContractNo());
                 allContractDTO.setSignDate(tempContract.getSignDate());
                 allContractDTO.setInvoiceType(tempContract.getInvoiceType());
                 allContractDTO.setEmployeeNo(tempContract.getEmployeeNo());
                 allContractDTO.setEmployeeName(salesmanMapper.selectEmployeeInformation(tempContract.getEmployeeNo()).get(0).getName());
                 allContractDTO.setReviewerNo(tempContract.getReviewerNo());
                 allContractDTO.setReviewerName(salesmanMapper.selectEmployeeInformation(tempContract.getReviewerNo()).get(0).getName());
                 CustomerInformation tempCustomerInformation=customerInformationMapper.selectOneCustomer(tempContract.getMemberNo()).get(0);
                 allContractDTO.setCustomerCompany(tempCustomerInformation.getCompany());
                 allContractDTO.setCustomerName(tempCustomerInformation.getCustomerName());
                 allContractDTO.setCustomerPhonenum(tempCustomerInformation.getCustomerPhone());
                 allContractDTO.setSecCustomerName(tempCustomerInformation.getCustomerName2());
                 allContractDTO.setSecCustomerPhonenum(tempCustomerInformation.getCustomerPhone2());
                 allContractDTO.setContractLifecycle(tempContract.getContractLifecycle());
                 allContractDTO.setEstimatedInstallDate(tempContract.getEstimatedInstallDate());
                 allContractDTO.setInstallDate(tempContract.getInstallDate());
                 allContractDTO.setInstallAddress(tempContract.getInstallAddress());
                 //合同利润率还没算
                 allContractDTO.setDeliveryMethod(tempContract.getDeliveryMethod());
                 allContractDTO.setContractProducts(salesmanMapper.selectContractProductByContractNo(tempContract.getContractNo()));
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
                         int index;
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
                 else {return data.get(0);}
                 return data.get(0);
             }
             else return "未查询到符合条件的数据";
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }

    @Override
    @Transactional
     public Object productInformation(){

        List<ProductInformation> data;
        try {
            data = salesmanMapper.selectProduct();
            if(data.size()!=0) return data;
              else return "未查询到符合条件的数据";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Object saveContract(MultipartFile file,SaveContractVO saveData) {
        //还需要合同编号重复性检查
        boolean saveDocumentFlag=false;
        try {
            Contract contractTemp = new Contract();
            contractTemp.setContractNo(saveData.getContractNumber());
            contractTemp.setSignDate(new Date(System.currentTimeMillis()));
            contractTemp.setInvoiceType(saveData.getInvoiceType());
            contractTemp.setMemberNo(saveData.getMemberNo());
            if(saveData.getEmployeeNo()!=null&&!saveData.getEmployeeNo().equals("")){
                List<User> users=salesmanMapper.selectEmployeeInformation(saveData.getEmployeeNo());
                if(users.size()!=0) {
                    contractTemp.setEmployeeNo(saveData.getEmployeeNo());
                }
                else {return "无法找到对应编号的员工";}
            }
            else return "员工编号缺失，创建合同失败";


            contractTemp.setContractLifecycle("合同已签订");
            contractTemp.setInstallAddress(saveData.getInstallAddress());
            contractTemp.setDeliveryMethod(saveData.getDeliveryMethod());

            //创建回款计划
            PayPlan payPlan = new PayPlan();
            payPlan.setPayCycle(saveData.getPayCycle());
            payPlan.setPayDate(saveData.getPayDate());
            payPlan.setContractNo(saveData.getContractNumber());
            payPlan.setPayStatus("未逾期");
            payPlan.setAmountPaid(new BigDecimal("0"));
            payPlan.setBalance(new BigDecimal("0"));
            payPlan.setAmountOnce(saveData.getAmountPlan());
            payPlan.setAmountPlan(saveData.getAmountPlan());
            payPlan.setLateTimes(0);
            payPlan.setAmountNotPaid(saveData.getUpAmount());
            for(ContractProduct pro:saveData.getContractProducts()){
                payPlan.setAmountNotPaid(payPlan.getAmountNotPaid().add(pro.getProductNumber().multiply(salesmanMapper.selectOneProduct(pro.getProductNo()).get(0).getPrice())));
                ContractProduct tempContractProduct=new ContractProduct();
                tempContractProduct.setContractNo(saveData.getContractNumber());
                tempContractProduct.setProductNo(pro.getProductNo());
                tempContractProduct.setProductNumber(pro.getProductNumber());
                tempContractProduct.setTag(0);
                salesmanMapper.insertContractProduct(tempContractProduct);
            }
            payPlan.setEmployeeNo(saveData.getEmployeeNo());
            payPlan.setPayCreateDate(new Date(System.currentTimeMillis()));

            //合同文件导入
            String uploadFileName = file.getOriginalFilename();

            File realPath = new File("ContractDocuments");
            if (!realPath.exists()) {
                if (!realPath.mkdir()) return "未能成功创建合同存储文件夹";
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

            //插入待审批信息
            ReviewRequest reviewRequest=new ReviewRequest();
            reviewRequest.setIndexNo(saveData.getContractNumber());
            reviewRequest.setRequestDate(new java.util.Date(System.currentTimeMillis()));
            reviewRequest.setReviewType("创建新合同");
            reviewRequest.setEmployeeNo(saveData.getEmployeeNo());
            reviewRequest.setRemark(saveData.getRequestComment());
            reviewRequestService.saveOrUpdate(reviewRequest);

            if (!saveOrUpdate(contractTemp)){
                if(contractTemp.getContractDocument()!=null&&!contractTemp.getContractDocument().equals(""))
                {File file1=new File(contractTemp.getContractDocument());
                    if(file1.exists()) file1.delete();}
                return "合同信息保存失败";
            }
            else if (!payPlanServiceImpl.savePayPlan(payPlan)) {
                if(contractTemp.getContractDocument()!=null&&!contractTemp.getContractDocument().equals(""))
                {File file1=new File(contractTemp.getContractDocument());
                    if(file1.exists()) file1.delete();}
                salesmanMapper.deleteByContractNumber(contractTemp.getContractNo());
                return "合同信息保存失败";
            }
            else if (!saveDocumentFlag) {
                if(contractTemp.getContractDocument()!=null&&!contractTemp.getContractDocument().equals(""))
                {File file1=new File(contractTemp.getContractDocument());
                if(file1.exists()) file1.delete();}
                return "合同信息保存成功，但合同文本保存失败";
            }
            else return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Object updateContract(MultipartFile file,UpdateContractVO saveData) {

        try {
            boolean saveDocumentFlag=false;

            Contract contractTemp = new Contract();
            contractTemp.setInvoiceType(saveData.getInvoiceType());
            contractTemp.setId(saveData.getId());
            Contract contractSelect= salesmanMapper.selectContractById(saveData.getId()).get(0);
            contractTemp.setInstallAddress(saveData.getInstallAddress());
            contractTemp.setDeliveryMethod(saveData.getDeliveryMethod());
            contractTemp.setEstimatedInstallDate(saveData.getEstimatedInstallDate());
            contractTemp.setInstallDate(saveData.getInstallDate());

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
            contractTemp.setContractNo(contractSelect.getContractNo());
            ContractHistory contractHistory = new ContractHistory();
            contractHistory.setModifyTime(new java.util.Date(System.currentTimeMillis()));
            contractHistory.setContractNo(contractSelect.getContractNo());
            contractHistory.setModifyBy(saveData.getModifyBy());


            //重新计算利润率(不知道成本，目前没法算)
//        if(contractSelect.getDeliveryFee()!=null&&contractSelect.getInstallFee()!=null){
//        }

            if (!saveDocumentFlag){
                return "合同文本及信息修改失败";
            }
            else if(!salesmanMapper.insertContractHistory(contractHistory)){
                return "修改记录插入失败，合同信息修改失败";
            }
            else if (!saveOrUpdate(contractTemp)) {
                return "合同文本修改成功，但合同信息保存失败";
            }
            else return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object contractHistory() {
        try {
            List<ContractHistory> data=salesmanMapper.selectContractHistory();
            if(data.size()!=0) return data;
            else return "未查询到符合条件的数据";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object deleteContract(Integer id,String employeeNo) {
        try {
            List<Contract> contracts=salesmanMapper.selectContractById(id);
            if(contracts.size()==0){return "不存在该合同";}
            Contract contractTemp=contracts.get(0);
            ContractHistory contractHistory = new ContractHistory();
            contractHistory.setModifyTime(new java.util.Date(System.currentTimeMillis()));
            contractHistory.setContractNo(contractTemp.getContractNo());
            contractHistory.setModifyBy(employeeNo);
            if(!salesmanMapper.insertContractHistory(contractHistory)){
                return "修改记录插入失败，合同信息删除失败";
            }
            if(!salesmanMapper.deletePayPlanByContractNumber(contractTemp.getContractNo())) return "回款计划删除失败";
            if(!removeById(id))return "合同信息删除失败";
            if(contractTemp.getContractDocument()!=null&&!contractTemp.getContractDocument().equals("")) {
                File file1=new File(contractTemp.getContractDocument());
                if(file1.exists()){
                    if(!file1.delete())return "合同文档删除失败";
                }
            }
            else {return "合同文档无记录";}
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}




