package com.iec.solr.bean;

import java.io.Serializable;

/**
 * @author ex_kjkfb_lvrz
 * @Date 2014-9-19 下午05:45:53
 * @description 自动补全功能实体
 * @Version V1.0
 */
public class AutoComplete implements Serializable{
	
     /**
	 * 
	 */
	private static final long serialVersionUID = 2591463043849129943L;
	private String value;
     private Long count;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
     
}
