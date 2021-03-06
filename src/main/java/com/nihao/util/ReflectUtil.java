package com.nihao.util;

import java.lang.reflect.Field;
import java.util.List;

/**
 * ReflectUtil
 * @author nihao by eclipse
 *
 */
public class ReflectUtil {

	/**
	 * 获取指定属性值
	 * @param obj
	 * @param name
	 * @return
	 */
	public static Object getValue(Object obj,String name){
		Field fields[]=obj.getClass().getDeclaredFields();
		Field field=null;
		for (int i = 0; i < fields.length; i++) {
			field=fields[i];
			field.setAccessible(true);
			if(field.getName().equalsIgnoreCase(name)){
				try {
					return field.get(obj);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		throw new RuntimeException("没有找到对应VALUE值:"+name+",在类:"+obj.getClass().getName()+"中");
	}

	/**
	 * 克隆属性值
	 * @param from
	 * @param to
	 */
	public static void cloneValues(Object from,Object to){
		Field fieldsFrom[]=from.getClass().getDeclaredFields();
		Field fieldsTo[]=to.getClass().getDeclaredFields();
		for(int i = 0; i < fieldsTo.length; i++){
			Field fieldTo=fieldsTo[i];
			fieldTo.setAccessible(true);
			for(int j = 0; j < fieldsFrom.length; j++){
				Field fieldFrom=fieldsFrom[j];
				if(fieldTo.getName().equals(fieldFrom.getName())){
					fieldFrom.setAccessible(true);
					try {
						fieldTo.set(to, fieldFrom.get(from));
						break;
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}

	/**
	 * 设置子节点
	 * @param obj
	 * @param list
	 */
	public static void setChildren(Object obj,List list){
		Field fields[]=obj.getClass().getDeclaredFields();
		Field field=null;
		for(int i = 0; i < fields.length; i++){
			field=fields[i];
			if(field.getName().equalsIgnoreCase("children")){
				field.setAccessible(true);
				try {
					field.set(obj, list);
					return;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		throw new RuntimeException("在类"+obj.getClass().getName()+"中未找到children属性");
	}
}
