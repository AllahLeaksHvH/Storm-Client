//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.render;

import me.dev.storm.features.modules.*;
import me.dev.storm.util.*;
import me.dev.storm.features.setting.*;
import com.google.common.collect.*;
import me.dev.storm.event.events.*;
import net.minecraft.client.renderer.vertex.*;
import org.lwjgl.opengl.*;
import java.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import java.awt.*;
import net.minecraft.util.math.*;
import com.ibm.icu.math.*;

public class DamageNumbers extends Module
{
    private final Map<Integer, Float> hpData;
    private final List<Particle> particles;
    private final Timer timer;
    public Setting<Integer> deleteAfter;
    
    public DamageNumbers() {
        super("DmgNumbers", "show damage", Module.Category.RENDER, true, false, false);
        this.hpData = (Map<Integer, Float>)Maps.newHashMap();
        this.particles = (List<Particle>)Lists.newArrayList();
        this.timer = new Timer();
        this.deleteAfter = (Setting<Integer>)this.register(new Setting("Remove Ticks", (T)7, (T)1, (T)60));
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (!this.particles.isEmpty()) {
            for (final Particle particle : this.particles) {
                if (particle != null) {
                    if (particle.ticks > this.deleteAfter.getValue()) {
                        continue;
                    }
                    GlStateManager.pushMatrix();
                    GlStateManager.disableDepth();
                    final Tessellator tessellator = Tessellator.getInstance();
                    final BufferBuilder bufferBuilder = tessellator.getBuffer();
                    bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
                    tessellator.draw();
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    GlStateManager.pushMatrix();
                    GlStateManager.enablePolygonOffset();
                    GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
                    GlStateManager.translate(particle.posX - DamageNumbers.mc.getRenderManager().renderPosX, particle.posY - DamageNumbers.mc.getRenderManager().renderPosY, particle.posZ - DamageNumbers.mc.getRenderManager().renderPosZ);
                    GlStateManager.rotate(-DamageNumbers.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(DamageNumbers.mc.getRenderManager().playerViewX, (DamageNumbers.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
                    GlStateManager.scale(-0.03, -0.03, 0.03);
                    GL11.glDepthMask(false);
                    DamageNumbers.mc.fontRenderer.drawStringWithShadow(particle.str, (float)(-DamageNumbers.mc.fontRenderer.getStringWidth(particle.str) * 0.5), (float)(-DamageNumbers.mc.fontRenderer.FONT_HEIGHT + 1), particle.color.getRGB());
                    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                    GL11.glDepthMask(true);
                    GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
                    GlStateManager.disablePolygonOffset();
                    GlStateManager.resetColor();
                    GlStateManager.popMatrix();
                }
            }
        }
    }
    
    public void onDisable() {
        this.particles.clear();
    }
    
    public String onUpdate() {
        if (this.timer.passedMs(12000L)) {
            this.particles.clear();
            this.timer.reset();
        }
        if (!this.particles.isEmpty()) {
            for (final Particle particle : this.particles) {
                if (particle == null) {
                    continue;
                }
                final Particle particle3 = particle;
                ++particle3.ticks;
            }
        }
        for (final Entity particle2 : DamageNumbers.mc.world.loadedEntityList) {
            if (!(particle2 instanceof EntityLivingBase)) {
                continue;
            }
            final EntityLivingBase entityLivingBase = (EntityLivingBase)particle2;
            final double d = this.hpData.getOrDefault(entityLivingBase.getEntityId(), entityLivingBase.getMaxHealth());
            this.hpData.remove(particle2.getEntityId());
            this.hpData.put(particle2.getEntityId(), entityLivingBase.getHealth());
            if (d == entityLivingBase.getHealth()) {
                continue;
            }
            final Color color = Color.YELLOW;
            final Vec3d vec3d = new Vec3d(particle2.posX + Math.random() * 0.5 * ((Math.random() > 0.5) ? -1 : 1), particle2.getEntityBoundingBox().minY + (particle2.getEntityBoundingBox().maxY - particle2.getEntityBoundingBox().minY) * 0.5, particle2.posZ + Math.random() * 0.5 * ((Math.random() > 0.5) ? -1 : 1));
            final double d2 = new BigDecimal(Math.abs(d - entityLivingBase.getHealth())).setScale(1, 4).doubleValue();
            this.particles.add(new Particle("" + d2, vec3d.x, vec3d.y, vec3d.z, color));
        }
        return null;
    }
    
    static class Particle
    {
        public double posY;
        public Color color;
        public String str;
        public int ticks;
        public double posZ;
        public double posX;
        
        public Particle(final String string, final double d, final double d2, final double d3, final Color color2) {
            this.str = string;
            this.posX = d;
            this.posY = d2;
            this.posZ = d3;
            this.color = color2;
            this.ticks = 0;
        }
    }
}
