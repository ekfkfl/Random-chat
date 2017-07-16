package ramchat.view.jdialog;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import ramchat.view.jframe.MainView;

import javax.swing.JButton;

/**
 *	ä�ô��ȭ��
 *	- MainView���� btnChatting Ŭ���� ����ȴ�.
 *  - ��-�� ��Ī�� �����ϸ� â�� �ڵ����� ������.
 *	- reSize : false(����� ũ������ �Ұ�)
 */
public class ChatWaitDialog extends JDialog {
	

	//�������
	JLabel lblChatWaitState1 = new JLabel("��ø� ��ٷ��ּ���");
	JLabel lblChatWaitState2 = new JLabel("������ ã���ֽ��ϴ�....��");
	JButton btnCancel = new JButton("���");
	
	//������
	public ChatWaitDialog(MainView mainJFrameView) {
		super(mainJFrameView);
		
	

		/*
		 * ChatWaitPage�� (��ü)���̾ƿ� GroupLayout
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
		
	}//������
	
	
}
