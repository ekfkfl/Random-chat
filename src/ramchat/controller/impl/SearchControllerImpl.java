package ramchat.controller.impl;

import java.sql.SQLException;

import ramchat.controller.SearchController;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.service.SearchService;
import ramchat.model.service.impl.SearchServiceImpl;
import ramchat.view.joptionpane.FailView;
import ramchat.view.joptionpane.SuccessView;

public class SearchControllerImpl implements SearchController {
	private SearchService searchService = new SearchServiceImpl();
	
	public boolean searchId(String name, String email) {
		boolean result=false;
		try {
			searchService.searchId(name, email);
			SuccessView.successMessage("해당 E-Mail로 ID를 전송하였습니다.\n메일을 확인해주세요.");
			result=true;
		} catch (Exception e) {
			FailView.failMessage(e.getMessage());
		}
		return result;
	}

	public boolean searchPw(String id, String name, String email) {
		boolean result=false;
		try {
			searchService.searchPw(id, name, email);
			SuccessView.successMessage("해당 E-Mail로 PW를 전송하였습니다.\n메일을 확인해주세요.");
			result=true;
		} catch (SQLException e) {
			FailView.failMessage(e.getMessage());
		}
		return result;
	}
}
