package com.aw.core.util;

import com.aw.core.dao.bean.BeanSqlAbstractTest;
import com.aw.core.dao.bean.DAOHbmTableImpl;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

/**
 * User: Julio C. Macavilca
 * Date: 29/01/2008
 */
public class TestDeleteableList  extends BeanSqlAbstractTest {
    private String FIELD_TEXT_PATTERN = "DEL";

    protected void setUp() throws Exception {
        super.setUp();
        deleteTestData();
    }

    protected void tearDown() throws Exception {
        deleteTestData();
        super.tearDown();
    }

    private void deleteTestData() {
        doUnderSession(new SessionUnitOfWork() {
            public Object execute(Session session) {
                return session.createSQLQuery("DELETE from DAO_HBM_TABLE_BEAN where FIELD_TEXT like ?")
                        .setString(0, FIELD_TEXT_PATTERN+"%")
                        .executeUpdate();
            }
        });
    }

    public void testSimple(){
        List dataFromDB = cargarTabla(5); // inserta en la tabla 5 elementos

        assertEquals( "data incial invalida", 5, dataFromDB.size());

        DeleteableList list = new DeleteableList();
        list.init(dataFromDB);
        assertEquals( "inicializacion invalida", 5, list.toPersist().size());
        assertEquals( "inicializacion invalida", 0, list.toDelete(true).size());
        assertEquals( "inicializacion invalida", 0, list.toDelete(false).size());

        list.add(createTrasientEntity(5)); // simular objeto agregado en memoria
        list.add(createTrasientEntity(6)); // simular objeto agregado en memoria
        list.add(createTrasientEntity(7)); // simular objeto agregado en memoria
        list.remove( 5 ); // remover el quinto elemento (transcient)
        list.remove( 2 ); // remover el segundo elemento (persistent)

        assertEquals( "Elementos a persitir invalido",5+3-2, list.toPersist().size());
        assertEquals( "Elementos a persitir invalido",list, list.toPersist());
        // solo 1 eliminado en BD
        assertEquals( "Elementos a eliminar invalido",1, list.toDelete(true).size());
        // 1 persited eliminado y 1 trascient eliminado
        assertEquals( "Elementos a eliminar invalido",2, list.toDelete(false).size());

    }
    public void testJTable(){
        List dataFromDB = cargarTabla(5); // inserta en la tabla 5 elementos

        assertEquals( "data incial invalida", 5, dataFromDB.size());

        DeleteableList list = new DeleteableList();
        list.init(dataFromDB);
        assertEquals( "inicializacion invalida", 5, list.toPersist().size());
        assertEquals( "inicializacion invalida", 0, list.toDelete(true).size());
        assertEquals( "inicializacion invalida", 0, list.toDelete(false).size());

        dataFromDB.add(createTrasientEntity(5)); // simular objeto agregado en memoria
        dataFromDB.add(createTrasientEntity(6)); // simular objeto agregado en memoria
        dataFromDB.add(createTrasientEntity(7)); // simular objeto agregado en memoria
        dataFromDB.remove( 5 ); // remover el quinto elemento (transcient)
        dataFromDB.remove( 2 ); // remover el segundo elemento (persistent)

        list.update(dataFromDB);

        assertEquals( "Elementos a persitir invalido",5+3-2, list.toPersist().size());
        assertEquals( "Elementos a persitir invalido",list, list.toPersist());
        // solo 1 eliminado en BD
        assertEquals( "Elementos a eliminar invalido",1, list.toDelete(true).size());
        // 1 persited eliminado y 1 trascient eliminado
        assertEquals( "Elementos a eliminar invalido",1, list.toDelete(false).size());

    }

    private List cargarTabla(final int rows) {
        return (List) doUnderSession(new SessionUnitOfWork() {
            public Object execute(Session session) {
                for (int i = 0; i < rows; i++) {
                    DAOHbmTableImpl table = createTrasientEntity(i);
                    session.saveOrUpdate(table);
                }
                return session.createQuery("from DAOHbmTableImpl p where p.fieldText like ?")
                        .setString(0, FIELD_TEXT_PATTERN+"%")
                        .list();
            }
        });
    }

    private DAOHbmTableImpl createTrasientEntity(int i) {
        DAOHbmTableImpl table = new DAOHbmTableImpl();
        table.setFieldText(FIELD_TEXT_PATTERN+i);
        table.setFieldDate(new Date());
        return table;
    }
}
