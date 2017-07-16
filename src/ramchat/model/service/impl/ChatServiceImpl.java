package ramchat.model.service.impl;

import java.sql.SQLException;

import ramchat.model.dao.ChatDao;
import ramchat.model.dao.impl.ChatDaoImpl;
import ramchat.model.service.ChatService;

public class ChatServiceImpl implements ChatService {

   ChatDao chatDao= new ChatDaoImpl();
   
   @Override
   public int dailyHeart(String id) throws SQLException {
   
      int result= chatDao.dailyHeart(id);
      if(result==-1) throw new SQLException();
      
      return result;
   }

   
   
   @Override
   public int totalHeart(String id) throws SQLException {
   
      int result= chatDao.totalHeart(id);
      if(result==-1) throw new SQLException();
      
      return result;
   }

   @Override
   public int sendHeart(String receiveId, String sendId) throws SQLException {
      int result=chatDao.sendHeart(receiveId, sendId);
      if(result==0)throw new SQLException("service 하트 보내기 에러");
      return result;
   }

   @Override
   public int receiveHeart(String receiveId, String sendId) throws SQLException {
      // TODO Auto-generated method stub
      return 0;
   }

   
   
}