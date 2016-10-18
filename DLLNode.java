package com.learn.ds.linkedList;

public class DLLNode {

	private int data;
	private DLLNode next;
	private DLLNode prev;

	public DLLNode(int data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}

	public DLLNode(int data, DLLNode next, DLLNode prev) {
		super();
		this.data = data;
		this.next = next;
		this.prev = prev;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public DLLNode getNext() {
		return next;
	}

	public void setNext(DLLNode next) {
		this.next = next;
	}

	public DLLNode getPrev() {
		return prev;
	}

	public void setPrev(DLLNode prev) {
		this.prev = prev;
	}

}
