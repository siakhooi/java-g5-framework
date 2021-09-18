/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicobject;

/**
 *
 * @author Ng Siak Hooi
 */
public class LanguageEnum {

    public enum SystemLanguage {

        EN_US("en", "US", ""),
        ZH_CN("zh", "CN", ""),
        ZH_TW("zh", "TW", ""),
        MS_MY("ms", "MY", "");
        //EN_US("en_US", "lang.name.en_US", "en", "US", "");
        //EN_GB("en_GB", "lang.name.en_GB", "en", "GB", "");
        //ZH_CN("zh_CN", "lang.name.zh_CN", "zh", "CN", "");
        //ZH_TW("zh_TW", "lang.name.zh_TW", "zh", "TW", "");
        //MS_MY("ms_MY", "lang.name.ms_MY", "ms", "MY", "");
        //
        //public String languageCode;
        //public String name;
        public String language;
        public String countryCode;
        public String variant;

        SystemLanguage(String language, String countryCode, String variant) {
            this.language = language;
            this.countryCode = countryCode;
            this.variant = variant;
        }
    }

    public enum BuiltInApplicationLanguage {

//        DEFAULT("default", "LanguageManager.language.default",
//        LanguageEnum.SystemLanguage.EN_US,
//        "en", "US", ""),
        EN_US("en_US", "lang.name.en.us",
        LanguageEnum.SystemLanguage.EN_US,
        "en", "US", ""),
        ZH_CN("zh_CN", "lang.name.zh.cn",
        LanguageEnum.SystemLanguage.ZH_CN,
        "zh", "CN", ""),
        ZH_TW("zh_TW", "lang.name.zh.tw",
        LanguageEnum.SystemLanguage.ZH_TW,
        "zh", "TW", ""),
        MS_MY("ms_MY", "lang.name.ms.my",
        LanguageEnum.SystemLanguage.MS_MY,
        "ms", "MY", "");
        //
        public String code;
        public String name; //to be translated
        public LanguageEnum.SystemLanguage syslang;
        public String lang;
        public String country;
        public String variant;

        BuiltInApplicationLanguage(String code, String name,
                LanguageEnum.SystemLanguage syslang,
                String lang, String country, String variant) {
            this.code = code;
            this.name = name;
            this.syslang = syslang;
            this.lang = lang;
            this.country = country;
            this.variant = variant;
        }
    }
}
