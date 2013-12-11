package lists;

public class PolynomialsCircular {
	int coef;
	int expon;
	PolynomialsCircular link;
	PolynomialsCircular avail;
	
	public PolynomialsCircular(int coef, int expon, PolynomialsCircular link){
		this.coef = coef;
		this.expon = expon;
		this.link = link;
	}
	
	public PolynomialsCircular attach(int coefficient, int exponent, PolynomialsCircular ptr){
		PolynomialsCircular temp;
		temp = new PolynomialsCircular(coefficient, exponent, null);
		temp.coef = coefficient;
		temp.expon = exponent;
		ptr.link = temp;
		ptr = temp;
		return ptr;
	}
	
	public PolynomialsCircular padd(PolynomialsCircular a, PolynomialsCircular b){
		boolean done = false;
		PolynomialsCircular starta = a;
		a = a.link;
		b = b.link;
		PolynomialsCircular d = this.getNode();
		d.expon = -1;
		PolynomialsCircular lastd = d;
		
		do{
			switch(this.compare(a.expon, b.expon)){
			case -1:
				lastd = this.attach(b.coef, b.expon, lastd);
				b = b.link;
				break;
			case 0:
				if(a == starta){
					done = true;
				} else {
					int sum = a.coef + b.coef;
					if(sum > 0){
						lastd = this.attach(sum, a.expon, lastd);
					}
					a = a.link;
					b = b.link;
				}				
				break;
			case 1:
				lastd = this.attach(a.coef, a.expon, lastd);
				a = a.link;
				break;
			}
		} while(!done);
		lastd.link = d;
		return d;
	}
	
	public PolynomialsCircular getNode(){
		PolynomialsCircular node;
		if(this.avail != null){
			node = avail;
			avail = avail.link;
		} else {
			node = new PolynomialsCircular(0, 0, null);
		}
		return node;
	}
	
	public void retNode(PolynomialsCircular ptr){
		ptr.link = this.avail;
		this.avail = ptr;
	}
	
	private int compare(int a, int b){
		if(a > b){
			return 1;
		} else if(a < b){
			return -1;
		} else{
			return 0;
		}
	}
	
	public static void main(String[] args){
		PolynomialsCircular a = new PolynomialsCircular(3, 14, null);
		PolynomialsCircular temp = a;
		temp = a.attach(2, 8,temp);
		temp = a.attach(1, 0, temp);		
		PolynomialsCircular heada = new PolynomialsCircular(0, -1, null);
		heada.link = a;
		temp.link = heada;
		PolynomialsCircular b = new PolynomialsCircular(8, 14, null);
		temp = b;
		temp = b.attach(-3, 10, temp);
		temp = b.attach(10, 6, temp);
		PolynomialsCircular headb = new PolynomialsCircular(0, -1, null);
		headb.link = b;
		temp.link = headb;
		PolynomialsCircular d = new PolynomialsCircular(0, 0, null);
		PolynomialsCircular result = d.padd(heada, headb);
		
		result.retNode(result);
		result.retNode(result);
	}

}
