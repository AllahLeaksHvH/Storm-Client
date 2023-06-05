//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.render;

import me.dev.storm.features.modules.*;
import me.dev.storm.event.events.*;
import java.awt.*;
import me.dev.storm.util.*;
import net.minecraft.client.network.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.gui.*;

public class TargetHud extends Module
{
    LBFontRenderer renderer;
    LBFontRenderer renderer2;
    
    public TargetHud() {
        super("TargetHud", "target hud", Module.Category.RENDER, true, false, false);
    }
    
    public void onEnable() {
        this.renderer = new LBFontRenderer(new Font("Comfortaa", 0, 32));
        this.renderer2 = new LBFontRenderer(new Font("Comfortaa", 0, 48));
    }
    
    @SubscribeEvent
    public void onRender2D(final Render2DEvent event) {
        if (fullNullCheck()) {
            return;
        }
        final EntityPlayer player = EntityUtil.getClosestEnemy(12.0);
        if (player != null && this.renderer != null && this.renderer2 != null) {
            try {
                RenderUtil.drawRectangleCorrectly(770, 350, 150, 50, new Color(30, 30, 30, 150).getRGB());
                this.drawHead(Objects.requireNonNull(TargetHud.mc.getConnection()).getPlayerInfo(player.getUniqueID()).getLocationSkin(), 775, 355);
                RenderUtil.drawGradientSideways(820.0, 390.0, 820.0f + player.getHealth() * 3.9f, 395.0, new Color(218, 186, 255).getRGB(), new Color(255, 208, 143).getRGB());
                this.renderer.drawString(String.format("%.1f", player.getHealth() + player.getAbsorptionAmount()), (float)(820 + (int)(player.getHealth() * 3.9f) + 5), 390.0f, new Color(255, 255, 255).getRGB(), false);
                this.renderer2.drawString(player.getName(), 820.0f, 355.0f, new Color(255, 255, 255).getRGB(), false);
                int iteration = 0;
                for (final ItemStack is : player.inventory.armorInventory) {
                    ++iteration;
                    if (is.isEmpty()) {
                        continue;
                    }
                    final int x = 715 + (9 - iteration) * 20 + 2;
                    GlStateManager.enableDepth();
                    RenderUtil.itemRender.zLevel = 200.0f;
                    RenderUtil.itemRender.renderItemAndEffectIntoGUI(is, x, 367);
                    RenderUtil.itemRender.renderItemOverlayIntoGUI(TargetHud.mc.fontRenderer, is, x, 367, "");
                    RenderUtil.itemRender.zLevel = 0.0f;
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableLighting();
                    GlStateManager.disableDepth();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void drawHead(final ResourceLocation skin, final int width, final int height) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        TargetHud.mc.getTextureManager().bindTexture(skin);
        Gui.drawScaledCustomSizeModalRect(width, height, 8.0f, 8.0f, 8, 8, 40, 40, 64.0f, 64.0f);
    }
}
