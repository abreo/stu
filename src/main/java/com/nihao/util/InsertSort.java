package com.nihao.util;

import java.util.List;


public class InsertSort {
	public static void sort(List list){
		if(list==null||list.size()<2){
			return;
		}
		for(int i=1;i<list.size();i++){
			Object currentObject=list.get(i);
			int position=i;
			for(int j=i-1;j>=0;j--){
				if(((Comparable)list.get(j)).compareTo(currentObject)>0){
					list.set(j+1, list.get(j));
					position-=1;
				}
				else{
					break;
				}
			}
			list.set(position, currentObject);
		}
	}
}
