/*
 * @(#)SwingApplet.java	1.14 01/12/03
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.applet.*;
import javax.swing.*;

/*
 * A very simple applet.
 */
public class LookAndFeelDemo extends JApplet {

    JButton button;

    public void init() {
        // 更换注释的内容可以改变界面风格
	//String laf ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	//String laf ="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	String laf ="javax.swing.plaf.metal";
	try {
	    UIManager.setLookAndFeel(laf);
	} catch (UnsupportedLookAndFeelException exc) {
	    System.err.println("Warning: UnsupportedLookAndFeel: " + laf);
	} catch (Exception exc) {
	    System.err.println("Error loading " + laf + ": " + exc);
	}

        getContentPane().setLayout(new FlowLayout());
        button = new JButton("<html><body>Hello, <br>I'm a Swing Button!<br>"+
                              "You can see different styles!</body></html>");
        getContentPane().add(button);
    }

    public void stop() {
        if (button != null) {
            getContentPane().remove(button);
            button = null;
        }
    }
}


