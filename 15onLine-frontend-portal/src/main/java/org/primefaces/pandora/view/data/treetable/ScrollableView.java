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
package org.primefaces.pandora.view.data.treetable;

import javax.faces.view.ViewScoped;
import org.primefaces.model.TreeNode;
import org.primefaces.pandora.service.DocumentService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("ttScrollableView")
@ViewScoped
public class ScrollableView implements Serializable {
    
    private TreeNode root1;
    private TreeNode root2;
    private TreeNode root3;
    
    @Inject
    private DocumentService service;
    
    @PostConstruct
    public void init() {
        root1 = service.createDocuments();
        root2 = service.createDocuments();
        root3 = service.createDocuments();
        
        root1.getChildren().get(0).setExpanded(true);
        root1.getChildren().get(1).setExpanded(true);
    }

    public TreeNode getRoot1() {
        return root1;
    }

    public TreeNode getRoot2() {
        return root2;
    }

    public TreeNode getRoot3() {
        return root3;
    }

    public void setService(DocumentService service) {
        this.service = service;
    }
}

