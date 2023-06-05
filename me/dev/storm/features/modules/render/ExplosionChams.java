//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.render;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import java.awt.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import me.dev.storm.event.events.*;
import java.util.*;
import org.lwjgl.opengl.*;

public class ExplosionChams extends Module
{
    public final Setting<Boolean> syncColor;
    public final Setting<Color> daColor;
    public static Color color8;
    public static HashMap<UUID, Thingering> thingers;
    
    public ExplosionChams() {
        super("ExplosionChams", "Renders a circle around newly spawned entities.", Module.Category.RENDER, true, false, false);
        this.syncColor = (Setting<Boolean>)this.register(new Setting("Sync", (T)false));
        this.daColor = (Setting<Color>)this.register(new Setting("Color", (T)new Color(255, 255, 255, 255)));
    }
    
    public String onUpdate() {
        if (fullNullCheck()) {}
        ExplosionChams.color8 = (this.syncColor.getValue() ? globalColor(255) : this.daColor.getValue());
        for (final Entity entity : ExplosionChams.mc.world.loadedEntityList) {
            if (entity instanceof EntityEnderCrystal) {
                if (ExplosionChams.thingers.containsKey(entity.getUniqueID())) {
                    continue;
                }
                ExplosionChams.thingers.put(entity.getUniqueID(), new Thingering(this, entity));
                ExplosionChams.thingers.get(entity.getUniqueID()).starTime = System.currentTimeMillis();
            }
        }
        return null;
    }
    
    public void onRender3D(final Render3DEvent Render3DEvent) {
        if (ExplosionChams.mc.player == null || ExplosionChams.mc.world == null) {
            return;
        }
        for (final Map.Entry<UUID, Thingering> entry : ExplosionChams.thingers.entrySet()) {
            if (System.currentTimeMillis() - entry.getValue().starTime >= 1500L) {
                continue;
            }
            float opacity = Float.intBitsToFloat(Float.floatToIntBits(1.2886874E38f) ^ 0x7EC1E66F);
            final long time = System.currentTimeMillis();
            final long duration = time - entry.getValue().starTime;
            if (duration < 1500L) {
                opacity = Float.intBitsToFloat(Float.floatToIntBits(13.7902155f) ^ 0x7EDCA4B9) - duration / Float.intBitsToFloat(Float.floatToIntBits(6.1687006E-4f) ^ 0x7E9A3573);
            }
            drawCircle(entry.getValue().entity, Render3DEvent.getPartialTicks(), Double.longBitsToDouble(Double.doubleToLongBits(205.3116845075892) ^ 0x7F89A9F951C9D87FL), (System.currentTimeMillis() - entry.getValue().starTime) / Float.intBitsToFloat(Float.floatToIntBits(0.025765074f) ^ 0x7E1B1147), opacity);
        }
    }
    
    public static void drawCircle(final Entity entity, final float partialTicks, final double rad, final float plusY, final float alpha) {
        final Color color = new Color(ExplosionChams.color8.getRed(), ExplosionChams.color8.getGreen(), ExplosionChams.color8.getBlue());
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        startSmooth();
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(Float.intBitsToFloat(Float.floatToIntBits(0.8191538f) ^ 0x7F11B410));
        GL11.glBegin(3);
        final double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks - ExplosionChams.mc.getRenderManager().viewerPosX;
        final double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks - ExplosionChams.mc.getRenderManager().viewerPosY;
        final double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks - ExplosionChams.mc.getRenderManager().viewerPosZ;
        final float r = Float.intBitsToFloat(Float.floatToIntBits(3180.4917f) ^ 0x7EC6475F) * color.getRed();
        final float g = Float.intBitsToFloat(Float.floatToIntBits(4554.3037f) ^ 0x7E0ED2EF) * color.getGreen();
        final float b = Float.intBitsToFloat(Float.floatToIntBits(29994.996f) ^ 0x7D6AD57F) * color.getBlue();
        final double pix2 = Double.longBitsToDouble(Double.doubleToLongBits(0.12418750450734782) ^ 0x7FA6EB3BC22A7D2FL);
        for (int i = 0; i <= 90; ++i) {
            GL11.glColor4f(r, g, b, alpha);
            GL11.glVertex3d(x + rad * Math.cos(i * Double.longBitsToDouble(Double.doubleToLongBits(0.038923223119235344) ^ 0x7FBACC45F0F011C7L) / Double.longBitsToDouble(Double.doubleToLongBits(0.010043755046771538) ^ 0x7FC211D1FBA3AC6BL)), y + plusY / Float.intBitsToFloat(Float.floatToIntBits(0.13022153f) ^ 0x7F2558CB), z + rad * Math.sin(i * Double.longBitsToDouble(Double.doubleToLongBits(0.012655047216797511) ^ 0x7F90CB18FB234FBFL) / Double.longBitsToDouble(Double.doubleToLongBits(0.00992417958121009) ^ 0x7FC2D320D5ED6BD3L)));
        }
        GL11.glEnd();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        endSmooth();
        GL11.glEnable(3553);
        GL11.glPopMatrix();
    }
    
    public static void startSmooth() {
        GL11.glEnable(2848);
        GL11.glEnable(2881);
        GL11.glEnable(2832);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glHint(3153, 4354);
    }
    
    public static void endSmooth() {
        GL11.glDisable(2848);
        GL11.glDisable(2881);
        GL11.glEnable(2832);
    }
    
    static {
        ExplosionChams.thingers = new HashMap<UUID, Thingering>();
    }
    
    public class Thingering
    {
        public Entity entity;
        public long starTime;
        public ExplosionChams this$0;
        
        public Thingering(final ExplosionChams this$0, final Entity entity) {
            this.this$0 = this$0;
            this.entity = entity;
            this.starTime = 0L;
        }
    }
}
