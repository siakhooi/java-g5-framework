/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.toolkit.demo.m90.HelloReportEntry;
import com.mysoftwarehouse.toolkit.demo.cmd.appinfo.ApplicationInformationViewer;
import com.mysoftwarehouse.toolkit.demo.cmd.form.FormCommandsTester;
import com.mysoftwarehouse.toolkit.demo.m80.Member;
import com.mysoftwarehouse.toolkit.demo.m80.GenerateDataProcessForm;
import com.mysoftwarehouse.toolkit.demo.m20.blank.HelloWorld;
import com.mysoftwarehouse.toolkit.demo.m30.menu.HelloMenu;
import com.mysoftwarehouse.toolkit.demo.m75.HelloEntry;
import com.mysoftwarehouse.toolkit.demo.cmd.looknfeel.LookAndFeelTester;
import com.mysoftwarehouse.toolkit.demo.m20.styledtext.HelloStyledText;
import com.mysoftwarehouse.toolkit.demo.m20.text.HelloText;
import com.mysoftwarehouse.toolkit.demo.m20.image.HelloImage;
import com.mysoftwarehouse.toolkit.demo.m20.image.HelloLogo;
import com.mysoftwarehouse.toolkit.demo.m40.selection.HelloMultiSelection;
import com.mysoftwarehouse.toolkit.demo.m40.selection.HelloSingleSelection;
import com.mysoftwarehouse.toolkit.demo.m30.tab.HelloTab;
import com.mysoftwarehouse.toolkit.demo.m75.HelloEntryWithEmbed;
import com.mysoftwarehouse.toolkit.demo.m78.HelloModeEntry;
import com.mysoftwarehouse.toolkit.demo.m70.HelloListWithArray;
import com.mysoftwarehouse.toolkit.demo.m70.HelloListWithVector;
import com.mysoftwarehouse.toolkit.demo.m70.HelloListWithVectorNoButton;
import com.mysoftwarehouse.toolkit.demo.m60.HelloProcess;
import com.mysoftwarehouse.toolkit.demo.m60.HelloProcessNoDisplay;
import com.mysoftwarehouse.toolkit.demo.m60.HelloProcessTestIndeterminate;
import com.mysoftwarehouse.toolkit.demo.m60.HelloProcessTestMinorUpdate;
import com.mysoftwarehouse.toolkit.demo.m60.HelloProcessTestProcessCancel;
import com.mysoftwarehouse.toolkit.demo.m30.systray.HelloSystemTray;
import com.mysoftwarehouse.toolkit.demo.m20.text.HelloEditText;
import com.mysoftwarehouse.toolkit.demo.m80.MemberList;
import com.mysoftwarehouse.toolkit.demo.m90.HelloReportExecution;
import com.mysoftwarehouse.toolkit.demo.m90.HelloReportExecutionWithCompiledReport;
import com.mysoftwarehouse.toolkit.demo.m90.HelloReportExecutionWithCsvText;
import com.mysoftwarehouse.toolkit.demo.m90.HelloReportExecutionWithResultSet;
import java.awt.AWTException;

/**
 *
 * @author Ng Siak Hooi
 */
public class DemoMainMenu extends MenuForm {

    @Override
    public Class<? extends MenuForm> getMenuForm() {
        return DemoMenuBar.class;
    }

    @Override
    public Class<? extends MenuForm> getMenuFormForToolbar() {
        return DemoToolBar.class;
    }

