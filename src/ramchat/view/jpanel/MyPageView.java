package ramchat.view.jpanel;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import mail.MailService;
import ramchat.controller.MypageController;
import ramchat.controller.impl.FileClientConnetControllerImpl;
import ramchat.controller.impl.FileServerConnetController;
import ramchat.controller.impl.MypageControllerImpl;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.util.DbUtil;
import ramchat.view.jframe.MainView;
import ramchat.view.joptionpane.FailView;
import usersession.UserSession;

/**
 * 마이페이지화면 - 수정가능한 항목 : 사진, PW, E-mail주소
 */
public class MyPageView extends JPanel implements ActionListener {
	FileServerConnetController fileServerConnect = new FileServerConnetController();
	Socket sk = fileServerConnect.getSocket();
	PrintWriter pw = fileServerConnect.getPrintWriter();
	FileInputStream fileInputStream;
	OutputStream outputStream;

	// JoinDialog joinDialog = new

	int pwdChk = 1;

	MypageController myc = new MypageControllerImpl();
	MainView mainView = null;
	UserInfoDTO userInfoDTO = new UserInfoDTO();

	FileDialog open = new FileDialog((Frame) getParent(), "사진불러오기");
	String readFile;

	private boolean emailFlag = false;
	private int randomNum = Integer.MIN_VALUE;
	Random random = new Random();

	JLabel lblPhoto = new JLabel("- 프로필사진");
	JLabel lblID = new JLabel("- ID ");
	JLabel lblCurrentPwd = new JLabel("- 현재 비밀번호");
	JLabel lblPW = new JLabel("- 비밀번호 ");
	JLabel lblPWCheck = new JLabel("- \uBE44\uBC00\uBC88\uD638 \uD655\uC778 ");
	JLabel lblName = new JLabel("- 이름 ");
	JLabel lblGender = new JLabel("- 성별 ");
	JLabel lblBirthday = new JLabel("- 생년월일 ");
	JLabel lblYear = new JLabel("년");
	JLabel lblMonth = new JLabel("월");
	JLabel lblDay = new JLabel("일");
	JLabel lblEmail = new JLabel("- E-mail 주소 ");
	JLabel lblEmailCheckNo = new JLabel("- 인증번호 ");

	JButton btnUploadPhoto = new JButton("사진업로드");
	JButton btnEmailNoSend = new JButton("인증번호 전송");
	JButton btnEmailNoCheck = new JButton("인증번호 확인");
	JButton btnUpdate = new JButton("수정하기");
	JButton btnCancel = new JButton("취소");

	JTextField tFieldID = new JTextField();

	JPasswordField tFieldCurrentPW = new JPasswordField(); // 현재패스워드
	JPasswordField tFieldPW = new JPasswordField();
	JPasswordField tFieldPWCheck = new JPasswordField();
	JTextField tFieldName = new JTextField();
	JTextField tFieldEmail = new JTextField();
	JTextField tFieldEmailCheckNo = new JTextField();

	JRadioButton rdbtnMan = new JRadioButton("남자");
	JRadioButton rdbtnWoman = new JRadioButton("여자");

	ImagePanel pnlPhoto = new ImagePanel();

	// 생년월일 콤보박스
	JComboBox<String> comBoxYear = new JComboBox<String>();
	JComboBox<String> comBoxMonth = new JComboBox<String>();
	JComboBox<String> comBoxDay = new JComboBox<String>();

	// JPasswordField textField;

