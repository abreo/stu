package com.nihao.model.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BootstrapTree {
	private Integer id;
	private Integer seq;
	private String text;
	private String icon;
	private List<BootstrapTree> nodes=new ArrayList<>(0);
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<BootstrapTree> getNodes() {
		return nodes;
	}
	public void setNodes(List<BootstrapTree> nodes) {
		this.nodes = nodes;
	}
	public void addNode(BootstrapTree node){
		this.nodes.add(node);
		Collections.sort(this.nodes, new Comparator<BootstrapTree>() {
            @Override
            public int compare(BootstrapTree o1, BootstrapTree o2) {
                return o1.getSeq()-o2.getSeq();
            }
        });
	}
}
