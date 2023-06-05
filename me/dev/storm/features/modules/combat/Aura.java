//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.combat;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import me.dev.storm.*;
import me.dev.storm.event.events.*;
import me.dev.storm.manager.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import me.dev.storm.util.*;
import java.util.*;

public class Aura extends Module
{
    public final Setting<Boolean> players;
    private final Setting<RenderMode> render;
    public final Setting<Boolean> packet;
    public final Setting<Boolean> tps;
    public final Setting<Float> raytrace;
    public final Setting<Boolean> vehicles;
    public final Setting<Boolean> animals;
    public final Setting<Boolean> delay;
    public static Entity target;
    public final Setting<Boolean> mobs;
    public final Setting<Boolean> onlySharp;
    public final Setting<Boolean> projectiles;
    public final Setting<Float> range;
    private final Timer timer;
    
    private void doAura() {
        int n = 0;
        if (this.onlySharp.getValue() && !EntityUtil.holdingWeapon((EntityPlayer)Aura.mc.player)) {
            Aura.target = null;
            return;
        }
        final int n2 = this.delay.getValue() ? (n = (int)(DamageUtil.getCooldownByWeapon((EntityPlayer)Aura.mc.player) * (this.tps.getValue() ? Storm.serverManager.getTpsFactor() : 1.0f))) : 0;
        if (!this.timer.passedMs(n)) {
            return;
        }
        Aura.target = this.getTarget();
        if (Aura.target == null) {
            return;
        }
        EntityUtil.attackEntity(Aura.target, this.packet.getValue(), true);
        this.timer.reset();
    }
    
    @Override
    public void onTick() {
        this.doAura();
    }
    
