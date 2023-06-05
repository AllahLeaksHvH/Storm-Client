//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm;

import club.minnced.discord.rpc.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import me.dev.storm.features.modules.misc.*;

public class DiscordPresence
{
    private static final DiscordRPC rpc;
    public static DiscordRichPresence presence;
    private static Thread thread;
    private static int index;
    
    public static void start() {
        final DiscordEventHandlers handlers = new DiscordEventHandlers();
        DiscordPresence.rpc.Discord_Initialize("1110102179903189043", handlers, true, "");
        DiscordPresence.presence.startTimestamp = System.currentTimeMillis() / 1000L;
        DiscordPresence.presence.details = ((Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) ? "In the menu." : ("Owning " + ((Minecraft.getMinecraft().currentServerData != null) ? (RPC.INSTANCE.showIP.getValue() ? ("on " + Minecraft.getMinecraft().currentServerData.serverIP + ".") : " multiplayer.") : " singleplayer.")));
        DiscordPresence.presence.state = RPC.INSTANCE.state.getValue();
        DiscordPresence.presence.largeImageKey = "stormlogo";
        DiscordPresence.presence.largeImageText = "Storm 0.3";
        DiscordPresence.rpc.Discord_UpdatePresence(DiscordPresence.presence);
        DiscordRichPresence presence;
        String string;
        String string2;
        final StringBuilder sb;
        (DiscordPresence.thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                DiscordPresence.rpc.Discord_RunCallbacks();
                presence = DiscordPresence.presence;
                if (Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) {
                    string = "In the menu.";
                }
                else {
                    new StringBuilder().append("Owning ");
                    if (Minecraft.getMinecraft().currentServerData != null) {
                        if (RPC.INSTANCE.showIP.getValue()) {
                            string2 = "on " + Minecraft.getMinecraft().currentServerData.serverIP + ".";
                        }
                        else {
                            string2 = " multiplayer.";
                        }
                    }
                    else {
                        string2 = " singleplayer.";
                    }
                    string = sb.append(string2).toString();
                }
                presence.details = string;
                DiscordPresence.presence.state = RPC.INSTANCE.state.getValue();
                if (RPC.INSTANCE.catMode.getValue()) {
                    if (DiscordPresence.index == 12) {
                        DiscordPresence.index = 1;
                    }
                    DiscordPresence.presence.largeImageKey = "" + DiscordPresence.index;
                    ++DiscordPresence.index;
                }
                DiscordPresence.rpc.Discord_UpdatePresence(DiscordPresence.presence);
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ex) {}
            }
        }, "DisableRPC-Callback-Handler")).start();
    }
    
    public static void stop() {
        if (DiscordPresence.thread != null && !DiscordPresence.thread.isInterrupted()) {
            DiscordPresence.thread.interrupt();
        }
        DiscordPresence.rpc.Discord_Shutdown();
    }
    
    static {
        DiscordPresence.index = 1;
        rpc = DiscordRPC.INSTANCE;
        DiscordPresence.presence = new DiscordRichPresence();
    }
}
