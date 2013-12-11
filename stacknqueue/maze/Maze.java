package stacknqueue;

public class Maze {
	
	static final int MAX_STACK_SIZE = 100;
	Offset move[];
	Element stack[];
	
	int mark[][];
	int maze[][];
	int top = 0;
	int EXIT_ROW;
	int EXIT_COL;
	
	public Maze(int row, int col){
		this.EXIT_ROW = row;
		this.EXIT_COL = col;
		this.maze = new int[row+2][col+2];
		this.initStack();
		this.initMark();
		this.initOffset();
	}
	
	private void initMark(){
		this.mark = new int[this.MAX_STACK_SIZE][this.MAX_STACK_SIZE];
	}
	
	private void initOffset(){
		this.move = new Offset[8];
		
		this.move[0] = new Offset(-1, 0);
		this.move[1] = new Offset(-1, 1);
		this.move[2] = new Offset(0, 1);
		this.move[3] = new Offset(1, 1);
		this.move[4] = new Offset(1, 0);
		this.move[5] = new Offset(1, -1);
		this.move[6] = new Offset(0, -1);
		this.move[7] = new Offset(-1, -1);
	}
	
	private void initStack(){
		this.stack = new Element[MAX_STACK_SIZE];
		for(int i = 0 ; i < this.stack.length ; i++){
			this.stack[i] = new Element();
		}
	}
	
	class Offset{
		private int vert;
		private int horiz;
		
		public Offset(int vert, int horiz){
			this.vert = vert;
			this.horiz = horiz;
		}
	}
	
	class Element{
		int row;
		int col;
		int dir;
		
		public Element(){
			this.row = 0;
			this.col = 0;
			this.dir = 0;
		}
	}
	
	public void path(){
		int row = 0;
		int col = 0;
		boolean found = false;
		this.mark[1][1] = 1;
		
		this.stack[0].row = 1;
		this.stack[0].col = 1;
		this.stack[0].dir = 0;
		
		while(this.top > -1 && !found){
			Element position = this.pop();
			row = position.row;
			col = position.col;
			int dir = position.dir;
			while(dir < 8 && !found){
				int next_row = row + move[dir].vert;
				int next_col = col + move[dir].horiz;
				if(next_row == this.EXIT_ROW && next_col == this.EXIT_COL){
					found = true;
				}else if(this.maze[next_row][next_col] == 0 && this.mark[next_row][next_col] == 0){
					mark[next_row][next_col] = 1;
					position.row = row;
					position.col = col;
					position.dir = ++dir;
					this.push(position);
					row = next_row;
					col = next_col;
					dir = 0;
				}else{
					dir++;
				}
			}
		}
		
		if(found){
			System.out.println("The path is : ");
			System.out.println("row   col");
			for(int i = 0 ; i <= top ; i++){
				System.out.println(this.stack[i].row + "  " + this.stack[i].col);
			}
			System.out.println(row + "   " + col);
			System.out.println(this.EXIT_ROW + "   " + this.EXIT_COL);
		}
		else{
			System.out.println("The maze does not have a path");
		}
	}
	
	private Element pop(){
		if(this.top == -1){
			System.out.println("this stack is empty@");
			return null;
		}
		return stack[top--];
	}
	
	private void push(Element item){
		if(this.top >= this.MAX_STACK_SIZE){
			System.out.println("this stack is full!");
			return;
		}
		stack[++top] = item;
	}

}
