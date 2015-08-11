package com.aw.core.dao.bean;

import java.util.Date;

/**
 * User: JCM
 * Date: 15/10/2007
 */
public class DAOHbmTableImpl {
    Long id;
    String fieldText;
    Date fieldDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFieldText() {
        return fieldText;
    }

    public void setFieldText(String fieldText) {
        this.fieldText = fieldText;
    }

    public Date getFieldDate() {
        return fieldDate;
    }

    public void setFieldDate(Date fieldDate) {
        this.fieldDate = fieldDate;
    }

}
