/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ah.baseprojectjsf.usuarios;

import java.io.Serializable;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author AHERNANDEZ
 */
public class PrimeDataModel<T> extends ListDataModel implements SelectableDataModel<T>, Serializable {
    
      public PrimeDataModel() {
    }

    public PrimeDataModel(Object data) {
        setWrappedData(data);
    }

    public Object getRowKey(T object) {
        throw new UnsupportedOperationException("Must be implemented");
    }

    public T getRowData(String rowKey) {
        throw new UnsupportedOperationException("Must be implemented");
    }
}
