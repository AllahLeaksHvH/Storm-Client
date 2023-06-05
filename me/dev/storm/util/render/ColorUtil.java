//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.util.render;

import org.lwjgl.opengl.*;
import java.awt.*;
import me.dev.storm.features.modules.client.*;

public class ColorUtil
{
    public ColorUtil(final int i, final int i1, final int i2, final int i3) {
    }
    
    public static void color(final int color) {
        GL11.glColor4f((color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, (color >> 24 & 0xFF) / 255.0f);
    }
    
    public static int shadeColour(final int color, final int precent) {
        final int r = ((color & 0xFF0000) >> 16) * (100 + precent) / 100;
        final int g = ((color & 0xFF00) >> 8) * (100 + precent) / 100;
        final int b = (color & 0xFF) * (100 + precent) / 100;
        return new Color(r, g, b).hashCode();
    }
    
    public static int getRainbow(final int speed, final float s) {
        final float hue = (float)(System.currentTimeMillis() % speed);
        return Color.getHSBColor(hue / speed, s, 1.0f).getRGB();
    }
    
    public static int getRainbow(final int speed, final int offset, final float s) {
        final float hue = (float)(System.currentTimeMillis() % speed + offset * 15L);
        return Color.getHSBColor(hue / speed, s, 1.0f).getRGB();
    }
    
    public static int toRGBA(final int r, final int g, final int b) {
        return toRGBA(r, g, b, 255);
    }
    
    public static int toRGBA(final int r, final int g, final int b, final int a) {
        return (r << 16) + (g << 8) + b + (a << 24);
    }
    
    public static int toARGB(final int r, final int g, final int b, final int a) {
        return new Color(r, g, b, a).getRGB();
    }
    
    public static int toRGBA(final float r, final float g, final float b, final float a) {
        return toRGBA((int)(r * 255.0f), (int)(g * 255.0f), (int)(b * 255.0f), (int)(a * 255.0f));
    }
    
    public static Color rainbow(final int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        return Color.getHSBColor((float)((rainbowState %= 360.0) / 360.0), (float)ClickGui.getInstance().rainbowSaturation.getValue() / 255.0f, (float)ClickGui.getInstance().rainbowBrightness.getValue() / 255.0f);
    }
    
    public static int toRGBA(final float[] colors) {
        if (colors.length != 4) {
            throw new IllegalArgumentException("colors[] must have a length of 4!");
        }
        return toRGBA(colors[0], colors[1], colors[2], colors[3]);
    }
    
    public static int toRGBA(final double[] colors) {
        if (colors.length != 4) {
            throw new IllegalArgumentException("colors[] must have a length of 4!");
        }
        return toRGBA((float)colors[0], (float)colors[1], (float)colors[2], (float)colors[3]);
    }
    
    public static int toRGBA(final Color color) {
        return toRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
    
    public static class ColorName
    {
        public int r;
        public int g;
        public int b;
        public String name;
        
        public ColorName(final String name, final int r, final int g, final int b) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.name = name;
        }
        
        public int computeMSE(final int pixR, final int pixG, final int pixB) {
            return ((pixR - this.r) * (pixR - this.r) + (pixG - this.g) * (pixG - this.g) + (pixB - this.b) * (pixB - this.b)) / 3;
        }
        
        public int getR() {
            return this.r;
        }
        
        public int getG() {
            return this.g;
        }
        
        public int getB() {
            return this.b;
        }
        
        public String getName() {
            return this.name;
        }
    }
    
    public static class Colors
    {
        public static final int WHITE;
        public static final int BLACK;
        public static final int RED;
        public static final int GREEN;
        public static final int BLUE;
        public static final int ORANGE;
        public static final int PURPLE;
        public static final int GRAY;
        public static final int DARK_RED;
        public static final int YELLOW;
        public static final int RAINBOW = Integer.MIN_VALUE;
        
        static {
            WHITE = ColorUtil.toRGBA(255, 255, 255, 255);
            BLACK = ColorUtil.toRGBA(0, 0, 0, 255);
            RED = ColorUtil.toRGBA(255, 0, 0, 255);
            GREEN = ColorUtil.toRGBA(0, 255, 0, 255);
            BLUE = ColorUtil.toRGBA(0, 0, 255, 255);
            ORANGE = ColorUtil.toRGBA(255, 128, 0, 255);
            PURPLE = ColorUtil.toRGBA(163, 73, 163, 255);
            GRAY = ColorUtil.toRGBA(127, 127, 127, 255);
            DARK_RED = ColorUtil.toRGBA(64, 0, 0, 255);
            YELLOW = ColorUtil.toRGBA(255, 255, 0, 255);
        }
    }
}
