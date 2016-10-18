package com.nihao.util;

import java.util.ArrayList;
import java.util.List;

import com.nihao.model.view.ResourceVO;

/**
 * 装配工具类
 * @author nihao by eclipse
 *
 */
public class AssembleUtil {
	/**
	 * 将list集合中的对象根据父子节点关系,装配到object类中
	 * @param list
	 * @param object
	 * @param cla
	 */
	public static void assemble(List list,Object object,Class cla){
		if(object==null||ReflectUtil.getValue(object, "id")==null)
			return;
		Integer objectId=(Integer)ReflectUtil.getValue(object, "id");
		List children=new ArrayList<>(list.size());
		for(int i=list.size()-1;i>=0;i--){
			if(i>=list.size())
				continue;
			Object currentObj=list.get(i);
			if(ReflectUtil.getValue(currentObj, "parentid")==null)
				continue;
			Integer parentId=(Integer)ReflectUtil.getValue(currentObj, "parentid");
			if(objectId.equals(parentId)){
				try {
					Object child = Class.forName(cla.getName()).newInstance();
					ReflectUtil.cloneValues(currentObj, child);
					children.add(child);
					list.remove(i);
					assemble(list, child, cla);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		InsertSort.sort(children);
		ReflectUtil.setChildren(object, children);
	}
}
