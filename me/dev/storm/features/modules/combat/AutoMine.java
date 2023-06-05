//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.combat;

import me.dev.storm.features.modules.*;
import net.minecraft.entity.player.*;
import me.dev.storm.features.setting.*;
import net.minecraft.util.math.*;
import me.dev.storm.features.modules.combat.autocity.*;
import net.minecraft.init.*;
import net.minecraft.block.state.*;
import me.dev.storm.util.*;
import net.minecraft.entity.*;
import me.dev.storm.*;
import java.util.*;

public class AutoMine extends Module
{
    public static EntityPlayer target;
    private final Setting<Float> range;
    private final Setting<Boolean> toggle;
    public final Setting<Boolean> db;
    
    private void surroundMine(final BlockPos blockPos) {
        if (InstantMine.breakPos != null) {
            if (InstantMine.breakPos.equals((Object)blockPos)) {
                return;
            }
            if (InstantMine.breakPos.equals((Object)new BlockPos(AutoMine.target.posX, AutoMine.target.posY, AutoMine.target.posZ)) && AutoMine.mc.world.getBlockState(new BlockPos(AutoMine.target.posX, AutoMine.target.posY, AutoMine.target.posZ)).getBlock() != Blocks.AIR) {
                return;
            }
            if (InstantMine.breakPos.equals((Object)new BlockPos(AutoMine.mc.player.posX, AutoMine.mc.player.posY + 2.0, AutoMine.mc.player.posZ))) {
                return;
            }
            if (InstantMine.breakPos.equals((Object)new BlockPos(AutoMine.mc.player.posX, AutoMine.mc.player.posY - 1.0, AutoMine.mc.player.posZ))) {
                return;
            }
            if (AutoMine.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.WEB) {
                return;
            }
        }
        AutoMine.mc.playerController.onPlayerDamageBlock(blockPos, BlockUtil.getRayTraceFacing(blockPos));
    }
    
    @Override
    public String getDisplayInfo() {
        if (AutoMine.target != null) {
            return AutoMine.target.getName();
        }
        return null;
    }
    
