package com.persistence.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.TreeMap;

public class TxtUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "C:/Users/guochang/Desktop/wsp/0518/123.txt";
		Map<String, String> map = readTxt(fileName);
		
		
	}
	public static Map<String, String> readTxt(String fileName){
		Map<String, String> map = new TreeMap();
		Map<String, Integer> map1 = new TreeMap();//存放重复的
		File file = new File(fileName);  
		BufferedReader reader = null;  
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null; 
			int line = 1;//重复行
			int i = 0;//重复数量
			StringBuffer sb = new StringBuffer();
			while ((tempString = reader.readLine()) != null){
				//tempString = tempString.substring(0, tempString.length()-1);
				 if(map.containsKey(tempString)){
					 i++;
					 //System.out.println("重复行：" + line + "-" + tempString);
					 
					 if(map1.containsKey(tempString)){
						 int a = map1.get(tempString);
						 a++;
						 map1.put(tempString, a);
					 }else{
						 map1.put(tempString, 1);
					 }
				 }else{
					 map.put(tempString, tempString);
				 }
				 line++;
				sb.append(tempString + ",");
			}
			
			//System.out.println("map1:" + map1);
			int u = 0;
			for(String s:map1.keySet()){
				u++;
				if(u < 1011111){
					System.out.println("[" + s + "]重复次数[" + map1.get(s) + "]");
				}
			}
			System.out.println("重复数量[" + i + "]条");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (Exception e2) {
				}
			}
		}
		return map;
	}
	
	
	/*public static Map<String, String> readTxt(String fileName){
		Map<String, String> map = new TreeMap();
		File file = new File(fileName);  
		BufferedReader reader = null;  
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null; 
			int line = 1;
			int i = 0;
			StringBuffer sb = new StringBuffer();
			while ((tempString = reader.readLine()) != null){
				tempString = tempString.substring(0, tempString.length()-1);
				 if(map.containsKey(tempString)){
					 System.out.println("重复行：" + line + "-" + tempString);
					 i++;
				 }else{
					 map.put(tempString, tempString);
				 }
				 line++;
				sb.append(tempString + ",");
			}
			System.out.println("重复数量[" + i + "]条");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (Exception e2) {
				}
			}
		}
		return map;
	}*/

}
