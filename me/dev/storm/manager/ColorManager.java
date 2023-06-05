//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.manager;

import java.awt.*;
import me.dev.storm.util.*;
import me.dev.storm.features.modules.client.*;
import me.dev.storm.features.gui.components.*;

public class ColorManager
{
    private float red;
    private float green;
    private float blue;
    private float alpha;
    private Color color;
    private static Color current;
    
    public ColorManager() {
        this.red = 1.0f;
        this.green = 1.0f;
        this.blue = 1.0f;
        this.alpha = 1.0f;
        this.color = new Color(this.red, this.green, this.blue, this.alpha);
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public int getColorAsInt() {
        return ColorUtil.toRGBA(this.color);
    }
    
    public int getColorAsIntFullAlpha() {
        return ColorUtil.toRGBA(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 255));
    }
    
    public static boolean isRainbow() {
        return (boolean)ClickGui.getInstance().rainbow.getValue();
    }
    
    public static Color getCurrent() {
        if (isRainbow()) {
            return getRainbow();
        }
        return ColorManager.current;
    }
    
    public int getCurrentGui(final int alpha) {
        if (isRainbow()) {
            return ColorUtil.rainbow(Component.counter1[0] * (int)ClickGui.getInstance().rainbowHue.getValue()).getRGB();
        }
        return ColorUtil.toRGBA(ColorUtil.injectAlpha(ColorManager.current, alpha));
    }
    
    public int getCurrentWithAlpha(final int alpha) {
        if (isRainbow()) {
            return ColorUtil.toRGBA(ColorUtil.injectAlpha(getRainbow(), alpha));
        }
        return ColorUtil.toRGBA(ColorUtil.injectAlpha(ColorManager.current, alpha));
    }
    
    public static Color getRainbow() {
        return ColorUtil.rainbow((int)ClickGui.getInstance().rainbowHue.getValue());
    }
    
    public int getColorWithAlpha(final int alpha) {
        if (ClickGui.getInstance().rainbow.getValue()) {
            return ColorUtil.rainbow(Component.counter1[0] * (int)ClickGui.getInstance().rainbowHue.getValue()).getRGB();
        }
        return ColorUtil.toRGBA(new Color(this.red, this.green, this.blue, alpha / 255.0f));
    }
    
    public Color getFriendColor(final int alpha) {
        return new Color(0, 191, 255, alpha);
    }
    
    public void setColor(final float red, final float green, final float blue, final float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        this.updateColor();
    }
    
    public void updateColor() {
        this.setColor(new Color(this.red, this.green, this.blue, this.alpha));
    }
    
    public void setColor(final int red, final int green, final int blue, final int alpha) {
        this.red = red / 255.0f;
        this.green = green / 255.0f;
        this.blue = blue / 255.0f;
        this.alpha = alpha / 255.0f;
        this.updateColor();
    }
    
    public void setRed(final float red) {
        this.red = red;
        this.updateColor();
    }
    
    public void setGreen(final float green) {
        this.green = green;
        this.updateColor();
    }
    
    public void setBlue(final float blue) {
        this.blue = blue;
        this.updateColor();
    }
    
    public void setAlpha(final float alpha) {
        this.alpha = alpha;
        this.updateColor();
    }
    
    static {
        ColorManager.current = new Color(-1);
    }
}