	/**
	 * Create the panel.
	 */
	public MyPageView(MainView mainView) {
		this.mainView = mainView;

		// 성별 단일선택 그룹화
		ButtonGroup groupGender = new ButtonGroup();
		groupGender.add(rdbtnMan);
		groupGender.add(rdbtnWoman);
		comBoxYear.setBounds(144, 369, 64, 21);
		comBoxYear.setEnabled(false);
		comBoxMonth.setBounds(250, 369, 46, 21);
		comBoxMonth.setEnabled(false);
		comBoxDay.setBounds(335, 369, 44, 21);
		comBoxDay.setEnabled(false);
		tFieldEmailCheckNo.setBounds(144, 457, 123, 21);

		tFieldEmailCheckNo.setEditable(true);
		tFieldEmailCheckNo.setEnabled(true);

		tFieldEmailCheckNo.setColumns(10);
		tFieldEmail.setBounds(144, 416, 123, 21);
		tFieldEmail.setEditable(true);
		tFieldEmail.setEnabled(true);
		tFieldEmail.setColumns(10);
		tFieldName.setBounds(144, 280, 123, 21);
		tFieldName.setEnabled(false);
		tFieldName.setColumns(10);
		tFieldID.setBounds(145, 120, 123, 21);
		tFieldID.setEnabled(false);
		tFieldID.setColumns(10);
		pnlPhoto.setBounds(168, 10, 89, 89);

		pnlPhoto.setBackground(new Color(255, 153, 153));
		setSize(500, 600);
		rdbtnMan.setBounds(144, 325, 57, 23);
		rdbtnMan.setEnabled(false);
		rdbtnWoman.setBounds(239, 325, 57, 23);
		rdbtnWoman.setEnabled(false);

		/** 디폴트값 가져오기 */
		tFieldID.setText(UserSession.getId());
		tFieldName.setText(UserSession.getName());
		String birtDate = UserSession.getBirthDate();
		String year = birtDate.substring(0, 4);
		String month = birtDate.substring(4, 6);
		String date = birtDate.substring(6, 8);

		comBoxYear.addItem(year);
		comBoxMonth.addItem(month);
		comBoxDay.addItem(date);

		if (UserSession.getGender() == 1)
			rdbtnMan.setSelected(true);
		else
			rdbtnWoman.setSelected(true);

		setLayout(null);
		lblPhoto.setBounds(36, 10, 83, 15);
		add(lblPhoto);
		add(pnlPhoto);
		add(btnUploadPhoto);
		add(btnUpdate);
		tFieldPW.setBounds(144, 200, 123, 21);
		add(tFieldPW);
		tFieldPWCheck.setBounds(145, 236, 123, 21);
		add(tFieldPWCheck);
		add(tFieldID);
		lblName.setBounds(36, 283, 70, 15);
		add(lblName);
		add(tFieldName);
		add(btnCancel);
		lblID.setBounds(36, 124, 70, 15);
		add(lblID);
		lblPW.setBounds(36, 203, 85, 15);
		add(lblPW);
		lblPWCheck.setBounds(36, 239, 109, 15);
		add(lblPWCheck);
		lblGender.setBounds(36, 329, 70, 15);
		add(lblGender);
		add(rdbtnMan);
		add(rdbtnWoman);
		lblEmailCheckNo.setBounds(36, 460, 70, 15);
		add(lblEmailCheckNo);
		add(tFieldEmailCheckNo);
		add(btnEmailNoCheck);
		lblYear.setBounds(213, 372, 14, 15);
		add(lblYear);
		add(comBoxMonth);
		lblEmail.setBounds(36, 419, 85, 15);
		add(lblEmail);
		add(tFieldEmail);
		add(btnEmailNoSend);
		lblMonth.setBounds(303, 372, 14, 15);
		add(lblMonth);
		add(comBoxDay);
		lblDay.setBounds(391, 372, 27, 15);
		add(lblDay);
		lblBirthday.setBounds(36, 372, 90, 15);
		add(lblBirthday);
		add(comBoxYear);
		lblCurrentPwd.setBounds(36, 166, 109, 15);

		add(lblCurrentPwd);

		tFieldCurrentPW.setBounds(144, 163, 123, 21);
		add(tFieldCurrentPW);
		tFieldCurrentPW.setColumns(10);

		btnUploadPhoto.setBounds(287, 64, 103, 23);
		btnEmailNoSend.setBounds(285, 415, 121, 23);
		btnEmailNoCheck.setBounds(285, 456, 121, 23);
		btnUpdate.setBounds(159, 530, 89, 23);
		btnCancel.setBounds(275, 530, 61, 23);

		/** 버튼 리스너 등록 */
		btnUploadPhoto.addActionListener(this);
		btnEmailNoSend.addActionListener(this);
		btnEmailNoCheck.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnCancel.addActionListener(this);
		
		initPhoto();
	}

	public void initPhoto() {
		FileClientConnetControllerImpl fileConnect = new FileClientConnetControllerImpl();
		
		pnlPhoto.setbImg(fileConnect.getImg(UserSession.getId()));
	}

