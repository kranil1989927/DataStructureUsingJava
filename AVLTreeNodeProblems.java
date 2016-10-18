package com.learn.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTreeNodeProblems {

	private int count = 0;

	/**
	 * Given a height h, give an algorithm for generating the HB(0)
	 * Algorithm:-
	 * -----------
	 * HB(0) is nothing but generating full binary tree. In full binary tree the no. of
	 * nodes with height : 2^(h+1) - 1
	 * 
	 * T: O(n)
	 * S: O(log n) - The max. stack size which is equal to the height of tree.
	 */
	public BinarySearchTreeNode buildHBT(int h) {
		BinarySearchTreeNode temp;
		if (h == 0) {
			return null;
		}
		temp = new BinarySearchTreeNode();
		temp.setLeft(buildHBT(h - 1));
		temp.setData(count++);
		temp.setRight(buildHBT(h - 1));
		return temp;
	}
	
	// Alternative way : Using Merge sort, that means instead of working with height
	// we can take the range.
	public BinarySearchTreeNode buildHBT_MergeSort(int l, int r) {
		BinarySearchTreeNode temp;
		int mid = l + (r - l) / 2;

		if (l > r) {
			return null;
		}
		temp = new BinarySearchTreeNode();
		temp.setData(mid);
		temp.setLeft(buildHBT_MergeSort(l, mid - 1));
		temp.setLeft(buildHBT_MergeSort(mid + 1, r));
		return temp;
	}
	
	/*	
		No. of nodes in a minimal AVL tree of height h
		N(h) = 1 + N(h-1) + N(h-2)
		
		No. of different shapes of a minimal AVL tree of height h
		NS(h) = 2 * NS(h-1) * NS(h-2)
	*/
	
	/**
	 * Check whether a binary search tree is an AVL Tree or not
	 */
	public boolean isAVL(BinarySearchTreeNode root){
		if(root == null){
			return true;
		}
		
		return isAVL(root.getLeft()) && isAVL(root.getRight()) 
				&& Math.abs(getHeight(root.getLeft()) - getHeight(root.getRight())) <= 1;
	}

	private int getHeight(BinarySearchTreeNode root) {
		int leftHeight, rightHeight;
		if (root == null) {
			return 0;
		} else {
			leftHeight = getHeight(root.getLeft());
			rightHeight = getHeight(root.getRight());
			if (leftHeight > rightHeight) {
				return leftHeight - 1;
			} else {
				return rightHeight + 1;
			}
		}
	}
	
	/**
	 * Generating an AVL Tree of height h with minimum no. of nodes
	 * Approach:-
	 * To get minimum no. of nodes, fill on level with h-1 and other with h-2;
	 */
	public AVLTreeNode generateAVL(int h) {
		AVLTreeNode temp;
		if (h == 0) {
			return null;
		}

		temp = new AVLTreeNode();
		temp.setLeft(generateAVL(h - 1));
		temp.setData(count++);
		temp.setRight(generateAVL(h - 1));
		temp.setHeight(temp.getLeft().getHeight() + temp.getRight().getHeight() + 1);

		return temp;
	}
	
	/**
	 * Algorithm to count of nodes of range [a, b]
	 */
	public int rangeCount(AVLTreeNode root, int a, int b) {
		if (root == null) {
			return 0;
		} else if (root.getData() > b) {
			return rangeCount(root.getLeft(), a, b);
		} else if (root.getData() > a) {
			return rangeCount(root.getRight(), a, b);
		} else if (root.getData() >= a && root.getData() <= b) {
			return rangeCount(root.getLeft(), a, b) + rangeCount(root.getRight(), a, b);
		}
		return 0;
	}
	
	/**
	 * Remove all the half nodes (Which has one child) without touching leave nodes
	 * Approach:-
	 * ----------
	 * By using post-order traversal, we can solve the problem efficiently.
	 * We first process the left children then right children, and finally the node itself.
	 * So, we form the new bottom up, starting from leaves towards the root.
	 * By the time we process the current node, both its left and right subtrees were already 
	 * processed.
	 */
	public BinaryTreeNode removeHalfNodes(BinaryTreeNode root) {
		if (root == null) {
			return null;
		}

		root.setLeft(removeHalfNodes(root.getLeft()));
		root.setRight(removeHalfNodes(root.getRight()));

		if (root.getLeft() == null && root.getRight() == null) {
			return root;
		}
		if (root.getLeft() == null) {
			return root.getRight();
		}
		if (root.getRight() == null) {
			return root.getLeft();
		}
		return root;
	}
	
	/**
	 * Remove leaf nodes from Binary Tree
	 */
	public BinaryTreeNode removeLeafNodes(BinaryTreeNode root){
		if(root != null){
			if(root.getLeft() == null && root.getRight() == null){
				root = null;
			} else {
				root.setLeft(removeLeafNodes(root.getLeft()));
				root.setRight(removeLeafNodes(root.getRight()));
			}
		}
		return root;
	}
	
	/**
	 * Remove the element that are not in the range.
	 */
	public BinarySearchTreeNode pruneBST(BinarySearchTreeNode root, int a, int b) {
		if (root == null) {
			return null;
		}

		root.setLeft(pruneBST(root.getLeft(), a, b));
		root.setRight(pruneBST(root.getRight(), a, b));

		if (a <= root.getData() && root.getData() >= b) {
			return root;
		}
		if (root.getData() < a) {
			return root.getRight();
		}
		if (root.getData() > b) {
			return root.getLeft();
		}
		return root;
	}
	
	/**
	 * Algorithm to connect all the adjacent nodes at the same level. 
	 * Assume that given binary tree has next pointer along with left and right pointers.
	 * 
	 * Approach:-
	 * Use Level-Order-Traversal and keep updating the next pointers, while traversing
	 * we will link the nodes on next level. If the node has left and right node, we will
	 * link left to right. If node has next node, then link right most child of current
	 * node to left most child of next node.
	 */
	public void linkLevelNode(BinaryTreeNode root) {
		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		BinaryTreeNode prev, temp;
		int currentLevelNodeCount, nextLevelNodeCount;

		if (root == null) {
			return;
		}
		q.offer(root);
		currentLevelNodeCount = 1;
		nextLevelNodeCount = 0;
		prev = null;

		while (!q.isEmpty()) {
			temp = q.poll();
			if (temp.getLeft() != null) {
				q.offer(temp.getLeft());
				nextLevelNodeCount++;
			}
			if (temp.getRight() != null) {
				q.offer(temp.getRight());
				nextLevelNodeCount++;
			}

			// Link the previous node of current level to this node
			if (prev != null) {
				prev.setLeft(temp);
			}

			// set the previous node to current
			prev = temp;
			currentLevelNodeCount--;

			// If this is the last node of the current level
			if (currentLevelNodeCount == 0) {
				currentLevelNodeCount = nextLevelNodeCount;
				nextLevelNodeCount = 0;
				prev = null;
			}
		}
	}
	
	// Improved space approach
	public void linkLevelNodes(BinaryTreeNode root) {
		if (root == null) {
			return;
		}
		BinaryTreeNode rightModeNode = null, nextHead = null, temp = root;

		// Connect next level of current root node level
		while (temp != null) {
			if (temp.getLeft() != null) {
				if (rightModeNode == null) {
					rightModeNode = temp.getLeft();
					nextHead = temp.getLeft();
				} else {
					rightModeNode.setLeft(temp.getLeft());
					rightModeNode = rightModeNode.getLeft();
				}
			}

			if (temp.getRight() != null) {
				if (rightModeNode == null) {
					rightModeNode = temp.getRight();
					nextHead = temp.getRight();
				} else {
					rightModeNode.setRight(temp.getRight());
					rightModeNode = rightModeNode.getRight();
				}
			}
			
			temp = temp.getLeft();
		}
			connect(nextHead);
	}

	private void connect(BinaryTreeNode nextHead) {
		return;
	}
}

