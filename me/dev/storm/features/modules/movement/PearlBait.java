//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.movement;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import net.minecraft.network.play.client.*;
import java.util.concurrent.*;
import me.dev.storm.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.entity.player.*;

public class PearlBait extends Module
{
    public final Setting<Boolean> guarantee;
    private final Queue<CPacketPlayer> packets;
    private int thrownPearlId;
    
    public PearlBait() {
        super("PearlBait", "Forces your enemy to cry", Module.Category.MOVEMENT, true, false, false);
        this.guarantee = (Setting<Boolean>)this.register(new Setting("Guarantee", (T)true));
        this.packets = new ConcurrentLinkedQueue<CPacketPlayer>();
        this.thrownPearlId = -1;
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketSpawnObject) {
            final SPacketSpawnObject packet = (SPacketSpawnObject)event.getPacket();
            if (packet.getType() == 65) {
                final SPacketSpawnObject sPacketSpawnObject;
                final SPacketSpawnObject sPacketSpawnObject2;
                PearlBait.mc.world.playerEntities.stream().min(Comparator.comparingDouble(p -> p.getDistance(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ()))).ifPresent(player -> {
                    if (player.equals((Object)PearlBait.mc.player)) {
                        if (!(!PearlBait.mc.player.onGround)) {
                            PearlBait.mc.player.motionX = 0.0;
                            PearlBait.mc.player.motionY = 0.0;
                            PearlBait.mc.player.motionZ = 0.0;
                            PearlBait.mc.player.movementInput.moveForward = 0.0f;
                            PearlBait.mc.player.movementInput.moveStrafe = 0.0f;
                            PearlBait.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PearlBait.mc.player.posX, PearlBait.mc.player.posY + 1.0, PearlBait.mc.player.posZ, false));
                            this.thrownPearlId = sPacketSpawnObject2.getEntityID();
                        }
                    }
                });
            }
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketPlayer && this.guarantee.getValue() && this.thrownPearlId != -1) {
            this.packets.add((CPacketPlayer)event.getPacket());
            event.setCanceled(true);
        }
    }
    
    public String onUpdate() {
        if (this.thrownPearlId != -1) {
            for (final Entity entity : PearlBait.mc.world.loadedEntityList) {
                if (entity.getEntityId() == this.thrownPearlId && entity instanceof EntityEnderPearl) {
                    final EntityEnderPearl pearl = (EntityEnderPearl)entity;
                    if (!pearl.isDead) {
                        continue;
                    }
                    this.thrownPearlId = -1;
                }
            }
        }
        else if (!this.packets.isEmpty()) {
            do {
                PearlBait.mc.player.connection.sendPacket((Packet)this.packets.poll());
            } while (!this.packets.isEmpty());
        }
        return null;
    }
}
