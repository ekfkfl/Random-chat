package ramchat.view.jdialog;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;

import mail.MailService;
import ramchat.controller.JoinController;
import ramchat.controller.impl.FileServerConnetController;
import ramchat.controller.impl.JoinControllerImpl;
import ramchat.model.dto.UserInfoDTO;
import ramchat.view.jframe.LoginView;
import ramchat.view.joptionpane.FailView;
import usersession.UserSession;

/**
 * 회원가입화면 - JDialog - LoginJFrameView의 btnJoin클릭시 실행된다. - resize은
 * false(사용자크기조정불가) - ※주의! 생년월일 콤보박스
 */
public class JoinDialog extends JDialog implements ActionListener {

   JoinController joinController = new JoinControllerImpl();

   FileServerConnetController fileServerConnect = new FileServerConnetController();
   Socket sk = fileServerConnect.getSocket();
   PrintWriter pw = fileServerConnect.getPrintWriter();
   FileInputStream fileInputStream;
   OutputStream outputStream;

   int idChk = 1;
   int pwdChk = 1;
   int nameChk = 1;
   int sexChk = 1;

   /** 플래그 변수 (아이디, 이메일 true인경우만 실행되게) */
   private boolean idFlag = false;
   private boolean emailFlag = false;

   // 멤버변수
   JLabel lblPhoto = new JLabel("- 프로필사진");
   JLabel lblID = new JLabel("- ID ");
   JLabel lblPW = new JLabel("- 비밀번호 ");
   JLabel lblPWCheck = new JLabel("- 비밀번호 확인 ");
   JLabel lblName = new JLabel("- 이름 ");
   JLabel lblGender = new JLabel("- 성별 ");
   JLabel lblBirthday = new JLabel("- 생년월일 ");
   JLabel lblYear = new JLabel("년");
   JLabel lblMonth = new JLabel("월");
   JLabel lblDay = new JLabel("일");
   JLabel lblEmail = new JLabel("- E-mail 주소 ");
   JLabel lblEmailCheckNo = new JLabel("인증번호 ");

   JButton btnUploadPhoto = new JButton("사진업로드");
   JButton btnIDCheck = new JButton("ID중복확인");
   JButton btnEmailNoSend = new JButton("인증번호 전송");
   JButton btnEmailNoCheck = new JButton("인증번호 확인");
   JButton btnJoin = new JButton("가입하기");
   JButton btnCancel = new JButton("취소");

   JTextField TFieldID = new JTextField();
   JPasswordField tFieldPW = new JPasswordField();
   JPasswordField tFieldPWCheck = new JPasswordField();
   JTextField TFieldName = new JTextField(6);
   JTextField TFieldEmail = new JTextField();
   JTextField TFieldEmailCheckNo = new JTextField();

   JRadioButton rdbtnMan = new JRadioButton("남자");
   JRadioButton rdbtnWoman = new JRadioButton("여자");
   ButtonGroup rbtnSexGroup = new ButtonGroup();

   // 생년월일 콤보박스

   JComboBox comBoxYear = new JComboBox();
   JComboBox comBoxMonth = new JComboBox();
   JComboBox comBoxDay = new JComboBox();

   Random random = new Random();
   private int randomNum = Integer.MIN_VALUE;

   PnlPhotoTest pnlPhoto = new PnlPhotoTest();
   ImageIcon icon = new ImageIcon("src/resource/defaultImg.png");
   Image img = icon.getImage();
   FileDialog open = new FileDialog(this, "열기");
  
  String readFile=null;

   class PnlPhotoTest extends JPanel {
      @Override
      protected void paintComponent(Graphics g) {
         g.drawImage(img, 0, 0, 100, 100, this);
      }
   }

   // 생성자

   // 생성자
   // public JoinJDialogView(LoginJFrameView loginPage,String title) {
   public JoinDialog(LoginView loginView) {
      super(loginView);
      this.setTitle("랜덤채팅 회원 가입");

      // 성별 단일선택 그룹화
      ButtonGroup groupGender = new ButtonGroup();
      groupGender.add(rdbtnMan);
      groupGender.add(rdbtnWoman);

      comBoxYear.setModel(new DefaultComboBoxModel(new String[] { "1998", "1997", "1996", "1995", "1994", "1993",
            "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983" }));
      comBoxMonth.setModel(new DefaultComboBoxModel(
            new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
      comBoxDay.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09",
            "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
            "27", "28", "29", "30", "31" }));

      pnlPhoto.setBackground(new Color(255, 153, 153));

