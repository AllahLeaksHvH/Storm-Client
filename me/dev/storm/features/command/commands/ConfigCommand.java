//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.command.commands;

import me.dev.storm.features.command.*;
import java.io.*;
import java.util.stream.*;
import me.dev.storm.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;

public class ConfigCommand extends Command
{
    public ConfigCommand() {
        super("config", new String[] { "<save/load>" });
    }
    
    public void execute(final String[] commands) {
        if (commands.length == 1) {
            sendMessage("You`ll find the config files in your gameProfile directory under storm/config");
            return;
        }
        if (commands.length == 2) {
            if ("list".equals(commands[0])) {
                String configs = "Configs: ";
                final File file = new File("storm/");
                final List<File> directories = Arrays.stream(file.listFiles()).filter(File::isDirectory).filter(f -> !f.getName().equals("util")).collect((Collector<? super File, ?, List<File>>)Collectors.toList());
                final StringBuilder builder = new StringBuilder(configs);
                for (final File file2 : directories) {
                    builder.append(file2.getName() + ", ");
                }
                configs = builder.toString();
                sendMessage(configs);
            }
            else {
                sendMessage("Not a valid command... Possible usage: <list>");
            }
        }
        if (commands.length >= 3) {
            final String s = commands[0];
            switch (s) {
                case "save": {
                    Storm.configManager.saveConfig(commands[1]);
                    sendMessage(ChatFormatting.GREEN + "Config '" + commands[1] + "' has been saved.");
                }
                case "load": {
                    if (Storm.configManager.configExists(commands[1])) {
                        Storm.configManager.loadConfig(commands[1]);
                        sendMessage(ChatFormatting.GREEN + "Config '" + commands[1] + "' has been loaded.");
                    }
                    else {
                        sendMessage(ChatFormatting.RED + "Config '" + commands[1] + "' does not exist.");
                    }
                }
                default: {
                    sendMessage("Not a valid command... Possible usage: <save/load>");
                    break;
                }
            }
        }
    }
}
