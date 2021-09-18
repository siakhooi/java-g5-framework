/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager.lang;

/**
 *
 * @author Ng Siak Hooi
 */
public class LanguageTextFinderResult {

    public boolean found = false;
    public String originalText;
    public String translatedText;
    public String systemBaseName;
    public String applicationBaseName;
    public String formBaseName;
    public String customBaseName;
    public ApplicationLanguage currentLanguage;
    public ApplicationLanguage defaultLanguage;
}
