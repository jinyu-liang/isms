package com.is.utils.swfUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SWFUpload 上传插件的Struts后台保存文件帮助类
 * 
 * @author 
 * 
 */

public class SWFUploadHelper {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SWFUploadHelper.class);

	/**
	 * SWFUpload 上传插件的Struts后台保存文件帮助方法
	 * 
	 * @param file
	 *            获取到的文件对象
	 * @param filePath
	 *            保存文件的路径
	 * @param fileName
	 *            保存文件的名称
	 */
	static public void uploadFiles(File file, String filePath, String fileName) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(file);

			if (!new File(filePath).exists()) {
				new File(filePath).mkdirs();
			}
			File deskFile = new File(filePath, fileName);
			os = new FileOutputStream(deskFile);
			byte[] bytefer = new byte[51200];
			int length = 0;
			while ((length = is.read(bytefer)) > 0) {
				os.write(bytefer, 0, length);
			}
		} catch (Exception e) {
			LOGGER.error("附件上传出错信息[{}]", e.getMessage());
		} finally {
			try {
				if (is != null && os != null) {
					os.close();
					is.close();
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 根据传入文件名获取文件类型
	 * 
	 * @param oldFileName
	 * @return
	 */
	public static String getFileType(String oldFileName) {
		String file_type = "";
		int begin = 0;
		int begin_tmp = 0;
		while ((begin = oldFileName.indexOf(".", begin + 1)) > -1) {
			begin_tmp = begin;
		}
		file_type = oldFileName.substring(begin_tmp + 1);
		if (oldFileName.length() == file_type.length() + 1) {
			file_type = "";
		}
		return file_type;
	}

	/**
	 * 传入包含文件名的文件路径，获取文件名字
	 * 
	 * @param oldFileName
	 * @return
	 */
	public static String getFileName(String fileName) {
		String file_type = "";
		int begin = 0;
		int begin_tmp = 0;
		while ((begin = fileName.indexOf("\\", begin + 1)) > -1) {
			begin_tmp = begin;
		}
		file_type = fileName.substring(begin_tmp + 1);
		if (fileName.length() == file_type.length() + 1) {
			file_type = "";
		}
		return file_type;
	}

}