    public Aura() {
        super("Aura", "Swords enemies.", Category.COMBAT, true, false, true);
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)6.0f, (T)0.1f, (T)7.0f));
        this.delay = (Setting<Boolean>)this.register(new Setting("HitDelay", (T)Boolean.TRUE));
        this.onlySharp = (Setting<Boolean>)this.register(new Setting("SwordOnly", (T)Boolean.TRUE));
        this.raytrace = (Setting<Float>)this.register(new Setting("Raytrace", (T)6.0f, (T)0.1f, (T)7.0f, "Wall Range."));
        this.players = (Setting<Boolean>)this.register(new Setting("Players", (T)Boolean.TRUE));
        this.mobs = (Setting<Boolean>)this.register(new Setting("Mobs", (T)Boolean.FALSE));
        this.animals = (Setting<Boolean>)this.register(new Setting("Animals", (T)Boolean.FALSE));
        this.vehicles = (Setting<Boolean>)this.register(new Setting("Entities", (T)Boolean.FALSE));
        this.projectiles = (Setting<Boolean>)this.register(new Setting("Projectiles", (T)Boolean.FALSE));
        this.tps = (Setting<Boolean>)this.register(new Setting("TpsSync", (T)Boolean.TRUE));
        this.packet = (Setting<Boolean>)this.register(new Setting("Packet", (T)Boolean.FALSE));
        this.timer = new Timer();
        this.render = (Setting<RenderMode>)this.register(new Setting("Render", (T)RenderMode.JELLO));
    }
    
    private double easeInOutQuad(final double d) {
        return (d < 0.5) ? (2.0 * d * d) : (1.0 - Math.pow(-2.0 * d + 2.0, 2.0) / 2.0);
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (Aura.target != null) {
            if (this.render.getValue() == RenderMode.OLD) {
                RenderUtil.drawEntityBoxESP(Aura.target, ColorManager.getCurrent(), true, new Color(255, 255, 255, 130), 0.7f, true, true, 35);
            }
            else if (this.render.getValue() == RenderMode.JELLO) {
                final double d = 1500.0;
                final double d2 = System.currentTimeMillis() % d;
                final boolean bl = d2 > d / 2.0;
                double d3 = d2 / (d / 2.0);
                d3 = (bl ? (d3 - 1.0) : (1.0 - d3));
                d3 = this.easeInOutQuad(d3);
                Aura.mc.entityRenderer.disableLightmap();
                GL11.glPushMatrix();
                GL11.glDisable(3553);
                GL11.glBlendFunc(770, 771);
                GL11.glEnable(2848);
                GL11.glEnable(3042);
                GL11.glDisable(2929);
                GL11.glDisable(2884);
                GL11.glShadeModel(7425);
                Aura.mc.entityRenderer.disableLightmap();
                final double d4 = Aura.target.width;
                final double d5 = Aura.target.height + 0.1;
                final double d6 = Aura.target.lastTickPosX + (Aura.target.posX - Aura.target.lastTickPosX) * Aura.mc.getRenderPartialTicks() - Aura.mc.getRenderManager().viewerPosX;
                final double d7 = Aura.target.lastTickPosY + (Aura.target.posY - Aura.target.lastTickPosY) * Aura.mc.getRenderPartialTicks() - Aura.mc.getRenderManager().viewerPosY + d5 * d3;
                final double d8 = Aura.target.lastTickPosZ + (Aura.target.posZ - Aura.target.lastTickPosZ) * Aura.mc.getRenderPartialTicks() - Aura.mc.getRenderManager().viewerPosZ;
                final double d9 = d5 / 3.0 * ((d3 > 0.5) ? (1.0 - d3) : d3) * (bl ? -1 : 1);
                for (int i = 0; i < 360; i += 5) {
                    final ColorManager colorManager = Storm.colorManager;
                    final Color color = ColorManager.getRainbow();
                    final double d10 = d6 - Math.sin(i * 3.141592653589793 / 180.0) * d4;
                    final double d11 = d8 + Math.cos(i * 3.141592653589793 / 180.0) * d4;
                    final double d12 = d6 - Math.sin((i - 5) * 3.141592653589793 / 180.0) * d4;
                    final double d13 = d8 + Math.cos((i - 5) * 3.141592653589793 / 180.0) * d4;
                    GL11.glBegin(7);
                    GL11.glColor4f(ColorUtil.pulseColor(color, 200, 1).getRed() / 255.0f, ColorUtil.pulseColor(color, 200, 1).getGreen() / 255.0f, ColorUtil.pulseColor(color, 200, 1).getBlue() / 255.0f, 0.0f);
                    GL11.glVertex3d(d10, d7 + d9, d11);
                    GL11.glVertex3d(d12, d7 + d9, d13);
                    GL11.glColor4f(ColorUtil.pulseColor(color, 200, 1).getRed() / 255.0f, ColorUtil.pulseColor(color, 200, 1).getGreen() / 255.0f, ColorUtil.pulseColor(color, 200, 1).getBlue() / 255.0f, 200.0f);
                    GL11.glVertex3d(d12, d7, d13);
                    GL11.glVertex3d(d10, d7, d11);
                    GL11.glEnd();
                    GL11.glBegin(2);
                    GL11.glVertex3d(d12, d7, d13);
                    GL11.glVertex3d(d10, d7, d11);
                    GL11.glEnd();
                }
                GL11.glEnable(2884);
                GL11.glShadeModel(7424);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glEnable(2929);
                GL11.glDisable(2848);
                GL11.glDisable(3042);
                GL11.glEnable(3553);
                GL11.glPopMatrix();
            }
        }
    }
    
    @Override
    public String getDisplayInfo() {
        if (Aura.target instanceof EntityPlayer) {
            return Aura.target.getName();
        }
        return null;
    }
    
    private Entity getTarget() {
        Entity entity = null;
        double d = this.range.getValue();
        double d2 = 36.0;
        for (final Entity entity2 : Aura.mc.world.loadedEntityList) {
            if ((!this.players.getValue() || !(entity2 instanceof EntityPlayer)) && (!this.animals.getValue() || !EntityUtil.isPassive(entity2)) && (!this.mobs.getValue() || EntityUtil.isMobAggressive(entity2)) && (!this.vehicles.getValue() || !EntityUtil.isVehicle(entity2))) {
                if (!this.projectiles.getValue()) {
                    continue;
                }
                if (!EntityUtil.isProjectile(entity2)) {
                    continue;
                }
            }
            if (entity2 != null && EntityUtil.isntValid(entity2, d)) {
                continue;
            }
            if (!Aura.mc.player.canEntityBeSeen(entity2) && !EntityUtil.canEntityFeetBeSeen(entity2) && Aura.mc.player.getDistanceSq(entity2) > MathUtil.square(this.raytrace.getValue())) {
                continue;
            }
            if (entity == null) {
                entity = entity2;
                d = Aura.mc.player.getDistanceSq(entity2);
                d2 = EntityUtil.getHealth(entity2);
            }
            else {
                if (entity2 instanceof EntityPlayer && DamageUtil.isArmorLow((EntityPlayer)entity2, 18)) {
                    entity = entity2;
                    break;
                }
                if (Aura.mc.player.getDistanceSq(entity2) < d) {
                    entity = entity2;
                    d = Aura.mc.player.getDistanceSq(entity2);
                    d2 = EntityUtil.getHealth(entity2);
                }
                if (EntityUtil.getHealth(entity2) >= d2) {
                    continue;
                }
                entity = entity2;
                d = Aura.mc.player.getDistanceSq(entity2);
                d2 = EntityUtil.getHealth(entity2);
            }
        }
        return entity;
    }
    
    private enum RenderMode
    {
        OLD, 
        JELLO, 
        OFF;
    }
}
