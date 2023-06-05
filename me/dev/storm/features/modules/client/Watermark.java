//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.client;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import java.awt.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.network.*;
import java.util.*;
import me.dev.storm.util.*;

public class Watermark extends Module
{
    public Setting<Integer> posY;
    public Setting<Double> barPosY;
    public Setting<Double> textPosY;
    public Setting<Color> bgC;
    public Setting<Color> bC;
    public Setting<Color> fC;
    public Setting<Color> sC;
    public Setting<Color> tC;
    public Setting<Color> foC;
    
    public Watermark() {
        super("Watermark [CT]", "Hot csgo watermark", Category.CLIENT, true, false, false);
        this.posY = (Setting<Integer>)this.register(new Setting("PosY", (T)2, (T)0, (T)512));
        this.barPosY = (Setting<Double>)this.register(new Setting("BarPosY", (T)0.0, (T)(-2.0), (T)15.0));
        this.textPosY = (Setting<Double>)this.register(new Setting("TextPosY", (T)0.0, (T)(-6.0), (T)3.0));
        this.bgC = (Setting<Color>)this.register(new Setting("BackgroundColor", (T)new Color(20, 20, 20, 255)));
        this.bC = (Setting<Color>)this.register(new Setting("BorderColor", (T)new Color(0, 0, 20, 255)));
        this.fC = (Setting<Color>)this.register(new Setting("FirstLineColor", (T)new Color(135, 135, 255, 255)));
        this.sC = (Setting<Color>)this.register(new Setting("SecondLineColor", (T)new Color(0, 255, 255, 255)));
        this.tC = (Setting<Color>)this.register(new Setting("ThirdLineColor", (T)new Color(135, 135, 255, 255)));
        this.foC = (Setting<Color>)this.register(new Setting("FourthLineColor", (T)new Color(0, 255, 255, 255)));
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post event) {
        if (nullCheck()) {
            return;
        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            final String ping = this.getPing((EntityPlayer)Watermark.mc.player) + "ms";
            final String fpsText = Minecraft.debugFPS + "fps ";
            final String name = Watermark.mc.player.getDisplayNameString();
            final String server = Minecraft.getMinecraft().isSingleplayer() ? "singleplayer".toLowerCase() : Watermark.mc.getCurrentServerData().serverIP.toLowerCase();
            final String Storm = ChatFormatting.DARK_BLUE + "storm" + ChatFormatting.WHITE + " v0.3";
            final String text = Storm + " | " + server + " | " + ping + " | " + fpsText;
            final float width = (float)(Minecraft.getMinecraft().fontRenderer.getStringWidth(text) + 6);
            final int height = 20;
            final int posX = 2;
            final int posY = this.posY.getValue();
            final double barPosY = this.barPosY.getValue();
            final double textPosY = this.textPosY.getValue();
            RenderUtil.drawRectangleCorrectly(posX - 4, posY - 4, (int)(width + 10.0f), height + 5, ColorUtil.toRGBA(this.bC.getValue().getRed(), this.bC.getValue().getGreen(), this.bC.getValue().getBlue(), this.bC.getValue().getAlpha()));
            RenderUtil.drawRectangleCorrectly(posX - 4, posY - 4, (int)(width + 11.0f), height + 5, ColorUtil.toRGBA(this.bC.getValue().getRed(), this.bC.getValue().getGreen(), this.bC.getValue().getBlue(), this.bC.getValue().getAlpha()));
            drawRect(posX, posY, posX + width + 1.0f, posY + height, new Color(this.bgC.getValue().getRed(), this.bgC.getValue().getGreen(), this.bgC.getValue().getBlue(), this.bgC.getValue().getAlpha()).getRGB());
            drawRect(posX + 2.5, posY + 2.5, posX + width - 0.5, posY + 4.5, new Color(this.bgC.getValue().getRed(), this.bgC.getValue().getGreen(), this.bgC.getValue().getBlue(), this.bgC.getValue().getAlpha()).getRGB());
            drawGradientSideways(3.0, posY + barPosY + 3.0, 4.0f + width / 3.0f, posY + barPosY + 4.0, new Color(this.fC.getValue().getRed(), this.fC.getValue().getGreen(), this.fC.getValue().getBlue(), this.fC.getValue().getAlpha()).getRGB(), new Color(this.sC.getValue().getRed(), this.sC.getValue().getGreen(), this.sC.getValue().getBlue(), this.sC.getValue().getAlpha()).getRGB());
            drawGradientSideways(3.0f + width / 3.0f, posY + barPosY + 3.0, 4.0f + width / 3.0f * 2.0f, posY + barPosY + 4.0, new Color(this.sC.getValue().getRed(), this.sC.getValue().getGreen(), this.sC.getValue().getBlue(), this.sC.getValue().getAlpha()).getRGB(), new Color(this.tC.getValue().getRed(), this.tC.getValue().getGreen(), this.tC.getValue().getBlue(), this.tC.getValue().getAlpha()).getRGB());
            drawGradientSideways(3.0f + width / 3.0f * 2.0f, posY + barPosY + 3.0, width / 3.0f * 3.0f + 1.0f, posY + barPosY + 4.0, new Color(this.tC.getValue().getRed(), this.tC.getValue().getGreen(), this.tC.getValue().getBlue(), this.tC.getValue().getAlpha()).getRGB(), new Color(this.foC.getValue().getRed(), this.foC.getValue().getGreen(), this.foC.getValue().getBlue(), this.foC.getValue().getAlpha()).getRGB());
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(text, (float)(4 + posX), (float)(8.0 + (posY + textPosY)), -1);
        }
    }
    
    public static void drawRect(double left, double top, double right, double bottom, final int color) {
        if (left < right) {
            final double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            final double j = top;
            top = bottom;
            bottom = j;
        }
        final float f3 = (color >> 24 & 0xFF) / 255.0f;
        final float f4 = (color >> 16 & 0xFF) / 255.0f;
        final float f5 = (color >> 8 & 0xFF) / 255.0f;
        final float f6 = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f4, f5, f6, f3);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(left, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, top, 0.0).endVertex();
        bufferBuilder.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawGradientSideways(final double left, final double top, final double right, final double bottom, final int col1, final int col2) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        final float f5 = (col2 >> 24 & 0xFF) / 255.0f;
        final float f6 = (col2 >> 16 & 0xFF) / 255.0f;
        final float f7 = (col2 >> 8 & 0xFF) / 255.0f;
        final float f8 = (col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    private int getPing(final EntityPlayer player) {
        int ping = 0;
        try {
            ping = (int)MathUtil.clamp((float)Objects.requireNonNull(Watermark.mc.getConnection()).getPlayerInfo(player.getUniqueID()).getResponseTime(), 1.0f, 300.0f);
        }
        catch (NullPointerException ex) {}
        return ping;
    }
}
