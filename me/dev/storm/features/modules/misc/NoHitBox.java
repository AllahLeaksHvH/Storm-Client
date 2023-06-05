//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.misc;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;

public class NoHitBox extends Module
{
    private static NoHitBox INSTANCE;
    public Setting<Boolean> pickaxe;
    public Setting<Boolean> crystal;
    public Setting<Boolean> gapple;
    
    public NoHitBox() {
        super("NoHitBox", "NoHitBox.", Category.MISC, false, false, false);
        this.pickaxe = (Setting<Boolean>)this.register(new Setting("Pickaxe", (T)true));
        this.crystal = (Setting<Boolean>)this.register(new Setting("Crystal", (T)true));
        this.gapple = (Setting<Boolean>)this.register(new Setting("Gapple", (T)true));
        this.setInstance();
    }
    
    public static NoHitBox getINSTANCE() {
        if (NoHitBox.INSTANCE == null) {
            NoHitBox.INSTANCE = new NoHitBox();
        }
        return NoHitBox.INSTANCE;
    }
    
    private void setInstance() {
        NoHitBox.INSTANCE = this;
    }
    
    static {
        NoHitBox.INSTANCE = new NoHitBox();
    }
}
