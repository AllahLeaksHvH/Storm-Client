//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.gui.components.items.buttons;

import me.dev.storm.features.setting.*;
import me.dev.storm.features.modules.client.*;
import me.dev.storm.*;
import me.dev.storm.util.*;
import com.mojang.realmsclient.gui.*;
import me.dev.storm.features.gui.*;
import java.util.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;

public class EnumButton extends Button
{
    public Setting setting;
    
    public EnumButton(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final boolean newStyle = ClickGui.getInstance().style.getValue() == ClickGui.Style.NEW || ClickGui.getInstance().style.getValue() == ClickGui.Style.DOTGOD;
        final boolean future = ClickGui.getInstance().style.getValue() == ClickGui.Style.FUTURE;
        final boolean dotgod = ClickGui.getInstance().style.getValue() == ClickGui.Style.DOTGOD;
        if (future) {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(mouseX, mouseY) ? Storm.colorManager.getCurrentWithAlpha(120) : Storm.colorManager.getCurrentWithAlpha(99)) : (this.isHovering(mouseX, mouseY) ? Storm.colorManager.getCurrentWithAlpha(55) : Storm.colorManager.getCurrentWithAlpha(26)));
        }
        else if (dotgod) {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(mouseX, mouseY) ? Storm.colorManager.getCurrentWithAlpha(90) : Storm.colorManager.getCurrentWithAlpha(65)) : (this.isHovering(mouseX, mouseY) ? Storm.colorManager.getCurrentWithAlpha(35) : Storm.colorManager.getCurrentWithAlpha(26)));
        }
        else {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(mouseX, mouseY) ? Storm.colorManager.getColorWithAlpha(Storm.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : Storm.colorManager.getColorWithAlpha(Storm.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue())) : (this.isHovering(mouseX, mouseY) ? -2007673515 : 290805077));
            Storm.textManager.drawStringWithShadow((newStyle ? (this.setting.getName().toLowerCase() + ":") : this.setting.getName()) + " " + ChatFormatting.GRAY + (this.setting.getCurrentEnumName().equalsIgnoreCase("ABC") ? "ABC" : this.setting.getCurrentEnumName()), this.x + 2.3f, this.y - 1.7f - StormGui.getInstance().getTextOffset(), this.getState() ? -1 : -5592406);
            int y = (int)this.y;
            if (this.setting.open) {
                for (final Object o : this.setting.getValue().getClass().getEnumConstants()) {
                    y += 12;
                    final String s = Objects.equals(o.toString(), "ABC") ? o.toString() : (Character.toUpperCase(o.toString().charAt(0)) + o.toString().toLowerCase().substring(1));
                    Storm.textManager.drawStringWithShadow((this.setting.getCurrentEnumName().equals(s) ? ChatFormatting.WHITE : ChatFormatting.GRAY) + s, this.width / 2.0f - Storm.textManager.getStringWidth(s) / 2.0f + 2.0f + this.x, y + 6.0f - EnumButton.mc.fontRenderer.FONT_HEIGHT / 2.0f + 3.5f, -1);
                }
            }
        }
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
            EnumButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    public int getHeight() {
        return 14;
    }
    
    public void toggle() {
        this.setting.increaseEnum();
    }
    
    public boolean getState() {
        return true;
    }
}
