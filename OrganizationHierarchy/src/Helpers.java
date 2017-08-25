
public class Helpers {
	public static Integer ConvertToInt(String str){
		Integer num = null;
		if(str == null){
			num = 0;
		}else{
			try{
				num = Integer.decode(str);
			}catch(Exception e){
				num = 0;
			}
		}
		
		return num;
	}
}
