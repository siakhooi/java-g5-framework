/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.config;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicform.ConfirmationDialogForm;
import com.gqrsoft.g5.developer.publicform.MessageDialogForm;
import com.gqrsoft.g5.developer.publicform.SaveTableModelToCSVFileForm;
import com.gqrsoft.g5.developer.publicform.SelectCalendarForm;
import com.gqrsoft.g5.developer.publicform.SelectColorForm;
import com.gqrsoft.g5.developer.publicform.SelectDirectoryForm;
import com.gqrsoft.g5.developer.publicform.SelectOpenExtensionFileForm;
import com.gqrsoft.g5.developer.publicform.SelectOpenFileForm;
import com.gqrsoft.g5.developer.publicform.SelectOpenImageFileForm;
import com.gqrsoft.g5.developer.publicform.SelectSaveExtensionFileForm;
import com.gqrsoft.g5.developer.publicform.SelectSaveFileForm;
import com.gqrsoft.g5.developer.publicform.ViewHtmlForm;
import com.gqrsoft.g5.developer.publicform.ViewImageForm;
import com.gqrsoft.g5.developer.publicobject.LanguageEnum;
import com.gqrsoft.g5.developer.info.AboutApplicationForm;
import com.gqrsoft.g5.developer.info.AboutEngineForm;
import com.gqrsoft.g5.developer.info.AboutSystemPropertiesForm;
import com.gqrsoft.g5.developer.info.ReleaseNoteMenuForm;

/**
 *
 * @author Ng Siak Hooi
 */
public class EngineConfiguration {

    public static String ENGINE_TITLE = "G5 Engine version 1.0.2.0";

    //public static int DEFAULT_BUTTON_IMAGE_HEIGHT = 24;
    //
    public static String YES = "Y";
    public static String NO = "N";
    public static String ISO8601_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static Class<? extends UserFormInterface> DEFAULT_ENGINE_ABOUT_FORM = AboutEngineForm.class;
    public static Class<? extends UserFormInterface> DEFAULT_APPLICATION_ABOUT_FORM = AboutApplicationForm.class;
    public static Class<? extends UserFormInterface> DEFAULT_RELEASE_NOTE_FORM = ReleaseNoteMenuForm.class;
    public static Class<? extends UserFormInterface> DEFAULT_SYSTEM_PROPERTIES_FORM = AboutSystemPropertiesForm.class;

    public static class Log {

        public static String G5SYSTEM_LOGGER_NAME = "g5systemlogger";

//        public static String DEFAULT_LOGGING_DATETIME_FORMAT =
//                "yyyyMMddHHmmss";
    }

//    public static class DefaultLanguage {
//
//        public static String LanguageCode = "default";
//        public static String LanguageName = "LanguageManager.language.default";
//        public static LanguageEnum.SystemLanguage SysLanguage =
//                LanguageEnum.SystemLanguage.EN_US;
//        public static String language="en";
//        public static String countryCode="US";
//        public static String variant="";
//    }
    public static class Language {

        public static String SYSTEM_LANGUAGE_BASE_NAME = "com.gqrsoft.g5.resources.languages.g5";
//        public static LanguageEnum.SystemLanguage DEFAULT_SYSTEM_LANGUAGE =
//                LanguageEnum.SystemLanguage.EN_US;
        public static LanguageEnum.BuiltInApplicationLanguage DEFAULT_LANGUAGE =
                LanguageEnum.BuiltInApplicationLanguage.EN_US;
        public static String[] allSystemBaseNames = {
            "com.gqrsoft.g5.resources.languages.g5P",
            "com.gqrsoft.g5.resources.languages.g5L",
            "com.gqrsoft.g5.resources.languages.g5E",
            "com.gqrsoft.g5.resources.languages.g5R",
            "com.gqrsoft.g5.resources.languages.g5A",
            "com.gqrsoft.g5.resources.languages.g5B"
        };
    }

    public static class Form {

        public static int FORM_UNIQUE_SESSION_ID_LENGTH = 10;
        public static Class<? extends UserFormInterface> DEFAULT_CONFIRMATION_DIALOG_FORM = ConfirmationDialogForm.class;
        public static Class<? extends UserFormInterface> DEFAULT_MESSAGE_DIALOG_FORM = MessageDialogForm.class;
    }

    public static class List {

        public static String DEFAULT_TEXT_REPRESENT_PASSWORD = "***";
        public static String DEFAULT_CALENDAR_OUTPUT_FORMAT =
                "dd MMM, yyyy HH:mm:ss";
        public static int DEFAULT_ICON_HEIGHT = 32;
    }

    public static class Entry {

        public static int DEFAULT_CONTEXT_MENU_IMAGE_HEIGHT = 16;
        public static int DEFAULT_SELECT_BUTTON_IMAGE_HEIGHT = 10;
        public static int DEFAULT_ICON_HEIGHT = 32;
        public static int DEFAULT_TEXT_COLUMNS = 5;
        public static String DEFAULT_TEXT_REPRESENT_PASSWORD = "***";
        public static String DEFAULT_CALENDAR_INPUT_FORMAT = "yyyyMMddHHmmss";
        public static Class<? extends UserFormInterface> DEFAULT_SELECT_CALENDAR_FORM = SelectCalendarForm.class;
        public static Class<? extends UserFormInterface> DEFAULT_SELECT_COLOR_FORM = SelectColorForm.class;
        public static Class<? extends UserFormInterface> DEFAULT_VIEW_HELP_FORM = ViewHtmlForm.class;
        public static Class<? extends UserFormInterface> DEFAULT_VIEW_IMAGE_FORM = ViewImageForm.class;
        public static Class<? extends UserFormInterface> DEFAULT_OPEN_IMAGE_FILE_FORM = SelectOpenImageFileForm.class;
        public static Class<? extends UserFormInterface> DEFAULT_VIEW_HTML_FORM = ViewHtmlForm.class;
        public static Class<? extends UserFormInterface> DEFAULT_SELECT_DIRECTORY_FORM = SelectDirectoryForm.class;
        public static Class<? extends UserFormInterface> DEFAULT_SAVE_FILE_FORM = SelectSaveFileForm.class;
        public static Class<? extends UserFormInterface> DEFAULT_SAVE_FILE_WITH_EXT_FORM = SelectSaveExtensionFileForm.class;
        public static Class<? extends UserFormInterface> DEFAULT_OPEN_FILE_FORM = SelectOpenFileForm.class;
        public static Class<? extends UserFormInterface> DEFAULT_OPEN_FILE_WITH_EXT_FORM = SelectOpenExtensionFileForm.class;
        public static Class<? extends UserFormInterface> DEFAULT_SAVE_TABLE_MODEL_TO_CSV_FORM = SaveTableModelToCSVFileForm.class;
    }

    public static class Process {

        public static int TOTAL_MINOR_COUNT_PER_PROCESS = 10000;
        public static int DEFAULT_ICON_HEIGHT = 48;
        public static String LOG_DATETIME_FORMAT = ISO8601_DATETIME_FORMAT;
        //"yyyy-MM-dd HH:mm:ss";
    }
}
