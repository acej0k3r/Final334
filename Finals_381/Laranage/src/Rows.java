import java.util.Vector;

public class Rows {
String name; 
int size;
Vector x = new Vector(); 



Rows(String n, int[] in){
	name = n;
	for(int i=0;i<in.length; i++) {
		x.add(in[i]); 
	}
	size = x.size();
}



Rows(String n, double[] fin){
	name = n;
	for(int i=0;i<fin.length; i++) {
		x.add(fin[i]); 
	}
}


void printRow(){
	System.out.println(name +" <rows> : " + x);
}

// gets x1
String displayWithX(int r) {
	String data = "";
	for(int i =0; i< x.size(); i++) {
		if(i != r) {
			data += "(" + name +" - " + x.get(i) + ")";
		}
	}
	
	
	return data; 
}

String displayWithNoX(int r) {
	String data = "";
	for(int i =0; i< x.size(); i++) {
		if(i != r) {
			data += "(" + x.get(r) +" - " + x.get(i) + ")";
		}
	}
	
	
	return data; 
}



}
