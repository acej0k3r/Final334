import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UtilityFinals {
public static void main(String[] args) {
	
	int a = 2;
	int b = 4; 
	boolean shdLoop = true;
	int temp; 
	double entropy = 0;
	
	int vCounter = 0; 
	ArrayList<Double> vi = new ArrayList<Double>();
	ArrayList<Double> si = new ArrayList<Double>();

	
	int totalCount = 100; 
	int count = 0; 
	
	Scanner sc = new Scanner(System.in);
	
	char z = 'z'; 
	
while(shdLoop) {	
	
	while(z == 'z') {
	
		System.out.println("a: b:");
		a = sc.nextInt();
		b = sc.nextInt();
		double r =  findProbWithLog2(a, b);
		
		System.out.println("Multiply by: ");
		temp = sc.nextInt();
		
		
		
			System.out.println(temp + " * (" + a + "/" + b + ")" + " * log_2 (" + b + "/" + a + ")  =  " + (temp*r));
			
			
			
			vCounter += (r * temp);
			
			System.out.println("...CurrentSet<z> continueNextSet<c>  loop1<x>");
		z = sc.next().charAt(0);
			
		if(z == 'c') {
			vi.add(r * temp);
			z = 'z';
		}
		
		drawLine();
	}//end while
	
	
	
	for(int i = 0; i < vi.size(); i++) {
		System.out.println("Enter count for v" + i);
		count = sc.nextInt();
		
		entropy += (vi.get(i) * ((double)count / (double)totalCount));
	}
	
	System.out.println("Entropy: " + entropy);
	si.add(entropy);
	entropy = 0; 
	System.out.println("...NEXT<z>, END <x>");

	z = sc.next().charAt(0);
	if(z == 'x') {
		shdLoop = false;
	}
	
	
	
}//shdloop
			

Collections.sort(si);

System.out.println(si.get(0));

System.out.println("Ended");

}


static void drawLine() {
	System.out.println("_____________________________________");
}

static double log2(double i,double j)
{
    return  (Math.log((i/j)) / Math.log(2));
}	


static double findProbWithLog2(int i, int j) {
	double x = (double)i;
	double y = (double)j;
	
	if(i!=0) {
		return ((x/y) * log2(y,x));
	}else {
		return 0;
	}
	
}

}
