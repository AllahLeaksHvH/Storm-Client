//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.movement;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class Clip extends Module
{
    public static Clip INSTANCE;
    private final Setting<Integer> timeout;
    private int packets;
    
    public static boolean isMoving() {
        return Clip.mc.gameSettings.keyBindForward.isKeyDown() || Clip.mc.gameSettings.keyBindBack.isKeyDown() || Clip.mc.gameSettings.keyBindLeft.isKeyDown() || Clip.mc.gameSettings.keyBindRight.isKeyDown();
    }
    
    public Clip() {
        super("Clip", "Clips into blocks nearby to prevent crystal damage.", Module.Category.MOVEMENT, true, false, false);
        this.timeout = (Setting<Integer>)this.register(new Setting("Timeout", (T)5, (T)1, (T)10));
        Clip.INSTANCE = this;
    }
    
    public void onDisable() {
        this.packets = 0;
    }
    
    public String getDisplayInfo() {
        return ChatFormatting.GRAY + "[" + ChatFormatting.RESET + ChatFormatting.WHITE + String.valueOf(this.packets).toLowerCase() + ChatFormatting.RESET + ChatFormatting.GRAY + "]";
    }
    
    public String onUpdate() {
        if (isMoving()) {
            this.toggle();
            return null;
        }
        if (Clip.mc.world.getCollisionBoxes((Entity)Clip.mc.player, Clip.mc.player.getEntityBoundingBox().grow(0.01, 0.0, 0.01)).size() < 2) {
            Clip.mc.player.setPosition(this.roundToClosest(Clip.mc.player.posX, Math.floor(Clip.mc.player.posX) + 0.301, Math.floor(Clip.mc.player.posX) + 0.699), Clip.mc.player.posY, this.roundToClosest(Clip.mc.player.posZ, Math.floor(Clip.mc.player.posZ) + 0.301, Math.floor(Clip.mc.player.posZ) + 0.699));
            this.packets = 0;
        }
        else if (Clip.mc.player.ticksExisted % this.timeout.getValue() == 0) {
            Clip.mc.player.setPosition(Clip.mc.player.posX + MathHelper.clamp(this.roundToClosest(Clip.mc.player.posX, Math.floor(Clip.mc.player.posX) + 0.241, Math.floor(Clip.mc.player.posX) + 0.759) - Clip.mc.player.posX, -0.03, 0.03), Clip.mc.player.posY, Clip.mc.player.posZ + MathHelper.clamp(this.roundToClosest(Clip.mc.player.posZ, Math.floor(Clip.mc.player.posZ) + 0.241, Math.floor(Clip.mc.player.posZ) + 0.759) - Clip.mc.player.posZ, -0.03, 0.03));
            Clip.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Clip.mc.player.posX, Clip.mc.player.posY, Clip.mc.player.posZ, true));
            Clip.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.roundToClosest(Clip.mc.player.posX, Math.floor(Clip.mc.player.posX) + 0.23, Math.floor(Clip.mc.player.posX) + 0.77), Clip.mc.player.posY, this.roundToClosest(Clip.mc.player.posZ, Math.floor(Clip.mc.player.posZ) + 0.23, Math.floor(Clip.mc.player.posZ) + 0.77), true));
            ++this.packets;
        }
        return null;
    }
    
    private double roundToClosest(final double num, final double low, final double high) {
        final double d1 = num - low;
        final double d2 = high - num;
        if (d2 > d1) {
            return low;
        }
        return high;
    }
}
