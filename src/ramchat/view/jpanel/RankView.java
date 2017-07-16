package ramchat.view.jpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ramchat.controller.impl.FileClientConnetControllerImpl;
import ramchat.controller.impl.RankControllerImpl;
import ramchat.model.dto.UserInfoDTO;
import ramchat.view.joptionpane.FailView;

/**
 * 랭킹화면 - 일간, 주간, 월간 - ※주의! 일간, 주간, 월간 라벨에 표시 안됨.
 */
public class RankView extends JPanel implements ActionListener {

   // Top
   JLabel lbTitle = new JLabel("<킹카 & 퀸카>");
   JLabel lbUnit = new JLabel("");
   JComboBox comboBoxUnit = new JComboBox();

   JLabel lbMans = new JLabel("#킹카");
   JLabel lbWomans = new JLabel("#퀸카");

   // Rank Init
   JLabel lbMan1 = new JLabel("1.");
   ImagePanel pnlMan1 = new ImagePanel();
   JLabel lbMan1Name = new JLabel("BoyUserA");
   JLabel lbMan1Heart = new JLabel("HeartCount");

   JLabel lbMan2 = new JLabel("2.");
   ImagePanel pnlMan2 = new ImagePanel();
   JLabel lbMan2Name = new JLabel("BoyUserB");
   JLabel lbMan2Heart = new JLabel("HeartCount");

   JLabel lbMan3 = new JLabel("3.");
   ImagePanel pnlMan3 = new ImagePanel();
   JLabel lbMan3Name = new JLabel("BoyUserC");
   JLabel lbMan3Heart = new JLabel("HeartCount");

   JLabel lbWoman1 = new JLabel("1.");
   ImagePanel pnlWoman1 = new ImagePanel();
   JLabel lbWoman1Name = new JLabel("GirlUserA");
   JLabel lbWoman1Heart = new JLabel("HeartCount");

   JLabel lbWoman2 = new JLabel("2.");
   ImagePanel pnlWoman2 = new ImagePanel();
   JLabel lbWoman2Name = new JLabel("GirlUserB");
   JLabel lbWoman2Heart = new JLabel("HeartCount");

   JLabel lbWoman3 = new JLabel("3.");
   ImagePanel pnlWoman3 = new ImagePanel();
   JLabel lbWoman3Name = new JLabel("GirlUserC");
   JLabel lbWoman3Heart = new JLabel("HeartCount");
   
   FileClientConnetControllerImpl fileConnect = new FileClientConnetControllerImpl();

