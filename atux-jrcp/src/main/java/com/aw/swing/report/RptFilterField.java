package com.aw.swing.report;

import com.aw.core.cache.loader.MetaLoader;
import com.aw.core.cache.support.DropDownFormatter;
import com.aw.core.format.DateFormatter;
import com.aw.core.format.Formatter;

/**
 * User: Julio C. Macavilca
 * Date: 09/06/2008
 */
public abstract class RptFilterField {

    public abstract String getValue() ;

    public static RptFilterField label(String label) {
        return new Label(label);
    }
    public static Text text(Object value) {
        return new Text(value);
    }


    public static class Label extends RptFilterField {
        String value;

        public Label(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    public static class Text extends RptFilterField {
        private Object value;
        private Formatter formatter;

        public Text(Object value) {
            this.value = value;
        }

        public String getValue() {
            Object retval = formatter != null ? formatter.format(value) : value;
            return retval==null? null:retval.toString();
        }
        public Text setDropDownFormatter(MetaLoader metaLoader) {
            formatter = new DropDownFormatter(metaLoader);
            return this;
        }
        public Text setAsDateFormatter() {
            formatter = DateFormatter.DATE_FORMATTER;
            return this;
        }

    }
}
