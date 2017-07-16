package ramchat.view.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ramchat.controller.LoginController;
import ramchat.controller.impl.LoginControllerImpl;
import ramchat.model.dto.UserInfoDTO;
import ramchat.view.jdialog.IDSearchDialog;
import ramchat.view.jdialog.JoinDialog;
import ramchat.view.jdialog.PWSearchDialog;
import ramchat.view.joptionpane.FailView;
import usersession.UserSession;

/**
 * �α���ȭ�� - JFrame - �α���ȭ���� IDSearchJDialogView, PWSearchJDialogView,
 * JoinJDialogView�� Owner�̴�. - resize : false (����� ũ������ �Ұ�)
 */
public class LoginView extends JFrame implements ActionListener {

	LoginController loginController = new LoginControllerImpl();

	// �������
	JLabel lblLoginTitle = new JLabel("�� �� �� �� ��");
	JLabel lblID = new JLabel("ID");
	JLabel lblPW = new JLabel("Password");

	JTextField tFieldID = new JTextField();
	JPasswordField tFieldPW = new JPasswordField();

	JButton btnLogin = new JButton("�α���");
	JButton btnIDsearch = new JButton("IDã��");
	JButton btnPWSearch = new JButton("PWã��");
	JButton btnJoin = new JButton("ȸ������");

	// static UserInfoDTO userInfo;

	public LoginView() {
		super("�α���");

		lblLoginTitle.setForeground(new Color(130, 226, 0));
		lblLoginTitle.setFont(new Font("�޸յձ�������", Font.BOLD, 35));

		/*
		 * LoginPage�� (��ü)���̾ƿ�: GroupLayout
		 */
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout.createSequentialGroup()
								.addGap(189).addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnJoin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 89,
												Short.MAX_VALUE))
								.addContainerGap(216, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addGap(149)
										.addComponent(btnIDsearch, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
										.addGap(18).addComponent(btnPWSearch).addGap(167))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap(111, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(
												lblLoginTitle)
												.addGroup(groupLayout
														.createParallelGroup(Alignment.LEADING, false).addGroup(
																groupLayout.createSequentialGroup()
																		.addComponent(lblPW, GroupLayout.PREFERRED_SIZE,
																				107, GroupLayout.PREFERRED_SIZE)
																		.addGap(18).addComponent(tFieldPW))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(lblID, GroupLayout.PREFERRED_SIZE, 107,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18).addComponent(tFieldID,
																		GroupLayout.PREFERRED_SIZE, 185,
																		GroupLayout.PREFERRED_SIZE))))
										.addGap(73)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(43).addComponent(lblLoginTitle).addGap(45)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblID).addComponent(tFieldID,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(24)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPW).addComponent(tFieldPW,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(41).addComponent(btnLogin)
				.addGap(18).addComponent(btnJoin).addGap(18).addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(btnPWSearch).addComponent(btnIDsearch))
				.addGap(53)));

		getContentPane().setLayout(groupLayout);

		this.setSize(500, 400);
		this.setVisible(true);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		/** ������ ��� */

		btnLogin.addActionListener(this);
		btnIDsearch.addActionListener(this);
		btnPWSearch.addActionListener(this);
		btnJoin.addActionListener(this);
	}

	/** ��ư ������ */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		String idText = tFieldID.getText().trim();
		String pwText = String.valueOf(tFieldPW.getPassword()).trim();

		/** 1.�α��� */
		if (obj == btnLogin) {
			if (idText.equals("")) {
				FailView.failMessage("���̵� �Է��Ͽ��ּ���.");
				return;
			}

			if (pwText.equals("")) {
				FailView.failMessage("��й�ȣ�� �Է��Ͽ��ּ���.");
				return;
			}

			UserInfoDTO userInfoDto = loginController.getUserInfo(idText, pwText);
			if (userInfoDto != null) {
				dispose();
				new MainView();
			}

		}

		/** 2.ȸ������ ��ư */
		else if (obj == btnJoin) {
			new JoinDialog(this);
		}
		/** 3.���̵� ã�� ��ư */
		else if (obj == btnIDsearch) {
			new IDSearchDialog();
		}
		/** 4.��й�ȣ ã�� ��ư */
		else if (obj == btnPWSearch) {
			new PWSearchDialog();

		}

	}

}
