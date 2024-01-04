package nl.budget.service;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
import nl.budget.view.ViewConstant;
import nl.budget.view.ViewMessage;

@Slf4j
public class ConfigService {

	private static String rootFolder;
	private static Path configPath;
	private static Properties appConfig;

	static {
		rootFolder = Path.of("").toAbsolutePath().toString();
		configPath = Path.of(rootFolder, ViewConstant.CONFIG_FILE);
		appConfig = new Properties(createDefaultProperties());
		readConfigProperties();	
	}

	public static String getConfigProperty(String key) {
		return appConfig.getProperty(key);
	}

	public static void setConfigProperty(String key, String value) {
		appConfig.setProperty(key, value);
		writeConfigProperties();
	}
	
	private static Properties createDefaultProperties() {
		Path defaultConfigPath = Path.of(rootFolder, ViewConstant.DEFAULT_CONFIG_FILE);
		Properties defaultConfig = new Properties();
		defaultConfig.setProperty(ViewConstant.DOWNLOAD_FOLDER_PROP, rootFolder);
		defaultConfig.setProperty(ViewConstant.BACKUP_FOLDER_PROP, rootFolder);
		defaultConfig.setProperty(ViewConstant.BACKUPS_TO_KEEP_PROP, "5");
		try {
			defaultConfig.store(new FileWriter(defaultConfigPath.toString()), null);
		} catch (IOException e) {
			log.error(ViewMessage.CANNOT_SAVE_DEFAULT_CONFIGURATION, e.getMessage());
		}
		return defaultConfig;
	}

	private static void readConfigProperties() {
		try {
			appConfig.load(new FileInputStream(configPath.toString()));
		} catch (IOException e) {
			log.info(ViewMessage.NO_CONFIGURATION_FOUND, e.getMessage());
		}
	}

	private static void writeConfigProperties() {
		try {
			appConfig.store(new FileWriter(configPath.toString()), null);
		} catch (IOException e) {
			log.error(ViewMessage.CANNOT_SAVE_CONFIGURATION, e.getMessage());
		}
	}
}
