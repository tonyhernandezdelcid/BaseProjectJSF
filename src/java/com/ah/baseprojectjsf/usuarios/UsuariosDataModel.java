/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ah.baseprojectjsf.usuarios;

import java.util.List;

/**
 *
 * @author AHERNANDEZ
 */
public class UsuariosDataModel extends PrimeDataModel<Usuario> {

    public UsuariosDataModel(Object data) {
        super(data);
    }

    @Override
    public Usuario getRowData(String rowKey) {
        List<Usuario> roles = (List<Usuario>) getWrappedData();
        for (Usuario rol : roles) {
            String codigo = "" + rol.getCodigo();
            if (codigo.equals(rowKey)) {
                return rol;
            }
        }
        return null;
    }

    @Override
    public String getRowKey(Usuario usr) {
        return "" + usr.getCodigo();
    }

}
