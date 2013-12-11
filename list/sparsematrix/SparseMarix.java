package lists;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SparseMarix {
	int MAX_SIZE = 50;
	AbstractNode[] hdnode;
	
	public SparseMarix(){
		hdnode = new HeadNode[MAX_SIZE];
		for(int i = 0 ; i < this.MAX_SIZE ; i++){
			hdnode[i] = new HeadNode();
		}
	}
	
	enum TagField{
		head, entry
	}
	
	class Entry{
		int row;
		int col;
		int value;
	}
	
	abstract class AbstractNode{
		AbstractNode down;
		AbstractNode right;
		TagField tag;
		abstract Entry getEntry();
		abstract HeadNode getNext();
		abstract void setNext(AbstractNode next);
	}
	
	class HeadNode extends AbstractNode{
		private AbstractNode next;
		@Override
		Entry getEntry() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		HeadNode getNext() {
			// TODO Auto-generated method stub
			return (HeadNode) next;
		}
		@Override
		void setNext(AbstractNode next) {
			// TODO Auto-generated method stub
			this.next = next;
		}		
	}
	
	class EntryNode extends HeadNode{
		Entry entry;
		
		public EntryNode(){
			this.entry = new Entry();
		}

		@Override
		Entry getEntry() {
			// TODO Auto-generated method stub
			return entry;
		}
	}
	
	AbstractNode mread(Scanner in){
		
		int numRows = in.nextInt();
		int numCols = in.nextInt();
		int numTerms = in.nextInt();
		
		int numHeads = numCols > numRows ? numCols : numRows;
		
		AbstractNode node = new EntryNode();
		node.tag = TagField.entry;
		
		node.getEntry().row = numRows;
		node.getEntry().col = numCols;
		
		if(numHeads == 0){
			node.right = node;			
		} else {
			for(int i = 0 ; i < numHeads ; i++){
				AbstractNode temp = new HeadNode();
				this.hdnode[i] = temp;
				this.hdnode[i].tag = TagField.head;
				this.hdnode[i].right = temp;
				this.hdnode[i].setNext(temp);
			}
			int currentRow = 0;
			AbstractNode last = this.hdnode[0];
			for(int i = 0 ; i < numTerms ; i++){
				int row = in.nextInt();
				int col = in.nextInt();
				int value = in.nextInt();
				
				if(row > currentRow){
					last.right = this.hdnode[currentRow];
					currentRow = row;
					last = this.hdnode[row];
				}
				AbstractNode temp = new EntryNode();
				temp.tag = TagField.entry;
				temp.getEntry().row = row;
				temp.getEntry().col = col;
				temp.getEntry().value = value;
				last.right = temp;
				last = temp;
				
				this.hdnode[col].getNext().down = temp;
				this.hdnode[col].setNext(temp);
			}
			
			last.right = this.hdnode[currentRow];
			
			for(int i = 0 ; i < numCols ; i++){
				this.hdnode[i].getNext().down = this.hdnode[i];
			}
			
			for(int i = 0 ; i < numHeads - 1 ; i++){
				this.hdnode[i].setNext(this.hdnode[i+1]);				
			}
			
			this.hdnode[numHeads - 1].setNext(node);
			node.right = this.hdnode[0];
		}
		return node;
	}
	
	void mwrite(AbstractNode node){
		AbstractNode head = node.right;
		System.out.println("numRows = " + node.getEntry().row + ", numCols = " + node.getEntry().col);
		System.out.println(" The matrix by row, column, and value : ");
		for(int i = 0 ; i < node.getEntry().row ; i++){
			for(AbstractNode temp = head.right ; temp != head ; temp = temp.right){
				System.out.println(temp.getEntry().row + " " + temp.getEntry().col + " " + temp.getEntry().value);
			}
			head = head.getNext();
		}
	}
	
	void merase(AbstractNode node){
		AbstractNode head = node.right;
		AbstractNode y = null, x = null;		
		
		for(int i = 0 ; i < node.getEntry().row ; i++){
			y = head.right;
			while(y != head){
				x = y;
				y = y.right;
			}
			x = head;
			head = head.getNext();
		}
		
		y = head;
		while(y != node){
			x = y;
			y = y.getNext();			
		}
		
		node = null;
	}
	
	public static void main(String[] args){
		Scanner in = null;
		try {
			in = new Scanner(new File("input.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SparseMarix matrix = new SparseMarix();
		
		AbstractNode result = matrix.mread(in);
		matrix.mwrite(result);
	}
}
