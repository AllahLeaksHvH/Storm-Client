//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.event.events;

import me.dev.storm.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;

@Cancelable
public class BlockEvent extends EventStage
{
    public BlockPos pos;
    public EnumFacing facing;
    
    public BlockEvent(final int stage, final BlockPos pos, final EnumFacing facing) {
        super(stage);
        this.pos = pos;
        this.facing = facing;
    }
}
