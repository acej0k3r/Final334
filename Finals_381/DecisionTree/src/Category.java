import java.util.ArrayList;
import java.util.Vector;

public class Category {

	
	public String catName;
	//maximum number of unique values (upperBound)
	public int upperBound; 
	//uniqueQuestions possible per category 
	public int totalQPossible; 
	
	
	//Total value of Count column only displayable if CatName = "Count"
	private int totalCount = 0; 
	
	
	
//value contains all value within a category
	public ArrayList<String> value = new ArrayList<String>();
//uniqueValue contains the mapping
	public ArrayList<String> uniqueValue = new ArrayList<String>();

	
//The digit representation 
	public ArrayList<Integer> digitValue = new ArrayList<Integer>(); 
	public ArrayList<Integer> uniqueDigitValue = new ArrayList<Integer>(); 
	
	//category entropy
	public double entropyVal; 

	
	Category(String name){
		catName = name;
	}
	
	public void printValueAndTransform() {
	
		
		System.out.print(catName + "(" + value.size() +")"+ ": ");
		
		for(int i=0;i<value.size(); i++) {
			System.out.print(" -> "+value.get(i));
		}
		
		//while printing we convert string to its corresponding unique digit
		transform();
		
		
	}
	
	public String getCatname() {
		
		return catName;
	}
	
	public Double getEntropy() {
		return entropyVal;
	}
	
	
	
	
	public void transform() {		
		String[] unique = value.stream().distinct().toArray(String[]::new);
		for(int i=0; i<unique.length; i++) {
			uniqueValue.add(unique[i]); 
			uniqueDigitValue.add(i); 
		}
		
		totalQPossible = uniqueDigitValue.size();

		for(int i=0; i <value.size(); i++) {
			
			if(!this.catName.equals("Count")) {
				for(int j=0; j< uniqueValue.size(); j++) {
				
					if(value.get(i).equals(uniqueValue.get(j))){
					digitValue.add(j); 
					break;
					}
				}//eof loop j
			
			}else { // count column
				int temp;
				
				for(int x=0; x<value.size();x++) {
					temp = Integer.parseInt(value.get(x));
					digitValue.add(temp);
					totalCount += temp;
					
				
					
					i = value.size()-1;
				}
				
				 
			
			}
		
		upperBound = (uniqueDigitValue.size() -1);
	  }//eof loop i
	
  }//end of transform
	
	
	public void showPairs() {
		
	
		
		System.out.print(catName + ": ");
		for(int i=0; i< uniqueValue.size(); i++) {
			if(i!=0)
			System.out.print(", " + uniqueValue.get(i) + " = " + uniqueDigitValue.get(i));
			else
			System.out.print(uniqueValue.get(i) + " = " + uniqueDigitValue.get(i));

		}
		System.out.print("    ---[" + 0 + "..." + upperBound + "]");
		System.out.println();
	}
	
	
	
	public void showDigitValues() {
		
		System.out.print(catName + ": ");
		for(int i=0;i<digitValue.size(); i++) {
			System.out.print("-> " + digitValue.get(i));
		}
		
		if(this.catName.equals("Count"))
		System.out.println(" | Total Count = " + getTotalOfCount());
		
		System.out.println();
	}
	
	

	
	public int getTotalOfCount() {
		if(this.catName.equals("Count")) {
			return totalCount;
		}else {
			System.out.println("This category is not the Count category so total count doesnt exist!");
			return -1;
		}
	}
	

	
	
	
	
	//PROBABILITY PORTION OF EACH CATEGORY
	
	
	double log2(double i,double j)
	{
	    return  (Math.log((i/j)) / Math.log(2));
	}	
	
	
	double findProbWithLog2(int i, int j) {
		double x = (double)i;
		double y = (double)j;
		
		if(i!=0) {
			return ((x/y) * log2(y,x));
		}else {
			return 0;
		}
		
	}

	
	
	void informationVal() {
		//iVal will hold the summation of all current questions
		double iVal = 0;
		double eVal = 0;

		double temp = 0; 
		ArrayList<Double> entropyArr = new ArrayList<Double>(); 
		ArrayList<Integer> countArr = new ArrayList<Integer>(); 
		int typeSize = DataSet.typeCat.value.size(); 
		int countSize = DataSet.countCat.value.size(); 
		// a and b will be used to do the probability calculation
		//where a holds all single type count
		//where b holds total questions
		ArrayList<Integer> a = new ArrayList<Integer>();
		int b=0; 
		
		
		String pos = " + ";
	
				for(int i=0; i< totalQPossible; i++) {//loop thourgh all possible questions to be asked by category
					DataSet.drawLine();
					System.out.println("\nI(" + catName +" : "+ uniqueValue.get(i) + " -> type)");
					a.clear();
					b =0;
					iVal=0.0;
					temp =0;
							for(int r=0; r<countSize; r++) {//loop finds total count for specific question >> assign to b
								
								if(digitValue.get(r).equals(i)) {
									//gets corresponding count value for the digit in question
									b+= DataSet.countCat.digitValue.get(r);
									a.add(DataSet.countCat.digitValue.get(r));
								}else {
									a.add(0);
								}
								
						
							}
							countArr.add(b);
							System.out.println("Total = " + b);
			
					
					for(int j=0; j<typeSize; j++) {//loop through each type to gain probability of all types in form of summation
						if(j!=0) {
							pos = " + ";
						}else {
							pos = "";
						}
						int numerator = a.get(j);
						System.out.print(pos+"Pr(TYPE : "+ DataSet.typeCat.value.get(j) + " | Q :" + value.get(i) + " )");
						
						temp= findProbWithLog2(numerator, b);
						iVal += temp; 
						System.out.println(" = " + temp); 
						

					}//for2
					System.out.println("V" + (i+1) + " = " + iVal);
					System.out.println();
				
					entropyArr.add(iVal);
					
					
				}//for1
		
				//Get the entropy
			DataSet.drawStar();
			eVal = entropyVal(entropyArr, countArr);
			System.out.println("Entropy Value (" + catName +  ") = " + eVal);
			entropyVal = eVal;
			DataSet.drawStar();
	}
	
	
	

	double entropyVal(ArrayList<Double> v, ArrayList<Integer> count) {
		double eVal = 0;
		double temp =0;
		String pos = " + ";
		for(int i=0; i<v.size();i++) {
			if(i!=0) {
				pos = "+";
			}else {
				pos = "";
			}
			
				temp = v.get(i) * ((double)count.get(i)/(double) DataSet.totalCount);
				System.out.print(pos + " V"+(i+1)+ " * (" + count.get(i) + "/" + DataSet.totalCount + ") = ");
				System.out.println(temp);
				eVal += temp; 
		}
		

		return eVal; 

	}

	

}
