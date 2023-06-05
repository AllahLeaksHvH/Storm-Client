//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.gui.components.items.buttons;

import me.dev.storm.features.modules.client.*;
import me.dev.storm.*;
import me.dev.storm.util.*;
import me.dev.storm.features.gui.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import me.dev.storm.features.setting.*;

public class BindButton extends Button
{
    private final Setting setting;
    public boolean isListening;
    
    public BindButton(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final boolean newStyle = ClickGui.getInstance().style.getValue() == ClickGui.Style.NEW || ClickGui.getInstance().style.getValue() == ClickGui.Style.DOTGOD;
        final int color = ColorUtil.toARGB(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue(), 255);
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(mouseX, mouseY) ? -2007673515 : 290805077) : (this.isHovering(mouseX, mouseY) ? Storm.colorManager.getColorWithAlpha(Storm.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : Storm.colorManager.getColorWithAlpha(Storm.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue())));
        if (this.isListening) {
            Storm.textManager.drawStringWithShadow("Press a Key...", this.x + 2.3f, this.y - 1.7f - StormGui.getClickGui().getTextOffset(), -1);
        }
        else {
            Storm.textManager.drawStringWithShadow((newStyle ? this.setting.getName().toLowerCase() : this.setting.getName()) + " " + ChatFormatting.GRAY + this.setting.getValue().toString().toUpperCase(), this.x + 2.3f, this.y - 1.7f - StormGui.getInstance().getTextOffset(), this.getState() ? -1 : -5592406);
        }
    }
    
    @Override
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
            BindButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    @Override
    public void onKeyTyped(final char typedChar, final int keyCode) {
        if (this.isListening) {
            Bind bind = new Bind(keyCode);
            if (bind.toString().equalsIgnoreCase("Escape")) {
                return;
            }
            if (bind.toString().equalsIgnoreCase("Delete")) {
                bind = new Bind(-1);
            }
            this.setting.setValue(bind);
            this.onMouseClick();
        }
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    @Override
    public void toggle() {
        this.isListening = !this.isListening;
    }
    
    @Override
    public boolean getState() {
        return !this.isListening;
    }
}
