package atux.interfaces;

import name.GenerateNameFile;
import report.WriteFormat;
import atux.managerbd.BaseConexion;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;

import com.aw.core.business.AWContext;


public class GeneraArchivo {

	private String codInterface;

	private String rutaXML = "";

	private String rutaArchivo = "";

	private String nombreArchivo = "";

	private int maxLines = 0;

	private String procIdentificador = "";

	private String procCabecera = "";

	private String procDetalle = "";

	private String procCondicion = "";

	private String paramCabecera = "";

	private String paramDetalle = "";

	private String paramCondicion = "";

	private boolean withCommit = true;

	public GeneraArchivo() {

	}

	public String getCodInterface() {
		return codInterface;
	}

	public void setCodInterface(String codInterface) {
		this.codInterface = codInterface;
	}

	public String getRutaXML() {
		return rutaXML;
	}

	public void setRutaXML(String rutaXML) {
		this.rutaXML = rutaXML;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public int getMaxLines() {
		return maxLines;
	}

	public void setMaxLines(int maxLines) {
		this.maxLines = maxLines;
	}

	public String getProcIdentificador() {
		return procIdentificador;
	}

	public void setProcIdentificador(String procIdentificador) {
		this.procIdentificador = procIdentificador;
	}

	public String getProcCabecera() {
		return procCabecera;
	}

	public void setProcCabecera(String procCabecera) {
		this.procCabecera = procCabecera;
	}

	public String getProcDetalle() {
		return procDetalle;
	}

	public void setProcDetalle(String procDetalle) {
		this.procDetalle = procDetalle;
	}

	public String getProcCondicion() {
		return procCondicion;
	}

	public void setProcCondicion(String procCondicion) {
		this.procCondicion = procCondicion;
	}

	public String getParamCabecera() {
		return paramCabecera;
	}

	public void setParamCabecera(String paramCabecera) {
		this.paramCabecera = paramCabecera;
	}

	public String getParamDetalle() {
		return paramDetalle;
	}

	public void setParamDetalle(String paramDetalle) {
		this.paramDetalle = paramDetalle;
	}

	public String getParamCondicion() {
		return paramCondicion;
	}

	public void setParamCondicion(String paramCondicion) {
		this.paramCondicion = paramCondicion;
	}

	public void generar() throws Exception {
		rutaXML = AtuxDBUtility.getValueAt("COTM_CF_INTERFACE", "DE_RUTA_XML", "CO_INTERFACE = '" + codInterface + "'");
		maxLines = Integer.parseInt(AtuxDBUtility.getValueAt("COTM_CF_INTERFACE", "NU_MAX_LINEAS", "CO_INTERFACE = '" + codInterface + "'"));
		rutaArchivo = AtuxDBUtility.getValueAt("COTR_CF_INTERFACE_RUTA", "DE_RUTA_ESC_ORIGEN", "CO_INTERFACE = '" + codInterface + "'");

		String rutaServidor = "/Users/user/Desktop/";
		
		
//		if (AtuxVariables.vModoTest) {
//			rutaServidor = AtuxDBUtility.getValueAt("CMTR_VARIABLES_SISTEMA", "VA_TEXTO", "CO_COMPANIA = '001' AND CO_LOCAL = '099' AND CO_VARIABLE = 'ruta.archivo.test'");
//		} else {
//			if (AtuxVariables.vCodigoLocal.equals("355")) {
//				
//			} else {
//				rutaServidor = "//" + AtuxUtility.getIPLocal() + "/";
//			}
//		}

		GenerateNameFile generateNameFile = new GenerateNameFile(BaseConexion.getConexion(), (Integer.valueOf(codInterface)).intValue(), "", AtuxVariables.vCodigoLocal);
		nombreArchivo = generateNameFile.getNameFile();

			WriteFormat writeFormat = new WriteFormat(BaseConexion.getConexion(), AtuxVariables.vCodigoLocal, rutaServidor + rutaArchivo, nombreArchivo, procCabecera, AtuxUtility.replaceString(
				paramCabecera, "NAME_FILE", nombreArchivo), procDetalle, paramDetalle, procCondicion, paramCondicion, rutaServidor + rutaXML, procIdentificador, maxLines, AtuxVariables.vModoTest);
			writeFormat.setWithCommit(withCommit);
			writeFormat.imprimir();
	}

	public void generarHiberanate() throws Exception {
		rutaXML = AtuxDBUtility.getValueAt("COTM_CF_INTERFACE", "DE_RUTA_XML", "CO_INTERFACE = '" + codInterface + "'");
		maxLines = Integer.parseInt(AtuxDBUtility.getValueAt("COTM_CF_INTERFACE", "NU_MAX_LINEAS", "CO_INTERFACE = '" + codInterface + "'"));
		rutaArchivo = AtuxDBUtility.getValueAt("COTR_CF_INTERFACE_RUTA", "DE_RUTA_ESC_ORIGEN", "CO_INTERFACE = '" + codInterface + "'");

		String rutaServidor = "";
		if (AtuxVariables.vModoTest) {
			rutaServidor = AtuxDBUtility.getValueAt("CMTR_VARIABLES_SISTEMA", "VA_TEXTO", "CO_COMPANIA = '001' AND CO_LOCAL = '099' AND CO_VARIABLE = 'ruta.archivo.test'");
		} else {
			rutaServidor = "//" + AtuxUtility.getIPLocal() + "/";
		}

		GenerateNameFile generateNameFile = new GenerateNameFile(BaseConexion.getConexion(), (Integer.valueOf(codInterface)).intValue(), "", AtuxVariables.vCodigoLocal);
		nombreArchivo = generateNameFile.getNameFile();

		WriteFormat writeFormat = new WriteFormat(BaseConexion.getConexion(), AtuxVariables.vCodigoLocal, rutaServidor + rutaArchivo, nombreArchivo, procCabecera,
				AtuxUtility.replaceString(paramCabecera, "NAME_FILE", nombreArchivo), procDetalle, paramDetalle, procCondicion, paramCondicion, rutaServidor + rutaXML, procIdentificador, maxLines,
				AtuxVariables.vModoTest);
		writeFormat.setWithCommit(withCommit);
		writeFormat.imprimir();
	}

	public boolean isWithCommit() {
		return withCommit;
	}

	public void setWithCommit(boolean withCommit) {
		this.withCommit = withCommit;
	}

}
