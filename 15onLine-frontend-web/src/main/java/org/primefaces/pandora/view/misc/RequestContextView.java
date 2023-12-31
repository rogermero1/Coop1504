/**
 *  Copyright 2009-2020 PrimeTek.
 *
 *  Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.primefaces.pandora.view.misc;

import org.primefaces.PrimeFaces;
import org.primefaces.pandora.domain.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class RequestContextView {
  
    private User user;
    
    @PostConstruct
    public void init() {
        user = new User();
        
        if(!FacesContext.getCurrentInstance().isPostback()) {
            PrimeFaces.current().executeScript("alert('This onload script is added from backing bean.')");
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public void save() {
	    PrimeFaces.current().ajax().addCallbackParam("saved", true);    //basic parameter
	    PrimeFaces.current().ajax().addCallbackParam("user", user);     //pojo as json
        
        //execute javascript oncomplete
	    PrimeFaces.current().executeScript("PrimeFaces.info('Hello from the Backing Bean');");
        
        //update panel
        PrimeFaces.current().ajax().update("form:panel");
        
        //scroll to panel
        PrimeFaces.current().scrollTo("form:panel");
        
        //add facesmessage
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "Success"));
	}
}
