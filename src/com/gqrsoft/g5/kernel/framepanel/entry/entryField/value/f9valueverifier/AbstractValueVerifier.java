/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractValueVerifier {

    protected FormControl formControl;
    protected AbstractEntryField entryField;

    public void init(FormControl fc, AbstractEntryField entryField) {
        this.formControl = fc;
        this.entryField = entryField;
    }

    public abstract void verify();

//    public MaskResult match(String inputMask, String outputMask, String value);
//    public MaskResult reverseMatch(String inputMask, String outputMask, UserValueInterface value);
//    public boolean isMatch=false;
//    public String formattedText="";
//    public String editText="";
//    public String errorMessage="";
//    
//    public UserValueInterface value;
}
