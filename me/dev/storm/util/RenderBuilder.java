//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.util;

import java.awt.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;

public class RenderBuilder
{
    private static boolean setup;
    private static boolean depth;
    private static boolean blend;
    private static boolean texture;
    private static boolean cull;
    private static boolean alpha;
    private static boolean shade;
    private static AxisAlignedBB axisAlignedBB;
    private static Box box;
    private static double height;
    private static double length;
    private static double width;
    private static Color color;
    
    public RenderBuilder setup() {
        GlStateManager.pushMatrix();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        RenderBuilder.setup = true;
        return this;
    }
    
    public RenderBuilder depth(final boolean in) {
        if (in) {
            GlStateManager.disableDepth();
            GlStateManager.depthMask(false);
        }
        RenderBuilder.depth = in;
        return this;
    }
    
    public RenderBuilder blend() {
        GlStateManager.enableBlend();
        RenderBuilder.blend = true;
        return this;
    }
    
    public RenderBuilder texture() {
        GlStateManager.disableTexture2D();
        RenderBuilder.texture = true;
        return this;
    }
    
    public RenderBuilder line(final float width) {
        GlStateManager.glLineWidth(width);
        return this;
    }
    
    public RenderBuilder cull(final boolean in) {
        if (RenderBuilder.cull) {
            GlStateManager.disableCull();
        }
        RenderBuilder.cull = in;
        return this;
    }
    
    public RenderBuilder alpha(final boolean in) {
        if (RenderBuilder.alpha) {
            GlStateManager.disableAlpha();
        }
        RenderBuilder.alpha = in;
        return this;
    }
    
    public RenderBuilder shade(final boolean in) {
        if (in) {
            GlStateManager.shadeModel(7425);
        }
        RenderBuilder.shade = in;
        return this;
    }
    
    public RenderBuilder build() {
        if (RenderBuilder.depth) {
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
        }
        if (RenderBuilder.texture) {
            GlStateManager.enableTexture2D();
        }
        if (RenderBuilder.blend) {
            GlStateManager.disableBlend();
        }
        if (RenderBuilder.cull) {
            GlStateManager.enableCull();
        }
        if (RenderBuilder.alpha) {
            GlStateManager.enableAlpha();
        }
        if (RenderBuilder.shade) {
            GlStateManager.shadeModel(7424);
        }
        if (RenderBuilder.setup) {
            GL11.glDisable(2848);
            GlStateManager.popMatrix();
        }
        return this;
    }
    
    public RenderBuilder position(final BlockPos in) {
        this.position(new AxisAlignedBB((double)in.getX(), (double)in.getY(), (double)in.getZ(), (double)(in.getX() + 1), (double)(in.getY() + 1), (double)(in.getZ() + 1)));
        return this;
    }
    
    public RenderBuilder position(final Vec3d in) {
        this.position(new AxisAlignedBB(in.x, in.y, in.z, in.x + 1.0, in.y + 1.0, in.z + 1.0));
        return this;
    }
    
    public RenderBuilder position(final AxisAlignedBB in) {
        RenderBuilder.axisAlignedBB = in;
        return this;
    }
    
    public RenderBuilder height(final double in) {
        RenderBuilder.height = in;
        return this;
    }
    
    public RenderBuilder width(final double in) {
        RenderBuilder.width = in;
        return this;
    }
    
    public RenderBuilder length(final double in) {
        RenderBuilder.length = in;
        return this;
    }
    
    public RenderBuilder color(final Color in) {
        RenderBuilder.color = in;
        return this;
    }
    
    public RenderBuilder box(final Box in) {
        RenderBuilder.box = in;
        return this;
    }
    
    public AxisAlignedBB getAxisAlignedBB() {
        return RenderBuilder.axisAlignedBB;
    }
    
    public double getHeight() {
        return RenderBuilder.height;
    }
    
    public double getWidth() {
        return RenderBuilder.width;
    }
    
    public double getLength() {
        return RenderBuilder.length;
    }
    
    public Color getColor() {
        return RenderBuilder.color;
    }
    
    public Box getBox() {
        return RenderBuilder.box;
    }
    
    static {
        RenderBuilder.axisAlignedBB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        RenderBuilder.box = Box.FILL;
        RenderBuilder.color = Color.WHITE;
    }
    
    public enum Box
    {
        FILL, 
        OUTLINE, 
        BOTH, 
        GLOW, 
        REVERSE, 
        CLAW, 
        NONE;
    }
}
