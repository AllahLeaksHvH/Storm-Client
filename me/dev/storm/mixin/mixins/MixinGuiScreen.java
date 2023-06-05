//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.dev.storm.features.modules.misc.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiScreen.class })
public class MixinGuiScreen extends Gui
{
    @Inject(method = { "renderToolTip" }, at = { @At("HEAD") }, cancellable = true)
    public void renderToolTipHook(final ItemStack stack, final int x, final int y, final CallbackInfo info) {
        if (ToolTips.getInstance().isOn() && stack.getItem() instanceof ItemShulkerBox) {
            ToolTips.getInstance().renderShulkerToolTip(stack, x, y, (String)null);
            info.cancel();
        }
    }
}
