//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.dev.storm.features.modules.render.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import me.dev.storm.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import me.dev.storm.manager.*;
import java.awt.*;
import me.dev.storm.util.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderLivingBase.class })
public abstract class MixinRenderLivingBase<T extends EntityLivingBase> extends Render<T>
{
    protected MixinRenderLivingBase(final RenderManager renderManager) {
        super(renderManager);
    }
    
    @Inject(method = { "renderModel" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V", shift = At.Shift.BEFORE) }, cancellable = true)
    private void renderModelHook(final T entity, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale, final CallbackInfo info) {
        final Chams mod = Chams.INSTANCE;
        final Minecraft mc = Minecraft.getMinecraft();
        final RenderLivingBase<?> renderLiving = (RenderLivingBase<?>)RenderLivingBase.class.cast(this);
        final ModelBase model = renderLiving.getMainModel();
        if (mod.isOn() && entity instanceof EntityPlayer) {
            info.cancel();
            final boolean isFriend = Storm.friendManager.isFriend(entity.getName());
            final float newLimbSwing = mod.noInterp.getValue() ? 0.0f : limbSwing;
            final float newLimbSwingAmount = mod.noInterp.getValue() ? 0.0f : limbSwingAmount;
            if (!(boolean)mod.self.getValue() && entity instanceof EntityPlayerSP) {
                model.render((Entity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                return;
            }
            if (mod.sneak.getValue()) {
                entity.setSneaking(true);
            }
            if (mod.model.getValue()) {
                model.render((Entity)entity, newLimbSwing, newLimbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            }
            else if (mod.xqz.getValue()) {
                GL11.glEnable(32823);
                GlStateManager.enablePolygonOffset();
                GL11.glPolygonOffset(1.0f, -1000000.0f);
                if (mod.modelColor.booleanValue) {
                    final ColorManager colorManager = Storm.colorManager;
                    final Color rainbow = ColorManager.getRainbow();
                    final Color color = isFriend ? Storm.colorManager.getFriendColor(((Color)mod.modelColor.getValue()).getAlpha()) : (mod.rainbow.getValue() ? new Color(rainbow.getRed(), rainbow.getGreen(), rainbow.getBlue(), ((Color)mod.modelColor.getValue()).getAlpha()) : new Color(((Color)mod.modelColor.getValue()).getRed(), ((Color)mod.modelColor.getValue()).getGreen(), ((Color)mod.modelColor.getValue()).getBlue(), ((Color)mod.modelColor.getValue()).getAlpha()));
                    RenderUtil.glColor(color);
                }
                model.render((Entity)entity, newLimbSwing, newLimbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                GL11.glDisable(32823);
                GlStateManager.disablePolygonOffset();
                GL11.glPolygonOffset(1.0f, 1000000.0f);
            }
            if (mod.wireframe.getValue()) {
                final ColorManager colorManager2 = Storm.colorManager;
                final Color rainbow = ColorManager.getRainbow();
                final Color color = isFriend ? Storm.colorManager.getFriendColor(mod.lineColor.booleanValue ? ((Color)mod.lineColor.getValue()).getAlpha() : ((Color)mod.color.getValue()).getAlpha()) : (mod.rainbow.getValue() ? new Color(rainbow.getRed(), rainbow.getGreen(), rainbow.getBlue(), ((Color)mod.color.getValue()).getAlpha()) : (mod.lineColor.booleanValue ? new Color(((Color)mod.lineColor.getValue()).getRed(), ((Color)mod.lineColor.getValue()).getGreen(), ((Color)mod.lineColor.getValue()).getBlue(), ((Color)mod.lineColor.getValue()).getAlpha()) : new Color(((Color)mod.color.getValue()).getRed(), ((Color)mod.color.getValue()).getGreen(), ((Color)mod.color.getValue()).getBlue(), ((Color)mod.color.getValue()).getAlpha())));
                GL11.glPushMatrix();
                GL11.glPushAttrib(1048575);
                GL11.glPolygonMode(1032, 6913);
                GL11.glDisable(3553);
                GL11.glDisable(2896);
                GL11.glDisable(2929);
                GL11.glEnable(2848);
                GL11.glEnable(3042);
                GlStateManager.blendFunc(770, 771);
                RenderUtil.glColor(color);
                GlStateManager.glLineWidth((float)mod.lineWidth.getValue());
                model.render((Entity)entity, newLimbSwing, newLimbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                GL11.glPopAttrib();
                GL11.glPopMatrix();
            }
            if (mod.fill.getValue()) {
                final ColorManager colorManager3 = Storm.colorManager;
                final Color rainbow = ColorManager.getRainbow();
                final Color color = isFriend ? Storm.colorManager.getFriendColor(((Color)mod.color.getValue()).getAlpha()) : (mod.rainbow.getValue() ? new Color(rainbow.getRed(), rainbow.getGreen(), rainbow.getBlue(), ((Color)mod.color.getValue()).getAlpha()) : new Color(((Color)mod.color.getValue()).getRed(), ((Color)mod.color.getValue()).getGreen(), ((Color)mod.color.getValue()).getBlue(), ((Color)mod.color.getValue()).getAlpha()));
                GL11.glPushAttrib(1048575);
                GL11.glDisable(3008);
                GL11.glDisable(3553);
                GL11.glDisable(2896);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glLineWidth(1.5f);
                GL11.glEnable(2960);
                if (mod.xqz.getValue()) {
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                }
                GL11.glEnable(10754);
                RenderUtil.glColor(color);
                model.render((Entity)entity, newLimbSwing, newLimbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                if (mod.xqz.getValue()) {
                    GL11.glEnable(2929);
                    GL11.glDepthMask(true);
                }
                GL11.glEnable(3042);
                GL11.glEnable(2896);
                GL11.glEnable(3553);
                GL11.glEnable(3008);
                GL11.glPopAttrib();
            }
            if (mod.glint.getValue()) {
                final ColorManager colorManager4 = Storm.colorManager;
                final Color rainbow = ColorManager.getRainbow();
                final Color color = isFriend ? Storm.colorManager.getFriendColor(((Color)mod.color.getValue()).getAlpha()) : (mod.rainbow.getValue() ? new Color(rainbow.getRed(), rainbow.getGreen(), rainbow.getBlue(), ((Color)mod.color.getValue()).getAlpha()) : new Color(((Color)mod.color.getValue()).getRed(), ((Color)mod.color.getValue()).getGreen(), ((Color)mod.color.getValue()).getBlue(), ((Color)mod.color.getValue()).getAlpha()));
                GL11.glPushMatrix();
                GL11.glPushAttrib(1048575);
                GL11.glPolygonMode(1032, 6914);
                GL11.glDisable(2896);
                GL11.glDepthRange(0.0, 0.1);
                GL11.glEnable(3042);
                RenderUtil.glColor(color);
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
                final float f = entity.ticksExisted + mc.getRenderPartialTicks();
                mc.getRenderManager().renderEngine.bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
                for (int i = 0; i < 2; ++i) {
                    GlStateManager.matrixMode(5890);
                    GlStateManager.loadIdentity();
                    GL11.glScalef(1.0f, 1.0f, 1.0f);
                    GlStateManager.rotate(30.0f - i * 60.0f, 0.0f, 0.0f, 1.0f);
                    GlStateManager.translate(0.0f, f * (0.001f + i * 0.003f) * 20.0f, 0.0f);
                    GlStateManager.matrixMode(5888);
                    model.render((Entity)entity, newLimbSwing, newLimbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                }
                GlStateManager.matrixMode(5890);
                GlStateManager.loadIdentity();
                GlStateManager.matrixMode(5888);
                GL11.glDisable(3042);
                GL11.glDepthRange(0.0, 1.0);
                GL11.glEnable(2896);
                GL11.glPopAttrib();
                GL11.glPopMatrix();
            }
        }
        else {
            model.render((Entity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }
}