    @Override
    public String onUpdate() {
        AutoMine.target = this.getTarget(this.range.getValue());
        if (AutoMine.target == null) {
            return null;
        }
        final BlockPos blockPos = new BlockPos(AutoMine.target.posX, AutoMine.target.posY, AutoMine.target.posZ);
        if (!this.detection(AutoMine.target)) {
            if (this.db.getValue()) {
                if (this.getBlock(blockPos.add(0, 1, 2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 0, 1));
                }
                else if (this.getBlock(blockPos.add(0, 1, -2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 0, -1));
                }
                else if (this.getBlock(blockPos.add(2, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(2, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(1, 0, 0));
                }
                else if (this.getBlock(blockPos.add(-2, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-2, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(-1, 0, 0));
                }
                else if (this.getBlock(blockPos.add(2, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(2, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(2, 0, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(2, 0, 0));
                }
                else if (this.getBlock(blockPos.add(-2, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-2, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-2, 0, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(-2, 0, 0));
                }
                else if (this.getBlock(blockPos.add(0, 1, -2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -2)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -2)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 0, -2));
                }
                else if (this.getBlock(blockPos.add(0, 1, 2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 2)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 2)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 0, 2));
                }
                else if (this.getBlock(blockPos.add(2, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(2, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(2, 0, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(2, 0, 0));
                    if (InstantMine.breakPos2 == null) {
                        this.surroundMine(blockPos.add(1, 0, 0));
                    }
                }
                else if (this.getBlock(blockPos.add(-2, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-2, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-2, 0, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(-2, 0, 0));
                    if (InstantMine.breakPos2 == null) {
                        this.surroundMine(blockPos.add(-1, 0, 0));
                    }
                }
                else if (this.getBlock(blockPos.add(0, 1, -2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -2)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -2)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 0, -2));
                    if (InstantMine.breakPos2 == null) {
                        this.surroundMine(blockPos.add(0, 0, -1));
                    }
                }
                else if (this.getBlock(blockPos.add(0, 1, 2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 2)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 2)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 0, 2));
                    if (InstantMine.breakPos2 == null) {
                        this.surroundMine(blockPos.add(0, 0, 1));
                    }
                }
                else if (this.getBlock(blockPos.add(0, 2, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 1, 1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 1, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, 1)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 1, 1));
                }
                else if (this.getBlock(blockPos.add(0, 2, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 1, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 0, 1));
                }
                else if (this.getBlock(blockPos.add(0, 2, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 1, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 0, -1));
                }
                else if (this.getBlock(blockPos.add(1, 2, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(1, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(1, 0, 0));
                }
                else if (this.getBlock(blockPos.add(-1, 2, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-1, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(-1, 0, 0));
                }
                else if (this.getBlock(blockPos.add(1, 2, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 1, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 1, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(1, 1, 0));
                }
                else if (this.getBlock(blockPos.add(-1, 2, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 1, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 1, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(-1, 1, 0));
                }
                else if (this.getBlock(blockPos.add(0, 2, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 1, -1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 1, -1)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 1, -1));
                }
                else if (this.getBlock(blockPos.add(1, 2, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(1, 1, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 1, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(1, 1, 0));
                    if (InstantMine.breakPos2 == null) {
                        this.surroundMine(blockPos.add(1, 0, 0));
                    }
                }
                else if (this.getBlock(blockPos.add(-1, 2, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-1, 1, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 1, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(-1, 1, 0));
                    if (InstantMine.breakPos2 == null) {
                        this.surroundMine(blockPos.add(-1, 0, 0));
                    }
                }
                else if (this.getBlock(blockPos.add(0, 2, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 1, -1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, -1)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 1, -1));
                    if (InstantMine.breakPos2 == null) {
                        this.surroundMine(blockPos.add(0, 0, -1));
                    }
                }
                else if (this.getBlock(blockPos.add(0, 2, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 1, 1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, 1)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 1, 1));
                    if (InstantMine.breakPos2 == null) {
                        this.surroundMine(blockPos.add(0, 0, 1));
                    }
                }
                else if (this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-2, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-2, 1, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-2, 1, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(-2, 1, 0));
                }
                else if (this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(2, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(2, 1, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(2, 1, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(2, 1, 0));
                }
                else if (this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 2)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, 2)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 1, 2)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 1, 2));
                }
                else if (this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -2)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, -2)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 1, -2)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 1, -2));
                }
                else if (this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 1, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 2, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-1, 2, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(-1, 2, 0));
                }
                else if (this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 1, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 2, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(1, 2, 0)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(1, 2, 0));
                }
                else if (this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 2, 1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 2, 1)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 2, 1));
                }
                else if (this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 2, -1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 2, -1)).getBlock() != Blocks.BEDROCK) {
                    this.surroundMine(blockPos.add(0, 2, -1));
                }
            }
            else if (this.getBlock(blockPos.add(0, 1, 2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 0, 1));
            }
            else if (this.getBlock(blockPos.add(0, 1, -2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 0, -1));
            }
            else if (this.getBlock(blockPos.add(2, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(2, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(1, 0, 0));
            }
            else if (this.getBlock(blockPos.add(-2, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-2, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(-1, 0, 0));
            }
            else if (this.getBlock(blockPos.add(2, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(2, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(2, 0, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(2, 0, 0));
            }
            else if (this.getBlock(blockPos.add(-2, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-2, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-2, 0, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(-2, 0, 0));
            }
            else if (this.getBlock(blockPos.add(0, 1, -2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -2)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -2)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 0, -2));
            }
            else if (this.getBlock(blockPos.add(0, 1, 2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 2)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 2)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 0, 2));
            }
            else if (this.getBlock(blockPos.add(2, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(2, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(2, 0, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(2, 0, 0));
            }
            else if (this.getBlock(blockPos.add(-2, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-2, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-2, 0, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(-2, 0, 0));
            }
            else if (this.getBlock(blockPos.add(0, 1, -2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -2)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -2)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 0, -2));
            }
            else if (this.getBlock(blockPos.add(0, 1, 2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 2)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 2)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 0, 2));
            }
            else if (this.getBlock(blockPos.add(0, 2, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 1, 1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 1, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, 1)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 1, 1));
            }
            else if (this.getBlock(blockPos.add(0, 2, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 1, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 0, 1));
            }
            else if (this.getBlock(blockPos.add(0, 2, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 1, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 0, -1));
            }
            else if (this.getBlock(blockPos.add(1, 2, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(1, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(1, 0, 0));
            }
            else if (this.getBlock(blockPos.add(-1, 2, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-1, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(-1, 0, 0));
            }
            else if (this.getBlock(blockPos.add(1, 2, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 1, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 1, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(1, 1, 0));
            }
            else if (this.getBlock(blockPos.add(-1, 2, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 1, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 1, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(-1, 1, 0));
            }
            else if (this.getBlock(blockPos.add(0, 2, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 1, -1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 1, -1)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 1, -1));
            }
            else if (this.getBlock(blockPos.add(1, 2, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(1, 1, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 1, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(1, 1, 0));
            }
            else if (this.getBlock(blockPos.add(-1, 2, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-1, 1, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 1, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(-1, 1, 0));
            }
            else if (this.getBlock(blockPos.add(0, 2, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 1, -1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, -1)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 1, -1));
            }
            else if (this.getBlock(blockPos.add(0, 2, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 1, 1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, 1)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 1, 1));
            }
            else if (this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-2, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-2, 1, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-2, 1, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(-2, 1, 0));
            }
            else if (this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(2, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(2, 1, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(2, 1, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(2, 1, 0));
            }
            else if (this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 2)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, 2)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 1, 2)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 1, 2));
            }
            else if (this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -2)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, -2)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 1, -2)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 1, -2));
            }
            else if (this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 1, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 2, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(-1, 2, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(-1, 2, 0));
            }
            else if (this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 1, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 2, 0)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(1, 2, 0)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(1, 2, 0));
            }
            else if (this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 2, 1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 2, 1)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 2, 1));
            }
            else if (this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 2, -1)).getBlock() != Blocks.AIR && this.getBlock(blockPos.add(0, 2, -1)).getBlock() != Blocks.BEDROCK) {
                this.surroundMine(blockPos.add(0, 2, -1));
            }
        }
        if (this.toggle.getValue()) {
            this.disable();
        }
        return null;
    }
    
    private IBlockState getBlock(final BlockPos blockPos) {
        return AutoMine.mc.world.getBlockState(blockPos);
    }
    
    public AutoMine() {
        super("AutoCity", "Automatically breaks the enemy's hole", Category.COMBAT, true, false, false);
        this.db = (Setting<Boolean>)this.register(new Setting("Double Mode", (T)false));
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)5.0f, (T)1.0f, (T)8.0f));
        this.toggle = (Setting<Boolean>)this.register(new Setting("Toggle", (T)false));
    }
    
    private EntityPlayer getTarget(final double d) {
        EntityPlayer entityPlayer = null;
        double d2 = Math.pow(d, 2.0) + 1.0;
        for (final EntityPlayer entityPlayer2 : AutoMine.mc.world.playerEntities) {
            if (!EntityUtil.isntValid((Entity)entityPlayer2, d)) {
                if (Storm.speedManager.getPlayerSpeed(entityPlayer2) > 10.0) {
                    continue;
                }
                if (entityPlayer == null) {
                    entityPlayer = entityPlayer2;
                    d2 = AutoMine.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
                else {
                    if (AutoMine.mc.player.getDistanceSq((Entity)entityPlayer2) >= d2) {
                        continue;
                    }
                    entityPlayer = entityPlayer2;
                    d2 = AutoMine.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
            }
        }
        return entityPlayer;
    }
    
    private boolean detection(final EntityPlayer entityPlayer) {
        return (AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX + 1.2, entityPlayer.posY, entityPlayer.posZ)).getBlock() == Blocks.AIR & AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX + 1.2, entityPlayer.posY + 1.0, entityPlayer.posZ)).getBlock() == Blocks.AIR) || (AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX - 1.2, entityPlayer.posY, entityPlayer.posZ)).getBlock() == Blocks.AIR & AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX - 1.2, entityPlayer.posY + 1.0, entityPlayer.posZ)).getBlock() == Blocks.AIR) || (AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ + 1.2)).getBlock() == Blocks.AIR & AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY + 1.0, entityPlayer.posZ + 1.2)).getBlock() == Blocks.AIR) || (AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ - 1.2)).getBlock() == Blocks.AIR & AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY + 1.0, entityPlayer.posZ - 1.2)).getBlock() == Blocks.AIR) || (AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX + 2.2, entityPlayer.posY + 1.0, entityPlayer.posZ)).getBlock() == Blocks.AIR & AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX + 2.2, entityPlayer.posY, entityPlayer.posZ)).getBlock() == Blocks.AIR & AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX + 1.2, entityPlayer.posY, entityPlayer.posZ)).getBlock() == Blocks.AIR) || (AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX - 2.2, entityPlayer.posY + 1.0, entityPlayer.posZ)).getBlock() == Blocks.AIR & AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX - 2.2, entityPlayer.posY, entityPlayer.posZ)).getBlock() == Blocks.AIR & AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX - 1.2, entityPlayer.posY, entityPlayer.posZ)).getBlock() == Blocks.AIR) || (AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY + 1.0, entityPlayer.posZ + 2.2)).getBlock() == Blocks.AIR & AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ + 2.2)).getBlock() == Blocks.AIR & AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ + 1.2)).getBlock() == Blocks.AIR) || (AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY + 1.0, entityPlayer.posZ - 2.2)).getBlock() == Blocks.AIR & AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ - 2.2)).getBlock() == Blocks.AIR & AutoMine.mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ - 1.2)).getBlock() == Blocks.AIR);
    }
}
