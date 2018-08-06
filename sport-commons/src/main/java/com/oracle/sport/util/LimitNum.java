package com.oracle.sport.util;

import java.io.Serializable;

public class LimitNum implements Serializable{

	private static final long serialVersionUID = 1L;
	private int pageno;
	private int size;
	
	public LimitNum() {
		super();
	}
	public LimitNum(int pageno, int size) {
		super();
		this.pageno = pageno;
		this.size = size;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
}
