package com.learn.ds.tree;

import java.util.Stack;

public class ThreadedBinaryTreeProblems {

	/**
	 * For a given binary tree (not threaded) how do we find the preorder successor using stack
	 */
	public BinaryTreeNode preOrderSuccessor(BinaryTreeNode node) {
		BinaryTreeNode p = null;
		Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
		if (node != null) {
			p = node;
		}
		if (p.getLeft() != null) {
			s.push(p);
			p = p.getLeft();
		} else {
			while (p.getRight() == null) {
				p = s.pop();
			}
			p = p.getRight();
		}
		return p;
	}
	
	/**
	 * InOrder Successor
	 */
	public BinaryTreeNode inOrderSuccessor(BinaryTreeNode node) {
		BinaryTreeNode p = null;
		Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
		if (node != null) {
			p = node;
		}
		if (p.getRight() != null) {
			p = s.pop();
		} else {
			while (p.getLeft() == null) {
				s.push(p);
			}
			p = p.getLeft();
		}
		return p;
	}
}
