//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.manager;

import me.dev.storm.features.*;
import me.dev.storm.util.*;
import me.dev.storm.features.gui.font.*;
import java.awt.*;
import me.dev.storm.features.modules.client.*;
import me.dev.storm.*;
import net.minecraft.util.math.*;

public class TextManager extends Feature
{
    private final Timer idleTimer;
    public int scaledWidth;
    public int scaledHeight;
    public int scaleFactor;
    private CustomFont customFont;
    private boolean idling;
    
    public TextManager() {
        this.idleTimer = new Timer();
        this.customFont = new CustomFont(new Font("Verdana", 0, 17), true, false);
        this.updateResolution();
    }
    
    public void init(final boolean startup) {
        final FontMod cFont = (FontMod)Storm.moduleManager.getModuleByClass((Class)FontMod.class);
        try {
            this.setFontRenderer(new Font((String)cFont.fontName.getValue(), (int)cFont.fontStyle.getValue(), (int)cFont.fontSize.getValue()), (boolean)cFont.antiAlias.getValue(), (boolean)cFont.fractionalMetrics.getValue());
        }
        catch (Exception ex) {}
    }
    
    public void drawStringWithShadow(final String text, final float x, final float y, final int color) {
        this.drawString(text, x, y, color, true);
    }
    
    public void drawString(final String text, final float x, final float y, final int color, final boolean shadow) {
        if (Storm.moduleManager.isModuleEnabled(FontMod.getInstance().getName())) {
            if (shadow) {
                this.customFont.drawStringWithShadow(text, (double)x, (double)y, color);
            }
            else {
                this.customFont.drawString(text, (double)x, (double)y, color, false);
            }
            return;
        }
        TextManager.mc.fontRenderer.drawString(text, x, y, color, shadow);
    }
    
    public int getStringWidth(final String text) {
        if (Storm.moduleManager.isModuleEnabled(FontMod.getInstance().getName())) {
            return this.customFont.getStringWidth(text);
        }
        return TextManager.mc.fontRenderer.getStringWidth(text);
    }
    
    public int getFontHeight() {
        if (Storm.moduleManager.isModuleEnabled(FontMod.getInstance().getName())) {
            final String text = "A";
            return this.customFont.getStringHeight(text);
        }
        return TextManager.mc.fontRenderer.FONT_HEIGHT;
    }
    
    public void setFontRenderer(final Font font, final boolean antiAlias, final boolean fractionalMetrics) {
        this.customFont = new CustomFont(font, antiAlias, fractionalMetrics);
    }
    
    public Font getCurrentFont() {
        return this.customFont.getFont();
    }
    
    public void updateResolution() {
        this.scaledWidth = TextManager.mc.displayWidth;
        this.scaledHeight = TextManager.mc.displayHeight;
        this.scaleFactor = 1;
        final boolean flag = TextManager.mc.isUnicode();
        int i = TextManager.mc.gameSettings.guiScale;
        if (i == 0) {
            i = 1000;
        }
        while (this.scaleFactor < i && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240) {
            ++this.scaleFactor;
        }
        if (flag && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
            --this.scaleFactor;
        }
        final double scaledWidthD = this.scaledWidth / this.scaleFactor;
        final double scaledHeightD = this.scaledHeight / this.scaleFactor;
        this.scaledWidth = MathHelper.ceil(scaledWidthD);
        this.scaledHeight = MathHelper.ceil(scaledHeightD);
    }
    
    public String getIdleSign() {
        if (this.idleTimer.passedMs(500L)) {
            this.idling = !this.idling;
            this.idleTimer.reset();
        }
        if (this.idling) {
            return "_";
        }
        return "";
    }
}
