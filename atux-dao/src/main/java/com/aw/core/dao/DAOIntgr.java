/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.core.dao;

import com.aw.core.dao.bean.meta.BeanMetaInfo;


/**
 * Created by IntelliJ IDEA.
 * User: Julio C. Macavilca
 * Date: 02/10/2007
 * Time: 05:31:58 PM
 */
public class DAOIntgr {
    private DAOHbm daoHbm;
    private DAOSql daoSql;
    private DAOBean daoBean;

    public DAOIntgr(DAOHbm daoHbm, DAOSql daoSql, DAOBean daoBean) {
        this.daoHbm = daoHbm;
        this.daoSql = daoSql;
        this.daoBean = daoBean;
    }

    public DAOHbm getHbm() {
        return daoHbm;
    }

    public DAOSql getSql() {
        return daoSql;
    }


    public void delete(Object bean) {
        if (BeanMetaInfo.isBean(bean))
            daoBean.delete(bean);
        else
            daoHbm.delete(bean);
    }

    public void saveOrUpdate(Object bean) {
        if (BeanMetaInfo.isBean(bean))
            daoBean.saveOrUpdate(bean);
        else
            daoHbm.saveOrUpdate(bean);
    }
}
