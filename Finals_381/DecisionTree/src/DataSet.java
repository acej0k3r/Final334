import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;

public class DataSet {

//Variables	
String name = "input.txt"; 
int numberOfCategory = 3;
ArrayList<Category> cat = new ArrayList<Category>();

static Category typeCat;
static Category countCat;
//attribute category will have all categories that work on attribute
ArrayList<Category> attrCat = new ArrayList<Category>();

static int totalCount = -1;



//Code for additional data if the user wants to input
boolean isFileDataStartsOnNewLine = false;
boolean isUserContinue = true;
int newRows = 0;
String newLine = "";







public DataSet() {
		
	
	while(isUserContinue) {
	
		readFileAndFillArrays(name);
		
		   System.out.println("WE WILL PRINT EACH CATEGORY AND ITS CONTENTS: \n");
		      for(int i=0;i<cat.size();i++) {
		    	  cat.get(i).printValueAndTransform();
		    	  System.out.println();
		      }
		      
		      
		      
		      drawLine();		    
		    
		    
		    System.out.println("CONVERSION OF STRING TYPE TO INTEGERS: \n");
		      for(int i=0;i<cat.size();i++) {
		    	  cat.get(i).showPairs();
		    	  System.out.println();
		      }
		      
		      
		      drawLine();			
			
		    System.out.println("CATEGORY WITH NUMBER REPRESENTATION: \n");
		      for(int i=0;i<cat.size();i++) {
		    	  cat.get(i).showDigitValues();
		    	  System.out.println();
		      }

		      
		      drawLine();
			//seperate the array list into pieces so we can do our questioning more clearly
		    seperateCatArrToSpecifics();
		    
		    totalCount = countCat.getTotalOfCount();
		 
		    
		    
		    findEntropyForAllCat();	  
		    
		    
			DecisionTree dt = new DecisionTree(attrCat);
			
			
			drawLine();
			

			
			
			askForNewInputThenWriteToFile();
			
			
			
	}
		    
}	

static void drawLine() {
	System.out.println("____________________________________________________________________________________");

}

static void drawStar() {
	System.out.println("************************************************************************************");

}


String readFileAndFillArrays(String n){
	String data = "";
	int rows = 0; 
	int counter = 0; 
	String[] titles = null;
	String[] values = null; 
	
	try {
	      File myObj = new File(n);
	      Scanner myReader = new Scanner(myObj);
	      String newData = "";
	      String emptyData[];
	      
	      while (myReader.hasNextLine()) {
	    	  
	    	  
	        newData = (myReader.nextLine() + "\n");
	        
	        //We need to get empty data while reading the file in case the user left the file ending with blank spaces
	       emptyData = getStringsWithoutSpaces(newData);
	        
	     if(newData.length() != 1 && emptyData.length != 0) {   
	    	 data += newData;
	        
	    	 	if(rows == 0) {

	    	 		titles = getStringsWithoutSpaces(newData);
	         	
	    	 		System.out.println("There are a total of " + titles.length + " categories: ");
        		
	    	 		//make category objects based on each category
	    	 		//cat[0] = type
        		
        		
	    	 		for(int i=0; i< titles.length; i++) {
	        		
	    	 			//Show all categories 
	    	 			Category catObj = new Category(titles[i]); 
	    	 			cat.add(catObj); 
	    	 			System.out.println(titles[i]);
	        		
	        		
	        		
	    	 		}
	        


	        	System.out.println("\n\n");
	        	
	        	
	      
	    	 	}else{//If its all values itll be stored in values
	        	
	        	 values = getStringsWithoutSpaces(newData); 
	        	
	        	 //put all values into categories
	        		for(int j=0; j<titles.length; j++) {
	        			cat.get(j).value.add(values[j]);
	        		}
	        	
	        		counter = 0;
	        	
	        	}//END if rows ==0
	        
	        
	        
	        	rows++;
	        
	     	}else if(newData.length() == 1){//END if(newData.length() != 1)
	     		//we know that the file ended on a new line
	     		
	     		isFileDataStartsOnNewLine = true;
	     		
	     		
	     	}else if(emptyData.length == 0) {
	     		isFileDataStartsOnNewLine = false;

	     	}
	       
	     }//END WHILE
   
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	
	 System.out.println(data);
	 return data; 
	
	
}






void askForNewInputThenWriteToFile() {
	
	
	
	
	
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Do you want to add any additional inputs? <NOTE: if you do the program will add them to the input file!>");
	
	
	System.out.println("How many rows of input do you want to add? \n<NOTE: typing 0 will close program because no rows will be added>");
	int temp = 0; 
	
	temp = sc.nextInt(); 
	
	while(temp < 0) {
		
		
		System.out.println("Invalid value! Either no rows added rows = 0, or a positive number should be added rows > 0");

		System.out.println("How many rows of input do you want to add? ");

		temp = sc.nextInt(); 
	}
	
	
	
	newRows += temp; 
	
if(temp != 0) {//if the user enters not 0 then we would loop as many times as the user wants to add a row
		//if 0 was entered we dont add a new row
	try{
		    
		    FileWriter fw = new FileWriter(name,true); //the true will append the new data
		 
		  
		drawLine();
						
		
		System.out.println("Great! You will be adding data of " + temp + " Rows: ");
	
		System.out.println("You must also add input data for each category! ");
				
		
		for(int i=0; i<temp; i++) {
			String line = "";
			int size = cat.size();
			drawLine();
			System.out.println("For row :" + (i+1));
			for(int j =0; j<size; j++) {
			
				System.out.println("(CATEGORY-> " + cat.get(j).catName + ") :");
				
				
				if(j!= (size-1)) {
					line = sc.next();
					
				}else {//check if it will be count category
					int x; 
					System.out.println("Count must be an integer and cannot be negative");
					x = sc.nextInt();
				
					while(x < 0) {
						System.out.println("Count must be an integer and cannot be negative \n ReEnter Value of Positive Integer");
						x = sc.nextInt();
					}
				
					line = Integer.toString(x);
				}
				

					
					line = (line.substring(0, 1).toUpperCase() + line.substring(1));
					
					newLine += (line + "\t\t");
			}
			
			if(isFileDataStartsOnNewLine) {
				fw.write(newLine + "\n");


			}else {
				isFileDataStartsOnNewLine = false; 
				fw.write("\n");
				fw.write(newLine + "\n");

			}
			
			newLine = "";
		}
		
		
		//must reset all lists

		cat.clear();
		typeCat = null;
		countCat = null;
		attrCat.clear();
		totalCount = -1; 
		
		
		drawLine();
		
		
		  fw.close();
   }//end of write file
	catch(IOException ioe){
		    System.err.println("IOException: " + ioe.getMessage());
	}				
		
		
		
}else {//No new Rows should be added
		System.out.println("The total number of new rows added = " + newRows);
		System.out.println("Since no new data will be added the system will close! ");	
		System.out.println("\n<PROGRAM ENDED>");
		isUserContinue = false; 
		
		
}//END OF IF
}




















String[] getStringsWithoutSpaces(String line) {
	//Replaces all tabs and white space with commas
	String trim;
	trim = line.replaceAll("(^\\s+|\\s|\\t|\\s+$)", ",");
	//There will be one or more tabs replace those with single ,
	trim = trim.replaceAll(",{1,}", ",");
	//Finally split string into parts by , 
	String[] parts = trim.split(","); 
	return  parts;
}


void seperateCatArrToSpecifics(){
	for(int i=0;i<cat.size();i++) {
		if(i!=0 && i != cat.size()-1) {
			attrCat.add(cat.get(i)); 	
		
		}else if(i == 0) {
			typeCat = cat.get(i);
		}else {
			countCat = cat.get(i);
		}
	}
	
}

void printArrayList(ArrayList<Category> ar) {
	for(int i=0;i<ar.size();i++) {
		System.out.println(ar.get(i).catName);
	}
}



//PROBABILITY PORTION

	

void findEntropyForAllCat() {

	for(int i=0;i<attrCat.size();i++) {
	
		attrCat.get(i).informationVal();
	}
}





}