      /*
       * JoinPage의 (전체)레이아웃: GroupLayout
       */
      GroupLayout groupLayout = new GroupLayout(getContentPane());
      groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
                  .createParallelGroup(
                        Alignment.LEADING)
                  .addGroup(
                        groupLayout.createSequentialGroup()
                              .addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 70,
                                    GroupLayout.PREFERRED_SIZE)
                              .addContainerGap(312, Short.MAX_VALUE))
                  .addGroup(groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblPWCheck)
                              .addComponent(lblID, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                              .addComponent(lblPW, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                              .addComponent(lblPhoto))
                        .addGap(16)
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                              .addGroup(groupLayout.createSequentialGroup().addGap(7).addComponent(pnlPhoto,
                                    GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                              .addComponent(tFieldPWCheck, 122, 122, Short.MAX_VALUE))
                        .addGap(150))
                  .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                              .addGroup(groupLayout
                                    .createSequentialGroup()
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                          .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblBirthday, GroupLayout.DEFAULT_SIZE, 90,
                                                      Short.MAX_VALUE)
                                                .addGap(18))
                                          .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 101,
                                                      GroupLayout.PREFERRED_SIZE)
                                                .addGap(2))
                                          .addGroup(groupLayout.createSequentialGroup().addGap(10)
                                                .addComponent(lblEmailCheckNo,
                                                      GroupLayout.PREFERRED_SIZE, 70,
                                                      GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(ComponentPlacement.UNRELATED))
                              .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 70,
                                          GroupLayout.PREFERRED_SIZE)
                                    .addGap(38)))
                        .addGroup(
                              groupLayout.createParallelGroup(Alignment.LEADING)
                                    .addGroup(groupLayout.createSequentialGroup()
                                          .addComponent(TFieldName, GroupLayout.PREFERRED_SIZE, 124,
                                                GroupLayout.PREFERRED_SIZE)
                                          .addContainerGap())
                                    .addGroup(Alignment.TRAILING, groupLayout
                                          .createParallelGroup(
                                                Alignment.TRAILING)
                                          .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout
                                                      .createParallelGroup(Alignment.LEADING)
                                                      .addGroup(groupLayout.createSequentialGroup()
                                                            .addGroup(groupLayout
                                                                  .createParallelGroup(
                                                                        Alignment.LEADING,
                                                                        false)
                                                                  .addComponent(TFieldID, 123,
                                                                        123, Short.MAX_VALUE)
                                                                  .addComponent(tFieldPW)
                                                                  .addComponent(
                                                                        TFieldEmailCheckNo,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        123,
                                                                        GroupLayout.PREFERRED_SIZE))
                                                            .addGap(18)
                                                            .addGroup(groupLayout
                                                                  .createParallelGroup(
                                                                        Alignment.LEADING)
                                                                  .addComponent(btnEmailNoCheck)
                                                                  .addGroup(groupLayout
                                                                        .createSequentialGroup()
                                                                        .addGap(2)
                                                                        .addGroup(groupLayout
                                                                              .createParallelGroup(
                                                                                    Alignment.LEADING)
                                                                              .addComponent(
                                                                                    btnUploadPhoto)
                                                                              .addComponent(
                                                                                    btnIDCheck)))))
                                                      .addGroup(groupLayout.createSequentialGroup()
                                                            .addGroup(groupLayout
                                                                  .createParallelGroup(
                                                                        Alignment.LEADING)
                                                                  .addComponent(comBoxYear,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        64,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                  .addComponent(rdbtnMan))
                                                            .addPreferredGap(
                                                                  ComponentPlacement.RELATED)
                                                            .addGroup(groupLayout
                                                                  .createParallelGroup(
                                                                        Alignment.LEADING)
                                                                  .addComponent(rdbtnWoman)
                                                                  .addGroup(groupLayout
                                                                        .createSequentialGroup()
                                                                        .addComponent(lblYear)
                                                                        .addGap(11)
                                                                        .addComponent(
                                                                              comBoxMonth,
                                                                              GroupLayout.PREFERRED_SIZE,
                                                                              GroupLayout.DEFAULT_SIZE,
                                                                              GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(
                                                                              ComponentPlacement.RELATED)
                                                                        .addComponent(lblMonth)
                                                                        .addPreferredGap(
                                                                              ComponentPlacement.RELATED)
                                                                        .addComponent(comBoxDay,
                                                                              GroupLayout.PREFERRED_SIZE,
                                                                              GroupLayout.DEFAULT_SIZE,
                                                                              GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(5)
                                                                        .addComponent(lblDay,
                                                                              GroupLayout.PREFERRED_SIZE,
                                                                              27,
                                                                              GroupLayout.PREFERRED_SIZE)))))
                                                .addGap(33))
                                          .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(btnJoin)
                                                .addPreferredGap(ComponentPlacement.RELATED, 14,
                                                      Short.MAX_VALUE)
                                                .addComponent(btnCancel).addGap(122))
                                          .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(TFieldEmail, GroupLayout.DEFAULT_SIZE,
                                                      123, Short.MAX_VALUE)
                                                .addGap(18).addComponent(btnEmailNoSend)
                                                .addGap(33))))))));
      groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup().addContainerGap(53, Short.MAX_VALUE)
                  .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                        .addGroup(groupLayout.createSequentialGroup()
                              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(pnlPhoto, GroupLayout.PREFERRED_SIZE, 89,
                                          GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPhoto))
                              .addGap(18))
                        .addGroup(groupLayout.createSequentialGroup().addComponent(btnUploadPhoto).addGap(26)))
                  .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblID)
                        .addComponent(btnIDCheck).addComponent(TFieldID, GroupLayout.PREFERRED_SIZE,
                              GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
                        .createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                              .addComponent(lblPW).addComponent(tFieldPW, GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(9)
                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPWCheck)
                              .addComponent(tFieldPWCheck, GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblName)
                              .addComponent(TFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblGender)
                              .addComponent(rdbtnWoman).addComponent(rdbtnMan))
                        .addGap(15)
                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblBirthday)
                              .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                    .addComponent(comBoxMonth, GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMonth).addComponent(lblYear).addComponent(comBoxYear,
                                          GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                          GroupLayout.PREFERRED_SIZE))))
                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                              .addComponent(comBoxDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE)
                              .addComponent(lblDay)))
                  .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup().addGap(18).addComponent(lblEmail))
                        .addGroup(groupLayout.createSequentialGroup()
                              .addPreferredGap(ComponentPlacement.UNRELATED).addGroup(
                                    groupLayout.createParallelGroup(Alignment.BASELINE)
                                          .addComponent(TFieldEmail, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                          .addComponent(btnEmailNoSend))))
                  .addGap(8)
                  .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnEmailNoCheck)
                        .addComponent(lblEmailCheckNo).addComponent(TFieldEmailCheckNo,
                              GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                              GroupLayout.PREFERRED_SIZE))
                  .addGap(18).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnCancel)
                        .addComponent(btnJoin))
                  .addGap(7)));
      getContentPane().setLayout(groupLayout);

      rbtnSexGroup.add(rdbtnMan);
      rbtnSexGroup.add(rdbtnWoman);

      setSize(400, 500);
      setVisible(true);
      setResizable(false);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);

      /** 리스너 등록 */

      btnUploadPhoto.addActionListener(this);
      btnIDCheck.addActionListener(this);
      btnEmailNoSend.addActionListener(this);
      btnEmailNoCheck.addActionListener(this);
      btnJoin.addActionListener(this);
      btnCancel.addActionListener(this);

   }// 생성자

   public void sendFile() {
      try {
         pw.println(TFieldID.getText());
         outputStream = sk.getOutputStream();

          if(readFile==null){
             readFile="src/resource/defaultImg.png";
         }
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

   /** 버튼 리스너 작업 */
   public void actionPerformed(ActionEvent e) {
      // 1.이미지 업로드
      if (e.getSource() == btnUploadPhoto) {
         open.setVisible(true);
         String dirPath = open.getDirectory();
         String fileName = open.getFile();
         readFile = dirPath + fileName;
         if (readFile != null) {
            icon = new ImageIcon(readFile);
            img = icon.getImage();
            repaint();
         }
      
      }
      // 2.ID중복 확인
      else if (e.getSource() == btnIDCheck) {
         switch (idValidate()) {
         case 0:
            joinController.getIdByCheck(TFieldID.getText());
            idFlag = true;
            break;
         case 1:
            FailView.failMessage("id 공백은 허용하지 않습니다.");
            idFlag = false;
            break;
         case 2:
            FailView.failMessage("id 는 4글자 이상의 문자로 구성되어야 합니다.");
            idFlag = false;
            break;
         default:
            break;
         }
      }

      // 3.이메일 보내기
      else if (e.getSource() == btnEmailNoSend) {
         randomNum = random.nextInt(200000) + 100000;
         new MailService(TFieldEmail.getText(), "랜덤 채팅 인증번호 입니다", "고객님의 인증번호는  " + randomNum + "입니다");
         JOptionPane.showMessageDialog(this, "인증번호를 전송하였습니다.");
      }

      // 4.이메일 인증확인
      else if (e.getSource() == btnEmailNoCheck) {
         if (TFieldEmailCheckNo.getText().equals(randomNum + "")) {
            JOptionPane.showMessageDialog(this, "인증에 성공하였습니다.");
            emailFlag = true;
         } else {
            JOptionPane.showMessageDialog(this, "인증에 실패 하였습니다");
         }
      }
      // 5.회원 가입
      else if (e.getSource() == btnJoin) {
         ValidateCreateUser();
      }
      // 6.가입 취소
      else if (e.getSource() == btnCancel) {
         this.dispose();
      }
   }

   /** 유효성 검사 */
   /** 1.id유효성 검사 */
   public int idValidate() {
      String id = TFieldID.getText();
      // 1.id가 공백일때
      if (id.trim().equals("")) {
         return 1;
      }
      // 2.id 길이가 4글자가 안될때
      else if (id.length() < 4) {
         return 2;
      }
      return 0;
   }

   /** 2.password 유효성 검사 */
   public int pwdValidate() {
      String pwd = new String(tFieldPW.getPassword());
      String pwdchk = new String(tFieldPWCheck.getPassword());
      if (pwd.trim().equals(""))
         return 1;
      else if (pwd.length() < 5)
         return 2;
      // 3.password 에 특수문자가 없을 경우
      else if (!(pwd.contains("!") || pwd.contains("@") || pwd.contains("#") || pwd.contains("$") || pwd.contains("%")
            || pwd.contains("^") || pwd.contains("&") || pwd.contains("*"))) {
         return 3;
      }
      // 4.
      else if (!pwd.equals(pwdchk)) {
         return 4;
      }

      return 0;
   }

   /** 3.이름 유효성 검사 */
   public int nameValidate() {
      String id = TFieldName.getText();
      if (id.trim().equals(""))
         return 1;
      else if (id.length() > 6)
         return 2;
      return 0;
   }

   /** 4.성별 유효성 검사 */
   public int sexValidate() {
      if (rdbtnMan.isSelected())
         return 1;
      else if (rdbtnWoman.isSelected())
         return 2;
      else
         return 0;
   }

   /** 2.이메일 유효성 검사 */
   public boolean emailValidate() {
      return emailFlag;
   }

   /***************************************************************/
   /** 유효성 검사 메시지 */
   public void idValidateMsg() {
      System.out.println(idFlag);
      if (idFlag == true) {
         switch (idChk) {
         case 0:
            pwdValidateMsg();
            break;
         case 1:
            FailView.failMessage("id 공백은 허용하지 않습니다.");
            break;
         case 2:
            FailView.failMessage("id 는 4글자 이상의 문자로 구성되어야 합니다.");
            break;
         }
      } else
         FailView.failMessage("id 중복검사를 해주세요");
   }

   public void pwdValidateMsg() {
      switch (pwdChk) {
      case 0:
         nameValideMsg();
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

   public void nameValideMsg() {
      switch (nameChk) {
      case 0:
         sexValidateMsg();
         break;
      case 1:
         FailView.failMessage("이름 공백은 허용하지 않습니다.");
         break;
      case 2:
         FailView.failMessage("이름은 6글자 이상 허용하지 않습니다.");
         break;
      }
   }

   public void sexValidateMsg() {
      switch (sexChk) {
      case 0:
         FailView.failMessage("성별을 체크해주세요.");
         break;
      case 1:
         emailValidateMsg();
         break;
      case 2:
         emailValidateMsg();
         break;
      }
   }

   public void emailValidateMsg() {
      if (!emailValidate()) {
         FailView.failMessage("이메일 인증을 해주세요.");
      }
   }

   public void ValidateCreateUser() {
      idChk = idValidate();
      pwdChk = pwdValidate();
      nameChk = nameValidate();
      sexChk = sexValidate();

      if (idFlag == true && pwdChk == 0 && nameChk == 0 && sexChk != 0 && emailFlag == true) {
         String birthDate = comBoxYear.getSelectedItem().toString() + comBoxMonth.getSelectedItem().toString()
               + comBoxDay.getSelectedItem().toString();

         UserInfoDTO user = new UserInfoDTO(TFieldID.getText(), new String(tFieldPW.getPassword()),
               TFieldName.getText(), sexValidate(), Integer.parseInt(birthDate), TFieldEmail.getText(), 0, 5);

         if (joinController.insertUserInfo(user) != 0) {
            sendFile();
            this.dispose();
         }
         // System.out.println(birthDate);
      } else {
         idValidateMsg();
      } // else 끝
   }// ValidateCreateUser

}