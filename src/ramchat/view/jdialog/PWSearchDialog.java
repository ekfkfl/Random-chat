package ramchat.view.jdialog;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ramchat.controller.SearchController;
import ramchat.controller.impl.SearchControllerImpl;

/**
 * PWã��ȭ�� - LoginView�� btnPWSearchŬ���� ����ȴ�. - reSize�� false�̴�.(����� ũ������ �Ұ�)
 */
public class PWSearchDialog extends JDialog implements ActionListener {
	// �������
	SearchController searchController = new SearchControllerImpl();

	JLabel lblIDSearch = new JLabel("< ��й�ȣ ã�� >");
	JLabel lblIDSearchDetail = new JLabel("- �Է��Ͻ� E-mail �ּҷ� ��й�ȣ�� �����մϴ�.");
	JLabel lblID = new JLabel("ID");
	JLabel lblName = new JLabel("�̸�");
	JLabel lblEmail = new JLabel("E-mail �ּ�");

	JTextField tFieldID = new JTextField();
	JTextField tFieldName = new JTextField();
	JTextField tFieldEmail = new JTextField();

	JButton btnPWSearch = new JButton("��й�ȣ ã��");
	JButton btnCancel = new JButton("���");

	// ������
	// public PWSearchJDialogView(LoginJFrameView loginPage,String title) {
	public PWSearchDialog() {
		this.setTitle("PW ã��");
		lblIDSearch.setFont(new Font("����", Font.BOLD, 15));

		/*
		 * LoginPage�� (��ü)���̾ƿ� GroupLayout
		 */
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(
										groupLayout.createSequentialGroup().addGap(102)
												.addComponent(btnPWSearch, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(18)
												.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(108))
								.addGroup(groupLayout.createSequentialGroup().addGap(42).addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(11).addGroup(groupLayout
												.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addComponent(lblID)
														.addGap(24))
												.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																.addComponent(lblName, 0, 0, Short.MAX_VALUE)
																.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addPreferredGap(ComponentPlacement.RELATED)))
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(tFieldName)
														.addComponent(tFieldID, GroupLayout.DEFAULT_SIZE, 185,
																Short.MAX_VALUE)
														.addComponent(tFieldEmail))
												.addContainerGap(18, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblIDSearch).addComponent(lblIDSearchDetail))
												.addContainerGap(80, Short.MAX_VALUE)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(34).addComponent(lblIDSearch)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblIDSearchDetail).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblID).addComponent(tFieldID,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblName).addComponent(
						tFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblEmail).addComponent(
						tFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED).addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(btnCancel).addComponent(btnPWSearch))
				.addGap(39)));
		getContentPane().setLayout(groupLayout);

		this.setSize(400, 300);
		this.setVisible(true);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		/*
		 * ��ư �̺�Ʈ ���
		 */
		btnPWSearch.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btnPWSearch) {
			System.out.println("PWã���ư����");
			boolean result = searchController.searchPw(tFieldID.getText().trim(), tFieldName.getText().trim(),
					tFieldEmail.getText().trim());
			if(result==true) {
				dispose();
			}

		} else if (obj == btnCancel) {
			System.out.println("��ҹ�ư����");
			dispose();
		}
	}
}
