package com.atux.service.qryMapper;

import com.atux.bean.consulta.UsuarioFlt;
import com.atux.dominio.modelo.UsuarioDTO;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
public interface UsuarioQryMapper {

    List<UsuarioDTO> findUsuarios(UsuarioFlt usuarioFlt);
}
