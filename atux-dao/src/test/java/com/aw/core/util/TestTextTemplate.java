package com.aw.core.util;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Julio C. Macavilca
 * Date: 29/01/2008
 */
public class TestTextTemplate extends TestCase {
    private String FIELD_TEXT_PATTERN = "DEL";

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testParseSimple(){
        String template = "fjsfjks {field1:LEFT:12}sfsdX{field2:LEFT:4}YYY{field3}ZZZ";
        String resultReal   = "fjsfjks 1234567890  sfsdXabcdYYY123ZZZ";
        Map cabecera = new HashMap();
        cabecera.put("field1", "1234567890");
        cabecera.put("field2", "abcdef");
        cabecera.put("field3", "123");
        TextTemplate textTemplate = new  TextTemplate(template);
        String result = textTemplate.build(cabecera);
        assertEquals(resultReal, result);
    }

    public void testParseDetalle(){
        String template = "WWW{field1}XXX{{ zzz{field1}xxx{field2:LEFT:4}yyy }}YYY";
        String resultReal   = "fjsfjks 1234567890  sfsdXabcdYYY";
        Map cabecera = new HashMap();
        cabecera.put("field1", "1234567890");

        Map detalle1 = new HashMap();
        cabecera.put("field2", "01-ABC");
        Map detalle2 = new HashMap();
        cabecera.put("field2", "02-XYZ");
        List detalle = new ArrayList();
        detalle.add(detalle1);
        detalle.add(detalle2);
        TextCDTemplate textTemplate = new  TextCDTemplate(template);
        String result = textTemplate.build(cabecera, detalle);
        assertEquals(resultReal, result);
    }


}