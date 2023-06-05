//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.bush.eventbus.handler.handlers;

import com.bush.eventbus.handler.*;
import java.lang.reflect.*;
import java.util.function.*;
import com.bush.eventbus.event.*;
import com.bush.eventbus.util.*;

public class ReflectHandler extends Handler
{
    private final Method listener;
    
    public ReflectHandler(final Method listener, final Object subscriber, final Consumer<String> logger) {
        super(listener, subscriber, (Consumer)logger);
        this.listener = listener;
    }
    
    public void invoke(final Event event) {
        try {
            this.listener.invoke(this.subscriber, event);
        }
        catch (Exception exception) {
            Util.logReflectionExceptions(exception, Util.formatMethodName(this.listener), this.logger);
            exception.printStackTrace();
        }
    }
}
