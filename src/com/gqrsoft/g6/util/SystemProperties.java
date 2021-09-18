/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util;

/**
 *
 * @author shng
 */
public class SystemProperties {

    public static String tempDirectory() {
        return System.getProperty("java.io.tmpdir");
    }

    public static String fileSeparator() {
        return System.getProperty("file.separator"); // either / or \
    }

    public static String pathSeparator() {
        return System.getProperty("path.separator"); // either : or ;
    }

    public static String lineSeparator() {
        return System.getProperty("line.separator"); // either : or ;
    }

    public static String userHome() {
        return System.getProperty("user.home");
    }

    public static String userDirectory() {
        return System.getProperty("user.dir");
    }

    public static String userName() {
        return System.getProperty("user.name");
    }

    public static String userLanguage() {
        return System.getProperty("user.language");
    }

    public static String userTimezone() {
        return System.getProperty("user.timezone");
    }

    public static String userRegion() {
        return System.getProperty("user.region");
    }

    public static String javaClassVersion() {
        return System.getProperty("java.class.version");
    }

    public static String javaClassPath() {
        return System.getProperty("java.class.path");
    }

    public static String javaLibraryPath() {
        return System.getProperty("java.library.path");
    }

    public static String javaVersion() {
        return System.getProperty("java.version");
    }

    public static String javaVendor() {
        return System.getProperty("java.vendor");
    }

    public static String javaVendorUrl() {
        return System.getProperty("java.vendor.url");
    }

    public static String javaHome() {
        return System.getProperty("java.home");
    }

    public static String javaVMSpecificationVersion() {
        return System.getProperty("java.vm.specification.version");
    }

    public static String javaVMSpecificationVendor() {
        return System.getProperty("java.vm.specification.vendor");
    }

    public static String javaVMSpecificationName() {
        return System.getProperty("java.vm.specification.name");
    }

    public static String javaVMVersion() {
        return System.getProperty("java.vm.version");
    }

    public static String javaVMVendor() {
        return System.getProperty("java.vm.vendor");
    }

    public static String javaVMName() {
        return System.getProperty("java.vm.name");
    }

    public static String javaRuntimeSpecificationVersion() {
        return System.getProperty("java.specification.version");
    }

    public static String javaRuntimeSpecificationVendor() {
        return System.getProperty("java.specification.vendor");
    }

    public static String javaRuntimeSpecificationName() {
        return System.getProperty("java.specification.name");
    }

    public static String osName() {
        return System.getProperty("os.name");
    }

    public static String osArch() {
        return System.getProperty("os.arch");
    }

    public static String osVersion() {
        return System.getProperty("os.version");
    }

    public static String javaCompiler() {
        return System.getProperty("java.compiler");
    }

    public static String javaExtDirs() {
        return System.getProperty("java.ext.dirs");
    }
}
