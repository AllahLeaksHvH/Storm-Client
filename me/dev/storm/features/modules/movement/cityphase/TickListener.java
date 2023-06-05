//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.movement.cityphase;

import me.dev.storm.event.*;
import me.dev.storm.event.events.*;
import me.dev.storm.features.modules.movement.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class TickListener extends EventListener<TickEvent, CityPhase>
{
    public TickListener(final CityPhase cityPhase) {
        super((Class)TickEvent.class, (Object)cityPhase);
    }
    
    public void invoke(final Object object) {
        if (((CityPhase)this.module).movingByKeys()) {
            ((CityPhase)this.module).toggle();
            return;
        }
        if (this.mc.world.getCollisionBoxes((Entity)this.mc.player, this.mc.player.getEntityBoundingBox().grow(0.01, 0.0, 0.01)).size() < 2) {
            this.mc.player.setPosition(((CityPhase)this.module).roundToClosest(this.mc.player.posX, Math.floor(this.mc.player.posX) + 0.301, Math.floor(this.mc.player.posX) + 0.699), this.mc.player.posY, ((CityPhase)this.module).roundToClosest(this.mc.player.posZ, Math.floor(this.mc.player.posZ) + 0.301, Math.floor(this.mc.player.posZ) + 0.699));
        }
        else if (this.mc.player.ticksExisted % ((CityPhase)this.module).timeout.getValue() == 0.0f) {
            this.mc.player.setPosition(this.mc.player.posX + MathHelper.clamp(((CityPhase)this.module).roundToClosest(this.mc.player.posX, Math.floor(this.mc.player.posX) + 0.241, Math.floor(this.mc.player.posX) + 0.759) - this.mc.player.posX, -0.03, 0.03), this.mc.player.posY, this.mc.player.posZ + MathHelper.clamp(((CityPhase)this.module).roundToClosest(this.mc.player.posZ, Math.floor(this.mc.player.posZ) + 0.241, Math.floor(this.mc.player.posZ) + 0.759) - this.mc.player.posZ, -0.03, 0.03));
            this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY, this.mc.player.posZ, true));
            this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(((CityPhase)this.module).roundToClosest(this.mc.player.posX, Math.floor(this.mc.player.posX) + 0.23, Math.floor(this.mc.player.posX) + 0.77), this.mc.player.posY, ((CityPhase)this.module).roundToClosest(this.mc.player.posZ, Math.floor(this.mc.player.posZ) + 0.23, Math.floor(this.mc.player.posZ) + 0.77), true));
        }
    }
}
