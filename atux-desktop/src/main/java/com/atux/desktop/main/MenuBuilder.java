package com.atux.desktop.main;

import atux.inventario.DlgGuiaIngreso;
import atux.trasladoproducto.DlgListaPedidoTraslado;
import atux.trasladoproducto.DlgPedidoTraslado;
import atux.trasladoproducto.DlgRecepGuias;
import atux.vistas.catalogo.*;
import atux.vistas.inventario.IListaPedidosReposicion;
import atux.vistas.inventario.IPedidoReposicion;
import atux.vistas.utilitario.ICambiarUsuario;
import atux.vistas.utilitario.ICorrelativoSis;
import atux.vistas.venta.*;
import atux.vistas.venta.aperCierre.IAperturaCaja;
import atux.vistas.venta.aperCierre.IArqueosDeCaja;
import atux.vistas.venta.aperCierre.ICierreCajaTurno;
import atux.vistas.venta.caja.ICobroPedido;
import com.atux.desktop.consulta.SeleccionarDetalleVentaPst;
import com.atux.desktop.consulta.SeleccionarVentaDiaPst;
import com.atux.desktop.consulta.SeleccionarVentaTransaccionPst;
import com.atux.desktop.consulta.SeleccionarVentaVendedorPst;
import com.atux.desktop.donacion.FNDonacionPst;
import com.atux.desktop.inventario.*;
import com.atux.desktop.precios.*;
import com.atux.desktop.promocion.FNPromocionPst;
import com.aw.core.format.FillerFormat;
import com.aw.swing.mvp.action.ActionMenuItem;
import com.aw.swing.mvp.security.SecurityCodeManager;
import com.aw.swing.mvp.util.SwingEmptyPstAction;
import com.aw.swing.mvp.util.SwingShowDialogAction;
import com.aw.swing.mvp.util.SwingShowJDialogAction;
import com.aw.swing.mvp.util.SwingShowPstAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: Juan Carlos Vergara
 * Date: 19/05/2009
 */
public class MenuBuilder {
    protected final Log logger = LogFactory.getLog(getClass());

    String label;
    Class pst;
    SecurityCodeManager securityCodeSetter;
    List<MenuItem> menues = new ArrayList<MenuItem>();

