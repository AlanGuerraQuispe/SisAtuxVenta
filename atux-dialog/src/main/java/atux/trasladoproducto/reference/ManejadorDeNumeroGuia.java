package atux.trasladoproducto.reference;

import java.sql.SQLException;

import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;

/**
 * User: gmc
 * Date: 14/05/2010
 */
public class ManejadorDeNumeroGuia {

    private static final int MAX_PRODS_X_GUIA = 36;
    private static final int MAX_SECUENCIAL = 9999999;
    private String nuSerieBase = "";
    private String nuSecuencialBase = "";

    private String nuSerieActual = "";
    private String nuSecuencialActual = "";

    private int nuPagina = 1;
    private int numeroProductos = 0;

    public void init(int numeroProductos) throws SQLException {
        String nuSecGuia = AtuxSearch.getNuSecNumeracionStr(ConstantsTrasladoProducto.NUMERACION_GUIA_SALIDA, 10);
//        String nuSecGuia = "0090000001";
        nuSerieBase = nuSecGuia.substring(0, 3);
        nuSecuencialBase = nuSecGuia.substring(3);
        nuSerieActual = nuSerieBase;
        nuSecuencialActual = nuSecuencialBase;
        this.numeroProductos = numeroProductos;
    }

    public int obtenerCaItemsGuia(int numeroDeFilas) {
        int residuo = numeroDeFilas % MAX_PRODS_X_GUIA;
        if (residuo == 1) {
            int filasRestantes = numeroProductos - numeroDeFilas;
            if (filasRestantes>=MAX_PRODS_X_GUIA){
                return MAX_PRODS_X_GUIA;
            }else{
                return filasRestantes+1;                
            }
        }
        return  0;
    }

    public String obtenerSiguiente(int numeroDeFilas) {
        int residuo = numeroDeFilas % MAX_PRODS_X_GUIA;
        if (numeroDeFilas > 1 && residuo == 1) {
            incrementarSecuencial();
            nuPagina = numeroDeFilas / MAX_PRODS_X_GUIA + 1;
        }
        return nuSerieActual + nuSecuencialActual;
    }

    private void incrementarSecuencial() {
        int iNuSerieActual = Integer.parseInt(nuSerieActual);
        int iNuSerieNuevo = iNuSerieActual;
        int iNuSecuencialActual = Integer.parseInt(nuSecuencialActual);
        int iNuSecuencialNuevo = iNuSecuencialActual + 1;
        boolean seTerminoSecuenciales = iNuSecuencialActual == MAX_SECUENCIAL;
        if (seTerminoSecuenciales) {
            iNuSerieNuevo = iNuSerieActual + 1;
            iNuSecuencialNuevo = 0;
        }
        nuSerieActual = AtuxUtility.completeWithSymbol("" + iNuSerieNuevo, 3, "0", "I");
        nuSecuencialActual = AtuxUtility.completeWithSymbol("" + iNuSecuencialNuevo, 7, "0", "I");
    }

    public int getNuPagina() {
        return nuPagina;
    }

    public String obtenerNumeroGuiaDisponible() {
        incrementarSecuencial();
        return nuSerieActual + nuSecuencialActual;
    }

    public static void main(String[] args) throws SQLException {
        ManejadorDeNumeroGuia manejadorDeNumeroGuia = new ManejadorDeNumeroGuia();
        manejadorDeNumeroGuia.init(110);
        String sgteNumero = manejadorDeNumeroGuia.obtenerSiguiente(1);
        System.out.println("1-->" + sgteNumero + " pag:" + manejadorDeNumeroGuia.nuPagina + " caItems: "+manejadorDeNumeroGuia.obtenerCaItemsGuia(1));
        sgteNumero = manejadorDeNumeroGuia.obtenerSiguiente(36);
        System.out.println("36-->" + sgteNumero + " pag:" + manejadorDeNumeroGuia.nuPagina+ " caItems: "+manejadorDeNumeroGuia.obtenerCaItemsGuia(36));
        sgteNumero = manejadorDeNumeroGuia.obtenerSiguiente(37);
        System.out.println("37-->" + sgteNumero + " pag:" + manejadorDeNumeroGuia.nuPagina+ " caItems: "+manejadorDeNumeroGuia.obtenerCaItemsGuia(37));
        sgteNumero = manejadorDeNumeroGuia.obtenerSiguiente(72);
        System.out.println("72-->" + sgteNumero + " pag:" + manejadorDeNumeroGuia.nuPagina+ " caItems: "+manejadorDeNumeroGuia.obtenerCaItemsGuia(72));
        sgteNumero = manejadorDeNumeroGuia.obtenerSiguiente(73);
        System.out.println("73-->" + sgteNumero + " pag:" + manejadorDeNumeroGuia.nuPagina+ " caItems: "+manejadorDeNumeroGuia.obtenerCaItemsGuia(73));
        sgteNumero = manejadorDeNumeroGuia.obtenerSiguiente(108);
        System.out.println("108-->" + sgteNumero + " pag:" + manejadorDeNumeroGuia.nuPagina+ " caItems: "+manejadorDeNumeroGuia.obtenerCaItemsGuia(108));
        sgteNumero = manejadorDeNumeroGuia.obtenerSiguiente(109);
        System.out.println("109-->" + sgteNumero + " pag:" + manejadorDeNumeroGuia.nuPagina+ " caItems: "+manejadorDeNumeroGuia.obtenerCaItemsGuia(109));
        System.out.println("Fin:" + manejadorDeNumeroGuia.obtenerNumeroGuiaDisponible());
    }
}
