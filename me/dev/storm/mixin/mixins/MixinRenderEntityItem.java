//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.mixin.mixins;

import net.minecraft.entity.item.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.*;
import java.util.*;
import net.minecraft.util.*;
import me.dev.storm.features.modules.render.*;
import net.minecraft.client.renderer.block.model.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.util.math.*;
import net.minecraft.block.material.*;
import org.lwjgl.opengl.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import net.minecraftforge.client.*;

@Mixin({ RenderEntityItem.class })
public abstract class MixinRenderEntityItem extends MixinRenderer<EntityItem>
{
    private final Minecraft mc;
    @Shadow
    @Final
    private RenderItem itemRenderer;
    @Shadow
    @Final
    private Random random;
    private long tick;
    
    public MixinRenderEntityItem() {
        this.mc = Minecraft.getMinecraft();
    }
    
    @Shadow
    protected abstract int getModelCount(final ItemStack p0);
    
    @Shadow
    public abstract boolean shouldSpreadItems();
    
    @Shadow
    public abstract boolean shouldBob();
    
    @Shadow
    protected abstract ResourceLocation getEntityTexture(final EntityItem p0);
    
    private double formPositive(final float rotationPitch) {
        return (rotationPitch > 0.0f) ? rotationPitch : ((double)(-rotationPitch));
    }
    
