//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.player;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;

public class TpsSync extends Module
{
    private static TpsSync INSTANCE;
    public Setting<Boolean> attack;
    public Setting<Boolean> mining;
    
    public TpsSync() {
        super("TpsSync", "Syncs your client with the TPS.", Module.Category.PLAYER, true, false, false);
        this.attack = (Setting<Boolean>)this.register(new Setting("Attack", (T)Boolean.FALSE));
        this.mining = (Setting<Boolean>)this.register(new Setting("Mine", (T)Boolean.TRUE));
        this.setInstance();
    }
    
    public static TpsSync getInstance() {
        if (TpsSync.INSTANCE == null) {
            TpsSync.INSTANCE = new TpsSync();
        }
        return TpsSync.INSTANCE;
    }
    
    private void setInstance() {
        TpsSync.INSTANCE = this;
    }
    
    static {
        TpsSync.INSTANCE = new TpsSync();
    }
}
