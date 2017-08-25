import java.util.ArrayList;

public class Tree {

	private Node root;
    private int count = 0;

    /** Constructor **/
    public Tree()
    {
        root = null;
    }

    /** Function to check if tree is empty **/
    public boolean isEmpty()
    {
        return root == null;
    }

    /** clear tree **/
    public void clear()
    {
        root = null;
    }

    /** function to insert element */
    public void insert(int orgId, int parentOrgId, String name)
    {
        Node z = root;
        Node p = null;
        while (z != null)
        {
            p = z;
            if (parentOrgId < p.orgId)//may need to be orgId
                z = z.right;
            else
                z = z.left;
        }
        
        z = new Node();
        z.orgId = orgId;
        z.parentOrgId = parentOrgId;
        z.name = name;
        z.parent = p;
        
        if(p != null){
        	z.treeLevel = p.treeLevel + 1;
        }
        
        if (p == null){
            root = z;
        }
        else if (parentOrgId < p.parentOrgId){
            p.right = z;
        }
        else
        {
            p.left = z;
        }
        Splay(z);

    }
    /** rotate **/
    public void makeLeftChildParent(Node c, Node p)
    {
        if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
            throw new RuntimeException("WRONG");

        if (p.parent != null)
        {
            if (p == p.parent.left)
                p.parent.left = c;
            else 
                p.parent.right = c;
        }
        if (c.right != null)
            c.right.parent = p;

        c.parent = p.parent;
        p.parent = c;
        p.left = c.right;
        c.right = p;
    }

    /** rotate **/
    public void makeRightChildParent(Node c, Node p)
    {
        if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))
            throw new RuntimeException("WRONG");
        if (p.parent != null)
        {
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }
        if (c.left != null)
            c.left.parent = p;
        c.parent = p.parent;
        p.parent = c;
        p.right = c.left;
        c.left = p;
    }

    /** function splay **/
    private void Splay(Node x)
    {
        while (x.parent != null)
        {
            Node Parent = x.parent;
            Node GrandParent = Parent.parent;
            if (GrandParent == null)
            {
                if (x == Parent.left)
                    makeLeftChildParent(x, Parent);
                else
                    makeRightChildParent(x, Parent);                 
            } 
            else
            {
                if (x == Parent.left)
                {
                    if (Parent == GrandParent.left)
                    {
                        makeLeftChildParent(Parent, GrandParent);
                        makeLeftChildParent(x, Parent);
                    }
                    else 
                    {
                        makeLeftChildParent(x, x.parent);
                        makeRightChildParent(x, x.parent);
                    }
                }
                else 
                {
                    if (Parent == GrandParent.left)
                    {
                        makeRightChildParent(x, x.parent);
                        makeLeftChildParent(x, x.parent);
                    } 
                    else 
                    {
                        makeRightChildParent(Parent, GrandParent);
                        makeRightChildParent(x, Parent);
                    }
                }
            }
        }
        root = x;
    }

    /** Functions to count number of nodes **/
    public int countNodes(Node x)
    {
        return count;
    }

    /** Returns one specific org **/
    public Node getOrg(int orgId)
    {
        Node z = root;
        while (z != null)
        {
            if (orgId < z.parentOrgId)
                z = z.right;
            else if (orgId > z.parentOrgId)
                z = z.left;
            else
                return z;
            
        }
        return null;
    }
    
    /** Returns the immediate children of the org **/
    public ArrayList<Node> getChildOrgs(int orgId)
    {
    	ArrayList<Node> childOrgs = new ArrayList<Node>();
        Node node = getOrg(orgId);
        childOrgs.add(node.left);
        childOrgs.add(node.left);
        return childOrgs;
    }

    /** Returns list of orgs below this node in the tree **/
    public ArrayList<Node> getOrgTree(int orgId, boolean inclusive)
    {
    	ArrayList<Node> lines = new ArrayList<Node>();
        getOrgTree(root, lines, inclusive);
        return lines;
    }
    private void getOrgTree(Node node, ArrayList<Node> list, boolean inclusive)
    {
        if (node != null)
        {
        	//first time check param, otherwise include the org in the list
        	if(inclusive){
        		list.add(node);
        	}
            getOrgTree(node.left, list, true);
            getOrgTree(node.right, list, true);
        }
    }

}