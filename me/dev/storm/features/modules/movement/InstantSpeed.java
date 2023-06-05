//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.movement;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import me.dev.storm.event.events.trollgod.*;
import me.dev.storm.util.trollhack.*;
import me.dev.storm.util.*;
import net.minecraftforge.fml.common.*;

public class InstantSpeed extends Module
{
    Setting<Boolean> noLiquid;
    
    public InstantSpeed() {
        super("InstantSpeed", "Trollgod speed!!!!", Module.Category.MOVEMENT, false, false, false);
        this.noLiquid = (Setting<Boolean>)this.register(new Setting("NoLiquid", (T)true));
    }
    
    public void onToggle() {
        if (fullNullCheck()) {
            return;
        }
    }
    
    @Mod.EventHandler
    public void onMove(final MoveEvent e) {
        if (this.isDisabled() || InstantSpeed.mc.player.isElytraFlying()) {
            return;
        }
        if ((this.noLiquid.getValue() && EntityUtil.isInLiquid()) || InstantSpeed.mc.player.capabilities.isFlying) {
            return;
        }
        MovementUtil.strafe(e, MovementUtil.getSpeed());
    }
    
    public String getDisplayInfo() {
        return "NoLiquid";
    }
}
