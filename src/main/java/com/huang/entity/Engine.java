package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "mainenginemodule")
public class Engine {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //膨胀水箱液位
    private float expansionWaterTankLevel;
    //膨胀水箱高限位
    private float expansionWaterTankHighLimit;
    //膨胀水箱低限位
    private float expansionWaterTankLowLimit;
    //料斗温度
    private float hopperTemp;
    //料斗高限位
    private float hopperHighLimit;
    //料斗低限位
    private float hopperLowLimit;
    //料斗高温设定
    private float hopperTempSet;
    //炉膛温度
    private float firepotTemp;
    //炉膛压力
    private float firepotPres;
    //炉膛高温设定
    private float firepotHighTempSet;
    //炉膛压力高设定
    private float firepotPresHighSet;
    //炉膛温度高设定
    private float firepotTempHighSet;
    //排烟温度
    private float exhaustSmokeTemperature;
    //排烟温度高设定
    private float smokeExhaustTemperatureHighSetting;
    //供水温度
    private float supplyWaterTemp;
    //供水温度高设定
    private float supplyWaterTempSet;
    //供水压力
    private float supplyWaterPres;
    //供水压力高设定
    private float supplyWaterPresSet;
    //回水温度
    private float returnWaterTemp;
    //回水温度高设定
    private float returnWaterTempSet;
    //回水压力
    private float returnWaterPres;
    //回水压力高设定
    private float returnWaterPresSet;
    //炉膛出口烟温
    private float furnaceOutletSmokeTemperature;

}
