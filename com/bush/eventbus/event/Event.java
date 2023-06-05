//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.bush.eventbus.event;

public abstract class Event
{
    private boolean cancelled;
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        if (this.isCancellable()) {
            this.cancelled = cancelled;
        }
    }
    
    protected abstract boolean isCancellable();
}
