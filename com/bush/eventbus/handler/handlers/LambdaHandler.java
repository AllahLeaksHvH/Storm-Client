//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.bush.eventbus.handler.handlers;

import java.util.concurrent.*;
import com.bush.eventbus.handler.*;
import java.util.function.*;
import java.lang.reflect.*;
import com.bush.eventbus.event.*;
import java.lang.invoke.*;

public class LambdaHandler extends Handler
{
    private static final ConcurrentHashMap<Method, DynamicHandler> handlerCache;
    private final DynamicHandler dynamicHandler;
    
    public LambdaHandler(final Method listener, final Object subscriber, final Consumer<String> logger) throws Throwable {
        super(listener, subscriber, (Consumer)logger);
        if (LambdaHandler.handlerCache.containsKey(listener)) {
            this.dynamicHandler = LambdaHandler.handlerCache.get(listener);
        }
        else {
            final MethodHandles.Lookup lookup = MethodHandles.lookup();
            final boolean isStatic = Modifier.isStatic(listener.getModifiers());
            final MethodType targetSignature = MethodType.methodType(DynamicHandler.class);
            final CallSite callSite = LambdaMetafactory.metafactory(lookup, "invoke", isStatic ? targetSignature : targetSignature.appendParameterTypes(subscriber.getClass()), MethodType.methodType(Void.TYPE, Event.class), lookup.unreflect(listener), MethodType.methodType(Void.TYPE, listener.getParameterTypes()[0]));
            final MethodHandle target = callSite.getTarget();
            this.dynamicHandler = (DynamicHandler)(isStatic ? target.invoke() : target.invoke(subscriber));
            LambdaHandler.handlerCache.put(listener, this.dynamicHandler);
        }
    }
    
    public void invoke(final Event event) {
        this.dynamicHandler.invoke(event);
    }
    
    static {
        handlerCache = new ConcurrentHashMap<Method, DynamicHandler>();
    }
}
