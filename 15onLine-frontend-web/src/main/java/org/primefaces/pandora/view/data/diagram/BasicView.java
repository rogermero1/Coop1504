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
package org.primefaces.pandora.view.data.diagram;

import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("diagramBasicView")
@RequestScoped
public class BasicView {
    
    private DefaultDiagramModel model;

    @PostConstruct
    public void init() {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
        
        Element elementA = new Element("A", "20em", "6em");
        elementA.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        
        Element elementB = new Element("B", "10em", "18em");
        elementB.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
        
        Element elementC = new Element("C", "40em", "18em");
        elementC.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                        
        model.addElement(elementA);
        model.addElement(elementB);
        model.addElement(elementC);
        
        model.connect(new Connection(elementA.getEndPoints().get(0), elementB.getEndPoints().get(0)));        
        model.connect(new Connection(elementA.getEndPoints().get(0), elementC.getEndPoints().get(0)));
    }
    
    public DiagramModel getModel() {
        return model;
    }
}
