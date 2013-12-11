package lists;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EquivalenceRelations {
	
	static final int MAX_SIZE = 24;
	boolean[] out;
	Node[] seq;
	
	public static void main(String[] args){
		EquivalenceRelations equivalenceChecker = new EquivalenceRelations();
		int n = 0;
		try {
			Scanner in = new Scanner(new File("input.txt"));
			n = in.nextInt();
			equivalenceChecker.readEquivalencePairs(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		equivalenceChecker.findAllPairs(n);
	}
	
	public class Node{
		int data;
		Node link;
	}
	
	public EquivalenceRelations(){
		out = new boolean[this.MAX_SIZE];
		seq = new Node[this.MAX_SIZE];
		for(int i = 0 ; i < this.MAX_SIZE ; i++){
			out[i] = true;
		}
		for(int i = 0 ; i < this.MAX_SIZE ; i++){
			seq[i] = new Node();
		}
	}
	
	void readEquivalencePairs(Scanner in){
		int i = in.nextInt();
		int j = in.nextInt();
		while(i >= 0){
			Node x = new Node();			
			x.data = j;
			x.link = seq[i];
			seq[i] = x;
			
			x = new Node();
			x.data = i;
			x.link = seq[j];
			seq[j] = x;
			
			i = in.nextInt();
			j = in.nextInt();
		}
	}
	
	void findAllPairs(int n){
		for(int i = 0 ; i < n ; i++){
			if(this.out[i]){
				System.out.print("New class : " + i);
				out[i] = false;
				
				Node x = seq[i]; Node top = null;
				while(true){
					while(x != null){
						int j = x.data;
						if(this.out[j]){
							System.out.print(" " + j); this.out[j] = false;
							Node y = x.link; x.link = top; top = x; x = y;
						} else {
							x = x.link;
						}
					}
					if(top == null){
						System.out.println();
						break;
					}
					x = this.seq[top.data]; top = top.link;
				}
			}
		}
	}

}
