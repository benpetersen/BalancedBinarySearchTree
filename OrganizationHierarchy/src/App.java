import java.io.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class App {

	public static void main(String[] args) {
		Tree tree = new Tree();
		
		retrieveOrgHierachyData(tree);
		retrieveUserData(tree);
		outputOrgData(tree);
	}
	private static void outputOrgData(Tree tree) {
		String outputDataFilename = "src/outputData.txt";
		ArrayList<Node> list = tree.getOrgTree(1,true);
		ArrayList<String> output = new ArrayList<String>();
		
		//build the tabbing structure for nested children
		for(int i = 0; i<list.size(); i++){
			String tabs = "";
			Node node = list.get(i);
	    	for( int j = 0; node.treeLevel > 0 && j < node.treeLevel; j++){
	    		tabs = tabs + "\t";
	    	}
			
			output.add(tabs + " " + node.orgId + ", " + node.totalNumUsers + ", " + node.totalNumFiles + ", " + node.totalNumBytes);
		}

		try{
			PrintWriter writer = new PrintWriter(outputDataFilename, "UTF-8");
			for(int i = output.size() - 1; i >= 0; i--){
				writer.println(output.get(i));
			}
		    writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	private static void retrieveUserData(Tree tree) {
		String userDataFileName = "src/UserData.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(userDataFileName))) {
			Integer userId, orgId, numFiles, numBytes;
			
		    for(String line; (line = br.readLine()) != null; ) {
		    	List<String> list = new ArrayList<String>(Arrays.asList(line.split(",")));
		    	
		    	userId = Helpers.ConvertToInt(list.get(0).trim());
		    	orgId = Helpers.ConvertToInt(list.get(1).trim());
		    	numFiles = Helpers.ConvertToInt(list.get(2).trim());
		    	numBytes = Helpers.ConvertToInt(list.get(3).trim());

		    	if(orgId != null){
			    	Node node = tree.getOrg(orgId);
			    	if(node != null){
				    	User user = new User(userId, orgId, numFiles, numBytes);
				    	node.users.add(user);
				    	node.totalNumBytes += numBytes;
				    	node.totalNumFiles += numFiles;
				    	node.totalNumUsers++;
			    	}
		    	}
		    }
		    
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	private static void retrieveOrgHierachyData(Tree tree) {
		String orgHierarchyFilename = "src/orgHierarchyData.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(orgHierarchyFilename))) {
	
			Integer orgId, parentOrgId;
			String name;
			
		    for(String line; (line = br.readLine()) != null; ) {
		    	List<String> list = new ArrayList<String>(Arrays.asList(line.split(",")));
		    	
		    	orgId = Helpers.ConvertToInt(list.get(0).trim());
		    	parentOrgId = Helpers.ConvertToInt(list.get(1).trim());
		    	name = list.get(2).trim();

		    	Org org = new Org(orgId, parentOrgId, name);
		    	tree.insert(org.orgId, org.parentOrgId, org.name);
		    }
		    
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
}
