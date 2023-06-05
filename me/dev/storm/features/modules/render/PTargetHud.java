//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.render;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import me.dev.storm.features.modules.render.ccTargetHud.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.player.*;
import me.dev.storm.*;
import net.minecraft.entity.*;
import java.awt.*;
import net.minecraft.client.entity.*;
import me.dev.storm.features.modules.misc.*;
import me.dev.storm.util.*;
import me.dev.storm.util.trollhack.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;
import me.dev.storm.features.modules.combat.*;

public class PTargetHud extends Module
{
    public final Setting<Float> x;
    public final Setting<Float> y;
    public final Setting<HealthColor> healthColor;
    public final Setting<Float> range;
    public final Setting<Integer> red;
    public final Setting<Integer> green;
    public final Setting<Integer> blue;
    public final Setting<Integer> alpha;
    public float health;
    
    public PTargetHud() {
        super("TargetHud", "Draws a rectangle on your screen with some useful information about the enemy", Module.Category.RENDER, true, false, false);
        this.x = (Setting<Float>)this.register(new Setting("X", (T)300.0f, (T)0.0f, (T)1500.0f));
        this.y = (Setting<Float>)this.register(new Setting("Y", (T)300.0f, (T)0.0f, (T)1500.0f));
        this.healthColor = (Setting<HealthColor>)this.register(new Setting("Health Color", (T)HealthColor.Rainbow));
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)400.0f, (T)0.0f, (T)600.0f));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)30, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)167, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)255, (T)0, (T)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)60, (T)0, (T)255));
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent.Text event) {
        if (PTargetHud.mc.player != null && PTargetHud.mc.world != null) {
            final EntityPlayer target = (EntityPlayer)PTargetHud.mc.world.playerEntities.stream().filter(entity -> !Storm.friendManager.isFriend(entity) && !entity.equals((Object)PTargetHud.mc.player) && !entity.isDead && entity.getDistance((Entity)PTargetHud.mc.player) <= this.range.getValue()).min(Comparator.comparingDouble(entity -> entity.getDistance((Entity)PTargetHud.mc.player))).orElse(null);
            if (target != null) {
                this.drawRoundedRect(this.x.getValue(), this.y.getValue(), this.x.getValue() + 150.0f, this.y.getValue() + 54.0f, 6.0, new Color(20, 20, 20, 65).hashCode());
                this.drawOutlinedRoundedRect(this.x.getValue(), this.y.getValue(), this.x.getValue() + 150.0f, this.y.getValue() + 54.0f, 6.0, 3.0f, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()).hashCode());
                this.drawPlayerHeadTexture(this.x.getValue() + 5.0f, this.y.getValue() + 5.0f, 8.0f, 8.0f, 8, 8, 32, 32, 64.0f, 64.0f, (AbstractClientPlayer)target);
                PTargetHud.mc.fontRenderer.drawString("Name: " + target.getName(), this.x.getValue() + 40.0f, this.y.getValue() + 5.5f, -1, true);
                PTargetHud.mc.fontRenderer.drawString("Pops: " + PopCounter.TotemPopContainer.getOrDefault(target.getCommandSenderEntity(), 0), this.x.getValue() + 40.0f, this.y.getValue() + 14.0f, -1, true);
                PTargetHud.mc.fontRenderer.drawString("Distance: " + MathUtil.round(target.getDistance((Entity)PTargetHud.mc.player), 1), this.x.getValue() + 80.0f, this.y.getValue() + 14.0f, -1, true);
                final float health = target.getHealth() + target.getAbsorptionAmount();
                this.health += (health * 3.5f - this.health) * 0.075f;
                if (this.healthColor.getValue() == HealthColor.Auto) {
                    RenderUtil.drawRect(this.x.getValue() + 5.0f, this.y.getValue() + 43.0f, this.x.getValue() + this.health, this.y.getValue() + 48.0f, this.getColorByHealth(health).hashCode());
                }
                else if (this.healthColor.getValue() == HealthColor.Rainbow) {
                    final float i = 0.0f;
                    for (int i2 = 0; i2 < this.health; i2 += (int)0.91f, ++i2) {
                        RenderUtil.drawRect(this.x.getValue() + 5.0f + i2, this.y.getValue() + 43.0f, this.x.getValue() + 5.0f + i2 + 1.0f, this.y.getValue() + 48.0f, ColorUtil.getRainbow(1.0f * (this.x.getValue() + 5.0f) * 1.4f + i2 / 6.0f, 0.5f, 1.0f, 4500.0f));
                    }
                }
                PTargetHud.mc.fontRenderer.drawString(MathUtil.round(health, 1) + "", this.x.getValue() + 6.0f + ((this.healthColor.getValue() == HealthColor.Rainbow) ? (this.health * 0.91f + 4.0f) : this.health) + 3.0f, this.y.getValue() + 42.0f, -1, true);
                GlStateManager.pushMatrix();
                float i = this.x.getValue() + 85.0f;
                for (final ItemStack armor : target.inventory.armorInventory) {
                    GlStateManager.enableDepth();
                    PTargetHud.mc.getItemRenderer().itemRenderer.renderItemAndEffectIntoGUI(armor, (int)i, (int)(this.y.getValue() + 23.0f));
                    PTargetHud.mc.getItemRenderer().itemRenderer.renderItemOverlayIntoGUI(PTargetHud.mc.fontRenderer, armor, (int)i, (int)(this.y.getValue() + 23.0f), "");
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableLighting();
                    GlStateManager.disableDepth();
                    i -= 15.0f;
                }
                GlStateManager.popMatrix();
            }
        }
    }
    
    public Color getColorByHealth(final float health) {
        return (health > 20.0f) ? new Color(0, 255, 10, 115) : ((health > 10.0f) ? new Color(255, 255, 0, 115) : new Color(255, 0, 10, 115));
    }
    
    public void drawPlayerHeadTexture(final float x, final float y, final float u, final float v, final int uWidth, final int vHeight, final int width, final int height, final float tileWidth, final float tileHeight, final AbstractClientPlayer target) {
        final ResourceLocation skinLocation = target.getLocationSkin();
        PTargetHud.mc.getTextureManager().bindTexture(skinLocation);
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableBlend();
        Gui.drawScaledCustomSizeModalRect((int)x, (int)y, u, v, uWidth, vHeight, width, height, tileWidth, tileHeight);
        GlStateManager.popMatrix();
    }
    
    public void drawOutlinedRoundedRect(double x, double y, double width, double height, final double radius, final float lineWidth, final int color) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5, 0.5, 0.5);
        x *= 2.0;
        y *= 2.0;
        width *= 2.0;
        height *= 2.0;
        GL11.glLineWidth(lineWidth);
        GL11.glDisable(3553);
        GL11.glColor4f(r, g, b, a);
        GL11.glEnable(2848);
        GL11.glBegin(2);
        for (int i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(x + radius + Math.sin(i * 3.141592653589793 / 180.0) * radius * -1.0, y + radius + Math.cos(i * 3.141592653589793 / 180.0) * radius * -1.0);
        }
        for (int i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(x + radius + Math.sin(i * 3.141592653589793 / 180.0) * radius * -1.0, height - radius + Math.cos(i * 3.141592653589793 / 180.0) * radius * -1.0);
        }
        for (int i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(width - radius + Math.sin(i * 3.141592653589793 / 180.0) * radius, height - radius + Math.cos(i * 3.141592653589793 / 180.0) * radius);
        }
        for (int i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(width - radius + Math.sin(i * 3.141592653589793 / 180.0) * radius, y + radius + Math.cos(i * 3.141592653589793 / 180.0) * radius);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glScaled(2.0, 2.0, 2.0);
        GL11.glPopAttrib();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GlStateManager.resetColor();
    }
    
    public void drawRoundedRect(double x, double y, double width, double height, final double radius, final int color) {
        GlStateManager.pushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5, 0.5, 0.5);
        x *= 2.0;
        y *= 2.0;
        width *= 2.0;
        height *= 2.0;
        GL11.glColor4f(r, g, b, a);
        GL11.glEnable(2848);
        GL11.glBegin(9);
        for (int i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(x + radius + Math.sin(i * 3.141592653589793 / 180.0) * radius * -1.0, y + radius + Math.cos(i * 3.141592653589793 / 180.0) * radius * -1.0);
        }
        for (int i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(x + radius + Math.sin(i * 3.141592653589793 / 180.0) * radius * -1.0, height - radius + Math.cos(i * 3.141592653589793 / 180.0) * radius * -1.0);
        }
        for (int i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(width - radius + Math.sin(i * 3.141592653589793 / 180.0) * radius, height - radius + Math.cos(i * 3.141592653589793 / 180.0) * radius);
        }
        for (int i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(width - radius + Math.sin(i * 3.141592653589793 / 180.0) * radius, y + radius + Math.cos(i * 3.141592653589793 / 180.0) * radius);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glScaled(2.0, 2.0, 2.0);
        GL11.glPopAttrib();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GlStateManager.resetColor();
    }
    
    public String getDisplayInfo() {
        if (this.alwaysListening) {
            return "§aSwitch";
        }
        if (AutoCrystal.target != null) {
            return AutoCrystal.target.getName();
        }
        return null;
    }
}
