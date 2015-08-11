package com.aw.core.report.custom;

import com.aw.core.domain.AWBusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptDocument {
    protected final Log logger = LogFactory.getLog(getClass());

    private String name;
    private List<RptSection> sections = new ArrayList<RptSection>();
    private String fullReportPathName;
    private RptLayout layout = new RptLayout();

    public RptDocument(String name) {
        this.name = name;
    }

    public List<RptSection> getSections() {
        return sections;
    }

    public String getName() {
        return name;
    }

    public String getFullReportPathName() {
        return fullReportPathName;
    }

    public void render(RptRenderer renderer) {
        try {
            fullReportPathName = renderer.start(name);
            for (RptSection section : sections)
                section.initialize(renderer);
            layout.print(renderer, sections);
        } catch (Exception e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        } finally {
            //asegurarse de cerrar de todos modos
            try {
                renderer.end();
            } catch (Exception e) {
                throw AWBusinessException.wrapUnhandledException(logger, e);
            }
        }
    }
}
