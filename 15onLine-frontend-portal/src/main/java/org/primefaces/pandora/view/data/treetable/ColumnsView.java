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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named("ttColumnsView")
@ViewScoped
public class ColumnsView implements Serializable {
    
    private final static List<String> VALID_COLUMN_KEYS = Arrays.asList("name", "size", "type");
	
    private String columnTemplate = "name size type";
    
    private List<ColumnModel> columns;
    
    private TreeNode root;
        
    @Inject
    private DocumentService service;
    
    @PostConstruct
    public void init() {
        root = service.createDocuments();
        
        createDynamicColumns();
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setService(DocumentService service) {
        this.service = service;
    }
    
    public void createDynamicColumns() {
        String[] columnKeys = columnTemplate.split(" ");
        columns = new ArrayList<ColumnModel>();   
        
        for(String columnKey : columnKeys) {
            String key = columnKey.trim();
            
            if(VALID_COLUMN_KEYS.contains(key)) {
                columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
            }
        }
    }

    public String getColumnTemplate() {
        return columnTemplate;
    }

    public void setColumnTemplate(String columnTemplate) {
        this.columnTemplate = columnTemplate;
    } 
    
    public List<ColumnModel> getColumns() {
        return columns;
    }
  
    static public class ColumnModel implements Serializable {

        private String header;
        private String property;

        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }

        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }
    }
}
