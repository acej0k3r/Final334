import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DecisionTree {
	class Node {
		String catName;
	    double value;
	    Node left;
	    Node right;

	    Node(String name,double value) {
	    	this.catName = name;
	        this.value = value;
	        right = null;
	        left = null;
	    }
	}
	
	
	//Variables of tree
	Node root;
	double minVal;
	

	ArrayList<String> catName = new ArrayList<String>();
	ArrayList<Double> entropy = new ArrayList<Double>();
	
	DecisionTree(ArrayList<Category> c){
		
		
		
		DataSet.drawLine();

		
		for(int i=0;i<c.size();i++) {
			
			catName.add(c.get(i).getCatname());
			entropy.add(c.get(i).getEntropy());
			
			
		}
		
	
		
		
		sortingBothArrayListAsc(entropy);
	    
	    minVal = -1; 
		add("START", minVal);
		for(int i=0;i<entropy.size();i++) {
			add(catName.get(i), entropy.get(i));
			add("YES",-1);
			
		}
	    
		add("NOT IN LIST", 100000);
		
	    
	    printLevelOrder(root);
		
		
	}
	
	private Node addRecursive(Node current,String name ,double value) {
	    if (current == null) {
	        return new Node(name,value);
	    }

	    if (value < current.value) {//YES
	    	if(name != "None") {
	        current.left = addRecursive(current.left, name ,value);
	    	}else {
    		return current;
	    	}
	    } else if (value >= current.value) {//NO
	    	
	        current.right = addRecursive(current.right, name,value);
	    	
	    }

	    return current;
	}
	
	
	public void add(String name, double value) {
	    root = addRecursive(root, name, value);
	}
	
	
	void sortingBothArrayListAsc(ArrayList<Double> list) {

	    for (int i = 0; i < list.size(); i++) {

	        for (int j = 0; j < list.size(); j++) {

	            if (list.get(i) < list.get(j)) {

	                Double temA = list.get(i);
	                String temS = catName.get(i); 
	                
	                list.set(i, list.get(j));
	                catName.set(i, catName.get(j));
	                
	                list.set(j, temA);
	                catName.set(j, temS);

	            }

	        }
	    }
	}

	    
	
	void printLevelOrder(Node root)
    {
        // Base Case
        if(root == null)
            return;
         
        // Create an empty queue for level order traversal
        Queue<Node> q =new LinkedList<Node>();
         
        // Enqueue Root and initialize height
        q.add(root);
        System.out.println("DECISION TREE");
        DataSet.drawLine();
         
        while(true)
        {
             
            // nodeCount (queue size) indicates number of nodes
            // at current level.
            int nodeCount = q.size();
            if(nodeCount == 0)
                break;
             
            //track original node per run
            int origNodeCount = nodeCount;
            // Dequeue all nodes of current level and Enqueue all
            // nodes of next level
            while(nodeCount > 0)
            {
                Node node = q.peek();
                
                
                
                
                if(node.catName != "YES" && node.catName != "START" && node.catName != "NOT IN LIST") {//right
                	if(nodeCount != 1) {
                        System.out.print("<" + node.catName + "(E = " + node.value + ")"+ "> ");

                	}else {
                		
                		if(origNodeCount != 1) {
                        System.out.println("<" + node.catName + "(E = " + node.value + ")"+ "> ");
                        System.out.println("  ______|		|			");
                        System.out.println("  |			|			");
                        	System.out.print("  V 			V			");
                		}else {
                			System.out.println("<" + node.catName + "(E = " + node.value + ")"+ "> ");
                            System.out.println("|		|				");
                            	System.out.print("V		V				");
                		}
                        	

                	}
                }else {//left first
                	if(node.catName != "START") {
                		if(node.catName != "NOT IN LIST")
                        System.out.print("<" + node.catName + "> ");
                		else
                         System.out.print("		<" + node.catName + "> ");


                	}else {//case when root
                        System.out.println("<" + node.catName + "> ");
                        System.out.println("|");
                        System.out.print("V");
                	}

                }
                
                
                
                q.remove();
                if(node.left != null)
                    q.add(node.left);
                if(node.right != null)
                    q.add(node.right);
                nodeCount--;
            }
            System.out.println();
        }
        
    }
    }
