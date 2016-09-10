package com.nihao.model.view;

import java.lang.reflect.Field;

public class SeqUtil {
	private static Integer getIndex(Object obj) {
		Field fields[]=obj.getClass().getDeclaredFields();
		Integer out=null;
		for(Field field:fields){
			field.setAccessible(true);
			if("seq".equals(field.getName())){
				try {
					out=(Integer) field.get(obj);
					break;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		if(out==null){
			throw new RuntimeException("没有找到SEQ");
		}
		return out;
	}
}
