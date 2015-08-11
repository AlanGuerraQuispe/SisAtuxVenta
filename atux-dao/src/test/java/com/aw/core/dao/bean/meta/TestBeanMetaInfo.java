package com.aw.core.dao.bean.meta;

import com.aw.core.dao.bean.*;
import junit.framework.Assert;

import java.util.Date;

/**
 * User: JCM
 * Date: 15/10/2007
 */
public class TestBeanMetaInfo extends BeanSqlAbstractTest {


    private void checkTests(BeanMetaInfo metaInfo) {
        Assert.assertEquals("DAO_HBM_TABLE_BEAN", metaInfo.getMainTable());

        Assert.assertNotNull(metaInfo.getColumn("fieldPk"));
        Assert.assertEquals("ID_FIELD", metaInfo.getColumn("fieldPk").getColumn());
        Assert.assertTrue(metaInfo.getColumn("fieldPk").isPk());

        Assert.assertNotNull(metaInfo.getColumn("field1"));
        Assert.assertEquals("FIELD_TEXT", metaInfo.getColumn("field1").getColumn());

        Assert.assertNotNull(metaInfo.getColumn("fieldDate"));
        Assert.assertEquals("FIELD_DATE", metaInfo.getColumn("fieldDate").getColumn());
    }


    //////////////////////////////// SQL Table ////////////////////////////////
    public void testDAOSqlTable(){

        BeanMetaInfo  metaInfo = BeanMetaInfoBuilder.instance().build(DAOSqlTableBean.class);
        checkTests(metaInfo);
    }

    /** Mapeo a una tabla de BD */
    @DAOSqlTable("DAO_HBM_TABLE_BEAN")
    public static class DAOSqlTableBean {
        /** Primary key configuracion */
        @DAOHbmColumnEx(table = DAOHbmTableImpl.class, column = "id")
        public Long fieldPk;

        /** Mapeo a una columna de BD */
        @DAOSqlColumn("FIELD_TEXT")
        public String field1;

        /** Mapeo a una columna de BD */
        @DAOSqlColumn("FIELD_DATE")
        public Date fieldDate;

        public Long getFieldPk() {
            return fieldPk;
        }

        public void setFieldPk(Long fieldPk) {
            this.fieldPk = fieldPk;
        }

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        public Date getFieldDate() {
            return fieldDate;
        }

        public void setFieldDate(Date fieldDate) {
            this.fieldDate = fieldDate;
        }
    }

    //////////////////////////////// Hibernate Table ////////////////////////////////
    public void testDAOHbmTable(){

        BeanMetaInfo  metaInfo = BeanMetaInfoBuilder.instance().build(DAOHbmTableBean.class);
        checkTests(metaInfo);
    }

    /** Link a entity DAOHbmTableImpl */
    @DAOHbmTable(DAOHbmTableImpl.class )
    public static class DAOHbmTableBean {
        /** Link a atributo DAOHbmTableImpl#id */
        @DAOHbmColumn("id")
        public Long fieldPk;

        /** Link a atributo DAOHbmTableImpl#fieldText */
        @DAOHbmColumn("fieldText")
        public String field1;

        /** Link a atributo DAOHbmTableImpl#fieldDate (por defecto asume el mismo nombre) */
        public Date fieldDate;

        public Long getFieldPk() {
            return fieldPk;
        }

        public void setFieldPk(Long fieldPk) {
            this.fieldPk = fieldPk;
        }

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        public Date getFieldDate() {
            return fieldDate;
        }

        public void setFieldDate(Date fieldDate) {
            this.fieldDate = fieldDate;
        }
    }
}
