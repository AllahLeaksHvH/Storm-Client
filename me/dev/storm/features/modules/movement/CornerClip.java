//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.movement;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import me.dev.storm.util.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;

public class CornerClip extends Module
{
    public final Setting<Mode> mode;
    public final Setting<VMode> vmode;
    public final Setting<HMode> hmode;
    public final Setting<Boolean> disables;
    public final Setting<Integer> CornerClipAmount;
    public final Setting<Boolean> autoAdjust;
    public final Setting<Double> offset;
    boolean isSneaking;
    
    public CornerClip() {
        super("Clip", "Automatically CornerClips you into blocks", Module.Category.MOVEMENT, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("CornerClip Mode", (T)Mode.Vertical));
        this.vmode = (Setting<VMode>)this.register(new Setting("V Mode", (T)VMode.Bypass, v -> this.mode.getValue() == Mode.Vertical));
        this.hmode = (Setting<HMode>)this.register(new Setting("H Mode", (T)HMode.Normal, v -> this.mode.getValue() == Mode.Horizontal));
        this.disables = (Setting<Boolean>)this.register(new Setting("Disables", (T)true));
        this.CornerClipAmount = (Setting<Integer>)this.register(new Setting("CornerClip Amount", (T)(-3), (T)(-50), (T)50));
        this.autoAdjust = (Setting<Boolean>)this.register(new Setting("Auto Adjust", (T)true));
        this.offset = (Setting<Double>)this.register(new Setting("Offset", (T)(-4.0), (T)(-70.0), (T)70.0));
    }
    
    public String onUpdate() {
        if (nullCheck()) {
            return null;
        }
        final Vec3d dir = MathUtil.direction(CornerClip.mc.player.rotationYaw);
        if (this.mode.getValue() == Mode.Vertical && this.vmode.getValue() == VMode.Normal) {
            CornerClip.mc.player.setPosition(CornerClip.mc.player.posX, CornerClip.mc.player.posY + this.CornerClipAmount.getValue(), CornerClip.mc.player.posZ);
            if (this.disables.getValue()) {
                this.disable();
            }
        }
        if (this.mode.getValue() == Mode.Horizontal && this.hmode.getValue() == HMode.Normal) {
            CornerClip.mc.player.setPosition(CornerClip.mc.player.posX + dir.x * this.CornerClipAmount.getValue(), CornerClip.mc.player.posY, CornerClip.mc.player.posZ + dir.z * this.CornerClipAmount.getValue());
            if (this.disables.getValue()) {
                this.disable();
            }
        }
        if (this.mode.getValue() == Mode.Vertical && this.vmode.getValue() == VMode.Bypass) {
            if (this.autoAdjust.getValue()) {
                this.offset.setValue(-CornerClip.mc.player.posY - 2.0);
            }
            if (this.isSneaking) {
                CornerClip.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)CornerClip.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                this.isSneaking = false;
            }
            CornerClip.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(CornerClip.mc.player.posX, CornerClip.mc.player.posY + this.offset.getValue(), CornerClip.mc.player.posZ, false));
            CornerClip.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(CornerClip.mc.player.posX, CornerClip.mc.player.posY + 0.41999998688698, CornerClip.mc.player.posZ, true));
            CornerClip.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(CornerClip.mc.player.posX, CornerClip.mc.player.posY + 0.7531999805211997, CornerClip.mc.player.posZ, true));
            CornerClip.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(CornerClip.mc.player.posX, CornerClip.mc.player.posY + 1.00133597911214, CornerClip.mc.player.posZ, true));
            CornerClip.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(CornerClip.mc.player.posX, CornerClip.mc.player.posY + 1.16610926093821, CornerClip.mc.player.posZ, true));
            CornerClip.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)CornerClip.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            CornerClip.mc.player.setSneaking(false);
        }
        return null;
    }
    
    public void onDisable() {
        if (CornerClip.mc.player == null) {
            return;
        }
        if (this.isSneaking) {
            CornerClip.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)CornerClip.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.isSneaking = false;
        }
    }
    
    public void onEnable() {
        if (CornerClip.mc.player == null) {
            return;
        }
        if (this.mode.getValue() == Mode.Vertical && this.vmode.getValue() == VMode.Bypass) {
            CornerClip.mc.player.jump();
        }
    }
    
    public enum HMode
    {
        Normal;
    }
    
    public enum VMode
    {
        Normal, 
        Bypass;
    }
    
    public enum Mode
    {
        Vertical, 
        Horizontal;
    }
}
