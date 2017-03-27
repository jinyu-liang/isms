package com.is.pretrst.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.pretrst.entity.DInvoiceItem;

/**
 *
 * @ClassName: DInvoiceItemServiceImpl
 * @Description: DInvoiceItem表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:18 *
 */
@Component
@Transactional 
public class DInvoiceItemServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(DInvoiceItem.class);

}
