/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.D;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.StyledTextType;

/**
 *
 * @author Ng Siak Hooi
 */
public class UTDH0 extends StyledTextForm {

    @Override
    public String getInitialText() {
        return                 "A tool that traverse all subdirectories of selected Directory, <BR>"+
                "renamed the subdirectories with files but with accestor directories which is empty <BR> " +
                "to single level directory. Example, there are directory structure <BR>" +
                "of d1/d2/d3/d4/d5/, where directory d1/d2, d1/d2/d3/d4 are empty, <BR>" +
                "(d1, d1/d2/d3, and d1/d2/d3/d4/d5 not empty). The tool will rename the <BR>" +
                "d1/d2/d3/d4/d5 to d1/d2.d3/d4.d5, d1/d2/d3 to d1/d2.d3 <BR>" +
                "ie: left only d1, d1/d2.d3, and d1/d2.d3/d4.d5" ;
    }

    @Override
    public void hyperlinkClick(String arg0) {
    }

    @Override
    public StyledTextType getType() {
        return StyledTextType.HTML;
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    public void onEscapeKeyPressed() {
    }
}
