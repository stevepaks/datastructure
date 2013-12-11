package lists;

public class ListQueue {
	static final int MAX_QUEUES = 10;
	
	Element item;
	ListQueue link;
	ListQueue front, rear;
	
	void enq(Element item){
		ListQueue temp = new ListQueue();
		if(isFull()){
			System.out.println("The memory is full");
			System.exit(1);
		}
		temp.item = item;
		temp.link = null;
		if(front != null){
			rear.link = temp;
		} else {
			front = temp;
		}
		rear = temp;
	}
	
	Element deq(){
		ListQueue temp = front;
		Element item;
		if(isEmpty()){
			System.out.println("The queue is empty");
			System.exit(1);
		}
		item = temp.item;
		front = temp.link;
		return item;
	}
	
	boolean isFull(){
		return false;
	}
	
	boolean isEmpty(){
		if(front.link == null){
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args){
		ListQueue queue = new ListQueue();
		Element e1 = new Element(1);
		Element e2 = new Element(2);
		queue.enq(e1);
		queue.enq(e2);
		queue.deq();
	}

}
