//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.render;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import java.awt.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.item.*;
import java.util.*;
import me.dev.storm.event.events.*;
import org.lwjgl.opengl.*;

public class Trails extends Module
{
    private final Setting<Float> thick;
    private final Setting<Boolean> pearl;
    private final Setting<Boolean> arrows;
    private final Setting<Boolean> exp;
    private final Setting<Boolean> potions;
    private final Setting<Boolean> render;
    private final Setting<Double> aliveTime;
    private final Setting<Integer> rDelay;
    private final Setting<Color> colorC;
    private final HashMap<UUID, List<Vec3d>> poses;
    private final HashMap<UUID, Double> time;
    
    public Trails() {
        super("Trails", "Draws a line behind projectiles", Module.Category.RENDER, true, false, false);
        this.thick = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.5f, (T)0.1f, (T)5.0f));
        this.pearl = (Setting<Boolean>)this.register(new Setting("Pearl", (T)true));
        this.arrows = (Setting<Boolean>)this.register(new Setting("Arrows", (T)true));
        this.exp = (Setting<Boolean>)this.register(new Setting("Experience Bottles", (T)true));
        this.potions = (Setting<Boolean>)this.register(new Setting("Splash Potions", (T)true));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (T)true));
        this.aliveTime = (Setting<Double>)this.register(new Setting("Fade Time", (T)5.0, (T)0.0, (T)20.0));
        this.rDelay = (Setting<Integer>)this.register(new Setting("Delay Before Render", (T)120, (T)0, (T)360));
        this.colorC = (Setting<Color>)this.register(new Setting("Color", (T)new Color(40, 192, 255, 255)));
        this.poses = new HashMap<UUID, List<Vec3d>>();
        this.time = new HashMap<UUID, Double>();
    }
    
    public String onUpdate() {
        UUID toRemove = null;
        for (final UUID uuid : this.time.keySet()) {
            if (this.time.get(uuid) <= 0.0) {
                this.poses.remove(uuid);
                toRemove = uuid;
            }
            else {
                this.time.replace(uuid, this.time.get(uuid) - 0.05);
            }
        }
        if (toRemove != null) {
            this.time.remove(toRemove);
            toRemove = null;
        }
        if (this.arrows.getValue() || this.exp.getValue() || this.pearl.getValue() || this.potions.getValue()) {
            for (final Entity e : Trails.mc.world.getLoadedEntityList()) {
                if (!(e instanceof EntityArrow) && !(e instanceof EntityExpBottle) && !(e instanceof EntityPotion)) {
                    continue;
                }
                if (!this.poses.containsKey(e.getUniqueID())) {
                    this.poses.put(e.getUniqueID(), new ArrayList<Vec3d>(Collections.singletonList(e.getPositionVector())));
                    this.time.put(e.getUniqueID(), 0.05);
                }
                else {
                    this.time.replace(e.getUniqueID(), 0.05);
                    final List<Vec3d> v = this.poses.get(e.getUniqueID());
                    v.add(e.getPositionVector());
                }
            }
        }
        for (final Entity e : Trails.mc.world.getLoadedEntityList()) {
            if (!(e instanceof EntityEnderPearl)) {
                continue;
            }
            if (!this.poses.containsKey(e.getUniqueID())) {
                this.poses.put(e.getUniqueID(), new ArrayList<Vec3d>(Collections.singletonList(e.getPositionVector())));
                this.time.put(e.getUniqueID(), this.aliveTime.getValue());
            }
            else {
                this.time.replace(e.getUniqueID(), this.aliveTime.getValue());
                final List<Vec3d> v = this.poses.get(e.getUniqueID());
                v.add(e.getPositionVector());
            }
        }
        return null;
    }
    
    public void onRender3D(final Render3DEvent event) {
        if (!this.render.getValue() && !this.poses.isEmpty()) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth((float)this.thick.getValue());
        for (final UUID uuid : this.poses.keySet()) {
            if (this.poses.get(uuid).size() <= 2) {
                continue;
            }
            int delay = 0;
            GL11.glBegin(1);
            for (int i = 1; i < this.poses.get(uuid).size(); ++i) {
                delay += this.rDelay.getValue();
                GL11.glColor4d((double)(this.colorC.getValue().getRed() / 255.0f), (double)(this.colorC.getValue().getGreen() / 255.0f), (double)(this.colorC.getValue().getBlue() / 255.0f), (double)(this.colorC.getValue().getAlpha() / 255.0f));
                final List<Vec3d> pos = this.poses.get(uuid);
                GL11.glVertex3d(pos.get(i).x - Trails.mc.getRenderManager().viewerPosX, pos.get(i).y - Trails.mc.getRenderManager().viewerPosY, pos.get(i).z - Trails.mc.getRenderManager().viewerPosZ);
                GL11.glVertex3d(pos.get(i - 1).x - Trails.mc.getRenderManager().viewerPosX, pos.get(i - 1).y - Trails.mc.getRenderManager().viewerPosY, pos.get(i - 1).z - Trails.mc.getRenderManager().viewerPosZ);
            }
            GL11.glEnd();
        }
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
}
