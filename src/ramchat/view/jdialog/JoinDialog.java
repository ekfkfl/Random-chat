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
 * ȸ������ȭ�� - JDialog - LoginJFrameView�� btnJoinŬ���� ����ȴ�. - resize��
 * false(�����ũ�������Ұ�) - ������! ������� �޺��ڽ�
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

   /** �÷��� ���� (���̵�, �̸��� true�ΰ�츸 ����ǰ�) */
   private boolean idFlag = false;
   private boolean emailFlag = false;

   // �������
   JLabel lblPhoto = new JLabel("- �����ʻ���");
   JLabel lblID = new JLabel("- ID ");
   JLabel lblPW = new JLabel("- ��й�ȣ ");
   JLabel lblPWCheck = new JLabel("- ��й�ȣ Ȯ�� ");
   JLabel lblName = new JLabel("- �̸� ");
   JLabel lblGender = new JLabel("- ���� ");
   JLabel lblBirthday = new JLabel("- ������� ");
   JLabel lblYear = new JLabel("��");
   JLabel lblMonth = new JLabel("��");
   JLabel lblDay = new JLabel("��");
   JLabel lblEmail = new JLabel("- E-mail �ּ� ");
   JLabel lblEmailCheckNo = new JLabel("������ȣ ");

   JButton btnUploadPhoto = new JButton("�������ε�");
   JButton btnIDCheck = new JButton("ID�ߺ�Ȯ��");
   JButton btnEmailNoSend = new JButton("������ȣ ����");
   JButton btnEmailNoCheck = new JButton("������ȣ Ȯ��");
   JButton btnJoin = new JButton("�����ϱ�");
   JButton btnCancel = new JButton("���");

   JTextField TFieldID = new JTextField();
   JPasswordField tFieldPW = new JPasswordField();
   JPasswordField tFieldPWCheck = new JPasswordField();
   JTextField TFieldName = new JTextField(6);
   JTextField TFieldEmail = new JTextField();
   JTextField TFieldEmailCheckNo = new JTextField();

   JRadioButton rdbtnMan = new JRadioButton("����");
   JRadioButton rdbtnWoman = new JRadioButton("����");
   ButtonGroup rbtnSexGroup = new ButtonGroup();

   // ������� �޺��ڽ�

   JComboBox comBoxYear = new JComboBox();
   JComboBox comBoxMonth = new JComboBox();
   JComboBox comBoxDay = new JComboBox();

   Random random = new Random();
   private int randomNum = Integer.MIN_VALUE;

   PnlPhotoTest pnlPhoto = new PnlPhotoTest();
   ImageIcon icon = new ImageIcon("src/resource/defaultImg.png");
   Image img = icon.getImage();
   FileDialog open = new FileDialog(this, "����");
  
  String readFile=null;

   class PnlPhotoTest extends JPanel {
      @Override
      protected void paintComponent(Graphics g) {
         g.drawImage(img, 0, 0, 100, 100, this);
      }
   }

   // ������

   // ������
   // public JoinJDialogView(LoginJFrameView loginPage,String title) {
   public JoinDialog(LoginView loginView) {
      super(loginView);
      this.setTitle("����ä�� ȸ�� ����");

      // ���� ���ϼ��� �׷�ȭ
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
       * JoinPage�� (��ü)���̾ƿ�: GroupLayout
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

      /** ������ ��� */

      btnUploadPhoto.addActionListener(this);
      btnIDCheck.addActionListener(this);
      btnEmailNoSend.addActionListener(this);
      btnEmailNoCheck.addActionListener(this);
      btnJoin.addActionListener(this);
      btnCancel.addActionListener(this);

   }// ������

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
         System.out.println("���� ����");
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

   /** ��ư ������ �۾� */
   public void actionPerformed(ActionEvent e) {
      // 1.�̹��� ���ε�
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
      // 2.ID�ߺ� Ȯ��
      else if (e.getSource() == btnIDCheck) {
         switch (idValidate()) {
         case 0:
            joinController.getIdByCheck(TFieldID.getText());
            idFlag = true;
            break;
         case 1:
            FailView.failMessage("id ������ ������� �ʽ��ϴ�.");
            idFlag = false;
            break;
         case 2:
            FailView.failMessage("id �� 4���� �̻��� ���ڷ� �����Ǿ�� �մϴ�.");
            idFlag = false;
            break;
         default:
            break;
         }
      }

      // 3.�̸��� ������
      else if (e.getSource() == btnEmailNoSend) {
         randomNum = random.nextInt(200000) + 100000;
         new MailService(TFieldEmail.getText(), "���� ä�� ������ȣ �Դϴ�", "������ ������ȣ��  " + randomNum + "�Դϴ�");
         JOptionPane.showMessageDialog(this, "������ȣ�� �����Ͽ����ϴ�.");
      }

      // 4.�̸��� ����Ȯ��
      else if (e.getSource() == btnEmailNoCheck) {
         if (TFieldEmailCheckNo.getText().equals(randomNum + "")) {
            JOptionPane.showMessageDialog(this, "������ �����Ͽ����ϴ�.");
            emailFlag = true;
         } else {
            JOptionPane.showMessageDialog(this, "������ ���� �Ͽ����ϴ�");
         }
      }
      // 5.ȸ�� ����
      else if (e.getSource() == btnJoin) {
         ValidateCreateUser();
      }
      // 6.���� ���
      else if (e.getSource() == btnCancel) {
         this.dispose();
      }
   }

   /** ��ȿ�� �˻� */
   /** 1.id��ȿ�� �˻� */
   public int idValidate() {
      String id = TFieldID.getText();
      // 1.id�� �����϶�
      if (id.trim().equals("")) {
         return 1;
      }
      // 2.id ���̰� 4���ڰ� �ȵɶ�
      else if (id.length() < 4) {
         return 2;
      }
      return 0;
   }

   /** 2.password ��ȿ�� �˻� */
   public int pwdValidate() {
      String pwd = new String(tFieldPW.getPassword());
      String pwdchk = new String(tFieldPWCheck.getPassword());
      if (pwd.trim().equals(""))
         return 1;
      else if (pwd.length() < 5)
         return 2;
      // 3.password �� Ư�����ڰ� ���� ���
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

   /** 3.�̸� ��ȿ�� �˻� */
   public int nameValidate() {
      String id = TFieldName.getText();
      if (id.trim().equals(""))
         return 1;
      else if (id.length() > 6)
         return 2;
      return 0;
   }

   /** 4.���� ��ȿ�� �˻� */
   public int sexValidate() {
      if (rdbtnMan.isSelected())
         return 1;
      else if (rdbtnWoman.isSelected())
         return 2;
      else
         return 0;
   }

   /** 2.�̸��� ��ȿ�� �˻� */
   public boolean emailValidate() {
      return emailFlag;
   }

   /***************************************************************/
   /** ��ȿ�� �˻� �޽��� */
   public void idValidateMsg() {
      System.out.println(idFlag);
      if (idFlag == true) {
         switch (idChk) {
         case 0:
            pwdValidateMsg();
            break;
         case 1:
            FailView.failMessage("id ������ ������� �ʽ��ϴ�.");
            break;
         case 2:
            FailView.failMessage("id �� 4���� �̻��� ���ڷ� �����Ǿ�� �մϴ�.");
            break;
         }
      } else
         FailView.failMessage("id �ߺ��˻縦 ���ּ���");
   }

   public void pwdValidateMsg() {
      switch (pwdChk) {
      case 0:
         nameValideMsg();
         break;
      case 1:
         FailView.failMessage("password ������ ������� �ʽ��ϴ�.");
         break;
      case 2:
         FailView.failMessage("password�� 5���� �̻� Ư�����ڰ� ���ԵǾ�� �մϴ� .");
         break;
      case 3:
         FailView.failMessage("password�� Ư�����ڰ� �ѱ��� �̻� ���ԵǾ�� �մϴ�");
         break;
      case 4:
         FailView.failMessage("ù��° password�� ��ġ ���� �ʽ��ϴ�.");
      }
   }

   public void nameValideMsg() {
      switch (nameChk) {
      case 0:
         sexValidateMsg();
         break;
      case 1:
         FailView.failMessage("�̸� ������ ������� �ʽ��ϴ�.");
         break;
      case 2:
         FailView.failMessage("�̸��� 6���� �̻� ������� �ʽ��ϴ�.");
         break;
      }
   }

   public void sexValidateMsg() {
      switch (sexChk) {
      case 0:
         FailView.failMessage("������ üũ���ּ���.");
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
         FailView.failMessage("�̸��� ������ ���ּ���.");
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
      } // else ��
   }// ValidateCreateUser

}