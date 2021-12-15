import java.util.ArrayList;
import java.util.Vector;

public class Larange {

	double[] y = {13.283,21.076,16.44,2.749,7.852,7.152,3.507,20.865,15.006};
	ArrayList<Rows> domx = new ArrayList<Rows>();
	
	
	
	Larange(){
		System.out.println("program started...");
		addInit(); 
		
		printVector(domx);

		printY();		
		drawLine();
		
		for(int i=0;i<domx.size();i++) {
			
			System.out.println("X" + (i+1) + " formula representation: ");
			displayLarangeForm(domx.get(i));
			
			drawLine(); 

		}
		
	}
	
	
	//adds to functions 
	void addInit(){
		double[] xArr1 = {8.76,9.559,5.393,8.622,7.797,9.968,6.115,2.662,8.401};
		//name, array of x
		Rows x1 = new Rows("x1", xArr1);
		
		
		double[] xArr2 = {73.201,27.959,68.224,12.303,83.466,51.702,42.621,94.934,54.954};
		//name, array of x
		Rows x2 = new Rows("x2", xArr2);
		
		
		
		domx.add(x1);
		domx.add(x2);
	
		
		//Array containing alll values
	
		
		
		
		
	}
	
	
	void drawLine() {
		System.out.println("__________________________________________________");
	}
	
	void printVector(ArrayList<Rows> v) {
		for(int i=0;i<v.size();i++) {
			v.get(i).printRow();;
		}
	}
	
	void printY() {
		System.out.print("y <rows> : [");
		
		for(int i=0;i<y.length;i++) {
			System.out.print(y[i] + ", ");
		}
		
		System.out.println("]");
	}
	
	
	
	void displayLarangeForm(Rows v1) {
		for(int i =0; i < y.length; i++) {
			System.out.println("\n");
			if(i!=0) {
				System.out.print(" + ");
			}
			System.out.print(y[i] + " (");

				System.out.print(v1.displayWithX(i));

				
			
			
			System.out.println(")");
			
			System.out.println("------------------------------------------------------------");
		
				System.out.println(v1.displayWithNoX(i));
			
		
		}//for i
		
		drawLine();
	}
	
	
	
	
	void doLarangeMath() {
		
	}
	
	
}
