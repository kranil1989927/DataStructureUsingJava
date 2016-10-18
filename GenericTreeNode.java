package com.learn.ds.tree;

public class GenericTreeNode {

	private int data;
	private GenericTreeNode firstChild;
	private GenericTreeNode nextSibling;

	public GenericTreeNode() {
		this.data = 0;
		this.firstChild = null;
		this.nextSibling = null;
	}

	public GenericTreeNode(int data) {
		this.data = data;
		this.firstChild = null;
		this.nextSibling = null;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public GenericTreeNode getFirstChild() {
		return firstChild;
	}

	public void setFirstChild(GenericTreeNode firstChild) {
		this.firstChild = firstChild;
	}

	public GenericTreeNode getNextSibling() {
		return nextSibling;
	}

	public void setNextSibling(GenericTreeNode nextSibling) {
		this.nextSibling = nextSibling;
	}
}
