//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.movement;

import me.dev.storm.features.modules.*;
import net.minecraft.client.settings.*;

public class AutoWalk extends Module
{
    public AutoWalk() {
        super("AutoWalk", "Automatically walks in a straight line", Module.Category.MOVEMENT, true, false, false);
    }
    
    public String onUpdate() {
        KeyBinding.setKeyBindState(AutoWalk.mc.gameSettings.keyBindForward.getKeyCode(), true);
        return null;
    }
    
    public void onDisable() {
        KeyBinding.setKeyBindState(AutoWalk.mc.gameSettings.keyBindForward.getKeyCode(), false);
    }
}
