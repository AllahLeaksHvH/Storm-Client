//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.mixin;

import net.minecraftforge.fml.relauncher.*;
import me.dev.storm.*;
import org.spongepowered.asm.launch.*;
import org.spongepowered.asm.mixin.*;
import java.util.*;

public class StormLoader implements IFMLLoadingPlugin
{
    private static boolean isObfuscatedEnvironment;
    
    public StormLoader() {
        Storm.LOGGER.info("\n\nLoading mixins by storm");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.storm.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        Storm.LOGGER.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }
    
    public String[] getASMTransformerClass() {
        return new String[0];
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
        StormLoader.isObfuscatedEnvironment = data.get("runtimeDeobfuscationEnabled");
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
    
    static {
        StormLoader.isObfuscatedEnvironment = false;
    }
}
