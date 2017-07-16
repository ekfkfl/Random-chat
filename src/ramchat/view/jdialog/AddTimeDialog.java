package ramchat.view.jdialog;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *	�ð��������ȭ��
 *	- ChatView���� btnAddTime Ŭ���� ���濡�Ը� ����ȴ�.
 *  - ��-�� ��Ī�� �����ϸ� â�� �ڵ����� ������.
 *	- reSize : false(����� ũ������ �Ұ�)
 */
public class AddTimeDialog extends JDialog {
	public AddTimeDialog() {

		JLabel lblmessage1 = new JLabel("������ �ð������� ��û�߽��ϴ�!");
		JLabel lblmessage2 = new JLabel("�����Ͻðڽ��ϱ�?");
		JLabel lblmessage3 = new JLabel("[��Tip] �����Ͻø� �����ʻ��� �Ϻΰ� ���µ˴ϴ�.");
		JButton btnAccept = new JButton("�����ϱ�");
		JButton btnRefusal = new JButton("�����ϱ�");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(89, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblmessage3)
						.addComponent(lblmessage2)
						.addComponent(lblmessage1))
					.addGap(79))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(131)
					.addComponent(btnAccept)
					.addGap(18)
					.addComponent(btnRefusal)
					.addContainerGap(123, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addComponent(lblmessage1)
					.addGap(27)
					.addComponent(lblmessage2)
					.addGap(26)
					.addComponent(lblmessage3)
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRefusal)
						.addComponent(btnAccept))
					.addGap(32))
		);
		getContentPane().setLayout(groupLayout);
		
		this.setSize(400, 300);
		this.setVisible(true);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
