package stacknqueue;

import stacknqueue.EvaluationExpression.Precedence;

public class Postfix {
	final static int MAX_STACK_SIZE = 100;	
	final static int MAX_EXPR_SIZE = 100;

	enum Precedence {
		lparen, rparen, plus, minus, times, divide, mod, eos, operand
	}
	
	Precedence[] stack;
	char[] expr;
	char symbol;
	int n;
	int top;
	static int[] isp = { 0, 19, 12, 12, 13, 13, 13, 0 };
	static int[] icp = { 20, 19, 12, 12, 13, 13, 13, 0 };
	
	public Postfix(char[] expr){
		this.stack = new Precedence[MAX_STACK_SIZE];
		this.stack[0] = Precedence.eos;
		this.n = 0;
		this.top = 0;
		this.expr = expr;
	}

	public void postfix(){
		Precedence token;
		for(token = this.getToken() ; token != Precedence.eos ; token = getToken()){
			if(token == Precedence.operand){
				System.out.println(symbol);
			} else if(token == Precedence.rparen){
				while(this.stack[this.top] != Precedence.lparen){
					this.printToken(this.pop());
				}
				this.pop();
			} else{
				while(this.isp[this.stack[this.top].ordinal()] >= this.icp[token.ordinal()]){
					this.printToken(this.pop());
				}
				this.push(token);
			}
		}
		while((token = this.pop()) != Precedence.eos){
			this.printToken(token);
		}
		System.out.println();
	}
	
	private Precedence getToken(){
		symbol = this.expr[n++];
		switch(symbol){
		case '(':
			return Precedence.lparen;
		case ')' :
			return Precedence.rparen;
		case '+' :
			return Precedence.plus;
		case '-' :
			return Precedence.minus;
		case '/' :
			return Precedence.divide;
		case '*' :
			return Precedence.times;
		case '%' :
			return Precedence.mod;
		case ' ' :
			return Precedence.eos;
			default : return Precedence.operand;
		}
	}
	
	private void printToken(Precedence token){
		System.out.println(token);
	}
	
	private void push(Precedence element){
		if(this.top >= this.MAX_STACK_SIZE){
			System.out.println("this stack is full!");
			return;
		}
		this.stack[++top] = element;
	}
	
	private Precedence pop(){
		if(this.top == -1){
			System.out.println("this stack is empty!");
			return Precedence.eos;
		}
		return this.stack[top--];
	}
	
	public static void main(String[] args){
		char[] expr = {'a', '*', '(', 'b', '+', 'c', ')', '*', 'd', ' '};
		Postfix postfix = new Postfix(expr);
		postfix.postfix();
	}

}
