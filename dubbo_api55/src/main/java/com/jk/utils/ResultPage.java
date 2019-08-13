package com.jk.utils;

import java.io.Serializable;

public class ResultPage  implements Serializable {
	
	private Integer total;
	private Object rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	public ResultPage(Integer total, Object rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public ResultPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ResultPage [total=" + total + ", rows=" + rows + "]";
	}
	
	

}
