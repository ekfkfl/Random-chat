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
 * IDã��ȭ�� - LoginView�� btnIdSearch Ŭ���� ����ȴ�. - reSize : false(����� ũ������ �Ұ�)
 */
public class IDSearchDialog extends JDialog implements ActionListener {
	// �������
	SearchController searchController = new SearchControllerImpl();

	JLabel lblIDSearch = new JLabel("< ID ã�� >");
	JLabel lblIDSearchDetail = new JLabel("- �Է��Ͻ� E-mail �ּҷ� ID�� �����մϴ�.");
	JLabel lblName = new JLabel("�̸�");
	JLabel lblEmail = new JLabel("E-mail �ּ�");

	JTextField tFieldName = new JTextField();
	JTextField tFieldEmail = new JTextField();

	JButton btnIDSearch = new JButton("ID ã��");
	JButton btnCancel = new JButton("���");

	// ������
	// public IDSearchJDialogView(LoginJFrameView loginPage,String title) {
	public IDSearchDialog() {
		this.setTitle("ID ã��");

		lblIDSearch.setFont(new Font("����", Font.BOLD, 15));

		/*
		 * LoginPage�� (��ü)���̾ƿ� GroupLayout
		 */
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(120)
						.addComponent(btnIDSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(18).addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE).addGap(122))
				.addGroup(groupLayout.createSequentialGroup().addGap(53)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblEmail).addGap(24))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addComponent(tFieldEmail)
								.addComponent(tFieldName, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
						.addContainerGap(25, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(44).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIDSearch).addComponent(lblIDSearchDetail))
						.addContainerGap(115, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(26).addComponent(lblIDSearch)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblIDSearchDetail)
						.addGap(26)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(tFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblName))
						.addGap(33)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblEmail)
								.addComponent(tFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnIDSearch)
								.addComponent(btnCancel))
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
		btnIDSearch.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btnIDSearch) {
			System.out.println("IDã���ư����");
			boolean result = searchController.searchId(tFieldName.getText().trim(), tFieldEmail.getText().trim());
			if(result==true) {
				dispose();
			}

		} else if (obj == btnCancel) {
			System.out.println("��ҹ�ư����");
			dispose();
		}
	}
}
