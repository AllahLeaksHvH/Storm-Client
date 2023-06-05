

//Decompiled by Procyon!

package me.dev.storm.event;

import net.minecraftforge.fml.common.eventhandler.*;

public class EventHandler extends Event
{
    private int stage;
    
    public EventHandler() {
    }
    
    public EventHandler(final int stage) {
        this.stage = stage;
    }
    
    public int getStage() {
        return this.stage;
    }
    
    public void setStage(final int stage) {
        this.stage = stage;
    }
}
