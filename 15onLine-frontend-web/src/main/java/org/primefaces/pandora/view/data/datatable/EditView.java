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
package org.primefaces.pandora.view.data.datatable;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.pandora.domain.Car;
import org.primefaces.pandora.service.CarService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("dtEditView")
@ViewScoped
public class EditView implements Serializable {
    
    private List<Car> cars1;
    private List<Car> cars2;
        
    @Inject
    private CarService service;
    
    @PostConstruct
    public void init() {
        cars1 = service.createCars(10);
        cars2 = service.createCars(10);
    }

    public List<Car> getCars1() {
        return cars1;
    }

    public List<Car> getCars2() {
        return cars2;
    }
    
    public List<String> getBrands() {
        return service.getBrands();
    }
    
    public List<String> getColors() {
        return service.getColors();
    }

    public void setService(CarService service) {
        this.service = service;
    }
    
    public void onRowEdit(RowEditEvent<Car> event) {
        FacesMessage msg = new FacesMessage("Car Edited", event.getObject().getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel(RowEditEvent<Car> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
