//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.client;

import me.dev.storm.features.modules.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.client.*;
import me.dev.storm.features.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;

public class GuiBlur extends Module
{
    public GuiBlur() {
        super("GuiBlur", "blur in gui", Category.CLIENT, true, false, false);
    }
    
    @Override
    public String onUpdate() {
        if (GuiBlur.mc.world != null) {
            if (!ClickGui.getInstance().isEnabled() && !(GuiBlur.mc.currentScreen instanceof GuiContainer) && !(GuiBlur.mc.currentScreen instanceof GuiChat) && !(GuiBlur.mc.currentScreen instanceof GuiConfirmOpenLink) && !(GuiBlur.mc.currentScreen instanceof GuiEditSign) && !(GuiBlur.mc.currentScreen instanceof GuiGameOver) && !(GuiBlur.mc.currentScreen instanceof GuiOptions) && !(GuiBlur.mc.currentScreen instanceof GuiIngameMenu) && !(GuiBlur.mc.currentScreen instanceof GuiVideoSettings) && !(GuiBlur.mc.currentScreen instanceof GuiScreenOptionsSounds) && !(GuiBlur.mc.currentScreen instanceof GuiControls) && !(GuiBlur.mc.currentScreen instanceof GuiCustomizeSkin) && !(GuiBlur.mc.currentScreen instanceof GuiModList) && !(GuiBlur.mc.currentScreen instanceof StormGui)) {
                if (GuiBlur.mc.entityRenderer.getShaderGroup() != null) {
                    GuiBlur.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
                }
            }
            else if (OpenGlHelper.shadersSupported && GuiBlur.mc.getRenderViewEntity() instanceof EntityPlayer) {
                if (GuiBlur.mc.entityRenderer.getShaderGroup() != null) {
                    GuiBlur.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
                }
                try {
                    GuiBlur.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else if (GuiBlur.mc.entityRenderer.getShaderGroup() != null && GuiBlur.mc.currentScreen == null) {
                GuiBlur.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
        }
        return null;
    }
}
