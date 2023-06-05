//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.util.trollhack;

import java.lang.annotation.*;
import me.dev.storm.features.modules.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ModuleManifest {
    String label() default "";
    
    Module.Category category();
    
    int key() default 0;
    
    boolean persistent() default false;
    
    boolean enabled() default false;
    
    boolean listen() default true;
}
