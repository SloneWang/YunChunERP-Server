package com.huang.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.dto.HeatAuthDTO;
import com.huang.entity.HeatAuth;
import com.huang.entity.HeatSys;
import com.huang.entity.User;
import com.huang.mapper.HeatAuthMapper;
import com.huang.mapper.HeatUserMapper;
import com.huang.tools.HeatAuthCheck;
import com.huang.vo.HeatSaveAuthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class HeatAuthServiceImpl extends ServiceImpl<HeatAuthMapper, HeatAuth> implements HeatAuthService {
    @Autowired
    HeatAuthMapper heatAuthMapper;
    @Autowired
    HeatUserMapper heatUserMapper;
    HeatAuthCheck heatAuthCheck= new HeatAuthCheck();
    @Override
    public List<HeatAuthDTO> selectHeatUser(String username){
        List<String> authMap=new ArrayList<>();
        List<HeatAuthDTO> userList=new ArrayList<>();
        List<HeatAuth> heatAdminAuth=heatAuthMapper.selectUserAuth(username);
        for(HeatAuth heatAu:heatAdminAuth){
            for(HeatAuth heatAut:heatAuthMapper.selectAdminByAuth(heatAu.getAdminAuthority())){
                if(!authMap.contains(heatAut.getUsername()))
                {
                    authMap.add(heatAut.getUsername());
                }
            }
            for(HeatAuth heatAut:heatAuthMapper.selectDisByAuth(heatAu.getAdminAuthority())){
                if(!authMap.contains(heatAut.getUsername()))
                {
                    authMap.add(heatAut.getUsername());
                }
            }
        }
        for(String au:authMap){
            User userTemp =heatAuthMapper.selectSysUser(au).get(0);
            HeatAuthDTO heatAuthDTO=new HeatAuthDTO();
            heatAuthDTO.setUsername(userTemp.getUsername());
            heatAuthDTO.setName(userTemp.getName());
            heatAuthDTO.setPhone(userTemp.getPhone());
            heatAuthDTO.setPosition(userTemp.getPosition());
            userList.add(heatAuthDTO);
        }
//
//        }
        return userList;
    }


    @Override
    public boolean deleteHeatAuth(int id){
        return removeById(id);
    }

    @Override
    public List<HeatAuth> selectHeatAdmin(String username, String name) {
        List<HeatAuth> sumAdminAuth=new ArrayList<>();
        if(heatAuthMapper.selectUserAuth(username)!=null&&heatAuthMapper.selectUserAuth(username).size()!=0) {
            for (HeatAuth heatAu : heatAuthMapper.selectUserAuth(username)) {
                if (heatAuthMapper.selectAdminByAuth(heatAu.getAdminAuthority()) != null && heatAuthMapper.selectAdminByAuth(heatAu.getAdminAuthority()).size() != 0) {
                    for (HeatAuth heatAut : heatAuthMapper.selectAdminByAuth(heatAu.getAdminAuthority())) {
                        if (heatAut.getUsername().equals(name)) sumAdminAuth.add(heatAut);
                    }
                }
            }
        }
        return sumAdminAuth;
    }

    @Override
    public List<HeatAuth> selectHeatDis(String username, String name) {
        List<HeatAuth> sumAdminDis=new ArrayList<>();
        if(heatAuthMapper.selectUserAuth(username)!=null&&heatAuthMapper.selectUserAuth(username).size()!=0) {
            for (HeatAuth heatAu : heatAuthMapper.selectUserAuth(username)) {
                if (heatAuthMapper.selectDisByAuth(heatAu.getAdminAuthority()) != null && heatAuthMapper.selectDisByAuth(heatAu.getAdminAuthority()).size() != 0) {
                    for (HeatAuth heatAut : heatAuthMapper.selectDisByAuth(heatAu.getAdminAuthority())) {
                        if (heatAut.getUsername().equals(name)) sumAdminDis.add(heatAut);
                    }
                }
            }
        }
        return sumAdminDis;
    }


    @Override
    public boolean saveUserAdmin(HeatSaveAuthVO heatSaveAuthVO){
        boolean flag=false;
        List<HeatAuth> sumAuth=heatSaveAuthVO.getHeatAuth();
        List<HeatAuth> adminAuth=new ArrayList<>();
        List<HeatAuth> disAuth=new ArrayList<>();
        for(HeatAuth sumAu:sumAuth){
            if(sumAu.getAdminAuthority()!=null){
                adminAuth.add(sumAu);
            } else if (sumAu.getCustomAuthority()!=null) {
                disAuth.add(sumAu);
            }
        }
        List<HeatAuth> heatAdminAuth=heatAuthMapper.selectUserAuth(heatSaveAuthVO.getUsername());
        if(adminAuth.size()!=0){
            //检查用户发送的权限是否有重复，并去掉重复的权限
            for(int i=0;i<adminAuth.size();i++){
                for(int j=i+1;j<adminAuth.size();j++){
                    if(adminAuth.get(i).getAdminAuthority().startsWith(adminAuth.get(j).getAdminAuthority()))
                    {adminAuth.remove(i);i--;}
                }
            }

            //检查是否与数据库中有重复，有重复则去掉
            Iterator<HeatAuth> iteratorAu=adminAuth.iterator();
            while (iteratorAu.hasNext()){
                HeatAuth heatAu=iteratorAu.next();
                if(heatAuthMapper.checkByAuth(heatAu.getUsername(),heatAu.getAdminAuthority()).size()!=0)
                {iteratorAu.remove();}
            }

            //检查用户权限是否足以执行操作,如果权限足够直接保存
            for(HeatAuth heatAu:adminAuth){
                if(heatAuthCheck.CheckAuth(heatAdminAuth,heatAu.getAdminAuthority()))
                {saveOrUpdate(heatAu);flag=true;}
            }
        }
        if(disAuth.size()!=0){
            //检查用户发送的权限是否有重复，并去掉重复的权限
            for(int i=0;i<disAuth.size();i++){
                for(int j=i+1;j<disAuth.size();j++){
                    if(disAuth.get(i).getCustomAuthority().startsWith(disAuth.get(j).getCustomAuthority()))
                    {disAuth.remove(i);i--;break;}
                }
            }

            //检查是否与数据库中有重复，有重复则去掉
            Iterator<HeatAuth> iteratorDi=disAuth.iterator();
            while (iteratorDi.hasNext()){
                HeatAuth heatAu=iteratorDi.next();
                if(heatAuthMapper.checkByDis(heatAu.getUsername(),heatAu.getCustomAuthority()).size()!=0)
                {iteratorDi.remove();}
            }

            //检查用户权限是否足以执行操作,如果权限足够直接保存
            for(HeatAuth heatAu:disAuth){
                if(heatAuthCheck.CheckAuth(heatAdminAuth,heatAu.getCustomAuthority()))
                { saveOrUpdate(heatAu);flag=true;}
            }
        }

        return flag;
    }
    @Override
    public List<String> selectUsername(){
        List<String> userList=new ArrayList<>();
        for(User us:heatAuthMapper.selectSystemUser()){
            userList.add(us.getUsername());
        }
        return userList;
    }
    @Override
    public List<HeatAuth> selectUserAuthByUs(String username){
        return heatAuthMapper.selectUserAuth(username);
    }

    @Override
    public  List<HeatAuth> selectMyAuth(String username){
        return heatAuthMapper.selectUserAuth(username);
    }
    @Override
    public  List<HeatAuth> selectMyDis(String username){
        return heatAuthMapper.selectUserDis(username);
    }

    @Override
    public  boolean saveSys(HeatSys heatSys){
        heatSys.setId(1);
        return heatAuthMapper.saveSysPar(heatSys);
    }

    @Override
    public  HeatSys selectSys(String username){
        if(username.equals("wizard")){
            return heatUserMapper.selectSysPara().get(0);
        }
        else return null;
    }
}
