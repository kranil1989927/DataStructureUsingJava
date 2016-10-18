package com.learn.ds.queue;

import com.learn.ds.stack.LinkedStack;

public class QueueProblems {

	/**
	 * Check each successive pair of numbers in the stack is consecutive or not
	 * 
	 * @throws Exception
	 */
	public static boolean checkStackPairwiseOrder(LinkedStack<Integer> s) throws Exception {
		LinkedQueue q = new LinkedQueue();
		boolean isPairOrdered = true;

		while (!s.isEmpty()) {
			q.enQueue(s.pop());
		}

		while (!q.isEmpty()) {
			s.push(q.deQueue());
		}

		while (!s.isEmpty()) {
			int n = s.pop();
			q.enQueue(n);

			if (!s.isEmpty()) {
				int m = s.pop();
				q.enQueue(m);
				if (Math.abs(n - m) != 1) {
					isPairOrdered = false;
				}
			}
		}

		while (!q.isEmpty()) {
			s.push(q.deQueue());
		}
		return isPairOrdered;
	}

	/**
	 * Rearrange the elements by interleaving the first half of the list with the second half
	 * of the list.
	 * Input : [11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
	 * Output : [11, 16, 12, 17, 13, 18, 14, 19, 15, 20]
	 * 
	 * Algorithm:
	 * --------
	 * 1. Push half of the elements from queue to stack. Now elements in the stack is in reverse 
	 * order.
	 * 2. Pop elements from the stack and add to queue. Now elements added to queue in reverse
	 * order.
	 * 3. Remove the element from queue one by one upto the half of queue and add to queue.
	 * 4. Push the half elements to stack.
	 * 5. Pop from the stack and remove from queue each element one by one and add to queue. 
	 * @throws Exception 
	 */
	public void interLeavingQueue(LinkedQueue q) throws Exception {
		if (q.size() % 2 == 0) {
			throw new IllegalArgumentException();
		}

		LinkedStack<Integer> s = new LinkedStack<Integer>();
		int halfSize = q.size() / 2;
		for (int i = 0; i < halfSize; i++) {
			s.push(q.deQueue());
		}
		
		while(!s.isEmpty()){
			q.enQueue(s.pop());
		}
		
		for (int i = 0; i < halfSize; i++) {
			q.enQueue(q.deQueue());
		}
		
		for (int i = 0; i < halfSize; i++) {
			s.push(q.deQueue());
		}
		
		while(!s.isEmpty()){
			q.enQueue(s.pop());
			q.enQueue(q.deQueue());
		}

	}
}
