package atux.core;

import java.io.FileInputStream;


public class DatoArchivo {
    
    private FileInputStream fis;
    private int longitud;

    public DatoArchivo(FileInputStream fis,int longitud) {
        this.fis= fis;
        this.longitud = longitud; 
    }

    public FileInputStream getFis() {
        return fis;
    }
    public int getLongitud() {
        return longitud;
    }
    
}
