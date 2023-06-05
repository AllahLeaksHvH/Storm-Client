//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.event.events;

import me.dev.storm.event.*;

public class TickEvent extends EventStage
{
    protected boolean isCancellable() {
        return false;
    }
    
    public static class Pre extends TickEvent
    {
    }
    
    public static class Post extends TickEvent
    {
    }
}
