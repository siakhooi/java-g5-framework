/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands;

import com.gqrsoft.g5.developer_protected.commands.core.ApplicationInformationCommands;
import com.gqrsoft.g5.developer_protected.commands.core.CommandLineArgumentsCommands;
import com.gqrsoft.g5.developer_protected.commands.core.ConfigurationCommands;
import com.gqrsoft.g5.developer_protected.commands.core.DatabaseAdministrationCommands;
import com.gqrsoft.g5.developer_protected.commands.core.DatabaseCommands;
import com.gqrsoft.g5.developer_protected.commands.core.IconResourcesCommands;
import com.gqrsoft.g5.developer_protected.commands.core.LanguageCommands;
import com.gqrsoft.g5.developer_protected.commands.core.LogManagerCommands;
import com.gqrsoft.g5.developer_protected.commands.core.SchedulerCommands;
import com.gqrsoft.g5.developer_protected.commands.core.SystemProperties;
import com.gqrsoft.g5.developer_protected.commands.form.ButtonCommands;
import com.gqrsoft.g5.developer_protected.commands.form.CommonFormCommands;
import com.gqrsoft.g5.developer_protected.commands.form.EngineCommands;
import com.gqrsoft.g5.developer_protected.commands.form.EntryFormCommands;
import com.gqrsoft.g5.developer_protected.commands.form.FormCommands;
import com.gqrsoft.g5.developer_protected.commands.form.FrameCommands;
import com.gqrsoft.g5.developer_protected.commands.form.ListFormCommands;
import com.gqrsoft.g5.developer_protected.commands.form.ModeEntryFormCommands;
import com.gqrsoft.g5.developer_protected.commands.form.ParentCommands;
import com.gqrsoft.g5.developer_protected.commands.form.ProcessFormCommands;
import com.gqrsoft.g5.developer_protected.commands.form.ReportEntryFormCommands;
import com.gqrsoft.g5.developer_protected.commands.form.SignalCommands;
import com.gqrsoft.g5.developer_protected.commands.form.SysTrayCommands;
import com.gqrsoft.g5.developer_protected.commands.util.CalendarCommands;
import com.gqrsoft.g5.developer_protected.commands.util.CodecCommands;
import com.gqrsoft.g5.developer_protected.commands.util.ZipCommands;
import com.gqrsoft.g5.developer_protected.commands.util.DataCommands;
import com.gqrsoft.g5.developer_protected.commands.util.DebugCommands;
import com.gqrsoft.g5.developer_protected.commands.util.DigestCommands;
import com.gqrsoft.g5.developer_protected.commands.util.GZipCommands;
import com.gqrsoft.g5.developer_protected.commands.util.ImageCommands;
import com.gqrsoft.g5.developer_protected.commands.util.JnlpServiceCommands;
import com.gqrsoft.g5.developer_protected.commands.util.LookAndFeelCommands;
import com.gqrsoft.g5.developer_protected.commands.util.RandomCommands;
import com.gqrsoft.g5.developer_protected.commands.util.StringPrintWriter;
import com.gqrsoft.g5.developer_protected.commands.util.SystemUtilities;
import com.gqrsoft.g5.developer_protected.commands.util.UnzipCommands;
import com.gqrsoft.g5.developer_protected.commands.util.UrlCodecCommands;
import com.gqrsoft.g5.developer_protected.commands.util.CsvReaderCommands;
import com.gqrsoft.g5.developer_protected.commands.util.CsvWriterCommands;
import com.gqrsoft.g5.developer_protected.commands.util.StringCommands;
import com.gqrsoft.g5.developer_protected.commands.util.StringLineReader;
import com.gqrsoft.g5.developer_protected.commands.util.WebCommands;
import com.gqrsoft.g5.developer_protected.commands.util2.RockeyCommands;
import com.gqrsoft.g5.developer_protected.parameters.FormParameters;
import com.gqrsoft.g5.developer_protected.parameters.GlobalParameters;

/**
 *
 * @author Ng Siak Hooi
 */
public class Commands { //extends AbstractCommandComponent {
// core
    public final ApplicationInformationCommands appinfo = new ApplicationInformationCommands();
    public final LogManagerCommands log = new LogManagerCommands();
    public final SystemProperties sysprop = new SystemProperties();
    public final ConfigurationCommands config = new ConfigurationCommands();
    public final CommandLineArgumentsCommands argv = new CommandLineArgumentsCommands();
    public final SchedulerCommands cron = new SchedulerCommands();
    public final LanguageCommands lang = new LanguageCommands();
    public final DatabaseAdministrationCommands dba = new DatabaseAdministrationCommands();
    public final IconResourcesCommands icon = new IconResourcesCommands();
// form    
    public final ParentCommands parent = new ParentCommands();
    public final FrameCommands frame = new FrameCommands();
    public final FormCommands form = new FormCommands();
    public final ProcessFormCommands process = new ProcessFormCommands();
    public final CommonFormCommands common = new CommonFormCommands();
    public final ListFormCommands list = new ListFormCommands();
    public final EntryFormCommands entry = new EntryFormCommands();
    public final ReportEntryFormCommands report = new ReportEntryFormCommands();
    public final ModeEntryFormCommands mode = new ModeEntryFormCommands();
    public final ButtonCommands button = new ButtonCommands();
    public final SysTrayCommands systray = new SysTrayCommands();
    public final SignalCommands signal = new SignalCommands();
//util    
    public final DataCommands data = new DataCommands();
    public final CalendarCommands cal = new CalendarCommands();
    public final StringCommands str=new StringCommands();
    public final ImageCommands image = new ImageCommands();
    public final SystemUtilities sys = new SystemUtilities();
    public final RandomCommands random = new RandomCommands();
    public final LookAndFeelCommands looknfeel = new LookAndFeelCommands();
    public final CodecCommands codec = new CodecCommands();
    public final DigestCommands digest = new DigestCommands();
    public final ZipCommands zip = new ZipCommands();
    public final UnzipCommands unzip = new UnzipCommands();
    public final GZipCommands gzip = new GZipCommands();
    public final UrlCodecCommands url = new UrlCodecCommands();
    public final CsvReaderCommands csvreader = new CsvReaderCommands();
    public final CsvWriterCommands csvwriter = new CsvWriterCommands();
    public final StringPrintWriter linewrite = new StringPrintWriter();
    public final StringLineReader lineread = new StringLineReader();
    public final WebCommands web = new WebCommands();
    public final JnlpServiceCommands jnlp = new JnlpServiceCommands();
    public final DebugCommands debug = new DebugCommands();
    public final DatabaseCommands db = new DatabaseCommands();
    public final EngineCommands engine = new EngineCommands();
    public final RockeyCommands rockey = new RockeyCommands();
//parameters    
    public FormParameters in;
    public FormParameters out;
    public GlobalParameters global;
    public GlobalParameters thread;
//    public GlobalParameters local;
    public FormParameters local;
}
