//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.setting;

import java.util.function.*;
import me.dev.storm.features.*;
import java.util.*;
import me.dev.storm.event.events.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.awt.*;

public class Setting<T>
{
    private final String name;
    private final T defaultValue;
    private T value;
    private T plannedValue;
    private T min;
    private T max;
    private boolean hasRestriction;
    private Predicate<T> visibility;
    private String description;
    private Feature feature;
    private List<String> combobox;
    private String current;
    private boolean button;
    private String label;
    private boolean shouldRenderStringName;
    private double slider;
    public boolean open;
    public boolean parent;
    public boolean hideAlpha;
    public boolean hasBoolean;
    public boolean booleanValue;
    double dVal;
    int mode;
    
    public Setting(final String name, final T defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.plannedValue = defaultValue;
        this.description = "";
    }
    
    public Setting(final String name, final T defaultValue, final String description) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.plannedValue = defaultValue;
        this.description = description;
    }
    
    public Setting(final String name, final T defaultValue, final T min, final T max, final String description) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.plannedValue = defaultValue;
        this.description = description;
        this.hasRestriction = true;
    }
    
    public Setting(final String name, final T defaultValue, final T min, final T max) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.plannedValue = defaultValue;
        this.description = "";
        this.hasRestriction = true;
    }
    
    public Setting(final String name, final T defaultValue, final T min, final T max, final Predicate<T> visibility, final String description) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.plannedValue = defaultValue;
        this.visibility = visibility;
        this.description = description;
        this.hasRestriction = true;
    }
    
    public Setting(final String name, final T defaultValue, final T min, final T max, final Predicate<T> visibility) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.plannedValue = defaultValue;
        this.visibility = visibility;
        this.description = "";
        this.hasRestriction = true;
    }
    
    public Setting(final String name, final T defaultValue, final Predicate<T> visibility) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.visibility = visibility;
        this.plannedValue = defaultValue;
    }
    
    public String getName() {
        return this.name;
    }
    
    public T getValue() {
        return this.value;
    }
    
    public void setValue(final T value) {
        this.setPlannedValue(value);
        if (this.hasRestriction) {
            if (((Number)this.min).floatValue() > ((Number)value).floatValue()) {
                this.setPlannedValue(this.min);
            }
            if (((Number)this.max).floatValue() < ((Number)value).floatValue()) {
                this.setPlannedValue(this.max);
            }
        }
        final ClientEvent event = new ClientEvent(this);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (!event.isCanceled()) {
            this.value = this.plannedValue;
        }
        else {
            this.plannedValue = this.value;
        }
    }
    
    public T getPlannedValue() {
        return this.plannedValue;
    }
    
    public void setPlannedValue(final T value) {
        this.plannedValue = value;
    }
    
    public Setting<T> setParent() {
        this.parent = true;
        return this;
    }
    
    public String getCurrentEnumName() {
        return EnumConverter.getProperName((Enum)this.value);
    }
    
    public T getMin() {
        return this.min;
    }
    
    public void setMin(final T min) {
        this.min = min;
    }
    
    public T getMax() {
        return this.max;
    }
    
    public int getMVal() {
        return this.mode;
    }
    
    public void setMax(final T max) {
        this.max = max;
    }
    
    public boolean get_value(final boolean type) {
        return this.button;
    }
    
    public List<String> get_values() {
        return this.combobox;
    }
    
    public String get_current_value() {
        return this.current;
    }
    
    public String get_value(final String type) {
        return this.label;
    }
    
    public double get_value(final double type) {
        return this.slider;
    }
    
    public int get_value(final int type) {
        return (int)Math.round(this.slider);
    }
    
    public void setValueNoEvent(final T value) {
        this.setPlannedValue(value);
        if (this.hasRestriction) {
            if (((Number)this.min).floatValue() > ((Number)value).floatValue()) {
                this.setPlannedValue(this.min);
            }
            if (((Number)this.max).floatValue() < ((Number)value).floatValue()) {
                this.setPlannedValue(this.max);
            }
        }
        this.value = this.plannedValue;
    }
    
    public Feature getFeature() {
        return this.feature;
    }
    
    public void setFeature(final Feature feature) {
        this.feature = feature;
    }
    
    public int getEnum(final String input) {
        for (int i = 0; i < this.value.getClass().getEnumConstants().length; ++i) {
            final Enum e = (Enum)this.value.getClass().getEnumConstants()[i];
            if (e.name().equalsIgnoreCase(input)) {
                return i;
            }
        }
        return -1;
    }
    
    public Setting<T> injectBoolean(final boolean valueIn) {
        if (this.value instanceof Color) {
            this.hasBoolean = true;
            this.booleanValue = valueIn;
        }
        return this;
    }
    
    public Setting<T> hideAlpha() {
        this.hideAlpha = true;
        return this;
    }
    
    public void setEnumValue(final String value) {
        for (final Enum e : (Enum[])((Enum)this.value).getClass().getEnumConstants()) {
            if (e.name().equalsIgnoreCase(value)) {
                this.value = (T)e;
            }
        }
    }
    
    public String currentEnumName() {
        return EnumConverter.getProperName((Enum)this.value);
    }
    
    public int currentEnum() {
        return EnumConverter.currentEnum((Enum)this.value);
    }
    
    public void increaseEnum() {
        this.plannedValue = (T)EnumConverter.increaseEnum((Enum)this.value);
        final ClientEvent event = new ClientEvent(this);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (!event.isCanceled()) {
            this.value = this.plannedValue;
        }
        else {
            this.plannedValue = this.value;
        }
    }
    
    public void increaseEnumNoEvent() {
        this.value = (T)EnumConverter.increaseEnum((Enum)this.value);
    }
    
    public String getType() {
        if (this.isEnumSetting()) {
            return "Enum";
        }
        return this.getClassName(this.defaultValue);
    }
    
    public <T> String getClassName(final T value) {
        return value.getClass().getSimpleName();
    }
    
    public String getDescription() {
        if (this.description == null) {
            return "";
        }
        return this.description;
    }
    
    public boolean isNumberSetting() {
        return this.value instanceof Double || this.value instanceof Integer || this.value instanceof Short || this.value instanceof Long || this.value instanceof Float;
    }
    
    public Setting<T> setRenderName(final boolean renderName) {
        this.shouldRenderStringName = renderName;
        return this;
    }
    
    public boolean isEnumSetting() {
        return !this.isNumberSetting() && !(this.value instanceof String) && !(this.value instanceof Bind) && !(this.value instanceof Character) && !(this.value instanceof Color) && !(this.value instanceof Boolean);
    }
    
    public boolean isOpen() {
        return this.open && this.parent;
    }
    
    public boolean isStringSetting() {
        return this.value instanceof String;
    }
    
    public T getDefaultValue() {
        return this.defaultValue;
    }
    
    public String getValueAsString() {
        return this.value.toString();
    }
    
    public boolean hasRestriction() {
        return this.hasRestriction;
    }
    
    public void setVisibility(final Predicate<T> visibility) {
        this.visibility = visibility;
    }
    
    public boolean isVisible() {
        return this.visibility == null || this.visibility.test(this.getValue());
    }
    
    public double getDVal() {
        return this.dVal;
    }
}
