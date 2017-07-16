package ramchat.view.jdialog;

import javax.swing.JDialog;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * 채팅화면에서 상대방이 나갔을 경우 뜨는 화면
 */
public class ChatFailDialog extends JDialog {
	public ChatFailDialog() {
		
		JLabel lblNewLabel = new JLabel("상대방이 채팅방을 나가셨습니다.");
		JLabel lblNewLabel_1 = new JLabel("메인화면으로 돌아갑니다.");		
		JButton btnNewButton = new JButton("확인");
		

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(184)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(122)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel))))
					.addContainerGap(132, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(81)
					.addComponent(lblNewLabel)
					.addGap(28)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(29))
		);
		getContentPane().setLayout(groupLayout);
		
		this.setSize(400, 300);
		this.setVisible(true);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
