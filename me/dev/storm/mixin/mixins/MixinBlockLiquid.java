//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.block.properties.*;
import me.dev.storm.features.modules.player.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ BlockLiquid.class })
public class MixinBlockLiquid extends Block
{
    protected MixinBlockLiquid(final Material materialIn) {
        super(materialIn);
    }
    
    @Inject(method = { "canCollideCheck" }, at = { @At("HEAD") }, cancellable = true)
    public void canCollideCheckHook(final IBlockState blockState, final boolean hitIfLiquid, final CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue((Object)((hitIfLiquid && (int)blockState.getValue((IProperty)BlockLiquid.LEVEL) == 0) || LiquidInteract.getInstance().isOn()));
    }
}
