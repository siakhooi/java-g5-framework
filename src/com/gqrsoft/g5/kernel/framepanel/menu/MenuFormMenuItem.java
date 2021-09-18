/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.menu;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractMenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.util.Vector;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class MenuFormMenuItem {

    public MenuItemType type;
    public String title = "";
    public String description = "";
    public Class<? extends UserFormInterface> userForm;
    public MenuFormMenuItem parent;
    public String commandTitle = "";
    public AbstractMenuForm menuForm;
    public boolean newThread = false;
    public Icon icon = null;
    public boolean enabled = true;

    public MenuFormMenuItem(AbstractMenuForm mf) {
        this.menuForm = mf;
    }
    public Vector<MenuFormMenuItem> children = new Vector<MenuFormMenuItem>();

    public void addChild(MenuFormMenuItem child) {
        this.children.add(child);
    }
/*
 * add Glue/Space/RigidArea
 set Mnemonic/Function Key
 * 
// void setHorizontalAlignment(int alignment) 
//  void setHorizontalTextPosition(int textPosition)  
//   void setIconTextGap(int iconTextGap) 
//   void setPressedIcon(Icon pressedIcon)  
//   void setRolloverEnabled(boolean b)  
//    void setRolloverIcon(Icon rolloverIcon) 
//     void setRolloverSelectedIcon(Icon rolloverSelectedIcon) 
//      void setVerticalAlignment(int alignment) 
// void setVerticalTextPosition(int textPosition)      
 
 */
    /**
     * user by TreeNode in Tree
     * @return
     */
    @Override
    public String toString() {
        return this.title;
    }
}
