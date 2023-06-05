//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.bush.eventbus.handler;

import java.util.function.*;
import java.lang.reflect.*;
import com.bush.eventbus.annotation.*;
import com.bush.eventbus.event.*;

public abstract class Handler
{
    private final ListenerPriority priority;
    private final boolean receiveCancelled;
    protected final Object subscriber;
    protected final Consumer<String> logger;
    
    public Handler(final Method listener, final Object subscriber, final Consumer<String> logger) {
        listener.setAccessible(true);
        final EventListener annotation = listener.getAnnotation(EventListener.class);
        this.priority = annotation.priority();
        this.receiveCancelled = annotation.recieveCancelled();
        this.subscriber = subscriber;
        this.logger = logger;
    }
    
    public abstract void invoke(final Event p0);
    
    public ListenerPriority getPriority() {
        return this.priority;
    }
    
    public boolean shouldRecieveCancelled() {
        return this.receiveCancelled;
    }
    
    public boolean isSubscriber(final Object object) {
        return this.subscriber.equals(object);
    }
}
