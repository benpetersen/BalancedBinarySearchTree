
public class Org {
	Integer orgId;
	Integer parentOrgId;
	String name;
    Integer TotalNumUsers;
    Integer TotalNumBytes;
    Integer TotalNumFiles;
	
	Org(Integer OrgId, Integer ParentOrgId, String Name){
		orgId = OrgId;
		parentOrgId = ParentOrgId;
		name = Name;
	}
	public int getTotalNumUsers(){
		return TotalNumUsers;
	}
	public int getTotalNumBytes(){
		return TotalNumBytes;
	}
	public int getTotalNumFiles(){
		return TotalNumFiles;
	}
}

