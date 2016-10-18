package com.learn.ds.tree;

public class GenericTreeNodeProblems {

	/**
	 * Algorithm for finding the sum of the elements of the tree
	 */
	public int findSum(GenericTreeNode root) {
		if (root == null) {
			return 0;
		}
		return root.getData() + findSum(root.getFirstChild()) + findSum(root.getNextSibling());
	}
	
	/**
	 * Given a parent array P, where P[i] indicates the parent of i th node in the tree (Assume parent
	 * of the root node is indicated with -1). 
	 * Give an algorithm for finding the height or depth of the tree.
	 * 
	 * For example : P = {-1, 0, 1, 6, 6, 0, 0, 2, 7}
	 * 
	 * Approach :-
	 * We need to start to every node and keep going to its parent until we reach -1
	 * and also keep track of the maximum depth among all nodes. 
	 */
	public int findDepthInGenericTree(int[] p) {
		int maxDepth = -1;
		int currDepth = -1;
		int j = 0;
		for (int i = 0; i < p.length; i++) {
			currDepth = 0;
			j = i;
			while (p[j] != -1) {
				currDepth++;
				j = p[j];
			}

			if (currDepth > maxDepth) {
				maxDepth = currDepth;
			}
		}
		return maxDepth;
	}
	
	/**
	 * For a node in Generic tree, find the number of siblings for that node
	 */
	public int siblingsCount(GenericTreeNode current) {
		if (current == null) {
			return 0;
		}
		int count = 0;
		while (current != null) {
			count++;
			current = current.getNextSibling();
		}
		return count;
	}
	
	/**
	 * Check whether the tree is an isomorphic or not.
	 */
	public boolean isIsomorphic(BinaryTreeNode r1, BinaryTreeNode r2) {
		if (r1 == null && r2 == null) {
			return true;
		}
		if ((r1 == null && r2 != null) || (r1 != null && r2 == null)) {
			return false;
		}
		return (isIsomorphic(r1.getLeft(), r2.getLeft())) && (isIsomorphic(r1.getRight(), r2.getRight()));
	}
	
	/**
	 * Algorithm for counting no. of children for the given node in the generic tree.
	 */
	public int childCount(GenericTreeNode current) {
		int count = 0;
		current = current.getFirstChild();
		while (current != null) {
			count++;
			current = current.getNextSibling();
		}
		return count;
	}
}