    @Overwrite
    private int transformModelCount(final EntityItem itemIn, final double x, final double y, final double z, final float p_177077_8_, final IBakedModel p_177077_9_) {
        if (ItemPhysics.INSTANCE.isEnabled()) {
            final ItemStack itemstack = itemIn.getItem();
            itemstack.getItem();
            final boolean flag = p_177077_9_.isAmbientOcclusion();
            final int i = this.getModelCount(itemstack);
            final float f2 = 0.0f;
            GlStateManager.translate((float)x, (float)y + 0.0f + 0.1f, (float)z);
            float f3 = 0.0f;
            if (flag || (this.mc.getRenderManager().options != null && this.mc.getRenderManager().options.fancyGraphics)) {
                GlStateManager.rotate(f3, 0.0f, 1.0f, 0.0f);
            }
            if (!flag) {
                f3 = -0.0f * (i - 1) * 0.5f;
                final float f4 = -0.0f * (i - 1) * 0.5f;
                final float f5 = -0.046875f * (i - 1) * 0.5f;
                GlStateManager.translate(f3, f4, f5);
            }
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            return i;
        }
        final ItemStack itemstack = itemIn.getItem();
        itemstack.getItem();
        final boolean flag = p_177077_9_.isGui3d();
        final int i = this.getModelCount(itemstack);
        final float f6 = this.shouldBob() ? (MathHelper.sin((itemIn.getAge() + p_177077_8_) / 10.0f + itemIn.hoverStart) * 0.1f + 0.1f) : 0.0f;
        final float f7 = p_177077_9_.getItemCameraTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.y;
        GlStateManager.translate((float)x, (float)y + f6 + 0.25f * f7, (float)z);
        if (flag || this.renderManager.options != null) {
            final float f8 = ((itemIn.getAge() + p_177077_8_) / 20.0f + itemIn.hoverStart) * 57.295776f;
            GlStateManager.rotate(f8, 0.0f, 1.0f, 0.0f);
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        return i;
    }
    
    @Overwrite
    public void doRender(final EntityItem entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
        if (ItemPhysics.INSTANCE.isEnabled()) {
            double rotation = (System.nanoTime() - this.tick) / 3000000.0;
            if (!this.mc.inGameHasFocus) {
                rotation = 0.0;
            }
            final ItemStack itemstack = entity.getItem();
            itemstack.getItem();
            this.random.setSeed(187L);
            this.mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            this.mc.getRenderManager().renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(516, 0.1f);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.pushMatrix();
            final IBakedModel ibakedmodel = this.itemRenderer.getItemModelMesher().getItemModel(itemstack);
            final int i = this.transformModelCount(entity, x, y, z, partialTicks, ibakedmodel);
            final BlockPos blockpos = new BlockPos((Entity)entity);
            if (entity.rotationPitch > 360.0f) {
                entity.rotationPitch = 0.0f;
            }
            if (!Double.isNaN(entity.posX) && !Double.isNaN(entity.posY) && !Double.isNaN(entity.posZ) && entity.world != null) {
                if (entity.onGround) {
                    if (entity.rotationPitch != 0.0f && entity.rotationPitch != 90.0f && entity.rotationPitch != 180.0f && entity.rotationPitch != 270.0f) {
                        final double d0 = this.formPositive(entity.rotationPitch);
                        final double d2 = this.formPositive(entity.rotationPitch - 90.0f);
                        final double d3 = this.formPositive(entity.rotationPitch - 180.0f);
                        final double d4 = this.formPositive(entity.rotationPitch - 270.0f);
                        if (d0 <= d2 && d0 <= d3 && d0 <= d4) {
                            entity.rotationPitch = ((entity.rotationPitch < 0.0f) ? (entity.rotationPitch += (float)rotation) : (entity.rotationPitch -= (float)rotation));
                        }
                        if (d2 < d0 && d2 <= d3 && d2 <= d4) {
                            entity.rotationPitch = ((entity.rotationPitch - 90.0f < 0.0f) ? (entity.rotationPitch += (float)rotation) : (entity.rotationPitch -= (float)rotation));
                        }
                        if (d3 < d2 && d3 < d0 && d3 <= d4) {
                            entity.rotationPitch = ((entity.rotationPitch - 180.0f < 0.0f) ? (entity.rotationPitch += (float)rotation) : (entity.rotationPitch -= (float)rotation));
                        }
                        if (d4 < d2 && d4 < d3 && d4 < d0) {
                            entity.rotationPitch = ((entity.rotationPitch - 270.0f < 0.0f) ? (entity.rotationPitch += (float)rotation) : (entity.rotationPitch -= (float)rotation));
                        }
                    }
                }
                else {
                    final BlockPos blockpos2 = new BlockPos((Entity)entity);
                    blockpos2.add(0, 1, 0);
                    final Material material = entity.world.getBlockState(blockpos2).getMaterial();
                    final Material material2 = entity.world.getBlockState(blockpos).getMaterial();
                    final boolean flag2 = entity.isInsideOfMaterial(Material.WATER);
                    final boolean flag3 = entity.isInWater();
                    entity.rotationPitch = ((flag2 | material == Material.WATER | material2 == Material.WATER | flag3) ? (entity.rotationPitch += (float)(rotation / 4.0)) : (entity.rotationPitch += (float)(rotation * 2.0)));
                }
            }
            GL11.glRotatef(entity.rotationYaw, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(entity.rotationPitch + 90.0f, 1.0f, 0.0f, 0.0f);
            for (int j = 0; j < i; ++j) {
                if (ibakedmodel.isAmbientOcclusion()) {
                    GlStateManager.pushMatrix();
                    GlStateManager.scale((float)ItemPhysics.INSTANCE.Scaling.getValue(), (float)ItemPhysics.INSTANCE.Scaling.getValue(), (float)ItemPhysics.INSTANCE.Scaling.getValue());
                    this.itemRenderer.renderItem(itemstack, ibakedmodel);
                    GlStateManager.popMatrix();
                }
                else {
                    GlStateManager.pushMatrix();
                    if (j > 0 && this.shouldSpreadItems()) {
                        GlStateManager.translate(0.0f, 0.0f, 0.046875f * j);
                    }
                    this.itemRenderer.renderItem(itemstack, ibakedmodel);
                    if (!this.shouldSpreadItems()) {
                        GlStateManager.translate(0.0f, 0.0f, 0.046875f);
                    }
                    GlStateManager.popMatrix();
                }
            }
            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
            this.mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            this.mc.getRenderManager().renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
            return;
        }
        final ItemStack itemstack2 = entity.getItem();
        final int k = itemstack2.isEmpty() ? 187 : (Item.getIdFromItem(itemstack2.getItem()) + itemstack2.getMetadata());
        this.random.setSeed(k);
        boolean flag4 = false;
        if (this.bindEntityTexture(entity)) {
            this.renderManager.renderEngine.getTexture(this.getEntityTexture(entity)).setBlurMipmap(false, false);
            flag4 = true;
        }
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(516, 0.1f);
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.pushMatrix();
        final IBakedModel ibakedmodel = this.itemRenderer.getItemModelWithOverrides(itemstack2, entity.world, (EntityLivingBase)null);
        final int l = this.transformModelCount(entity, x, y, z, partialTicks, ibakedmodel);
        final boolean flag5 = ibakedmodel.isGui3d();
        if (!flag5) {
            final float f3 = -0.0f * (l - 1) * 0.5f;
            final float f4 = -0.0f * (l - 1) * 0.5f;
            final float f5 = -0.09375f * (l - 1) * 0.5f;
            GlStateManager.translate(f3, f4, f5);
        }
        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }
        for (int m = 0; m < l; ++m) {
            GlStateManager.pushMatrix();
            if (flag5) {
                if (m > 0) {
                    final float f6 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    final float f7 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    final float f8 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    GlStateManager.translate(this.shouldSpreadItems() ? f6 : 0.0f, this.shouldSpreadItems() ? f7 : 0.0f, f8);
                }
                final IBakedModel transformedModel = ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.GROUND, false);
                this.itemRenderer.renderItem(itemstack2, transformedModel);
                GlStateManager.popMatrix();
            }
            else {
                if (m > 0) {
                    final float f9 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f * 0.5f;
                    final float f10 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f * 0.5f;
                    GlStateManager.translate(f9, f10, 0.0f);
                }
                final IBakedModel transformedModel = ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.GROUND, false);
                this.itemRenderer.renderItem(itemstack2, transformedModel);
                GlStateManager.popMatrix();
                GlStateManager.translate(0.0f, 0.0f, 0.09375f);
            }
        }
        if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        this.bindEntityTexture(entity);
        if (flag4) {
            this.renderManager.renderEngine.getTexture(this.getEntityTexture(entity)).restoreLastBlurMipmap();
        }
    }
}
