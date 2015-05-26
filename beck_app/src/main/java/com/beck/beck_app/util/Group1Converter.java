/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.util;

import com.beck.beck_app.controller.Group1Controller;
import com.beck.beck_app.controller.UserController;
import com.beck.beck_app.model.Group1;
import com.beck.beck_app.model.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Patryk
 */
@FacesConverter("com.beck.beck_app.Group1Converter")
public class Group1Converter implements Converter{

      @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            Group1Controller controller = (Group1Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "group1Controller");
            return controller.getGroup1(getKey(value));
        }

       public java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

      public  String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Group1) {
                Group1 o = (Group1) object;
                return getStringKey(o.getIdgroup());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), User.class.getName()});
                return null;
            }
        }
    
}
