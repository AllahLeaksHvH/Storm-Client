//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.movement;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import me.dev.storm.util.*;
import net.minecraft.entity.*;
import me.dev.storm.event.events.*;
import net.minecraft.network.play.client.*;

public class TickShift extends Module
{
    Setting<Integer> ticksVal;
    Setting<Float> timer;
    boolean canTimer;
    int tick;
    
    public TickShift() {
        super("TickShift", "funny exploit on top", Module.Category.MOVEMENT, true, false, false);
        this.ticksVal = (Setting<Integer>)this.register(new Setting("Ticks", (T)18, (T)1, (T)100));
        this.timer = (Setting<Float>)this.register(new Setting("Timer", (T)1.8f, (T)1.0f, (T)3.0f));
        this.canTimer = false;
        this.tick = 0;
    }
    
    public void onEnable() {
        this.canTimer = false;
        this.tick = 0;
    }
    
    public String onUpdate() {
        if (this.tick <= 0) {
            this.tick = 0;
            this.canTimer = false;
            TickShift.mc.timer.tickLength = 50.0f;
        }
        if (this.tick > 0 && EntityUtil.isEntityMoving((Entity)TickShift.mc.player)) {
            --this.tick;
            TickShift.mc.timer.tickLength = 50.0f / this.timer.getValue();
        }
        if (!EntityUtil.isEntityMoving((Entity)TickShift.mc.player)) {
            ++this.tick;
        }
        if (this.tick >= this.ticksVal.getValue()) {
            this.tick = this.ticksVal.getValue();
        }
        return null;
    }
    
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketPlayer) {
            --this.tick;
            if (this.tick <= 0) {
                this.tick = 0;
            }
        }
    }
    
    public String getDisplayInfo() {
        return String.valueOf(this.tick);
    }
    
    public void onDisable() {
        TickShift.mc.timer.tickLength = 50.0f;
    }
}
