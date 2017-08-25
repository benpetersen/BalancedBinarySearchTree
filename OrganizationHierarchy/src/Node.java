import java.util.ArrayList;

class Node
 {    
	Node left, right, parent;
	int orgId;
	int parentOrgId;
	String name;
	ArrayList<User> users;
	int totalNumUsers;
	int totalNumFiles;
	int totalNumBytes;
	int treeLevel;
	
	 /** Default Constructor **/
     
	 public Node()
	 {
	     this(0, 0, "", null, null, null);
	 }
	 /** Constructor, initialize with 0 users **/
	 public Node(int orgId, int parentOrgId, String name)
	 {
	     this(orgId, parentOrgId, name, null, null, null);
	 } 
	 /** Constructor **/
	 public Node(int orgId, int parentOrgId, String name, Node left, Node right, Node parent)
	 {
	     this.left = left;
	     this.right = right;
	     this.parent = parent;
	     this.orgId = orgId;    
	     this.parentOrgId = parentOrgId;
	     this.name = name;
	     this.users = new ArrayList<User>();
	     this.totalNumUsers = 0;
	     this.totalNumBytes = 0;
	     this.totalNumFiles = 0;
	     this.treeLevel = 0;
	 }
}
