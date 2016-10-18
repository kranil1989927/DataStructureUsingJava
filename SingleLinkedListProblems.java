package com.learn.ds.linkedList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SingleLinkedListProblems {

	/*
	 * Find the nth node from the end of the Linked List 
	 * 
	 * Concept : 
	 * -------
	 * Use two pointers pnthNode and pTemp, Initially both points to head node of the
	 * list. pnthNode starts moving only after pTemp made N moves. From there
	 * both moves forward until pTemp reaches end of the list. As a result
	 * pnthNode points to nth node from end of the linked list
	 * 
	 * Time complexity : o(n) and space complexity: o(1)
	 */
	public LinkedListNode nthNodefromEnd(LinkedListNode head, int nthNode) {
		LinkedListNode pnthNode = head;
		LinkedListNode pTemp = head;

		// pTemp moves nth position
		for (int i = 0; i < nthNode; i++) {
			pTemp = pTemp.getNext();
		}

		// Both pTemp and pnthNode until pTemp is null
		while (pTemp != null) {
			if (pnthNode == null) {
				return head;
			} else {
				pnthNode = pnthNode.getNext();
				pTemp = pTemp.getNext();
			}
		}

		if (pnthNode != null) {
			return pnthNode;
		}
		return null;
	}
	
	/*
	 * Recursion solution -- Time complexity : o(n) and space complexity: o(1)
	 */
	public LinkedListNode nthNodeFromEndUsingRecursion(LinkedListNode head, int n) {
		int counter = 0;
		if (head != null) {
			this.nthNodefromEnd(head.getNext(), n);
			counter++;
			if (n == counter) {
				return head;
			}
		}
		return null;
	}
	
	/* Find the loop in the linked list:
	 * Concept :
	 * --------
	 * We will use slowPtr which move one position at a time and fastPtr which moves 2 positions at a time.
	 * It there were in loop then they would point same node. 
	 * 
	 * Time complexity : o(n) and space complexity: o(1)
	 */
	public static boolean findIfLoopExistsUsingFloyds(LinkedListNode head) {

		LinkedListNode slowPtr = head;
		LinkedListNode fastPtr = head;

		while (fastPtr != null && fastPtr.getNext() != null) {
			slowPtr = slowPtr.getNext();
			fastPtr = fastPtr.getNext().getNext();

			if (slowPtr == fastPtr) {
				return true;
			}
		}
		return false;
	}
	
	/* Find whether the given linked list is either NULL-terminated or not. If there is a cycle find the start node of the loop.
	 * Approach:
	 * ---------
	 * After finding the loop nin the linked list, we initialize the slowPtr to head of the linked list. From that point onwards both 
	 * slowPtr and fastPtr only moves one node at a time. The point at which they meet is the start of the loop.
	 * Generally we used this method for removing the loops.
	 */ 
	public static LinkedListNode findBeginOfLoop(LinkedListNode head) {
		LinkedListNode fastPtr = head;
		LinkedListNode slowPtr = head;
		boolean loopExist = false;
		while (fastPtr != null && fastPtr.getNext() != null) {
			slowPtr = slowPtr.getNext();
			fastPtr = fastPtr.getNext().getNext();
			if (slowPtr == fastPtr) {
				loopExist = true;
				break;
			}

		}
		if (loopExist) {
			slowPtr = head;
			while (slowPtr != fastPtr) {
				slowPtr = slowPtr.getNext();
				fastPtr = fastPtr.getNext();
			}
			return fastPtr;
		}
		return null;
	}
	
	/*
	 * Find the length of the loop
	 */
	public static int findLengthOfLoop(LinkedListNode head) {
		LinkedListNode fastPtr = head;
		LinkedListNode slowPtr = head;
		boolean loopExist = false;
		while (fastPtr != null && fastPtr.getNext() != null) {
			slowPtr = slowPtr.getNext();
			fastPtr = fastPtr.getNext().getNext();
			if (slowPtr == fastPtr) {
				loopExist = true;
				break;
			}

		}

		int length = 0;
		if (loopExist) {
			do {
				slowPtr = slowPtr.getNext();
				length++;
			} while (slowPtr != fastPtr);
		}
		return length;
	}
	
	/* Insert the node in a sorted linked list	 */
	public LinkedListNode insertinSortedList(LinkedListNode head, LinkedListNode newNode) {
		LinkedListNode ptr = head;
		LinkedListNode prePtr = head;

		if (head == null) {
			return head;
		}
		// Traverse the list until you find item bigger than the new node value
		while (ptr.getNext() != null && ptr.getData() < newNode.getData()) {
			prePtr = ptr;
			ptr = ptr.getNext();
		}
		newNode.setNext(ptr);
		prePtr.setNext(newNode);
		return head;
	}
	
	/* Reverse the Singly LinkedList 
	 * 
	 * Time complexity : o(n) and space complexity: o(1) */
	public static LinkedListNode recursiveListIterative(LinkedListNode head) {
		LinkedListNode current = head;
		LinkedListNode prev = null;
		while (current != null) {
			LinkedListNode next = current.getNext();
			current.setNext(prev);
			prev = current;
			current = next;
		}

		return prev;
	}
	
	/* Reverse the Single LinkedList using Recursive 
	 * 
	 * Time complexity : o(n) and space complexity: o(n)
	 */
	public static void reverseLinkedListRecursive(LinkedListNode current, LinkedListNode[] head) {
		if (current == null) {
			return;
		}
		
		LinkedListNode next = current.getNext();
		if (next == null) {
			head[0] = current;
			return;
		}
		reverseLinkedListRecursive(next, head);
		next.setNext(current);
		current.setNext(null);
	}
	
	/* Intersection of two Linked list
	 * Approach:
	 * --------
	 * Find lengths(L1 and L2) of both lists.
	 * Take the difference d of the lengths.
	 * Make d steps in the longer list.
	 * Step on both list in parallel until links to next node match
	 * 
	 * Time complexity : o(max(m,n) and space complexity: o(1)
	 */
	public static LinkedListNode findIntersectingNode(LinkedListNode list1, LinkedListNode list2) {
		int length1 = 0, length2 = 0, difference = 0;
		LinkedListNode head1 = list1, head2 = list2;

		while (head1 != null) {
			length1++;
			head1 = head1.getNext();
		}

		while (head2 != null) {
			length2++;
			head2 = head2.getNext();
		}

		if (length1 > length2) {
			head1 = list1;
			head2 = list2;
			difference = length1 - length2;
		} else {
			head1 = list2;
			head2 = list1;
			difference = length2 - length1;
		}

		for (int i = 0; i < difference; i++) {
			head1 = head1.getNext();
		}

		while (head1 != null && head2 != null) {
			if (head1 == head2) {
				return head1;
			}
			head1 = head1.getNext();
			head2 = head2.getNext();
		}

		return null;
	}
	
	/* Middle of the linked list
	 * Approach:-
	 * -----------
	 * Use two pointers. Move one pointer at twice the speed of the second.
	 * When the first pointer reaches the end of list, the second pointer will be pointing to the middle of node.
	 * 
	 * Time complexity : o(n) and space complexity: o(1)
	 */
	public static LinkedListNode getMiddleNode(LinkedListNode head) {
		LinkedListNode ptr1, ptr2;
		ptr1 = ptr2 = head;
		int i = 0;
		while (ptr1.getNext() != null) {
			if (i == 0) {
				ptr1 = ptr1.getNext();
				i = 1;
			} else if (i == 1) {
				ptr1 = ptr1.getNext();
				ptr2 = ptr2.getNext();
				i = 0;
			}
		}
		return ptr2;
	}
	
	/* Display a linked list from the end.
	 * Approach :-
	 * ----------
	 * Traverse recursively till end of the linked list while coming back, start printing the element.
	 * Time complexity : o(n) and space complexity: o(n) - using stack
	 */
	public static void printListFromEnd(LinkedListNode head) {
		if (head == null) {
			return;
		}

		printListFromEnd(head.getNext());
		System.out.println(head.getData());
	}
	
	/* Check whether the given linked list is even or odd
	 * Approach :-
	 * -----------
	 * Use 2x pointer, tale a pointer that moves at 2x(2 nodes). At the end, if the length is even, then pointer will be NULL
	 * otherwise it will point to last node.
	 * 
	 * Time complexity : o(n) and space complexity: o(1)
	 */
	public int isLinkedListEven(LinkedListNode head) {
		while (head != null && head.getNext() != null) {
			head = head.getNext().getNext();
		}
		if (head == null) {
			return 0;
		}
		return 1;
	}
	
	/* Merge the two sorted Linked list -  using recursive approach */
	public LinkedListNode mergeTwoList(LinkedListNode head1, LinkedListNode head2) {
		if(head1 == null){
			return head2;
		}
		if(head2 == null){
			return head1;
		}
		
		LinkedListNode head = new LinkedListNode();
		if(head1.getData() <= head2.getData()){
			head = head1;
			head.setNext(mergeTwoList(head1.getNext(), head2));
		} else {
			head = head2;
			head.setNext(mergeTwoList(head2.getNext(), head1));
		}
		return head;
	}
	
	/* Merge the two sorted Linked list -  using non-recursive approach */
	public LinkedListNode mergeTwoListNonRecursive(LinkedListNode head1, LinkedListNode head2) {
		LinkedListNode head = new LinkedListNode();
		LinkedListNode current = head;
		while (head1 != null & head2 != null) {
			if (head1.getData() <= head2.getData()) {
				current.setNext(head1);
				head1 = head1.getNext();
			} else {
				current.setNext(head2);
				head2 = head2.getNext();
			}

			if (head1 != null) {
				current.setNext(head1);
			} else if (head2 != null) {
				current.setNext(head2);
			}
		}
		return head.getNext();
	}
	
	/* Reverse the linked in pairs 
	 * 1-> 2-> 3-> 4-> X  => 2-> 1-> 4-> 3-> X
	 * Approach : Swap the head and head.next 
	 */
	public static LinkedListNode reversePairRecursion(LinkedListNode head) {
		LinkedListNode temp;
		if (head == null || head.getNext() == null) {
			return null;
		} else {
			temp = head.getNext();
			head.setNext(temp.getNext());
			temp.setNext(head);
			head = temp;

			// Call the method recursively
			head.getNext().setNext(reversePairRecursion(head.getNext().getNext()));
			return head;
		}
	}
	
	public static LinkedListNode reversePairIterative(LinkedListNode head) {
		LinkedListNode temp1 = null;
		LinkedListNode temp2 = null;

		while (head != null && head.getNext() != null) {
			if (temp1 != null) {
				temp1.getNext().setNext(head.getNext());
			}
			temp1 = head.getNext();
			head.setNext(head.getNext().getNext());
			temp1.setNext(head);
			if (temp2 == null) {
				temp2 = temp1;
			}
			head = head.getNext();
		}
		return temp2;
	}
	
	/* Split a circular linked list into 2 equal parts
	 * Approach :
	 * ----------
	 * Store the mid and last pointer using floyd cycle finding algorithm.
	 * Make the second half circular
	 * Make the first half circular
	 * Set head pointer of both of the linked list
	 */
	public static void splitList(LinkedListNode head, LinkedListNode head1, LinkedListNode head2) {
		LinkedListNode slowPtr = head;
		LinkedListNode fastPtr = head;

		while (fastPtr.getNext() != head && fastPtr.getNext().getNext() != head) {
			fastPtr = fastPtr.getNext().getNext();
			slowPtr = slowPtr.getNext();
		}

		// If there is even node, then move fastPtr
		if (fastPtr.getNext().getNext() == head) {
			fastPtr = fastPtr.getNext();
		}

		head1 = head; // Set the head position of the first half

		// Set he head pinter of 2nd half
		if (head.getNext() != head) {
			head2 = slowPtr.getNext();
		}

		fastPtr.setNext(slowPtr.getNext());
		slowPtr.setNext(head);
	}
	
	/* Exchange the adjacent element in a linked list*/
	public LinkedListNode exchangeAdjacentNode(LinkedListNode head) {
		LinkedListNode temp = new LinkedListNode(0);
		temp.setNext(head);
		LinkedListNode prev = temp, curr = head;
		while (curr != null && curr.getNext() != null) {
			LinkedListNode tmp = curr.getNext().getNext();
			curr.getNext().setNext(prev.getNext()); // Head
			prev.setNext(curr.getNext());
			curr.setNext(tmp);
			prev = curr;
			curr = prev.getNext();
		}
		return temp.getNext();
	}
	
	/* Reverse block of K nodes in a list
	 * Approach:-
	 * -----------
	 * This is extension of swapping nodes in a linkedlist
	 * 1. Check if the remaining list has k nodes 
	 * 		-	If Yes get the pointer of k+1 node
	 * 		-	Else return
	 * 2. Return first k nodes
	 * 3. Set next of last node to k+1 th node
	 * 4. Move to k+1 th node
	 * 5. Go to step 1
	 * 6. k-1th node of first k nodes becomes the new head if available. Otherwise we can return the head. 
	 */
	public static LinkedListNode reverseKNodesRecursive(LinkedListNode head, int k) {
		LinkedListNode current = head;
		LinkedListNode next = null;
		LinkedListNode prev = null;
		int count = k;

		// Reverse the k nodes
		while (current != null && count > 0) {
			next = current.getNext();
			current.setNext(prev);
			prev = current;
			current = next;
			count--;
		}

		// Now next points to k+1 th node, returns the pointer to the head
		if (next != null) {
			head.setNext(reverseKNodesRecursive(next, k));
		}

		// Return head next
		return prev;
	}
	
	// Non - recursive
	public static LinkedListNode reverseKNode(LinkedListNode head, int k) {
		LinkedListNode current = head; // Start with head
		LinkedListNode prevTail = null; // Last node after reverse
		LinkedListNode prevCurrent = head; // First node before reverse
		while (current != null) {
			int count = k;
			LinkedListNode tail = null;
			while (current != null && count > 0) {
				LinkedListNode next = current.getNext();
				current.setNext(tail);
				tail = current;
				current = next;
				count--;
			}

			// Reversed K nodes
			if (prevTail != null) {
				prevTail.setNext(tail); // Link this set and previous set
			} else {
				/* We just reversed first set of k nodes, updates head point to the Kth node */
				head = tail;
			}

			/* Save the last node after reverse since we need to connect to the next set */
			prevTail = prevCurrent;

			/* Save the current node which will become the last node after reverse */
			prevCurrent = current;
		}
		return head;
	}
	
	/* Josephus Circle */
	public LinkedListNode getJosephusPosition(int n, int m) {

		LinkedListNode p = new LinkedListNode(1);
		LinkedListNode q = null;

		for (int i = 2; i <= n; i++) {
			p = p.getNext();
			p.setData(i);
		}
		p.setNext(q);

		for (int count = n; count > 1; count--) {
			for (int i = 0; i < m - 1; ++i) {
				p = p.getNext();
			}
			p.setNext(p.getNext().getNext());
		}
		System.out.println(p.getData());
		return p;
	}
	
	/* Find modular node*/
	public LinkedListNode modularNode(LinkedListNode head, int k) {
		LinkedListNode modularNode = null;
		int i = 0;
		if (k <= 0) {
			return null;
		}

		for (; head != null; head = head.getNext()) {
			if (i % k == 0) {
				modularNode = head;
			}
			i++;
		}
		return modularNode;
	}
	
	/* Find modular node from the End
	 * Approach :
	 * ---------
	 * Get first node after module = 0. prior to 18th 
	 */
	public LinkedListNode modularNodesFromEnd(LinkedListNode head, int k) {
		LinkedListNode modularNode = null;

		if (k <= 0) {
			return null;
		}

		for (int i = 0; i < k; i++) {
			if (head != null) {
				head = head.getNext();
			} else {
				return null;
			}
		}

		while (head != null) {
			modularNode = head.getNext();
			head = head.getNext();
		}
		return modularNode;
	}
	
	/* Find fractional node */
	public LinkedListNode getfractionalnodes(LinkedListNode head, int k) {
		LinkedListNode fractionalNode = null;
		int i = 0;

		if (k <= 0) {
			return null;
		}

		for (; head != null; head = head.getNext()) {
			if (i % k == 0) {
				if(fractionalNode != null) {
					fractionalNode = head;
				} else {
					fractionalNode = new LinkedListNode();
					fractionalNode = fractionalNode.getNext();
				}
			}
			i++;
		}
		return fractionalNode;
	}
	
	/* Given a singly linked list
	 * L : L1 -> L2 -> L3.....-> Ln-1 -> Ln reorder it to L1 -> Ln -> l2 -> ln-1...
	 * Approach:-
	 * -------
	 * 1. Divide the linked list 
	 * 2. Reverse the second list
	 * 
	 * Using a stack for reversing the list  
	 */
	
	public void reorderList(LinkedListNode head) {
		if (head == null) {
			return;
		}

		// Divide the linked list
		LinkedListNode slowPtr = head;
		LinkedListNode fastPtr = head.getNext();

		while (fastPtr != null && fastPtr.getNext() != null) {
			fastPtr = fastPtr.getNext().getNext();
			slowPtr = slowPtr.getNext();
		}

		LinkedListNode head2 = slowPtr.getNext();
		slowPtr.setNext(null);

		// Reverse the list using queue
		LinkedList<LinkedListNode> queue = new LinkedList<LinkedListNode>();
		while (head2 != null) {
			LinkedListNode temp = head2;
			head2 = head2.getNext();
			temp.setNext(null);
			queue.push(temp);
		}
		
		// Merging the two lists
		while (!queue.isEmpty()) {
			LinkedListNode temp = queue.pop();
			temp.setNext(head.getNext());
			head.setNext(temp);
			head = head.getNext().getNext();
		}
	}
	
	/* Rotate the list to the right by k places
	 *  1 -> 2 -> 3 -> 4 -> 5 -> Null and K = 2, then return 4 -> 5 -> 1 -> 2 -> 3 -> NULL
	 */
	public LinkedListNode rotateRight(LinkedListNode head, int n) {

		if (head == null || head.getNext() == null) {
			return null;
		}
		LinkedListNode rotateStart = head;
		LinkedListNode rotateEnd = head;

		while (n-- > 0) {
			rotateEnd = rotateEnd.getNext();
			if (rotateEnd == null) {
				rotateEnd = head;
			}
		}

		if (rotateStart == rotateEnd) {
			return head;
		}

		while (rotateEnd.getNext() != null) {
			rotateStart = rotateStart.getNext();
			rotateEnd = rotateEnd.getNext();
		}

		LinkedListNode temp = rotateStart.getNext();
		rotateStart.setNext(null);
		rotateEnd.setNext(head);
		return temp;
	}
	
	/* Add the two numbers
	 * (3-> 4- > 3) + (5-> 6-> 4) => (8-> 0-> 8)
	 */
	public LinkedListNode addTwoNumbers(LinkedListNode list1, LinkedListNode list2) {
		if (list1 == null) {
			return list2;
		}

		if (list2 == null) {
			return list1;
		}

		LinkedListNode head = new LinkedListNode(0);
		LinkedListNode curr = head;
		int advance = 0;
		while (list1 != null && list2 != null) {
			int sum = list1.getData() + list2.getData() + advance;
			advance = sum % 10;
			sum = sum % 10;
			LinkedListNode ptr = new LinkedListNode(sum);
			curr.setNext(ptr);

			curr = curr.getNext();
			list1 = list1.getNext();
			list2 = list2.getNext();
		}

		if (list1 != null) {
			if (advance != 0) {
				curr.setNext(addTwoNumbers(list1, new LinkedListNode(advance)));
			} else {
				curr.setNext(list1);
			}
		} else if (list2 != null) {
			if (advance != 0) {
				curr.setNext(addTwoNumbers(list2, new LinkedListNode(advance)));
			} else {
				curr.setNext(list2);
			}
		} else if (advance != 0) {
			curr.setNext(new LinkedListNode(advance));
		}

		return head.getNext();
	}
	
	/* Given a linked list and value k , partition it such that all nodes less than k comes before nodes greater than or equal to k.
	 * you should preserve the original relative order of the nodes in each of the two partitions
	 * 1 -> 4 -> 3 ->  2 -> 5 -> 2 and k = 3  =>  1 -> 2 -> 2 -> 4 -> 3 -> 5
	 */
	public LinkedListNode partition(LinkedListNode head, int k) {
		LinkedListNode root = new LinkedListNode(0);  /* To keep lower elements */
		LinkedListNode pivot = new LinkedListNode(k);  /* To keep higher nodes */
		LinkedListNode rootNext = root, pivotNext = pivot;
		LinkedListNode curr = head;

		while (curr != null) {
			LinkedListNode next = curr.getNext();
			if (curr.getData() >= k) {
				pivotNext.setNext(curr);
				pivotNext = curr;
				pivotNext.setNext(null);
			} else {
				rootNext.setNext(curr);
				rootNext = curr;
			}
			curr = next;
		}

		rootNext.setNext(pivot.getNext());
		return rootNext.getNext();
	}
	
	/* Remove the duplicates in a linked list */
	@SuppressWarnings("null")
	public static void removeDuplicates(LinkedListNode head) {
		if (head == null || head.getNext() == null) {
			return;
		}
		LinkedListNode curr = head;
		LinkedListNode curr2, prev;
		while (curr != null) {
			curr2 = curr.getNext();
			prev = curr;
			
			while(curr2 != null){
				/* Check if curr and curr2 values are the same then delete curr2*/
				if(curr.getData() == curr2.getData()){
					prev.setNext(curr2.getNext());
				}
				
				prev = curr2;
				curr2 = curr2.getNext();
			}
		}
		curr = curr.getNext();
	}
	
	// Remove duplicates using hashtable
	public static void removeDuplicatesUsingHashTable(LinkedListNode head) {
		Map<Integer, Boolean> listNodesMap = new HashMap<Integer, Boolean>();
		LinkedListNode curr = head;
		LinkedListNode next;

		while (curr.getNext() != null) {
			next = curr.getNext();

			if (listNodesMap.get(next.getData()) != null) {
				/* Already exist, so deleted */
				curr.setNext(next.getNext());
			} else {
				listNodesMap.put(next.getData(), true);
				curr = curr.getNext();
			}
		}

	}
	
}
