//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.misc;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import me.dev.storm.util.*;
import me.dev.storm.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.play.server.*;
import com.mojang.realmsclient.gui.*;
import me.dev.storm.features.command.*;

public class ChatModifier extends Module
{
    public Setting<Boolean> antiUnicode;
    public Setting<Integer> maxSymbolCount;
    public Setting<Boolean> greenT;
    public Setting<Suffix> suffix;
    public Setting<Boolean> clean;
    public Setting<Boolean> infinite;
    private final Timer timer;
    private final Timer delay;
    private static ChatModifier INSTANCE;
    
    public ChatModifier() {
        super("ChatModifier", "Modifies your chat.", Category.MISC, true, false, false);
        this.antiUnicode = (Setting<Boolean>)this.register(new Setting("AntiUnicode", (T)true, "Blocks "));
        this.maxSymbolCount = (Setting<Integer>)this.register(new Setting("MaxUnicodeCount", (T)100, (T)1, (T)250));
        this.greenT = (Setting<Boolean>)this.register(new Setting("GreenText", (T)false));
        this.suffix = (Setting<Suffix>)this.register(new Setting("Suffix", (T)Suffix.NONE, "Your Suffix."));
        this.clean = (Setting<Boolean>)this.register(new Setting("CleanChat", (T)false, "Cleans your chat"));
        this.infinite = (Setting<Boolean>)this.register(new Setting("Infinite", (T)false, "Makes your chat infinite."));
        this.timer = new Timer();
        this.delay = new Timer();
        this.setInstance();
    }
    
    private void setInstance() {
        ChatModifier.INSTANCE = this;
    }
    
    public static ChatModifier getInstance() {
        if (ChatModifier.INSTANCE == null) {
            ChatModifier.INSTANCE = new ChatModifier();
        }
        return ChatModifier.INSTANCE;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getStage() == 0 && event.getPacket() instanceof CPacketChatMessage) {
            final CPacketChatMessage packet = (CPacketChatMessage)event.getPacket();
            String s = packet.getMessage();
            if (s.startsWith("/")) {
                return;
            }
            switch (this.suffix.getValue()) {
                case STORM: {
                    s += " S\u1d1b\u1d0f\u0280\u1d0d";
                    break;
                }
                case DOTGOD: {
                    s += " \u1d05\u1d0f\u1d1b\u0262\u1d0f\u1d05";
                    break;
                }
                case PYRO: {
                    s += " \u1d18\u028f\u0280\u1d0f";
                    break;
                }
                case FUTURE: {
                    s += " future";
                    break;
                }
            }
            if (this.greenT.getValue()) {
                s = "> " + s;
            }
            if (s.length() >= 256) {
                s = s.substring(0, 256);
            }
            packet.message = s;
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (this.antiUnicode.getValue() && event.getPacket() instanceof SPacketChat) {
            final String text = ((SPacketChat)event.getPacket()).chatComponent.getFormattedText();
            int symbolCount = 0;
            for (int i = 0; i < text.length(); ++i) {
                final char c = text.charAt(i);
                if (this.isSymbol(c)) {
                    ++symbolCount;
                }
                if (symbolCount > this.maxSymbolCount.getValue()) {
                    if (this.delay.passed(10L)) {
                        Command.sendMessage(ChatFormatting.GREEN + "Unicode message blocked!");
                        this.delay.reset();
                    }
                    event.setCanceled(true);
                    break;
                }
            }
        }
    }
    
    private boolean isSymbol(final char charIn) {
        return (charIn < 'A' || charIn > 'Z') && (charIn < 'a' || charIn > 'z') && (charIn < '0' || charIn > '9');
    }
    
    static {
        ChatModifier.INSTANCE = new ChatModifier();
    }
    
    public enum Suffix
    {
        NONE, 
        STORM, 
        DOTGOD, 
        PYRO, 
        FUTURE;
    }
}
