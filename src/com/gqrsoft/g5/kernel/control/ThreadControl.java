/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.control;

import com.gqrsoft.g5.kernel.frame.FrameInterface;
import com.gqrsoft.g5.developer_protected.parameters.GlobalParameters;
import java.sql.Connection;

/**
 *
 * @author Ng Siak Hooi
 */
public class ThreadControl {

    public Connection connection;
    public FrameInterface root;
    public GlobalParameters param;
}
