//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.movement;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Anchor extends Module
{
    public Setting<Boolean> pull;
    public Setting<Integer> pitch;
    private final ArrayList<BlockPos> holes;
    int holeblocks;
    public static boolean AnchorING;
    private Vec3d Center;
    
    public Anchor() {
        super("Anchor", "Stops all movement if player is above a hole.", Module.Category.MOVEMENT, false, false, false);
        this.pull = (Setting<Boolean>)this.register(new Setting("Pull", (T)true));
        this.pitch = (Setting<Integer>)this.register(new Setting("Pitch", (T)60, (T)0, (T)90));
        this.holes = new ArrayList<BlockPos>();
        this.Center = Vec3d.ZERO;
    }
    
    public boolean isBlockHole(final BlockPos blockpos) {
        this.holeblocks = 0;
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 3, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 2, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 1, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 0, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, -1, 0)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(0, -1, 0)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(1, 0, 0)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(1, 0, 0)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(-1, 0, 0)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 0, 1)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(0, 0, 1)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 0, -1)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(0, 0, -1)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        return this.holeblocks >= 9;
    }
    
    public Vec3d GetCenter(final double posX, final double posY, final double posZ) {
        final double x = Math.floor(posX) + 0.5;
        final double y = Math.floor(posY);
        final double z = Math.floor(posZ) + 0.5;
        return new Vec3d(x, y, z);
    }
    
    @SubscribeEvent
    public String onUpdate() {
        if (Anchor.mc.world == null) {
            return null;
        }
        if (Anchor.mc.player.posY < 0.0) {
            return null;
        }
        if (Anchor.mc.player.rotationPitch >= this.pitch.getValue()) {
            if (this.isBlockHole(this.getPlayerPos().down(1)) || this.isBlockHole(this.getPlayerPos().down(2)) || this.isBlockHole(this.getPlayerPos().down(3)) || this.isBlockHole(this.getPlayerPos().down(4))) {
                Anchor.AnchorING = true;
                if (!this.pull.getValue()) {
                    Anchor.mc.player.motionX = 0.0;
                    Anchor.mc.player.motionZ = 0.0;
                }
                else {
                    this.Center = this.GetCenter(Anchor.mc.player.posX, Anchor.mc.player.posY, Anchor.mc.player.posZ);
                    final double XDiff = Math.abs(this.Center.x - Anchor.mc.player.posX);
                    final double ZDiff = Math.abs(this.Center.z - Anchor.mc.player.posZ);
                    if (XDiff <= 0.1 && ZDiff <= 0.1) {
                        this.Center = Vec3d.ZERO;
                    }
                    else {
                        final double MotionX = this.Center.x - Anchor.mc.player.posX;
                        final double MotionZ = this.Center.z - Anchor.mc.player.posZ;
                        Anchor.mc.player.motionX = MotionX / 2.0;
                        Anchor.mc.player.motionZ = MotionZ / 2.0;
                    }
                }
            }
            else {
                Anchor.AnchorING = false;
            }
        }
        return null;
    }
    
    public void onDisable() {
        Anchor.AnchorING = false;
        this.holeblocks = 0;
    }
    
    public BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(Anchor.mc.player.posX), Math.floor(Anchor.mc.player.posY), Math.floor(Anchor.mc.player.posZ));
    }
}
