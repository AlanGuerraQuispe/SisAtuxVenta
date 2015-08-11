package atux.trasladoproducto.reference;

/**
 * Copyright (c) 2010 Eckerd Pero S.A.<br>
 * <br>
 * Funcionalidad : Clase usada para hacer validaciones especoficas <br>
 * <br>
 * Historico de Creacion/Modificacion<br>
 * <br>
 * ID    PROGRAMADOR    FECHA/HORA            TIPO                 OBSERVACIoN
 * 000    GMATEO       10/03/2010 10:00:00   Creacion <br>
 * <br>
 *
 * @version 1.0<br>
 */
public abstract class CustomValidator {

    private String msg;

    protected CustomValidator(String msg) {
        this.msg = msg;
    }

    public abstract boolean validate(int cantidad);

    public String getMsg() {
        return msg;
    }
}
