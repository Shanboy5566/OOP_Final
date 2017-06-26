/**
 * 描述一個分級的class，規則如下：
 * 1: General Audience 
 * 2: Parental Guidance Suggested (6 up)
 * 3: Parents Strongly Cautioned (15 up)
 * 4: Restricted(18 up)
 * 
 * @author Ching Wei Wu
 * @since 2017/06/20
 */
public class Classification {
	private int classificaiton;
	private int AgeLimit;
	/**
	 * Default Constructor
	 * 初值設為0
	 */
	public Classification(){
		classificaiton=0;
		AgeLimit=0;
	}
	/**
	 * 取得使用者年齡限制代碼
	 * @return AgeLimit
	 */
	public int getAgelimit(){
		return AgeLimit;
	}
	/**
	 * 透過輸入的分級，建立指定建構子，將該電影的分級變數存為整數格式
	 * ，並且更新該電影能觀看的最年輕年齡
	 * @param classificaiton
	 */
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
	/**
	 * 取得指定電影的分級
	 * @return String
	 */
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
	/**
	 * 提供外界設定電影的分級，並更新分級代碼、年齡層下限
	 * @param classificaiton
	 */
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