    public JMenuBar createMenus() {
        JMenuBar mb = new JMenuBar();

        mb.setBackground(new java.awt.Color(79, 129, 189));
        mb.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        MenuItem ventas = createMenu(mb, 'V',new javax.swing.ImageIcon(getClass().getResource("/atux/resources/menuVentas.png")))
                .addJInternalFrame(IPedidoVenta.class, "Realizar Venta")
                .addJInternalFrame(IPedidoVentaInsumo.class, "Productos terminados")
                .addJInternalFrame(ICompletarPedidoVenta.class, "Completar Ventas")
                .addJInternalFrame(IConsultarVentas.class, "Consultar Ventas")
                .addJInternalFrame(IAnularComprobante.class, "Devolución")
                .addJInternalFrame(IConsultarPedidosACredito.class, "Pedidos a Crédito")
                ;

        createMenu(ventas.getMenu(),"Consulta")
                .addPst(SeleccionarDetalleVentaPst.class, "Detalle de Venta")
                .addPst(SeleccionarVentaTransaccionPst.class, "Venta x Transacción")
                .addPst(SeleccionarVentaDiaPst.class, "Venta x Día")
                .addPst(SeleccionarVentaVendedorPst.class, "Venta x Vendedor")
                ;

        ventas.addJInternalFrame(ICobroPedido.class, "Caja")
                .addJInternalFrame(IAperturaCaja.class, "Apertura Caja")
        ;

        createMenu(ventas.getMenu(), "Cierre de Caja")
                .addJDialog(IArqueosDeCaja.class, "Arqueos")
                .addJInternalFrame(ICierreCajaTurno.class, "Cierre de Caja Turno")
                ;

        MenuItem   inventario = createMenu(mb, 'I',new javax.swing.ImageIcon(getClass().getResource("/atux/resources/menuInventarios.png")))
                .addJDialog(DlgGuiaIngreso.class, "Ingreso Manual")
                .addJDialog(DlgPedidoTraslado.class, "Pedido de Traslado")
                .addJDialog(DlgListaPedidoTraslado.class, "Lista de Traslados")
                .addJDialog(DlgRecepGuias.class, "Recepción de Productos")
                .add(FNAjusteStockPst.class, "Ajuste Stock", true)
                .add(FNKardexPst.class, "Kardex",true)
                .add(FNRotacionPst.class, "Rotación",true)
                .addJInternalFrame(IPedidoReposicion.class, "Ped. Reposición")
                .addJInternalFrame(IListaPedidosReposicion.class, "Lista Reposición")
        ;
        createMenu(inventario.getMenu(), "Toma Inventario")
                .addPst(FNLaboratorioInventarioPst.class, "Iniciar Toma")
                .addPst(FNTomaInventarioPst.class, "Toma")
                .addPst(FNTomaInventarioPst.class, "Historico")
        ;

        MenuItem catalogos = createMenu(mb, 'C', new javax.swing.ImageIcon(getClass().getResource("/atux/resources/menuCatalogos.png")))
                .addJInternalFrame(ICompania.class, "Compañías")
                .addJInternalFrame(IMaestroProductos.class, "Productos")
                .addJInternalFrame(ILocales.class, "Locales")
                .addJInternalFrame(IProveedores.class, "Proveedores")
                .addJInternalFrame(ILaboratorio.class, "Laboratorios")
                .addJInternalFrame(IVendedores.class, "Vendedores")
                .addJInternalFrame(IClientes.class, "Clientes")
                .addJInternalFrame(IImpuestoIGV.class, "IGV")
                .addJInternalFrame(ITipoDeCambio.class, "Tipo de Cambio")
                .addPst(FNPromocionPst.class, "Promociones")
                .addPst(FNDonacionPst.class, "Donaciones")
                .addJInternalFrame(IFormaPago.class, "Formas de Pago");
        
         catalogos.addJInternalFrame(IPrincipioActivo.class, "Principio Activo")
                 .addJInternalFrame(IMedicos.class, "Médicos")
                ;
         
        createMenu(catalogos.getMenu(),"Set Categorias")
                .addJInternalFrame(IG1_LineaComercial.class, "G1- Linea")
                .addJInternalFrame(IG2_Division.class, "G2- División")
                .addJInternalFrame(IG3_SubDivision.class, "G3- Sub.División")
                .addJInternalFrame(IG4_Familia.class, "G4- Familia")
                .addJInternalFrame(IG5_SubFamilia.class, "G5- Sub.Familia")
                ;        

        createMenu(mb, 'P',new javax.swing.ImageIcon(getClass().getResource("/atux/resources/menuPrecios.png")))
                .addPst(FNLocalPreciosPst.class, "Precios")
                .addPst(FNProveedorPrecioPst.class, "Precios-Proveedor")
                .addPst(FNAjustePrecioPst.class, "Descuento General")
                .addPst(FNLaboratorioDescuentoPst.class, "Descuento x Laboratorio")
                .addPst(FNLaboratorioIncentivoPst.class, "Incentivos")
                .addPst(FNLaboratorioExhibicionPst.class, "Min. Exhibición")
        ;

        createMenu(mb, 'U', new javax.swing.ImageIcon(getClass().getResource("/atux/resources/menuUtilitarios.png")))
                .addJInternalFrame(ICorrelativoSis.class, "Correlativos")
                //.addJInternalFrame(ICambiarUsuario.class, "Cambio Usuario")
        ;

        return mb;
    }


    public static void main(String[] args) {
        MenuBuilder m = new MenuBuilder();
        m.createMenus();
    }

    private void asignarMnemonics(List<? extends JMenuItem> menus, Character usedLetter) {
        List<Character> usedChars = new ArrayList<Character>();
        if (usedLetter != null) usedChars.add(usedLetter);
        for (JMenuItem menu : menus) {
            String title = menu.getText();

            String[] words = title.split(" ");
            // poner primera letra de cada palabra primero
            List<Character> avalaibleChars = new ArrayList<Character>();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                avalaibleChars.add(word.charAt(0));
                words[i] = words[i].substring(1);
            }
            //poner el resto al final
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                char[] wordChars = word.toCharArray();
                for (char wordChar : wordChars) {
                    avalaibleChars.add(wordChar);
                }
            }

            avalaibleChars.removeAll(usedChars);

