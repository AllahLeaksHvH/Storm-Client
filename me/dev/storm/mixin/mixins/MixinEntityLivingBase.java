//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.dev.storm.features.modules.render.*;
import me.dev.storm.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ EntityLivingBase.class })
public class MixinEntityLivingBase
{
    @Inject(method = { "getArmSwingAnimationEnd" }, at = { @At("HEAD") }, cancellable = true)
    public void getArmSwingAnimationEndHook(final CallbackInfoReturnable<Integer> cir) {
        final int stuff = (int)(((SwingAnimation)Storm.moduleManager.getModuleByClass((Class)SwingAnimation.class)).isEnabled() ? SwingAnimation.changeSwing.getValue() : 6);
        cir.setReturnValue((Object)stuff);
    }
}
