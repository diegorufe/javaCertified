package com.rfjavacertified.beans;

import java.io.Serializable;

/**
 * 
 * @author diego
 *
 */
public class Answer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7633010167035685624L;
	private String text;
	private int index;

	public Answer() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
