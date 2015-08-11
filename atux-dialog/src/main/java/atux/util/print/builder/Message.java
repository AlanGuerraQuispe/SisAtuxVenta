package atux.util.print.builder;

import atux.controllers.repository.AtuxException;
import atux.util.common.AtuxUtility;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Message {
    private String coMessage;
    private List listaLineas;

    public Message(String coMessage) {
        this.coMessage = coMessage;
        listaLineas = new ArrayList();

    }

    public void readFileProperties() {
        try {
            Properties properties = null;
            String file= this.getClass().getClassLoader().getResource("mensajesMoviles.properties").getFile();
            DataInputStream archivo = new DataInputStream(new FileInputStream(file));

            if (archivo != null) {
                properties = new Properties();
                properties.load(archivo);
                String propertyNumLineas = coMessage + "_LINEAS";
                int lineas = AtuxUtility.trunc(properties.getProperty(propertyNumLineas));
                String properyLinea = "";
                for (int i = 1; i <= lineas; i++) {
                    properyLinea = coMessage + i;
                    listaLineas.add(properties.getProperty(properyLinea));
                }
            }

        } catch (FileNotFoundException fnfException) {
            fnfException.printStackTrace();
            throw new AtuxException("El archivo de mensajes no fue encontrado ");
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new AtuxException("Error al leer archivo de mensajes ");
        }
    }

    public List getListaLineasCustom(int espacios) {
        List lista = new ArrayList();
        StringBuffer str = new StringBuffer();
        int var = 0;
        int aux = 0;
        for (int i = 0; i < listaLineas.size(); i++) {
            String tmp = (String) listaLineas.get(i);
            aux = (tmp.length()+1) + var;
            if (aux <= espacios) {
                str.append(i==0?"":" ");
                str.append(tmp);
                var += tmp.length()+1;
            } else {
                lista.add(str.toString());
                str = new StringBuffer();
                str.append(tmp);
                var = (tmp.length()+1);
            }

        }
        if(str.length()>0)
            lista.add(str.toString());

        return  lista;

    }

    public int getCantidadLineasCustom(int espacios){
        return getListaLineasCustom(espacios).size();
    }
                                                                      
    public static void main(String[] args) {
        Message message=new Message("HABLA_SYMPATICO");
        message.readFileProperties();
        message.getListaLineasCustom(140);

    }
    public List getListaLineas() {
        return listaLineas;
    }

    public void setListaLineas(List listaLineas) {
        this.listaLineas = listaLineas;
    }
}
