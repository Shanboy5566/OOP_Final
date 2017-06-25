
public class Classification {
	/**
	 * classificaiton:
	 * 1: General Audience 
	 * 2: Parental Guidance Suggested (6 up)
	 * 3: Parents Strongly Cautioned (15 up)
	 * 4: Restricted(18 up)
	 */
	private int classificaiton = 0;
	
	
	public Classification(){
	}
	
	public Classification(String classificaiton){
		switch(classificaiton){
		case "普遍":
			this.classificaiton = 1;
			break;
		case "保護":
			this.classificaiton = 2;
			break;
		case "輔導":
			this.classificaiton = 3;
			break;
		case "限制":
			this.classificaiton = 4;
			break;
		
		}
	}
	
	public String getClassificaiton() {
		switch(classificaiton){
		case 1:
			return "普遍";
//			break;
		case 2:
			return "保護";
//			break;
		case 3:
			return "輔導";
//			break;
		case 4:
			return "限制";
//			break;
		default:
			return "Exception";
		}
		
	}

	public void setClassificaiton(String classificaiton) {
		switch(classificaiton){
		case "普遍":
			this.classificaiton = 1;
			break;
		case "保護":
			this.classificaiton = 2;
			break;
		case "輔導":
			this.classificaiton = 3;
			break;
		case "限制":
			this.classificaiton = 4;
			break;
		
		}
	}
}

