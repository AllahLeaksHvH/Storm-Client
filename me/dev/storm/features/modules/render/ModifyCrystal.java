//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.render;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import me.dev.storm.*;

public class ModifyCrystal extends Module
{
    public static Setting<Float> spin;
    public static Setting<Float> bounce;
    public static Setting<Float> scale;
    
    public ModifyCrystal() {
        super("Modify Crystal", "STOP DMING ME AAAAAAAAAAA", Module.Category.RENDER, true, false, false);
        this.register((Setting)ModifyCrystal.spin);
        this.register((Setting)ModifyCrystal.scale);
        this.register((Setting)ModifyCrystal.bounce);
    }
    
    public static float[] getSpeed() {
        return Storm.moduleManager.isModuleEnabled("Modify Crystal") ? new float[] { ModifyCrystal.spin.getValue(), ModifyCrystal.bounce.getValue() } : new float[] { 1.0f, 1.0f };
    }
    
    static {
        ModifyCrystal.spin = new Setting<Float>("Spin", 1.0f, 0.0f, 10.0f);
        ModifyCrystal.bounce = new Setting<Float>("Bounce", 1.0f, 0.0f, 10.0f);
        ModifyCrystal.scale = new Setting<Float>("Scale", 1.0f, 0.0f, 1.0f);
    }
}
