//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.render;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import me.dev.storm.event.events.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import me.dev.storm.util.*;
import java.awt.*;
import me.dev.storm.util.trollhack.*;

public class HoleESP extends Module
{
    private static HoleESP INSTANCE;
    private final Setting<Integer> range;
    private final Setting<Integer> rangeY;
    private final Setting<Integer> red;
    private final Setting<Integer> green;
    private final Setting<Integer> blue;
    private final Setting<Integer> alpha;
    private final Setting<Integer> boxAlpha;
    private final Setting<Float> lineWidth;
    private final Setting<Integer> safeRed;
    private final Setting<Integer> safeGreen;
    private final Setting<Integer> safeBlue;
    private final Setting<Integer> safeAlpha;
    private final Setting<RenderMode> renderMode;
    public Setting<Boolean> doubleHoles;
    public Setting<Boolean> fov;
    public Setting<Boolean> renderOwn;
    public Setting<Boolean> box;
    public Setting<Boolean> outline;
    private final Setting<Integer> cRed;
    private final Setting<Integer> cGreen;
    private final Setting<Integer> cBlue;
    private final Setting<Integer> cAlpha;
    private final Setting<Integer> safecRed;
    private final Setting<Integer> safecGreen;
    private final Setting<Integer> safecBlue;
    private final Setting<Integer> safecAlpha;
    
