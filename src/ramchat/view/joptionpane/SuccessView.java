package ramchat.view.joptionpane;

import javax.swing.JOptionPane;

public class SuccessView extends JOptionPane {
	/*
	 * - IDSearchView���� "ID�� e-mail�� �����߽��ϴ�."
	 * - PWSearchView���� "PW�� e-mail�� �����߽��ϴ�." 
	 * - JoinView���� "������ �����մϴ�."
	 * - MyPageView���� "������ �Ϸ����ϴ�."
	 * -
	 */
	public static void successMessage(String message){
		   showMessageDialog(null, message);
	   }
	
	
}
