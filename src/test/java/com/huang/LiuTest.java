package com.huang;

import com.huang.entity.*;
import com.huang.service.*;
import com.huang.vo.SaveContractVO;
import com.huang.vo.UpdateContractVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LiuTest {
    @Autowired
    HeatAuthServiceImpl heatAuthService;

    @Autowired
    HeatDistrictServiceImpl heatDistrictService;
    @Autowired
    SalesmanServiceImpl salesmanService;
    @Autowired
    ReviewRequestServiceImpl reviewRequestService;
    @Autowired
    CustomerInformationServiceImpl customerInformationService;
    @Autowired
    MaterialInformationServiceImpl materialInformationService;
    @Autowired
    ProductInformationServiceImpl productInformationService;
    @Autowired
    MaterialListServiceImpl materialListService;
    @Autowired
    ProductListServiceImpl productListService;
    @Test
    public void tete() {

// 测试district插入
//        HeatDistrict heatDistrict=new HeatDistrict();
//        heatDistrict.setCity("08");
//        heatDistrict.setCounty("27");
//        heatDistrict.setProvince("13");
//        heatDistrict.
//        setResidentialQuarter("兆丰润景小区");
//        heatDistrictService.saveUser(heatDistrict);

// 测试auth插入
//        HeatAuth heatAuth=new HeatAuth();
//        heatAuth.setUsername("user2");
//        heatAuth.setAdminAuthority("130827,11,1002");
//        heatAuth.setCustomAuthority("130827兆丰润景小区,110374幸福里小区");
//        heatAuthService.saveUser("user",heatAuth);

// 测试auth查询
        //List<HeatAuth> heatAuth=new ArrayList<>();
        //heatAuth=heatAuthService.selectHeatUser("user");
        //ystem.out.println(heatAuth);

// 测试district查询
//        List<String> heatDistr=new ArrayList<>();
//        heatDistr= heatDistrictService.selectQua("13","08","27");
//        System.out.println(heatDistr);

// 测试auth删除
//        HeatAuth heatAuth=new HeatAuth();
//        heatAuth.setUsername("user1");
//        heatAuth.setAdminAuthority("130827,11");
//        heatAuth.setCustomAuthority("130827兆丰润景小区,110374幸福里小区");
//        heatAuthService.deleteHeatAuth(heatAuth);

          // 测试创建合同
//        SaveContractVO saveContractVO=new SaveContractVO();
//        saveContractVO.setMemberNo("00000001");
//        saveContractVO.setRequestComment("客户非常着急，请尽快批复");
//        saveContractVO.setInvoiceType("普通");
//        saveContractVO.setEmployeeNo("wizard");
//        saveContractVO.setInstallAddress("吉林省风车市演出小区4号楼1单元1楼");
//        saveContractVO.setDeliveryMethod("自提");
//        saveContractVO.setSignFee(new BigDecimal("5000"));
//        saveContractVO.setPickFee(new BigDecimal("15000"));
//        saveContractVO.setInstallFee(new BigDecimal("1000"));
//        saveContractVO.setWarrantyFee(new BigDecimal("10000"));
//        saveContractVO.setWarrantyPeriod(6);
//        saveContractVO.setContractTax(new BigDecimal("0.08"));
//        saveContractVO.setTotalBasePrice(new BigDecimal("27260.16"));
//        saveContractVO.setTotalSalePrice(new BigDecimal("31000"));
//        List<MaterialList> materialLists=new ArrayList<>();
//        MaterialList materialList1=new MaterialList();
//        materialList1.setMaterialId(5);
//        materialList1.setMaterialNumber(new BigDecimal("2"));
//        materialList1.setMaterialPrice(new BigDecimal("24"));
//        materialLists.add(materialList1);
//        List<ProductList> productLists=new ArrayList<>();
//        ProductList productList1=new ProductList();
//        productList1.setProductId(6);
//        productList1.setProductNumber(new BigDecimal("1"));
//        productList1.setProductPrice(new BigDecimal("29952"));
//        productLists.add(productList1);
//        saveContractVO.setContractMaterial(materialLists);
//        saveContractVO.setContractProduct(productLists);
//        salesmanService.saveContract(saveContractVO);

//           修改合同
//        UpdateContractVO updateContractVO=new UpdateContractVO();
//        updateContractVO.setId(16);
//        updateContractVO.setModifyBy("wizard");
//        updateContractVO.setInvoiceType("普通");
//        updateContractVO.setUpdateReason("发票类型不小心填错了");
//        System.out.println(salesmanService.updateContract(updateContractVO));

        //测试简单查询
//        System.out.println(salesmanService.contractSimp("15052382109"));
//        System.out.println(salesmanService.contract(16));
//        System.out.println(salesmanService.productInformation());
//        System.out.println(salesmanService.contractHistory());
//        System.out.println(materialListService.selectMaterialList());
//        System.out.println(productListService.selectProductList());
        //测试简单操作
//        productListService.productWrap(2);
//        productListService.productComplete(2);
//        salesmanService.pickComplete(16);
//        salesmanService.installComplete(16,new BigDecimal("500"));
//        salesmanService.warrantyComplete(16);
//        salesmanService.deleteContract(16,"wizard");

        //测试删除合同
//        System.out.println(salesmanService.deleteContract(10,"55555"));
        //测试审核
//        System.out.println(reviewRequestService.reviewInformation("abcd"));
//        reviewRequestService.reviewResult("wizard",6,true,null);

        //测试会员信息
//        CustomerInformation saveContractVO=new CustomerInformation();
//        saveContractVO.setIndustry("货物运输");
//        saveContractVO.setCustomerJob("经理");
//        saveContractVO.setChannel("qqqq公众号");
//        saveContractVO.setCompany("北京钢铁厂");
//        saveContractVO.setCustomerName("网三");
//        saveContractVO.setCustomerPhone("13344426066");
//        saveContractVO.setCustomerName2("王五");
//        saveContractVO.setCustomerPhone2("18862673348");
//        saveContractVO.setMemberNo("00000002");
//        customerInformationService.saveOrUpdateCustomer(saveContractVO);


//        customerInformationService.deleteCustomerByid(9);
//        System.out.println(customerInformationService.selectAllCustomer());

          //材料信息测试
//        MaterialInformation materialInformation=new MaterialInformation();
//        materialInformation.setMaterialNo("DN20 PN16");
//        materialInformation.setMaterialName("国标法兰");
//        materialInformation.setSupplier("瓦房店永宁机械厂");
//        materialInformation.setMaterialType("零部件");
//        materialInformation.setUnit("片");
//        materialInformation.setUnitPrice(new BigDecimal("17"));
//        materialInformation.setLowestAddRate(new BigDecimal("0.30"));
//        materialInformationService.saveOrUpdateMaterialInformation(materialInformation);

//        materialInformationService.deleteMaterialInformationByid(4);
//        System.out.println(materialInformationService.selectMaterialInformation());

        //产品信息测试
//        ProductInformation productInformation=new ProductInformation();
//        productInformation.setId(7);
//        productInformation.setProductNo("DZC0.35-0.7/95/70-S");
//        productInformation.setProductName("承压热水锅炉");
//        productInformation.setCompositeMaterial(new BigDecimal("101"));
//        productInformation.setCoConsumableMaterial(new BigDecimal("400"));
//        productInformation.setLowestAddRate(new BigDecimal("0.28"));
//        productInformation.setMasonryLabor(new BigDecimal("1500"));
//        productInformation.setOntologyLabor(new BigDecimal("2225"));
//        List<MaterialRequirement> materialRequirements=new ArrayList<>();
//        MaterialRequirement materialRequirement1=new MaterialRequirement();
//        materialRequirement1.setMaterialId(1);
//        materialRequirement1.setMaterialNumber(new BigDecimal("3"));
//        materialRequirements.add(materialRequirement1);
//        MaterialRequirement materialRequirement2=new MaterialRequirement();
//        materialRequirement2.setMaterialId(2);
//        materialRequirement2.setMaterialNumber(new BigDecimal("10"));
//        materialRequirements.add(materialRequirement2);
//        MaterialRequirement materialRequirement3=new MaterialRequirement();
//        materialRequirement3.setMaterialId(3);
//        materialRequirement3.setMaterialNumber(new BigDecimal("1"));
//        materialRequirements.add(materialRequirement3);
//        productInformationService.saveOrUpdateProductInformation(productInformation,materialRequirements);

//        productInformationService.deleteProductInformationByid(7);
//        System.out.println(productInformationService.selectProductInformation());



    }


}



