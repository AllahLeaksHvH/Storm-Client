//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer.throwables;

import org.spongepowered.asm.mixin.throwables.*;
import org.spongepowered.asm.mixin.extensibility.*;

public class MixinReloadException extends MixinException
{
    private static final long serialVersionUID = 2L;
    private final IMixinInfo mixinInfo;
    
    public MixinReloadException(final IMixinInfo mixinInfo, final String message) {
        super(message);
        this.mixinInfo = mixinInfo;
    }
    
    public IMixinInfo getMixinInfo() {
        return this.mixinInfo;
    }
}
