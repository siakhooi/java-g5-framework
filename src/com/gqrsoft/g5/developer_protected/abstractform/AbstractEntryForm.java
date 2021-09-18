/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.TabType;
import com.gqrsoft.g5.developer_protected.tools.ButtonsBuilder;
import com.gqrsoft.g5.developer.publicobject.FormEnum.ButtonPageLocation;
import com.gqrsoft.g5.developer_secret.abstractform.PrivateAbstractEntryForm;
import com.gqrsoft.g5.kernel.framepanel.entry.entryForm.EntryFieldGroup;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractEntryForm extends PrivateAbstractEntryForm {

    public final ButtonsBuilder buttons = new ButtonsBuilder(this);

    @Override
    protected final void addNewField(Field field) {
        allFieldsByIndex.add(field);
        allFieldsByName.put(field.fieldName, field);
        currentEntryFieldGroup.allFieldsByIndex.add(field);
        currentEntryFieldGroup.allFieldsByName.put(field.fieldName, field);
    }

    protected abstract void buildFieldList();

    protected abstract void buildButtonsList();

    public abstract void initValue();

    public boolean showStatusBar() {
        return true;
    }

    @Override
    public final void initForm() {
        currentEntryFieldGroup = keyEntryFieldGroup;
        buildFieldList();
        buttons.clearAll();
        buttons.verticalLocation = ButtonPageLocation.TOP;
        buildButtonsList();
    }

    public final void addTab(String tab, String description) {
        description = cmd.lang.getString(description);
        addI18nTab(tab, description);
    }

    public final void addI18nTab(String tab, String i18nDescription) {
        addI18nTab(tab, i18nDescription, 1);
    }

    public final void addTab(String tab, String description, int columns) {
        description = cmd.lang.getString(description);
        addI18nTab(tab, description, columns);
    }

    public final void addI18nTab(String tab, String i18nDescription, int columns) {
        if (allTab.containsKey(tab)) {
            currentEntryFieldGroup = allTab.get(tab);
        } else {
            EntryFieldGroup fg = new EntryFieldGroup();
            fg.tabName = tab;
            fg.tabI18nDescription = i18nDescription;
            fg.type = TabType.FIELD;
            fg.columns = columns;
            allTab.put(tab, fg);
            currentEntryFieldGroup = fg;
        }
    }

    public final void addTab(String tab, String description,
            Class<? extends UserFormInterface> form) {
        description = cmd.lang.getString(description);
        addI18nTab(tab, description, form);
    }

    public final void addI18nTab(String tab, String i18nDescription,
            Class<? extends UserFormInterface> form) {
        if (allTab.containsKey(tab)) {
            return;
        }
        EntryFieldGroup fg = new EntryFieldGroup();
        fg.form = form;
        fg.tabName = tab;
        fg.tabI18nDescription = i18nDescription;
        fg.type = TabType.FORM;
        allTab.put(tab, fg);
    }
}
