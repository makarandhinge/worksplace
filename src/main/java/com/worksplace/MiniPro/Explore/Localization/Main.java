package com.worksplace.MiniPro.Explore.Localization;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	public static void main(String[] args) {
		
        // 1️⃣ Set locale (try ENGLISH or GERMAN)
//        Locale locale = Locale.GERMAN;
         Locale locale = Locale.ENGLISH;

        // 2️⃣ Load resource bundle
        ResourceBundle bundle =
                ResourceBundle.getBundle("Localization.logmessages", locale);

        // 3️⃣ Create logger
        Logger logger = Logger.getLogger("MyLogger");

        // 4️⃣ Log localized messages
        logger.logrb(Level.INFO, bundle, "readingFile", "data.txt");

        logger.logrb(Level.INFO, bundle,
                "renamingFile", "old.txt", "new.txt");	

	}

}
