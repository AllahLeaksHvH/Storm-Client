//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.client;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import java.util.concurrent.atomic.*;

public class Media extends Module
{
    public Setting<Boolean> getName;
    private final AtomicBoolean connected;
    private static Media instance;
    private StringBuffer name;
    
    public Media() {
        super("Media", "", Category.CLIENT, false, false, false);
        this.getName = (Setting<Boolean>)this.register(new Setting("GetName", (T)true));
        this.connected = new AtomicBoolean(false);
        this.name = null;
        Media.instance = this;
    }
    
    public static Media getInstance() {
        if (Media.instance == null) {
            Media.instance = new Media();
        }
        return Media.instance;
    }
    
    public String getPlayerName() {
        if (this.name == null) {
            return null;
        }
        return this.name.toString();
    }
    
    public boolean isConnected() {
        return this.connected.get();
    }
    
    @Override
    public void onTick() {
        if (Media.mc.getConnection() != null && this.isConnected() && this.getName.getValue()) {
            this.getName.setValue(false);
        }
    }
}
