/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ah.baseprojectjsf.beans;

import com.ah.baseprojectjsf.usuarios.Usuario;
import com.ah.baseprojectjsf.usuarios.UsuariosDataModel;
import com.ah.baseprojectjsf.util.ConnectionAPIs;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
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

        
        ConnectionAPIs capi = new ConnectionAPIs();
        usuarios = capi.consultarUsuariosApi();
        
        usuariosDataModel = new UsuariosDataModel((Object) usuarios);

    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setSelectedUsuarios(Object[] selectedUsuarios) {
        System.out.println("entrando a selected usuarios object");
        try {
            System.out.println(((Usuario)selectedUsuarios[0]).getCodigo());
        } catch (Exception e) {
        }
        
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
        if (selectedUsuarioEdit != null && selectedUsuarioEdit.getCodigo()!= null && selectedUsuarioEdit.getCodigo().trim().length() > 0) {
            usuarioCodigo = quitaNulo(selectedUsuarioEdit.getCodigo()); 
            usuarioNombre = quitaNulo(selectedUsuarioEdit.getNombre()); 
            usuarioTelefono = quitaNulo(selectedUsuarioEdit.getTelefono()); 
            
            RequestContext contextoRequest = RequestContext.getCurrentInstance();
            contextoRequest.update("formModiUsu2:usercodMod");
            contextoRequest.update("formModiUsu2:usernomMod");
            contextoRequest.update("formModiUsu2:usertelMod");
            contextoRequest.execute("PF('modificarUsuarioDialog').show()");
        }
        
        
        this.selectedUsuarioEdit = selectedUsuarioEdit;
    }

    public void setSelectedUsuarioView(Usuario selectedUsuarioView) {
        
        
        if (selectedUsuarioView != null && selectedUsuarioView.getCodigo()!= null && selectedUsuarioView.getCodigo().trim().length() > 0) {
            usuarioCodigo = quitaNulo(selectedUsuarioView.getCodigo()); 
            usuarioNombre = quitaNulo(selectedUsuarioView.getNombre()); 
            usuarioTelefono = quitaNulo(selectedUsuarioView.getTelefono()); 
            System.out.println("test");
            RequestContext contextoRequest = RequestContext.getCurrentInstance();
            contextoRequest.update("formVisUsu2:usercodVis");
            contextoRequest.update("formVisUsu2:usernomVis");
            contextoRequest.update("formVisUsu2:usertelVis");
            contextoRequest.execute("PF('visualizarUsuarioDialog').show()");
        }
        
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
    
    public void grabandoUsuario(){
        ConnectionAPIs cpi = new ConnectionAPIs();
        Usuario usr = new Usuario();
        usr.setCodigo(usuarioCodigo);
        usr.setNombre(usuarioNombre);
        usr.setTelefono(usuarioTelefono);
        System.out.println(cpi.insertarUsuariosApi(usr));
        RequestContext contextoRequest = RequestContext.getCurrentInstance();
        contextoRequest.execute("PF('insertarUsuarioDialog').hide()");
        
    }
    
    public void modificandoUsuario(){
        ConnectionAPIs cpi = new ConnectionAPIs();
        Usuario usr = new Usuario();
        usr.setCodigo(usuarioCodigo);
        usr.setNombre(usuarioNombre);
        usr.setTelefono(usuarioTelefono);
        System.out.println(cpi.modificarUsuarioApi(usr));
        RequestContext contextoRequest = RequestContext.getCurrentInstance();
        contextoRequest.execute("PF('modificarUsuarioDialog').hide()");
        
    }
    
    
    public void eliminaUsuario() {
        List<Usuario> seleccionados = new LinkedList<>();
        for (Object ob : selectedUsuarios) {
            seleccionados.add((Usuario) ob);
        }
        if (seleccionados.size() > 0) {
            System.err.println("si hay clientes seleccionados");
            boolean todook = false;
            ConnectionAPIs cnapi = new ConnectionAPIs();
            for (Usuario cli : seleccionados) {
                //cli.setCodigo(cli.getCodigo());
                System.out.println("usuario" + cli.getCodigo());
                
                cnapi.eliminarUsuarioApi(cli);
                todook=true;
//                boolean res = eliminaUsuarioage(usua);
//                if (res) {
//                    todook = true;
//                }
            }
            if (todook) {
//                clientes = consultaUsuariosClix();
//                clientesCliDataModel = new ObiUsuariosDataModel(clientes);
                selectedUsuarios = null;
                RequestContext contextoRequest = RequestContext.getCurrentInstance();
                contextoRequest.update("piform:rolesTable");

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuarios eliminados exitosamente", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No fue posible eliminar los usuarios seleccionados", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar los usuarios a eliminar", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }
    
    public String quitaNulo(String var) {
        String res = "";
        if (var != null && var.trim().length() > 0) {
            res = var.trim();
        } else {
            res = "";
        }
        return res;
    }

}
