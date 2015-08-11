package atux.util.common.cashdrawer;

import atux.util.common.AtuxVariables;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;

import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;
import jpos.CashDrawer;
import jpos.JposException;
import jpos.CashDrawerConst;


public class EckerdCashDrawerManager implements StatusUpdateListener {

    private final Log logger = LogFactory.getLog(EckerdCashDrawerManager.class);
    private static EckerdCashDrawerManager instance = null;

    //Inicio ID: 001
    CashDrawer cashDrawer = new CashDrawer();;
    //Fin ID: 001

    /**
     * Creates a new instance of ManageCashDrawer
     */
    private EckerdCashDrawerManager() {
    }

    public static EckerdCashDrawerManager instance() throws EckerdCashDrawerException {
        if (instance == null) {
            instance = new EckerdCashDrawerManager();

            //Inicio ID: 001
            instance.openCashDriver();
            //Fin ID: 001
        }
        return instance;
    }

    public void abrirCashDrawer() throws EckerdCashDrawerException {
        String modelo = AtuxVariables.vModelCashDrawer;

        if (modelo == null || modelo.equals(""))
            throw new EckerdCashDrawerException("No existe modelo para la caja electronica");

        //Inicio ID: 001
        //CashDrawer cashDrawer = new CashDrawer();
        //Fin ID: 001

        try {
            logger.info("Modelo Cash Drawer:" + modelo);
            // le asigno el tipo de caja electronica

            //Inicio ID: 001
            //cashDrawer.addStatusUpdateListener(this);
            //cashDrawer.open(modelo);
            //Fin ID: 001

            cashDrawer.claim(1);
            cashDrawer.setDeviceEnabled(true);

            // chekeamos si la caja electronica esta abierta
            if (cashDrawer.getDrawerOpened()) {
                logger.info("La caja electronica ya esta abierta:" + modelo);
                JOptionPane.showMessageDialog(null, "La caja ya esta abierta,por favor proceda a cerrarla",
                        "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                try {
                    if (!cashDrawer.getDrawerOpened()) {
                        cashDrawer.openDrawer();
                    }
                }
                catch (JposException ex) {
                    logger.error("Fallo en abrir la caja,verificar." + ex.getMessage());
                }
            }

            // abrimos la caja electronica
            cashDrawer.openDrawer();
            logger.info("Se abrio la gabeta electronica");
        } catch (JposException ex) {
            logger.error("Fallo en abrir la caja \"" + modelo + "\" Exception: " + ex.getMessage());
            throw new EckerdCashDrawerException("Fallo en abrir");
        }

        //Inicio ID: 001
        /*finally {
            try {
                cashDrawer.close();
            } catch (JposException ex) {
                logger.error("Fallo al cerrar la caja\"" + modelo + "\" Exception: " + ex.getMessage());
                throw new EckerdCashDrawerException("Fallo al cerrar");
            }
        }*/
        //Fin ID: 001
    }

    public void statusUpdateOccurred(StatusUpdateEvent sue) {
        String msg = "Actualizar el estado del Evento: ";
        switch (sue.getStatus()) {
            case CashDrawerConst.CASH_SUE_DRAWERCLOSED:
                msg += "Drawer Closed\n";
                break;
            case CashDrawerConst.CASH_SUE_DRAWEROPEN:
                msg += "Drawer Opened\n";
                break;
            default:
                msg += "Unknown Status: " + Integer.toString(sue.getStatus()) + "\n";
                break;
        }
        logger.info("MSG STATUS DRAWER:" + msg);
    }

    //Inicio ID: 001
    public void closeCashDriver() throws EckerdCashDrawerException {
        try {
            cashDrawer.close();
        } catch (JposException ex) {
            logger.error("Fallo al cerrar la caja\"" + AtuxVariables.vModelCashDrawer + "\" Exception: " + ex.getMessage());
            throw new EckerdCashDrawerException("Fallo al cerrar");
        }
    }

    public void openCashDriver() throws EckerdCashDrawerException {
        try {
            cashDrawer.open(AtuxVariables.vModelCashDrawer);
            cashDrawer.addStatusUpdateListener(this);
        } catch (JposException ex) {
            logger.error("Fallo al configurar la caja\"" + AtuxVariables.vModelCashDrawer + "\" Exception: " + ex.getMessage());
            throw new EckerdCashDrawerException("Fallo al configurar");
        }
    }

    //Fin ID: 001

}
