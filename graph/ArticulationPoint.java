package graph;

public class ArticulationPoint {
	int[] dfn;
	int[] low;
	int MAX_VERTICES = 100;
	int num = 0;
	
	Node[] graph;	
	Edge stack[];
	
	class Node{
		int vertex;
		Node link;
	}
	
	public ArticulationPoint(){
		graph = new Node[this.MAX_VERTICES];
		stack = new Edge[MAX_VERTICES];
		this.dfn = new int[this.MAX_VERTICES];
		this.low = new int[this.MAX_VERTICES];
		this.init();
	}
	
	void init(){
		for(int i = 0 ; i < this.MAX_VERTICES ; i++){
			dfn[i] = -1;
			low[i] = -1;
		}
	}
	
	public int dfnLow(int u, int v){
		dfn[u] = num;
		low[u] = num++;
		
		for(Node ptr = graph[u] ; ptr != null ; ptr = ptr.link){
			int w = ptr.vertex;
			
			if(dfn[w] < 0){
				dfnLow(w, u);
				low[u] = min(low[u], low[w]);
			} else if(w != v){
				low[u] = min(low[u], dfn[w]);
			}
		}
		
		return low[u];
	}
	
	private int top = -1;
	class Edge{
		int x;
		int y;
		
		Edge(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		int getX(){
			return this.x;
		}
		
		int getY(){
			return this.y;
		}
	}
	
	private void push(int x, int y){
		this.stack[++top] = new Edge(x, y);
	}
	
	private Edge pop(){
		return this.stack[top--];
	}
	
	public void bicon(int u, int v){
		dfn[u] = low[u] = num++;
		for( Node ptr = graph[u] ; ptr != null ; ptr = ptr.link ){
			int w = ptr.vertex;
			if(v != w && dfn[w] < dfn[u]){	//backedge or none visiting
				push(u, w);
				if(dfn[w] < 0){	//if dfn[w] >= 0 then it is backedge, otherwise none visiting  
					bicon(w, u);
					low[u] = min(low[u], low[w]);
					if(low[w] >= dfn[u]){	//articulation point
						System.out.println("New biconnected component:");
						int x = 0, y = 0;
						do{
							Edge edge = pop();
							x = edge.getX();
							y = edge.getY();
							System.out.println("<" + x + ", " + y + ">");
						}while(!((x == u) && (y == w)));
						System.out.println();
					}
				} else if(w != u) low[u] = min(low[u], dfn[w]);				
			}
		}
	}

	
	public void print(){
		for(int i = 0 ; i < num ; i++){
			System.out.println(i + " " + this.dfn[i] + " " +this.low[i]);
		}
	}
	
	int min(int x, int y){
		return x < y ? x : y;
	}
	
	public void makeVertex(int i){
		Node vertex = new Node();
		vertex.vertex = i;
		this.graph[i] = vertex;
	}
	
	public void makeEdge(int i, int j){
		Node node1 = new Node();
		node1.vertex = i;
		
		Node node2 = new Node();
		node2.vertex = j;
		
		Node temp = this.graph[i];
		while(temp.link != null) temp = temp.link;
		temp.link = node2;
		
		temp = this.graph[j];
		while(temp.link != null) temp = temp.link;
		temp.link = node1;
	}
	
	public static void main(String[] args){
		ArticulationPoint a = new ArticulationPoint();
		a.makeVertex(0);
		a.makeVertex(1);
		a.makeVertex(2);
		a.makeVertex(3);
		a.makeVertex(4);
		a.makeVertex(5);
		a.makeVertex(6);
		a.makeVertex(7);
		a.makeVertex(8);
		a.makeVertex(9);
		
		a.makeEdge(3, 4);
		a.makeEdge(2, 4);
		a.makeEdge(1, 2);
		a.makeEdge(0, 1);
		a.makeEdge(1, 3);
		a.makeEdge(3, 5);
		a.makeEdge(5, 6);
		a.makeEdge(6, 7);
		a.makeEdge(7, 9);
		a.makeEdge(7, 8);
		a.makeEdge(5, 7);
//		a.dfnLow(3, -1);
		
		a.bicon(3, -1);
		
		a.print();
//		System.out.println(a.dfnLow(3, 100));
	}

}
