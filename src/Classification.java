public class Classification {
	/**
	 * classificaiton:
	 * 1: General Audience 
	 * 2: Parental Guidance Suggested (6 up)
	 * 3: Parents Strongly Cautioned (15 up)
	 * 4: Restricted(18 up)
	 */
	private int classificaiton;
	private int AgeLimit;
	
	public Classification(){
		classificaiton=0;
		AgeLimit=0;
	}
	

	public int getAgelimit(){
		return AgeLimit;
	}
	
	public Classification(String classificaiton){
		switch(classificaiton){
		case "普遍":
			this.classificaiton = 1;
			this.AgeLimit=0;
			break;
		case "保護":
			this.classificaiton = 2;
			this.AgeLimit=6;
			break;
		case "輔導":
			this.classificaiton = 3;
			this.AgeLimit=15;
			break;
		case "限制":
			this.classificaiton = 4;
			this.AgeLimit=18;
			break;
		
		}
	}
	
	public String getClassificaiton() {
		switch(classificaiton){
		case 1:
			return "普遍";
		case 2:
			return "保護";
		case 3:
			return "輔導";
		case 4:
			return "限制";
		default:
			return "Exception";
		}
		
	}

	public void setClassificaiton(String classificaiton) {
		if(classificaiton=="普遍"){
			this.classificaiton = 1;
			this.AgeLimit=0;
		}
		else if(classificaiton=="保護"){
			this.classificaiton = 2;
			this.AgeLimit=6;
		}
		else if(classificaiton=="輔導"){
			this.classificaiton = 3;
			this.AgeLimit=15;
		}
		else if(classificaiton=="限制"){
			this.classificaiton = 4;
			this.AgeLimit=18;
		}
	}
}