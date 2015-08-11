package com.aw.core.dao.bean.sql;

import com.aw.core.dao.bean.BeanSqlAbstractTest;
import com.aw.core.dao.bean.meta.BeanMetaInfo;
import com.aw.core.dao.bean.meta.TestBeanMetaInfo;
import junit.framework.Assert;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.*;

/**
 * User: JCM
 * Date: 15/10/2007
 */
public class TestBeanSqlBuilder extends BeanSqlAbstractTest {

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
        // SELECT
        BeanSqlBuilderQueryMock sqlSelect = new BeanSqlBuilderQueryMock(TestBeanMetaInfo.DAOSqlTableBean.class);
        TestBeanMetaInfo.DAOSqlTableBean  bean = (TestBeanMetaInfo.DAOSqlTableBean) sqlSelect.load(new Long(1));
        Assert.assertEquals("SELECT ID_FIELD as \"fieldPk\",FIELD_TEXT as \"field1\",FIELD_DATE as \"fieldDate\" FROM DAO_HBM_TABLE_BEAN WHERE ID_FIELD=?", sqlSelect.lastSql);

        // INSERT
        bean = new TestBeanMetaInfo.DAOSqlTableBean();
        bean.setFieldPk(null);
        bean.setField1("Holas");
        bean.setFieldDate(new Date());
        BeanSqlBuilderUpdateMock sqlInsert = new BeanSqlBuilderUpdateMock(bean);
        Assert.assertTrue(sqlInsert.isUnsaved());
        sqlInsert.saveOrUpdate();
        Assert.assertEquals("INSERT INTO DAO_HBM_TABLE_BEAN (ID_FIELD,FIELD_TEXT,FIELD_DATE) VALUES(?,?,?)", sqlInsert.lastSql);

        // UPDATE
        bean = new TestBeanMetaInfo.DAOSqlTableBean();
        bean.setFieldPk(new Long(1));
        bean.setField1("Holas2");
        bean.setFieldDate(new Date());
        BeanSqlBuilderUpdateMock sqlUpdate = new BeanSqlBuilderUpdateMock(bean);
        Assert.assertFalse(sqlUpdate.isUnsaved());
        sqlUpdate.saveOrUpdate();
        Assert.assertEquals("UPDATE DAO_HBM_TABLE_BEAN SET FIELD_TEXT=?, FIELD_DATE=? WHERE ID_FIELD=?", sqlUpdate.lastSql);
    }
    //////////////////////////////// Hibernate Table ////////////////////////////////
//    public void testDAOHbmTable(){
//        // es equivalente al anterior
//
//    }

    public static class BeanSqlBuilderQueryMock extends BeanSqlBuilderQueryImpl{
        protected String  lastSql;
        protected Object[] lastParams;


        public BeanSqlBuilderQueryMock(Class beanClass) {
//            super(beanClass);
            super(null,null,null);
        }

        /** Allow test case*/
        protected List execSqlQuery(String fullSql, Object[] params) {
            lastSql = fullSql;
            lastParams=params;
            logger.debug(fullSql +":"+ Arrays.asList(params));
            return new ArrayList(0);
        }

        /** Allow test case*/
        protected int execSqlUpdate(String fullSql, Object[] params) {
            lastSql = fullSql;
            lastParams=params;
            logger.debug(fullSql +":"+ Arrays.asList(params));
            return 1;
        }

        /** Allow test case*/
        protected Serializable generateIdentifier(final IdentifierGenerator identifierGenerator) {
            return new Random().nextLong();
        }
    }
    public static class BeanSqlBuilderUpdateMock extends BeanSqlBuilderUpdateImpl {
        protected String  lastSql;
        protected Object[] lastParams;

        public BeanSqlBuilderUpdateMock(Object bean) {
//            super(bean);
            super(null,null,bean);
        }


        /** Allow test case*/
        protected List execSqlQuery(String fullSql, Object[] params) {
            lastSql = fullSql;
            lastParams=params;
            logger.debug(fullSql +":"+ Arrays.asList(params));
            return new ArrayList(0);
        }

        /** Allow test case*/
        protected int execSqlUpdate(String fullSql, Object[] params) {
            lastSql = fullSql;
            lastParams=params;
            logger.debug(fullSql +":"+ Arrays.asList(params));
            return 1;
        }

        /** Allow test case*/
        protected Serializable generateIdentifier(final IdentifierGenerator identifierGenerator) {
            return new Random().nextLong();
        }
    }
}
