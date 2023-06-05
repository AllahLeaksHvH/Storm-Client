//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.event.events;

import net.minecraftforge.fml.common.eventhandler.*;

public class UpdateEvent extends Event
{
    private final int stage;
    
    public UpdateEvent(final int stage) {
        this.stage = stage;
    }
    
    public final int getStage() {
        return this.stage;
    }
}
