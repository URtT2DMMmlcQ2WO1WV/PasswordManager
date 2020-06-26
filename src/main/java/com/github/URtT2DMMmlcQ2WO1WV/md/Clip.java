package com.github.URtT2DMMmlcQ2WO1WV.md;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Clip {

    public String setClip(String str){

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        StringSelection selection = new StringSelection(str);

        clipboard.setContents(selection, null);

        return str;
    }

}
