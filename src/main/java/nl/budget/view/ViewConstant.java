package nl.budget.view;

public class ViewConstant {
	
	private ViewConstant() {}

	// Application
	public static final String PROGRAM_TITLE = "Budget versie 0.0.1";
	public static final String DEFAULT_CONFIG_FILE = "default.properties";
	public static final String CONFIG_FILE = "budget.properties";
	
	// Configuration properties
	public static final String BACKUP_FOLDER = "backup-folder";
	public static final String BACKUPS_TO_KEEP = "backups-to-keep";
	public static final String DOWNLOAD_FOLDER = "download-folder";
	
	// Menu items
	public static final String BUDGET = "Budget";
	public static final String TRANSACTIONS = "Transacties";
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
