package com.aw.desktop.spring.security;


/**
 * Clase usada para generar los còdigos de seguridad de las ventanas y de las acciones.
 * User: gmc
 * Date: 03/06/2009
 */
public class SiderUtilAppCtxListener/* implements ApplicationListener*/ {
/*

    protected final Log logger = LogFactory.getLog(getClass());
    private static final String SEC_CODE_PROPERTIES_FILE_NAME = "SecCodes.properties";

    private static final int NRO_ACCIONES_X_VENTANA= 50;

    public void onApplicationEvent(ApplicationEvent event) {
        List listaVentanas = new ArrayList();
        if (event instanceof ContextRefreshedEvent) {
            logger.info("On Context Refreshed Event");
            ContextRefreshedEvent currentEvent = (ContextRefreshedEvent) event;
            ApplicationContext applicationContext = currentEvent.getApplicationContext();
            String[] presenters = applicationContext.getBeanNamesForType(Presenter.class);
            logger.info("Existen :<" + presenters.length + "> presenters.");



            SVAplicacion svAplicacion = (SVAplicacion) applicationContext.getBean("SVAplicacionImpl");
            SVPerfil svPerfil = (SVPerfil) applicationContext.getBean("SVPerfilImpl");
//             Creación de la aplicación
            AplicacionTmImpl aplicacionTm = new AplicacionTmImpl();
            aplicacionTm.setPkAplicacion(new Long(622));
            aplicacionTm.setCoAplicacion("APL00002");
//        svAplicacion.limpiarAplicacion(aplicacionTm.getCoAplicacion());


            FillerFormat filler = new FillerFormat('0', 5);
            int presentersCounter = 0;
            FileWriter outFile = null;
            List ventanas = new ArrayList();
            try {
                outFile = new FileWriter(SEC_CODE_PROPERTIES_FILE_NAME);
                PrintWriter out = new PrintWriter(outFile);
                SiderAccionesLabelsMgr labelsMgr = new SiderAccionesLabelsMgr();
                for (String pstName : presenters) {
                    Presenter pst = (Presenter) applicationContext.getBean(pstName);
                    if (pst instanceof AuditoriaInfoPst || pst instanceof PickAgentePst || pst instanceof PickUsuariosPst || pst instanceof PickFamiliasPst) {
                        continue;
                    }
                    if (pst.getClass().getName().startsWith("com.empresa.app")) {
                        continue;
                    }
                    AWPresenter awPresenter = AnnotationUtils.findAnnotation(pst.getClass(), AWPresenter.class);
                    if (!awPresenter.secure()){
                        continue;                                                
                    }
                    pst = PstMgr.instance().getPst(pst.getClass());
                    pst.setBackBean(pst.createBackBean());
                    pst.configure();

                    PresenterActionInfo pstActionInfo = new PresenterActionInfo(pst);
                    String simpleName = pst.getClass().getSimpleName();
                    if (simpleName.length() != pstName.length()) {
                        System.out.println("1:<" + simpleName + "> 2:<" + pstName + ">");
                    }
//                    String view = simpleName.substring(0, pstName.length() - 3);
                    String view = StringUtils.capitalize(pstName).substring(0, pstName.length() - 3);

                    String securityCode = "VEN" + filler.parseObject("" + ++presentersCounter);
                    System.out.println("## Datos de seguridad de la ventana:" + view);
                    System.out.println(view + "=" + securityCode);
                    out.println("## Datos de seguridad de la ventana:" + view);
                    out.println(view + "=" + securityCode);
                    out.println(view + ".Class=" + pst.getClass().getName());
//
                    VentanaTmImpl ventanaTm = obtenerVentana(pst.getViewMgr().getTitle(), view, securityCode);
                    ventanaTm.setFkAplicacion(aplicacionTm.getPkAplicacion());
                    listaVentanas.add(ventanaTm);
//
                    int inicioSecZonas = 30 * (presentersCounter - 1);
                    Collection<Zone> zones = pst.getZonekMgr().getZones();

                    List ventanaZonas = new ArrayList();
                    for (Zone zone : zones) {
                        String propName = view + "." + zone.getName();
                        String propValue = "ZON" + filler.parseObject(String.valueOf(inicioSecZonas));
                        inicioSecZonas++;
                        System.out.println(propName + "=" + propValue);
                        out.println(propName + "=" + propValue);
                        ZonaTmImpl zona = obtenerZonaTmImpl(zone, propValue);
                        ventanaZonas.add(zona);
                    }
                    ventanaTm.setZonas(ventanaZonas);
                    int inicioSecAcciones = NRO_ACCIONES_X_VENTANA * (presentersCounter - 1);
                    // Información de las acciones
                    Collection<Action> acciones = pst.getActionRsr().getActions().values();
                    List<AccionTmImpl> ventanaAcciones = new ArrayList();
                    List<AccionTmImplWrapper> ventanaAccionesWrapper = new ArrayList();
                    for (Action action : acciones) {
                        String actionClassName = action.getClass().getSimpleName();
                        boolean esVerAccion = actionClassName.startsWith("Ver");
                        String idAsString = action.getId().asString();
                        boolean esShowAction = idAsString.startsWith("Show");
                        boolean esSugAction = idAsString.startsWith("Sug");
                        boolean esPdfOExcelAction = action instanceof ExportarFindPstAction;

                        if (//                                !(action instanceof PaginateAction) &&
                                !esSugAction&&
                                !esPdfOExcelAction&&
                                !esVerAccion&&
                                !esShowAction&&
                                !(action instanceof SortByColumnAction) &&
                                        !(action instanceof PickAction) &&
//                                !(action instanceof PickCleanDataAction) &&
                                        !(action instanceof SelectRowsAction) &&
                                        !(action instanceof ShowAuditingInfoAction) &&
//                                !(action instanceof UpdateCellValueAction) &&
                                        !(action instanceof CancelAction)
//                                        &&
//                                !(action instanceof RefreshDependentGridsAction) &&
//                                !(action instanceof AlphabeticPaginateAction) &&
//                                !(action instanceof GetDependentCmbDataInfoAction)
                                ) {

                            String propName = view + "." + action.getId().asString();
                            String propValue = "ACC" + filler.parseObject(String.valueOf(inicioSecAcciones));
                            inicioSecAcciones++;
                            System.out.println(propName + "=" + propValue);
                            out.println(propName + "=" + propValue);
//                            AccionTmImpl accionTm = obtenerActionTmImpl(action, propValue);
                            AccionTmImplWrapper accionTmImplWrapper = obtenerActionTmImpl(action, propValue);
                            ventanaAcciones.add(accionTmImplWrapper.getActionTm());
                            ventanaAccionesWrapper.add(accionTmImplWrapper);
                        }
                    }
                    int accionesXVentana = ventanaAcciones.size();
                    if (accionesXVentana > NRO_ACCIONES_X_VENTANA) {
                        throw new IllegalStateException("La ventana:<" + view + "> tiene más de "+NRO_ACCIONES_X_VENTANA+" acciones:<" + accionesXVentana + ">");
                    }
                    pstActionInfo.setVentanaAcciones(ventanaAccionesWrapper);
                    labelsMgr.add(pstActionInfo);
                    ventanaTm.setAcciones(ventanaAcciones);
                    ventanas.add(ventanaTm);
                }
                out.close();
                labelsMgr.guardarEnArchivo();
            } catch (IOException e) {
                e.printStackTrace();
            }
             generarMenus(applicationContext);
//            svAplicacion.guardarVentana(listaVentanas);
        }

        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("aplicacion");
            document.appendChild(rootElement);

            for (Iterator iterator = listaVentanas.iterator(); iterator.hasNext();) {
                VentanaTmImpl ventanaTm = (VentanaTmImpl) iterator.next();
                Element emVentana = document.createElement("ventana");
                emVentana.setAttribute("codigo", ventanaTm.getCoVentana());
                Element emVentanaNombre = document.createElement("nombre");
                emVentanaNombre.appendChild(document.createTextNode(ventanaTm.getNoVentana()));
                emVentana.appendChild(emVentanaNombre);

                Element emVentanaDescripcion = document.createElement("descripcion");
                emVentanaDescripcion.appendChild(document.createTextNode(""));
                emVentana.appendChild(emVentanaDescripcion);

                Element emVentanaUrl = document.createElement("urlbase");
                emVentanaUrl.appendChild(document.createTextNode(""));
                emVentana.appendChild(emVentanaUrl);

                Element emVentanaFuncion = document.createElement("funcion");
                emVentanaFuncion.appendChild(document.createTextNode(""));
                emVentana.appendChild(emVentanaFuncion);

                Element emVentanaPrincipal = document.createElement("principal");
                emVentanaPrincipal.appendChild(document.createTextNode("0"));
                emVentana.appendChild(emVentanaPrincipal);

                for (Iterator iterator1 = ventanaTm.getAcciones().iterator(); iterator1.hasNext();) {
                    AccionTmImpl accionTm = (AccionTmImpl) iterator1.next();
                    Element emAccion = document.createElement("accion");
                    emAccion.setAttribute("codigo", accionTm.getCoAccion());
                    Element emAccionNombre = document.createElement("nombre");
                    emAccionNombre.appendChild(document.createTextNode(accionTm.getNoAccion()));
                    emAccion.appendChild(emAccionNombre);

                    Element emAccionDescripcion = document.createElement("descripcion");
                    emAccionDescripcion.appendChild(document.createTextNode(""));
                    emAccion.appendChild(emAccionDescripcion);

                    emVentana.appendChild(emAccion);
                }

                for (Iterator iterator1 = ventanaTm.getZonas().iterator(); iterator1.hasNext();) {
                    ZonaTmImpl zonaTm = (ZonaTmImpl) iterator1.next();
                    Element emZona = document.createElement("zona");
                    emZona.setAttribute("codigo", zonaTm.getCoZona());
                    Element emZonaNombre = document.createElement("nombre");
                    emZonaNombre.appendChild(document.createTextNode(zonaTm.getNoZona()));
                    emZona.appendChild(emZonaNombre);

                    Element emZonaDescripcion = document.createElement("descripcion");
                    emZonaDescripcion.appendChild(document.createTextNode(""));
                    emZona.appendChild(emZonaDescripcion);

                    emVentana.appendChild(emZona);
                }
                rootElement.appendChild(emVentana);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            FileWriter outFilex = new FileWriter("dataAplicacion.xml");
            PrintWriter out = new PrintWriter(outFilex);

            StreamResult result = new StreamResult(out);
            transformer.transform(source, result);
            System.out.println(transformer.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void generarMenus(ApplicationContext applicationContext) {

        final MenuBuilder mb = new MenuBuilder();
//        mb.setSecurityCodeSetter(Application.instance().getBean(SecurityCodeManager.class));
        mb.setSecurityCodeSetter((SecurityCodeManager) ApplicationContexUtils.getRegisteredBeanForType(applicationContext, SiderSecurityCodeManager.class));

        mb.createMenus();
        mb.crearXml();


    }

    private AccionTmImplWrapper obtenerActionTmImpl(Action action, String acSecCode) {
        String acName = action.getSecurityLabel();
        JButton jbutton = (JButton) action.getJComponent();
        if (StringUtils.isEmpty(acName)) {
            acName = jbutton.getText();
        }
        String acDescripcion = (action.getId().getGridIndex() != null) ? "La accion está relacionada al Grid:" + action.getId().getGridIndex().intValue() : "";
        String acTipo = "1";

        AccionTmImpl accionTm = new AccionTmImpl();
        accionTm.setCoAccion(acSecCode);
        if(acName.length()>50) throw new AWBusinessException(acName);
        accionTm.setNoAccion(acName);
        accionTm.setDeAccion(acDescripcion);
        accionTm.setTiAccionTm(acTipo);
        AccionTmImplWrapper wrapper = new AccionTmImplWrapper(action,accionTm);
        return wrapper;
    }

    private ZonaTmImpl obtenerZonaTmImpl(Zone action, String acSecCode) {


        ZonaTmImpl accionTm = new ZonaTmImpl();
        accionTm.setCoZona(acSecCode);
        accionTm.setNoZona(action.getSecurityLabel());
        return accionTm;
    }

    private VentanaTmImpl obtenerVentana(String title, String view, String securityCode) {
        String name = title;
        String inPrincipal = "0";
        logger.info(securityCode + " " + name + " " + inPrincipal);

        VentanaTmImpl ventanaTm = new VentanaTmImpl();
        ventanaTm.setCoVentana(securityCode);
        if (name.length() > 50) {
            ventanaTm.setNoVentana(name.substring(0, 50));
        } else {
            ventanaTm.setNoVentana(name);
        }

//        ventanaTm.setUrlBase(url);
        ventanaTm.setInPrincipal(inPrincipal);
        return ventanaTm;
    }
*/
}

