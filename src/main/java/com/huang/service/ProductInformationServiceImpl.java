package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.dto.ProductInformationDTO;
import com.huang.entity.ListMaterialRequirement;
import com.huang.entity.MaterialInformation;
import com.huang.entity.MaterialRequirement;
import com.huang.entity.ProductInformation;
import com.huang.mapper.ProductInformationMapper;
import com.huang.vo.SaveOrUpdateProductInformationVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInformationServiceImpl extends ServiceImpl<ProductInformationMapper, ProductInformation> implements ProductInformationService {
    @Autowired
    ProductInformationMapper productInformationMapper;
    @Autowired
    MaterialInformationServiceImpl materialInformationService;

    @Override
    @Transactional
    public SaveOrUpdateProductInformationVO getProductDetail(Integer id)
    {
        QueryWrapper<ProductInformation> queryWrapper =
            new QueryWrapper<ProductInformation>()
            .eq("id", id);
        ProductInformation product = queryWrapper.getEntity();
        List<MaterialRequirement> requirements = productInformationMapper.selectMaterialRequirementByProductId(id);
        SaveOrUpdateProductInformationVO result = new SaveOrUpdateProductInformationVO();
        result.setProductInformation(product);
        result.setMaterialRequirements(requirements);
        return result;
    }

    @Override
    @Transactional
    public boolean saveOrUpdateProductInformation(ProductInformation productInformation, List<MaterialRequirement> materialRequirements) {
        try {
            QueryWrapper<ProductInformation> queryWrapper=new QueryWrapper<ProductInformation>()
                    .eq("product_name",productInformation.getProductName())
                    .eq("product_no",productInformation.getProductNo())
                    .eq("tag",0);
            List<ProductInformation> productInformationList=list(queryWrapper);
            if(productInformationList.size()!=0){
                productInformationMapper.deleteMaterialRequirementByProductId(productInformationList.get(0).getId());
                removeById(productInformationList.get(0).getId());
            }

            if (!saveOrUpdate(productInformation)) {
                throw new Exception("未能成功保存产品信息");
            }
            Integer tempProductId = productInformationMapper.selectProductInformationByNameAndNo(productInformation.getProductName(), productInformation.getProductNo()).get(0).getId();

            productInformationMapper.deleteMaterialRequirementByProductId(tempProductId);
            for (MaterialRequirement ma : materialRequirements) {
                MaterialRequirement tempMa = new MaterialRequirement();
                tempMa.setProductId(tempProductId);
                tempMa.setMaterialNumber(ma.getMaterialNumber());
                tempMa.setMaterialId(ma.getMaterialId());
                productInformationMapper.insertMaterialRequirement(tempMa);
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public boolean deleteProductInformationByid(Integer id) {
        try {
            ProductInformation tempProductInformation=new ProductInformation();
            tempProductInformation.setId(id);
            tempProductInformation.setTag(0);
            if(!saveOrUpdate(tempProductInformation)){
                throw new Exception("删除产品信息失败");
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductInformationDTO> selectProductInformation() {
        List<ProductInformationDTO> productInformationDTOS = new ArrayList<>();
        QueryWrapper<ProductInformation> queryWrapper1=new QueryWrapper<ProductInformation>()
                .eq("tag",1);
        for (ProductInformation pro : list(queryWrapper1)) {
            ProductInformationDTO productInformationDTO = new ProductInformationDTO();
            productInformationDTO.setId(pro.getId());
            productInformationDTO.setProductNo(pro.getProductNo());
            productInformationDTO.setProductName(pro.getProductName());
            productInformationDTO.setLowestAddRate(pro.getLowestAddRate());
            BigDecimal tempPrice = new BigDecimal("0");
            tempPrice = tempPrice.add(pro.getCoConsumableMaterial().add(pro.getCompositeMaterial().add(pro.getMasonryLabor().add(pro.getOntologyLabor().add(pro.getRandomFiles())))));
            for (MaterialRequirement ma : productInformationMapper.selectMaterialRequirementByProductId(pro.getId())) {
                QueryWrapper<MaterialInformation> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id", ma.getMaterialId());
                tempPrice=tempPrice.add(ma.getMaterialNumber().multiply(materialInformationService.getOne(queryWrapper).getUnitPrice()));
            }
            productInformationDTO.setProductPrice(tempPrice);
            productInformationDTOS.add(productInformationDTO);
        }
        return productInformationDTOS;
    }

    @Override
    public ProductInformationDTO selectProductInformationByListId(Integer productId,Integer listId) {
        QueryWrapper<ProductInformation> queryWr=new QueryWrapper<>();
        queryWr.eq("id",productId);
        ProductInformation pro=getOne(queryWr);
        ProductInformationDTO productInformationDTO = new ProductInformationDTO();
        productInformationDTO.setLowestAddRate(pro.getLowestAddRate());
        BigDecimal tempPrice = new BigDecimal("0");
        tempPrice = tempPrice.add(pro.getCoConsumableMaterial().add(pro.getCompositeMaterial().add(pro.getMasonryLabor().add(pro.getOntologyLabor().add(pro.getRandomFiles())))));
        for (ListMaterialRequirement rere : productInformationMapper.selectListMaterialRequirementByListId(listId)){
            QueryWrapper<MaterialInformation> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",rere.getMaterialId());
            tempPrice=tempPrice.add(rere.getMaterialNumber().multiply(materialInformationService.getOne(queryWrapper).getUnitPrice()));
        }
        productInformationDTO.setProductPrice(tempPrice);
        return productInformationDTO;
    }
}