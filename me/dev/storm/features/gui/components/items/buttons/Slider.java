//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.gui.components.items.buttons;

import me.dev.storm.features.setting.*;
import me.dev.storm.features.modules.client.*;
import me.dev.storm.*;
import org.lwjgl.input.*;
import java.awt.*;
import me.dev.storm.util.*;
import com.mojang.realmsclient.gui.*;
import me.dev.storm.features.gui.*;
import me.dev.storm.manager.*;
import me.dev.storm.features.gui.components.*;
import java.util.*;

public class Slider extends Button
{
    private final Number min;
    private final Number max;
    private final int difference;
    public Setting setting;
    private float renderWidth;
    private float prevRenderWidth;
    
    public Slider(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.min = setting.getMin();
        this.max = setting.getMax();
        this.difference = this.max.intValue() - this.min.intValue();
        this.width = 15;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final boolean newStyle = ClickGui.getInstance().style.getValue() == ClickGui.Style.NEW;
        final boolean future = ClickGui.getInstance().style.getValue() == ClickGui.Style.FUTURE;
        final boolean dotgod = ClickGui.getInstance().style.getValue() == ClickGui.Style.DOTGOD;
        this.dragSetting(mouseX, mouseY);
        this.setRenderWidth(this.x + (this.width + 7.4f) * this.partialMultiplier());
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.isHovering(mouseX, mouseY) ? -2007673515 : 290805077);
        if (future) {
            RenderUtil.drawRect(this.x, this.y, (this.setting.getValue().floatValue() <= this.min.floatValue()) ? this.x : this.getRenderWidth(), this.y + this.height - 0.5f, this.isHovering(mouseX, mouseY) ? Storm.colorManager.getCurrentWithAlpha(120) : Storm.colorManager.getCurrentWithAlpha(99));
        }
        else if (dotgod) {
            RenderUtil.drawRect(this.x, this.y, (this.setting.getValue().floatValue() <= this.min.floatValue()) ? this.x : this.getRenderWidth(), this.y + this.height - 0.5f, this.isHovering(mouseX, mouseY) ? Storm.colorManager.getCurrentWithAlpha(90) : Storm.colorManager.getCurrentWithAlpha(65));
        }
        else {
            if (this.isHovering(mouseX, mouseY) && Mouse.isButtonDown(0)) {
                RenderUtil.drawHGradientRect(this.x, this.y, (this.setting.getValue().floatValue() <= this.min.floatValue()) ? this.x : this.getRenderWidth(), this.y + this.height - 0.5f, ColorUtil.pulseColor(new Color(ClickGui.getInstance().color.getValue().getRed(), ClickGui.getInstance().color.getValue().getGreen(), ClickGui.getInstance().color.getValue().getBlue(), 200), 50, 1).getRGB(), ColorUtil.pulseColor(new Color(ClickGui.getInstance().color.getValue().getRed(), ClickGui.getInstance().color.getValue().getGreen(), ClickGui.getInstance().color.getValue().getBlue(), 200), 50, 1000).getRGB());
            }
            else {
                RenderUtil.drawRect(this.x, this.y, (this.setting.getValue().floatValue() <= this.min.floatValue()) ? this.x : this.getRenderWidth(), this.y + this.height - 0.5f, this.isHovering(mouseX, mouseY) ? Storm.colorManager.getCurrentWithAlpha(200) : Storm.colorManager.getCurrentWithAlpha(120));
            }
            RenderUtil.drawLine(this.x + 1.0f, this.y, this.x + 1.0f, this.y + this.height - 0.5f, 0.9f, Storm.colorManager.getCurrentWithAlpha(255));
        }
        if (dotgod) {
            Storm.textManager.drawStringWithShadow(this.getName().toLowerCase() + ":" + " " + ChatFormatting.GRAY + ((this.setting.getValue() instanceof Float) ? this.setting.getValue() : this.setting.getValue().doubleValue()), this.x + 2.3f, this.y - 1.7f - StormGui.getInstance().getTextOffset(), Storm.colorManager.getCurrentGui(240));
        }
        else {
            RenderUtil.drawRect(this.x, this.y, (this.setting.getValue().floatValue() <= this.min.floatValue()) ? this.x : (this.x + (this.width + 7.4f) * this.partialMultiplier()), this.y + this.height - 0.5f, this.isHovering(mouseX, mouseY) ? Storm.colorManager.getColorWithAlpha(Storm.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : Storm.colorManager.getColorWithAlpha(Storm.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue()));
            Storm.textManager.drawStringWithShadow((newStyle ? (this.getName().toLowerCase() + ":") : this.getName()) + " " + ChatFormatting.GRAY + ((this.setting.getValue() instanceof Float) ? this.setting.getValue() : this.setting.getValue().doubleValue()), this.x + 2.3f, this.y - 1.7f - StormGui.getInstance().getTextOffset(), -1);
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
            this.setSettingFromX(mouseX);
        }
    }
    
    public void setRenderWidth(final float renderWidth) {
        if (this.renderWidth == renderWidth) {
            return;
        }
        this.prevRenderWidth = this.renderWidth;
        this.renderWidth = renderWidth;
    }
    
    public float getRenderWidth() {
        if (FpsManager.getFPS() < 20) {
            return this.renderWidth;
        }
        return this.renderWidth = this.prevRenderWidth + (this.renderWidth - this.prevRenderWidth) * Slider.mc.getRenderPartialTicks() / (8.0f * (Math.min(240, FpsManager.getFPS()) / 240.0f));
    }
    
    public boolean isHovering(final int mouseX, final int mouseY) {
        for (final Component component : StormGui.getClickGui().getComponents()) {
            if (!component.drag) {
                continue;
            }
            return false;
        }
        return mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() + 8.0f && mouseY >= this.getY() && mouseY <= this.getY() + this.height;
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    private void dragSetting(final int mouseX, final int mouseY) {
        if (this.isHovering(mouseX, mouseY) && Mouse.isButtonDown(0)) {
            this.setSettingFromX(mouseX);
        }
    }
    
    public int getHeight() {
        return 14;
    }
    
    private void setSettingFromX(final int mouseX) {
        final float percent = (mouseX - this.x) / (this.width + 7.4f);
        if (this.setting.getValue() instanceof Double) {
            final double result = this.setting.getMin() + this.difference * percent;
            this.setting.setValue(Math.round(10.0 * result) / 10.0);
        }
        else if (this.setting.getValue() instanceof Float) {
            final float result2 = this.setting.getMin() + this.difference * percent;
            this.setting.setValue(Math.round(10.0f * result2) / 10.0f);
        }
        else if (this.setting.getValue() instanceof Integer) {
            this.setting.setValue(this.setting.getMin() + (int)(this.difference * percent));
        }
    }
    
    private float middle() {
        return this.max.floatValue() - this.min.floatValue();
    }
    
    private float part() {
        return this.setting.getValue().floatValue() - this.min.floatValue();
    }
    
    private float partialMultiplier() {
        return this.part() / this.middle();
    }
}
