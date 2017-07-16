package ramchat.view.joptionpane;

import javax.swing.JOptionPane;

public class SuccessView extends JOptionPane {
	/*
	 * - IDSearchView에서 "ID를 e-mail로 전송했습니다."
	 * - PWSearchView에서 "PW를 e-mail로 전송했습니다." 
	 * - JoinView에서 "가입을 축하합니다."
	 * - MyPageView에서 "수정이 완료됬습니다."
	 * -
	 */
	public static void successMessage(String message){
		   showMessageDialog(null, message);
	   }
	
	
}
