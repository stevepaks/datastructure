package lists;

public class Polynomials {
	int coef;
	int expon;
	Polynomials link;
	
	public Polynomials(int coef, int expon, Polynomials link){
		this.coef = coef;
		this.expon = expon;
		this.link = link;
	}
	
	Polynomials attach(int coefficient, int exponent, Polynomials ptr){
		Polynomials temp;
		temp = new Polynomials(coefficient, exponent, null);
		temp.coef = coefficient;
		temp.expon = exponent;
		ptr.link = temp;
		ptr = temp;
		return ptr;
	}
	
	Polynomials padd(Polynomials a, Polynomials b){
		Polynomials rear = new Polynomials(0, 0, null);
		Polynomials front = rear;
		
		while (a != null && b != null){
			switch(compare(a, b)){
			case -1:
				rear = attach(b.coef, b.expon, rear);
				b = b.link;
				break;
			case 0:
				int sum = a.coef+b.coef;
				if(sum > 0){
					rear = attach(sum, a.expon, rear);					
				}
				a = a.link;
				b = b.link;
				break;
			case 1:
				rear = attach(a.coef, a.expon, rear);
				a = a.link;				
			}
		}
		for(; a != null ; a = a.link){
			rear = attach(a.coef, a.expon, rear);
		}
		for(; b != null ; b = b.link){
			rear = attach(b.coef, b.expon, rear);
		}
		rear.link = null;
		front = front.link;
		return front;
	}
	
	int compare(Polynomials a, Polynomials b){
		if(a.expon > b.expon){
			return 1;
		} else if(a.expon < b.expon){
			return -1;
		} else{
			return 0;
		}
	}
	
	public static void main(String[] args){
		Polynomials a = new Polynomials(3, 14, null);
		Polynomials temp = a;
		temp = a.attach(2, 8,temp);
		a.attach(1, 0, temp);
		Polynomials b = new Polynomials(8, 14, null);
		temp = b;
		temp = b.attach(-3, 10, temp);
		b.attach(10, 6, temp);
		Polynomials d = new Polynomials(0, 0, null);
		Polynomials result = d.padd(a, b);
	}

}
