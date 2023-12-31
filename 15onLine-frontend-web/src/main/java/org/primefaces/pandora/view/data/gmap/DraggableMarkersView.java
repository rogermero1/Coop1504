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
package org.primefaces.pandora.view.data.gmap;

import javax.faces.view.ViewScoped;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
 
@Named
@ViewScoped
public class DraggableMarkersView implements Serializable {
 
    private MapModel draggableModel;
    
    private Marker marker;
    
    @PostConstruct
    public void init() {
        draggableModel = new DefaultMapModel();
         
        //Shared coordinates
        LatLng coord1 = new LatLng(36.879466, 30.667648);
        LatLng coord2 = new LatLng(36.883707, 30.689216);
        LatLng coord3 = new LatLng(36.879703, 30.706707);
        LatLng coord4 = new LatLng(36.885233, 30.702323);
         
        //Draggable
        draggableModel.addOverlay(new Marker(coord1, "Konyaalti"));
        draggableModel.addOverlay(new Marker(coord2, "Ataturk Parki"));
        draggableModel.addOverlay(new Marker(coord3, "Karaalioglu Parki"));
        draggableModel.addOverlay(new Marker(coord4, "Kaleici"));
         
        for(Marker premarker : draggableModel.getMarkers()) {
            premarker.setDraggable(true);
        }
    }
     
    public MapModel getDraggableModel() {
        return draggableModel;
    }
     
    public void onMarkerDrag(MarkerDragEvent event) {
        marker = event.getMarker();
         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
    }
}
