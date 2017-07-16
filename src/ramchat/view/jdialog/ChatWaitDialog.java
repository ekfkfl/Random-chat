package ramchat.view.jdialog;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import ramchat.view.jframe.MainView;

import javax.swing.JButton;

/**
 *	채팅대기화면
 *	- MainView에서 btnChatting 클릭시 실행된다.
 *  - 남-녀 매칭이 성공하면 창이 자동으로 닫힌다.
 *	- reSize : false(사용자 크기조정 불가)
 */
public class ChatWaitDialog extends JDialog {
	

	//멤버변수
	JLabel lblChatWaitState1 = new JLabel("잠시만 기다려주세요");
	JLabel lblChatWaitState2 = new JLabel("상대방을 찾고있습니다....♥");
	JButton btnCancel = new JButton("취소");
	
	//생성자
	public ChatWaitDialog(MainView mainJFrameView) {
		super(mainJFrameView);
		
	

		/*
		 * ChatWaitPage의 (전체)레이아웃 GroupLayout
		 */	
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(122)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblChatWaitState1)
								.addComponent(lblChatWaitState2)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(154)
							.addComponent(btnCancel)))
					.addContainerGap(113, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(99)
					.addComponent(lblChatWaitState1)
					.addGap(18)
					.addComponent(lblChatWaitState2)
					.addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addGap(18))
		);
		getContentPane().setLayout(groupLayout);
		
		this.setSize(400, 300);
		this.setVisible(true);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}//생성자
	
	
}
