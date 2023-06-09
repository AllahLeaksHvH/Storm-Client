//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.mixin.mixins;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.model.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import me.dev.storm.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.*;
import me.dev.storm.features.modules.render.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.*;
import org.lwjgl.opengl.*;
import me.dev.storm.util.*;
import me.dev.storm.features.modules.client.*;
import java.awt.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderEnderCrystal.class })
public abstract class MixinRenderEnderCrystal
{
    @Shadow
    public ModelBase modelEnderCrystal;
    @Shadow
    public ModelBase modelEnderCrystalNoBase;
    @Final
    @Shadow
    private static ResourceLocation ENDER_CRYSTAL_TEXTURES;
    private static final ResourceLocation RES_ITEM_GLINT;
    
    @Shadow
    public abstract void doRender(final EntityEnderCrystal p0, final double p1, final double p2, final double p3, final float p4, final float p5);
    
    @Redirect(method = { "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    private void render1(final ModelBase var1, final Entity var2, final float var3, final float var4, final float var5, final float var6, final float var7, final float var8) {
        if (!Storm.moduleManager.isModuleEnabled((Class)CrystalChams.class)) {
            var1.render(var2, var3, var4, var5, var6, var7, var8);
        }
    }
    
    @Redirect(method = { "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V", ordinal = 1))
    private void render2(final ModelBase var1, final Entity var2, final float var3, final float var4, final float var5, final float var6, final float var7, final float var8) {
        if (!Storm.moduleManager.isModuleEnabled((Class)CrystalChams.class)) {
            var1.render(var2, var3, var4, var5, var6, var7, var8);
        }
    }
    
    @Inject(method = { "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V" }, at = { @At("RETURN") }, cancellable = true)
    public void IdoRender(final EntityEnderCrystal var1, final double var2, final double var4, final double var6, final float var8, final float var9, final CallbackInfo var10) {
        final Minecraft mc = Minecraft.getMinecraft();
        mc.gameSettings.fancyGraphics = false;
        if (Storm.moduleManager.isModuleEnabled((Class)ESP.class) && (boolean)ESP.getInstance().others.getValue()) {
            final float var11 = var1.innerRotation + var9;
            GlStateManager.pushMatrix();
            GlStateManager.translate(var2, var4, var6);
            Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(MixinRenderEnderCrystal.ENDER_CRYSTAL_TEXTURES);
            float var12 = MathHelper.sin(var11 * 0.2f) / 2.0f + 0.5f;
            var12 += var12 * var12;
            GL11.glLineWidth(5.0f);
            if (var1.shouldShowBottom()) {
                this.modelEnderCrystal.render((Entity)var1, 0.0f, var11 * 3.0f, var12 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            else {
                this.modelEnderCrystalNoBase.render((Entity)var1, 0.0f, var11 * 3.0f, var12 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            RenderUtil.renderOne((float)ESP.getInstance().lineWidth.getValue());
            if (var1.shouldShowBottom()) {
                this.modelEnderCrystal.render((Entity)var1, 0.0f, var11 * 3.0f, var12 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            else {
                this.modelEnderCrystalNoBase.render((Entity)var1, 0.0f, var11 * 3.0f, var12 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            RenderUtil.renderTwo();
            if (var1.shouldShowBottom()) {
                this.modelEnderCrystal.render((Entity)var1, 0.0f, var11 * 3.0f, var12 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            else {
                this.modelEnderCrystalNoBase.render((Entity)var1, 0.0f, var11 * 3.0f, var12 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            final Color rainbowColor1 = ESP.getInstance().colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)ESP.getInstance().red.getValue(), (int)ESP.getInstance().green.getValue(), (int)ESP.getInstance().blue.getValue());
            final Color rainbowColor2 = new Color(rainbowColor1.getRed(), rainbowColor1.getGreen(), rainbowColor1.getBlue());
            final Color n = new Color(rainbowColor2.getRed(), rainbowColor2.getGreen(), rainbowColor2.getBlue());
            RenderUtil.renderThree();
            RenderUtil.renderFour(rainbowColor1);
            RenderUtil.setColor(n);
            if (var1.shouldShowBottom()) {
                this.modelEnderCrystal.render((Entity)var1, 0.0f, var11 * 3.0f, var12 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            else {
                this.modelEnderCrystalNoBase.render((Entity)var1, 0.0f, var11 * 3.0f, var12 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            RenderUtil.renderFive();
            GlStateManager.popMatrix();
        }
        if (Storm.moduleManager.isModuleEnabled((Class)CrystalChams.class)) {
            GL11.glPushMatrix();
            final float var13 = var1.innerRotation + var9;
            GlStateManager.translate(var2, var4, var6);
            GlStateManager.scale((float)CrystalChams.INSTANCE.size.getValue(), (float)CrystalChams.INSTANCE.size.getValue(), (float)CrystalChams.INSTANCE.size.getValue());
            Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(MixinRenderEnderCrystal.ENDER_CRYSTAL_TEXTURES);
            float var14 = MathHelper.sin(var13 * 0.2f) / 2.0f + 0.5f;
            var14 += var14 * var14;
            final float spinSpeed = (float)CrystalChams.INSTANCE.crystalSpeed.getValue();
            final float bounceSpeed = (float)CrystalChams.INSTANCE.crystalBounce.getValue();
            if (CrystalChams.INSTANCE.texture.getValue()) {
                if (var1.shouldShowBottom()) {
                    this.modelEnderCrystal.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                }
                else {
                    this.modelEnderCrystalNoBase.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                }
            }
            GL11.glPushAttrib(1048575);
            if (((CrystalChams.modes)CrystalChams.INSTANCE.mode.getValue()).equals((Object)CrystalChams.modes.WIREFRAME)) {
                GL11.glPolygonMode(1032, 6913);
            }
            GL11.glDisable(3008);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glLineWidth(1.5f);
            GL11.glEnable(2960);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glEnable(10754);
            final Color visibleColor = CrystalChams.INSTANCE.colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)CrystalChams.INSTANCE.red.getValue(), (int)CrystalChams.INSTANCE.green.getValue(), (int)CrystalChams.INSTANCE.blue.getValue());
            final Color hiddenColor = CrystalChams.INSTANCE.colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)CrystalChams.INSTANCE.hiddenRed.getValue(), (int)CrystalChams.INSTANCE.hiddenGreen.getValue(), (int)CrystalChams.INSTANCE.hiddenBlue.getValue());
            final Color outlineColor = CrystalChams.INSTANCE.colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)CrystalChams.INSTANCE.outlineRed.getValue(), (int)CrystalChams.INSTANCE.outlineGreen.getValue(), (int)CrystalChams.INSTANCE.outlineBlue.getValue());
            if (CrystalChams.INSTANCE.hiddenSync.getValue()) {
                GL11.glColor4f(visibleColor.getRed() / 255.0f, visibleColor.getGreen() / 255.0f, visibleColor.getBlue() / 255.0f, (int)CrystalChams.INSTANCE.alpha.getValue() / 255.0f);
            }
            else {
                GL11.glColor4f(hiddenColor.getRed() / 255.0f, hiddenColor.getGreen() / 255.0f, hiddenColor.getBlue() / 255.0f, (int)CrystalChams.INSTANCE.hiddenAlpha.getValue() / 255.0f);
            }
            if (var1.shouldShowBottom()) {
                this.modelEnderCrystal.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
            }
            else {
                this.modelEnderCrystalNoBase.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
            }
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            GL11.glColor4f(visibleColor.getRed() / 255.0f, visibleColor.getGreen() / 255.0f, visibleColor.getBlue() / 255.0f, (int)CrystalChams.INSTANCE.alpha.getValue() / 255.0f);
            if (var1.shouldShowBottom()) {
                this.modelEnderCrystal.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
            }
            else {
                this.modelEnderCrystalNoBase.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
            }
            if (CrystalChams.INSTANCE.enchanted.getValue()) {
                mc.getTextureManager().bindTexture(MixinRenderEnderCrystal.RES_ITEM_GLINT);
                GL11.glTexCoord3d(1.0, 1.0, 1.0);
                GL11.glEnable(3553);
                GL11.glBlendFunc(768, 771);
                GL11.glColor4f((int)CrystalChams.INSTANCE.enchantRed.getValue() / 255.0f, (int)CrystalChams.INSTANCE.enchantGreen.getValue() / 255.0f, (int)CrystalChams.INSTANCE.enchantBlue.getValue() / 255.0f, (int)CrystalChams.INSTANCE.enchantAlpha.getValue() / 255.0f);
                if (var1.shouldShowBottom()) {
                    this.modelEnderCrystal.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                }
                else {
                    this.modelEnderCrystalNoBase.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                }
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            }
            GL11.glEnable(3042);
            GL11.glEnable(2896);
            GL11.glEnable(3553);
            GL11.glEnable(3008);
            GL11.glPopAttrib();
            if (CrystalChams.INSTANCE.outline.getValue()) {
                if (((CrystalChams.outlineModes)CrystalChams.INSTANCE.outlineMode.getValue()).equals((Object)CrystalChams.outlineModes.WIRE)) {
                    GL11.glPushAttrib(1048575);
                    GL11.glPolygonMode(1032, 6913);
                    GL11.glDisable(3008);
                    GL11.glDisable(3553);
                    GL11.glDisable(2896);
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                    GL11.glLineWidth((float)CrystalChams.INSTANCE.lineWidth.getValue());
                    GL11.glEnable(2960);
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                    GL11.glEnable(10754);
                    GL11.glColor4f(outlineColor.getRed() / 255.0f, outlineColor.getGreen() / 255.0f, outlineColor.getBlue() / 255.0f, (int)CrystalChams.INSTANCE.outlineAlpha.getValue() / 255.0f);
                    if (var1.shouldShowBottom()) {
                        this.modelEnderCrystal.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    else {
                        this.modelEnderCrystalNoBase.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    GL11.glEnable(2929);
                    GL11.glDepthMask(true);
                    if (var1.shouldShowBottom()) {
                        this.modelEnderCrystal.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    else {
                        this.modelEnderCrystalNoBase.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    GL11.glEnable(3042);
                    GL11.glEnable(2896);
                    GL11.glEnable(3553);
                    GL11.glEnable(3008);
                    GL11.glPopAttrib();
                }
                else {
                    RenderUtil.setColor(new Color(outlineColor.getRed(), outlineColor.getGreen(), outlineColor.getBlue()));
                    RenderUtil.renderOne((float)CrystalChams.INSTANCE.lineWidth.getValue());
                    if (var1.shouldShowBottom()) {
                        this.modelEnderCrystal.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    else {
                        this.modelEnderCrystalNoBase.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    RenderUtil.renderTwo();
                    if (var1.shouldShowBottom()) {
                        this.modelEnderCrystal.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    else {
                        this.modelEnderCrystalNoBase.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    RenderUtil.renderThree();
                    RenderUtil.renderFour(outlineColor);
                    RenderUtil.setColor(new Color(outlineColor.getRed(), outlineColor.getGreen(), outlineColor.getBlue()));
                    if (var1.shouldShowBottom()) {
                        this.modelEnderCrystal.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    else {
                        this.modelEnderCrystalNoBase.render((Entity)var1, 0.0f, var13 * spinSpeed, var14 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    RenderUtil.renderFive();
                    RenderUtil.setColor(Color.WHITE);
                }
            }
            GL11.glPopMatrix();
        }
    }
    
    static {
        RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    }
}
