/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.core;

/**
 *
 * @author Ng Siak Hooi
 */
public class SystemProperties {

    public String crlf() {
        return lineSeparator();
    }

    public String tempDirectory() {
        return System.getProperty("java.io.tmpdir");
    }

    public String fileSeparator() {
        return System.getProperty("file.separator"); // either / or \
    }

    public String pathSeparator() {
        return System.getProperty("path.separator"); // either : or ;
    }

    public String lineSeparator() {
        return System.getProperty("line.separator"); // either : or ;
    }

    public String userHome() {
        return System.getProperty("user.home");
    }

    public String userDirectory() {
        return System.getProperty("user.dir");
    }

    public String userName() {
        return System.getProperty("user.name");
    }

    public String userLanguage() {
        return System.getProperty("user.language");
    }

    public String userTimezone() {
        return System.getProperty("user.timezone");
    }

    public String userRegion() {
        return System.getProperty("user.region");
    }

    public String javaClassVersion() {
        return System.getProperty("java.class.version");
    }

    public String javaClassPath() {
        return System.getProperty("java.class.path");
    }

    public String javaLibraryPath() {
        return System.getProperty("java.library.path");
    }

    public String javaVersion() {
        return System.getProperty("java.version");
    }

    public String javaVendor() {
        return System.getProperty("java.vendor");
    }

    public String javaVendorUrl() {
        return System.getProperty("java.vendor.url");
    }

    public String javaHome() {
        return System.getProperty("java.home");
    }

    public String javaVMSpecificationVersion() {
        return System.getProperty("java.vm.specification.version");
    }

    public String javaVMSpecificationVendor() {
        return System.getProperty("java.vm.specification.vendor");
    }

    public String javaVMSpecificationName() {
        return System.getProperty("java.vm.specification.name");
    }

    public String javaVMVersion() {
        return System.getProperty("java.vm.version");
    }

    public String javaVMVendor() {
        return System.getProperty("java.vm.vendor");
    }

    public String javaVMName() {
        return System.getProperty("java.vm.name");
    }

    public String javaRuntimeSpecificationVersion() {
        return System.getProperty("java.specification.version");
    }

    public String javaRuntimeSpecificationVendor() {
        return System.getProperty("java.specification.vendor");
    }

    public String javaRuntimeSpecificationName() {
        return System.getProperty("java.specification.name");
    }

    public String osName() {
        return System.getProperty("os.name");
    }

    public String osArch() {
        return System.getProperty("os.arch");
    }

    public String osVersion() {
        return System.getProperty("os.version");
    }

    public String javaCompiler() {
        return System.getProperty("java.compiler");
    }

    public String javaExtDirs() {
        return System.getProperty("java.ext.dirs");
    }
}
