package com.nihao.util;

import java.util.List;

public class QuickSort {
	public static void sort(List list,int left,int right){
		if(left>=right)
			return;
		int i=left,j=right;
		Comparable tempObject=(Comparable) list.get(left);
		
		while(i!=j){
			while(tempObject.compareTo(list.get(j))<0&&i<j)
				j--;
			while(tempObject.compareTo(list.get(i))>0&&j<j)
				i++;
			if(i<j){
				Object t=list.get(i);
				list.set(i, list.get(j));
				list.set(j, t);
				j--;
			}
		}
		
		list.set(left, list.get(i));
		list.set(i, tempObject);
		
		if(left<i-1)
			sort(list,left,i-1);
		if(i+1<right)
			sort(list,i+1,right);
	}
}
