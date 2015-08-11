package com.aw.core.domain.entity;

/**
 * User: gmc
 * Date: 06/05/2009
 */

public class ApplicationUser extends UserInfo{

    protected String userName;
    protected String fullName;
    protected String coFicha;
    protected Long fkArea;


    public ApplicationUser() {
    }

    public ApplicationUser(String userName, String fullName, String coFicha, Long fkArea) {
        this.userName = userName;
        this.fullName = fullName;
        this.coFicha = coFicha;
        this.fkArea = fkArea;
    }

    public ApplicationUser(String fullName, String userName) {
        this.fullName = fullName;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCoFicha() {
        return coFicha;
    }

    public void setCoFicha(String coFicha) {
        this.coFicha = coFicha;
    }

    public Long getFkArea() {
        return fkArea;
    }

    public void setFkArea(Long fkArea) {
        this.fkArea = fkArea;
    }
}
