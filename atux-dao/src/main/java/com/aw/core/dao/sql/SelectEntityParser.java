package com.aw.core.dao.sql;

import com.aw.core.dao.meta.EntityPropertyMapper;
import com.aw.core.dao.meta.HbmUtilFactory;
import com.aw.core.util.QTTablaABeanMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Julio C. Macavilca
 * Date: 29/01/2008
 */
public class SelectEntityParser implements Serializable {
    transient protected static Log logger = LogFactory.getLog(SelectEntityParser.class);
    /**
     * Mantiene la lista de properties
     */
    protected Map<String, Class> properyToEntityClassMap = new HashMap<String, Class>();
    protected BeanWrapper wrap = null;

    public StringBuffer paserXX(CharSequence sql, Class beanClass) {
        wrap = new BeanWrapperImpl(beanClass);
        StringBuffer workSQL = (StringBuffer) (sql instanceof StringBuffer ? sql : new StringBuffer(sql));
        do {
            // Buscar pattern (\w+).* donde  \w+  es el nombre del alias
            Matcher matcherTabFull = Pattern.compile("\\b(\\w+)\\.\\*").matcher(workSQL);
            if (!matcherTabFull.find()) break; // no hay mas matches
            String tablaAlias = matcherTabFull.group(1);
            logger.debug("SQLParse entity :" + tablaAlias + " fullSQL:" + workSQL);

            // Buscar  FROM(.+alias)
            //String tabla = buscarTabla(workSQL, tablaAlias);

            String entityProperty = tablaAlias; //asumir que el nombre del property es el alias
            logger.debug("SQLParse alias:" + tablaAlias + " entityProperty:" + entityProperty);
            StringBuffer fullEntitySelec = expandSelectExpr(tablaAlias, entityProperty);
            workSQL.replace(matcherTabFull.start(), matcherTabFull.end(), fullEntitySelec.toString());
        } while (true);
        // Buscar pattern SELECT(.*)FROM
        Matcher matcherSelectExp = Pattern.compile("SELECT(.*)FROM", Pattern.CASE_INSENSITIVE).matcher(workSQL);
        List<SelectExpr> selectExprList = new ArrayList<SelectExpr>();
        while (matcherSelectExp.find()) {
            SelectExpr selectExpr = new SelectExpr();
            selectExpr.idxStart = matcherSelectExp.start(1);
            selectExpr.idxEnd = matcherSelectExp.end(1);
            selectExpr.strOld = matcherSelectExp.group(1);
            // as\s(colAlias)  --> espacio (cualquiera)
            Matcher matcherColsExp = Pattern.compile("(as\\s+)(\\w+)(\\s*(?:,|$))", Pattern.CASE_INSENSITIVE).matcher(selectExpr.strOld);
            selectExpr.strNew = matcherColsExp.replaceAll("$1\"$2\"$3");
            selectExprList.add(selectExpr);
            logger.debug("SQLSelectParse expr :" + selectExpr.strOld + "-->" + selectExpr.strNew);
        }
        for (int i = selectExprList.size() - 1; i >= 0; i--) {
            SelectExpr selectExpr = selectExprList.get(i);
            workSQL.replace(selectExpr.idxStart, selectExpr.idxEnd, selectExpr.strNew);
        }

        logger.debug("SQL result :" + workSQL);
        return workSQL;
    }

    private static class SelectExpr {
        int idxStart;
        int idxEnd;
        String strOld;
        String strNew;
    }

    public SelectEntityParser mapPropertyClass(String property, Class entityClass) {
        properyToEntityClassMap.put(property, entityClass);
        return this;
    }

    public Map<String, Class> getProperyToEntityClassMap() {
        return properyToEntityClassMap;
    }

