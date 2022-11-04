import java.util.LinkedList;

public class Stack {
	private LinkedList<Integer> stackList = new LinkedList<Integer>();
	
	
	public void push(int number) {
		this.stackList.add(number);
	}
	
	
	public int pop() {
		int head = this.stackList.get(this.stackList.size() - 1);
		this.stackList.remove(this.stackList.size() - 1);
		
		return head;
	}
	
	
	public int peek() {
		return this.stackList.get(this.stackList.size() - 1);
	}
	
	
	@Override
	public String toString() {
		String outputString = "Stack: [";
		
		for (int element : this.stackList) {
			outputString += (element + ",");
		}
		
		outputString += "]\n";
		outputString += "Head: " + this.peek();
		
		return outputString;
	}
}
