/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list;

import com.gqrsoft.g5.developer.form.UserFormInterface;

/**
 *
 * @author Ng Siak Hooi
 */
public class ListModel {

    public Class<? extends UserFormInterface> AddFormInterface = null;
    public Class<? extends UserFormInterface> CopyFormInterface = null;
    public Class<? extends UserFormInterface> DeleteFormInterface = null;
    public Class<? extends UserFormInterface> EditFormInterface = null;
    public Class<? extends UserFormInterface> ViewFormInterface = null;
    public boolean add = true;
    public boolean copy = true;
    public boolean edit = true;
    public boolean delete = true;
    public boolean view = true;
    public boolean saveAllCSV = true;
    public boolean saveSelectedCSV = true;
    public boolean select = false;
    public boolean reload = true;
    public boolean close = true;
    public boolean recordCheckOnDelete = true;
    public boolean recordCheckOnCopy = true;
    public boolean recordCheckOnEdit = true;
    public boolean recordCheckOnView = true;
}
