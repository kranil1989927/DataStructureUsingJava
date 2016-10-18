package com.learn.ds.queue;
/**
 * @author ANIL
 *
 * Implementation of one stack using two queues
 * Approach:
 * ---------
 * Q1 and Q2 be the two queues to be used in the Implementation of stack.
 * We make sure that one queue is always empty
 * 
 * Push Operation Algorithm :
 * --------------------------
 * Insert an element in whichever queue is empty
 * --> Check whether queue Q1 is empty or not. If Q1 is empty, then enqueue the element into Q2.
 * --> Otherwise enqueue the element into Q1
 * Time Complexity : O(1)
 * 
 * Pop Operation Algorithm :
 * -------------------------
 * Transfer n-1 elements to other queue and delete last from queue for performing pop operation
 * --> If queue is not empty then transfer n-1 elements from Q1 to Q2 and then, dequeue the last
 * element of the Q1 and return it
 * --> If queue Q2 is not empty then transfer n- elements Q2 to Q1 and then, dequeue the last element
 * of Q2 and return it
 */
public class StackWithTwoQueues {

	private LinkedQueue q1 = new LinkedQueue();
	private LinkedQueue q2 = new LinkedQueue();

	public void push(int data) {
		if (q1.isEmpty()) {
			q2.enQueue(data);
			;
		} else {
			q1.enQueue(data);
		}
	}

	public int pop() throws Exception {
		int i = 0, size;
		if (q2.isEmpty()) {
			size = q1.size();
			while (i < size - 1) {
				q2.enQueue(q1.deQueue());
				i++;
			}
			return q1.deQueue();
		} else {
			size = q2.size();
			while (i < size - 1) {
				q1.enQueue(q2.deQueue());
				i++;
			}
			return q2.deQueue();
		}
	}
}
