//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.bush.eventbus.util;

import java.util.function.*;
import java.lang.reflect.*;

public class Util
{
    public static void logReflectionExceptions(final Exception exception, final String name, final Consumer<String> logger) {
        final String simpleName = exception.getClass().getSimpleName();
        switch (simpleName) {
            case "IllegalAccessException": {
                logger.accept(name + " could not be accessed.");
                break;
            }
            case "InstantiationException": {
                logger.accept(name + " could not be instantiated.");
                break;
            }
            case "InvocationTargetException": {
                logger.accept(name + " threw an exception.");
                break;
            }
            case "NoSuchMethodException": {
                logger.accept(name + " has an incorrect constructor. See me.bush.eventbus.handler.Handler");
                break;
            }
        }
    }
    
    public static String formatMethodName(final Method method) {
        final Class<?> clazz = method.getDeclaringClass();
        final String packageName = (clazz.getPackage() != null) ? clazz.getPackage().getName() : "";
        return clazz.getName().replace(packageName + ".", "") + "#" + method.getName();
    }
    
    public static String formatClassName(final Class<?> clazz) {
        final String packageName = (clazz.getPackage() != null) ? clazz.getPackage().getName() : "";
        return clazz.getName().replace(packageName + ".", "");
    }
}
