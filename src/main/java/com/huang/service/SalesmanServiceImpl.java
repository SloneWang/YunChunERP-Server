package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.dto.AllContractDTO;
import com.huang.dto.ContractSimpleDTO;
import com.huang.dto.ProductInformationDTO;
import com.huang.entity.*;
import com.huang.mapper.*;
import com.huang.vo.SaveContractVO;
import com.huang.vo.UpdateContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class SalesmanServiceImpl extends ServiceImpl<SalesmanMapper, Contract> implements SalesmanService{
    @Autowired
    SalesmanMapper salesmanMapper;
    @Autowired
    CustomerInformationServiceImpl customerInformationService;
    @Autowired
    CustomerInformationMapper customerInformationMapper;
    @Autowired
    ReviewRequestServiceImpl reviewRequestService;
    @Autowired
    ProductListServiceImpl productListService;
    @Autowired
    MaterialListServiceImpl materialListService;
    @Autowired
    ProductListMapper productListMapper;
    @Autowired
    MaterialListMapper materialListMapper;
    @Autowired
    MaterialInformationServiceImpl materialInformationService;
    @Autowired
    ProductInformationServiceImpl productInformationService;
    @Autowired
    ProductInformationMapper productInformationMapper;
    @Autowired
    ListMaterialRequirementServiceImpl listMaterialRequirementService;
    @Override
    @Transactional
    public List<ContractSimpleDTO> contractSimp(String employeeNo) {
        try {
            List<Contract> allContracts;
            if(salesmanMapper.selectEmployeeInformation(employeeNo).get(0).getPosition().equals("销售员")){
                allContracts=salesmanMapper.selectContractInformationByEmployeeNo(employeeNo);
            }
            else {allContracts=salesmanMapper.selectAllContract();}
            List<ContractSimpleDTO> contractSimpAll=new ArrayList<>();
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
                conTemp.setTag(contra.getTag());
                contractSimpAll.add(conTemp);
            }
            return contractSimpAll;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
     }

    @Override
    public boolean selectContractFile(HttpServletResponse response, Integer id) {
        //查询id对应合同信息
        try {
            QueryWrapper<Contract> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            Contract contractSelect= getOne(queryWrapper);
            String pathName=contractSelect.getContractDocument();
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
                    return true;
                } catch (IOException e) {
                    throw new Exception("文件读取失败");
                } finally {
                    if (os != null) {
                        os.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                }
            }
            else {throw new Exception("未找到对应合同文件");}
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

     @Override
     @Transactional
     public AllContractDTO contract(Integer id){
         try {
             AllContractDTO allContractDTO=new AllContractDTO();
             QueryWrapper<Contract> queryWrapper=new QueryWrapper<>();
             queryWrapper.eq("id",id);
             Contract tempContract = getOne(queryWrapper);
             if(tempContract!=null){
                 allContractDTO.setId(id);
                 allContractDTO.setContractNumber(tempContract.getContractNo());
                 allContractDTO.setMemberNo(tempContract.getMemberNo());
                 allContractDTO.setInvoiceType(tempContract.getInvoiceType());
                 allContractDTO.setCreateEmployeeNo(tempContract.getCreateEmployeeNo());
                 allContractDTO.setContractLifecycle(tempContract.getContractLifecycle());
                 allContractDTO.setInstallAddress(tempContract.getInstallAddress());
                 allContractDTO.setDeliveryMethod(tempContract.getDeliveryMethod());
                 allContractDTO.setEmployeeNo(tempContract.getEmployeeNo());
                 allContractDTO.setEmployeeName(salesmanMapper.selectEmployeeInformation(tempContract.getEmployeeNo()).get(0).getName());
                 allContractDTO.setBalance(tempContract.getBalance());
                 allContractDTO.setSignDate(tempContract.getSignDate());
                 allContractDTO.setSignFee(tempContract.getSignFee());
                 allContractDTO.setPickDate(tempContract.getPickDate());
                 allContractDTO.setPickFee(tempContract.getPickFee());
                 allContractDTO.setInstallDate(tempContract.getInstallDate());
                 allContractDTO.setInstallFee(tempContract.getInstallFee());
                 allContractDTO.setInstallCost(tempContract.getInstallCost());
                 allContractDTO.setWarrantyPeriod(tempContract.getWarrantyPeriod());
                 allContractDTO.setWarrantyFee(tempContract.getWarrantyFee());
                 allContractDTO.setContractTax(tempContract.getContractTax());
                 allContractDTO.setTag(tempContract.getTag());
                 allContractDTO.setReviewerNo(tempContract.getReviewerNo());
                 allContractDTO.setReviewerName(salesmanMapper.selectEmployeeInformation(tempContract.getReviewerNo()).get(0).getName());
                 CustomerInformation tempCustomerInformation=customerInformationMapper.selectOneCustomer(tempContract.getMemberNo()).get(0);
                 allContractDTO.setCustomerCompany(tempCustomerInformation.getCompany());
                 allContractDTO.setCustomerName(tempCustomerInformation.getCustomerName());
                 allContractDTO.setCustomerPhonenum(tempCustomerInformation.getCustomerPhone());
                 allContractDTO.setSecCustomerName(tempCustomerInformation.getCustomerName2());
                 allContractDTO.setSecCustomerPhonenum(tempCustomerInformation.getCustomerPhone2());
                 allContractDTO.setIndustry(tempCustomerInformation.getIndustry());
                 allContractDTO.setChannel(tempCustomerInformation.getChannel());
                 allContractDTO.setCustomerJob(tempCustomerInformation.getCustomerJob());
                 allContractDTO.setContractMaterial(materialListMapper.selectMaterialListByContractId(id));
                 allContractDTO.setContractProduct(productListMapper.selectProductListByContractId(id));

                 if(tempContract.getContractLifecycle().equals("合同已签订")||tempContract.getContractLifecycle().equals("生产完毕")||tempContract.getContractLifecycle().equals("提货完毕")){
                     BigDecimal tempTotalGrossProfit=new BigDecimal("0");
                     BigDecimal tempTotalNetProfit=new BigDecimal("0");
                     for(MaterialList ma:allContractDTO.getContractMaterial()){
                         MaterialInformation tempMaterialInformation=materialInformationService.selectMaterialInformationById(ma.getMaterialId());
                         tempTotalGrossProfit=tempTotalGrossProfit.add(ma.getMaterialNumber().multiply(ma.getMaterialPrice().divide(BigDecimal.valueOf(1).add(tempContract.getContractTax()),2, RoundingMode.HALF_UP).subtract(tempMaterialInformation.getUnitPrice())));
                         tempTotalNetProfit=tempTotalNetProfit.add(ma.getMaterialNumber().multiply(ma.getMaterialPrice().divide(BigDecimal.valueOf(1).add(tempContract.getContractTax()),2, RoundingMode.HALF_UP).subtract(tempMaterialInformation.getUnitPrice().add(tempMaterialInformation.getUnitPrice().multiply(tempMaterialInformation.getLowestAddRate())))));
                     }
                     for(ProductList pro:allContractDTO.getContractProduct()){
                         ProductInformationDTO productInformationDTO=productInformationService.selectProductInformationByListId(pro.getProductId(),pro.getId());
                         tempTotalGrossProfit=tempTotalGrossProfit.add(pro.getProductNumber().multiply(pro.getProductPrice().divide(BigDecimal.valueOf(1).add(tempContract.getContractTax()),2, RoundingMode.HALF_UP).subtract(productInformationDTO.getProductPrice())));
                         tempTotalNetProfit=tempTotalNetProfit.add(pro.getProductNumber().multiply(pro.getProductPrice().divide(BigDecimal.valueOf(1).add(tempContract.getContractTax()),2, RoundingMode.HALF_UP).subtract(productInformationDTO.getProductPrice().add(productInformationDTO.getProductPrice().multiply(productInformationDTO.getLowestAddRate())))));
                     }
                     allContractDTO.setTotalNetProfit(tempTotalNetProfit);
                     allContractDTO.setTotalGrossProfit(tempTotalGrossProfit);
                 }
                 else{
                     allContractDTO.setTotalNetProfit(tempContract.getTotalNetProfit());
                     allContractDTO.setTotalGrossProfit(tempContract.getTotalGrossProfit());
                 }

                 return allContractDTO;
             }
             else throw new Exception("未查询到符合条件的数据");
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }



    @Override
    @Transactional
    public boolean saveContract(SaveContractVO saveData) {
        //还需要合同编号重复性检查
        try {
            Contract contractTemp = new Contract();
            contractTemp.setMemberNo(saveData.getMemberNo());
            contractTemp.setInvoiceType(saveData.getInvoiceType());
            //检测数据库里是否存在该用户
            if(saveData.getEmployeeNo()!=null&&!saveData.getEmployeeNo().equals("")){
                List<User> users=salesmanMapper.selectEmployeeInformation(saveData.getEmployeeNo());
                if(users.size()!=0) {
                    contractTemp.setCreateEmployeeNo(saveData.getEmployeeNo());
                    contractTemp.setEmployeeNo(saveData.getEmployeeNo());
                }
                else {throw new Exception("无法找到对应编号的员工");}
            }
            else throw new Exception("员工编号缺失，创建合同失败");
            contractTemp.setContractLifecycle("订金待收取");
            contractTemp.setInstallAddress(saveData.getInstallAddress());
            contractTemp.setDeliveryMethod(saveData.getDeliveryMethod());
            contractTemp.setBalance(new BigDecimal("0"));
            contractTemp.setSignDate(new Date(System.currentTimeMillis()));
            contractTemp.setSignFee(saveData.getSignFee());
            contractTemp.setPickFee(saveData.getPickFee());
            contractTemp.setInstallFee(saveData.getInstallFee());
            contractTemp.setWarrantyFee(saveData.getWarrantyFee());
            contractTemp.setWarrantyPeriod(saveData.getWarrantyPeriod());
            contractTemp.setContractTax(saveData.getContractTax());
            contractTemp.setTag(2);
            contractTemp.setInstallAddRate(saveData.getInstallAddRate());
            if (!saveOrUpdate(contractTemp)){
                throw new Exception("保存合同信息失败");
            }
            //保存产品列表
            for(ProductList pro:saveData.getContractProduct()){
                ProductList tempPro=new ProductList();
                tempPro.setProductId(pro.getProductId());
                tempPro.setProductNumber(pro.getProductNumber());
                tempPro.setContractId(contractTemp.getId());
                tempPro.setProductPrice(pro.getProductPrice());
                tempPro.setProjectStart(new Date(System.currentTimeMillis()));
                if(!productListService.saveOrUpdate(tempPro)){
                    throw new Exception("产品列表保存失败");
                }

                //保存用料列表
                for(MaterialRequirement mma:productInformationMapper.selectMaterialRequirementByProductId(pro.getProductId())){
                    ListMaterialRequirement listMaterialRequirements=new ListMaterialRequirement();
                    listMaterialRequirements.setListId(tempPro.getId());
                    listMaterialRequirements.setTag(0);
                    listMaterialRequirements.setMaterialId(mma.getMaterialId());
                    listMaterialRequirements.setMaterialNumber(mma.getMaterialNumber());
                    if(!listMaterialRequirementService.saveOrUpdate(listMaterialRequirements)){
                        throw new Exception("保存用料表失败");
                    }
                }
//                boolean faker;
//                Integer tempId = null;
//                for(MaterialRequirement mma:productInformationMapper.selectMaterialRequirementByProductId(pro.getProductId())){
//                    faker=true;
//                    for(ListMaterialRequirement lis:listMaterialRequirements){
//                        if(mma.getMaterialId().equals(lis.getMaterialId())){
//                            faker=false;
//                            tempId=listMaterialRequirements.indexOf(lis);
//                            break;
//                        }
//                    }
//                    if(faker){
//                        ListMaterialRequirement listMaterialRequirementNew=new ListMaterialRequirement();
//                        listMaterialRequirementNew.setListId(tempPro.getId());
//                        listMaterialRequirementNew.setMaterialId(mma.getMaterialId());
//                        listMaterialRequirementNew.setMaterialNumber(mma.getMaterialNumber().multiply(pro.getProductNumber()));
//                        listMaterialRequirements.add(listMaterialRequirementNew);
//                    }else {
//                        listMaterialRequirements.get(tempId).setMaterialNumber(listMaterialRequirements.get(tempId).getMaterialNumber().add(mma.getMaterialNumber().multiply(pro.getProductNumber())));
//                    }
//                }
            }

            //保存材料列表
            for(MaterialList ma:saveData.getContractMaterial()){
                MaterialList tempMa=new MaterialList();
                tempMa.setContractId(contractTemp.getId());
                tempMa.setMaterialId(ma.getMaterialId());
                tempMa.setMaterialNumber(ma.getMaterialNumber());
                tempMa.setMaterialPrice(ma.getMaterialPrice());
                if(!materialListService.saveOrUpdate(tempMa)){
                    throw new Exception("材料列表保存失败");
                }
            }

            //插入待审批信息
            ReviewRequest reviewRequest =new ReviewRequest();
            reviewRequest.setReviewType("创建新合同");
            reviewRequest.setEmployeeNo(saveData.getEmployeeNo());
            reviewRequest.setRemark(saveData.getRequestComment());
            reviewRequest.setRequestDate(new java.util.Date(System.currentTimeMillis()));
            reviewRequest.setAdditionalInformation("总基价："+saveData.getTotalBasePrice().toString()+"\n总售价："+saveData.getTotalSalePrice().toString()+"\n订金："+saveData.getSignFee()+"\n提货收款："+saveData.getPickFee()+"\n安装收款："+saveData.getInstallFee()+"\n质保收款："+saveData.getWarrantyFee()+"\n质保期："+saveData.getWarrantyPeriod().toString());
            reviewRequest.setIndexNo(contractTemp.getId());
            if (!reviewRequestService.saveOrUpdate(reviewRequest)){
                throw new Exception("保存审批信息失败");
            }
            else return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public boolean saveOrUpdateContractFile(MultipartFile file, Integer id) throws IOException {
        try {
            Contract contractTemp = new Contract();
            contractTemp.setId(id);
            //创建文件夹路径
            File realPath = new File("ContractDocuments");
            if (!realPath.exists()) {
                if (!realPath.mkdir())  throw new Exception("未能成功创建文件夹");
            }
            //查询id对应合同文件信息
            QueryWrapper<Contract> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            String tempStr=getOne(queryWrapper).getContractDocument();
            //如果原来存在路径就删除对应路径下文件
            if(tempStr!=null&&!tempStr.equals("")){
                if(!new File(tempStr).delete()){
                    throw new Exception("未能成功成功删除原有文件");
                }
            }
            //合同文件导入
            String uploadFileName = file.getOriginalFilename();
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
                } catch (IOException e) {
                    throw new Exception("读写文件过程失败");
                }finally {
                    if (os != null) {
                        os.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                }
            }
            if(!saveOrUpdate(contractTemp)){
                throw new Exception("保存合同路径失败");
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional
    public boolean updateContract(UpdateContractVO saveData) {

        try {

            //如果tag不为1表示该合同不处于正常状态无法修改
            QueryWrapper<Contract> queryWrapper = new QueryWrapper<Contract>()
                    .eq("id",saveData.getId())
                    .eq("tag",1);
            List<Contract> contractSelects= list(queryWrapper);
            if(contractSelects.size()==0){
                throw new Exception("无法找到对应合同，或合同正在审核");
            }
            Contract contractSelect=contractSelects.get(0);

            //如果有另一条一样合同号的信息，表示该合同处于修改状态，必须审批结束才能修改
            QueryWrapper<Contract> queryWrapper1 = new QueryWrapper<Contract>()
                    .eq("contract_no",contractSelect.getContractNo())
                    .eq("tag",0);
            List<Contract> contractSelectTemp= list(queryWrapper1);
            if(contractSelectTemp.size()!=0){
                throw new Exception("审核中，无法执行修改操作");
            }

            //留存记录
            ContractHistory contractHistory = new ContractHistory();
            contractHistory.setModifyTime(new java.util.Date(System.currentTimeMillis()));
            contractHistory.setContractNo(contractSelect.getContractNo());
            contractHistory.setModifyBy(saveData.getModifyBy());
            contractHistory.setRemark(saveData.getUpdateReason());

            if(!salesmanMapper.insertContractHistory(contractHistory)){
                throw new Exception("修改记录插入失败，合同信息修改失败");
            }

            Contract contractTemp = new Contract();
            contractTemp.setContractNo(contractSelect.getContractNo());
            contractTemp.setInvoiceType(saveData.getInvoiceType());
            contractTemp.setInstallAddress(saveData.getInstallAddress());
            contractTemp.setDeliveryMethod(saveData.getDeliveryMethod());
            contractTemp.setEmployeeNo(saveData.getEmployeeNo());
            contractTemp.setCreateEmployeeNo(contractHistory.getId().toString());
            if(!contractSelect.getContractLifecycle().equals("安装完毕")&&!contractSelect.getContractLifecycle().equals("质保结束")&&!contractSelect.getContractLifecycle().equals("已结束")){
                contractTemp.setWarrantyPeriod(saveData.getWarrantyPeriod());
            }
            if (!saveOrUpdate(contractTemp)) {
                throw new Exception("合同信息保存失败");
            }

            //插入待审批信息
            ReviewRequest reviewRequest =new ReviewRequest();
            reviewRequest.setReviewType("修改合同信息");
            reviewRequest.setEmployeeNo(saveData.getModifyBy());
            reviewRequest.setRemark(saveData.getUpdateReason());
            reviewRequest.setRequestDate(new java.util.Date(System.currentTimeMillis()));
            reviewRequest.setAdditionalInformation(saveData.toString());
            reviewRequest.setIndexNo(saveData.getId());
            if (!reviewRequestService.saveOrUpdate(reviewRequest)){
                throw new Exception("保存审批信息失败");
            }



            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ContractHistory> contractHistory() {
        try {
            List<ContractHistory> data=salesmanMapper.selectContractHistory();
            if(data.size()!=0) return data;
            else throw new Exception("未查询到符合条件的数据");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteContract(Integer id,String employeeNo,String reviewNo) {
        try {
            List<Contract> contracts=salesmanMapper.selectContractById(id);
            if(contracts.size()==0){
                throw new Exception("合同不存在");
            }
            Contract contractTemp=contracts.get(0);

            //删除合同附件
            if(contractTemp.getContractDocument()!=null&&!contractTemp.getContractDocument().equals("")) {
                File file1=new File(contractTemp.getContractDocument());
                if(file1.exists()){
                    if(!file1.delete()){
                        throw new Exception("合同文档删除失败");
                    }
                }
            }

            //删除用料表
            for(ProductList pp:productListMapper.selectProductListByContractId(id)){
                for(ListMaterialRequirement listMa:productInformationMapper.selectListMaterialRequirementByListId(pp.getId())){
                    if(!listMaterialRequirementService.removeById(listMa.getId())){
                        throw new Exception("用料列表删除失败");
                    }
                }
            }


            //删除相关材料列表和产品列表
            if(!productListMapper.deleteProductListByContractId(id)){
                throw new Exception("产品列表删除失败");
            }
            if(!materialListMapper.deleteMaterialListByContractId(id)){
                throw new Exception("材料列表删除失败");
            }

            //删除相关修改信息
            QueryWrapper<Contract> queryWrapper=new QueryWrapper<Contract>()
                    .eq("tag",0)
                    .eq("contract_no",contractTemp.getContractNo());
            remove(queryWrapper);

            //删除相关待审核信息
            QueryWrapper<ReviewRequest> queryWrapper1=new QueryWrapper<ReviewRequest>()
                    .eq("index_no",id)
                    .eq("review_result","待审核");
            reviewRequestService.remove(queryWrapper1);

            //删除合同信息
            if(!removeById(id)){
                throw new Exception("合同信息删除失败");
            }

            //插入删除记录
            ContractHistory contractHistory = new ContractHistory();
            contractHistory.setModifyTime(new java.util.Date(System.currentTimeMillis()));
            contractHistory.setContractNo(contractTemp.getContractNo());
            contractHistory.setModifyBy(employeeNo);
            if(reviewNo!=null&&!reviewNo.equals("")){
                contractHistory.setReviewBy(reviewNo);
            }
            else contractHistory.setReviewBy(employeeNo);
            contractHistory.setRemark("合同删除操作");

            if(!salesmanMapper.insertContractHistory(contractHistory)){
                throw new Exception( "修改记录插入失败，合同信息删除失败");
            }


            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean pickComplete(Integer id) {
        try {
            Contract tempContract = new Contract();
            QueryWrapper<Contract> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            Contract contractSelect= getOne(queryWrapper);
            if(contractSelect.getTag()==0){
                throw new Exception("审核中，无法执行修改操作");
            }
            if(contractSelect.getContractLifecycle().equals("生产完毕")){
                tempContract.setId(id);
                tempContract.setContractLifecycle("提货费待收取");
                tempContract.setPickDate(new Date(System.currentTimeMillis()));
                if(!saveOrUpdate(tempContract)){
                    throw new Exception("合同更新失败");
                }
                return true;
            }
            else throw new Exception("无法跳过生产环节，请先完成生产项目");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //加入成本，加入时间，更新利润率，自动更新合同标签
    @Override
    public boolean installComplete(Integer id,BigDecimal installCost) {
        try {
            Contract tempContract = new Contract();
            QueryWrapper<Contract> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            Contract contractSelect= getOne(queryWrapper);
            if(contractSelect.getTag()==0){
                throw new Exception("审核中，无法执行修改操作");
            }

            //确认最终利润，之后不允许退货
            if(contractSelect.getContractLifecycle().equals("提货完毕")){
                tempContract.setId(id);
                tempContract.setContractLifecycle("安装费待收取");
                tempContract.setInstallCost(installCost);
                tempContract.setInstallDate(new Date(System.currentTimeMillis()));
                BigDecimal tempTotalGrossProfit=new BigDecimal("0");
                BigDecimal tempTotalNetProfit=new BigDecimal("0");
                for(MaterialList ma:materialListMapper.selectMaterialListByContractId(id)){
                    MaterialInformation tempMaterialInformation=materialInformationService.selectMaterialInformationById(ma.getMaterialId());
                    tempTotalGrossProfit=tempTotalGrossProfit.add(ma.getMaterialNumber().multiply(ma.getMaterialPrice().divide(BigDecimal.valueOf(1).add(contractSelect.getContractTax()),2, RoundingMode.HALF_UP).subtract(tempMaterialInformation.getUnitPrice())));
                    tempTotalNetProfit=tempTotalNetProfit.add(ma.getMaterialNumber().multiply(ma.getMaterialPrice().divide(BigDecimal.valueOf(1).add(contractSelect.getContractTax()),2, RoundingMode.HALF_UP).subtract(tempMaterialInformation.getUnitPrice().add(tempMaterialInformation.getUnitPrice().multiply(tempMaterialInformation.getLowestAddRate())))));
                }
                for(ProductList pro:productListMapper.selectProductListByContractId(id)){
                    ProductInformationDTO productInformationDTO=productInformationService.selectProductInformationByListId(pro.getProductId(),pro.getId());
                    tempTotalGrossProfit=tempTotalGrossProfit.add(pro.getProductNumber().multiply(pro.getProductPrice().divide(BigDecimal.valueOf(1).add(contractSelect.getContractTax()),2, RoundingMode.HALF_UP).subtract(productInformationDTO.getProductPrice())));
                    tempTotalNetProfit=tempTotalNetProfit.add(pro.getProductNumber().multiply(pro.getProductPrice().divide(BigDecimal.valueOf(1).add(contractSelect.getContractTax()),2, RoundingMode.HALF_UP).subtract(productInformationDTO.getProductPrice().add(productInformationDTO.getProductPrice().multiply(productInformationDTO.getLowestAddRate())))));
                }
                tempContract.setTotalGrossProfit(tempTotalGrossProfit);
                tempContract.setTotalNetProfit(tempTotalNetProfit);
                tempContract.setTotalNetProfit(contractSelect.getTotalNetProfit().add(contractSelect.getInstallFee().divide(BigDecimal.valueOf(1).add(contractSelect.getContractTax()),2, RoundingMode.HALF_UP).subtract(installCost.add(installCost.multiply(contractSelect.getInstallAddRate())))));
                tempContract.setTotalGrossProfit(contractSelect.getTotalGrossProfit().add(contractSelect.getInstallFee().divide(BigDecimal.valueOf(1).add(contractSelect.getContractTax()),2, RoundingMode.HALF_UP).subtract(installCost)));
                if(!saveOrUpdate(tempContract)){
                    throw new Exception("合同更新失败");
                }
                return true;
            }
            else throw new Exception("无法跳过提货环节，请先完成提货");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean warrantyComplete(Integer id) {
        try {
            Contract tempContract = new Contract();
            QueryWrapper<Contract> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            Contract contractSelect= getOne(queryWrapper);
            if(contractSelect.getTag()==0){
                throw new Exception("审核中，无法执行修改操作");
            }
            if(contractSelect.getContractLifecycle().equals("质保结束")){
                tempContract.setId(id);
                tempContract.setContractLifecycle("质保费待收取");
                if(!saveOrUpdate(tempContract)){
                    throw new Exception("合同更新失败");
                }
                return true;
            }
            else throw new Exception("无法跳过提货环节，请先完成提货");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Contract selectUpdateContractInformation(Integer id) {
        try {
            QueryWrapper<Contract> queryWrapper=new QueryWrapper<Contract>()
                    .eq("id",id);
            Contract contractInformation =getOne(queryWrapper);
            QueryWrapper<Contract> queryWrapper1=new QueryWrapper<Contract>()
                    .eq("contract_no",contractInformation.getContractNo())
                    .eq("tag",0);
            return getOne(queryWrapper1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional
    public boolean deleteContractLimit(Integer id, String employeeNo,String requestComment) {
        //插入待审核信息
        try {
            ReviewRequest reviewRequest =new ReviewRequest();
            reviewRequest.setReviewType("删除合同");
            reviewRequest.setEmployeeNo(employeeNo);
            reviewRequest.setRemark(requestComment);
            reviewRequest.setRequestDate(new java.util.Date(System.currentTimeMillis()));
            reviewRequest.setIndexNo(id);
            if (!reviewRequestService.saveOrUpdate(reviewRequest)){
                throw new Exception("保存审批信息失败");
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    @Scheduled(cron = "0 0 3 * * ?")
    @Transactional
    public void autoUpdate() {
        try {
            long oldDate = System.currentTimeMillis();
            for(Contract con:list()){
                if(con.getContractLifecycle().equals("安装完毕")){
                    Calendar calendar=Calendar.getInstance();
                    calendar.setTime(con.getInstallDate());
                    calendar.add(Calendar.MONTH,con.getWarrantyPeriod());
                    calendar.add(Calendar.DATE,1);
                    if(calendar.getTime().getTime()>oldDate){
                        Contract tempContract=new Contract();
                        tempContract.setId(con.getId());
                        tempContract.setContractLifecycle("质保结束");
                        if(!saveOrUpdate(tempContract)){
                            throw new Exception("系统更新失败");
                        }
                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean cancelContract(Integer id,String employeeNo,String reviewNo) {
        try {
            List<Contract> contracts=salesmanMapper.selectContractById(id);
            if(contracts.size()==0){
                throw new Exception("合同不存在");
            }
            Contract contractTemp=contracts.get(0);

            //删除用料表
            for(ProductList pp:productListMapper.selectProductListByContractId(id)){
                for(ListMaterialRequirement listMa:productInformationMapper.selectListMaterialRequirementByListId(pp.getId())){
                    listMa.setTag(4);
                    if(!listMaterialRequirementService.saveOrUpdate(listMa)){
                        throw new Exception("用料列表更新失败");
                    }
                }
            }

            //删除相关材料列表和产品列表
            for(ProductList pp:productListMapper.selectProductListByContractId(id)){
                pp.setTag(4);
                if(!productListService.saveOrUpdate(pp)){
                    throw new Exception("产品列表更新失败");
                }
            }

            for(MaterialList mm:materialListMapper.selectMaterialListByContractId(id)){
                mm.setTag(4);
                if(!materialListService.saveOrUpdate(mm)){
                    throw new Exception("材料列表更新失败");
                }
            }

            //删除相关修改信息
            QueryWrapper<Contract> queryWrapper=new QueryWrapper<Contract>()
                    .eq("tag",0)
                    .eq("contract_no",contractTemp.getContractNo());
            remove(queryWrapper);

            //删除相关待审核信息
            QueryWrapper<ReviewRequest> queryWrapper1=new QueryWrapper<ReviewRequest>()
                    .eq("index_no",id)
                    .eq("review_result","待审核");
            reviewRequestService.remove(queryWrapper1);

            //删除合同信息
            contractTemp.setTag(4);
            if(!saveOrUpdate(contractTemp)){
                throw new Exception("合同信息更新失败");
            }

            //插入删除记录
            ContractHistory contractHistory = new ContractHistory();
            contractHistory.setModifyTime(new java.util.Date(System.currentTimeMillis()));
            contractHistory.setContractNo(contractTemp.getContractNo());
            contractHistory.setModifyBy(employeeNo);
            if(reviewNo!=null&&!reviewNo.equals("")){
                contractHistory.setReviewBy(reviewNo);
            }
            else contractHistory.setReviewBy(employeeNo);
            contractHistory.setRemark("合同作废操作");

            if(!salesmanMapper.insertContractHistory(contractHistory)){
                throw new Exception( "修改记录插入失败，合同信息作废失败");
            }


            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cancelContractLimit(Integer id, String employeeNo, String requestComment) {
        //插入待审核信息
        try {
            ReviewRequest reviewRequest =new ReviewRequest();
            reviewRequest.setReviewType("作废合同");
            reviewRequest.setEmployeeNo(employeeNo);
            reviewRequest.setRemark(requestComment);
            reviewRequest.setRequestDate(new java.util.Date(System.currentTimeMillis()));
            reviewRequest.setIndexNo(id);
            if (!reviewRequestService.saveOrUpdate(reviewRequest)){
                throw new Exception("保存审批信息失败");
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public boolean payReturn(Integer id) {
        try {
            PayReturn payReturn=salesmanMapper.selectPayReturnById(id).get(0);
            if(!payReturn.getStatus().equals("待收取")){
                throw new Exception("非待收取状态");
            }
            if(!salesmanMapper.updatePayReturn("已收取",id)){
                throw new Exception("更新回款计划失败");
            }
            Contract tempContract = getById(payReturn.getContractId());
            Contract updateContract=new Contract();
            updateContract.setId(payReturn.getContractId());
            switch (tempContract.getContractLifecycle()) {
                case "订金待收取":
                    updateContract.setContractLifecycle("合同已签订");
                    break;
                case "提货费待收取":
                    updateContract.setContractLifecycle("提货完毕");
                    break;
                case "安装费待收取":
                    updateContract.setContractLifecycle("安装完毕");
                    break;
                case "质保费待收取":
                    updateContract.setContractLifecycle("已结束");
                    break;
                default:
                    throw new Exception("合同生命周期异常");
            }
            if(!saveOrUpdate(updateContract)){
                throw new Exception("更新合同信息失败");
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PayReturn> selectPayReturn() {
        return salesmanMapper.selectPayReturn();
    }
}




