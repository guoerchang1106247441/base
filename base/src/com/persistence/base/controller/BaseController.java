package com.persistence.base.controller;

import java.io.File;
import java.util.Date;
import java.util.Random;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.persistence.base.tool.Constains;
import com.persistence.base.tool.kit.SerializableUtil;
import com.persistence.base.tool.kit.date.DateFormats;
import com.persistence.base.tool.kit.date.DateUtil;
import com.persistence.base.tool.util.FileUploadUtil;


public abstract class BaseController extends BaseSpringMvcController{
	
	protected <T> T getUser(){
		if (null == this.session) {
			return null;
		}
		return (T) session.getAttribute(Constains.USER_SESSION);
	}
	
	protected Object unserializable(String source){
		try{
			return SerializableUtil.unserializable(source);
		}catch (Exception e){
			return null;
		}
	}
	
	protected void success(String msg){
		session.setAttribute(Constains.FLASH_SUCCESS_SUCCESS, msg);
	}
	
	protected void error(String msg){
		session.setAttribute(Constains.FLASH_SUCCESS_ERROR, msg);
	}
	
	protected Object upload(CommonsMultipartFile file){
		FileUploadUtil.UploadResultInfo uploadInfo = null;
        try {
        	//获取文件名称
            String name = file.getOriginalFilename();
            //获取文件后缀名
            String suffix = name.substring(name.lastIndexOf("."));
            //重新生成文件名称
            name = getDateTimeStr() + getRandomNum(4) + suffix;
            String serverPath = File.separator + "upload" + File.separator + "user" + File.separator;//文件在服务端路径
            uploadInfo = FileUploadUtil.transferFile2Server(name, serverPath, this.request, file);//上传文件
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadInfo;
	}
	
	public static String getRandomString(int length) {
		// length表示生成字符串的长度
	    String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		// 生成字符串从此序列中取
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * 生成随机数
	 * @param length
	 * @return
	 */
	public static String getRandomNum(int length) {
		// length表示生成字符串的长度
		String base = "0123456789";
		// 生成字符串从此序列中取
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	
	/**
	 * 获取当前时间字符格式
	 * @param length
	 * @return
	 */
	public static String getDateTimeStr(){
		return DateUtil.DateToString(new Date(), DateFormats.DATE_TIME_STR);
	}
}
