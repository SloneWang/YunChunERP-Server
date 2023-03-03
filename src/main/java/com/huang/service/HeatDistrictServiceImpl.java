package com.huang.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.entity.HeatDistrict;
import com.huang.mapper.HeatDistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeatDistrictServiceImpl extends ServiceImpl<HeatDistrictMapper, HeatDistrict> implements HeatDistrictService {
//    @Autowired
//    HeatDistrictService heatDistrictService;
    @Autowired
    HeatDistrictMapper heatDistrictMapper;

    @Override
    public List<String> selectQua(String disCode){
        List<String> heatDisSum=new ArrayList<>();

        for(HeatDistrict heatDi:heatDistrictMapper.selectQua(disCode.substring(0,2),disCode.substring(2,4),disCode.substring(4,6))){
            heatDisSum.add(heatDi.getResidentialQuarter());
        }
        return heatDisSum;
    }


    @Override
    public boolean saveUser(String heatDistrict){
        if(heatDistrict.length()>6){
            HeatDistrict heatDistrict1=new HeatDistrict();
            heatDistrict1.setProvince(heatDistrict.substring(0,2));
            heatDistrict1.setCity(heatDistrict.substring(2,4));
            heatDistrict1.setCounty(heatDistrict.substring(4,6));
            heatDistrict1.setResidentialQuarter(heatDistrict.substring(6,heatDistrict.length()));
            return saveOrUpdate(heatDistrict1);}
        else return false;
    }

    @Override
    public List<String> selectDisByStr(String disCode){
        List<HeatDistrict> heatDisSum=new ArrayList<>();
        List<String> disResult=new ArrayList<>();
        if(disCode.length()==6){
            heatDisSum.addAll(heatDistrictMapper.selectQua(disCode.substring(0,2),disCode.substring(2,4),disCode.substring(4,6)));
        }
        else if(disCode.length()==4){
            heatDisSum.addAll(heatDistrictMapper.selectDisByCi(disCode.substring(0,2),disCode.substring(2,4)));
        }
        else if(disCode.length()==2){
            heatDisSum.addAll(heatDistrictMapper.selectDisByPr(disCode));
        }
        if(heatDisSum.size()!=0){
            for(HeatDistrict heatDi:heatDisSum){
                String str=new String();
                str=heatDi.getProvince();
                str=str.concat(heatDi.getCity());
                str=str.concat(heatDi.getCounty());
                str=str.concat(heatDi.getResidentialQuarter());
                disResult.add(str);
            }
        }
        return disResult;
    }

}
