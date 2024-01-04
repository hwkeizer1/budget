package nl.budget.view;

public class ViewConstant {
	
	private ViewConstant() {}

	// Application
	public static final String PROGRAM_TITLE = "Budget versie 0.0.1";
	public static final String DEFAULT_CONFIG_FILE = "default.properties";
	public static final String CONFIG_FILE = "budget.properties";
	
	// Configuration properties
	public static final String BACKUP_FOLDER_PROP = "backup-folder";
	public static final String BACKUPS_TO_KEEP_PROP = "backups-to-keep";
	public static final String DOWNLOAD_FOLDER_PROP = "download-folder";
	
	// Configuration dialog
	public static final String DIALOG_CHANGE_CONFIGURATION = "Instellingen wijzigen";
	public static final String ELLIPSIS = "...";
	public static final String BACKUP_FOLDER_LABEL = "Backup folder";
	public static final String DOWNLOAD_FOLDER_LABEL = "Download folder";
	public static final String OK_BUTTON_TEXT = "OK";
	public static final String CANCEL_BUTTON_TEXT = "Annuleren";
		
	// Menu items
	public static final String BUDGET = "Budget";
	public static final String CONFIGURATION = "Instellingen";
	public static final String CLOSE_PROGRAM = "Programma afsluiten";
	
	public static final String TRANSACTIONS = "Transacties";
	public static final String TRANSACTIONS_OVERVIEW = "Overzicht";
	public static final String SEARCH_NEW_TRANSACTIONS = "Zoek nieuwe transacties";
	
	public static final String POSTS = "Posten";
	
	
	// Table columns
	public static final String TRANSACTIONS_DATE = "Datum";
	public static final String TRANSACTIONS_NUMBER = "Volgnummer";
	public static final String TRANSACTIONS_ACCOUNT = "Rekening";
	public static final String TRANSACTIONS_CONTRA_ACCOUNT = "Tegenrekening";
	public static final String TRANSACTIONS_BALANCE = "Balans";
	public static final String TRANSACTIONS_CURRENCY_TYPE = "Valuta type";
	public static final String TRANSACTIONS_AMOUNT = "Bedrag";
	public static final String TRANSACTIONS_DESCRIPTION = "Omschrijving";
}