    @Override
    public int getMenuItemHeight() {
        return 20;
    }

//    @Override
//    public Class<? extends UserFormInterface> getLeftForm() {
//        return HelloStyledText.class;
//    }
    @Override
    public Class<? extends UserFormInterface> getRightForm() {
        return MenuStyledComment.class;
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    @Override
    public String getFormI18nTitle() {
        return "Demo Main Menu";
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }

    @Override
    public void eventAfterVisible() {
        cmd.frame.setLocation(
                cmd.frame.getX() - cmd.frame.getWidth() / 2,
                cmd.frame.getY());
        cmd.frame.setSize(
                cmd.frame.getWidth() * 2,
                cmd.frame.getHeight());
    }

    @Override
    public void build() {
        openI18nRole("Simple Form", "");
        /**/ openI18nCategory("Blank Form", "");
        /**//**/ addI18nApplication(HelloWorld.class, "Hello World!", "Every Software Start with Hello World! (use BlankForm)");
        /**/ closeCategory();
        /**/ openI18nCategory("Image Form", "");
        /**//**/ addI18nApplication(HelloImage.class, "Hello Image", "ImageForm2");
        /**//**/ addI18nApplication(HelloLogo.class, "Hello Logo", "ImageForm2");
        /**/ closeCategory();
        /**/ openI18nCategory("Text Form", "");
        /**//**/ addI18nApplication(HelloText.class, "Hello Text", "");
        /**//**/ addI18nApplication(HelloEditText.class, "Hello Edit Text", "");
        /**/ closeCategory();
        /**/ openI18nCategory("Styled Text Form", "");
        /**//**/ addI18nApplication(HelloStyledText.class, "Hello Styled Text Demo (HTML)", "show HTML documents");
        /**/ closeCategory();
        closeRole();

        openI18nRole("Container/Menu Form", "");
        /**/ openI18nCategory("Menu Form", "");
        /**//**/ addI18nApplication(HelloMenu.class, "Hello Menu", "MenuForm");
        /**/ closeCategory();
        /**/ openI18nCategory("SystemTray (cmd.systray)", "");
        /**//**/ addI18nApplication("systray", "System Tray Tester", "");
        /**/ closeCategory();
        /**/ openI18nCategory("Tab Forms", "Tab Forms");
        /**//**/ addI18nApplication(HelloTab.class, "Tab Form Demo", "Show Tab");
        /**/ closeCategory();
        closeRole();

        openI18nRole("Helper Forms", "");
        /**/ openI18nCategory("Selection Forms", "");
        /**//**/ addI18nApplication(HelloSingleSelection.class, "Selection Form Demo (Single)", "");
        /**//**/ addI18nApplication(HelloMultiSelection.class, "Selection Form Demo (Multi)", "");
        /**/ closeCategory();
        closeRole();

        openI18nRole("Process Form", "");
        /**/ openI18nCategory("Process Forms", "All Forms derived from Process Form");
        /**//**/ addI18nApplication(HelloProcess.class, "Hello Process Demo", "Process Form Demo");
        /**//**/ addI18nApplication(HelloProcessNoDisplay.class, "Hello Process (No Display)", "");
        /**//**/ addI18nApplication(HelloProcessTestProcessCancel.class,
                "Hello Process Demo(Test Process Cancel)",
                "Process Form Demo (Test Process Cancel)");

        /**//**/ addI18nApplication(HelloProcessTestMinorUpdate.class,
                "Hello Process Demo(Test Minor Update)",
                "Process Form Demo (Test Minor Updates)");

        /**//**/ addI18nApplication(HelloProcessTestIndeterminate.class,
                "Hello Process Demo(Test Indeterminate)",
                "Process Form Demo (Test Indeterminate)");
        /**/ closeCategory();
        closeRole();

        openI18nRole("Entry Forms", "Field Forms");
        /**/ openI18nCategory("List Forms", "List Forms Demo");
        /**//**/ addI18nApplication(HelloListWithArray.class, "List Form Demo (With Array)", "");
        /**//**/ addI18nApplication(HelloListWithVector.class, "List Form Demo (With Vector)", "");
        /**//**/ addI18nApplication(HelloListWithVectorNoButton.class, "List Form Demo (With Vector, No Button)", "");
        /**/ closeCategory();
        /**/ openI18nCategory("Entry Forms", "Entry Forms Demo");
        /**//**/ addI18nApplication(HelloEntry.class, "Entry Form", "");
        /**//**/ addI18nApplication(HelloEntryWithEmbed.class, "Entry Form (With Embedded Form)", "");
        /**/ closeCategory();
        /**/ openI18nCategory("Mode Entry Forms", "Mode Entry Forms Demo");
        /**//**/ addI18nApplication(HelloModeEntry.class, "Mode Entry Form Demo", "Show Mode Entry");
        /**/ closeCategory();
        closeRole();
        openI18nRole("Member", "");
        /**/ addI18nApplication(GenerateDataProcessForm.class, "Generate Data Process", "Generate Test Data");
        /**/ addI18nApplication(MemberList.class, "Member List", "");
        /**/ addI18nApplication(Member.class, "Member", "Member Maintenance");
        closeRole();

        /**/ openI18nCategory("Report Execution Forms", "All Forms derived from Report Execution Form");
        /**//**/ addI18nApplication(HelloReportEntry.class, "Report Entry Form Demo", "Show Report Entry");
        /**//**/ addI18nApplication(HelloReportExecution.class, "Hello Report Execution Demo", "Report Execution Form Demo");
        /**//**/ addI18nApplication(HelloReportExecutionWithCompiledReport.class, "Hello Report Execution Demo (Compiled Report)", "Report Execution Form Demo (Compiled Report)");
        /**//**/ addI18nApplication(HelloReportExecutionWithResultSet.class, "Hello Report Execution Demo (ResultSet)", "Report Execution Form Demo (ResultSet)");
        /**//**/ addI18nApplication(HelloReportExecutionWithCsvText.class, "Hello Report Execution Demo (Csv Text)", "Report Execution Form Demo (Csv Text)");
        /**/ closeCategory();

        openI18nRole("Command (cmd)", "Testing for commands.");
        /**/ openI18nCategory("Forms (cmd.form)", "");
        /**//**/ addI18nApplication(FormCommandsTester.class, "Form Commands Tester", "");
        /**/ closeCategory();
        /**/ openI18nCategory("Look And Feel (cmd.looknfeel)", "");
        /**//**/ addI18nApplication(LookAndFeelTester.class, "Look And Feel Tester", "");
        /**/ closeCategory();
        /**/ openI18nCategory("Application Info (cmd.appinfo)", "");
        /**//**/ addI18nApplication(ApplicationInformationViewer.class, "Application Information", "show Application Information");
        /**/ closeCategory();
//        /**/ addI18nApplication(BuiltInFormTestingMenu.class, "Hello BuiltIn Forms Demo", "");
//        /**/ openI18nCategory("Application", "Application Specific");
//        /**//**/ addI18nApplication(CsvWriterCommandsTester.class, "CsvWriterCommands Tester", "CsvWriterCommands Tester");
//        /**//**/ addI18nApplication(CsvReaderCommandsTester.class, "CsvReaderCommands Tester", "CsvReaderCommands Tester");
//        /**//**/ addI18nApplication(RandomCommandsTester.class, "RandomCommands Tester", "RandomCommands Tester");
//        /**//**/ addI18nApplication(CompressionCommandsTester.class, "CompressionCommands Tester", "CompressionCommands Tester");
//        /**//**/ addI18nApplication(CommonFormCommandsTestingMenu.class, "CommonFormCommands Tester", "CommonFormCommands Tester");
//        /**//**/ addI18nApplication(DigestTester.class, "Digest Tester", "show Digest Testsuite");
//        /**/ closeCategory();
//        /**/ openI18nCategory("System", "System Specific");
//        /**//**/ addI18nApplication(SystemUtilitiesTester.class, "System Utilities Tester", "System Utilities Tester");
//        /**/ openI18nCategory("Web (cmd.web)", "");
//        /**//**/ addI18nApplication(WebTester.class, "Web Tester", "");\
//        /**/ closeCategory();
//        /**/ openI18nCategory("Web Forms", "All Forms derived from Web Form");
//        /**//**/ addI18nApplication(HelloWeb.class, "Hello Web Demo", "Web Form Demo");
//        /**//**/ addI18nApplication(HelloWebTestPost.class, "Hello Web Demo (Test Post)", "Web Form (Test Post) Demo");
//        /**/ closeCategory();
//        /**/ addI18nApplication(BuiltInFormTestingMenu.class, "Hello BuiltIn Forms Demo", "");
//        closeRole();
        
        closeRole();
    }

    @Override
    public void itemSelected(String title, String description) {
        cmd.form.broadcastBlockingSignal(title.length());
    }

    @Override
    public void commandActivated(String name, String title, String description) {
        if ("systray".equals(name)) {
            try {
                cmd.systray.show(new HelloSystemTray());
                cmd.form.setLastWindowsCheck(false);
            } catch (AWTException ex) {
                cmd.log.severe("error launch systemtray", ex);
            }
        }
    }
}
