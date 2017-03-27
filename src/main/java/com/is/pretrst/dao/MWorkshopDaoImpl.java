package com.is.pretrst.dao;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.MWorkshopExt;
import com.is.pretrst.entity.query.MWorkshopExtQuery;

@Component
public class MWorkshopDaoImpl extends Mybatis3Dao<MWorkshop>
{
    @SuppressWarnings("unused")
    private static final Logger   LOGGER    = LoggerFactory.getLogger(MWorkshopDaoImpl.class);

    protected static final String NAMESPACE = "MWorkshop";

    @Override
    public String getIbatisMapperNamesapce()
    {
        return NAMESPACE;
    }
    /**
     * 根据工地名称查询负责人名称 
     * @param wsNm 工地代码
     * @return
     */
    public String getShopMangeUserName(String wsNm){
       return (String) this.getSqlSessionTemplate().selectOne("MWorkshop.selectMangeName", wsNm);
    }
    /**
     * 根据工地名称查询工地代码 
     * @param wsNm 工地名称
     * @return
     */
    public String getShopWsCd(String wsNm){
       return (String) this.getSqlSessionTemplate().selectOne("MWorkshop.selectShopWsCd", wsNm);
    }
    
    /**
     * 根据wscd查询条件查询当前进行中的项目
     * @param entity
     * @return
     */
    public List<MWorkshop> selectEntityByNow(MWorkshop entity){
         return this.getSqlSessionTemplate().selectList("MWorkshop.selectEntityByNow", entity);
    }
    /**
     * 查询所有当前进行中的项目
     * @param entity
     * @return
     */
    public List<MWorkshop> selectAllEntityByNow(){
        return this.getSqlSessionTemplate().selectList("MWorkshop.selectAllEntityByNow");
    }
	public List<MWorkshopExt> selectWorkshopByEntity(
			MWorkshopExtQuery entity) {
		return this.getSqlSessionTemplate().selectList("MWorkshop.selectWorkExByEntity",entity);
	}
	
	public List<String> getWorkShopImg(MWorkshop entity){
		return this.getSqlSessionTemplate().selectList("MWorkshop.getWorkShopImg",entity);
	}
	public List< MWorkshopExt> selectQyBZWorkshopByEntity(
			MWorkshopExtQuery entity) {
		return this.getSqlSessionTemplate().selectList("MWorkshop.selectQyBZWorkshopByEntity",entity);	}
	
}