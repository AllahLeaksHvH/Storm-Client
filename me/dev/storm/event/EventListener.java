//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.event;

import net.minecraft.client.*;
import me.dev.storm.*;

public abstract class EventListener<E, M>
{
    protected final Minecraft mc;
    protected final Class<? extends E> listener;
    public M module;
    
    public EventListener(final Class<? extends E> listener) {
        this.mc = Storm.mc;
        this.listener = listener;
    }
    
    public EventListener(final Class<? extends E> listener, final M module) {
        this.mc = Storm.mc;
        this.listener = listener;
        this.module = module;
    }
    
    public Class<? extends E> getListener() {
        return this.listener;
    }
    
    public void invoke(final Object object) {
    }
}
