package com.is.utils.authUtil;

import java.util.List;
import java.util.Map;

import org.springside.modules.utils.spring.SpringContextHolder;

import com.is.pretrst.entity.DProgressReport;
import com.is.pretrst.service.DProgressReportServiceImpl;

/**
 * 终端权限处理类
 * 
 * @author zjm
 * 
 */
public class JdglTreeUtil {

	private JdglTreeUtil() {
	}

	private static Map<String, List<DProgressReport>> reportMap = null;

	public static Map<String, List<DProgressReport>> getJDGLTree() {
		if (reportMap == null) {
			DProgressReportServiceImpl dprogressReportServiceImpl = (DProgressReportServiceImpl) SpringContextHolder
					.getBean(DProgressReportServiceImpl.class);
			reportMap = dprogressReportServiceImpl.selectProgressIndex();
		}
		return reportMap;
	}

	public static void refresh() {
		reportMap = null;
	}
}
