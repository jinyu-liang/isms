package com.base.dict;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.googlecode.ehcache.annotations.Cacheable;
import com.is.sys.dao.SysDictDaoImpl;
import com.is.sys.entity.SysDict;
import com.is.utils.StringUtils;

/**
 * 字典工具类
 * 
 * @author life
 * 
 */
@Component
@Service
public class SysDictInterfaceImpl {

	/**
	 * 根据字典类型和字典值，获取字典名称
	 * 
	 * @param dictType
	 * @param dictCode
	 * @return 查找不到返回null，参数值任意为空返回null
	 */
	@Cacheable(cacheName = "DEFAULT_CACHE")
	public String getDictNameByTypeAndCode(String dictType, String dictCode) {
		if (StringUtils.isEmpty(dictType) || StringUtils.isEmpty(dictCode)) {
			return null;
		}
		SysDictDaoImpl sysDict = (SysDictDaoImpl) SpringContextHolder
				.getBean("sysDictDaoImpl");
		SysDict dict = new SysDict();
		dict.setDictTypeCode(dictType);
		dict.setDictCode(dictCode);
		dict = sysDict.selectOneByEntity(dict);
		if (dict != null) {
			return dict.getDictName();
		}
		return "";
	}

	/**
	 * 根据机构id和字典类型获取字典列表
	 * 
	 * @param orgId
	 * @param dictType
	 * @return
	 */
	@Cacheable(cacheName = "DEFAULT_CACHE")
	public List<SysDict> getDictByType(String dictType) {
		List<SysDict> dictList = new ArrayList<SysDict>();
		if (StringUtils.isEmpty(dictType)) {
			return dictList;
		}
		SysDictDaoImpl sysDict = (SysDictDaoImpl) SpringContextHolder
				.getBean("sysDictDaoImpl");
		SysDict type = new SysDict();
		type.setDictTypeCode(dictType);
		return sysDict.selectByEntity(type);
	}
}
