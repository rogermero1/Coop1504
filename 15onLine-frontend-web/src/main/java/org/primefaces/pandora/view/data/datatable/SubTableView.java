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

import org.primefaces.pandora.domain.Stats;
import org.primefaces.pandora.domain.Team;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("dtSubTableView")
@RequestScoped
public class SubTableView {
    
    private List<Team> teams;

    @PostConstruct
    public void init() {
        teams = new ArrayList<Team>();
        Team lakers = new Team("Los Angeles Lakers");
        lakers.getStats().add(new Stats("2005-2006", 50, 32));
        lakers.getStats().add(new Stats("2006-2007", 44, 38));
        lakers.getStats().add(new Stats("2007-2008", 40, 42));
        lakers.getStats().add(new Stats("2008-2009", 45, 37));
        lakers.getStats().add(new Stats("2009-2010", 48, 34));
        lakers.getStats().add(new Stats("2010-2011", 42, 42));
        teams.add(lakers);
        
        Team celtics = new Team("Boston Celtics");
        celtics.getStats().add(new Stats("2005-2006", 46, 36));
        celtics.getStats().add(new Stats("2006-2007", 50, 32));
        celtics.getStats().add(new Stats("2007-2008", 41, 41));
        celtics.getStats().add(new Stats("2008-2009", 45, 37));
        celtics.getStats().add(new Stats("2009-2010", 38, 44));
        celtics.getStats().add(new Stats("2010-2011", 35, 47));
        teams.add(celtics);
    }
    
    public List<Team> getTeams() {
        return teams;
    }
}
