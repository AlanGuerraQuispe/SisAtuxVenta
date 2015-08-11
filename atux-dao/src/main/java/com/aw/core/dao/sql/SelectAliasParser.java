package com.aw.core.dao.sql;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Julio C. Macavilca
 * Date: 19/01/2008
 */
public class SelectAliasParser {
    protected final Log logger = LogFactory.getLog(getClass());
    private String sql;
    private IdAlias[] cols;
    private IdAlias[] tabs;

    //String aliases[];
    public SelectAliasParser(String sql) {
        this.sql = sql;
        cols = buildAliases(sql, "SELECT", "FROM");
        tabs = buildAliases(sql, "FROM", "WHERE");
    }
    public String replaceSelect(StringBuffer selectClause) {
        String[] parts = divide(sql, "SELECT", "FROM");
        return "SELECT " +selectClause+ " FROM "+ parts[2];
    }

    private IdAlias[] buildAliases(String sql, String part1, String part2) {
        String selectClause = divide(sql, part1, part2)[1];
        String[] cols       = split(selectClause, ',', new String[]{"()"});
        List<IdAlias> listOfAlias = new ArrayList<IdAlias>();
        for (String col : cols) {
            listOfAlias.add(new IdAlias(col));
        }
        return listOfAlias.toArray(new IdAlias[listOfAlias.size()]);
    }

    private String[] split(String str, char sep, String[] groups) {
        Map groupStartToEnd = new HashMap();
        Map groupEndToStart = new HashMap();
        for (String group : groups) {
            char start = group.charAt(0);
            char end = group.charAt(1);
            groupStartToEnd.put(start, end);
            groupEndToStart.put(end, start);
        }

        List subStr = new ArrayList();
        List groupStack = new ArrayList();
        int lastSepIdx = -1;
        for (int i = 0; i < str.length(); i++) {
            Character curChar = str.charAt(i);
            if (groupStack.isEmpty() && curChar.equals(sep)){
                subStr.add(str.substring(lastSepIdx+1, i));
                lastSepIdx = i;
            }else if (groupStartToEnd.keySet().contains(curChar)) {
                //agregar grupo
                Character end = (Character) groupStartToEnd.get(curChar);
                groupStack.add(end);
            }else if (groupEndToStart.keySet().contains(curChar)) {
                //remover grupo
                if (!groupStack.isEmpty() && groupStack.get(groupStack.size()-1).equals(curChar))
                    groupStack.remove(groupStack.size()-1);
            }
        }
        if (str.length()> lastSepIdx+1)
            subStr.add(str.substring(lastSepIdx+1, str.length()-1));

        return (String[]) subStr.toArray(new String[subStr.size()]);
    }

    private String[] divide(String sql, String part1, String part2) {
        String sqlUpper = sql.toUpperCase();
        int idxStart = sqlUpper.indexOf(part1) + part1.length();
        int idxEnd = sqlUpper.indexOf(part2);
        String[] parts = new String[3];
        parts[0] = sql.substring(0, idxStart-part1.length());
        parts[1] = idxEnd!=-1? sql.substring(idxStart, idxEnd):sql.substring(idxStart);
        parts[2] = idxEnd!=-1? sql.substring(idxEnd+ part2.length()):"";
        return parts;
    }

    public String[] getAliases() {
        String[] aliases= new String[cols.length];
        for (int i = 0; i < aliases.length; i++) {
            aliases[i] = cols[i].alias;
        }
        return aliases;
    }

    public IdAlias[] getCols() {
        return cols;
    }

    public IdAlias[] getTabs() {
        return tabs;
    }


    public static class IdAlias{
        String fullExp;
        String id;
        String alias;

        public IdAlias(String fullExp) {
            this.fullExp = fullExp;
            if (fullExp.lastIndexOf(" as ") != -1){
                int idx = fullExp.lastIndexOf(" as ");
                id =fullExp.substring(0, idx).trim();
                alias = fullExp.substring(idx + " as ".length()).trim();
            }else{
                String[] components = fullExp.trim().split("[ \n\t]");
                if (components.length >0){
                    id =components[0];
                    alias = components[components.length - 1];
                }else{
                    id =fullExp;
                    alias = fullExp;
                }
            }
            //String alias = components.length > 0 ? components[components.length - 1] : col;
        }

        public String getFullExp() {
            return fullExp;
        }

        public String getId() {
            return id;
        }

        public String getAlias() {
            return alias;
        }
    }

    public static void main(String[] args) {
        String sql = "   select  corresp as domain,"+
        "           sa.fechaIniBuscaCorrespDocs as fechaInicio,"+
        "   	     sa.fechaFinBuscaCorrespDocs as fechaFin"+
        "   from CorrespDocsImpl sa"+
        "           LEFT OUTER JOIN sa.corresp corresp";

        new SelectAliasParser(sql);
    }

}
