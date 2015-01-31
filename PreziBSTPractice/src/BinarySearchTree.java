import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class BinarySearchTree {

	public Node root;
	public int Size=0;
    ArrayList<Integer> preorder=new ArrayList<>();
    ArrayList<Integer> posorder=new ArrayList<>();
    ArrayList<Integer> inorder=new ArrayList<>();
    ArrayList<Integer> OriginalTree=new ArrayList<>();
	
    
    public void Delete(int value)
    {
    	@SuppressWarnings("rawtypes")
		Node node=findNodeByVal(value);
    	
    	//Case 1 is when the node is leaf itself
    	if(node.left==null && node.right==null)
    	{
    		WhenNodeIsLeaf(node);
    		
    	}
    	//case 3 when node has two childs
    	else if(node.right!=null && node.left!=null)
    	{
    		WhenNodeHasTwoChilds(node);
    	}
    	//Case 2 is when node has one child
    	else if(node.left!=null)
    	{
    		WhenNodeHasOneChild(node);
    	}
    	else if(node.right!=null)
    	{
    		WhenNodeHasOneChild(node);
    	}
    	
    	
    }
    
    private void WhenNodeIsLeaf(Node node)
    {
    	if(node.parent.left==node)
		{
			node.parent.left=null;
			//return true;
			
		}
		else if(node.parent.right==node)
		{
			node.parent.right=null;
			//return true;
		}
    }
    
    private void WhenNodeHasOneChild(Node node)
    {
    	if(node.parent.left==node)
    	{
    	
    		if(node.left!=null)
    		{
    			node.parent.left=node.left;
    		}
    		else if(node.right!=null)
    		{
    			node.parent.left=node.right;
    		}
    		else
    		{
    			WhenNodeIsLeaf(node);
    		}
    		
    	}
    	else if(node.parent.right==node)
    	{
    		
    		if(node.left!=null)
    		{
    			node.parent.right=node.left;
    		}
    		else if(node.right!=null)
    		{
    			node.parent.right=node.right;
    		}
    		else
    		{
    			WhenNodeIsLeaf(node);
    		}
    		
    	}
    }
    
   
    int getMaxWidth(Node root)
    {
      int maxWidth = 0;   
      int width;
      int h = height(root);
      int i;
       
      /* Get width of each level and compare 
         the width with maximum width so far */
      for(i=1; i<=h; i++)
      {
        width = getWidth(root, i);
        if(width > maxWidth)
          maxWidth = width;
      }     
       
      return maxWidth;
    }
    
    int height(Node node)
    {
       if (node==null)
         return 0;
       else
       {
         /* compute the height of each subtree */
         int lHeight = height(node.left);
         int rHeight = height(node.right);
         /* use the larger one */
        
         return (lHeight > rHeight)? (lHeight+1): (rHeight+1);
       }
    }
     
    /* Get width of a given level */
    int getWidth(Node node, int level)
    {
         
      if(node == null)
        return 0;
       
      if(level == 1)
        return 1;
                 
      else if (level > 1)
      {
        return getWidth(node.left, level-1) + getWidth(node.right, level-1);
      }
      
      return 0;
    }
    
    private void WhenNodeHasTwoChilds(Node node)
    {
    	
    	Node minnode= findMinimumNode(node.right);
    	
    	WhenNodeHasOneChild(minnode);
    	minnode.left=node.left;
		minnode.right=node.right;
		minnode.parent=node.parent;
		
		if(node.parent==null)
		{
			root=minnode;
		}
		
		else if(node.parent.left==node)
    	{
    		node.parent.left=minnode;
    	}
    	
    	else if(node.parent.right==node)
    	{
    		node.parent.right=minnode;
    	}
    }
    
	public void insert(int value)
	{
		Node node= new Node<>(value);
		if(root==null)
		{
			root=node;
			return;
		}
		
		InsertRec(root,node);
		Size++;
	}
	
	
	
	public void InsertRec(Node latestroot,Node node)
	{
		if(latestroot.value > node.value)
		{
			if(latestroot.left==null)
			{
				node.parent=latestroot;
				latestroot.left=node;
				return;
			}
			
			InsertRec(latestroot.left,node);
			
		}
		
		else 
		{
			if(latestroot.right==null)
			{
				node.parent=latestroot;
				latestroot.right=node;
				return;
			}
			
			InsertRec(latestroot.right,node);
		}
	}
	
	public int findMinimum()
	{
		if(root==null)
		{
			return 0;
		}
		Node currNode=root;
		
		while(currNode.left!=null)
		{
			currNode=currNode.left;
		}
		
		return currNode.value;
	}
	
	public Node findMinimumNode(Node n)
	{
		if(n==null)
		{
			return n;
		}
		Node currNode=n;
		
		while(currNode.left!=null)
		{
			currNode=currNode.left;
		}
		
		return currNode;
	}
	
	public int findMinimum(Node n)
	{
		if(n==null)
		{
			return 0;
		}
		Node currNode=n;
		
		while(currNode.left!=null)
		{
			currNode=currNode.left;
		}
		
		return currNode.value;
	}
	
	public Node findMaximumNode(Node n)
	{
		if(n==null)
		{
			return n;
		}
		
		Node currNode=n;
		
		while(currNode.right!=null)
		{
			currNode=currNode.right;
		}
		
		return currNode;
	}
	
	public int findMaximum(Node n)
	{
		if(n==null)
		{
			return 0;
		}
		
		Node currNode=n;
		
		while(currNode.right!=null)
		{
			currNode=currNode.right;
		}
		
		return currNode.value;
	}
	
	public int findMaximum()
	{
		if(root==null)
		{
			return 0;
		}
		
		Node currNode=root;
		
		while(currNode.right!=null)
		{
			currNode=currNode.right;
		}
		
		return currNode.value;
	}
	
	public void WriteTreeToFile(ArrayList<Integer> intlist, String type)
	{
		 BufferedWriter writer = null;
	        try {
	           
	        	//File file = new File("C:/Users/mua/Desktop/bst.txt");
	        	File desktop = new File(System.getProperty("user.home"), "Desktop");
	        	File bstfile= new File(desktop+"/inputnew.txt");
	            // if file doesnt exists, then create it
	            if (!bstfile.exists()) {
	            	bstfile.createNewFile();
	            }

	            // This will output the full path where the file will be written to...
	          //  System.out.println(file.getCanonicalPath());

	            writer = new BufferedWriter(new FileWriter(bstfile,true));
	           // writer.write("This is "+type+" traversal");
	            //writer.write(System.lineSeparator());
	            for(int i=0;i<intlist.size();i++)
	            {
	                writer.write(intlist.get(i)+"");
	                writer.write(System.lineSeparator());
	            }
	           // writer.write(System.lineSeparator());
	            //writer.write("This is "+type+" traversal end");
	            //writer.write(System.lineSeparator());
	            //writer.write(System.lineSeparator());
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                // Close the writer regardless of what happens...
	                writer.close();
	            } catch (Exception e) {
	            }
	        }
	}
	
	
	public Node findNodeByVal(int value) {
	    // Finds the node that contains the value and returns a reference to the node.
	    // Returns null if value does not exist in the tree.                
	    if (root== null) return null;
	    else
	    	return findNode(root, value);
	    
	}
	
	public Node findNode(Node n,int value)
	{
		if (n.value == value) {
	        return n;
	    } 
		
		else if(n.value > value) 
		{
	        Node left = findNode(n.left, value);
	     
	        if (left != null) 
	        {
	            return left;
	        }
	    }
	    else
	    {
	    	   Node right = findNode(n.right, value);
	   
	    	   if(right!=null)
	    	   {
	            return right;
	    	   }
	    }
		
		return null;
		
	    }
		
	
	
	public ArrayList<Integer> ReadIntFromFile(String filepath)
	{
		File fil = new File(filepath);
		FileReader inputFil;
		ArrayList<Integer> intlist= new ArrayList<Integer>();
		try {
			inputFil = new FileReader(fil);
			BufferedReader in = new BufferedReader(inputFil);
			int i = 0;
			String s;
			try {
				s = in.readLine();
				while (s != null) {
				    // Skip empty lines.
				    s = s.trim();
				    if (s.length() == 0) {
				        continue;
				    }

				    intlist.add(Integer.parseInt(s)); // This is line 19.
//				    System.out.println(intlist.get(i));
//				    System.out.println("\n");
					s = in.readLine();
				    i++;
				    
				}
				in.close();
				
			} 
				catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return intlist;
		
	}
	
	public ArrayList<Integer> ReadIntFromFile1(String filepath)
	{
		File fil = new File(filepath);
		FileReader inputFil;
		ArrayList<Integer> intlist= new ArrayList<Integer>();
		try {
			inputFil = new FileReader(fil);
			BufferedReader in = new BufferedReader(inputFil);
			int i = 0;
			String s;
			try {
				s = in.readLine();
				while (s != null) {
				    // Skip empty lines.
					String[] splitstring= s.split("TERM");
				   // s = s.trim();
				    if (s.length() == 0) {
				        continue;
				    }
                    for(int j=0;j<splitstring.length;j++)
                    {
				    intlist.add(Integer.parseInt(splitstring[j])); 
                    }

					s = in.readLine();
				    i++;
				    
				}
				in.close();
				
			} 
				catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return intlist;
		
	}
	
	
	public Node buildBinarytreefromPostOrder(int[] post, int start, int end)
	{
	    if (post[end] < post[start])
	        return null;

	    Node root = new Node(post[end]);

	    if (post[end] ==post[start])
	        return root;
 
	    int i;
	    for (i = end; i >= start; i--)
	        if (post[i] < root.value) 
	            break;

	    OriginalTree.add(root.value);
	    root.left = buildBinarytreefromPostOrder(post, start, i);
	    OriginalTree.add(root.left.value);
	    root.right = buildBinarytreefromPostOrder(post, i + 1, end - 1);
	    OriginalTree.add(root.right.value);
	   
	    return root;
	}
	
	
	public void PrintFromFileOriginalTree()
	{
		File desktop = new File(System.getProperty("user.home"), "Desktop");
		ArrayList<Integer> intlist= ReadIntFromFile1(desktop+"/input001.txt");
		int[] intarr = new int[intlist.size()];
	    for (int i=0; i < intarr.length; i++)
	    {
	    	intarr[i] = intlist.get(i).intValue();
	    }
	    //System.out.print("The size of int arr " + intarr.length);
		Node n=buildBinarytreefromPostOrder(intarr, 0, intarr.length-1) ;
		
		printOriginalTree(OriginalTree);
	}
	
	public void printOriginalTree(Node n)
	{
		while(n!=null)
		{
			System.out.print(" " + n.value);
			printOriginalTree(n.left);
			printOriginalTree(n.right);
		}
	}
	
	public void printOriginalTree(ArrayList<Integer> n)
	{
		if(n!=null)
		{
			System.out.print("The orignal tree inserted was::");
			for(int i=0;i<n.size();i++)
			{
				System.out.print(" " + n.get(i));
			}
		}
	}
	
	public void printInorder()
	{
		printInOrderRec(root);
		System.out.println("");

	}
	
	private void printInOrderRec(Node currRoot) {
		// TODO Auto-generated method stub
		
		if(currRoot==null)
			return;
		
		printInOrderRec(currRoot.left);
		System.out.print(currRoot.value+", ");
		inorder.add(currRoot.value);
		printInOrderRec(currRoot.right);

	}
	public void printPreorder()
	{
		printPreOrderRec(root);
		System.out.println("");

	}
	
	private void printPreOrderRec(Node currRoot) {
		// TODO Auto-generated method stub
		
		if(currRoot==null)
		{
			return;
		}
		
		System.out.print(currRoot.value+", ");
		preorder.add(currRoot.value);
		printPreOrderRec(currRoot.left);
		printPreOrderRec(currRoot.right);

	}
	
	public void printPosorder()
	{
		printPosOrderRec(root);
		System.out.println("");

	}
	
	private void printPosOrderRec(Node currRoot) {
		// TODO Auto-generated method stub
		
		if(currRoot==null)
		{
			return;
		}
		
		printPosOrderRec(currRoot.left);
		printPosOrderRec(currRoot.right);
		System.out.print(currRoot.value+", ");
		posorder.add(currRoot.value);

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinarySearchTree bst= new BinarySearchTree();
		
		ArrayList<Integer> valList= new ArrayList<Integer>();
		File desktop = new File(System.getProperty("user.home"), "Desktop");
		
		valList= bst.ReadIntFromFile1(desktop+"/input001.txt");
		
		if(valList.size()>0)
		{
			for(int i=0;i<valList.size();i++)
			{
				bst.insert(valList.get(i));
			}
		}
//		bst.insert(40);
//		bst.insert(25);
//		bst.insert(78);
//		bst.insert(10);
//		bst.insert(3);
//		bst.insert(17);
//		bst.insert(32);
//		bst.insert(30);
//		bst.insert(38);
//		bst.insert(78);
//		bst.insert(50);
//		bst.insert(93);
//		
//		bst.Delete(78);
		
//		System.out.println("Inorder traversal");
//        bst.printInorder();
//        System.out.println(System.lineSeparator());
//        
//    	System.out.println("Preorder traversal");
//        bst.printPreorder();
//        System.out.println(System.lineSeparator());
//        
        System.out.println("Postorder traversal");
        bst.printPosorder();
        System.out.println(System.lineSeparator());
        
//        System.out.println("The minimum value in the BST: " + bst.findMinimum());
//        System.out.println("The maximum value in the BST: " + bst.findMaximum());
//        System.out.println("The Size of the BST is: " + bst.Size);
        
        System.out.println("The width of BST is: " + bst.getMaxWidth(bst.root));
        
        //bst.WriteTreeToFile(bst.inorder, "Inorder");
        
       // bst.WriteTreeToFile(bst.preorder, "Preorder");
        
      bst.WriteTreeToFile(bst.posorder, "Postorder");
       
      bst.PrintFromFileOriginalTree();
       
//      Node n=bst.findNodeByVal(32);
//       
//     if( n!=null)
//      {
//    	  System.out.println(System.lineSeparator());
//    	  System.out.println("Successfull the node exist with value: "+n.value);
//      }
       
	}
	
	
	public class Node<T>
	{
		public int value;
		public Node left;
		public Node right;
		public Node parent;
		
		public Node(int value)
		{
			this.value=value;
		}
	}

}
