//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.bush.eventbus.handler.handlers;

import java.util.concurrent.*;
import com.bush.eventbus.handler.*;
import java.util.function.*;
import java.lang.reflect.*;
import com.bush.eventbus.event.*;
import org.objectweb.asm.*;
import com.bush.eventbus.util.*;

public class ASMHandler extends Handler
{
    private static final ASMLoader loader;
    private static final String handlername;
    private static final String methodname;
    private static int listeners;
    private static final ConcurrentHashMap<Method, DynamicHandler> listenercache;
    private final DynamicHandler dynamicHandler;
    
    public ASMHandler(final Method listener, final Object subscriber, final Consumer<String> logger) throws Exception {
        super(listener, subscriber, (Consumer)logger);
        if (ASMHandler.listenercache.containsKey(listener)) {
            this.dynamicHandler = ASMHandler.listenercache.get(listener);
        }
        else {
            if (Modifier.isStatic(listener.getModifiers())) {
                this.dynamicHandler = (DynamicHandler)this.createWrapper(listener).newInstance();
            }
            else {
                this.dynamicHandler = (DynamicHandler)this.createWrapper(listener).getConstructor(Object.class).newInstance(subscriber);
            }
            ASMHandler.listenercache.put(listener, this.dynamicHandler);
        }
    }
    
    public void invoke(final Event event) {
        this.dynamicHandler.invoke(event);
    }
    
    public Class<?> createWrapper(final Method method) {
        final ClassWriter cw = new ClassWriter(0);
        final boolean isStatic = Modifier.isStatic(method.getModifiers());
        final String name = this.getUniqueName(method);
        final String desc = name.replace('.', '/');
        final String instType = Type.getInternalName((Class)method.getDeclaringClass());
        final String eventType = Type.getInternalName((Class)method.getParameterTypes()[0]);
        cw.visit(50, 33, desc, (String)null, "java/lang/Object", new String[] { ASMHandler.handlername });
        cw.visitSource(".dynamic", (String)null);
        if (!isStatic) {
            cw.visitField(1, "instance", "Ljava/lang/Object;", (String)null, (Object)null).visitEnd();
        }
        MethodVisitor mv = cw.visitMethod(1, "<init>", isStatic ? "()V" : "(Ljava/lang/Object;)V", (String)null, (String[])null);
        mv.visitCode();
        mv.visitVarInsn(25, 0);
        mv.visitMethodInsn(183, "java/lang/Object", "<init>", "()V", false);
        if (!isStatic) {
            mv.visitVarInsn(25, 0);
            mv.visitVarInsn(25, 1);
            mv.visitFieldInsn(181, desc, "instance", "Ljava/lang/Object;");
        }
        mv.visitInsn(177);
        mv.visitMaxs(2, 2);
        mv.visitEnd();
        mv = cw.visitMethod(1, "invoke", ASMHandler.methodname, (String)null, (String[])null);
        mv.visitCode();
        mv.visitVarInsn(25, 0);
        if (!isStatic) {
            mv.visitFieldInsn(180, desc, "instance", "Ljava/lang/Object;");
            mv.visitTypeInsn(192, instType);
        }
        mv.visitVarInsn(25, 1);
        mv.visitTypeInsn(192, eventType);
        mv.visitMethodInsn(isStatic ? 184 : 182, instType, method.getName(), Type.getMethodDescriptor(method), false);
        mv.visitInsn(177);
        mv.visitMaxs(2, 2);
        mv.visitEnd();
        cw.visitEnd();
        return ASMHandler.loader.define(name, cw.toByteArray());
    }
    
    private String getUniqueName(final Method method) {
        return String.format("%s_%d_%s", "ASMListener", ASMHandler.listeners++, Util.formatMethodName(method));
    }
    
    static {
        loader = new ASMLoader();
        handlername = Type.getInternalName((Class)DynamicHandler.class);
        methodname = Type.getMethodDescriptor(DynamicHandler.class.getDeclaredMethods()[0]);
        listenercache = new ConcurrentHashMap<Method, DynamicHandler>();
    }
    
    private static class ASMLoader extends ClassLoader
    {
        private ASMLoader() {
            super(ASMLoader.class.getClassLoader());
        }
        
        public Class<?> define(final String name, final byte[] data) {
            return this.defineClass(name, data, 0, data.length);
        }
    }
}
