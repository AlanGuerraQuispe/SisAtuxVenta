package atux.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JComboBox;
import atux.managerbd.BaseConexion;
import atux.util.common.AtuxDBUtility;

public class AtuxLoadCVL{

  /** Almacena todos los objetos y datos asociados a un ComboBox */
  private static Hashtable tableList = new Hashtable();

  /**
  *Constructor
  */
  public AtuxLoadCVL() {
  }

  /**
  *
  */
  public static void loadCVL(JComboBox combo,
                             String tableName,
                             String fieldCode,
                             String fieldValue,
                             boolean isMandatory,
                             String whereCondition) {
    loadCVL(combo,tableName,fieldCode,fieldValue,isMandatory,whereCondition,null,null);
  }

  public static void loadCVL(JComboBox combo,
                             String tableName,
                             String fieldCode,
                             String fieldValue,
                             boolean isMandatory,
                             String whereCondition,
                             String nameInHashTable)
  {
    loadCVL(combo,tableName,fieldCode,fieldValue,isMandatory,whereCondition,null,nameInHashTable);
  }

  /*
  public static void loadCVL(JComboBox combo,
                             String tableName,
                             String fieldCode,
                             String fieldValue,
                             boolean isMandatory,
                             String whereCondition,
                             String orderByFields,
                             String nameInHashTable) {
    if(nameInHashTable == null) {
      if(tableList.containsKey(tableName))  tableList.remove(tableName);
      nameInHashTable = tableName;
      loadNewCVL(combo,tableName,fieldCode,fieldValue,isMandatory,whereCondition, orderByFields, nameInHashTable);
    } else {
      if (!tableList.containsKey(nameInHashTable))
        loadNewCVL(combo,tableName,fieldCode,fieldValue,isMandatory,whereCondition, orderByFields, nameInHashTable);
    }
    ArrayList list = (ArrayList)tableList.get(nameInHashTable);
    for (int i = 0 ; i< list.size(); i++) {
      ArrayList data = (ArrayList)list.get(i);
      combo.addItem(data.get(1));
    }
  }
  */

  public static void loadCVL(JComboBox combo,
                             String tableName,
                             String fieldCode,
                             String fieldValue,
                             boolean isMandatory,
                             String whereCondition,
                             String orderByFields,
                             String nameInHashTable)
  {
    loadCVL(combo,tableName,fieldCode,fieldValue,isMandatory,whereCondition,orderByFields,nameInHashTable, false);
  }

  public static void loadCVL(JComboBox combo,
                             String tableName,
                             String fieldCode,
                             String fieldValue,
                             boolean isMandatory,
                             String whereCondition,
                             String orderByFields,
                             String nameInHashTable, boolean rewriteInHashTable) {
    if(nameInHashTable == null) {
      if(tableList.containsKey(tableName))  tableList.remove(tableName);
      nameInHashTable = tableName;
      loadNewCVL(combo,tableName,fieldCode,fieldValue,isMandatory,whereCondition, orderByFields, nameInHashTable);
    }
    else {
       //if(tableList.containsKey(tableName))  tableList.remove(tableName);
       if (!tableList.containsKey(nameInHashTable) || rewriteInHashTable)
          loadNewCVL(combo,tableName,fieldCode,fieldValue,isMandatory,whereCondition, orderByFields, nameInHashTable);
    }
    ArrayList list = (ArrayList)tableList.get(nameInHashTable);
    for (int i = 0 ; i< list.size(); i++) {
      ArrayList data = (ArrayList)list.get(i);
      combo.addItem(data.get(1));
    }
  }

