/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager.frame;

import com.gqrsoft.g5.kernel.frame.FrameInterface;
import com.gqrsoft.g5.kernel.frame.G5Dialog;
import com.gqrsoft.g5.kernel.frame.G5Frame;

/**
 *
 * @author Ng Siak Hooi
 */
public class FrameSelector {

    public FrameInterface getFrame(boolean hasParent, boolean newThread) {
        if (hasParent && !newThread) {
            return new G5Dialog();
        } else {
            return new G5Frame();
        }
    }
}
