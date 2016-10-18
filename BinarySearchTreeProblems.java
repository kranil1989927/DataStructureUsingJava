package com.learn.ds.tree;

import com.learn.ds.linkedList.LinkedListNode;

public class BinarySearchTreeProblems {

	/**
	 * Given pointers to two nodes in a binary search tree. 
	 * Find the Lowest Common Ancestor (LCA). Assume that both values already exist in the tree.
	 * Approach :
	 * ----------
	 * While traversing BST from root to bottom, the first node we encounter with value between
	 * a and b i.e. a < node.data is the LCA of a and b (where a < b).
	 * 
	 * So, just traverse the BST in pre-order. If we find node with value in between a and b,
	 * then that node is LCA.
	 * If its value is greater than both a and b, then LCA lies on left side of the node and if 
	 * its value is smaller than both a and b, then LCA lies on right side.
	 */
	public BinarySearchTreeNode LCA(BinarySearchTreeNode root, BinarySearchTreeNode a, 
			BinarySearchTreeNode b) {
		if (root == null) {
			return root;
		}

		if (root == a || root == b) {
			return root;
		}

		if (Math.max(a.getData(), b.getData()) < root.getData()) {
			return LCA(root.getLeft(), a, b);
		} else if (Math.max(a.getData(), b.getData()) > root.getData()) {
			return LCA(root.getRight(), a, b);

		} else {
			return root;
		}
	}
	
	/**
	 * Algorithm to check whether the given binary tree is BST or not.
	 */
	public boolean isBST(BinarySearchTreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.getLeft() != null 
				&& BinarySearchTreeNode.findMaximum(root.getLeft()).getData() > root.getData()) {
			return false;
		}
		if (root.getRight() != null 
				&& BinarySearchTreeNode.findMaximum(root.getRight()).getData() < root.getData()) {
			return false;
		}

		if (!isBST(root.getLeft()) || !isBST(root.getRight())) {
			return false;
		}
		return true;
	}
	
	// Better Solution
	public boolean isBST_1(BinarySearchTreeNode root, int max, int min) {
		if (root == null) {
			return true;
		}
		return (root.getData() > min && root.getData() < max && isBST_1(root.getLeft(), min, root.getData())
				&& isBST_1(root.getRight(), root.getData(), max));
	}
	
	/*
	 * Another Best Approach 
	 * By using inorder traversal. The idea behind this
	 * solution is that, inorder traversal of BST produces sorted list. While
	 * traversing the BST inorder, at each node check the condition that its key
	 * value should be greater than the key value of its previous visited node.
	 * Also we need to initialize the previous with possible minimum integer
	 * value(say Integer.MIN_VALUE)
	 */
	private int prev = Integer.MIN_VALUE;
	public boolean checkValidBST(BinarySearchTreeNode root) {
		return isBST_2(root);
	}

	private boolean isBST_2(BinarySearchTreeNode root) {
		if (root == null) {
			return true;
		}
		if (!isBST_2(root)) {
			return false;
		}
		if (root.getData() < prev) {
			return false;
		}
		prev = root.getData();
		return isBST_2(root.getRight());
	}
	
	/**
	 * Converting BST to Circular Linked List Doubly with s : O(1)
	 * Convert left and right subtrees to DLL's and maintain end of those lists.
	 */
	public BinarySearchTreeNode BST2DLL(BinarySearchTreeNode root, BinarySearchTreeNode leftTail) {
		BinarySearchTreeNode left, lTail = null, right, rTail = null;
		if (root == null) {
			lTail = null;
			return null;
		}
		left = BST2DLL(root.getLeft(), lTail);
		right = BST2DLL(root.getRight(), rTail);
		root.setLeft(lTail);
		root.setRight(rTail);

		if (right == null) {
			lTail = root;
		} else {
			right.setLeft(root);
			lTail = right;
		}

		if (left == null) {
			return root;
		} else {
			lTail.setRight(root);
			return left;
		}
	}
	
	/**
	 * Given a sorted DLL. given an algorithm for converting it into balanced Binary Search Tree.
	 * 
	 * Algorithm:-
	 * Find the list length and construct bottom-up approach
	 */
	public BinarySearchTreeNode sortedListToBST(LinkedListNode head) {
		int len = 0;
		LinkedListNode curr = head;
		while (curr != null) {
			len++;
			curr = curr.getNext();
		}

		return construct(head, 0, len - 1);
	}

	private BinarySearchTreeNode construct(LinkedListNode head, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = start + (end - start) / 2;

		// Build left first, since it is bottom up approach
		BinarySearchTreeNode left = construct(head, start, mid - 1);

		BinarySearchTreeNode root = new BinarySearchTreeNode(head.getData());
		root.setLeft(left);
		if (head.getNext() != null) {
			head.setData(head.getNext().getData());
			head.setNext(head.getNext().getNext());
		}
		root.setRight(construct(head, mid + 1, end));
		return root;
	}
	
	/**
	 * Algorithm for converting the array to BST.
	 */
	@SuppressWarnings("unused")
	public BinarySearchTreeNode buildBST(int[] a, int left, int right) {
		BinarySearchTreeNode newNode = null;
		if (left > right) {
			return null;
		}
		newNode = new BinarySearchTreeNode();
		if (newNode == null) {
			System.out.println("Memory error");
			return null;
		}

		if (left == right) {
			newNode.setData(a[left]);
		} else {
			int mid = left + (right - left) / 2;
			newNode.setData(a[mid]);
			newNode.setLeft(buildBST(a, left, mid - 1));
			newNode.setRight(buildBST(a, mid + 1, right));
		}
		return newNode;
	}
	
	/**
	 * Singly LinkedList (sorted in ascending order) convert it to height balanced BST.
	 */
	public BinaryTreeNode sortedListToBST(LinkedListNode head, int n) {
		return sortedListToBST(head, 0, n - 1);
	}

	@SuppressWarnings("unused")
	private BinaryTreeNode sortedListToBST(LinkedListNode list, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = start + (end - start) / 2;
		BinaryTreeNode leftChild = sortedListToBST(list, start, mid - 1);
		BinaryTreeNode parent = new BinaryTreeNode();
		if (parent == null) {
			System.out.println("Memory Error");
			return null;
		}

		parent.setData(list.getData());
		parent.setLeft(leftChild);
		list = list.getNext();
		parent.setRight(sortedListToBST(list, mid + 1, end));

		return parent;
	}
	
	/**
	 * Algorithm for finding Kth element in BST.
	 * Using Inorder traversal of BST produces sorted lists. While traversing the BST in inorder, keep track
	 * of number of element visited.
	 */
	public BinarySearchTreeNode kthSmallestInBST(BinarySearchTreeNode root, int k, int count) {
		if (root == null) {
			return null;
		}

		BinarySearchTreeNode left = kthSmallestInBST(root.getLeft(), k, count);
		if (left != null) {
			return left;
		}
		if (++count == k) {
			return root;
		}

		return kthSmallestInBST(root.getRight(), k, count);
	}
	
	/**
	 * Given BST and two numbers k1 nad k2, give an algorithm for printing all the elements of BST
	 * in the range k1 and k2.
	 */
	public void printRange(BinarySearchTreeNode root, int k1, int k2) {
		if (root == null) {
			return;
		}
		if (root.getData() >= k1) {
			printRange(root.getLeft(), k1, k2);
		}
		if (root.getData() >= k1 && root.getData() <= k2) {
			System.out.println(root.getData());
		}
		if (root.getData() <= k2) {
			printRange(root.getRight(), k1, k2);
		}
	}
}
