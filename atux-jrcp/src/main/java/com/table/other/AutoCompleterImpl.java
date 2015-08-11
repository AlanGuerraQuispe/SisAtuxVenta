package com.table.other;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: GM
 * Date: 14/05/2009
 * Time: 07:48:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class AutoCompleterImpl extends AutoCompleter{
    public AutoCompleterImpl(JTextComponent comp){
        super(comp);
    }

    protected boolean updateListData(){ 
        String value = textComp.getText();
        String[] files = new String[]{"abbb","abc","bba","bbb"};
        List newFiles = new ArrayList();
        for (String file : files) {
            if (file.startsWith(value)){
                newFiles.add(file);
            }
        }
        files = (String[]) newFiles.toArray(new String[newFiles.size()]);
        if(files == null){
            list.setListData(new String[0]);
            return true;
        } else{
//            if(files.length==1 && files[0].equalsIgnoreCase(prefix))
            if(files.length==1 )
                list.setListData(new String[0]);
            else
                list.setListData(files);
            return true;
        }
    }

    protected void acceptedListItem(String selected){
        if(selected==null)
            return;

        String value = textComp.getText();
//        int index1 = value.lastIndexOf('\\');
//        int index2 = value.lastIndexOf('/');
//        int index = Math.max(index1, index2);
        int index = 0;
        if(index==-1)
            return;
        int prefixlen = textComp.getDocument().getLength()-index-1;
        try{
            textComp.getDocument().insertString(textComp.getCaretPosition(), selected.substring(prefixlen), null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
