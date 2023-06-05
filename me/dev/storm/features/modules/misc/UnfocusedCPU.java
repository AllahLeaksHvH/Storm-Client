//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.misc;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;

public class UnfocusedCPU extends Module
{
    public static UnfocusedCPU INSTANCE;
    public Setting<Integer> unfocusedFps;
    
    public UnfocusedCPU() {
        super("UnfocusedCPU", "Decreases your framerate when minecraft is unfocused.", Category.MISC, false, false, false);
        this.unfocusedFps = (Setting<Integer>)this.register(new Setting("UnfocusedFPS", (T)5, (T)1, (T)30));
        UnfocusedCPU.INSTANCE = this;
    }
}