    public HoleESP() {
        super("HoleESP", "Shows safe spots.", Module.Category.RENDER, false, false, false);
        this.range = (Setting<Integer>)this.register(new Setting("RangeX", (T)0, (T)0, (T)10));
        this.rangeY = (Setting<Integer>)this.register(new Setting("RangeY", (T)0, (T)0, (T)10));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)0, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)255, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)0, (T)0, (T)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)255, (T)0, (T)255));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (T)125, (T)0, (T)255));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.0f, (T)0.1f, (T)5.0f));
        this.safeRed = (Setting<Integer>)this.register(new Setting("BedrockRed", (T)0, (T)0, (T)255));
        this.safeGreen = (Setting<Integer>)this.register(new Setting("BedrockGreen", (T)255, (T)0, (T)255));
        this.safeBlue = (Setting<Integer>)this.register(new Setting("BedrockBlue", (T)0, (T)0, (T)255));
        this.safeAlpha = (Setting<Integer>)this.register(new Setting("BedrockAlpha", (T)255, (T)0, (T)255));
        this.renderMode = (Setting<RenderMode>)this.register(new Setting("RenderMode", (T)RenderMode.Crossed));
        this.doubleHoles = (Setting<Boolean>)this.register(new Setting("DoubleHoles", (T)true));
        this.fov = (Setting<Boolean>)this.register(new Setting("InFov", (T)true));
        this.renderOwn = (Setting<Boolean>)this.register(new Setting("RenderOwn", (T)true));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (T)true));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (T)true));
        this.cRed = (Setting<Integer>)this.register(new Setting("OL-Red", (T)0, (T)0, (T)255, v -> this.outline.getValue()));
        this.cGreen = (Setting<Integer>)this.register(new Setting("OL-Green", (T)0, (T)0, (T)255, v -> this.outline.getValue()));
        this.cBlue = (Setting<Integer>)this.register(new Setting("OL-Blue", (T)255, (T)0, (T)255, v -> this.outline.getValue()));
        this.cAlpha = (Setting<Integer>)this.register(new Setting("OL-Alpha", (T)255, (T)0, (T)255, v -> this.outline.getValue()));
        this.safecRed = (Setting<Integer>)this.register(new Setting("OL-BedrockRed", (T)0, (T)0, (T)255, v -> this.outline.getValue()));
        this.safecGreen = (Setting<Integer>)this.register(new Setting("OL-BedrockGreen", (T)255, (T)0, (T)255, v -> this.outline.getValue()));
        this.safecBlue = (Setting<Integer>)this.register(new Setting("OL-BedrockBlue", (T)0, (T)0, (T)255, v -> this.outline.getValue()));
        this.safecAlpha = (Setting<Integer>)this.register(new Setting("OL-BedrockAlpha", (T)255, (T)0, (T)255, v -> this.outline.getValue()));
        this.setInstance();
    }
    
    public static HoleESP getInstance() {
        if (HoleESP.INSTANCE == null) {
            HoleESP.INSTANCE = new HoleESP();
        }
        return HoleESP.INSTANCE;
    }
    
    private void setInstance() {
        HoleESP.INSTANCE = this;
    }
    
    public void onRender3D(final Render3DEvent event) {
        assert HoleESP.mc.renderViewEntity != null;
        final Vec3i playerPos = new Vec3i(HoleESP.mc.renderViewEntity.posX, HoleESP.mc.renderViewEntity.posY, HoleESP.mc.renderViewEntity.posZ);
        for (int x = playerPos.getX() - this.range.getValue(); x < playerPos.getX() + this.range.getValue(); ++x) {
            for (int z = playerPos.getZ() - this.range.getValue(); z < playerPos.getZ() + this.range.getValue(); ++z) {
                for (int y = playerPos.getY() + this.rangeY.getValue(); y > playerPos.getY() - this.rangeY.getValue(); --y) {
                    final BlockPos pos = new BlockPos(x, y, z);
                    if (HoleESP.mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR) && HoleESP.mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR) && HoleESP.mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR) && (!pos.equals((Object)new BlockPos(HoleESP.mc.player.posX, HoleESP.mc.player.posY, HoleESP.mc.player.posZ)) || this.renderOwn.getValue()) && (BlockUtil.isPosInFov(pos) || !this.fov.getValue())) {
                        if (this.doubleHoles.getValue()) {
                            if (HoleESP.mc.world.getBlockState(pos.north()).getBlock() == Blocks.AIR && HoleESP.mc.world.getBlockState(pos.north().up()).getBlock() == Blocks.AIR && HoleESP.mc.world.getBlockState(pos.north().down()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.north(2)).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.east()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.north().east()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.west()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.north().west()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.south()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.down()).getBlock() == Blocks.BEDROCK) {
                                if (this.renderMode.getValue() == RenderMode.Full) {
                                    RenderUtil.drawBoxESP(pos, new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.outline.getValue(), new Color(this.safecRed.getValue(), this.safecGreen.getValue(), this.safecBlue.getValue(), this.safecAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                    RenderUtil.drawBoxESP(pos.north(), new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.outline.getValue(), new Color(this.safecRed.getValue(), this.safecGreen.getValue(), this.safecBlue.getValue(), this.safecAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                }
                                if (this.renderMode.getValue() == RenderMode.FullOffset) {
                                    RenderUtil.drawBoxESP(pos.down(), new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.outline.getValue(), new Color(this.safecRed.getValue(), this.safecGreen.getValue(), this.safecBlue.getValue(), this.safecAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                    RenderUtil.drawBoxESP(pos.north().down(), new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.outline.getValue(), new Color(this.safecRed.getValue(), this.safecGreen.getValue(), this.safecBlue.getValue(), this.safecAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                }
                                if (this.renderMode.getValue() == RenderMode.Crossed) {
                                    RenderUtil.drawCrossESP(pos, new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.lineWidth.getValue(), true);
                                    RenderUtil.drawCrossESP(pos.north(), new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.lineWidth.getValue(), true);
                                }
                            }
                            else if (HoleESP.mc.world.getBlockState(pos.north()).getBlock() == Blocks.AIR && HoleESP.mc.world.getBlockState(pos.north().up()).getBlock() == Blocks.AIR && (HoleESP.mc.world.getBlockState(pos.north().down()).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(pos.north().down()).getBlock() == Blocks.BEDROCK) && (HoleESP.mc.world.getBlockState(pos.north(2)).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(pos.north(2)).getBlock() == Blocks.BEDROCK) && (HoleESP.mc.world.getBlockState(pos.east()).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(pos.east()).getBlock() == Blocks.BEDROCK) && (HoleESP.mc.world.getBlockState(pos.north().east()).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(pos.north().east()).getBlock() == Blocks.BEDROCK) && (HoleESP.mc.world.getBlockState(pos.west()).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(pos.west()).getBlock() == Blocks.BEDROCK) && (HoleESP.mc.world.getBlockState(pos.north().west()).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(pos.north().west()).getBlock() == Blocks.BEDROCK) && (HoleESP.mc.world.getBlockState(pos.south()).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(pos.south()).getBlock() == Blocks.BEDROCK) && (HoleESP.mc.world.getBlockState(pos.down()).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(pos.down()).getBlock() == Blocks.BEDROCK)) {
                                if (this.renderMode.getValue() == RenderMode.Full) {
                                    RenderUtil.drawBoxESP(pos, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.alpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                    RenderUtil.drawBoxESP(pos.north(), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.alpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                }
                                if (this.renderMode.getValue() == RenderMode.FullOffset) {
                                    RenderUtil.drawBoxESP(pos.down(), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.alpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                    RenderUtil.drawBoxESP(pos.north().down(), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.alpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                }
                                if (this.renderMode.getValue() == RenderMode.Crossed) {
                                    RenderUtil.drawCrossESP(pos, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.lineWidth.getValue(), true);
                                    RenderUtil.drawCrossESP(pos.north(), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.lineWidth.getValue(), true);
                                }
                            }
                            if (HoleESP.mc.world.getBlockState(pos.east()).getBlock() == Blocks.AIR && HoleESP.mc.world.getBlockState(pos.east().up()).getBlock() == Blocks.AIR && HoleESP.mc.world.getBlockState(pos.east().down()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.east(2)).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.east(2).down()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.north()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.east().north()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.west()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.east().south()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.south()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.down()).getBlock() == Blocks.BEDROCK) {
                                if (this.renderMode.getValue() == RenderMode.Full) {
                                    RenderUtil.drawBoxESP(pos, new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.outline.getValue(), new Color(this.safecRed.getValue(), this.safecGreen.getValue(), this.safecBlue.getValue(), this.safecAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                    RenderUtil.drawBoxESP(pos.east(), new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.outline.getValue(), new Color(this.safecRed.getValue(), this.safecGreen.getValue(), this.safecBlue.getValue(), this.safecAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                }
                                if (this.renderMode.getValue() == RenderMode.FullOffset) {
                                    RenderUtil.drawBoxESP(pos.down(), new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.outline.getValue(), new Color(this.safecRed.getValue(), this.safecGreen.getValue(), this.safecBlue.getValue(), this.safecAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                    RenderUtil.drawBoxESP(pos.east().down(), new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.outline.getValue(), new Color(this.safecRed.getValue(), this.safecGreen.getValue(), this.safecBlue.getValue(), this.safecAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                }
                                if (this.renderMode.getValue() == RenderMode.Crossed) {
                                    RenderUtil.drawCrossESP(pos, new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.lineWidth.getValue(), true);
                                    RenderUtil.drawCrossESP(pos.east(), new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.lineWidth.getValue(), true);
                                }
                            }
                            else if (HoleESP.mc.world.getBlockState(pos.east()).getBlock() == Blocks.AIR && HoleESP.mc.world.getBlockState(pos.east().up()).getBlock() == Blocks.AIR && (HoleESP.mc.world.getBlockState(pos.east().down()).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(pos.east().down()).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(pos.east(2)).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(pos.east(2)).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(pos.north()).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(pos.north()).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(pos.east().north()).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(pos.east().north()).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(pos.west()).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(pos.west()).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(pos.east().south()).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(pos.east().south()).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(pos.south()).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(pos.south()).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(pos.down()).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(pos.down()).getBlock() == Blocks.OBSIDIAN)) {
                                if (this.renderMode.getValue() == RenderMode.Full) {
                                    RenderUtil.drawBoxESP(pos, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                    RenderUtil.drawBoxESP(pos.east(), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                }
                                if (this.renderMode.getValue() == RenderMode.FullOffset) {
                                    RenderUtil.drawBoxESP(pos.down(), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                    RenderUtil.drawBoxESP(pos.east().down(), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                }
                                if (this.renderMode.getValue() == RenderMode.Crossed) {
                                    RenderUtil.drawCrossESP(pos, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.lineWidth.getValue(), true);
                                    RenderUtil.drawCrossESP(pos.east(), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.lineWidth.getValue(), true);
                                }
                            }
                        }
                        if (HoleESP.mc.world.getBlockState(pos.north()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.east()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.west()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.south()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.down()).getBlock() == Blocks.BEDROCK) {
                            if (this.renderMode.getValue() == RenderMode.Full) {
                                RenderUtil.drawBoxESP(pos, new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.outline.getValue(), new Color(this.safecRed.getValue(), this.safecGreen.getValue(), this.safecBlue.getValue(), this.safecAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                            }
                            if (this.renderMode.getValue() == RenderMode.FullOffset) {
                                RenderUtil.drawBoxESP(pos.down(), new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.outline.getValue(), new Color(this.safecRed.getValue(), this.safecGreen.getValue(), this.safecBlue.getValue(), this.safecAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                            }
                            if (this.renderMode.getValue() == RenderMode.Crossed) {
                                RenderUtil.drawCrossESP(pos, new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.lineWidth.getValue(), true);
                            }
                            if (this.renderMode.getValue() == RenderMode.Fluctuate) {
                                RenderUtil.drawFlucESP(pos, new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.lineWidth.getValue(), true);
                            }
                        }
                        else if (BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.down()).getBlock()) && BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.east()).getBlock()) && BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.west()).getBlock()) && BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.south()).getBlock()) && BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.north()).getBlock())) {
                            if (this.renderMode.getValue() == RenderMode.Full) {
                                RenderUtil.drawBoxESP(pos, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                            }
                            if (this.renderMode.getValue() == RenderMode.FullOffset) {
                                RenderUtil.drawBoxESP(pos.down(), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                            }
                            if (this.renderMode.getValue() == RenderMode.Crossed) {
                                RenderUtil.drawCrossESP(pos, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.safeAlpha.getValue()), this.lineWidth.getValue(), true);
                            }
                        }
                    }
                }
            }
        }
    }
    
    static {
        HoleESP.INSTANCE = new HoleESP();
    }
    
    public enum RenderMode
    {
        Full, 
        FullOffset, 
        Crossed, 
        CrossedFull, 
        Fluctuate;
    }
}
