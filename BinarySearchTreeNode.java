package com.learn.ds.tree;

public class BinarySearchTreeNode {

	private int data;
	private BinarySearchTreeNode left;
	private BinarySearchTreeNode right;

	public BinarySearchTreeNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public BinarySearchTreeNode() {
		this.left = null;
		this.right = null;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public BinarySearchTreeNode getLeft() {
		return left;
	}

	public void setLeft(BinarySearchTreeNode left) {
		this.left = left;
	}

	public BinarySearchTreeNode getRight() {
		return right;
	}

	public void setRight(BinarySearchTreeNode right) {
		this.right = right;
	}

	/**
	 * Finding an element in binary search tree.
	 */
	public BinarySearchTreeNode find(BinarySearchTreeNode root, int data) {
		if (root == null) {
			return null;
		}
		if (data < root.getData()) {
			return find(root.getLeft(), data);
		} else if (data < root.getData()) {
			return find(root.getRight(), data);
		}
		return root;
	}

	// Non-Recursive Approach
	public BinarySearchTreeNode searchElement(BinarySearchTreeNode root, int data) {
		if (root == null) {
			return null;
		}

		while (root != null) {
			if (data == root.getData()) {
				return root;
			} else if (data < root.getData()) {
				root = root.getLeft();
			} else {
				root = root.getRight();
			}
		}
		return null;
	}

	/**
	 * Finding minimum element in binary search tree
	 */
	public BinarySearchTreeNode findMinimum(BinarySearchTreeNode root) {
		if (root == null) {
			return root;
		} else {
			if (root.getLeft() == null) {
				return root;
			} else {
				return findMinimum(root.getLeft());
			}
		}
	}

	// Non-Recursive Approach
	public BinarySearchTreeNode findMinimumIterative(BinarySearchTreeNode root) {
		if (root == null) {
			return root;
		}

		while (root.getLeft() != null) {
			root = root.getLeft();
		}
		return root;
	}

	/**
	 * Finding maximum element in binary search tree
	 */
	public static BinarySearchTreeNode findMaximum(BinarySearchTreeNode root) {
		if (root == null) {
			return root;
		} else {
			if (root.getRight() == null) {
				return root;
			} else {
				return findMaximum(root.getRight());
			}
		}
	}

	// Non-Recursive Approach
	public static BinarySearchTreeNode findMaximumIterative(BinarySearchTreeNode root) {
		if (root == null) {
			return root;
		}

		while (root.getRight() != null) {
			root = root.getRight();
		}
		return root;
	}

	/**
	 * Inserting an element in Binary Search Tree
	 */
	@SuppressWarnings("unused")
	public static BinarySearchTreeNode insert(BinarySearchTreeNode root, int data) {
		if (root == null) {
			root = new BinarySearchTreeNode(data);
			if (root == null) {
				System.out.println("Memory Error");
				return root;
			}
		} else {
			if (data < root.getData()) {
				root.setLeft(insert(root.getLeft(), data));
			} else if (data > root.getData()) {
				root.setRight(insert(root.getRight(), data));
			}
		}
		return root;
	}

	/**
	 * Delete an element from Binary Search Tree.
	 */
	public static BinarySearchTreeNode delete(BinarySearchTreeNode root, int data) {
		BinarySearchTreeNode temp = null;
		if (root == null) {
			System.out.println("No element in the tree");
		} else if (data < root.getData()) {
			root.setLeft(delete(root.getLeft(), data));
		} else if (data > root.getData()) {
			root.setRight(delete(root.getRight(), data));
		} else { // Find element

			if (root.getLeft() != null && root.getRight() != null) {
				// Replace with largest in the left sub tree
				temp = findMaximum(root.getLeft());
				root.setData(temp.getData());
				root.setLeft(delete(root.getLeft(), root.getData()));
			} else { // One Child
				temp = root;
				if (root.getLeft() == null) {
					root = root.getRight();
				}
				if (root.getRight() == null) {
					root = root.getLeft();
				}
				temp = null;
			}
		}
		return root;
	}
}
