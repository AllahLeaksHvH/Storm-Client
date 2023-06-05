//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import me.dev.storm.features.modules.render.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ModelPlayer.class })
public class MixinModelPlayer
{
    @Inject(method = { "setRotationAngles" }, at = { @At("RETURN") })
    public void setRotationAngles(final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor, final Entity entityIn, final CallbackInfo callbackInfo) {
        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null && entityIn instanceof EntityPlayer) {
            Skeleton.addEntity((EntityPlayer)entityIn, (ModelPlayer)ModelPlayer.class.cast(this));
        }
    }
}
