/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ah.baseprojectjsf.beans;

import com.ah.baseprojectjsf.usuarios.Usuario;
import com.ah.baseprojectjsf.usuarios.UsuariosDataModel;
import java.util.LinkedList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author AHERNANDEZ
 */
public class BeanUsuarios {

    private List<Usuario> usuarios;
    private Object[] selectedUsuarios;
    private UsuariosDataModel usuariosDataModel;
    private Usuario selectedUsuarioEdit;
    private Usuario selectedUsuarioView;
    private String usuarioCodigo;
    private String usuarioNombre;
    private String usuarioTelefono;

    public BeanUsuarios() {

        usuarios = new LinkedList<>();
        Usuario us = new Usuario();
        us.setCodigo("C1");
        us.setNombre("Luis Perez");
        us.setTelefono("34343443");
        usuarios.add(us);
        usuariosDataModel = new UsuariosDataModel((Object) usuarios);

    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setSelectedUsuarios(Object[] selectedUsuarios) {
        this.selectedUsuarios = selectedUsuarios;
    }

    public Object[] getSelectedUsuarios() {
        return selectedUsuarios;
    }

    public void setUsuariosDataModel(UsuariosDataModel usuariosDataModel) {
        this.usuariosDataModel = usuariosDataModel;
    }

    public UsuariosDataModel getUsuariosDataModel() {
        return usuariosDataModel;
    }

    public void setSelectedUsuarioEdit(Usuario selectedUsuarioEdit) {
        this.selectedUsuarioEdit = selectedUsuarioEdit;
    }

    public void setSelectedUsuarioView(Usuario selectedUsuarioView) {
        this.selectedUsuarioView = selectedUsuarioView;
    }

    public Usuario getSelectedUsuarioEdit() {
        return selectedUsuarioEdit;
    }

    public Usuario getSelectedUsuarioView() {
        return selectedUsuarioView;
    }

    public void setUsuarioCodigo(String usuarioCodigo) {
        this.usuarioCodigo = usuarioCodigo;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public void setUsuarioTelefono(String usuarioTelefono) {
        this.usuarioTelefono = usuarioTelefono;
    }

    public String getUsuarioCodigo() {
        return usuarioCodigo;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public String getUsuarioTelefono() {
        return usuarioTelefono;
    }

    public void agregarUsuario() {

        usuarioCodigo = "";
        usuarioNombre = "";
        usuarioTelefono = "";
        RequestContext contextoRequest = RequestContext.getCurrentInstance();
        contextoRequest.update("formInserUsu2:usercod");
        contextoRequest.update("formInserUsu2:usernom");
        contextoRequest.update("formInserUsu2:usertel");
        contextoRequest.execute("PF('insertarUsuarioDialog').show()");

    }

}
