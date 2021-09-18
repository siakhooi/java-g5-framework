/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.form;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import com.gqrsoft.g5.kernel.config.EngineConfiguration;

/**
 *
 * @author Ng Siak Hooi
 */
public class EngineCommands extends AbstractCommandComponent {

    public void showAbout() {
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.DEFAULT_ENGINE_ABOUT_FORM);
        getFormControl().cmd.form.execute(form);
    }
    public void showAboutApplication(){
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.DEFAULT_APPLICATION_ABOUT_FORM);
        getFormControl().cmd.form.execute(form);
    }
    public void showReleaseNote(){
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.DEFAULT_RELEASE_NOTE_FORM);
        getFormControl().cmd.form.execute(form);
    }
    public void showSystemProperties(){
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.DEFAULT_SYSTEM_PROPERTIES_FORM);
        getFormControl().cmd.form.execute(form);
    }
}
