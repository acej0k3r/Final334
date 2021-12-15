import java.util.Scanner;

public class LogMainf {
public static void main(String[] args) {
	
double ax = 0; 

double praxb = 0; 
double prAnumerator = 0;
double prBnumerator = 0; 
double prab = prAnumerator * prBnumerator; 
double totalCount= 250; 
double threshHold = 0.01; 
double lhs; 

double oi;
double ei;
double chi;
double rhs; 

Scanner sc = new Scanner(System.in);
String z1;

System.out.println("Start <press z>, to end <x>");


char z = 'z'; 

Scanner s2 = new Scanner(System.in);


while(z == 'z' || z == 'Z') {
	
	System.out.println("pr A x B :  A numerator");
	ax  = s2.nextDouble();

	
	praxb = ax * totalCount; 

	
	System.out.println("PrA & PrB:   Pr(A) : numerator, pr (B): numertaor");
	prAnumerator = s2.nextDouble();
	prBnumerator = s2.nextDouble();
	
	prab = (prAnumerator * prBnumerator); 
	
	
	lhs = mutualInformation(praxb, prab);
	
	
	if(lhs > threshHold) {
		System.out.println("Failed threshHold Test");
		System.out.println("(lhs < thresh) : (" + lhs + " < " + threshHold + ")" );
		
		drawLine();
		continue;
	}
	
	
	
	System.out.println("obtaining chi...");
	
	oi = ax;	
	ei = totalCount * (prAnumerator/totalCount) * (prBnumerator/totalCount); 
	
	System.out.println("(" + oi + " - " + ei + ")^2 / (" + ei + ")" );

	chi = Math.pow((oi - ei), 2) / ei; 
	rhs = chi/ (2* totalCount); 
	
	
	System.out.println("CHI/ 2N) = " + rhs);
	
	
	
	if(lhs < rhs) {

		
		System.out.println("Not statistically significant (lhs < rhs) = (" + lhs  + " < " + rhs + ")" );
	}else {
		System.out.println("Significant");
	}
	
	
	
	
	
	
	
	//
	System.out.println("... <z>");

	z1  = sc.next();
	z = z1.charAt(0); 
	
	drawLine();
	
}







}



static void drawLine() {
	System.out.println("____________________________");
}

static double mutualInformation(double inte, double ab) {
	double mI = 0; 
	
	System.out.println("Mi[ aXb: " + inte + " / a & b: " + ab + " ] = ");
	
	mI= log2(inte, ab);
	
	
	System.out.println(mI);
	return mI;
	
	
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
