//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.gui.components.items.buttons;

import me.dev.storm.features.gui.components.items.*;
import me.dev.storm.features.modules.client.*;
import me.dev.storm.util.*;
import me.dev.storm.*;
import me.dev.storm.features.gui.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import me.dev.storm.features.gui.components.*;
import java.util.*;

public class Button extends Item
{
    private boolean state;
    
    public Button(final String name) {
        super(name);
        this.height = 15;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float f) {
        final boolean newStyle = ClickGui.getInstance().style.getValue() == ClickGui.Style.NEW;
        final boolean future = ClickGui.getInstance().style.getValue() == ClickGui.Style.FUTURE;
        final boolean dotgod = ClickGui.getInstance().style.getValue() == ClickGui.Style.DOTGOD;
        if (newStyle) {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height - 0.5f, this.isHovering(n, n2) ? -2007673515 : 290805077);
            Storm.textManager.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 2.0f - StormGui.getInstance().getTextOffset(), this.getState() ? Storm.colorManager.getCurrentGui(240) : -1);
            if (ClickGui.getInstance().box2.getValue()) {
                RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? Storm.colorManager.getColorWithAlpha(Storm.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : Storm.colorManager.getColorWithAlpha(Storm.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue())) : (this.isHovering(n, n2) ? -2007673515 : 894784853));
            }
            else if (dotgod) {
                RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? Storm.colorManager.getCurrentWithAlpha(90) : Storm.colorManager.getCurrentWithAlpha(65)) : (this.isHovering(n, n2) ? Storm.colorManager.getCurrentWithAlpha(35) : Storm.colorManager.getCurrentWithAlpha(26)));
                Storm.textManager.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 2.0f - StormGui.getInstance().getTextOffset(), this.getState() ? Storm.colorManager.getCurrentGui(240) : 11579568);
            }
            else if (future) {
                RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? Storm.colorManager.getCurrentWithAlpha(120) : Storm.colorManager.getCurrentWithAlpha(99)) : (this.isHovering(n, n2) ? Storm.colorManager.getCurrentWithAlpha(55) : Storm.colorManager.getCurrentWithAlpha(26)));
                Storm.textManager.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 2.0f - StormGui.getInstance().getTextOffset(), this.getState() ? -1 : -5592406);
                Storm.textManager.drawStringWithShadow(this.getName(), this.x + 1.3f, this.y - 1.0f - StormGui.getClickGui().getTextOffset(), this.getState() ? -1 : -159240);
            }
        }
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (mouseButton == 0 && this.isHovering(mouseX, mouseY)) {
            this.onMouseClick();
        }
    }
    
    public void onMouseClick() {
        this.state = !this.state;
        this.toggle();
        Button.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
    }
    
    public void toggle() {
    }
    
    public boolean getState() {
        return this.state;
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    public boolean isHovering(final int mouseX, final int mouseY) {
        for (final Component component : StormGui.getClickGui().getComponents()) {
            if (!component.drag) {
                continue;
            }
            return false;
        }
        return mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() && mouseY >= this.getY() && mouseY <= this.getY() + this.height;
    }
}
