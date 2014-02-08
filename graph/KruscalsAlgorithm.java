package graph;

public class KruscalsAlgorithm {
	Node[] nodes;
	Edge[][] cost;	
	MCST[] mcst;
	Edge[] minHeap;
	int count = 0;
	int numOfEdges;
	
	class Node{
		int vertex;
		Node link;
		
		Node(int vertex){
			this.vertex = vertex;
		}
	}
	
	class Edge{
		int i, j;
		int weight;
	}
	
	public KruscalsAlgorithm(int numOfNodes, int numOfEdges){
		this.numOfEdges = numOfEdges;
		this.nodes = new Node[numOfNodes];
		this.cost = new Edge[numOfEdges][numOfEdges];
		this.mcst = new MCST[numOfEdges];
		this.minHeap = new Edge[numOfEdges+1];
	}
	
	public void makeVertex(int vertex){
		this.nodes[vertex] = new Node(vertex);
	}
	
	public void makeEdge(int i, int j, int weight){
		Node temp = this.nodes[i];
		for( ; temp.link != null ; temp = temp.link);
		temp.link = new Node(j);
		
		temp = this.nodes[j];
		for( ; temp.link != null ; temp = temp.link);
		temp.link = new Node(i);
		
		cost[i][j] = new Edge();
		
		cost[i][j].weight = weight;
		
		this.addMinHeap(i, j, weight);
	}
	
	public void printGraph(){
		for(Node node : this.nodes){
			Node temp = node;
			while(temp != null){
				System.out.print(temp.vertex + " ");
				temp = temp.link;
			}
			System.out.println();
		}
	}
	
	public void addMinHeap(int i, int j, int weight){
		int k = ++count;
		while((count != 1) && (weight < this.minHeap[k/2].weight)){
			this.minHeap[k] = this.minHeap[k/2];
			k /= 2;
		}
		this.minHeap[k] = new Edge();
		this.minHeap[k].weight = weight;
		this.minHeap[k].i = i;
		this.minHeap[k].j = j;
	}
	
	public Edge deleteMinHeap(){
		Edge item = this.minHeap[1];
		Edge temp = this.minHeap[count--];
		int parent = 1;
		int child = 2;
		while(child <= this.count){
			if((child < this.count) && (this.minHeap[child].weight > this.minHeap[child+1].weight)) child++;
			if(temp.weight <= this.minHeap[child].weight) break;
			this.minHeap[parent] = this.minHeap[child];
			parent = child;
			child *= 2;
		}
		this.minHeap[parent] = temp;
		return item;
	}
	
	class MCST{
		int i, j;
		int cost;
	}	
	
	
	public void makeMcst(){
		int num = 0;
		while(num < this.numOfEdges && this.minHeap != null){
			Edge edge = this.deleteMinHeap();
			this.mcst[num] = new MCST();
			this.mcst[num].i = edge.i;
			this.mcst[num].j = edge.j;
			this.mcst[num++].cost = edge.weight;
		}
	}
	
	public static void main(String[] args){
		KruscalsAlgorithm kruscalsGraph = new KruscalsAlgorithm(7, 9);
		kruscalsGraph.makeVertex(0);
		kruscalsGraph.makeVertex(1);
		kruscalsGraph.makeVertex(2);
		kruscalsGraph.makeVertex(3);
		kruscalsGraph.makeVertex(4);
		kruscalsGraph.makeVertex(5);
		kruscalsGraph.makeVertex(6);
		
		kruscalsGraph.makeEdge(0, 5, 10);
		kruscalsGraph.makeEdge(0, 1, 28);
		kruscalsGraph.makeEdge(5, 4, 25);
		kruscalsGraph.makeEdge(4, 3, 22);
		kruscalsGraph.makeEdge(4, 6, 24);
		kruscalsGraph.makeEdge(3, 2, 12);
		kruscalsGraph.makeEdge(3, 6, 18);
		kruscalsGraph.makeEdge(2, 1, 16);
		kruscalsGraph.makeEdge(6, 1, 14);
		
		kruscalsGraph.printGraph();
		
		kruscalsGraph.makeMcst();
	}

}
