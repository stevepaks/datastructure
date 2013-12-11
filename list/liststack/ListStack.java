package lists;

public class ListStack {
	Element item;
	ListStack link;
	static final int MAX_STACKS = 10;
	int pointer = -1;
	ListStack top;	
	
	void push(Element item){
		ListStack temp = new ListStack();
		if(this.isFull()){
			System.out.println("The memory is full");
			System.exit(1);
		}
		this.pointer++;
		temp.item = item;
		temp.link = this.top;
		this.top = temp;
	}
	
	Element pop(){
		ListStack temp = this.top;
		Element item;		
		this.pointer--;
		if(isEmpty()){
			System.out.println("The stack is empty");
			System.exit(1);
		}
		item = temp.item;
		top = temp.link;
		return item;
	}
	
	public static void main(String[] args){
		ListStack stack = new ListStack();
		Element e1 = new Element(1);
		Element e2 = new Element(2);
		stack.push(e1);	
		stack.push(e2);
		stack.pop();
		
	}
	
	boolean isFull(){
		if(this.pointer == this.MAX_STACKS - 1){
			return true;
		}
		return false;
	}
	
	boolean isEmpty(){
		if(this.pointer == -1){
			return true;
		}
		return false;
	}

}
