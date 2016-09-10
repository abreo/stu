package cn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ss {

	public static void main(String[] args) {
		List<String> list=new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		Iterator<String> it=list.iterator();
		while(it.hasNext()){
			String s=it.next();
			if(s.equals("2")){
				it.remove();
			}
		}
		for(String s:list){
			System.out.println(s);
		}
		it=list.iterator();
		while(it.hasNext()){
			String s=it.next();
			System.out.println(s);
		}
	}

}
