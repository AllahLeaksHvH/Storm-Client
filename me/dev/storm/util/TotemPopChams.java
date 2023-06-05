//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.util;

import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import me.dev.storm.features.modules.render.*;
import net.minecraft.util.math.*;
import net.minecraft.client.model.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;

public class TotemPopChams
{
    private static final Minecraft mc;
    EntityOtherPlayerMP player;
    ModelPlayer playerModel;
    Long startTime;
    double alphaFill;
    double alphaLine;
    
    public TotemPopChams(final EntityOtherPlayerMP player, final ModelPlayer playerModel, final Long startTime, final double alphaFill, final double alphaLine) {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.player = player;
        this.playerModel = playerModel;
        this.startTime = startTime;
        this.alphaFill = alphaFill;
        this.alphaLine = alphaFill;
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        if (this.player == null || TotemPopChams.mc.world == null || TotemPopChams.mc.player == null) {
            return;
        }
        GL11.glLineWidth(1.0f);
        final Color lineColorS = new Color(((Color)PopChams.lineC.getValue()).getRed(), ((Color)PopChams.lineC.getValue()).getGreen(), ((Color)PopChams.lineC.getValue()).getBlue(), ((Color)PopChams.lineC.getValue()).getAlpha());
        final Color fillColorS = new Color(((Color)PopChams.fillC.getValue()).getRed(), ((Color)PopChams.fillC.getValue()).getGreen(), ((Color)PopChams.fillC.getValue()).getBlue(), ((Color)PopChams.fillC.getValue()).getAlpha());
        int lineA = lineColorS.getAlpha();
        int fillA = fillColorS.getAlpha();
        final long time = System.currentTimeMillis() - this.startTime - ((Number)PopChams.fadestart.getValue()).longValue();
        if (System.currentTimeMillis() - this.startTime > ((Number)PopChams.fadestart.getValue()).longValue()) {
            double normal = this.normalize((double)time, 0.0, ((Number)PopChams.fadetime.getValue()).doubleValue());
            normal = MathHelper.clamp(normal, 0.0, 1.0);
            normal = -normal + 1.0;
            lineA *= (int)normal;
            fillA *= (int)normal;
        }
        final Color lineColor = newAlpha(lineColorS, lineA);
        final Color fillColor = newAlpha(fillColorS, fillA);
        if (this.player != null && this.playerModel != null) {
            NordTessellator.prepareGL();
            GL11.glPushAttrib(1048575);
            GL11.glEnable(2881);
            GL11.glEnable(2848);
            if (this.alphaFill > 1.0) {
                this.alphaFill -= (float)PopChams.fadetime.getValue();
            }
            final Color fillFinal = new Color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), (int)this.alphaFill);
            if (this.alphaLine > 1.0) {
                this.alphaLine -= (float)PopChams.fadetime.getValue();
            }
            final Color outlineFinal = new Color(lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue(), (int)this.alphaLine);
            glColor(fillFinal);
            GL11.glPolygonMode(1032, 6914);
            renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, (float)this.player.ticksExisted, this.player.rotationYawHead, this.player.rotationPitch, 1.0f);
            glColor(outlineFinal);
            GL11.glPolygonMode(1032, 6913);
            renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, (float)this.player.ticksExisted, this.player.rotationYawHead, this.player.rotationPitch, 1.0f);
            GL11.glPolygonMode(1032, 6914);
            GL11.glPopAttrib();
            NordTessellator.releaseGL();
        }
    }
    
    double normalize(final double value, final double min, final double max) {
        return (value - min) / (max - min);
    }
    
    public static void renderEntity(final EntityLivingBase entity, final ModelBase modelBase, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale) {
        if (TotemPopChams.mc.getRenderManager() == null) {
            return;
        }
        final float partialTicks = TotemPopChams.mc.getRenderPartialTicks();
        final double x = entity.posX - TotemPopChams.mc.getRenderManager().viewerPosX;
        double y = entity.posY - TotemPopChams.mc.getRenderManager().viewerPosY;
        final double z = entity.posZ - TotemPopChams.mc.getRenderManager().viewerPosZ;
        GlStateManager.pushMatrix();
        if (entity.isSneaking()) {
            y -= 0.125;
        }
        final float interpolateRotation = interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
        final float interpolateRotation2 = interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
        final float rotationInterp = interpolateRotation2 - interpolateRotation;
        final float renderPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
        renderLivingAt(x, y, z);
        final float f8 = handleRotationFloat(entity, partialTicks);
        prepareRotations(entity);
        final float f9 = prepareScale(entity, scale);
        GlStateManager.enableAlpha();
        modelBase.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
        modelBase.setRotationAngles(limbSwing, limbSwingAmount, f8, entity.rotationYawHead, entity.rotationPitch, f9, (Entity)entity);
        modelBase.render((Entity)entity, limbSwing, limbSwingAmount, f8, entity.rotationYawHead, entity.rotationPitch, f9);
        GlStateManager.popMatrix();
    }
    
    public static void prepareTranslate(final EntityLivingBase entityIn, final double x, final double y, final double z) {
        renderLivingAt(x - TotemPopChams.mc.getRenderManager().viewerPosX, y - TotemPopChams.mc.getRenderManager().viewerPosY, z - TotemPopChams.mc.getRenderManager().viewerPosZ);
    }
    
    public static void renderLivingAt(final double x, final double y, final double z) {
        GlStateManager.translate((float)x, (float)y, (float)z);
    }
    
    public static float prepareScale(final EntityLivingBase entity, final float scale) {
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0f, -1.0f, 1.0f);
        final double widthX = entity.getRenderBoundingBox().maxX - entity.getRenderBoundingBox().minX;
        final double widthZ = entity.getRenderBoundingBox().maxZ - entity.getRenderBoundingBox().minZ;
        GlStateManager.scale(scale + widthX, (double)(scale * entity.height), scale + widthZ);
        final float f = 0.0625f;
        GlStateManager.translate(0.0f, -1.501f, 0.0f);
        return 0.0625f;
    }
    
    public static void prepareRotations(final EntityLivingBase entityLivingBase) {
        GlStateManager.rotate(180.0f - entityLivingBase.rotationYaw, 0.0f, 1.0f, 0.0f);
    }
    
    public static float interpolateRotation(final float prevYawOffset, final float yawOffset, final float partialTicks) {
        float f;
        for (f = yawOffset - prevYawOffset; f < -180.0f; f += 360.0f) {}
        while (f >= 180.0f) {
            f -= 360.0f;
        }
        return prevYawOffset + partialTicks * f;
    }
    
    public static Color newAlpha(final Color color, final int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
    
    public static void glColor(final Color color) {
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    public static float handleRotationFloat(final EntityLivingBase livingBase, final float partialTicks) {
        return 0.0f;
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
