package com.huang.tools;

import com.huang.entity.HeatAuth;

import java.util.List;

public class HeatAuthCheck {

    public boolean CheckAuth(List<HeatAuth> heatCheckAuths,String auth){
        for(HeatAuth heatAu:heatCheckAuths){
            if(auth.startsWith(heatAu.getAdminAuthority())&&!auth.equals(heatAu.getAdminAuthority()))return true;
        }
        return false;
    }

}
