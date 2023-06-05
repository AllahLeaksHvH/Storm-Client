//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.misc;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import me.dev.storm.*;

public class RPC extends Module
{
    public static RPC INSTANCE;
    public Setting<String> state;
    public Setting<Boolean> showIP;
    public Setting<Boolean> catMode;
    
    public RPC() {
        super("RPC", "Discord rich presence", Category.MISC, false, false, false);
        this.state = (Setting<String>)this.register(new Setting("State", (T)"Storm 0.3", "Sets the state of the DiscordRPC."));
        this.showIP = (Setting<Boolean>)this.register(new Setting("ShowIP", (T)Boolean.TRUE, "Shows the server IP in your discord presence."));
        this.catMode = (Setting<Boolean>)this.register(new Setting("SnineMode", (T)false, "cute cat supremacy"));
        RPC.INSTANCE = this;
    }
    
    @Override
    public void onEnable() {
        DiscordPresence.start();
    }
    
    @Override
    public void onDisable() {
        DiscordPresence.stop();
    }
}
