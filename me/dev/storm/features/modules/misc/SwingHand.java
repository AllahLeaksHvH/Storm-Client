//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.misc;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import net.minecraft.util.*;
import me.dev.storm.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class SwingHand extends Module
{
    private Setting<Hand> hand;
    
    public SwingHand() {
        super("SwingHand", "Changes the hand you swing with", Category.MISC, false, false, false);
        this.hand = (Setting<Hand>)this.register(new Setting("Hand", (T)Hand.OFFHAND));
    }
    
    @Override
    public String onUpdate() {
        if (SwingHand.mc.world == null) {
            return null;
        }
        if (this.hand.getValue().equals(Hand.OFFHAND)) {
            SwingHand.mc.player.swingingHand = EnumHand.OFF_HAND;
        }
        if (this.hand.getValue().equals(Hand.MAINHAND)) {
            SwingHand.mc.player.swingingHand = EnumHand.MAIN_HAND;
        }
        return null;
    }
    
    @SubscribeEvent
    public void onPacket(final Packet event) {
        if (nullCheck() || event.getType() == Packet.Type.INCOMING) {
            return;
        }
        if (event.getPacket() instanceof CPacketAnimation) {
            event.setCanceled(true);
        }
    }
    
    public enum Hand
    {
        OFFHAND, 
        MAINHAND, 
        PACKETSWING;
    }
}
