//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.event.events;

import net.minecraftforge.fml.common.eventhandler.*;

@Cancelable
public class Packet extends Event
{
    private Object packet;
    private Type type;
    
    public Packet(final Object packet, final Type type) {
        this.packet = packet;
        this.type = type;
    }
    
    public void setPacket(final Object packet) {
        this.packet = packet;
    }
    
    public void setType(final Type type) {
        this.type = type;
    }
    
    public Object getPacket() {
        return this.packet;
    }
    
    public Type getType() {
        return this.type;
    }
    
    public enum Type
    {
        INCOMING, 
        OUTGOING;
    }
}