            char choosedChar = avalaibleChars.get(0);
            menu.setMnemonic(choosedChar);
            usedChars.add(choosedChar);
        }

    }

    private MenuItem createMenu(JMenuBar mb, String name) {
        return new MenuItem(mb, name);
    }

    private MenuItem createMenu(JMenuBar mb, char nemonic,Icon icono) {
        return new MenuItem(mb, nemonic,icono);
    }

    private MenuItem createMenu(JMenu mb, String name) {
        return new MenuItem(mb, name);
    }

    public void crearXml() {


        FillerFormat filler = new FillerFormat('0', 5);
        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("aplicacion");
            document.appendChild(rootElement);

            int numMenu = 1;
            for (Iterator<MenuItem> menuItemIterator = menues.iterator(); menuItemIterator.hasNext(); ) {
                MenuItem menuItem = menuItemIterator.next();
                System.out.println(menuItem.name + " " + menuItem.menu.getItemCount());
                String coMenuPadre = "MEN" + filler.format(numMenu);
                Element itemPadre = document.createElement("menuItem");
                itemPadre.setAttribute("codigo", coMenuPadre);
                itemPadre.setAttribute("nombre", menuItem.name);
                numMenu++;
                for (int i = 0; i < menuItem.menu.getItemCount(); i++) {
                    Element item = document.createElement("menuItem");
                    item.setAttribute("codigo", "MEN" + filler.format(numMenu));
                    item.setAttribute("nombre", menuItem.menu.getItem(i).getText());

                    item.setAttribute("coVentana", securityCodeSetter.getSecurityCode((String) menuItem.menu.getItem(i).getClientProperty("class")));
                    item.setAttribute("coMenuPadre", coMenuPadre);
                    System.out.println(" -->  " + menuItem.menu.getItem(i).getText() + " " + menuItem.menu.getItem(i).getClientProperty("class") + " code ->" + securityCodeSetter.getSecurityCode((String) menuItem.menu.getItem(i).getClientProperty("class")) + "<--");
                    itemPadre.appendChild(item);
                    numMenu++;
                }
                rootElement.appendChild(itemPadre);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            FileWriter outFilex = new FileWriter("dataMenu.xml");
            PrintWriter out = new PrintWriter(outFilex);

            StreamResult result = new StreamResult(out);
            transformer.transform(source, result);
            System.out.println(transformer.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class MenuItem {
        String name;
        JMenu menu;

        private MenuItem(JMenuBar mb, String name) {
            menu = new JMenu(name);
            mb.add(menu);
            this.name = name;
        }

        private MenuItem(JMenuBar mb, char nemonic,Icon icono) {
            if(icono!=null){
                menu = new JMenu();
                menu.setIcon(icono);
            }else{
                menu = new JMenu(name);
            }
            menu.setMnemonic(nemonic);
            mb.add(menu);
            this.name = name;
        }

        private MenuItem(JMenu menuPadre, String name) {
            menu = new JMenu(name);
            menuPadre.add(menu);
            this.name = name;
        }


        public JMenu getMenu() {
            return menu;
        }

        protected MenuItem addPst(Class pst, String label) {
            ActionMenuItem jxc=null;
            if(pst==null){
                jxc = new ActionMenuItem(new SwingEmptyPstAction(label));
                jxc.putClientProperty("class", label);
            }else{
                jxc = new ActionMenuItem(new SwingShowPstAction(pst, label));
                jxc.putClientProperty("class", pst.getSimpleName());
            }
            menu.add(jxc);

            return this;
        }

        protected MenuItem addJInternalFrame(Class pst, String label) {
            ActionMenuItem jxc = new ActionMenuItem(new SwingShowDialogAction(pst, label));
            jxc.putClientProperty("class", pst.getSimpleName());
            menu.add(jxc);

            return this;
        }

        protected MenuItem addJDialog(Class pst, String label) {
            ActionMenuItem jxc = new ActionMenuItem(new SwingShowJDialogAction(pst, label));
            jxc.putClientProperty("class", pst.getSimpleName());
            menu.add(jxc);

            return this;
        }

        protected MenuItem add(Class pst, String label,boolean userMessageBlocker) {
            ActionMenuItem jxc = new ActionMenuItem(new SwingShowPstAction(pst, label,userMessageBlocker));
            jxc.putClientProperty("class", pst.getSimpleName());
            menu.add(jxc);
            return this;
        }

        public MenuItem conIcon() {
//            setInew javax.swing.ImageIcon(getClass().getResource("/atux/resources/menuCatalogos.png"))
            return this;
        }
    }

    public void setSecurityCodeSetter(SecurityCodeManager securityCodeSetter) {
        this.securityCodeSetter = securityCodeSetter;
    }

    public List<MenuItem> getMenues() {
        return menues;
    }

    public void setMenues(List<MenuItem> menues) {
        this.menues = menues;
    }
}
