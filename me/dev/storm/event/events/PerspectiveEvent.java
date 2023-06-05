//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.event.events;

import me.dev.storm.event.*;

public class PerspectiveEvent extends EventStage
{
    private float aspect;
    
    public PerspectiveEvent(final float aspect) {
        this.aspect = aspect;
    }
    
    public float getAspect() {
        return this.aspect;
    }
    
    public void setAspect(final float aspect) {
        this.aspect = aspect;
    }
}
