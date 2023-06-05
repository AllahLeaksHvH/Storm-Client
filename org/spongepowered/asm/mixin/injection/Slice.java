//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface Slice {
    String id() default "";
    
    At from() default @At("HEAD");
    
    At to() default @At("TAIL");
}
