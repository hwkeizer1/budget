package nl.budget.model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

import nl.budget.application.Budget;

@Component
public class BudgetPreferences {
	private static final String DOWNLOAD_FOLDER = "download-folder";
	private static final String BANKACCOUNT_LIST = "bankaccount-list";
	
	private Preferences userPrefs;
	private List<String> bankAccountList = new ArrayList<>();
	
	public BudgetPreferences() {
		this.userPrefs = Preferences.userNodeForPackage(Budget.class);
	}
	
	public Path getDownloadLocation() {
		Path path = Paths.get(userPrefs.get(DOWNLOAD_FOLDER, ""));
		return path;
	}
	
	public void setDownloadLocation(String downloadFolder) {
		userPrefs.put(DOWNLOAD_FOLDER, downloadFolder);
	}
	
	public void addBankAccountToList(String bankAccountNumber) {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonArray = userPrefs.get(BANKACCOUNT_LIST, "");
		if (!StringUtils.hasLength(jsonArray)) {
			bankAccountList.add(bankAccountNumber);
			try {
				jsonArray = objectMapper.writeValueAsString(bankAccountList);
				userPrefs.put(BANKACCOUNT_LIST, jsonArray);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				bankAccountList = objectMapper.readValue(jsonArray, new TypeReference<List<String>>() {});
				bankAccountList.add(bankAccountNumber);
				jsonArray = objectMapper.writeValueAsString(bankAccountList);
				userPrefs.put(BANKACCOUNT_LIST, jsonArray);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
