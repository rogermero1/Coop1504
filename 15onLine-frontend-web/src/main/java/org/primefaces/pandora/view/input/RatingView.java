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
package org.primefaces.pandora.view.input;

import org.primefaces.event.RateEvent;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class RatingView {
    
    private Integer rating1;   
    private Integer rating2;   
    private Integer rating3;   
    private Integer rating4 = 3;
    
    public void onrate(RateEvent<Integer> rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + rateEvent.getRating());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Integer getRating1() {
        return rating1;
    }

    public void setRating1(Integer rating1) {
        this.rating1 = rating1;
    }

    public Integer getRating2() {
        return rating2;
    }

    public void setRating2(Integer rating2) {
        this.rating2 = rating2;
    }

    public Integer getRating3() {
        return rating3;
    }

    public void setRating3(Integer rating3) {
        this.rating3 = rating3;
    }

    public Integer getRating4() {
        return rating4;
    }

    public void setRating4(Integer rating4) {
        this.rating4 = rating4;
    }
}
