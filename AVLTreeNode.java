package com.learn.ds.tree;

public class AVLTreeNode {

	private int data;
	private int height;
	private AVLTreeNode left;
	private AVLTreeNode right;

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public AVLTreeNode getLeft() {
		return left;
	}

	public void setLeft(AVLTreeNode left) {
		this.left = left;
	}

	public AVLTreeNode getRight() {
		return right;
	}

	public void setRight(AVLTreeNode right) {
		this.right = right;
	}

	public int height(AVLTreeNode root) {
		if (root == null) {
			return -1;
		} else {
			return root.getHeight();
		}
	}

	/**
	 * Left Left Rotation (LL Rotation) [Case - 1] T : O(1) and S: O(1)
	 */
	public AVLTreeNode singleRotateLeft(AVLTreeNode x) {
		AVLTreeNode w = x.getLeft();

		x.setLeft(w.getRight());
		w.setRight(x);

		x.setHeight(Math.max(height(x.getLeft()), height(x.getRight()) + 1));
		w.setHeight(Math.max(height(w.getLeft()), x.getHeight()) + 1);

		return w;
	}

	/**
	 * Right Right Rotation (RR Rotation) [Case - 4] T : O(1) and S: O(1)
	 */
	public AVLTreeNode singleRotateRight(AVLTreeNode x) {
		AVLTreeNode w = x.getRight();

		x.setRight(w.getLeft());
		w.setLeft(x);

		x.setHeight(Math.max(height(x.getLeft()), height(x.getRight()) + 1));
		w.setHeight(Math.max(height(w.getRight()), x.getHeight()) + 1);

		return w;
	}

	/**
	 * Double Rotations 1. Lift Right Rotation (LR Rotation) [Case - 2] 
	 * 2. Right Left Rotation (RL Rotation) [Case - 3]
	 */
	public AVLTreeNode doubleRotateWithLeft(AVLTreeNode z) {
		z.setLeft(singleRotateLeft(z.getLeft()));
		return singleRotateLeft(z);
	}

	private AVLTreeNode doubleRotateWithRight(AVLTreeNode z) {
		z.setRight(singleRotateRight(z.getRight()));
		return singleRotateRight(z);
	}

	/**
	 * Insertion into AVL Tree
	 */
	public AVLTreeNode insert(AVLTreeNode root, AVLTreeNode parent, int data) {
		if (root == null) {
			root = new AVLTreeNode();
			root.setData(data);
			root.setHeight(0);
			root.setLeft(null);
			root.setRight(null);
		} else if (data < root.getData()) {
			root.setLeft(insert(root.getLeft(), root, data));
			if ((height(root.getLeft()) - height(root.getRight())) == 2) {
				if (data < root.getLeft().getData()) {
					root = singleRotateLeft(root);
				} else {
					root = doubleRotateWithLeft(root);
				}
			}
		} else if (data > root.getData()) {
			root.setRight(insert(root.getRight(), root, data));
			if ((height(root.getRight()) - height(root.getLeft())) == 2) {
				if (data < root.getRight().getData()) {
					root = singleRotateRight(root);
				} else {
					root = doubleRotateWithRight(root);
				}
			}
		}
		root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);
		return root;
	}

}
