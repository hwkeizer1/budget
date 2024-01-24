package nl.budget.view;

public class ViewConstant {
	
	private ViewConstant() {}
	
	// General
	public static final String OK_BUTTON_TEXT = "OK";
	public static final String CANCEL_BUTTON_TEXT = "Annuleren";

	// Application
	public static final String PROGRAM_TITLE = "Budget versie 0.0.1";
	public static final String DEFAULT_CONFIG_FILE = "default.properties";
	public static final String CONFIG_FILE = "budget.properties";
	
	// Configuration properties
	public static final String BACKUP_FOLDER_PROP = "backup-folder";
	public static final String BACKUPS_TO_KEEP_PROP = "backups-to-keep";
	public static final String DOWNLOAD_FOLDER_PROP = "download-folder";
	
	// ConfigurationDialog
	public static final String DIALOG_CHANGE_CONFIGURATION = "Instellingen wijzigen";
	public static final String ELLIPSIS = "...";
	public static final String BACKUP_FOLDER_LABEL = "Backup folder";
	public static final String DOWNLOAD_FOLDER_LABEL = "Download folder";
	
	// AddAccountConfigurationDialog
	public static final String DIALOG_ADD_ACCOUNT = "Rekening toevoegen";
	public static final String IBAN_LABEL = "IBAN";
	public static final String ACCOUNT_HOLDER_LABEL = "Rekeninghouder";
	public static final String DESCRIPTION_LABEL = "Omschrijving";
	public static final String BALANCE_LABEL = "Huidige balans";
		
	// Menu items
	public static final String BUDGET = "Budget";
	public static final String CONFIGURATION = "Instellingen";
	public static final String CLOSE_PROGRAM = "Programma afsluiten";
	
	public static final String ACCOUNTS = "Rekeningen";
	public static final String ACCOUNTS_OVERVIEW = "Overzicht";
	public static final String NEW_ACCOUNT = "Nieuwe rekening toevoegen";
	
	public static final String TRANSACTIONS = "Transacties";
	public static final String TRANSACTIONS_OVERVIEW = "Overzicht";
	public static final String SEARCH_NEW_TRANSACTIONS = "Zoek nieuwe transacties";
	
	public static final String POSTS = "Posten";
	public static final String POSTS_OVERVIEW = "Overzicht";
	public static final String EDIT_POSTS = "Posten bewerken";
	
	// Bank codes
	public static final String ASN = "ASNB";
	public static final String ABNAMRO = "ABNA";
	public static final String TRIODOS = "TRIO";
	public static final String ING = "INGB";
	
	// Table columns
	public static final String TRANSACTIONS_POST = "Post";
	public static final String TRANSACTIONS_DATE = "Datum";
	public static final String TRANSACTIONS_NUMBER = "Volgnummer";
	public static final String TRANSACTIONS_ACCOUNT = "Rekening";
	public static final String TRANSACTIONS_CONTRA_ACCOUNT = "Tegenrekening";
	public static final String TRANSACTIONS_BALANCE = "Balans";
	public static final String TRANSACTIONS_CURRENCY_TYPE = "Valuta type";
	public static final String TRANSACTIONS_AMOUNT = "Bedrag";
	public static final String TRANSACTIONS_DESCRIPTION = "Omschrijving";
}
