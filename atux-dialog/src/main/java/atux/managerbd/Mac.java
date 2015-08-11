/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atux.managerbd;


import atux.controllers.CMedioControlAcceso;
import atux.modelbd.MedioControlAcceso;
import atux.vistas.catalogo.ISerieValidacion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author ABC
 */
public class Mac {
    private CMedioControlAcceso cp;
    private boolean StatusValidacion;
    
    public void VisualizaVentana(javax.swing.JFrame Ventana){
        boolean Valida = ObtenerMAC();
        if (Valida == false) {
            ISerieValidacion panelBronce = new ISerieValidacion(Ventana);
            panelBronce.setLocationRelativeTo(null);
            panelBronce.setVisible(true);    
        }        
    }

    public boolean ObtenerMAC() {
        String Direccion;
        Direccion=getMAC();
        return ValidaDireccion(Direccion);
    }
    
    public boolean ValidaDireccion(String Direccion){
        MedioControlAcceso MAC = new  MedioControlAcceso();
        cp = new CMedioControlAcceso();
        MAC = cp.getRegistroPorPk(new Object[]{Direccion});
        if (MAC == null){
            return false;
        }
            
        if (MAC.getDeCodigoHDD() == null){
            return false;
        }
        return true;
    }


    
    public String getMAC(){
        NetworkInterface a; 
        try {
            a = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            byte[] mac = a.getHardwareAddress();
   
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));  
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return "";        
    }    

    public String getSerialNumberHDD() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto",".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                       + "Set colDrives = objFSO.Drives\n"
                       + "Set objDrive = colDrives.item(\"" + "C" + "\")\n"
                       + "Wscript.Echo objDrive.SerialNumber";  // see note
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader (new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.trim();
    }    

    public void getValidaSerie(JFrame VtaPrincipal, String RazonSocial,String CodigoLocal, String SerieIngresada, int NumeroTerminal){
        String SerieGenerada = getGeneraSerie(RazonSocial, CodigoLocal, NumeroTerminal);
        if (!SerieGenerada.equals(SerieIngresada)){
            VtaPrincipal.setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(null, "Error en la validacion del numero de Serie.\nFavor Comunicarse con Atux Perú EIRL", "Error en Validacion del Sistema",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/atux/resources/CaritaFeliz.jpg"));
            this.StatusValidacion= false;
            System.exit(0);
        }else{
            this.StatusValidacion= true;
        }
    }
    
    public String getGeneraSerie(String RazonSocial,String CodigoLocal, int NumeroTerminal) {
        String Caracter="";
        String NuevoNombre="";
        String NuevaSerie="";
        
        int CodigoAutomatico = Integer.parseInt(CodigoLocal)+NumeroTerminal;

        for (int i = 0; i < RazonSocial.length(); i++) {
            NuevoNombre=NuevoNombre + RazonSocial.substring(i, i+1) + CodigoAutomatico;
        }
        
        for (int i = 0; i < NuevoNombre.length(); i++) {
            Caracter = NuevoNombre.substring(i, i+1);
            int Ascci =  Caracter.codePointAt(0)+CodigoAutomatico;
            NuevaSerie = NuevaSerie +  String.valueOf(Ascci) ;
        }
        return NuevaSerie ;
    }

    public boolean getStatusValidacion() {
        return StatusValidacion;
    }

    public void setStatusValidacion(boolean StatusValidacion) {
        this.StatusValidacion = StatusValidacion;
    }
    
    
}

