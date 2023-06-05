//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.bush.eventbus.bus;

import com.bush.eventbus.handler.*;
import com.bush.eventbus.event.*;
import com.bush.eventbus.util.*;
import com.bush.eventbus.annotation.*;
import java.lang.annotation.*;
import java.util.concurrent.*;
import java.util.*;
import java.util.function.*;
import java.lang.reflect.*;
import com.bush.eventbus.handler.handlers.*;

public class EventBus
{
    private final Set<Object> subscribers;
    private Map<Class<?>, List<Handler>> handlerMap;
    private Class<? extends Handler> handlerType;
    private final Consumer<String> errorLogger;
    private final Consumer<String> infoLogger;
    
    public EventBus() {
        this(LambdaHandler.class);
    }
    
    public EventBus(final Class<? extends Handler> handlerType) {
        this(handlerType, message -> System.out.println("[EVENTBUS]: " + message));
    }
    
    public EventBus(final Consumer<String> messageLogger) {
        this(LambdaHandler.class, messageLogger, messageLogger);
    }
    
    public EventBus(final Consumer<String> errorLogger, final Consumer<String> infoLogger) {
        this(LambdaHandler.class, errorLogger, infoLogger);
    }
    
    public EventBus(final Class<? extends Handler> handlerType, final Consumer<String> messageLogger) {
        this(handlerType, messageLogger, messageLogger);
    }
    
    public EventBus(final Class<? extends Handler> handlerType, final Consumer<String> errorLogger, final Consumer<String> infoLogger) {
        this.subscribers = Collections.synchronizedSet(new HashSet<Object>());
        this.handlerMap = new ConcurrentHashMap<Class<?>, List<Handler>>();
        this.handlerType = handlerType;
        this.errorLogger = errorLogger;
        this.infoLogger = infoLogger;
    }
    
    public void subscribe(final Object subscriber) {
        if (subscriber == null || this.subscribers.contains(subscriber)) {
            return;
        }
        this.subscribers.add(subscriber);
        this.addHandlers(subscriber);
    }
    
    public boolean post(final Event event) {
        if (event == null) {
            return false;
        }
        final List<Handler> handlers = this.handlerMap.get(event.getClass());
        if (handlers == null) {
            return false;
        }
        for (final Handler handler : handlers) {
            if (!event.isCancelled() || handler.shouldRecieveCancelled()) {
                handler.invoke(event);
            }
        }
        return event.isCancelled();
    }
    
    public void unsubscribe(final Object subscriber) {
        if (subscriber == null || !this.subscribers.contains(subscriber)) {
            return;
        }
        this.subscribers.remove(subscriber);
        this.handlerMap.values().forEach(handlers -> handlers.removeIf(handler -> handler.isSubscriber(subscriber)));
        this.handlerMap.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }
    
    public void getInfo() {
        final String format = "%-25s%-25s";
        this.infoLogger.accept("============ EVENTBUS INFO ============");
        this.infoLogger.accept(String.format(format, "Handler type", this.handlerType.getSimpleName()));
        this.infoLogger.accept(String.format(format, "Subscriber count", this.subscribers.size()));
        final int total = this.handlerMap.values().stream().mapToInt(Collection::size).sum();
        this.infoLogger.accept(String.format(format, "Listener count", total));
        final int listenerCount;
        final String eventName;
        final String format2;
        this.handlerMap.forEach((eventType, handlers) -> {
            listenerCount = handlers.size();
            eventName = Util.formatClassName(eventType);
            this.infoLogger.accept(String.format(format2, eventName, listenerCount));
        });
    }
    
    public Class<? extends Handler> getHandlerType() {
        return this.handlerType;
    }
    
    public void setHandlerType(final Class<? extends Handler> handlerType) {
        if (this.handlerType == handlerType) {
            return;
        }
        this.handlerType = handlerType;
        this.handlerMap = new ConcurrentHashMap<Class<?>, List<Handler>>();
        this.subscribers.forEach(this::addHandlers);
    }
    
    private void addHandlers(final Object subscriber) {
        final boolean isClass = subscriber instanceof Class;
        final Class<?>[] parameters;
        List<Handler> handlers;
        Arrays.stream(((Class)(isClass ? subscriber : subscriber.getClass())).getMethods()).filter(method -> method.isAnnotationPresent((Class<? extends Annotation>)EventListener.class)).filter(method -> isClass == Modifier.isStatic(method.getModifiers())).forEach(method -> {
            parameters = method.getParameterTypes();
            if (method.getReturnType() != Void.TYPE) {
                this.errorLogger.accept(method + " has an incorrect return type. Listeners must return void.");
            }
            else if (parameters.length != 1 || !Event.class.isAssignableFrom(parameters[0])) {
                this.errorLogger.accept(method + " has incorrect parameters. Listeners must have one parameter that is a subclass of Event.");
            }
            else {
                handlers = this.handlerMap.computeIfAbsent(parameters[0], v -> new CopyOnWriteArrayList());
                handlers.add(this.createHandler(method, subscriber));
                handlers.sort(Comparator.comparing((Function<? super Handler, ? extends Comparable>)Handler::getPriority));
            }
        });
    }
    
    private Handler createHandler(final Method method, final Object object) {
        try {
            return (Handler)this.handlerType.getDeclaredConstructor(Method.class, Object.class, Consumer.class).newInstance(method, object, this.errorLogger);
        }
        catch (Exception exception) {
            Util.logReflectionExceptions(exception, Util.formatClassName(this.handlerType), this.errorLogger);
            this.errorLogger.accept("Defaulting to ReflectHandler for listener method " + Util.formatMethodName(method) + ".");
            exception.printStackTrace();
            return new ReflectHandler(method, object, this.errorLogger);
        }
    }
}
