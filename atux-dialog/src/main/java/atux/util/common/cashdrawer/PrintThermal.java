package atux.util.common.cashdrawer;

import jpos.POSPrinter;
import jpos.JposException;
import jpos.events.StatusUpdateListener;
import jpos.events.StatusUpdateEvent;


public class PrintThermal implements StatusUpdateListener {

    private POSPrinter printer = new POSPrinter();

    public void statusUpdateOccurred(StatusUpdateEvent event) {
        
    }

    public PrintThermal() {
        
    }

    public void openPort() {
        try {
            printer.open("TSP100");
            printer.printNormal(0, "Texto Normal");
        } catch (JposException e) {
            e.printStackTrace();
        }
    }
}
