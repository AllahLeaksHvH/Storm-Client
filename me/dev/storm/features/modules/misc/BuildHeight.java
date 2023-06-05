//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.misc;

import me.dev.storm.features.modules.*;
import me.dev.storm.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class BuildHeight extends Module
{
    public BuildHeight() {
        super("BuildHeight", "Allows you to place at build height", Category.MISC, true, false, false);
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        final CPacketPlayerTryUseItemOnBlock packet;
        if (event.getStage() == 0 && event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock && (packet = (CPacketPlayerTryUseItemOnBlock)event.getPacket()).getPos().getY() >= 255 && packet.getDirection() == EnumFacing.UP) {
            packet.placedBlockDirection = EnumFacing.DOWN;
        }
    }
}
