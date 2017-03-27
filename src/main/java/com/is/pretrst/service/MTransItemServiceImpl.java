package com.is.pretrst.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.pretrst.entity.MTransItem;

/**
 *
 * @ClassName: MTransItemServiceImpl
 * @Description: MTransItem表对应的服务类
 * @author 
 * @date 2013-10-31 18:27:51 *
 */
@Component
@Transactional 
public class MTransItemServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(MTransItem.class);

}
