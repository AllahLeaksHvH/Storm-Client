//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.render;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.modules.render.jumpanim.*;
import me.dev.storm.features.setting.*;
import me.dev.storm.util.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.dev.storm.event.events.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import java.util.*;

public class JumpCircle extends Module
{
    public static aAnimation jumpanim;
    static List<Circle> circles;
    public Setting<Float> range2;
    public Setting<Float> range;
    public Setting<Integer> lifetime;
    public Setting<mode> Mode;
    public Timer timer;
    boolean check;
    
    public JumpCircle() {
        super("JumpCircle", "jump circle render", Module.Category.RENDER, true, false, false);
        this.range2 = (Setting<Float>)this.register(new Setting("Radius", (T)1.0f, (T)0.1f, (T)3.0f));
        this.range = (Setting<Float>)this.register(new Setting("Radius2", (T)3.0f, (T)0.1f, (T)3.0f));
        this.lifetime = (Setting<Integer>)this.register(new Setting("Live", (T)1000, (T)1, (T)10000));
        this.Mode = (Setting<mode>)this.register(new Setting("Mode", (T)mode.Jump));
        this.timer = new Timer();
        this.check = false;
    }
    
    public String onUpdate() {
        if (JumpCircle.mc.player.collidedVertically && this.Mode.getValue() == mode.Landing && this.check) {
            JumpCircle.circles.add(new Circle(new Vec3d(JumpCircle.mc.player.posX, JumpCircle.mc.player.posY + 0.0625, JumpCircle.mc.player.posZ)));
            this.check = false;
        }
        JumpCircle.jumpanim.update();
        for (final Circle circle : JumpCircle.circles) {
            circle.update();
        }
        JumpCircle.circles.removeIf(Circle::update);
        return null;
    }
    
    @SubscribeEvent
    public void onJump(final JumpEvent e) {
        if (this.Mode.getValue() == mode.Jump) {
            JumpCircle.circles.add(new Circle(new Vec3d(JumpCircle.mc.player.posX, JumpCircle.mc.player.posY + 0.0625, JumpCircle.mc.player.posZ)));
        }
        this.check = true;
    }
    
    public void onRender3D(final Render3DEvent event) {
        GlStateManager.pushMatrix();
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glDisable(3008);
        GL11.glEnable(2848);
        GlStateManager.resetColor();
        GL11.glShadeModel(7425);
        final double ix = -(JumpCircle.mc.player.lastTickPosX + (JumpCircle.mc.player.posX - JumpCircle.mc.player.lastTickPosX) * JumpCircle.mc.getRenderPartialTicks());
        final double iy = -(JumpCircle.mc.player.lastTickPosY + (JumpCircle.mc.player.posY - JumpCircle.mc.player.lastTickPosY) * JumpCircle.mc.getRenderPartialTicks());
        final double iz = -(JumpCircle.mc.player.lastTickPosZ + (JumpCircle.mc.player.posZ - JumpCircle.mc.player.lastTickPosZ) * JumpCircle.mc.getRenderPartialTicks());
        GL11.glTranslated(ix, iy, iz);
        Collections.reverse(JumpCircle.circles);
        try {
            for (final Circle c : JumpCircle.circles) {
                final double x = c.position().x;
                final double y = c.position().y;
                final double z = c.position().z;
                final float k = c.timer.getPassedTimeMs() / (float)this.lifetime.getValue();
                final float start = k * this.range.getValue();
                final float end = k * this.range2.getValue();
                final float middle = (start + end) / 2.0f;
                GL11.glBegin(8);
                for (int i = 0; i <= 360; i += 5) {
                    final double stage = (i + 90) / 360.0;
                    final int clr = JumpCircle.jumpanim.getColor(stage);
                    final int red = clr >> 16 & 0xFF;
                    final int green = clr >> 8 & 0xFF;
                    final int blue = clr & 0xFF;
                    GL11.glColor4f(red / 255.0f, green / 255.0f, blue / 255.0f, 0.0f);
                    GL11.glVertex3d(x + Math.cos(Math.toRadians(i)) * start, y, z + Math.sin(Math.toRadians(i)) * start);
                    GL11.glColor4f(red / 255.0f, green / 255.0f, blue / 255.0f, 1.0f - c.timer.getPassedTimeMs() / (float)this.lifetime.getValue());
                    GL11.glVertex3d(x + Math.cos(Math.toRadians(i)) * middle, y, z + Math.sin(Math.toRadians(i)) * middle);
                }
                GL11.glEnd();
                GL11.glBegin(8);
                for (int i = 0; i <= 360; i += 5) {
                    final double stage = (i + 90) / 360.0;
                    final int clr = JumpCircle.jumpanim.getColor(stage);
                    final int red = clr >> 16 & 0xFF;
                    final int green = clr >> 8 & 0xFF;
                    final int blue = clr & 0xFF;
                    GL11.glColor4f(red / 255.0f, green / 255.0f, blue / 255.0f, 1.0f - c.timer.getPassedTimeMs() / (float)this.lifetime.getValue());
                    GL11.glVertex3d(x + Math.cos(Math.toRadians(i)) * middle, y, z + Math.sin(Math.toRadians(i)) * middle);
                    GL11.glColor4f(red / 255.0f, green / 255.0f, blue / 255.0f, 0.0f);
                    GL11.glVertex3d(x + Math.cos(Math.toRadians(i)) * end, y, z + Math.sin(Math.toRadians(i)) * end);
                }
                GL11.glEnd();
            }
        }
        catch (Exception ex) {}
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glEnable(3008);
        GlStateManager.resetColor();
        Collections.reverse(JumpCircle.circles);
        GlStateManager.popMatrix();
        GL11.glShadeModel(7424);
    }
    
    static {
        JumpCircle.jumpanim = new aAnimation();
        JumpCircle.circles = new ArrayList<Circle>();
    }
    
    public enum mode
    {
        Jump, 
        Landing;
    }
    
    class Circle
    {
        private final Vec3d vec;
        Timer timer;
        
        Circle(final Vec3d vec) {
            this.timer = new Timer();
            this.vec = vec;
            this.timer.reset();
        }
        
        Vec3d position() {
            return this.vec;
        }
        
        public boolean update() {
            return this.timer.passedMs(JumpCircle.this.lifetime.getValue());
        }
    }
}
