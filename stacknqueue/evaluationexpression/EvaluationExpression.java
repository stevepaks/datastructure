package stacknqueue;

public class EvaluationExpression {
	final static int MAX_STACK_SIZE = 100;	
	final static int MAX_EXPR_SIZE = 100;
	
	enum Precedence {
		lparen, rparen, plus, minus, times, divide, mod, eos, operand
	}
	
	int[] stack;
	char[] expr;
	char symbol;
	int n;
	int top;
	
	public EvaluationExpression(char[] expr){
		this.n = 0;
		this.top = -1;
		this.symbol = 0;
		this.stack = new int[MAX_STACK_SIZE];
		this.expr = new char[MAX_EXPR_SIZE];
		this.expr = expr;
	}
	
	public int eval(){
		Precedence token = this.getToken();
		while(token != Precedence.eos){
			if(token == Precedence.operand){
				this.push(symbol-'0');
			} else{
				int op2 = this.pop();
				int op1 = this.pop();
				if(token == Precedence.plus){
					this.push(op1 + op2);
				}else if(token == Precedence.minus){
					this.push(op1 - op2);
				}else if(token == Precedence.times){
					this.push(op1 * op2);
				}else if(token == Precedence.divide){
					this.push(op1 / op2);
				}else if(token == Precedence.mod){
					this.push(op1 % op2);
				}
			}
			token = getToken();
		}
		return this.pop();
	}
	
	public Precedence getToken(){
		symbol = expr[n++];
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
	
	void push(int element){
		if(this.top >= this.MAX_STACK_SIZE){
			System.out.println("this stack is full!");
			return;
		}
		this.stack[++top] = element;
	}
	
	int pop(){
		if(this.top == -1){
			System.out.println("this stack is empty!");
			return 0;
		}
		return this.stack[top--];
	}
	
	public static void main(String[] args){
		char[] expr = {'6', '2', '/', '3', '-', '4', '2', '*', '+', ' '};
		EvaluationExpression eval = new EvaluationExpression(expr);
		System.out.println(eval.eval());
	}

}
