package ramchat.view.jdialog;

import javax.swing.JDialog;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * ä��ȭ�鿡�� ������ ������ ��� �ߴ� ȭ��
 */
public class ChatFailDialog extends JDialog {
	public ChatFailDialog() {
		
		JLabel lblNewLabel = new JLabel("������ ä�ù��� �����̽��ϴ�.");
		JLabel lblNewLabel_1 = new JLabel("����ȭ������ ���ư��ϴ�.");		
		JButton btnNewButton = new JButton("Ȯ��");
		

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
