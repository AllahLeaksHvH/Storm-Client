//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.util.throwables;

import org.spongepowered.asm.mixin.throwables.*;

public class LVTGeneratorException extends MixinException
{
    private static final long serialVersionUID = 1L;
    
    public LVTGeneratorException(final String message) {
        super(message);
    }
    
    public LVTGeneratorException(final String message, final Throwable cause) {
        super(message, cause);
    }
}