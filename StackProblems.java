package com.learn.ds.stack;

public class StackProblems {

	/* Checking Balance of Symbols using Stack */
	public boolean isSymbolsAreBalanced(String s) {

		LinkedStack<Character> stk = new LinkedStack<Character>();
		if (s == null || s.length() == 0) {
			return true;
		}

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				if (!stk.isEmpty() && stk.peek() == '(') {
					stk.pop();
				} else {
					return false;
				}
			} else if (s.charAt(i) == ']') {
				if (!stk.isEmpty() && stk.peek() == '[') {
					stk.pop();
				} else {
					return false;
				}
			} else if (s.charAt(i) == '}') {
				if (!stk.isEmpty() && stk.peek() == '{') {
					stk.pop();
				} else {
					return false;
				}
			} else {
				stk.push(s.charAt(i));
			}
		}
		if (stk.isEmpty())
			return true;
		else
			return false;
	}

	/* Postfix evaluation using stack */
	public int expressionEvaluation(String[] tokens) {
		LinkedStack<Integer> s = new LinkedStack<Integer>();

		for (String token : tokens) {
			if (token.equals("+")) {
				int opr1 = s.pop();
				int opr2 = s.pop();
				s.push(opr1 + opr2);
			} else if (token.equals("-")) {
				int opr1 = s.pop();
				int opr2 = s.pop();
				s.push(opr2 - opr1);
			} else if (token.equals("*")) {
				int opr1 = s.pop();
				int opr2 = s.pop();
				s.push(opr1 * opr2);
			} else if (token.equals("/")) {
				int opr1 = s.pop();
				int opr2 = s.pop();
				s.push(opr1 / opr2);
			} else {
				s.push(Integer.parseInt(token));
			}
		}
		return s.pop();
	}

	/* Check if the String is Palindrome */
	public int isPalindrome(String inputStr) {
		char[] a = inputStr.toCharArray();
		int i = 0, j = inputStr.length();
		while (i < j && a[i] == a[j]) {
			i++;
			j--;
		}
		if (i < j) {
			System.out.println("Not a Palindrome");
			return 0;
		} else {
			System.out.println("It is a Palindrome");
			return 1;
		}
	}
	
	// Using Stack
	public boolean isPalindromeString(String inputStr) {
		char[] a = inputStr.toCharArray();
		LinkedStack<Character> s = new LinkedStack<Character>();
		int i = 0;
		while (a[i] != 'X') {
			s.push(a[i]);
			i++;
		}
		i++;

		while (i < a.length) {
			if (s.isEmpty())
				return false;
			if (a[i] != (char) s.pop()) {
				return false;
			}
			i++;
		}
		return true;
	}
	
	/* Reverse the contents of stacks using push and pop operations */
	public static void reverseStack(LinkedStack<Integer> stack){
		if(stack.isEmpty()){
			return;
		}
		int temp = stack.pop();
		reverseStack(stack);
		insertAtBottom(stack, temp);
	}

	private static void insertAtBottom(LinkedStack<Integer> stack, int temp) {
		if(stack.isEmpty()){
			stack.push(temp);
			return;
		}
		int temp1 = stack.pop();
		insertAtBottom(stack, temp);
		stack.push(temp1);
	}
	
	/* Finding spans*/
	public int[] findingSpans(int[] inputArray) {
		int[] spans = new int[inputArray.length];
		for (int i = 0; i < inputArray.length; i++) {
			int span = 1;
			int j = i - 1;
			while (j >= 0 && inputArray[j] <= inputArray[j + 1]) {
				span++;
				j--;
			}
			spans[i] = span;
		}
		return spans;
	}
	
	/* Finding spans -- Using Stacks*/
	public int[] findingSpansUsingStacks(int[] arr) {
		int[] spans = new int[arr.length];
		LinkedStack<Integer> stack = new LinkedStack<Integer>();
		int p = 0;
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[i] > arr[(Integer) stack.pop()]) {
				stack.pop();
				if (stack.isEmpty()) {
					p = -1;
				} else {
					p = (Integer) stack.pop();
				}
				spans[i] = i - p;
				stack.push(i);
			}
		}
		return spans;
	}
	
	/* Largest rectangle =under Histogram */
	public int maxRectangleArea(int[] a) {
		LinkedStack<Integer> s = new LinkedStack<Integer>();
		if (a == null || a.length == 0) {
			return 0;
		}

		int maxArea = 0;
		int i = 0;
		// Run through all the bars of the given histogram
		while (i < a.length) {
			// If current bar is higher than the bar of the stack peek, push it
			// to stack
			if (s.isEmpty() || a[s.peek()] <= a[i]) {
				s.push(i++);
			} else {
				int top = s.pop();
				maxArea = Math.max(maxArea, a[top] * (s.isEmpty() ? i : i - s.peek() - 1));
			}
		}

		while (!s.isEmpty()) {
			int top = s.pop();
			maxArea = Math.max(maxArea, a[top] * (s.isEmpty() ? i : i - s.peek() - 1));
		}

		return maxArea;
	}
	
	/* Sorting the stack in ascending order T : o(n2), S : o(n) */
	public static LinkedStack<Integer> sort(LinkedStack<Integer> stk) {
		LinkedStack<Integer> rstk = new LinkedStack<Integer>();
		while (!stk.isEmpty()) {
			int temp = stk.pop();
			while (!rstk.isEmpty() && rstk.peek() > temp) {
				stk.push(rstk.pop());
			}
			rstk.push(temp);
		}
		return rstk;
	}
	
	/* Remove duplicates or Adjacent Duplicates
	 * Approach : If the element doesn't match to current of stack, then add to stack
	 * else skip until element match the top of the stack amd remove the element from stack
	 */
	public int removeDuplicates(int[] arr) {
		int stkPtr = -1;
		int i = 0;
		while (i < arr.length) {
			if (stkPtr == -1 || arr[stkPtr] != arr[i]) {
				stkPtr++;
				i++;
			} else {
				while (i < arr.length && arr[stkPtr] == arr[i]) {
					i++;
				}
				stkPtr--;
			}
		}
		return stkPtr;
	}
}
