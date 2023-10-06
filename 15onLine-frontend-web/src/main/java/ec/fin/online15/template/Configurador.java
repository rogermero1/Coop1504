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
package ec.fin.online15.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class Configurador implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String layoutPrimaryColor = "teallight";
    
    private String topbarTheme = "teallight";
    
    private String componentTheme = "green-dark";
    
    private String menuMode = "layout-slim-plus";
    
    private String menuColor = "light";
    
    private String menuTheme = "teallight";

    private boolean groupedMenu = true;

    private boolean darkLogo;

    private List<ComponentTheme> componentThemes = new ArrayList<>();
    
    private List<TopbarTheme> topbarThemes = new ArrayList<>();
    
    private Map<String, List<MenuTheme>> menuColors = new HashMap<>();

    private List<Palette> palettes = new ArrayList<>();

    private Palette selectedPalette;

    @PostConstruct
    public void init() {
        /************** Palettes ********************/
        
        /* Menu: Light | Active: Blue | Topbar: Blue | Components: Blue Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("blue", "#2E88FF", null), 
                new Color("blue", "#2E88FF", null), 
                new Color("blue-light", "#2E88FF", "#eeeeee")
            )
        );

         /* Menu: Dark | Active: Blue | Topbar: Blue | Components: Blue-Grey Light */
         palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("blue", "#2E88FF", null), 
                new Color("blue", "#2E88FF", null), 
                new Color("bluegrey-light", "#607D8B", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Teal-Light | Topbar: Teal-Light | Components: Teal-Light Accent */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("teallight", "#21B5AD", null), 
                new Color("teallight", "#21B5AD", null), 
                new Color("teallight-accent", "#21B5AD", "#FC970A")
            )
        );

        /* Menu: Dark | Active: Teal-Light | Topbar: Teal-Light | Components: Blue-Grey Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("teallight", "#21B5AD", null), 
                new Color("teallight", "#21B5AD", null), 
                new Color("bluegrey-light", "#607D8B", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Purple | Topbar: Purple | Components: Purple Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("purple", "#636FC0", null), 
                new Color("purple", "#636FC0", null), 
                new Color("purple-light", "#636FC0", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Orange | Topbar: Purple | Components: Orange Light body .ui-button*/
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("orange", "#EC7A51", null), 
                new Color("purple", "#636FC0", null), 
                new Color("orange-light", "#EC7A51", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Magenta | Topbar: Magenta | Components: Magenta Dark */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("magenta", "#C03995", null), 
                new Color("magenta", "#C03995", null), 
                new Color("magenta-dark", "#C03995", "#212121")
            )
        );

        /* Menu: Light | Active: Cyan | Topbar: Magenta | Components: Cyan Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("cyan", "#2199B5", null), 
                new Color("magenta", "#C03995", null), 
                new Color("cyan-light", "#2199B5", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Yellow | Topbar: Yellow | Components: Yellow Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("yellow", "#F3A841", null), 
                new Color("yellow", "#F3A841", null), 
                new Color("yellow-light", "#F3A841", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Yellow | Topbar: Yellow | Components: Blue-Grey Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("yellow", "#F3A841", null), 
                new Color("yellow", "#F3A841", null), 
                new Color("bluegrey-light", "#607D8B", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Green | Topbar: Green | Components: Green Dark */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("green", "#21B567", null), 
                new Color("green", "#21B567", null), 
                new Color("green-dark", "#21B567", "#212121")
            )
        );

        /* Menu: Light | Active: Green | Topbar: Green | Components: Yellow Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("green", "#21B567", null), 
                new Color("green", "#21B567", null), 
                new Color("yellow-light", "#F3A841", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Cyan | Topbar: Cyan | Components: Cyan Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("cyan", "#2199B5", null), 
                new Color("cyan", "#2199B5", null), 
                new Color("cyan-light", "#2199B5", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Cyan | Topbar: Cyan | Components: Yellow Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("cyan", "#2199B5", null), 
                new Color("cyan", "#2199B5", null), 
                new Color("yellow-light", "#F3A841", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Purple-Light | Topbar: Purple-Light | Components: Purple-Light Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("purplelight", "#9754B8", null), 
                new Color("purplelight", "#9754B8", null), 
                new Color("purplelight-light", "#9754B8", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Purple-Light | Topbar: Purple-Light | Components: Purple-Light Accent */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("purplelight", "#9754B8", null), 
                new Color("purplelight", "#9754B8", null), 
                new Color("purplelight-accent", "#9754B8", "#F7B500")
            )
        );

        /* Menu: Dark | Active: Orange | Topbar: Orange | Components: Orange Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("orange", "#EC7A51", null), 
                new Color("orange", "#EC7A51", null), 
                new Color("orange-light", "#EC7A51", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Orange | Topbar: Orange | Components: Yellow Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("orange", "#EC7A51", null), 
                new Color("orange", "#EC7A51", null), 
                new Color("yellow-light", "#F3A841", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Red | Topbar: Red | Components: Red Dark */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("red", "#FE566D", null), 
                new Color("red", "#FE566D", null), 
                new Color("red-dark", "#FE566D", "#212121")
            )
        );

        /* Menu: Light | Active: Red | Topbar: Red | Components: Blue-Grey Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("red", "#FE566D", null), 
                new Color("red", "#FE566D", null), 
                new Color("bluegrey-light", "#607D8B", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Teal-Dark | Topbar: Teal-Dark | Components: Teal-Light Accent */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("tealdark", "#076D72", null), 
                new Color("tealdark", "#076D72", null), 
                new Color("teallight-accent", "#21B5AD", "#FC970A")
            )
        );

        /* Menu: Light | Active: Teal-Dark | Topbar: Teal-Dark | Components: Teal-Dark Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("tealdark", "#076D72", null), 
                new Color("tealdark", "#076D72", null), 
                new Color("tealdark-light", "#076D72", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Purple-Dark | Topbar: Purple-Dark | Components: Purple-Dark Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("purpledark", "#44358F", null), 
                new Color("purpledark", "#44358F", null), 
                new Color("purpledark-light", "#44358F", "#eeeeee")
            )
        );
        
        /* Menu: Light | Active: Purple-Dark | Topbar: Purple-Dark | Components: Purple-Light Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("purpledark", "#44358F", null), 
                new Color("purpledark", "#44358F", null), 
                new Color("purplelight-light", "#9754B8", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Indigo | Topbar: Indigo | Components: Magenta Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("indigo", "#303498", null), 
                new Color("indigo", "#303498", null), 
                new Color("magenta-light", "#C03995", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Indigo | Topbar: Indigo | Components: Indigo Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("indigo", "#303498", null), 
                new Color("indigo", "#303498", null), 
                new Color("indigo-light", "#303498", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Cyan | Topbar: Seagreen | Components: Cyan Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("cyan", "#2199B5", null), 
                new Color("seagreen", "#354045", null), 
                new Color("cyan-light", "#2199B5", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Orange | Topbar: Seagreen | Components: Orange Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("orange", "#EC7A51", null), 
                new Color("seagreen", "#354045", null), 
                new Color("orange-light", "#EC7A51", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Teal-Light | Topbar: Seagreen | Components: Teal-Light Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("teallight", "#21B5AD", null), 
                new Color("seagreen", "#354045", null), 
                new Color("teallight-light", "#21B5AD", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Blue | Topbar: Dark | Components: Blue Accent */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("blue", "#2E88FF", null), 
                new Color("dark", "#252729", null), 
                new Color("blue-accent", "#2E88FF", "#FFB432")
            )
        );

        /* Menu: Light | Active: Purple | Topbar: Dark | Components: Purple Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("purple", "#636FC0", null), 
                new Color("dark", "#252729", null), 
                new Color("purple-light", "#636FC0", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Green | Topbar: Dark | Components: Green Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("green", "#21B567", null), 
                new Color("dark", "#252729", null), 
                new Color("green-light", "#21B567", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Cyan | Topbar: Dark | Components: Cyan Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("cyan", "#2199B5", null), 
                new Color("dark", "#252729", null), 
                new Color("cyan-light", "#2199B5", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Orange | Topbar: Dark | Components: Orange Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("orange", "#EC7A51", null), 
                new Color("dark", "#252729", null), 
                new Color("orange-light", "#EC7A51", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Indigo | Topbar: Dark | Components: Indigo Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("indigo", "#303498", null), 
                new Color("dark", "#252729", null), 
                new Color("indigo-light", "#303498", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Cyan | Topbar: Light | Components: Blue-Grey Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("cyan", "#2199B5", null), 
                new Color("light", "#f8fafc", null), 
                new Color("bluegrey-light", "#607D8B", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Teal-Light | Topbar: Light | Components: Teal-Light Accent */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("teallight", "#21B5AD", null), 
                new Color("light", "#f8fafc", null), 
                new Color("teallight-accent", "#21B5AD", "#FC970A")
            )
        );

        /* Menu: Light | Active: Purple | Topbar: Light | Components: Purple Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("purple", "#636FC0", null), 
                new Color("light", "#f8fafc", null), 
                new Color("purple-light", "#636FC0", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Cyan | Topbar: Light | Components: Cyan Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("cyan", "#2199B5", null), 
                new Color("light", "#f8fafc", null), 
                new Color("cyan-light", "#2199B5", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Orange | Topbar: Light | Components: Orange Dark */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("orange", "#EC7A51", null), 
                new Color("light", "#f8fafc", null), 
                new Color("orange-dark", "#EC7A51", "#212121")
            )
        );

        /* Menu: Light | Active: Teal-Dark | Topbar: Light | Components: Teal-Dark Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("tealdark", "#076D72", null), 
                new Color("light", "#f8fafc", null), 
                new Color("tealdark-light", "#076D72", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Indigo | Topbar: Light | Components: Indigo Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("indigo", "#303498", null), 
                new Color("light", "#f8fafc", null), 
                new Color("indigo-light", "#303498", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Indigo | Topbar: Blue-Dark | Components: Indigo Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("indigo", "#303498", null), 
                new Color("bluedark", "#232946", null), 
                new Color("indigo-light", "#303498", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Magenta | Topbar: Blue-Dark | Components: Magenta Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("magenta", "#C03995", null), 
                new Color("bluedark", "#232946", null), 
                new Color("magenta-light", "#C03995", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Purple-Light | Topbar: Blue-Dark | Components: Purple-Light Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("purplelight", "#9754B8", null), 
                new Color("bluedark", "#232946", null), 
                new Color("purplelight-light", "#9754B8", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Yellow | Topbar: Grey | Components: Blue-Grey Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("yellow", "#F3A841", null), 
                new Color("gray", "#525557", null), 
                new Color("bluegrey-light", "#607D8B", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Teal-Light | Topbar: Grey | Components: Teal-Light Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("teallight", "#21B5AD", null), 
                new Color("gray", "#525557", null), 
                new Color("teallight-light", "#21B5AD", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Magenta | Topbar: Greylight | Components: Magenta Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("magenta", "#21B5AD", null), 
                new Color("graylight", "#8B8E90", null), 
                new Color("magenta-light", "#C03995", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Magenta | Topbar: Greylight | Components: Yellow Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("magenta", "#21B5AD", null), 
                new Color("graylight", "#8B8E90", null), 
                new Color("yellow-light", "#F3A841", "#eeeeee")
            )
        );

        /* Menu: Blue-Dark | Active: Blue-Dark | Topbar: Blue-Dark | Components: Purple Light*/
        palettes.add(
            new Palette(
                new Color("colored", "#232946", null),
                new Color("bluedark", "#232946", null), 
                new Color("bluedark", "#232946", null), 
                new Color("purple-light", "#636FC0", "#eeeeee")
            )
        );

        /* Menu: Blue-Dark | Active: Blue-Dark | Topbar: Blue-Dark | Components: Yellow Light */
        palettes.add(
            new Palette(
                new Color("colored", "#232946", null),
                new Color("bluedark", "#232946", null), 
                new Color("bluedark", "#232946", null), 
                new Color("yellow-light", "#F3A841", "#eeeeee")
            )
        );

        /* Menu: Blue-Dark | Active: Blue-Dark | Topbar: Blue-Dark | Components: Purple-Light Light */
        palettes.add(
            new Palette(
                new Color("colored", "#232946", null),
                new Color("bluedark", "#232946", null), 
                new Color("bluedark", "#232946", null), 
                new Color("purplelight-light", "#9754B8", "#eeeeee")
            )
        );

        /* Menu: Blue-Dark | Active: Blue-Dark | Topbar: Blue-Dark | Components: Orange Light */
        palettes.add(
            new Palette(
                new Color("colored", "#232946", null),
                new Color("bluedark", "#232946", null), 
                new Color("bluedark", "#232946", null), 
                new Color("orange-light", "#EC7A51", "#eeeeee")
            )
        );

        /* Menu: Blue-Dark | Active: Blue-Dark | Topbar: Blue-Dark | Components: Indigo Accent */
        palettes.add(
            new Palette(
                new Color("colored", "#232946", null),
                new Color("bluedark", "#232946", null), 
                new Color("bluedark", "#232946", null), 
                new Color("indigo-accent", "#303498", "#D6BE0A")
            )
        );

        /* Menu: Light | Active: Teallight | Topbar: Tealdark | Components: Blue-Grey Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("teallight", "#21B5AD", null), 
                new Color("tealdark", "#076D72", null), 
                new Color("bluegrey-light", "#607D8B", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Teallight | Topbar: Tealdark | Components: Teal-Light Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("teallight", "#21B5AD", null), 
                new Color("tealdark", "#076D72", null), 
                new Color("teallight-light", "#21B5AD", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Teallight | Topbar: Tealdark | Components: Yellow Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("teallight", "#21B5AD", null), 
                new Color("tealdark", "#076D72", null), 
                new Color("yellow-light", "#F3A841", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Teallight | Topbar: Tealdark | Components: Orange Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("teallight", "#21B5AD", null), 
                new Color("tealdark", "#076D72", null), 
                new Color("orange-light", "#EC7A51", "#eeeeee")
            )
        );

        /* Menu: Light | Active: Teallight | Topbar: Tealdark | Components: Teal-Dark Accent */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("teallight", "#21B5AD", null), 
                new Color("tealdark", "#076D72", null), 
                new Color("tealdark-accent", "#076D72", "#C98634")
            )
        );

        /* Menu: Light | Active: Blue | Topbar: Dark | Components: Blue Light */
        palettes.add(
            new Palette(
                new Color("light", "#f8fafc", null),
                new Color("blue", "#2E88FF", null), 
                new Color("dark", "#252729", null), 
                new Color("blue-light", "#2E88FF", "#eeeeee")
            )
        );

        /* Menu: Blue | Active: Blue | Topbar: Light | Components: Blue Accent */
        palettes.add(
            new Palette(
                new Color("colored", "#2E88FF", null),
                new Color("blue", "#2E88FF", null), 
                new Color("light", "#f8fafc", null), 
                new Color("blue-accent", "#2E88FF", "#FFB432")
            )
        );

        /* Menu: Purple | Active: Purple | Topbar: Purple | Components: Purple Light */
        palettes.add(
            new Palette(
                new Color("colored", "#636FC0", null),
                new Color("purple", "#636FC0", null), 
                new Color("purple", "#636FC0", null), 
                new Color("purple-light", "#636FC0", "#eeeeee")
            )
        );

        /* Menu: Purple | Active: Purple | Topbar: Darkblue | Components: Indigo Light */
        palettes.add(
            new Palette(
                new Color("colored", "#636FC0", null),
                new Color("purple", "#636FC0", null), 
                new Color("bluedark", "#232946", null), 
                new Color("indigo-light", "#303498", "#eeeeee")
            )
        );

        /* Menu: Purple | Active: Purple | Topbar: Dark | Components: Indigo Light */
        palettes.add(
            new Palette(
                new Color("colored", "#636FC0", null),
                new Color("purple", "#636FC0", null), 
                new Color("dark", "#252729", null), 
                new Color("indigo-light", "#303498", "#eeeeee")
            )
        );

        /* Menu: Magenta | Active: Magenta | Topbar: Magenta | Components: Magenta Light */
        palettes.add(
            new Palette(
                new Color("colored", "#C03995", null),
                new Color("magenta", "#C03995", null), 
                new Color("magenta", "#C03995", null), 
                new Color("magenta-light", "#C03995", "#eeeeee")
            )
        );

        /* Menu: Magenta | Active: Magenta | Topbar: Dark | Components: Blue-Grey Light */
        palettes.add(
            new Palette(
                new Color("colored", "#C03995", null),
                new Color("magenta", "#C03995", null), 
                new Color("dark", "#252729", null), 
                new Color("bluegrey-light", "#607D8B", "#eeeeee")
            )
        );

        /* Menu: Magenta | Active: Magenta | Topbar: Light | Components: Purple-Light Light */
        palettes.add(
            new Palette(
                new Color("colored", "#C03995", null),
                new Color("magenta", "#C03995", null), 
                new Color("light", "#f8fafc", null), 
                new Color("purplelight-light", "#9754B8", "#eeeeee")
            )
        );

        /* Menu: Cyan | Active: Cyan | Topbar: Cyan | Components: Cyan Accent */
        palettes.add(
            new Palette(
                new Color("colored", "#2199B5", null),
                new Color("cyan", "#2199B5", null), 
                new Color("cyan", "#2199B5", null), 
                new Color("cyan-accent", "#2199B5", "#FFB600")
            )
        );

        /* Menu: Cyan | Active: Cyan | Topbar: Light | Components: Cyan Light */
        palettes.add(
            new Palette(
                new Color("colored", "#2199B5", null),
                new Color("cyan", "#2199B5", null), 
                new Color("light", "#f8fafc", null), 
                new Color("cyan-light", "#2199B5", "#eeeeee")
            )
        );

        /* Menu: Cyan | Active: Cyan | Topbar: Seagreen | Components: Blue-Grey Light */
        palettes.add(
            new Palette(
                new Color("colored", "#2199B5", null),
                new Color("cyan", "#2199B5", null), 
                new Color("seagreen", "#354045", null), 
                new Color("bluegrey-light", "#607D8B", "#eeeeee")
            )
        );

        /* Menu: Yellow | Active: Yellow | Topbar: Dark | Components: Yellow Dark */
        palettes.add(
            new Palette(
                new Color("colored", "#F3A841", null),
                new Color("yellow", "#F3A841", null), 
                new Color("dark", "#252729", null), 
                new Color("yellow-dark", "#F3A841", "#212121")
            )
        );

        /* Menu: Yellow | Active: Yellow | Topbar: Seagreen | Components: Blue-Grey Light */
        palettes.add(
            new Palette(
                new Color("colored", "#F3A841", null),
                new Color("yellow", "#F3A841", null), 
                new Color("seagreen", "#354045", null), 
                new Color("bluegrey-light", "#607D8B", "#eeeeee")
            )
        );

        /* Menu: Green | Active: Green | Topbar: Green | Components: Green Light */
        palettes.add(
            new Palette(
                new Color("colored", "#21B567", null),
                new Color("green", "#21B567", null), 
                new Color("green", "#21B567", null), 
                new Color("green-light", "#21B567", "#eeeeee")
            )
        );

        /* Menu: Green | Active: Green | Topbar: Dark | Components: Green Dark */
        palettes.add(
            new Palette(
                new Color("colored", "#21B567", null),
                new Color("green", "#21B567", null), 
                new Color("dark", "#252729", null), 
                new Color("green-dark", "#21B567", "#212121")
            )
        );

        /* Menu: Green | Active: Green | Topbar: Light | Components: Yellow Light */
        palettes.add(
            new Palette(
                new Color("colored", "#21B567", null),
                new Color("green", "#21B567", null), 
                new Color("light", "#f8fafc", null), 
                new Color("yellow-light", "#F3A841", "#eeeeee")
            )
        );

        /* Menu: Purple-Light | Active: Purple-Light | Topbar: Purple-Light | Components: Purple-Light Light */
        palettes.add(
            new Palette(
                new Color("colored", "#9754B8", null),
                new Color("purplelight", "#9754B8", null), 
                new Color("purplelight", "#9754B8", null), 
                new Color("purplelight-light", "#9754B8", "#eeeeee")
            )
        );
        
        /* Menu: Purple-Light | Active: Purple-Light | Topbar: Light | Components: Blue-Grey Light */
        palettes.add(
            new Palette(
                new Color("colored", "#9754B8", null),
                new Color("purplelight", "#9754B8", null), 
                new Color("light", "#f8fafc", null), 
                new Color("bluegrey-light", "#607D8B", "#eeeeee")
            )
        );

        /* Menu: Orange | Active: Orange | Topbar: Dark | Components: Orange Light */
        palettes.add(
            new Palette(
                new Color("colored", "#EC7A51", null),
                new Color("orange", "#EC7A51", null), 
                new Color("dark", "#252729", null), 
                new Color("orange-light", "#EC7A51", "#eeeeee")
            )
        );

        /* Menu: Orange | Active: Orange | Topbar: Light | Components: Yellow Light */
        palettes.add(
            new Palette(
                new Color("colored", "#EC7A51", null),
                new Color("orange", "#EC7A51", null), 
                new Color("light", "#f8fafc", null), 
                new Color("yellow-light", "#F3A841", "#eeeeee")
            )
        );

        /* Menu: Red | Active: Red | Topbar: Dark | Components: Red Light */
        palettes.add(
            new Palette(
                new Color("colored", "#FE566D", null),
                new Color("red", "#FE566D", null), 
                new Color("dark", "#252729", null), 
                new Color("red-light", "#FE566D", "#eeeeee")
            )
        );

        /* Menu: Red | Active: Red | Topbar: Light | Components: Blue-Grey Light */
        palettes.add(
            new Palette(
                new Color("colored", "#FE566D", null),
                new Color("red", "#FE566D", null), 
                new Color("light", "#f8fafc", null), 
                new Color("bluegrey-light", "#607D8B", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Teal-Dark | Topbar: Teal-Dark | Components: Teal-Dark Accent */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("tealdark", "#076D72", null), 
                new Color("tealdark", "#076D72", null), 
                new Color("tealdark-accent", "#076D72", "#C98634")
            )
        );

        /* Menu: Dark | Active: Teal-Dark | Topbar: Dark | Components: Blue-Grey Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("tealdark", "#076D72", null), 
                new Color("dark", "#252729", null), 
                new Color("bluegrey-light", "#607D8B", "#eeeeee")
            )
        );

        /*Menu: Dark | Active: Teal-Dark | Topbar: Light | Components: Teal-Dark Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("tealdark", "#076D72", null), 
                new Color("light", "#f8fafc", null), 
                new Color("tealdark-light", "#076D72", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Purple-Dark | Topbar: Purple-Dark | Components: Purple-Dark Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("purpledark", "#44358F", null), 
                new Color("purpledark", "#44358F", null), 
                new Color("purpledark-light", "#44358F", "#eeeeee")
            )
        );

        /* Menu: Dark | Active: Purple-Dark | Topbar: Purple | Components: Purple Light */
        palettes.add(
            new Palette(
                new Color("dark", "#252729", null),
                new Color("purpledark", "#44358F", null), 
                new Color("purple", "#636FC0", null), 
                new Color("purple-light", "#636FC0", "#eeeeee")
            )
        );

        /* Menu: Indigo | Active: Indigo | Topbar: Indigo | Components: Indigo Light */
        palettes.add(
            new Palette(
                new Color("colored", "#303498", null),
                new Color("indigo", "#303498", null), 
                new Color("indigo", "#303498", null), 
                new Color("indigo-light", "#303498", "#eeeeee")
            )
        );

        /* Menu: Indigo | Active: Indigo | Topbar: Darkblue | Components: Indigo Light */
        palettes.add(
            new Palette(
                new Color("colored", "#303498", null),
                new Color("indigo", "#303498", null), 
                new Color("bluedark", "#232946", null), 
                new Color("indigo-light", "#303498", "#eeeeee")
            )
        );

        /* Menu: Indigo | Active: Indigo | Topbar: Light | Components: Blue Light */
        palettes.add(
            new Palette(
                new Color("colored", "#303498", null),
                new Color("indigo", "#303498", null), 
                new Color("light", "#f8fafc", null), 
                new Color("blue-light", "#2E88FF", "#eeeeee")
            )
        );

        /************** Light - Dark Menu ********************/
        List<MenuTheme> menuThemes = new ArrayList<>();
        menuThemes.add(new MenuTheme("Teal Light", "teallight", "#21B5AD"));
        menuThemes.add(new MenuTheme("Blue", "blue","#2E88FF"));
        menuThemes.add(new MenuTheme("Purple", "purple","#636FC0"));
        menuThemes.add(new MenuTheme("Magenta", "magenta", "#C03995"));
        menuThemes.add(new MenuTheme("Cyan", "cyan", "#2199B5"));
        menuThemes.add(new MenuTheme("Yellow", "yellow","#F3A841"));
        menuThemes.add(new MenuTheme("Green", "green", "#21B567"));
        menuThemes.add(new MenuTheme("Purple Light", "purplelight","#9754B8"));
        menuThemes.add(new MenuTheme("Orange", "orange","#EC7A51"));
        menuThemes.add(new MenuTheme("Red", "red","#FE566D"));
        menuThemes.add(new MenuTheme("Teal Dark", "tealdark","#076D72"));
        menuThemes.add(new MenuTheme("Purple Dark", "purpledark", "#44358F"));
        menuThemes.add(new MenuTheme("Indigo", "indigo","#303498"));
        menuColors.put("light", menuThemes);
        menuColors.put("dark", menuThemes);
        
        /************** Custom Menu ********************/
        menuThemes = new ArrayList<>();
        menuThemes.add(new MenuTheme("Blue Dark", "bluedark", "#232946"));
        menuThemes.add(new MenuTheme("Teal Light", "teallight", "#21B5AD"));
        menuThemes.add(new MenuTheme("Blue", "blue","#2E88FF"));
        menuThemes.add(new MenuTheme("Purple", "purple","#636FC0"));
        menuThemes.add(new MenuTheme("Magenta", "magenta", "#C03995"));
        menuThemes.add(new MenuTheme("Cyan", "cyan", "#2199B5"));
        menuThemes.add(new MenuTheme("Yellow", "yellow","#F3A841"));
        menuThemes.add(new MenuTheme("Green", "green", "#21B567"));
        menuThemes.add(new MenuTheme("Purple Light", "purplelight","#9754B8"));
        menuThemes.add(new MenuTheme("Orange", "orange","#EC7A51"));
        menuThemes.add(new MenuTheme("Red", "red","#FE566D"));
        menuThemes.add(new MenuTheme("Teal Dark", "tealdark","#076D72"));
        menuThemes.add(new MenuTheme("Purple Dark", "purpledark", "#44358F"));
        menuThemes.add(new MenuTheme("Indigo", "indigo","#303498"));
        menuColors.put("colored", menuThemes);

        topbarThemes.add(new TopbarTheme("Seagreen", "seagreen", "#354045"));
        topbarThemes.add(new TopbarTheme("Dark", "dark", "#252729"));
        topbarThemes.add(new TopbarTheme("Light", "light", "#f8fafc"));
        topbarThemes.add(new TopbarTheme("Blue Dark", "bluedark", "#232946"));
        topbarThemes.add(new TopbarTheme("Blue", "blue", "#2E88FF"));
        topbarThemes.add(new TopbarTheme("Teal Light", "teallight","#21B5AD"));
        topbarThemes.add(new TopbarTheme("Purple", "purple", "#636FC0"));
        topbarThemes.add(new TopbarTheme("Magenta", "magenta","#C03995"));
        topbarThemes.add(new TopbarTheme("Yellow", "yellow", "#F3A841"));
        topbarThemes.add(new TopbarTheme("Green", "green", "#21B567"));
        topbarThemes.add(new TopbarTheme("Cyan", "cyan", "#2199B5"));
        topbarThemes.add(new TopbarTheme("Gray", "gray", "#525557"));
        topbarThemes.add(new TopbarTheme("Gray Light", "graylight", "#8B8E90"));
        topbarThemes.add(new TopbarTheme("Purple Light", "purplelight","#9754B8"));
        topbarThemes.add(new TopbarTheme("Orange", "orange", "#EC7A51"));
        topbarThemes.add(new TopbarTheme("Red", "red", "#FE566D"));
        topbarThemes.add(new TopbarTheme("Teal Dark", "tealdark", "#076D72"));
        topbarThemes.add(new TopbarTheme("Purple Dark", "purpledark", "#44358F"));
        topbarThemes.add(new TopbarTheme("Indigo", "indigo", "#303498"));

        componentThemes.add(new ComponentTheme("Blue Grey Accent", "bluegrey-accent","#607D8B","#8BC34A"));
        componentThemes.add(new ComponentTheme("Blue Grey Light", "bluegrey-light","#607D8B", "#eeeeee"));
        componentThemes.add(new ComponentTheme("Blue Grey Dark", "bluegrey-dark", "#607D8B", "#212121"));
        componentThemes.add(new ComponentTheme("Teal-Light Accent", "teallight-accent","#21B5AD","#FC970A"));
        componentThemes.add(new ComponentTheme("Teal-Light Light", "teallight-light","#21B5AD","#eeeeee"));
        componentThemes.add(new ComponentTheme("Teal-Light Dark", "teallight-dark","#21B5AD","#212121"));
        componentThemes.add(new ComponentTheme("Blue Accent", "blue-accent","#2E88FF", "#FFB432"));
        componentThemes.add(new ComponentTheme("Blue Light", "blue-light", "#2E88FF","#eeeeee"));
        componentThemes.add(new ComponentTheme("Blue Dark", "blue-dark","#2E88FF","#212121"));
        componentThemes.add(new ComponentTheme("Purple Accent", "purple-accent", "#636FC0","#52C1A3"));
        componentThemes.add(new ComponentTheme("Purple Light", "purple-light","#636FC0","#eeeeee"));
        componentThemes.add(new ComponentTheme("Purple Dark", "purple-dark", "#636FC0","#212121"));
        componentThemes.add(new ComponentTheme("Magenta Accent", "magenta-accent","#C03995","#21B5AD"));
        componentThemes.add(new ComponentTheme("Magenta Light", "magenta-light", "#C03995","#eeeeee"));
        componentThemes.add(new ComponentTheme("Magenta Dark", "magenta-dark", "#C03995","#212121"));
        componentThemes.add(new ComponentTheme("Yellow Accent", "yellow-accent","#F3A841","#2E88FF"));
        componentThemes.add(new ComponentTheme("Yellow Light", "yellow-light", "#F3A841","#eeeeee"));
        componentThemes.add(new ComponentTheme("Yellow Dark", "yellow-dark", "#F3A841","#212121"));
        componentThemes.add(new ComponentTheme("Green Accent", "green-accent","#21B567","#F9E60E"));
        componentThemes.add(new ComponentTheme("Green Light", "green-light","#21B567","#eeeeee"));
        componentThemes.add(new ComponentTheme("Green Dark", "green-dark", "#21B567","#212121"));
        componentThemes.add(new ComponentTheme("Cyan Accent", "cyan-accent","#2199B5","#FFB600"));
        componentThemes.add(new ComponentTheme("Cyan Light", "cyan-light", "#2199B5","#eeeeee"));
        componentThemes.add(new ComponentTheme("Cyan Dark", "cyan-dark", "#2199B5","#212121"));
        componentThemes.add(new ComponentTheme("Purple-Light Accent", "purplelight-accent", "#9754B8","#F7B500"));
        componentThemes.add(new ComponentTheme("Purple-Light Light", "purplelight-light", "#9754B8","#eeeeee"));
        componentThemes.add(new ComponentTheme("Purple-Light Dark", "purplelight-dark", "#9754B8","#212121"));
        componentThemes.add(new ComponentTheme("Orange Accent", "orange-accent", "#EC7A51","#15D3ED"));
        componentThemes.add(new ComponentTheme("Orange Light", "orange-light", "#EC7A51","#eeeeee"));
        componentThemes.add(new ComponentTheme("Orange Dark", "orange-dark", "#EC7A51","#212121"));
        componentThemes.add(new ComponentTheme("Red Accent", "red-accent", "#FE566D","#9ED611"));
        componentThemes.add(new ComponentTheme("Red Light", "red-light", "#FE566D","#eeeeee"));
        componentThemes.add(new ComponentTheme("Red Dark", "red-dark", "#FE566D","#212121"));
        componentThemes.add(new ComponentTheme("Teal-Dark Accent", "tealdark-accent","#076D72","#C98634"));
        componentThemes.add(new ComponentTheme("Teal-Dark Light", "tealdark-light", "#076D72","#eeeeee"));
        componentThemes.add(new ComponentTheme("Teal-Dark Dark", "tealdark-dark","#076D72","#212121"));
        componentThemes.add(new ComponentTheme("Purple-Dark Accent", "purpledark-accent","#44358F","#2CB2A8"));
        componentThemes.add(new ComponentTheme("Purple-Dark Light", "purpledark-light", "#44358F","#eeeeee"));
        componentThemes.add(new ComponentTheme("Purple-Dark Dark", "purpledark-dark","#44358F","#212121"));
        componentThemes.add(new ComponentTheme("Indigo Accent", "indigo-accent", "#303498","#D6BE0A"));
        componentThemes.add(new ComponentTheme("Indigo Light", "indigo-light", "#303498","#eeeeee"));
        componentThemes.add(new ComponentTheme("Indigo Dark", "indigo-dark", "#303498","#212121"));
    }
    
    public String getLayoutConfig() {
        StringBuilder sb = new StringBuilder();
        String menuModeClass = this.menuMode.equals("layout-static") ? "layout-static layout-static-active" : this.menuMode;
        String menuThemeClass = "layout-menu-" + (this.menuColor.equals("colored") ? this.menuTheme : this.menuColor);

        sb.append("layout-topbar-").append(this.topbarTheme);
        sb.append(" ").append(menuThemeClass);
        sb.append(" ").append(menuModeClass);

        return sb.toString();
    }

    public void changePalette(Palette palette) {
        this.setMenuColor(palette.menuColor.name);
        this.setMenuTheme(palette.menuTheme.name);
        this.setTopbarTheme(palette.topbarTheme.name);
        this.setComponentTheme(palette.componentTheme.name);
        this.setSelectedPalette(palette);
    }

    public List<Palette> getPalettes() {
        return palettes;
    }
    
    public String getLayout() {
        return "layout-" + this.layoutPrimaryColor;
    }

    public String getLayoutPrimaryColor() {
        return layoutPrimaryColor;
    }

    public void setLayoutPrimaryColor(String layoutPrimaryColor) {
        this.layoutPrimaryColor = layoutPrimaryColor;
    }

    public String getTopbarTheme() {
        return topbarTheme;
    }

    public boolean isDarkLogo() {
        return this.darkLogo;
    }
    
    public void setTopbarTheme(String topbarTheme) {
        this.topbarTheme = topbarTheme;
        this.darkLogo = this.topbarTheme.equals("light");
        this.setSelectedPalette(null);
    }

    public String getComponentTheme() {
        return componentTheme;
    }

    public void setComponentTheme(String componentTheme) {
        this.componentTheme = componentTheme;
        this.setSelectedPalette(null);
    }

    public String getMenuMode() {
        return menuMode;
    }

    public void setMenuMode(String menuMode) {
        this.menuMode = menuMode;
    }

    public String getMenuColor() {
        return menuColor;
    }

    public void setMenuColor(String menuColor) {
        this.menuColor = menuColor;
        this.menuTheme = this.menuColors.get(menuColor).get(0).getFile();
        this.setSelectedPalette(null);
    }

    public String getMenuTheme() {
        return menuTheme;
    }

    public void setMenuTheme(String menuTheme) {
        this.menuTheme = menuTheme;
        this.setSelectedPalette(null);
    }

    public boolean isGroupedMenu() {
        return this.groupedMenu;
    }

    public void setGroupedMenu(boolean value) {
        this.groupedMenu = value;
    }
    
    public List<ComponentTheme> getComponentThemes() {
        return componentThemes;
    }
    
    public List<TopbarTheme> getTopbarThemes() {
        return topbarThemes;
    }
    
    public Map<String, List<MenuTheme>> getMenuColors() {
        return menuColors;
    }

    public Palette getSelectedPalette() {
        return selectedPalette;
    }

    public void setSelectedPalette(Palette selectedPalette) {
        this.selectedPalette = selectedPalette;
    }

    public class Palette {

        Color menuColor;
        Color menuTheme;
        Color topbarTheme;
        Color componentTheme;

        public Palette(Color menuColor, Color menuTheme, Color topbarTheme, Color componentTheme) {
            this.menuColor = menuColor;
            this.menuTheme = menuTheme;
            this.topbarTheme = topbarTheme;
            this.componentTheme = componentTheme;
        }

        public Color getMenuColor() {
            return this.menuColor;
        }

        public Color getMenuTheme() {
            return this.menuTheme;
        }

        public Color getTopbarTheme() {
            return this.topbarTheme;
        }

        public Color getComponentTheme() {
            return this.componentTheme;
        }
    }

    public class Color {

        String name;
        String code;
        String accentCode;

        public Color(String name, String code, String accentCode) {
            this.name = name;
            this.code = code;
            this.accentCode = accentCode;
        }

        public String getName() {
            return this.name;
        }

        public String getCode() {
            return this.code;
        }

        public String getAccentCode() {
            return this.accentCode;
        }
    }

    public class MenuTheme {

        String name;
        String file;
        String color;

        public MenuTheme(String name, String file, String color) {
            this.name = name;
            this.file = file;
            this.color = color;
        }

        public String getName() {
            return this.name;
        }

        public String getFile() {
            return this.file;
        }

        public String getColor() {
            return this.color;
        }
    }

    public class TopbarTheme {

        String name;
        String file;
        String color;

        public TopbarTheme(String name, String file, String color) {
            this.name = name;
            this.file = file;
            this.color = color;
        }

        public String getName() {
            return this.name;
        }

        public String getFile() {
            return this.file;
        }

        public String getColor() {
            return this.color;
        }
    }

    public class ComponentTheme {

        String name;
        String file;
        String color;
        String accent;

        public ComponentTheme(String name, String file, String color, String accent) {
            this.name = name;
            this.file = file;
            this.color = color;
            this.accent = accent;
        }

        public String getName() {
            return this.name;
        }

        public String getFile() {
            return this.file;
        }

        public String getColor() {
            return this.color;
        }
        
        public String getAccent() {
            return this.accent;
        }
    }
}
