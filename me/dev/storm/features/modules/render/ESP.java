//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.render;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import me.dev.storm.event.events.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import me.dev.storm.features.modules.client.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import me.dev.storm.util.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.util.math.*;

public class ESP extends Module
{
    private static ESP INSTANCE;
    public final Setting<Boolean> colorSync;
    public final Setting<Boolean> players;
    public final Setting<Boolean> colorFriends;
    public final Setting<Boolean> animals;
    public final Setting<Boolean> mobs;
    public final Setting<Boolean> others;
    public final Setting<Boolean> items;
    public final Setting<Boolean> xporbs;
    public final Setting<Boolean> xpbottles;
    public final Setting<Boolean> pearl;
    public final Setting<Integer> red;
    public final Setting<Integer> green;
    public final Setting<Integer> blue;
    public final Setting<Integer> boxAlpha;
    public final Setting<Integer> alpha;
    public final Setting<Float> lineWidth;
    
    public ESP() {
        super("ESP", "Renders a nice ESP.", Module.Category.RENDER, false, false, false);
        this.colorSync = (Setting<Boolean>)this.register(new Setting("Sync", (T)false));
        this.players = (Setting<Boolean>)this.register(new Setting("Players", (T)true));
        this.colorFriends = (Setting<Boolean>)this.register(new Setting("Friends", (T)true, v -> this.players.getValue()));
        this.animals = (Setting<Boolean>)this.register(new Setting("Animals", (T)false));
        this.mobs = (Setting<Boolean>)this.register(new Setting("Mobs", (T)false));
        this.others = (Setting<Boolean>)this.register(new Setting("Crystals", (T)false));
        this.items = (Setting<Boolean>)this.register(new Setting("Items", (T)false));
        this.xporbs = (Setting<Boolean>)this.register(new Setting("XpOrbs", (T)false));
        this.xpbottles = (Setting<Boolean>)this.register(new Setting("XpBottles", (T)false));
        this.pearl = (Setting<Boolean>)this.register(new Setting("Pearls", (T)false));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)255, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)255, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)255, (T)0, (T)255));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (T)120, (T)0, (T)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)255, (T)0, (T)255));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)2.0f, (T)0.1f, (T)5.0f));
        this.setInstance();
    }
    
    public static ESP getInstance() {
        if (ESP.INSTANCE == null) {
            ESP.INSTANCE = new ESP();
        }
        return ESP.INSTANCE;
    }
    
    private void setInstance() {
        ESP.INSTANCE = this;
    }
    
    public void onRender3D(final Render3DEvent event) {
        if (this.items.getValue()) {
            int i = 0;
            for (final Entity entity : ESP.mc.world.loadedEntityList) {
                if (entity instanceof EntityItem) {
                    if (ESP.mc.player.getDistanceSq(entity) >= 2500.0) {
                        continue;
                    }
                    final Vec3d interp = EntityUtil.getInterpolatedRenderPos(entity, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB bb = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + interp.y, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + interp.z, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + interp.y, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + interp.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(bb, ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getRed() / 255.0f) : (this.red.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getGreen() / 255.0f) : (this.green.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getBlue() / 255.0f) : (this.blue.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? ((float)Colors.INSTANCE.getCurrentColor().getAlpha()) : (this.boxAlpha.getValue() / 255.0f));
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(bb, ((boolean)this.colorSync.getValue()) ? Colors.INSTANCE.getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), 1.0f);
                    if (++i < 50) {
                        continue;
                    }
                    break;
                }
            }
        }
        if (this.xporbs.getValue()) {
            int i = 0;
            for (final Entity entity : ESP.mc.world.loadedEntityList) {
                if (entity instanceof EntityXPOrb) {
                    if (ESP.mc.player.getDistanceSq(entity) >= 2500.0) {
                        continue;
                    }
                    final Vec3d interp = EntityUtil.getInterpolatedRenderPos(entity, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB bb = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + interp.y, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + interp.z, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + interp.y, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + interp.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(bb, ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getRed() / 255.0f) : (this.red.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getGreen() / 255.0f) : (this.green.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getBlue() / 255.0f) : (this.blue.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getAlpha() / 255.0f) : (this.boxAlpha.getValue() / 255.0f));
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(bb, ((boolean)this.colorSync.getValue()) ? Colors.INSTANCE.getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), 1.0f);
                    if (++i < 50) {
                        continue;
                    }
                    break;
                }
            }
        }
        if (this.pearl.getValue()) {
            int i = 0;
            for (final Entity entity : ESP.mc.world.loadedEntityList) {
                if (entity instanceof EntityEnderPearl) {
                    if (ESP.mc.player.getDistanceSq(entity) >= 2500.0) {
                        continue;
                    }
                    final Vec3d interp = EntityUtil.getInterpolatedRenderPos(entity, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB bb = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + interp.y, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + interp.z, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + interp.y, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + interp.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(bb, ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getRed() / 255.0f) : (this.red.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getGreen() / 255.0f) : (this.green.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getBlue() / 255.0f) : (this.blue.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getAlpha() / 255.0f) : (this.boxAlpha.getValue() / 255.0f));
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(bb, ((boolean)this.colorSync.getValue()) ? Colors.INSTANCE.getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), 1.0f);
                    if (++i < 50) {
                        continue;
                    }
                    break;
                }
            }
        }
        if (this.xpbottles.getValue()) {
            int i = 0;
            for (final Entity entity : ESP.mc.world.loadedEntityList) {
                if (entity instanceof EntityExpBottle) {
                    if (ESP.mc.player.getDistanceSq(entity) >= 2500.0) {
                        continue;
                    }
                    final Vec3d interp = EntityUtil.getInterpolatedRenderPos(entity, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB bb = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + interp.y, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + interp.z, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + interp.y, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + interp.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(bb, ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getRed() / 255.0f) : (this.red.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getGreen() / 255.0f) : (this.green.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getBlue() / 255.0f) : (this.blue.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? (Colors.INSTANCE.getCurrentColor().getAlpha() / 255.0f) : (this.boxAlpha.getValue() / 255.0f));
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(bb, ((boolean)this.colorSync.getValue()) ? Colors.INSTANCE.getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), 1.0f);
                    if (++i < 50) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    static {
        ESP.INSTANCE = new ESP();
    }
}
