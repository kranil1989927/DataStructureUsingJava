package com.learn.ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeTraversals {

	/**
	 * PreOrder Traversal (NLR) : 
	 * 1. Visit the root. 
	 * 2. Traverse the left subtree in preorder. 
	 * 3. Traverse the right subtree in preorder.
	 * 
	 * T : O(n) and S : O(n)
	 */
	public void preOrder(BinaryTreeNode root) {
		if (root != null) {
			System.out.println(root.getData());
			preOrder(root.getLeft());
			preOrder(root.getRight());
		}
	}

	/** Iterative Preorder Traversal
	 * -----------------------------
	 * In a recursive version a stack is required as we need to remember the current node so that
	 * after completing the left subtree, we can go to right subtree.
	 * To do the same, first we process the current node and before going to left subtree, we store
	 * the current node on stack. After completing the left subtree processing, pop the element
	 * and go to its right subtree. continue this process until stack is non empty.
	 */
	public ArrayList<Integer> preOrderTraversal(BinaryTreeNode root) {

		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null) {
			return res;
		}
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		stack.push(root);

		while (!stack.isEmpty()) {
			BinaryTreeNode temp = stack.pop();
			res.add(temp.getData());

			// Pay more attention to this sequence so that left node should be
			// first one in the stack
			if (temp.getRight() != null) {
				stack.push(temp.getRight());
			}

			if (temp.getLeft() != null) {
				stack.push(temp.getLeft());
			}
		}
		return res;
	}
	
	/**
	 * In Order Traversal (LNR)
	 * 1. Traverse the left subtree in inorder. 
	 * 2. Visit the root.
	 * 3. Traverse the right subtree in inorder.
	 * 
	 * T : O(n) and S : O(n)
	 */
	public void inOrder(BinaryTreeNode root) {
		if (root != null) {
			inOrder(root.getLeft());
			System.out.println(root.getData());
			inOrder(root.getRight());
		}
	}
	
	/** Iterative Inorder Traversal
	 * -----------------------------
	 * Similar to Pre Order, the only change is, instead of processing the node before going to left subtree
	 * process it after the poping ( which indicates after completion of left sub tree processing)
	 */
	public ArrayList<Integer> inOrderTraversal(BinaryTreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		if (root == null) {
			return result;
		}

		BinaryTreeNode currentNode = root;
		boolean done = false;

		while (!done) {
			if (currentNode != null) {
				stack.push(currentNode);
				currentNode = currentNode.getLeft();
			} else {
				if (stack.isEmpty()) {
					done = true;
				} else {
					currentNode = stack.pop();
					result.add(currentNode.getData());
					currentNode = currentNode.getRight();
				}
			}
		}
		return result;
	}
	
	/**
	 * Post Order Traversal (LRN)
	 * 1. Traverse the left subtree in postorder. 
	 * 2. Traverse the right subtree in postorder.
	 * 3. Visit the root.
	 * 
	 * T : O(n) and S : O(n)
	 */
	public void postOrder(BinaryTreeNode root) {
		if (root != null) {
			postOrder(root.getLeft());
			postOrder(root.getRight());
			System.out.println(root.getData());
		}
	}
	
	/**
	 * Iterative Post Order Traversal
	 * ------------------------------
	 * In preorder and inorder traversals, after popping the stack element we don't need to visit the same vertex again.
	 * But in post order traversal, each node is visited twice. That means, after processing left subtree we will visit the current
	 * node and after processing right subtree we will visit the same current node.
	 * 
	 * After popping the element from the stack, check whether that element and right of the top of the stack are same or not.
	 * If they are same, then we have completed the process of left subtree and right subtree.
	 * 
	 * In this case, we just need to pop the stack one more time and prints its data.
	 */
	public ArrayList<Integer> postOrderTraversal(BinaryTreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		stack.push(root);
		BinaryTreeNode prev = null;

		while (!stack.isEmpty()) {
			BinaryTreeNode curr = stack.peek();
			if (prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
				// Traverse from top to bottom, and if curr has left and right
				// child push into the stack; otherwise pop out.

				if (curr.getLeft() != null) {
					stack.push(curr.getLeft());
				} else if (curr.getRight() != null) {
					stack.push(curr.getRight());
				}
			} else if (curr.getLeft() == prev) {
				if (curr.getRight() != null) {
					stack.push(curr.getRight());
				}
			} else {
				result.add(curr.getData());
				stack.pop();
			}
			prev = curr;
		}
		return result;
	}
	
	/**
	 * Level Order Traversal
	 * ---------------------
	 * 1. Visit the root.
	 * 2. While traversing level 1, keep all the elements at level 1+1 in queue.
	 * 3. Go to next level and visit all the nodes at that level.
	 * 4. Repeat this until all levels are completed.
	 */
	public ArrayList<ArrayList<Integer>> levelOrder(BinaryTreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (root == null) {
			return result;
		}

		// Initialization
		Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
		queue.offer(root);
		queue.offer(null);

		ArrayList<Integer> curr = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			BinaryTreeNode tmp = queue.poll();
			
			// Level is separated by null
			if (tmp != null) {
				curr.add(tmp.getData());
				if (tmp.getLeft() != null) {
					queue.offer(tmp.getLeft());
				}
				if (tmp.getRight() != null) {
					queue.offer(tmp.getRight());
				}
			} else {
				ArrayList<Integer> c_curr = new ArrayList<Integer>(curr);
				result.add(c_curr);
				curr.clear(); // Clear all the java references
				// Completion of level
				if (!queue.isEmpty()) {
					queue.offer(null);
				}
			}
		}
		return result;
	}
}
