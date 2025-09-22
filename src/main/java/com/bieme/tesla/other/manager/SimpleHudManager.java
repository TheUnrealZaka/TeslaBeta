package com.bieme.tesla.other.manager;

import com.bieme.tesla.other.guiscreen.render.pinnables.Pinnable;
import java.util.ArrayList;
import java.util.List;

public class SimpleHudManager {
    
    private List<Pinnable> pinnables = new ArrayList<>();
    
    public void addPinnable(Pinnable pinnable) {
        pinnables.add(pinnable);
    }
    
    public List<Pinnable> get_array_huds() {
        return pinnables;
    }
    
    public Pinnable get_pinnable_with_tag(String tag) {
        for (Pinnable pinnable : pinnables) {
            if (pinnable.get_tag().equalsIgnoreCase(tag)) {
                return pinnable;
            }
        }
        return null;
    }
}