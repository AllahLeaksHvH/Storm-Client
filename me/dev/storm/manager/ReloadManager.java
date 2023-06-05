//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.manager;

import me.dev.storm.features.*;
import net.minecraftforge.common.*;
import com.mojang.realmsclient.gui.*;
import me.dev.storm.features.command.*;
import me.dev.storm.event.events.*;
import net.minecraft.network.play.client.*;
import me.dev.storm.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ReloadManager extends Feature
{
    public String prefix;
    
    public void init(final String prefix) {
        this.prefix = prefix;
        MinecraftForge.EVENT_BUS.register((Object)this);
        if (!fullNullCheck()) {
            Command.sendMessage(ChatFormatting.RED + "storm has been unloaded. Type " + prefix + "reload to reload.");
        }
    }
    
    public void unload() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        final CPacketChatMessage packet;
        if (event.getPacket() instanceof CPacketChatMessage && (packet = (CPacketChatMessage)event.getPacket()).getMessage().startsWith(this.prefix) && packet.getMessage().contains("reload")) {
            Storm.load();
            event.setCanceled(true);
        }
    }
}
