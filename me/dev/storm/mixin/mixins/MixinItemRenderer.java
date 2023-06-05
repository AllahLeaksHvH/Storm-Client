//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.mixin.mixins;

import net.minecraft.client.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.entity.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import me.dev.storm.features.modules.client.*;
import me.dev.storm.util.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.block.model.*;
import me.dev.storm.features.modules.render.*;

@Mixin({ ItemRenderer.class })
public abstract class MixinItemRenderer
{
    @Shadow
    @Final
    public Minecraft mc;
    private boolean injection;
    
    public MixinItemRenderer() {
        this.injection = true;
    }
    
    @Shadow
    public abstract void renderItemInFirstPerson(final AbstractClientPlayer p0, final float p1, final float p2, final EnumHand p3, final float p4, final ItemStack p5, final float p6);
    
    @Shadow
    protected abstract void renderArmFirstPerson(final float p0, final float p1, final EnumHandSide p2);
    
    @Inject(method = { "renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V" }, at = { @At("HEAD") }, cancellable = true)
    public void renderItemInFirstPersonHook(final AbstractClientPlayer player, final float p_187457_2_, final float p_187457_3_, final EnumHand hand, final float p_187457_5_, final ItemStack stack, final float p_187457_7_, final CallbackInfo info) {
        if (this.injection) {
            info.cancel();
            final SmallShield offset = SmallShield.getINSTANCE();
            float xOffset = 0.0f;
            float yOffset = 0.0f;
            this.injection = false;
            if (hand == EnumHand.MAIN_HAND) {
                if (offset.isOn()) {
                    xOffset = (float)offset.mainX.getValue();
                    yOffset = (float)offset.mainY.getValue();
                }
            }
            else if (offset.isOn()) {
                xOffset = (float)offset.offX.getValue();
                yOffset = (float)offset.offY.getValue();
            }
            if (HandChams.getINSTANCE().isOn() && hand == EnumHand.MAIN_HAND && stack.isEmpty()) {
                if (((HandChams.RenderMode)HandChams.getINSTANCE().mode.getValue()).equals((Object)HandChams.RenderMode.WIREFRAME)) {
                    this.renderItemInFirstPerson(player, p_187457_2_, p_187457_3_, hand, p_187457_5_ + xOffset, stack, p_187457_7_ + yOffset);
                }
                GlStateManager.pushMatrix();
                if (((HandChams.RenderMode)HandChams.getINSTANCE().mode.getValue()).equals((Object)HandChams.RenderMode.WIREFRAME)) {
                    GL11.glPushAttrib(1048575);
                }
                else {
                    GlStateManager.pushAttrib();
                }
                if (((HandChams.RenderMode)HandChams.getINSTANCE().mode.getValue()).equals((Object)HandChams.RenderMode.WIREFRAME)) {
                    GL11.glPolygonMode(1032, 6913);
                }
                GL11.glDisable(3553);
                GL11.glDisable(2896);
                if (((HandChams.RenderMode)HandChams.getINSTANCE().mode.getValue()).equals((Object)HandChams.RenderMode.WIREFRAME)) {
                    GL11.glEnable(2848);
                    GL11.glEnable(3042);
                }
                GL11.glColor4f(((boolean)ClickGui.getInstance().rainbow.getValue()) ? (ColorUtil.rainbow((int)ClickGui.getInstance().rainbowHue.getValue()).getRed() / 255.0f) : ((int)HandChams.getINSTANCE().red.getValue() / 255.0f), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? (ColorUtil.rainbow((int)ClickGui.getInstance().rainbowHue.getValue()).getGreen() / 255.0f) : ((int)HandChams.getINSTANCE().green.getValue() / 255.0f), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? (ColorUtil.rainbow((int)ClickGui.getInstance().rainbowHue.getValue()).getBlue() / 255.0f) : ((int)HandChams.getINSTANCE().blue.getValue() / 255.0f), (int)HandChams.getINSTANCE().alpha.getValue() / 255.0f);
                this.renderItemInFirstPerson(player, p_187457_2_, p_187457_3_, hand, p_187457_5_ + xOffset, stack, p_187457_7_ + yOffset);
                GlStateManager.popAttrib();
                GlStateManager.popMatrix();
            }
            if (SmallShield.getINSTANCE().isOn() && (!stack.isEmpty || HandChams.getINSTANCE().isOff())) {
                this.renderItemInFirstPerson(player, p_187457_2_, p_187457_3_, hand, p_187457_5_ + xOffset, stack, p_187457_7_ + yOffset);
            }
            else if (!stack.isEmpty || HandChams.getINSTANCE().isOff()) {
                this.renderItemInFirstPerson(player, p_187457_2_, p_187457_3_, hand, p_187457_5_, stack, p_187457_7_);
            }
            this.injection = true;
        }
    }
    
    @Inject(method = { "renderItemSide" }, at = { @At("HEAD") })
    public void renderItemSide(final EntityLivingBase entitylivingbaseIn, final ItemStack heldStack, final ItemCameraTransforms.TransformType transform, final boolean leftHanded, final CallbackInfo ci) {
        if (ViewModel.INSTANCE.isEnabled()) {
            GlStateManager.scale((int)ViewModel.INSTANCE.scaleX.getValue() / 100.0f, (int)ViewModel.INSTANCE.scaleY.getValue() / 100.0f, (int)ViewModel.INSTANCE.scaleZ.getValue() / 100.0f);
            if (transform == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
                GlStateManager.translate((int)ViewModel.INSTANCE.translateX.getValue() / 200.0f, (int)ViewModel.INSTANCE.translateY.getValue() / 200.0f, (int)ViewModel.INSTANCE.translateZ.getValue() / 200.0f);
                GlStateManager.rotate((float)(int)ViewModel.INSTANCE.rotateX.getValue(), 1.0f, 0.0f, 0.0f);
                GlStateManager.rotate((float)(int)ViewModel.INSTANCE.rotateY.getValue(), 0.0f, 1.0f, 0.0f);
                GlStateManager.rotate((float)(int)ViewModel.INSTANCE.rotateZ.getValue(), 0.0f, 0.0f, 1.0f);
                GlStateManager.color((float)(int)ViewModel.INSTANCE.alpha.getValue(), 0.0f, 0.0f, 1.0f);
            }
            else if (transform == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
                GlStateManager.translate(-(int)ViewModel.INSTANCE.translateX.getValue() / 200.0f, (int)ViewModel.INSTANCE.translateY.getValue() / 200.0f, (int)ViewModel.INSTANCE.translateZ.getValue() / 200.0f);
                GlStateManager.rotate((float)(-(int)ViewModel.INSTANCE.rotateX.getValue()), 1.0f, 0.0f, 0.0f);
                GlStateManager.rotate((float)(int)ViewModel.INSTANCE.rotateY.getValue(), 0.0f, 1.0f, 0.0f);
                GlStateManager.rotate((float)(int)ViewModel.INSTANCE.rotateZ.getValue(), 0.0f, 0.0f, 1.0f);
                GlStateManager.color((float)(int)ViewModel.INSTANCE.alpha.getValue(), 0.0f, 0.0f, 1.0f);
            }
        }
    }
}
