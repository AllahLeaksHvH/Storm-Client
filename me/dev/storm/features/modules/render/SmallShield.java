//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.render;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;

public class SmallShield extends Module
{
    private static SmallShield INSTANCE;
    public Setting<Float> offX;
    public Setting<Float> offY;
    public Setting<Float> mainX;
    public Setting<Float> mainY;
    
    public SmallShield() {
        super("SmallShield", "Makes you offhand lower.", Module.Category.RENDER, false, false, false);
        this.offX = (Setting<Float>)this.register(new Setting("OffHandX", (T)0.0f, (T)(-1.0f), (T)1.0f));
        this.offY = (Setting<Float>)this.register(new Setting("OffHandY", (T)0.0f, (T)(-1.0f), (T)1.0f));
        this.mainX = (Setting<Float>)this.register(new Setting("MainHandX", (T)0.0f, (T)(-1.0f), (T)1.0f));
        this.mainY = (Setting<Float>)this.register(new Setting("MainHandY", (T)0.0f, (T)(-1.0f), (T)1.0f));
        this.setInstance();
    }
    
    public static SmallShield getINSTANCE() {
        if (SmallShield.INSTANCE == null) {
            SmallShield.INSTANCE = new SmallShield();
        }
        return SmallShield.INSTANCE;
    }
    
    private void setInstance() {
        SmallShield.INSTANCE = this;
    }
    
    static {
        SmallShield.INSTANCE = new SmallShield();
    }
}