  public static void loadNewCVL(JComboBox combo,
                                String tableName,
                                String fieldCode,
                                String fieldValue,
                                boolean isMandatory,
                                String whereCondition,
                                String orderByFields,
                                String nameInHashTable) {
    ArrayList  arrayCVL = new ArrayList();
    String query = "";
    if (whereCondition == null) query = "SELECT " + fieldCode+ ", " + fieldValue + " FROM " + tableName;
    else query = "SELECT " + fieldCode+ ", " + fieldValue + " FROM " + tableName + " WHERE " + whereCondition;

    if (orderByFields == null) {
      query += " ORDER BY " + fieldValue;
    }
    else{
      query += " ORDER BY " + orderByFields;
    }

    //System.out.println("JREBATTA " + query);

    try {
      Statement stmt = ((Connection) BaseConexion.getConexion()).createStatement();
      ResultSet results = stmt.executeQuery(query);
      ArrayList myArray;
      if (!isMandatory) {
        myArray = new ArrayList();
        myArray.add("");
        myArray.add("");
        arrayCVL.add(myArray);
      }
      while (results.next()) {
        myArray = new ArrayList();
        myArray.add(results.getString(1));
        myArray.add(results.getString(2));
        arrayCVL.add(myArray);
      }
      results.close();
      stmt.close();
      tableList.put(nameInHashTable, arrayCVL);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   *
   */
  public static String getCVLCode(String tableName, int index) {
      String code = new String("");
      if (tableList.containsKey(tableName)){
         ArrayList list = (ArrayList)tableList.get(tableName);
         ArrayList data = (ArrayList)list.get(index);
         code = (String)data.get(0);
      }
      return code;
  }

  public static void loadCVLfromArrays(JComboBox combo,
                                       String nameInHashTable,
                                       String[] fieldCode,
                                       String[] fieldValue,
                                       boolean isMandatory) {

      if(!tableList.containsKey(nameInHashTable)) {
         ArrayList  arrayCVL = new ArrayList();
         ArrayList myArray;
         if (!isMandatory) {
           myArray = new ArrayList();
           myArray.add("");
           myArray.add("");
           arrayCVL.add(myArray);
         }
         for (int i=0;i<fieldCode.length;i++) {
           myArray = new ArrayList();
           myArray.add(fieldCode[i]);
           myArray.add(fieldValue[i]);
           arrayCVL.add(myArray);
         }
         tableList.put(nameInHashTable, arrayCVL);
      }

      ArrayList list = (ArrayList)tableList.get(nameInHashTable);
      for (int i = 0 ; i< list.size(); i++) {
         ArrayList data = (ArrayList)list.get(i);
         combo.addItem(data.get(1));
      }
  }
  public static void loadCVLfromArraysCustom(JComboBox combo,
                                       String nameInHashTable,
                                       String[] fieldCode,
                                       String[] fieldValue,
                                       boolean isMandatory,boolean rewrite) {

      if (!tableList.containsKey(nameInHashTable) || rewrite)
         loadNewCVLfromArrays(combo,nameInHashTable,fieldCode,fieldValue,isMandatory);

      if(!tableList.containsKey(nameInHashTable)) {
         ArrayList  arrayCVL = new ArrayList();
         ArrayList myArray;
         if (!isMandatory) {
           myArray = new ArrayList();
           myArray.add("");
           myArray.add("");
           arrayCVL.add(myArray);
         }
         for (int i=0;i<fieldCode.length;i++) {
           myArray = new ArrayList();
           myArray.add(fieldCode[i]);
           myArray.add(fieldValue[i]);
           arrayCVL.add(myArray);
         }
         tableList.put(nameInHashTable, arrayCVL);
      }

      ArrayList list = (ArrayList)tableList.get(nameInHashTable);
      for (int i = 0 ; i< list.size(); i++) {
         ArrayList data = (ArrayList)list.get(i);
         combo.addItem(data.get(1));
      }
  }
  public static void loadNewCVLfromArrays(JComboBox combo,
                                       String nameInHashTable,
                                       String[] fieldCode,
                                       String[] fieldValue,
                                       boolean isMandatory) {

         ArrayList  arrayCVL = new ArrayList();
         ArrayList myArray;
         if (!isMandatory) {
           myArray = new ArrayList();
           myArray.add("");
           myArray.add("");
           arrayCVL.add(myArray);
         }
         for (int i=0;i<fieldCode.length;i++) {
           myArray = new ArrayList();
           myArray.add(fieldCode[i]);
           myArray.add(fieldValue[i]);
           arrayCVL.add(myArray);
         }
         tableList.put(nameInHashTable, arrayCVL);
  }

  public static void setSelectedValueInComboBox(JComboBox combo,
                                                String nameInHashTable,
                                                String codigoBusqueda) {
      String codigo;
      for (int i=0; i< combo.getItemCount(); i++) {
          codigo = getCVLCode(nameInHashTable, i);
          if (codigo.equalsIgnoreCase(codigoBusqueda)) {
            combo.setSelectedIndex(i);
            return;
         }
      }
  }

  public static void loadCVLFromSP(JComboBox combo,
                                   String nameInHashTable,
                                   String pSP,
                                   ArrayList parametros,
                                   boolean isMandatory) {
    loadCVLFromSP(combo,nameInHashTable,pSP,parametros,isMandatory,false);
  }

  /** Le enviamos un ArrayList y lo carga desde un Stored Procedure para hacer combo */
  public static void loadCVLFromSP(JComboBox combo,
                                   String nameInHashTable,
                                   String pSP,
                                   ArrayList parametros,
                                   boolean isMandatory,
                                   boolean replaceCVL) {
    try {
      ArrayList myArrayList = new ArrayList();
      AtuxDBUtility.executeSQLStoredProcedureArrayList(myArrayList,pSP,parametros);
      if ( replaceCVL ) {
        if ( tableList.containsKey(nameInHashTable))
          tableList.remove(nameInHashTable);
        loadDataToCVLFromSP(myArrayList,nameInHashTable,isMandatory);
      } else if ( !tableList.containsKey(nameInHashTable) )
        loadDataToCVLFromSP(myArrayList,nameInHashTable,isMandatory);
      ArrayList list = (ArrayList)tableList.get(nameInHashTable);
      for (int i = 0 ; i< list.size(); i++) {
         ArrayList data = (ArrayList)list.get(i);
         combo.addItem(data.get(1));
      }
    } catch (SQLException errCVLFromSP) {
      errCVLFromSP.printStackTrace();
    }
  }

  public static void loadDataToCVLFromSP(ArrayList pArrayList,
                                         String pNameInHashTable,
                                         boolean pIsMandatory) {
    ArrayList arrayCVL = new ArrayList();
    ArrayList myArray;
    if (!pIsMandatory) {
      myArray = new ArrayList();
      myArray.add("");
      myArray.add("");
      arrayCVL.add(myArray);
    }
    for (int i=0; i<pArrayList.size() ; i++) {
      myArray = new ArrayList();
      myArray.add((String)((ArrayList)pArrayList.get(i)).get(0));
      myArray.add((String)((ArrayList)pArrayList.get(i)).get(1));
      arrayCVL.add(myArray);
    }
    tableList.put(pNameInHashTable, arrayCVL);
  }

  public static String getCVLDescription(String pTableName, String pCode) {
    String description = new String("");
    if ( tableList.containsKey(pTableName) ) {
      ArrayList list = (ArrayList)tableList.get(pTableName);
      //ArrayList data = new ArrayList();
      for (int i=0; i<list.size(); i++) {
        if ( ((String)((ArrayList)list.get(i)).get(0)).trim().equalsIgnoreCase(pCode) )
          description = ((String)((ArrayList)list.get(i)).get(1)).trim();
      }
    }
    return description;
    }

}
