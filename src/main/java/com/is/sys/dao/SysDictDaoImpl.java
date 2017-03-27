package com.is.sys.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.sys.entity.SysDict;

/**
 *
 * @ClassName: SysDictDaoImpl
 * @Description: SysDict表对应的数据库操作类
 * @author 
 * @date 2013-02-27 14:20:15 *
 */
@Component
public class SysDictDaoImpl extends Mybatis3Dao<SysDict> {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(SysDictDaoImpl.class);

    protected static final String NAMESPACE = "SysDict";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
    
    
	/**
	 * 查询当前最大的序号并加1,
	 * 
	 * @param
	 * @return
	 */
	public int selectMaxNumber(String  typeCode) {
		Object obj = getSqlSessionTemplate().selectOne(
				"SysDict.selectMaxNumber",typeCode);
		if (obj != null) {
			return (Integer) obj;
		}
		return 1;
	}
/*	public static void main(String[] args) {
		SysDictDaoImpl sysDictDaoImpl = new SysDictDaoImpl();
	
	}*/
}