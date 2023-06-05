//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.misc;

import me.dev.storm.features.modules.*;
import me.dev.storm.event.events.*;
import net.minecraft.network.play.server.*;
import me.dev.storm.features.command.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PayloadSpoof extends Module
{
    public PayloadSpoof() {
        super("PayloadSpoof", "blocks payloads and exploits", Category.MISC, true, false, false);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPacketRecieve(final PacketEvent.Receive event) {
        final String text;
        if (event.getPacket() instanceof SPacketChat && ((text = ((SPacketChat)event.getPacket()).getChatComponent().getUnformattedText()).contains("${") || text.contains("$<") || text.contains("$:-") || text.contains("jndi:ldap"))) {
            Command.sendMessage("[PayloadSpoof] Blocked message: " + text);
            event.setCanceled(true);
        }
    }
}
