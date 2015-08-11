package com.aw.builder;

import com.aw.builder.templates.*;
import com.aw.builder.templates.form.SimpleForm;
import com.aw.builder.templates.form.SimpleFormClass;
import com.aw.builder.templates.hbm.Pojo;
import com.aw.builder.templates.web.*;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * User: Kaiser
 * Date: 31/03/2009
 */
public class SVGenerarTemplateImpl implements SVGenerarTemplate {

    public void execute() throws Exception {

        VelocityEngine ve = new VelocityEngine();
        Properties properties = new Properties();

        properties.load(new FileInputStream("./src/velocity.properties"));

        ve.init(properties);

        try {
            generateTemplateForTm(ve);

        } catch (IOException e) {
        }
    }

    private void generateTemplateForTm(VelocityEngine ve) throws Exception {


        List tables = RPRepository.getMasterTable();

/*          BNTable bnTable= new BNTable();
          bnTable.setTableName("VENTA_TC");
          bnTable.setUseDetail(true);
          bnTable.setMasterTable(true);
          BNTable bnTable2= new BNTable();
          bnTable2.setTableName("VENTA_TD");
          bnTable2.setUseDetail(false);
          bnTable2.setMasterTable(false);

          List tables = new ArrayList();
          tables.add(bnTable);
          tables.add(bnTable2);*/


        for (Iterator iterator = tables.iterator(); iterator.hasNext();) {
            BNTable table = (BNTable) iterator.next();

            List columns = RPRepository.generateColumns(table);

            if (table.getTableName().endsWith("_TD")) {
                new IpConfig().process(ve, table, columns);
            }

            new FNController().process(ve, table, columns);
            new SimpleController().process(ve, table, columns);
            new Service().process(ve, table, columns);
            new ServiceImpl().process(ve, table, columns);
            new Repository().process(ve, table, columns);
            new SimpleForm().process(ve, table, columns);
            new SimpleFormClass().process(ve, table, columns);
            new Pojo().process(ve, table, columns);
            if (table.isUserBN()) {
                new Bean().process(ve, table, columns);
            }
        }
    }
}
