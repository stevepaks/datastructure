package lists;

public class ListNode {
	int data;
	ListNode link;
	
	public ListNode(){
	}
	
	ListNode create2(){
		ListNode first, second;
		first = new ListNode();
		second = new ListNode();
		second.link = null;
		second.data = 20;
		first.data = 10;
		first.link = second;
		this.link = first;
		return first;
	}
	
	void insert(ListNode node){
		ListNode temp = new ListNode();		
		temp.data = 50;
		if(this.link != null){
			temp.link = node.link;
			node.link = temp;
		} else {
			temp.link = null;
			this.link = temp;
		}
	}
	
	void delete(ListNode trail, ListNode node){
		if(trail != null){
			trail.link = node.link;
		} else {
			this.link = this.link.link;
		}
	}
	
	ListNode getLink(){
		return this.link;
	}
	
	void printLint(ListNode ptr){
		System.out.println("This constains : ");
		for(; ptr != null ; ptr = ptr.link){
			System.out.println(ptr.data);
		}
	}
	
	public static void main(String[] args){
		ListNode listNode = new ListNode();
		ListNode newList = listNode.create2();
		listNode.insert(newList);
		listNode.delete(newList, newList.link);
		listNode.printLint(listNode.link);
	}

}
