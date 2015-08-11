package com.aw.core.domain.security;

import com.aw.core.domain.entity.UserInfo;

/**
 * User: gmc
 * Date: 20/10/2008
 */
public interface SVLogin {
    UserInfo executeLogin(String codUsuario, String password);
}
