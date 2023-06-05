//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.gui.components.items.buttons;

import me.dev.storm.features.setting.*;
import me.dev.storm.features.modules.client.*;
import me.dev.storm.*;
import me.dev.storm.util.*;
import me.dev.storm.features.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.*;
import me.dev.storm.features.gui.components.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;

public class BooleanButton extends Button
{
    private final Setting setting;
    private int progress;
    
    public BooleanButton(final Setting setting) {
        super(setting.getName());
        this.progress = 0;
        this.setting = setting;
        this.width = 15;
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final boolean newStyle = ClickGui.getInstance().style.getValue() == ClickGui.Style.NEW;
        final boolean future = ClickGui.getInstance().style.getValue() == ClickGui.Style.FUTURE;
        final boolean dotgod = ClickGui.getInstance().style.getValue() == ClickGui.Style.DOTGOD;
        if (future) {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(mouseX, mouseY) ? Storm.colorManager.getCurrentWithAlpha(120) : Storm.colorManager.getCurrentWithAlpha(99)) : (this.isHovering(mouseX, mouseY) ? Storm.colorManager.getCurrentWithAlpha(55) : Storm.colorManager.getCurrentWithAlpha(26)));
            Storm.textManager.drawStringWithShadow(newStyle ? this.getName().toLowerCase() : this.getName(), this.x + 2.3f, this.y - 1.7f - StormGui.getInstance().getTextOffset(), this.getState() ? -1 : -5592406);
        }
        else if (dotgod) {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(mouseX, mouseY) ? Storm.colorManager.getCurrentWithAlpha(90) : Storm.colorManager.getCurrentWithAlpha(65)) : (this.isHovering(mouseX, mouseY) ? Storm.colorManager.getCurrentWithAlpha(55) : Storm.colorManager.getCurrentWithAlpha(26)));
            Storm.textManager.drawStringWithShadow(this.getName().toLowerCase(), this.x + 2.3f, this.y - 1.7f - StormGui.getInstance().getTextOffset(), this.getState() ? Storm.colorManager.getCurrentGui(240) : 11579568);
        }
        else {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(mouseX, mouseY) ? Storm.colorManager.getColorWithAlpha(Storm.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : Storm.colorManager.getColorWithAlpha(Storm.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue())) : (this.isHovering(mouseX, mouseY) ? -2007673515 : 290805077));
            Storm.textManager.drawStringWithShadow(newStyle ? this.getName().toLowerCase() : this.getName(), this.x + 2.3f, this.y - 1.7f - StormGui.getInstance().getTextOffset(), this.getState() ? -1 : -5592406);
        }
        if (this.setting.parent) {
            if (this.setting.open) {
                ++this.progress;
            }
            if (future) {
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                BooleanButton.mc.getTextureManager().bindTexture(new ResourceLocation("textures/mio/gear.png"));
                GlStateManager.translate(this.getX() + this.getWidth() - 6.7f + 8.0f, this.getY() + 7.7f - 0.3f, 0.0f);
                GlStateManager.rotate(Component.calculateRotation((float)this.progress), 0.0f, 0.0f, 1.0f);
                RenderUtil.drawModalRect(-5, -5, 0.0f, 0.0f, 10, 10, 10, 10, 10.0f, 10.0f);
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
            else {
                final String color = (this.getState() || newStyle) ? "" : ("" + ChatFormatting.GRAY);
                final String gear = this.setting.open ? "-" : "+";
                Storm.textManager.drawStringWithShadow(color + gear, this.x - 1.5f + this.width - 7.4f + 8.0f, this.y - 2.2f - StormGui.getInstance().getTextOffset(), -1);
            }
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
            BooleanButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    @Override
    public void toggle() {
        this.setting.setValue(!this.setting.getValue());
    }
    
    @Override
    public boolean getState() {
        return this.setting.getValue();
    }
}
