package com.learn.ds.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeProblem {

	/**
	 * Algorithm for finding maximum element in a binary tree
	 * 1. Find the maximum element in left subtree.
	 * 2. Find the maximum element in right subtree.
	 * 3. Compare them with root adta and select the one which have the maximum value.
	 * 
	 * T : O(n) and S : O(n)
	 */
	public int maxInBinaryTree(BinaryTreeNode root) {

		int maxValue = Integer.MAX_VALUE;
		if (root != null) {
			int leftMax = maxInBinaryTree(root.getLeft());
			int rightMax = maxInBinaryTree(root.getRight());

			if (leftMax > rightMax) {
				maxValue = leftMax;
			} else {
				maxValue = rightMax;
			}

			if (root.getData() > maxValue) {
				maxValue = root.getData();
			}
		}
		return maxValue;
	}
	
	/**
	 * Find maximum element in the binary tree without recursion
	 * Algorithm: Using Level Order traversal : just observe the elements data while deleting.
	 */
	public int maxInBinaryTreeLevelOrder(BinaryTreeNode root) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}

		int max = Integer.MIN_VALUE;

		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);

		while (!q.isEmpty()) {
			BinaryTreeNode temp = q.poll();
			if (temp.getData() > max) {
				max = temp.getData();
			}

			if (temp != null) {
				if (temp.getLeft() != null) {
					q.offer(temp.getLeft());
				}
				if (temp.getRight() != null) {
					q.offer(temp.getRight());
				}
			}
		}
		return max;
	}
	
	/**
	 * Searching an element in binary tree.
	 */
	public static boolean findInBT(BinaryTreeNode root, int data) {
		if (root == null) {
			return false;
		}
		if (root.getData() == data) {
			return true;
		}
		return findInBT(root.getLeft(), data) || findInBT(root.getRight(), data);
	}
	
	/**
	 * Searching an element in binary tree using without recursion.
	 * We can use level order traversal for solving this problem
	 */
	public boolean findInBTterative(BinaryTreeNode root, int data) {
		if (root == null) {
			return false;
		}

		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			BinaryTreeNode temp = q.poll();
			if (temp.getData() == data) {
				return true;
			}
			if (temp != null) {
				if (temp.getLeft() != null) {
					q.offer(temp.getLeft());
				}
				if (temp.getRight() != null) {
					q.offer(temp.getRight());
				}
			}
		}
		return false;
	}
	
	/**
	 * Insert an element in the Binary tree
	 * Approach : We can use level order traversal and insert the element where ever we found a
	 * node whose left and right child is null.
	 */
	public BinaryTreeNode insertBinaryTreeLevelOrder(BinaryTreeNode root, int data) {
		if (root == null) {
			return null;
		}
		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);

		while (!q.isEmpty()) {
			BinaryTreeNode temp = q.poll();
			if (temp != null) {
				if (temp.getLeft() != null) {
					q.offer(temp.getLeft());
				} else {
					temp.setLeft(new BinaryTreeNode(data));
					return root;
				}

				if (temp.getRight() != null) {
					q.offer(temp.getRight());
				} else {
					temp.setRight(new BinaryTreeNode(data));
					return root;
				}
			}
		}
		return root;
	}
	
	/** Iterative Method to insert the element into binary tree */
	public void insert(BinaryTreeNode root, int data) {
		if (root == null) {
			root = new BinaryTreeNode(data);
		} else {
			insertHelper(root, data);
		}
	}

	private void insertHelper(BinaryTreeNode root, int data) {
		if (root.getData() >= data) {
			if (root.getLeft() == null) {
				root.setLeft(new BinaryTreeNode(data));
			} else {
				insertHelper(root.getLeft(), data);
			}
		} else {
			if (root.getRight() == null) {
				root.setRight(new BinaryTreeNode(data));
			} else {
				insertHelper(root.getRight(), data);
			}
		}
	}
	
	/** Finding the size of binary tree */
	public int sizeOfBinaryTree(BinaryTreeNode root) {
		int leftCount = root.getLeft() == null ? 0 : sizeOfBinaryTree(root.getLeft());
		int rightCount = root.getRight() == null ? 0 : sizeOfBinaryTree(root.getRight());
		return 1 + leftCount + rightCount;
	}
	
	/** Iterative Approach */
	public int sizeOfBT(BinaryTreeNode root) {
		int count = 0;
		if (root == null) {
			return count;
		}

		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			BinaryTreeNode temp = q.poll();
			count++;
			if (temp.getLeft() != null) {
				q.offer(temp.getLeft());
			}
			if (temp.getRight() != null) {
				q.offer(temp.getRight());
			}
		}
		return count;
	}
	
	/** Printing level order in reverse order */
	public static void levelOrderTravelInReverese(BinaryTreeNode root) {
		if (root == null) {
			return;
		}
		Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);

		while (!q.isEmpty()) {
			BinaryTreeNode temp = q.poll();
			if (temp.getLeft() != null) {
				q.offer(temp.getLeft());
			}
			if (temp.getRight() != null) {
				q.offer(temp.getRight());
			}
			s.push(temp);
		}

		while (!s.isEmpty()) {
			System.out.println(s.pop().getData());
		}
	}
	
	/** Algorithm to find the height of the binary tree */
	public int maxDepthRecursive(BinaryTreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftDepth = maxDepthRecursive(root.getLeft());
		int rightDepth = maxDepthRecursive(root.getRight());

		return (leftDepth > rightDepth) ? leftDepth + 1 : rightDepth + 1;
	}
	
	/** Algorithm to find the height of the binary tree - Non - Recursive using Stack */
	public int maxDepthIterative(BinaryTreeNode root) {

		if (root == null) {
			return 0;
		}
		Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();
		s.push(root);

		int maxDepth = 0;
		BinaryTreeNode prev = null;

		while (!s.isEmpty()) {
			BinaryTreeNode curr = s.peek();
			if (prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
				if (curr.getLeft() != null) {
					s.push(curr.getLeft());
				} else if (curr.getRight() != null) {
					s.push(curr.getRight());
				}
			} else if (curr.getLeft() == null) {
				if (curr.getRight() != null) {
					s.push(curr.getRight());
				}
			} else {
				s.pop();
			}

			prev = curr;
			if (s.size() > maxDepth) {
				maxDepth = s.size();
			}
		}
		return maxDepth;
	}
	
	/** Algorithm to find the height of the binary tree - Non - Recursive 
	 *  Using Level Order Traversal 
	 */
	public int minDepth(BinaryTreeNode root) {
		if (root == null) {
			return 0;
		}

		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		q.offer(null);

		int count = 1;
		while (!q.isEmpty()) {
			BinaryTreeNode curr = q.poll();
			if (curr != null) {
				if (curr.getLeft() == null && curr.getRight() == null) {
					return count;
				}

				if (curr.getLeft() != null) {
					q.offer(curr.getLeft());
				}
				if (curr.getRight() != null) {
					q.offer(curr.getRight());
				}
			} else {
				if (!q.isEmpty()) {
					count++;
					q.offer(null);
				}
			}
		}
		return count;
	}
	
	/**
	 * Deepest node in the binary tree 
	 * The last node processed from queue in level order is the deepest node in the binary tree.  
	 */
	public BinaryTreeNode deepestNodeInBinaryTree(BinaryTreeNode root) {
		BinaryTreeNode tmp = null;
		if (root == null) {
			return null;
		}

		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			tmp = q.poll();
			if (tmp.getLeft() != null) {
				q.offer(tmp.getLeft());
			}
			if (tmp.getRight() != null) {
				q.offer(tmp.getRight());
			}
		}
		return tmp;
	}
	
	/**
	 * Algorithm for finding the no. of leaves node in the binary tree
	 */
	public int noOfLeavesInBTUsingLevelOrder(BinaryTreeNode root) {
		int count = 0;
		if (root == null) {
			return count;
		}

		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			BinaryTreeNode tmp = q.poll();
			if (tmp.getLeft() == null && tmp.getRight() == null) {
				count++;
			}

			if (tmp.getLeft() != null) {
				q.offer(tmp.getLeft());
			}
			if (tmp.getRight() != null) {
				q.offer(tmp.getRight());
			}
		}
		return count;
	}
	
	/**
	 * Algorithm for finding the number of full nodes in the binary tree.
	 * A set of all nodes with both the left and right children are full nodes
	 */
	public int noOfFullNodesInBTUsingLevelOrder(BinaryTreeNode root) {
		int count = 0;
		if (root == null) {
			return count;
		}
		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			BinaryTreeNode tmp = q.poll();
			if (tmp.getLeft() != null && tmp.getRight() != null) {
				count++;
			}

			if (tmp.getLeft() != null) {
				q.offer(tmp.getLeft());
			}

			if (tmp.getRight() != null) {
				q.offer(tmp.getRight());
			}
		}
		return count;
	}
	
	/**
	 * Algorithm for finding the number of half nodes (nodes with only one child)
	 * in the binary tree
	 * The set of all the nodes with either left or either right child (but not both)
	 * are called half nodes.
	 */
	public int noOfHalfNodesInBTUsingLevelOrder(BinaryTreeNode root) {
		int count = 0;
		if (root == null) {
			return count;
		}

		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			BinaryTreeNode tmp = q.poll();
			if ((tmp.getLeft() == null && tmp.getRight() != null)
					|| (tmp.getLeft() != null && tmp.getRight() == null)) {
				count++;
			}
			if (tmp.getLeft() != null) {
				q.offer(tmp.getLeft());
			}
			if (tmp.getRight() != null) {
				q.offer(tmp.getRight());
			}
		}
		return count;
	}
	
	/**
	 * Is two binary tree are structurally Identical.
	 */
	public boolean checkStructurallySameTrees(BinaryTreeNode root1, BinaryTreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}

		if (root1 == null || root2 == null) {
			return false;
		}

		return (checkStructurallySameTrees(root1.getLeft(), root2.getRight())
				&& checkStructurallySameTrees(root1.getRight(), root2.getLeft()));
	}
	
	/** 
	 * Algorithm for finding the diameter of the binary tree. The diameter of tree (sometimes 
	 * called the width ) is the no. of nodes on the longest path between two leaves in the tree.
	 * Algorithm :-
	 * To find the diameter of the tree, first calculate the diameter of left subtree and
	 * right subtree recursively.
	 * Among these values, we need to send maximum values along with current level (+1) 
	 */
	public int diameterOfTree(BinaryTreeNode root) {
		int left, right, diameter = 0;
		if (root == null) {
			return 0;
		}
		left = diameterOfTree(root.getLeft());
		right = diameterOfTree(root.getRight());
		if (left + right > diameter) {
			diameter = left + right;
		}

		return Math.max(left, right) + 1;
	}
	
	// Alternative Approach
	public int diameterOfBT(BinaryTreeNode root) {
		if (root == null) {
			return 0;
		}

		// Pass goes through the root
		int len1 = height(root.getLeft()) + height(root.getRight()) + 3;

		// Path does not pass the root
		int len2 = Math.max(diameterOfBT(root.getLeft()), diameterOfBT(root.getRight()));

		return Math.max(len1, len2);
	}
	
	private int height(BinaryTreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftDepth = height(root.getLeft());
		int rightDepth = height(root.getRight());
		return (leftDepth > rightDepth) ? leftDepth + 1 : rightDepth + 1;
	}
	
	/**
	 * Algorithm for finding the width of binary tree. The diameter of the tree is the max. no. of
	 * nodes at any level in the tree.
	 */
	public int width(BinaryTreeNode root) {
		int max = 0;
		if (root == null) {
			return 0;
		}

		int height = maxDepthRecursive(root);
		for (int i = 0; i < height; i++) {
			int nodesCount = width(root, i);
			if (nodesCount > max) {
				max = nodesCount;
			}
		}
		return max;
	}

	private int width(BinaryTreeNode root, int depth) {
		if (root == null) {
			return 0;
		} else {
			if (depth == 0) {
				return 1;
			} else {
				return width(root.getLeft(), depth - 1) + width(root.getRight(), depth - 1);
			}
		}
	}
	
	/**
	 * Algorithm for finding a level that has the maximum sum in the binary tree.
	 * Algorithm : The logic is very similar to find no. of levels. The only change is, we 
	 * need to keep track of sums as well.
	 */
	public int findLevelWithSum(BinaryTreeNode root) {
		int maxSum = 0, currentSum = 0;
		if (root == null) {
			return 0;
		}
		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		q.offer(null);

		while (!q.isEmpty()) {
			BinaryTreeNode temp = q.poll();
			if (temp != null) {
				if (temp.getLeft() != null) {
					q.offer(temp.getLeft());
				}

				if (temp.getRight() != null) {
					q.offer(temp.getRight());
				}
			} else {
				if (currentSum > maxSum) {
					maxSum = currentSum;
				}
				currentSum = 0;
				// Completion of level
				if (!q.isEmpty()) {
					q.offer(null);
				}
			}
		}
		return maxSum;
	}
	
	/**
	 * Print out all its root-to-leaf paths
	 */
	public void printPaths(BinaryTreeNode root) {
		int[] path = new int[256];
		printPaths(root, path, 0);
	}

	private void printPaths(BinaryTreeNode root, int[] path, int pathLen) {
		if (root == null) {
			return;
		}
		// Append this node to the path array
		path[pathLen] = root.getData();
		pathLen++;

		// It's a leaf, so print the path that lead to there
		if (root.getLeft() == null && root.getRight() == null) {
			printArray(path, pathLen);
		} else {
			printPaths(root.getLeft(), path, pathLen);
			printPaths(root.getRight(), path, pathLen);
		}
	}

	private void printArray(int[] paths, int len) {
		for (int i = 0; i < len; i++) {
			System.out.println(paths[i] + " ");
		}
		System.out.println();

	}
	
	/**
	 * Algorithm for checking the existence of path with given sum
	 * Approach : Subtract the node values from the sum before calling its children recursively.
	 * And check to see if the sum is 0 when we run out of tree. 
	 */
	public boolean hasPathSum(BinaryTreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		if (root.getLeft() == null && root.getRight() == null && root.getData() == sum) {
			return true;
		} else {
			return hasPathSum(root.getLeft(), sum - root.getData())
					|| hasPathSum(root.getRight(), sum - root.getData());
		}
	}
	
	/**
	 * Find the sum of all the elements in  binary tree
	 */
	public int addBT(BinaryTreeNode root) {
		if (root == null) {
			return 0;
		}

		return addBT(root.getLeft()) + addBT(root.getRight()) + root.getData();
	}
	
	// Non Recursive approach
	public int addSumBT(BinaryTreeNode root) {
		int sum = 0;
		if (root == null) {
			return 0;
		}

		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			BinaryTreeNode temp = q.poll();
			sum = sum + temp.getData();

			if (temp.getLeft() != null) {
				q.offer(temp.getLeft());
			}

			if (temp.getRight() != null) {
				q.offer(temp.getRight());
			}
		}
		return sum;
	}
	
	/**
	 * Algorithm to converting a tree to its mirror.
	 * Mirror of the tree is another tree with left and right children for all
	 * non-leaf nodes interchanged
	 */
	public BinaryTreeNode mirrorOfBT(BinaryTreeNode root) {
		BinaryTreeNode temp;
		if (root != null) {
			mirrorOfBT(root.getLeft());
			mirrorOfBT(root.getRight());

			temp = root.getLeft();
			root.setLeft(root.getRight());
			root.setRight(temp);
		}
		return root;
	}
	
	/**
	 * Algorithm to check whether two trees are mirror to each other.
	 */
	public boolean areMirrors(BinaryTreeNode root1, BinaryTreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null) {
			return false;
		}
		return areMirrors(root1.getLeft(), root2.getRight()) && areMirrors(root1.getRight(), root2.getLeft());
	}

	/**
	 * Construct Binary tree from given Inorder and Preorder traversal
	 */
	public BinaryTreeNode buildBinaryTree(int[] preorder, int[] inorder) {
		if (preorder.length == 0 || inorder.length != preorder.length) {
			return null;
		}
		return buildBT(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
	}

	private BinaryTreeNode buildBT(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
		
		if(preStart > preEnd || inStart > inEnd){
			return null;
		}
		int data = preorder[preStart];
		BinaryTreeNode curr = new BinaryTreeNode(data);
		int offset = inStart;
		for (; offset < inEnd; offset++) {
			if (inorder[offset] == data) {
				break;
			}
		}
		
		curr.setLeft(buildBT(preorder, preStart + 1, preStart + offset - inStart, inorder, inStart, offset - 1));
		curr.setRight(buildBT(preorder, preStart + offset - inStart + 1, preEnd, inorder, offset + 1, inEnd));
		return curr;
	}
	
	/**
	 * Construct BT from given inorder and postorder traversal
	 */
	public BinaryTreeNode buildBinaryTreeFromInOrder(int[] postorder, int[] inorder) {
		if (postorder.length == 0 || inorder.length != postorder.length) {
			return null;
		}
		return buildBTFromInOrder(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
	}

	private BinaryTreeNode buildBTFromInOrder(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart,
			int inEnd) {
		if (postStart > postEnd || inStart > inEnd) {
			return null;
		}
		int data = postorder[postStart];
		BinaryTreeNode curr = new BinaryTreeNode(data);
		int offset = inStart;
		for (; offset < inEnd; offset++) {
			if (inorder[offset] == data) {
				break;
			}
		}

		curr.setLeft(buildBT(postorder, postStart, postStart + offset - inStart - 1, inorder, inStart, offset - 1));
		curr.setRight(buildBT(postorder, postStart + offset - inStart + 1, postEnd - 1, inorder, offset + 1, inEnd));
		return curr;
	}
	
	/**
	 * Algorithm for printing all the ancestors of the node in a binary tree.
	 */
	public static boolean printAllAncestors(BinaryTreeNode root, BinaryTreeNode node) {
		if (root == null) {
			return false;
		}
		if (root.getLeft() == node || root.getRight() == node || printAllAncestors(root.getLeft(), node)
				|| printAllAncestors(root.getRight(), node)) {
			System.out.println(root.getData());
			return true;
		}
		return false;
	}

	/**
	 * Zig Zag traversal : -We are using two stacks
	 */
	public ArrayList<ArrayList<Integer>> zigZagLevelOrder(BinaryTreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (root == null) {
			return res;
		}

		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		q.offer(null);

		boolean leftToRight = true;
		ArrayList<Integer> curr = new ArrayList<Integer>();
		while (!q.isEmpty()) {
			BinaryTreeNode temp = q.poll();
			if (temp != null) {
				curr.add(temp.getData());
				if (temp.getLeft() != null) {
					q.offer(temp.getLeft());
				}

				if (temp.getRight() != null) {
					q.offer(temp.getRight());
				}
			} else {
				if (leftToRight) {
					ArrayList<Integer> c_curr = new ArrayList<Integer>(curr);
					res.add(c_curr);
					curr.clear();
				} else {
					Stack<Integer> s = new Stack<Integer>();
					s.addAll(curr);
					ArrayList<Integer> c_curr = new ArrayList<Integer>();
					while (!s.isEmpty()) {
						c_curr.add(s.pop());
					}
					res.add(c_curr);
					curr.clear();
				}

				if (!q.isEmpty()) {
					q.offer(null);
					leftToRight = !leftToRight;
				}
			}
		}
		return res;
	}
	
	/**
	 * Find the vertical sum of a binary tree
	 */
	public static void verticalSum(HashMap<Integer, Integer> hashMap, BinaryTreeNode root, int c) {
		if (root.getLeft() != null) {
			verticalSum(hashMap, root.getLeft(), c - 1);
		}
		if (root.getRight() != null) {
			verticalSum(hashMap, root.getRight(), c + 1);
		}
		int data = 0;
		if (hashMap.containsKey(c)) {
			data = hashMap.get(c);
		}
		hashMap.put(c, data + root.getData());
	}
	
	/**
	 * Possible Binary Tree with n nodes
	 */
	public int noOfBSTs(int n) {
		int[] count = new int[n + 1];
		count[0] = 1;
		count[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				count[i] += count[j] * count[i - j - 1];
			}
		}
		return count[n];
	}
	
	// Alternative Approch
	public ArrayList<BinaryTreeNode> generateTrees(int n){
		if(n==0){
			return generateTrees(1, 0);
		}
		return generateTrees(1, n);
	}

	private ArrayList<BinaryTreeNode> generateTrees(int start, int end) {
		ArrayList<BinaryTreeNode> subTrees = new ArrayList<BinaryTreeNode>();
		if (start > end) {
			subTrees.add(null);
			return subTrees;
		}

		for (int i = start; i < end; i++) {
			for (BinaryTreeNode left : generateTrees(start, i - 1)) {
				for (BinaryTreeNode right : generateTrees(i + 1, end)) {
					BinaryTreeNode aTree = new BinaryTreeNode(i);
					aTree.setLeft(left);
					aTree.setRight(right);
					subTrees.add(aTree);
				}
			}
		}
		return subTrees;
	}
	
	/**
	 * Build tree from a given preorder string --> ILILL 
	 * I - Internal, L - Leaf Node
	 */
	public BinaryTreeNode binaryTreeFromPreorder(char[] a, int i) {
		if (a == null) {
			return null;
		}
		if (a.length == i) {
			return null;
		}

		BinaryTreeNode newNode = new BinaryTreeNode();
		newNode.setData(a[i]);

		if (a[i] == 'L') {
			return newNode;
		}

		i = i + 1;
		newNode.setLeft(binaryTreeFromPreorder(a, i));
		i = i + 1;
		newNode.setRight(binaryTreeFromPreorder(a, i));
		return newNode;
	}
}
