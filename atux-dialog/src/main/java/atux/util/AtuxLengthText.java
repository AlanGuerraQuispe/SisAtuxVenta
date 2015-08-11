package atux.util;

import javax.swing.text.*;

/**
*
*/
public class AtuxLengthText extends javax.swing.text.PlainDocument {

   /** Almacena la longitud m�xima del contenido del Objeto JTextField */
   int maxLength;

  /**
  *Constructor : Inicializa la longitud del Objeto JTextField.
  *@param <b>maxLength</b> Longitud m�xima del contenido del Objeto.
  */
   public AtuxLengthText(int pMaxLength) {		
      super();		
      this.maxLength = pMaxLength;
   }	

   /** 
   *Permite el ingreso de caracteres controlando no sobrepasar la longitud
   *m�xima del contenido del Objeto.
   *@param <b>pOffSet</b>
   *@param <b>pStr</b>
   *@param <b>pAttribute</b>
   */
    @Override
   public void insertString(int pOffSet, String pStr, AttributeSet pAttribute) {
      if(getLength() < maxLength)
         try {
            super.insertString(pOffSet,pStr,pAttribute);
         }
         catch(Exception e){}
   }

}