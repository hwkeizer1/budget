package nl.budget.view;

public final class ViewMessage {

	private ViewMessage() {}
	
	public static final String NO_CONFIGURATION_FOUND = "Geen configuratie gevonden: {}";
	public static final String CANNOT_SAVE_CONFIGURATION = "Kan configuratie niet opslaan: {}";
	public static final String CANNOT_SAVE_DEFAULT_CONFIGURATION = "Kan default configuratie niet opslaan: {}";
	public static final String ERROR_FINDING_FILES_IN_DOWNLOAD_FOLDER = "Fout bij het zoeken van bestanden in de download folder: {}";
	public static final String ERROR_OPENING_FILES_IN_DOWNLOAD_FOLDER = "Fout bij het openen van bestanden in de download folder: {}";	
	
	// CreateAccountDialog
	public static final String IBAN_NUMBER_INCORRECT = "Het IBAN nummer is ongeldig of bestaat al";
	public static final String ACCOUNT_HOLDER_REQUIRED = "De rekeninghouder moet ingevuld worden";
	public static final String DESCRIPTION_REQUIRED = "De omschrijving moet ingevuld worden";
	public static final String BALANCE_REQUIRED = "De balans moet ingevuld worden";
	
	// CreatePostDialog
	public static final String CATEGORY_REQUIRED = "De categorie moet ingevuld worden";
	public static final String BUDGET_REQUIRED = "Het maand budget moet ingevuld worden";
	public static final String ACCOUNT_REQUIRED = "De rekening moet ingevuld worden";
	public static final String CATEGORY_ALREADY_EXISTS = "De categorie bestaat al voor deze periode, kies een andere categorie";
	
}
