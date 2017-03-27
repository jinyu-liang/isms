package com.is.pretrst.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.dao.DDeliveryPlanDaoImpl;
import com.is.pretrst.entity.DDeliveryPlan;

@Component
public class DDeliveryPlanDaoImpl extends Mybatis3Dao<DDeliveryPlan>
{
    @SuppressWarnings("unused")
    private static final Logger   LOGGER    = LoggerFactory.getLogger(DDeliveryPlanDaoImpl.class);

    protected static final String NAMESPACE = "DDeliveryPlan";

    @Override
    public String getIbatisMapperNamesapce()
    {
        return NAMESPACE;
    }
    
    /**
     * 根据查询条件查找多个webservice对象
     */
    public List<DDeliveryPlan> selectByEntityWs(DDeliveryPlan entity){
        return  this.getSqlSessionTemplate().selectList(getIbatisMapperNamesapce()+".selectByEntityWs", entity);
    }
    
    /**
     *  根据userId查找回复表里对应已经完成的发料计划
     *  @param userId 用户id
     */
    public List<DDeliveryPlan> selectPlanFromMapp(DDeliveryPlan entity){
        return  this.getSqlSessionTemplate().selectList(getIbatisMapperNamesapce()+".selectPlanFromMapp", entity);
    }
    
    
  /*  *//**
     * 查询当前用户回复的已下发数据与出门图片用的对象 (已下发数据表用)
     *//*
    public List<DDeliveryPlan> selectPlanAndInvoiceImageByUserIdStatus(DDeliveryPlan entity){
        return  this.getSqlSessionTemplate().selectList(getIbatisMapperNamesapce()+".selectPlanAndInvoiceImageByUserIdStatus", entity);
    }
    *//**
     * 查询所有已下发数据与出门图片用的对象 (已下发数据表用)
     *//*
    public List<DDeliveryPlan> selectAllPlanAndInvoiceImageByStatus(){
        return this.getSqlSessionTemplate().selectList(getIbatisMapperNamesapce()+".selectAllPlanAndInvoiceImageByStatus");
    }*/
    
//    /**
//     * 发货员查看所有已下发的数据，封装与计划表相关的出门单的状态
//     */
//    public List<DDeliveryPlan> selectIssueedBySender(DDeliveryPlan entity){
//        return this.getSqlSessionTemplate().selectList(getIbatisMapperNamesapce()+".selectIssueedBySender");
//    }
}