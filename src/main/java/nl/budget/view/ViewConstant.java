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
	
	// CreateAccountDialog
	public static final String DIALOG_ADD_ACCOUNT = "Rekening toevoegen";
	public static final String IBAN_LABEL = "IBAN";
	public static final String ACCOUNT_HOLDER_LABEL = "Rekeninghouder";
	public static final String DESCRIPTION_LABEL = "Omschrijving";
	public static final String CURRENT_BALANCE_LABEL = "Huidige balans";
	
	// CreatePostDialog
	public static final String DIALOG_ADD_POST = "Post toevoegen";
	public static final String START_BALANCE_LABEL = "Start balans";
	public static final String BUDGET_LABEL = "Maand budget";
	public static final String ACCOUNT_LABEL = "Rekening";
	
	// UpdateTransactionPostDialog
	public static final String DIALOG_UPDATE_TRANSACTION_POST = "Wijzig transactie post";
		
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
	public static final String ADD_POSTS_TO_TRANSACTIONS = "Posten toekennen";
	
	public static final String POSTS = "Posten";
	public static final String POSTS_OVERVIEW = "Overzicht";
	public static final String ADD_POST = "Post toevoegen";
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
	
	public static final String POSTS_CATEGORY = "Categorie";
	public static final String POSTS_START_BALANCE = "Start balans";
	public static final String POSTS_BUDGET = "Begroting";
	public static final String POSTS_END_BALANCE = "Eind balans";
	public static final String POSTS_ACCOUNT = "Rekening";
	public static final String POSTS_MONTH_YEAR = "Maand";
	
}