   /**
    * Create the panel.
    */
   public RankView() {

      comboBoxUnit.setModel(new DefaultComboBoxModel(new String[] { "일간", "주간", "월간" }));
      comboBoxUnit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.out.println(comboBoxUnit.getSelectedItem().toString());
            lbUnit = new JLabel(comboBoxUnit.getSelectedItem().toString());
         }
      });

      lbTitle.setFont(new Font("굴림", Font.BOLD, 18));

      lbMans.setForeground(new Color(65, 105, 225));
      lbMans.setFont(new Font("굴림", Font.BOLD, 20));

      lbWomans.setForeground(new Color(250, 128, 114));
      lbWomans.setFont(new Font("굴림", Font.BOLD, 20));

      pnlMan1.setBackground(Color.WHITE);
      pnlMan2.setBackground(Color.WHITE);
      pnlMan3.setBackground(Color.WHITE);
      pnlWoman1.setBackground(Color.WHITE);
      pnlWoman2.setBackground(Color.WHITE);
      pnlWoman3.setBackground(Color.WHITE);

      // GroupLayout
      GroupLayout groupLayout = new GroupLayout(this);
      groupLayout.setHorizontalGroup(
         groupLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
               .addContainerGap()
               .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                  .addGroup(groupLayout.createSequentialGroup()
                     .addComponent(lbUnit, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
                     .addGap(570)
                     .addComponent(comboBoxUnit, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
                  .addGroup(groupLayout.createSequentialGroup()
                     .addGap(26)
                     .addComponent(lbMans, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                     .addGap(239)
                     .addComponent(lbWomans, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                  .addComponent(lbTitle, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
                  .addGroup(groupLayout.createSequentialGroup()
                     .addGap(14)
                     .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING)
                           .addGroup(groupLayout.createSequentialGroup()
                              .addComponent(lbMan3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
                              .addGap(6)
                              .addComponent(pnlMan3, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                              .addGap(6)
                              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                 .addComponent(lbMan3Heart, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                 .addComponent(lbMan3Name, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
                              .addGap(47)
                              .addComponent(lbWoman3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
                              .addGap(6)
                              .addComponent(pnlWoman3, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                              .addGap(6)
                              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                 .addComponent(lbWoman3Name, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                 .addComponent(lbWoman3Heart, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
                           .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                              .addComponent(lbMan2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
                              .addGap(6)
                              .addComponent(pnlMan2, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                              .addGap(6)
                              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                 .addComponent(lbMan2Heart, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                 .addComponent(lbMan2Name, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
                              .addGap(37)
                              .addComponent(lbWoman2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
                              .addGap(6)
                              .addComponent(pnlWoman2, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                              .addGap(6)
                              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                 .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lbWoman2Heart, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                    .addGap(60))
                                 .addComponent(lbWoman2Name, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))))
                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                           .addComponent(lbMan1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
                           .addGap(6)
                           .addComponent(pnlMan1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                           .addGap(6)
                           .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                              .addComponent(lbMan1Heart, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbMan1Name, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
                           .addGap(28)
                           .addComponent(lbWoman1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
                           .addGap(6)
                           .addComponent(pnlWoman1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                           .addGap(6)
                           .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                              .addComponent(lbWoman1Heart, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbWoman1Name, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))))))
               .addGap(21))
      );
      groupLayout.setVerticalGroup(
         groupLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
               .addContainerGap()
               .addComponent(lbTitle, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
               .addGap(7)
               .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                  .addGroup(groupLayout.createSequentialGroup()
                     .addGap(3)
                     .addComponent(lbUnit))
                  .addComponent(comboBoxUnit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
               .addGap(42)
               .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                  .addComponent(lbMans, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                  .addComponent(lbWomans, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
               .addGap(18)
               .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                  .addComponent(lbMan1)
                  .addComponent(pnlMan1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                  .addGroup(groupLayout.createSequentialGroup()
                     .addComponent(lbMan1Name)
                     .addGap(7)
                     .addComponent(lbMan1Heart))
                  .addComponent(lbWoman1)
                  .addComponent(pnlWoman1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                  .addGroup(groupLayout.createSequentialGroup()
                     .addComponent(lbWoman1Name)
                     .addGap(7)
                     .addComponent(lbWoman1Heart)))
               .addGap(18)
               .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                  .addComponent(lbMan2)
                  .addComponent(pnlMan2, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                  .addGroup(groupLayout.createSequentialGroup()
                     .addComponent(lbMan2Name)
                     .addGap(7)
                     .addComponent(lbMan2Heart))
                  .addComponent(lbWoman2)
                  .addComponent(pnlWoman2, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                  .addGroup(groupLayout.createSequentialGroup()
                     .addComponent(lbWoman2Name)
                     .addGap(7)
                     .addComponent(lbWoman2Heart)))
               .addGap(18)
               .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                  .addComponent(lbMan3)
                  .addComponent(pnlMan3, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                  .addGroup(groupLayout.createSequentialGroup()
                     .addComponent(lbMan3Name)
                     .addGap(7)
                     .addComponent(lbMan3Heart))
                  .addComponent(lbWoman3)
                  .addComponent(pnlWoman3, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                  .addGroup(groupLayout.createSequentialGroup()
                     .addComponent(lbWoman3Name)
                     .addGap(7)
                     .addComponent(lbWoman3Heart)))
               .addContainerGap(118, Short.MAX_VALUE))
      );
      setLayout(groupLayout);

      comboBoxUnit.addActionListener(this);

      dailyView(1);
      dailyView(2);
   }

   public void dailyView(int gender) {
      List<UserInfoDTO> list = RankControllerImpl.getRankController().dailyRank(gender);
      if (list == null)
         return;
      List<BufferedImage> imageList = getPhoto(list);
      if (imageList == null) {
         return;
      }
      setAll(list, imageList, gender);
   }

   public void weeklyView(int gender) {
      List<UserInfoDTO> list = RankControllerImpl.getRankController().weeklyRank(gender);
      if (list == null)
         return;
      List<BufferedImage> imageList = getPhoto(list);
      if (imageList == null) {
         return;
      }
      setAll(list, imageList, gender);
   }

   public void monthlyView(int gender) {
      List<UserInfoDTO> list = RankControllerImpl.getRankController().monthlyRank(gender);
      if (list == null)
         return;
      List<BufferedImage> imageList = getPhoto(list);
      if (imageList == null) {
         return;
      }
      setAll(list, imageList, gender);
   }

   public List<BufferedImage> getPhoto(List<UserInfoDTO> list) {
      List<BufferedImage> imageList = new ArrayList<>();

      for (int i = 0; i < list.size(); i++) {
         imageList.add(fileConnect.getImg(list.get(i).getId()));
      }
      return imageList;
   }

   public void setAll(List<UserInfoDTO> list, List<BufferedImage> imageList, int gender) {
      if (gender == 1) {
         if (list.size() > 0) {
            lbMan1Name.setText("ID : "+list.get(0).getId());
            lbMan1Heart.setText("좋아요♥ : "+String.valueOf(list.get(0).getHeart()));
            pnlMan1.setbImg(imageList.get(0));
         }
         if (list.size() > 1) {
            lbMan2Name.setText("ID : "+list.get(1).getId());
            lbMan2Heart.setText("좋아요♥ : "+String.valueOf(list.get(1).getHeart()));
            pnlMan2.setbImg(imageList.get(1));
         }
         if (list.size() > 2) {
            lbMan3Name.setText("ID : "+list.get(2).getId());
            lbMan3Heart.setText("좋아요♥ : "+String.valueOf(list.get(2).getHeart()));
            pnlMan3.setbImg(imageList.get(2));
         }
      } else {
         if (list.size() > 0) {
            lbWoman1Name.setText("ID : "+list.get(0).getId());
            lbWoman1Heart.setText("좋아요♥ : "+String.valueOf(list.get(0).getHeart()));
            pnlWoman1.setbImg(imageList.get(0));
         }
         if (list.size() > 1) {
            lbWoman2Name.setText("ID : "+list.get(1).getId());
            lbWoman2Heart.setText("좋아요♥ : "+String.valueOf(list.get(1).getHeart()));
            pnlWoman2.setbImg(imageList.get(1));
         }
         if (list.size() > 2) {
            lbWoman3Name.setText("ID : "+list.get(2).getId());
            lbWoman3Heart.setText("좋아요♥ : "+String.valueOf(list.get(2).getHeart()));
            pnlWoman3.setbImg(imageList.get(2));
         }
      }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == comboBoxUnit) {
         String selected = comboBoxUnit.getSelectedItem().toString().trim();
         if (selected.equals("일간")) {
            dailyView(1);
            dailyView(2);
         } else if (selected.equals("주간")) {
            weeklyView(1);
            weeklyView(2);
         } else if (selected.equals("월간")) {
            monthlyView(1);
            monthlyView(2);
         }
      }
   }
}

class ImagePanel extends JPanel {
   private ImageIcon img;
   private BufferedImage bImg;

   public void setImg(ImageIcon img) {
      this.img = img;
   }

   public void setbImg(BufferedImage bImg) {
      this.bImg = bImg;
   }

   @Override
   public void paint(Graphics g) {
      if (img != null) {
         g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), null);
      }
      if (bImg != null) {
         g.drawImage(bImg, 0, 0, getWidth(), getHeight(), null);
      }
   }
}