/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ah.baseprojectjsf.util;

import com.ah.baseprojectjsf.usuarios.Usuario;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.net.ConnectException;
import java.net.URI;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import com.google.gson.Gson;
import com.google.gson.JsonPrimitive;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;

/**
 *
 * @author AHERNANDEZ
 */
public class ConnectionAPIs {

    public ConnectionAPIs() {
    }

    public List<Usuario> consultarUsuariosApi() {
        List<Usuario> usuarios = new LinkedList<>();
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet("http://localhost:8080/baseprojectapi/consultausuarios");
            httpget.addHeader("Content-Type", "application/json");
            HttpResponse response = httpclient.execute(httpget);
            JSONTable js = new JSONTable();
            
            
            String jsonRes = js.inputStreamToString(response.getEntity().getContent()).toString();
            System.out.println("Arreglo usuarios: " + jsonRes);
            JSONArray arreglo = new JSONArray(jsonRes);
            Usuario usr;
              for (int i = 0; i < arreglo.length(); i++) {
                usr = new Usuario();
                JSONObject resjson = arreglo.getJSONObject(i);
                usr.setCodigo(resjson.getString("codigo"));
                usr.setNombre(resjson.getString("nombre"));
                usr.setTelefono(resjson.getString("telefono"));
                usuarios.add(usr);
              }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return usuarios;

    }
    
    
    public boolean insertarUsuariosApi(Usuario usr) {
        boolean inserto = false;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://localhost:8080/baseprojectapi/crearusuario");
            httppost.addHeader("Content-Type", "application/json");
            JSONObject object = new JSONObject();
            object.put("codigo", usr.getCodigo());
            object.put("nombre", usr.getNombre());
            object.put("telefono", usr.getTelefono());
            StringEntity entity = new StringEntity(object.toString(), "UTF-8");
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httppost.setEntity(entity);
            HttpResponse response = httpclient.execute(httppost);
            JSONTable js = new JSONTable();

            JsonPrimitive res = new JsonPrimitive(js.inputStreamToString(response.getEntity().getContent()).toString());
            if (res.getAsBoolean()) {
                inserto = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return inserto;
    }
    
    
    public boolean modificarUsuarioApi(Usuario usr) {
        boolean modi = false;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPut httpput = new HttpPut("http://localhost:8080/baseprojectapi/modificarusuario");
            httpput.addHeader("Content-Type", "application/json");
            JSONObject object = new JSONObject();
            object.put("codigo", usr.getCodigo());
            object.put("nombre", usr.getNombre());
            object.put("telefono", usr.getTelefono());
            StringEntity entity = new StringEntity(object.toString(), "UTF-8");
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httpput.setEntity(entity);
            HttpResponse response = httpclient.execute(httpput);
            JSONTable js = new JSONTable();
            
            JsonPrimitive res = new JsonPrimitive(js.inputStreamToString(response.getEntity().getContent()).toString());
            if (res.getAsBoolean()) {
                modi = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return modi;
    }

    public boolean eliminarUsuarioApi(Usuario usuario) {
        boolean eli = false;
        try {
            String params = "/" + usuario.getCodigo();
            HttpClient httpclient = new DefaultHttpClient();
            HttpDelete httpdel = new HttpDelete("http://localhost:8080/baseprojectapi/eliminausuario/" + params);
            httpdel.addHeader("Content-Type", "application/json");
            HttpResponse response = httpclient.execute(httpdel);
            JSONTable js = new JSONTable();

            JsonPrimitive res = new JsonPrimitive(js.inputStreamToString(response.getEntity().getContent()).toString());
            if (res.getAsBoolean()) {
                eli = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return eli;
    }

}