	public void sendFile() {
		try {
			pw.println(tFieldID.getText());
			outputStream = sk.getOutputStream();

			fileInputStream = new FileInputStream(readFile);

			byte[] dataBuff = new byte[10000];
			int length = fileInputStream.read(dataBuff);
			while (length != -1) {
				outputStream.write(dataBuff, 0, length);
				length = fileInputStream.read(dataBuff);
			}
			System.out.println("전송 선공");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (sk != null) {
				try {
					sk.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// 리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btnUploadPhoto) {
			System.out.println("사진 업로드 누름");
			open.setVisible(true);
			String dirPath = open.getDirectory();
			String fileName = open.getFile();
			readFile = dirPath + fileName;
			if (readFile != null) {
				ImageIcon icon = new ImageIcon(readFile);
				pnlPhoto.setbImg(null);
				pnlPhoto.setImg(icon);
				pnlPhoto.repaint();
			}
		}

		if (obj == btnCancel) {
			System.out.println("취소 버튼 누름");
			mainView.card.show(mainView.pnlMiddle, "PanelMain");
		}

		if (obj == btnUpdate) {
			System.out.println("수정하기 버튼 누름");
			ValidateCreateUser();

		}

		if (obj == btnEmailNoSend) {
			System.out.println("인증번호 발송");
			randomNum = random.nextInt(1000000) + 100000;
			new MailService(tFieldEmail.getText(), "랜덤 채팅 인증번호 입니다", "고객님의 인증번호는  " + randomNum + "입니다");
			JOptionPane.showMessageDialog(this, "인증번호를 전송하였습니다.");

		} else if (e.getSource() == btnEmailNoCheck) {

			if (tFieldEmailCheckNo.getText().equals(randomNum + "")) {
				JOptionPane.showMessageDialog(this, "인증에 성공하였습니다.");
				emailFlag = true;
				tFieldEmail.setEnabled(true);

			} else {
				JOptionPane.showMessageDialog(this, "인증에 실패 하였습니다");
				emailFlag = false;
			}
		}
	}

	public int pwdValidate() {
		String pwd = new String(tFieldPW.getPassword());
		String pwdchk = new String(tFieldPWCheck.getPassword());
		int result = 0;
		String dbPwd = null;

		try {
			Connection con = DbUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select pw from userinfo where id= ? ");
			pstmt.setString(1, UserSession.getId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dbPwd = rs.getString("pw");
			}

			if (!String.valueOf(tFieldCurrentPW.getPassword()).equals(dbPwd)) {
				result = 5;

			} else if (pwd.trim().equals("")) {
				result = 1;

			} else if (pwd.length() < 5) {
				result = 2;
				// 3.password 에 특수문자가 없을 경우
			} else if (!(pwd.contains("!") || pwd.contains("@") || pwd.contains("#") || pwd.contains("$")
					|| pwd.contains("%") || pwd.contains("^") || pwd.contains("&") || pwd.contains("*"))) {
				result = 3;
			} else if (!pwd.equals(pwdchk)) {
				result = 4;
			}
		} catch (SQLException e) {

		}
		return result;

	}

	public void pwdValidateMsg() {
		switch (pwdChk) {
		case 0:
			return;
		case 5:
			FailView.failMessage("password가 맞지 않습니다.");
			break;
		case 1:
			FailView.failMessage("password 공백은 허용하지 않습니다.");
			break;
		case 2:
			FailView.failMessage("password는 5글자 이상 특수문자가 포함되어야 합니다 .");
			break;
		case 3:
			FailView.failMessage("password는 특수문자가 한글자 이상 포함되어야 합니다");
			break;
		case 4:
			FailView.failMessage("첫번째 password와 일치 하지 않습니다.");
		}
	}

	public void emailValidateMsg() {
		if (!emailValidate()) {
			FailView.failMessage("이메일 인증을 해주세요.");
		}
	}

	public boolean emailValidate() {
		return emailFlag;
	}

	public void ValidateCreateUser() {
		pwdChk = pwdValidate();

		if (pwdChk == 0) {
			if (emailFlag == false) {
				FailView.failMessage("이메일 인증을 해주세요.");
				return;
			}
			try {
				myc.updateUserInfo(tFieldID.getText(), String.valueOf(tFieldCurrentPW.getPassword()),
						tFieldEmail.getText(), String.valueOf(tFieldPW.getPassword()));
				System.out.println("들어옴");
				System.out.println(tFieldID.getText() + readFile);
				sendFile();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			pwdValidateMsg();
		}
	}
}
