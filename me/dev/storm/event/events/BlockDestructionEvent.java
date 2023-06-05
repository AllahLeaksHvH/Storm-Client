//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.event.events;

import me.dev.storm.event.*;
import net.minecraft.util.math.*;

public class BlockDestructionEvent extends EventStage
{
    BlockPos blockz;
    
    public BlockDestructionEvent(BlockPos blockz) {
        blockz = blockz;
    }
    
    public BlockPos getBlockPos() {
        return this.blockz;
    }
}
