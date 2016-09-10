package com.nihao.util;

import java.util.ArrayList;
import java.util.List;

import com.nihao.model.view.ResourceVO;

public class ZuZhuangUtil {
	public static void zuZhuang(List list,Object object,Class cla){
		List children=new ArrayList<>();
		for(int i=list.size()-1;i>=0;i--){
			if(i>=list.size())
				continue;
			Object obj=list.get(i);
			Integer objectId=(Integer)ReflectUtil.getValue(object, "id");
			Integer parentId=(Integer)ReflectUtil.getValue(obj, "parentid");
			if(objectId.equals(parentId)){
				
				try {
					Object child = Class.forName(cla.getName()).newInstance();
					ReflectUtil.cloneValues(obj, child);
					children.add(child);
					list.remove(i);
					zuZhuang(list, child, cla);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				
			}
		}
		InsertSort.sort(children);
		ReflectUtil.setChildren(object, children);
	}
}
