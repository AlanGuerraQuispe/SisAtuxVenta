package com.atux.desktop.principal;


import com.aw.desktop.spring.security.SiderSecurityCodeManager;
import com.aw.swing.mvp.security.SecurityCodeManager;
import com.sun.animation.transitions.ScreenTransition;
import com.sun.animation.transitions.TransitionTarget;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.fuse.InjectedResource;
import org.jdesktop.fuse.ResourceInjector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * User: Juan Carlos Vergara
 * Date: 11/12/2009
 */
public class TransitionPanel extends JPanel {
    protected static final Log logger = LogFactory.getLog(TransitionPanel.class);

    private static final int TRANSITION_TIME_IN_MILLIS = 500;
    private final JPanel content;
    private String currentScreen;

    SecurityCodeManager securityCodeSetter;

    private final ScreenTransition screenTransition;

    MenuPanelManager menuPanelManager;

    @InjectedResource
    private BufferedImage close;
    @InjectedResource
    private BufferedImage closeInactive;
    @InjectedResource
    private BufferedImage closeOver;
    @InjectedResource
    private BufferedImage closePressed;
    @InjectedResource
    private BufferedImage minimize;
    @InjectedResource
    private BufferedImage minimizeInactive;
    @InjectedResource
    private BufferedImage minimizeOver;
    @InjectedResource
    private BufferedImage minimizePressed;

    TransitionPanel(final NavigationHeader navigationHeader) {
        super(new BorderLayout());
        ResourceInjector.get().inject(this);
        
        setOpaque(false);

        content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setOpaque(false);

//        menuPanelManager = new MenuPanelManager();
//        menuPanelManager.createPanels(createMenuComponents());
//        content.add(menuPanelManager.getPanel(MainFrame.PRINCIPAL), BorderLayout.CENTER);

        JPanel northPanel = new NavigationPanel();
        northPanel.setLayout(new BorderLayout());

        JButton closeButton = createButton(new CloseAction(), close, closePressed, closeOver);

        JButton iconifyButton = createButton(new IconifyAction(), minimize, minimizePressed, minimizeOver);

        JPanel buttonsPanel = new NavigationPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(iconifyButton);
        buttonsPanel.add(closeButton);

        northPanel.add(navigationHeader, BorderLayout.WEST);
        northPanel.add(buttonsPanel, BorderLayout.EAST);

//        add(navigationHeader, BorderLayout.NORTH);
        add(northPanel, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

        currentScreen = MainFrame.PRINCIPAL;

        screenTransition = new ScreenTransition(content, new ContentTransitionTarget());
    }

    public void createPanelManager(){
        if (menuPanelManager == null){
            menuPanelManager = new MenuPanelManager();
            securityCodeSetter
                    = com.aw.swing.spring.Application.instance().getBean(SiderSecurityCodeManager.class);
            menuPanelManager = new MenuPanelManager();
            menuPanelManager.createPanels(createMenuComponents());
            content.add(menuPanelManager.getPanel(MainFrame.PRINCIPAL), BorderLayout.CENTER);
        }
    }

    private class CloseAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            close();
        }
    }

    private class IconifyAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            iconify();
        }
    }    

    private void close() {
        Window w = SwingUtilities.getWindowAncestor(this);
        w.dispatchEvent(new WindowEvent(w,
                                        WindowEvent.WINDOW_CLOSING));
    }

    private void iconify() {
        Frame frame = (Frame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setExtendedState(frame.getExtendedState() | Frame.ICONIFIED);
        }
    }    

    private static JButton createButton(final AbstractAction action,
                                 final BufferedImage image,
                                 final Image pressedImage,
                                 final Image overImage) {
        JButton button = new JButton(action);
        button.setIcon(new ImageIcon(image));
        button.setPressedIcon(new ImageIcon(pressedImage));
        button.setRolloverIcon(new ImageIcon(overImage));
        button.setRolloverEnabled(true);
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(image.getWidth(),
                                              image.getHeight()));
        return button;
    }

    /**
     * Retorna las opciones del menu
     * @return
     */
    private MenuPanelConfig createMenuComponents(){
            return createStaticMenu();
    }





