package com.aw.builder;

/**
 * User: Kaiser
 * Date: 31/03/2009
 */
public class GenerateTemplate {
    public static void main(String[] args) throws Exception{

        Context.packageDir= args[0];
        Context.packageName= args[1];
/*
        Context.packageDir= "com/sider/suministros";
        Context.packageName= "com.sider.suministros";
*/
        Context.schema= "SUMIDESA" ;
        Context.password= "SUMIDESA";

        SVGenerarTemplate svGenerarTemplate = new SVGenerarTemplateImpl();
        svGenerarTemplate.execute();
    }
}
