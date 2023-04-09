package com.huang.dto;
import com.huang.entity.MaterialList;
import com.huang.entity.ProductList;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
public class AllContractDTO {
    //主键
    private Integer id;
    //合同编号
    private String contractNumber;
    //会员编号
    private String memberNo;
    //发票类型
    private String invoiceType;
    //创建员工编号
    private String createEmployeeNo;
    //合同生命周期
    private String  contractLifecycle;
    //安装地址
    private String installAddress;
    //送货方式
    private String deliveryMethod;
    //负责员工编号
    private String employeeNo;
    //创建员工姓名
    private String  employeeName;
    //欠款
    private BigDecimal balance;
    //签约日期
    private Date signDate;
    //订金
    private BigDecimal signFee;
    //提货日期
    private Date pickDate;
    //提货收款
    private BigDecimal pickFee;
    //实际安装日期
    private Date installDate;
    //安装成本
    private BigDecimal installCost;
    //安装收款
    private BigDecimal installFee;
    //质保时间（月）
    private Integer warrantyPeriod;
    //质保收款
    private BigDecimal warrantyFee;
    //合同税率
    private BigDecimal contractTax;
    //审核标志
    private Integer tag;
    //审核人编号
    private String  reviewerNo;
    //审核人姓名
    private String  reviewerName;


    //客户公司
    private String  customerCompany;
    //客户姓名
    private String  customerName;
    //客户电话
    private String  customerPhonenum;
    //第二联系人姓名
    private String  secCustomerName;
    //第二联系人电话
    private String  secCustomerPhonenum;
    //客户所属行业
    private String industry;
    //客户职务
    private String customerJob;
    //渠道来源
    private String channel;

    //合同总净利润
    private BigDecimal totalNetProfit;
    //合同总毛利润
    private BigDecimal totalGrossProfit;

    //订购材料列表
    private List<MaterialList> contractMaterial;
    //订购产品列表
    private List<ProductList> contractProduct;
}