    private String buscarTabla(StringBuffer workSQL, String alias) {
        Matcher matcherTabla = Pattern.compile("FROM(.+\\b" + alias + "\\b)", Pattern.CASE_INSENSITIVE).matcher(workSQL);
        if (!matcherTabla.find())
            throw new IllegalArgumentException("Error : No se encontro tabla para alias:" + alias);
        String fromClause = matcherTabla.group(1).trim();
        if (fromClause.lastIndexOf(',') != -1)
            fromClause = fromClause.substring(fromClause.lastIndexOf(','));

        String[] tablaAlias = Pattern.compile("(\\bAS\\b)|(\\s)", Pattern.CASE_INSENSITIVE).split(fromClause);
        String tabla = tablaAlias[0].trim();
        return tabla;
    }

//    private Pattern buildPattern(String expresion) {
//        return Pattern.compile("\\b"+expresion+"\\b",Pattern.CASE_INSENSITIVE);
//    }

//    public String convertSimpleSelect(String sql, Class beanClass) {
//        SelectAliasParser parser = new SelectAliasParser(sql);
//        StringBuffer selectClause = new StringBuffer();
//        final BeanWrapper wrap = new BeanWrapperImpl(beanClass);
//        for (SelectAliasParser.IdAlias col:  parser.getCols()) {
//
//            String colExpr = col.getId() + " as \""+col.getAlias()+"\" ";
//            if (col.getId().indexOf(".*") !=-1){
//                String tabAlias = col.getId().substring(0, col.getId().indexOf(".*"));
//                // hay una sentencia para traer toda la tabla
//
////                // Buscar tabla relacionada
////                SelectAliasParser.IdAlias tab = null;
////                for (SelectAliasParser.IdAlias tabCur:  parser.getTabs()) {
////                    if (tabCur.getAlias().toUpperCase().indexOf(col.getId().toUpperCase())!=-1){
////                        tab = tabCur;
////                        break;
////                    }
////                }
////                if (tab==null) throw new IllegalArgumentException("Tabla "+col.getFullExp()+" no se puede ubicar en FROM clause");
//
//                //Obtener entity relacionado
//                String entityProperty = col.getAlias().replaceAll("\\.\\*","");
//                StringBuffer colExprFull = expandSelectExpr(wrap, tabAlias, entityProperty);
//                //entityFieldsToCreateList.add(tabAlias); //asegurarse de crear el campo
//                entityFieldsToCreateList.add(entityProperty); //asegurarse de crear el campo
//                colExpr = colExprFull.toString();
//                logger.debug("Entity found expr:"+col.getId()+" colExpr :"+colExpr);
//            }
//            if (selectClause.length()>0) selectClause.append(", ");
//            selectClause.append(colExpr);
//        }
//        sql = parser.replaceSelect(selectClause);
//        logger.info("SQL parsed:"+sql);
//        return sql;
//    }

    private StringBuffer expandSelectExpr(String tablaAlias, String entityProperty) {
        logger.debug("Entity found tablaAlias:" + tablaAlias + " entityProperty:" + entityProperty);
        Class entityClass = guessClassForProperty(tablaAlias, entityProperty);
        EntityPropertyMapper propertyMapper = HbmUtilFactory.newInstance().buildPropertyMapper(entityClass);
        StringBuffer colExprFull = new StringBuffer();
        for (EntityPropertyMapper.PropertyIterator iterator = propertyMapper.iterator(); iterator.hasNext();) {
            String propName = (String) iterator.next();
            String[] colName = iterator.getPropertyColumnNames();
            if (iterator.getPropertyType().isEntityType())
                continue;
            if (iterator.getPropertyType().isCollectionType())
                continue;
            if (colName.length == 0) continue;
            if (colExprFull.length() > 0) colExprFull.append(", ");
            colExprFull.append(tablaAlias).append(".").append(colName[0]);
            colExprFull.append(" as ");
            colExprFull.append("\"").append(entityProperty).append(".").append(propName).append("\"");
        }

//        Class entityClass = guessClassForProperty(tablaAlias,entityProperty);
//        AbstractEntityPersister metaData = HbmUtilFactory.newInstance().getClasMetadata(entityClass);
//        String[] propNames = metaData.getPropertyNames();
//        StringBuffer colExprFull = new StringBuffer();
//        for (int i = 0; i < propNames.length; i++) {
//            String propName = propNames[i];
//            String[] colName = metaData.getPropertyColumnNames(i);
//            if (metaData.getPropertyType(propName).isEntityType())
//                continue;
//            if (colName.length==0) continue;
//            if (colExprFull.length()>0) colExprFull.append(", ");
//            colExprFull.append(tablaAlias).append(".").append(colName[0]);
//            colExprFull.append(" as ");
//            colExprFull.append("\"").append(entityProperty).append(".").append(propName).append("\"");
//        }
        return colExprFull;
    }

    private Class guessClassForProperty(String tablaAlias, String entityProperty) {
        Class entityClass = properyToEntityClassMap.get(entityProperty);
        if (entityClass == null) {
            entityClass = wrap.getPropertyType(entityProperty);
            if (entityClass == null)
                throw new IllegalArgumentException("Property " + entityProperty + " does not exist on result row bean");
            properyToEntityClassMap.put(entityProperty, entityClass);
        }
        return entityClass;
    }

    public QTTablaABeanMapper buildBeanMapper() {
        QTTablaABeanMapper beanMapper = new CustomQTTablaABeanMapper(properyToEntityClassMap);
        return beanMapper;
    }


    public static class CustomQTTablaABeanMapper extends QTTablaABeanMapper {
        protected Map<String, Class> properyToEntityClassMap = new HashMap<String, Class>();

        public CustomQTTablaABeanMapper(Map<String, Class> properyToEntityClassMap) {
            this.properyToEntityClassMap = properyToEntityClassMap;
        }

        protected Object instantiateBean(Class type) {
            Object bean = super.instantiateBean(type);
            final BeanWrapper wrap = new BeanWrapperImpl(bean);
            for (String entityFieldsToCreate : properyToEntityClassMap.keySet()) {
                if (wrap.getPropertyValue(entityFieldsToCreate) == null) {

                    Class classType = properyToEntityClassMap.get(entityFieldsToCreate);
                    wrap.setPropertyValue(entityFieldsToCreate, super.instantiateBean(classType));
                }
            }
            return bean;
        }
    }
}
