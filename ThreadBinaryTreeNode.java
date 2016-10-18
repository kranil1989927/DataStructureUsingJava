package com.learn.ds.tree;

public class ThreadBinaryTreeNode {
	private ThreadBinaryTreeNode left;
	private int lTag;
	private int data;
	private int rTag;
	private ThreadBinaryTreeNode right;

	public ThreadBinaryTreeNode getLeft() {
		return left;
	}

	public void setLeft(ThreadBinaryTreeNode left) {
		this.left = left;
	}

	public int getLTag() {
		return lTag;
	}

	public void setLTag(int lTag) {
		this.lTag = lTag;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public int getRTag() {
		return rTag;
	}

	public void setRTag(int rTag) {
		this.rTag = rTag;
	}

	public ThreadBinaryTreeNode getRight() {
		return right;
	}

	public void setRight(ThreadBinaryTreeNode right) {
		this.right = right;
	}

	/**
	 * Finding Inorder Successor in Inorder Threaded Binary Tree
	 * Approach :
	 * If P has a no right subtree, then return the right child of P.
	 * If P has right subtree, then return the left of the nearest node 
	 * whose left subtree contains P.
	 */
	public ThreadBinaryTreeNode inOrderSuccessor(ThreadBinaryTreeNode p) {
		ThreadBinaryTreeNode position;
		if (p.getRTag() == 0) {
			return p.getRight();
		} else {
			position = p.getRight();
		}

		while (p.getLTag() == 1) {
			position = position.getLeft();
		}
		return position;
	}
	
	/**
	 * Inorder traversal in Inorder Threaded Binary Tree
	 * We can start with dummy node and call InOrderSuccessor() to visit each node
	 * until we reach dummy node.
	 */
	public void inOrderTraversal(ThreadBinaryTreeNode root) {
		ThreadBinaryTreeNode p = inOrderSuccessor(root);
		while (p != root) {
			p = inOrderSuccessor(p);
			System.out.println(p.getData());
		}
	}
	
	// Alternative approach
	public void inOrderTraversal1(ThreadBinaryTreeNode root) {
		ThreadBinaryTreeNode p = root;
		while (true) {
			p = inOrderSuccessor(p);
			if (p == root) {
				return;
			}
			System.out.println(p.getData());

		}
	}
	
	/**
	 * Finding PreOrder Successor in InOrder Threaded Binary Tree
	 * Approach :
	 * If P has left subtree, then return the left child of P. If P has no left subtree, then
	 * return the right child of the nearest node whose right subtree contain P.
	 */
	public ThreadBinaryTreeNode preOrderSuccessor(ThreadBinaryTreeNode p) {
		ThreadBinaryTreeNode position;
		if (p.getLTag() == 1) {
			return p.getLeft();
		} else {
			position = p;
			while (position.getRTag() == 0) {
				position = position.getRight();
			}
			return position.getRight();
		}
	}
	
	/**
	 * PreOrder traversal of InOrder Threaded Binary Tree
	 * Approach :
	 * Start with dummy node and call preOrderSuccessor() to visit each node until we get dummy
	 * node again
	 */
	public void preOrderTraversal(ThreadBinaryTreeNode root) {
		ThreadBinaryTreeNode p = preOrderSuccessor(root);
		while (p != root) {
			p = preOrderSuccessor(p);
			System.out.println(p.getData());
		}
	}
	
	// Alternative way of coding
	public void preOrderTraversal1(ThreadBinaryTreeNode root) {
		ThreadBinaryTreeNode p = root;
		while (true) {
			p = preOrderSuccessor(p);
			if (p == root) {
				return;
			}
			System.out.println(p.getData());
		}
	}
	
	/**
	 * Insertion nodes in InOrder Threaded Binary Tree.
	 * We have two cases:
	 * ------------------
	 * 1. Node P doesn't have right child : In this case we just need to attach Q to P and change
	 * its left and right pointers.
	 * 2. Node P has right child (say R): In this case we need to traverse R' left subtree and find
	 * the left most node and then update the left and right pointers of that node.
	 */
	
	public void insertRightInOrderTBT(ThreadBinaryTreeNode p, ThreadBinaryTreeNode q) {
		ThreadBinaryTreeNode temp;
		q.setRight(p.getRight());
		q.setRTag(p.getRTag());
		q.setLeft(p);
		q.setLTag(0);

		p.setRight(q);
		p.setRTag(1);

		if (q.getRTag() == 1) {
			temp = q.getRight();
			while (temp.getLTag() == 0) {
				temp = temp.getLeft();
			}
			temp.setLeft(q);
		}
	}
}