//    private void createMenuDepends(MenuItem menuItem, com.aw.core.security.resources.Menu menuPadre) {
//        java.util.List subMenus = menuPadre.getSubMenus();
//
//        for (Iterator iterator = subMenus.iterator(); iterator.hasNext();) {
//            com.aw.core.security.resources.Menu menu = (com.aw.core.security.resources.Menu) iterator.next();
//            MenuItem subMenuItem = menuItem.add(securityCodeSetter.getPresenterClass(menu.getCoVentana()), menu.getNoMenu());
//            createMenuDepends(subMenuItem, menu);
//        }
//    }
    

    private MenuPanelConfig createStaticMenu() {
      /*  MenuPanelConfig principal = new MenuPanelConfig("Principal");

        MenuPanelConfig pnlProveedores = new MenuPanelConfig("Proveedores");
        MenuPanelItem proveedores = new MenuPanelItem(FNProveedorPst.class, "Lista de Proveedores");
        MenuPanelItem criterios = new MenuPanelItem(FNCriterioProveedorPst.class, "Criterios de Evaluación");
        pnlProveedores.add(proveedores);
        pnlProveedores.add(criterios);
        principal.add(pnlProveedores);

        MenuPanelConfig pnlProductos = new MenuPanelConfig("Productos");
        MenuPanelItem solCat = new MenuPanelItem(FNSolicitudPst.class, "Solicitud de Catalogación");
        MenuPanelItem catProd = new MenuPanelItem(FNProducto2Pst.class, "Catálogo de Productos");
        pnlProductos.add(solCat);
        pnlProductos.add(catProd);
        principal.add(pnlProductos);

        MenuPanelConfig pnlReqCompras = new MenuPanelConfig("Requisición de Compras");
        MenuPanelItem carrito = new MenuPanelItem(FNCarritoComprasPst.class, "Carrito de Compras");
        MenuPanelItem requisicion = new MenuPanelItem(FNRequisicionCompraPst.class, "Requisición de Compras");
        MenuPanelItem reqPlaner = new MenuPanelItem(FNRequisicionCompraPlanerPst.class, "Requisición de Compras (Suministros)");
        pnlReqCompras.add(carrito);
        pnlReqCompras.add(requisicion);
        pnlReqCompras.add(reqPlaner);
        principal.add(pnlReqCompras);

        MenuPanelConfig pnlCompras = new MenuPanelConfig("Compras");
        MenuPanelItem pendCompra = new MenuPanelItem(FNPendientesCompraPst.class, "Pendientes de compra");
        MenuPanelItem solCot = new MenuPanelItem(FNSolicitudCotizacionPst.class, "Solicitud de Cotización");
        MenuPanelItem coti = new MenuPanelItem(FNCotizacionPst.class, "Cotización");
        MenuPanelItem cuadroComp = new MenuPanelItem(FNCuadroComparativoPst.class, "Cuadro Comparativo");
        MenuPanelItem ordenCompra = new MenuPanelItem(FNOrdenCompraPst.class, "Orden de Compra");
        MenuPanelItem cronEntrega = new MenuPanelItem(FNCronogramaEntregaPst.class, "Cronograma Entrega");
        MenuPanelItem cronPago = new MenuPanelItem(FNCronogramaPagoPst.class, "Cronograma Pago");
        MenuPanelItem agente = new MenuPanelItem(FNAgentePst.class, "Agente");
        MenuPanelItem asignacionAgente = new MenuPanelItem(FNAsignacionAgenteRCPst.class, "Asignación de Agentes a Items de RC");
        MenuPanelItem alertaPlanif = new MenuPanelItem(FNAlertaPlanificadorPst.class, "Alerta Planificador");
        pnlCompras.add(pendCompra);
        pnlCompras.add(solCot);
        pnlCompras.add(coti);
        pnlCompras.add(cuadroComp);
        pnlCompras.add(ordenCompra);
        pnlCompras.add(cronEntrega);
        pnlCompras.add(cronPago);
        pnlCompras.add(agente);
        pnlCompras.add(asignacionAgente);
        pnlCompras.add(alertaPlanif);
        principal.add(pnlCompras);

        MenuPanelConfig pnlAlmacen = new MenuPanelConfig("Almacén");
        MenuPanelItem ocPenLLegada = new MenuPanelItem(FNOrdenesCompraPst.class, "OC Pendientes de Llegada");
        MenuPanelItem llegadaMat = new MenuPanelItem(FNLlegadasMaterialesPst.class, "Bandeja de Llegadas");
        MenuPanelItem notasDev = new MenuPanelItem(FNNotasDevolucionPst.class, "Devolución al Proveedor");
        MenuPanelItem banSolConfTec = new MenuPanelItem(FNConformidadTecnicaPst.class, "Bandeja de Solicitudes de CT");
        MenuPanelItem banNoConformidad = new MenuPanelItem(FNNoConformidadPst.class, "Bandeja de No Conformidad");
        MenuPanelItem banIngresos = new MenuPanelItem(FNIngresoMaterialesPst.class, "Bandeja de Ingresos");
        MenuPanelItem almacenes = new MenuPanelItem(FNAlmacenPst.class, "Almacenes");
        MenuPanelItem notasSalida = new MenuPanelItem(FNNotaSalidaPst.class, "Notas Salida");
        MenuPanelItem delivery = new MenuPanelItem(FNDeliveryPst.class, "Delivery");
        MenuPanelItem almacDevolucion = new MenuPanelItem(FNAlmacenDevolucionPst.class, "Devolución Usuario");
        MenuPanelItem transf = new MenuPanelItem(FNAlmacenTransferenciaPst.class, "Transferencia");
        MenuPanelItem cierreConsig = new MenuPanelItem(FNCierreConsignacionPst.class, "Cierre Consignación");
        pnlAlmacen.add(ocPenLLegada);
        pnlAlmacen.add(llegadaMat);
        pnlAlmacen.add(notasDev);
        pnlAlmacen.add(banSolConfTec);
        pnlAlmacen.add(banNoConformidad);
        pnlAlmacen.add(banIngresos);
        pnlAlmacen.add(almacenes);
        pnlAlmacen.add(notasSalida);
        pnlAlmacen.add(delivery);
        pnlAlmacen.add(almacDevolucion);
        pnlAlmacen.add(transf);
        pnlAlmacen.add(cierreConsig);
        principal.add(pnlAlmacen);*/

/*        MenuPanelConfig pnlDocumentos = new MenuPanelConfig("Documentos");
        MenuPanelItem banAprob = new MenuPanelItem(FNBandejaAprobacionPst.class, "Bandeja Aprobación");
        pnlDocumentos.add(banAprob);
        principal.add(pnlDocumentos);

        MenuPanelConfig pnlMaestros = new MenuPanelConfig("Maestros");

        MenuPanelConfig grupoMenu = new MenuPanelConfig("Grupo Menu");
        grupoMenu.add(new MenuPanelItem(FNEspecificacionTecnicaPst.class, "Especificaciones Técnicas"));
        grupoMenu.add(new MenuPanelItem(FNFamiliasPst.class, "Familia"));
        pnlMaestros.add(grupoMenu);

        pnlMaestros.add(new MenuPanelItem(TablaMultiplePst.class, "Tabla Maestra"));
        pnlMaestros.add(new MenuPanelItem(FNOrganizacionCompraPst.class, "Organización de Compra"));
        pnlMaestros.add(new MenuPanelItem(FNMotivoRechazoPst.class, "Motivos de Rechazo por Documento"));
        pnlMaestros.add(new MenuPanelItem(FNGruposProductosPst.class, "Grupo Productos"));
        pnlMaestros.add(new MenuPanelItem(FNDesarrolloCompradoresPst.class, "Desarrollo de Compradores"));
        pnlMaestros.add(new MenuPanelItem(FNEquiposPst.class, "Equipo"));
        pnlMaestros.add(new MenuPanelItem(FNProyectoPst.class, "Proyecto"));
        pnlMaestros.add(new MenuPanelItem(FNUnidadMedidaPst.class, "Unidad de Medida"));
        pnlMaestros.add(new MenuPanelItem(FNParametroPst.class, "Parámetros"));
        pnlMaestros.add(new MenuPanelItem(FNPuntoEntregaPst.class, "Punto de Entrega"));
        pnlMaestros.add(new MenuPanelItem(FNCertificadoPst.class, "Certificado"));
        pnlMaestros.add(new MenuPanelItem(FNFormaPagoPst.class, "Forma de Pago"));
        pnlMaestros.add(new MenuPanelItem(FNModuloPst.class, "Módulo y Numeración"));
        pnlMaestros.add(new MenuPanelItem(FNImpuestoPst.class, "Impuesto"));
        pnlMaestros.add(new MenuPanelItem(FNRubroPst.class, "Rubro"));
        pnlMaestros.add(new MenuPanelItem(FNOperacionNegocioPst.class, "Sucursal"));
        pnlMaestros.add(new MenuPanelItem(FNTerminosCondicionesPst.class, "Términos y Condiciones"));
        pnlMaestros.add(new MenuPanelItem(FNArchivoTipoPst.class, "Archivo Tipo"));*/
//        principal.add(pnlMaestros);

//        if (SPSysProp.isDeveloperDebugEnabled())
//            menues.add(createMenu(mb, "Pruebas en desarrollo")
//                    .add(NuevoCorreoPst.class, "Envio Correo")
//                    .add(VerificarInfraestructuraPst.class, "Verificar Infraestructura"));

        return null;
    }

    void setContentVisible(boolean visible) {
        content.setVisible(visible);
    }

    void showMainOptionsPanel() {
        startTransition("Principal");
    }

    void activateMenu(String name){
        startTransition(name);
    }

    private void startTransition(String screenName){
        if (!screenName.equals(currentScreen)) {
            logger.debug("Starting screen transition...");
            currentScreen = screenName;
            screenTransition.startTransition(TRANSITION_TIME_IN_MILLIS);
        }
    }

    private class ContentTransitionTarget implements TransitionTarget {
        public void resetCurrentScreen() {
            content.removeAll();
        }

        public void setupNextScreen() {
            JPanel panel = menuPanelManager.getPanel(currentScreen);
            content.add(panel, BorderLayout.CENTER);
        }

        public void transitionComplete() {
            logger.debug("Screen transition done...");
        }
    }
}
