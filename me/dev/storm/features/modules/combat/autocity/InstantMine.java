//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.combat.autocity;

import me.dev.storm.features.modules.*;
import me.dev.storm.util.*;
import me.dev.storm.features.setting.*;
import net.minecraft.network.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import me.dev.storm.features.modules.combat.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import java.awt.*;
import me.dev.storm.util.trollhack.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.item.*;
import java.util.function.*;
import java.util.stream.*;
import net.minecraft.network.play.client.*;
import me.dev.storm.event.events.*;
import java.util.*;

public class InstantMine extends Module
{
    boolean switched;
    Timer retryTime;
    private final Setting<Boolean> rotate;
    private final Setting<Integer> red;
    public final Setting<Boolean> db;
    public final Setting<Bind> bind;
    public static BlockPos breakPos;
    private final Timer breakSuccess;
    private static InstantMine INSTANCE;
    private final Setting<Boolean> render2;
    private final Setting<Integer> alpha;
    public FadeUtils secondFade;
    public static BlockPos breakPos2;
    public FadeUtils firstFade;
    private final Setting<Boolean> retry;
    private final Setting<Integer> green;
    private final Setting<Boolean> placeCrystal;
    public static final List<Block> godBlocks;
    private final Setting<Integer> pos1BoxAlpha;
    private final Setting<Integer> pos1FillAlpha;
    private final Setting<Boolean> ghostHand;
    public final Setting<Boolean> attackCrystal;
    private EnumFacing facing;
    private final Setting<Boolean> render;
    int slotMain2;
    public final Setting<Float> health;
    private final Setting<Integer> blue;
    private final Setting<Boolean> crystalOnBreak;
    static int ticked;
    private boolean empty;
    private final Setting<Boolean> instant;
    private final Setting<Integer> alpha2;
    private boolean cancelStart;
    
    private boolean lambda$new$0(final Float f) {
        return this.db.getValue();
    }
    
    @Override
    public String onUpdate() {
        if (fullNullCheck()) {
            return null;
        }
        if (InstantMine.mc.player.isCreative()) {
            return null;
        }
        this.slotMain2 = InstantMine.mc.player.inventory.currentItem;
        if (InstantMine.ticked == 0) {
            InstantMine.breakPos2 = null;
        }
        if (InstantMine.breakPos2 != null) {
            if (InstantMine.ticked >= 60 || InstantMine.ticked > 1) {}
            if (++InstantMine.ticked > 30 || (InstantMine.ticked > 13 && InstantMine.mc.world.getBlockState(InstantMine.breakPos2).getBlock() == Blocks.ENDER_CHEST) || (InstantMine.ticked >= 3 && InstantMine.mc.world.getBlockState(InstantMine.breakPos2).getBlock() == Blocks.WEB)) {
                if (InstantMine.mc.player.isHandActive()) {
                    this.resetMine();
                }
                else {
                    this.switchMine();
                }
            }
            if (InstantMine.mc.world.isAirBlock(InstantMine.breakPos2)) {
                this.resetMine();
                InstantMine.breakPos2 = null;
                InstantMine.ticked = 0;
            }
        }
        else {
            InstantMine.ticked = 0;
        }
        if (InstantMine.ticked > 80) {
            this.resetMine();
            InstantMine.breakPos2 = null;
            InstantMine.ticked = 0;
        }
        if (InstantMine.breakPos == null) {
            return null;
        }
        if (!this.instant.getValue() && InstantMine.mc.world.isAirBlock(InstantMine.breakPos)) {
            InstantMine.breakPos = null;
            return null;
        }
        if (!this.cancelStart) {
            return null;
        }
        if (InstantMine.mc.player.getDistance((double)InstantMine.breakPos.getX(), (double)InstantMine.breakPos.getY(), (double)InstantMine.breakPos.getZ()) > 6.0) {
            return null;
        }
        if (this.retry.getValue() && this.retryTime.passedMs(1000L) && !this.instant.getValue()) {
            this.retryTime.reset();
            InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
            this.cancelStart = true;
            InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
        }
        if (this.attackCrystal.getValue() && InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.AIR) {
            attackcrystal(this.rotate.getValue());
        }
        if (this.bind.getValue().isDown() && this.placeCrystal.getValue() && InventoryUtil.findHotbarBlock(BlockObsidian.class) != -1 && InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.AIR) {
            final int n = InventoryUtil.findHotbarBlock(BlockObsidian.class);
            final int n2 = InstantMine.mc.player.inventory.currentItem;
            this.switchToSlot(n);
            BlockUtil.placeBlock(InstantMine.breakPos, EnumHand.MAIN_HAND, false, true, false);
            this.switchToSlot(n2);
        }
        if (InventoryUtil.getItemHotbar(Items.END_CRYSTAL) != -1 && this.placeCrystal.getValue() && InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.OBSIDIAN && !InstantMine.breakPos.equals((Object)AntiBurrow.pos)) {
            if (this.empty) {
                BlockUtil.placeCrystalOnBlock3(InstantMine.breakPos, EnumHand.MAIN_HAND, true, false, true);
            }
            else if (!this.crystalOnBreak.getValue()) {
                BlockUtil.placeCrystalOnBlock3(InstantMine.breakPos, EnumHand.MAIN_HAND, true, false, true);
            }
        }
        if (InstantMine.godBlocks.contains(InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock())) {
            return null;
        }
        if (this.rotate.getValue()) {
            RotationUtil.facePos(InstantMine.breakPos);
        }
        if (InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock() != Blocks.WEB) {
            if (this.ghostHand.getValue() && InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE) != -1 && InventoryUtil.getItemHotbars(Items.DIAMOND_PICKAXE) != -1) {
                final int n = InstantMine.mc.player.inventory.currentItem;
                if (InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock() != Blocks.OBSIDIAN) {
                    InstantMine.mc.player.inventory.currentItem = InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE);
                    InstantMine.mc.playerController.updateController();
                    InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
                    InstantMine.mc.player.inventory.currentItem = n;
                    InstantMine.mc.playerController.updateController();
                    return null;
                }
                if (!this.breakSuccess.passedMs(1400L)) {
                    return null;
                }
                InstantMine.mc.player.inventory.currentItem = InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE);
                InstantMine.mc.playerController.updateController();
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
                InstantMine.mc.player.inventory.currentItem = n;
                InstantMine.mc.playerController.updateController();
                return null;
            }
        }
        else if (this.ghostHand.getValue() && InventoryUtil.getItemHotbar(Items.DIAMOND_SWORD) != -1 && InventoryUtil.getItemHotbars(Items.DIAMOND_SWORD) != -1) {
            final int n = InstantMine.mc.player.inventory.currentItem;
            InstantMine.mc.player.inventory.currentItem = InventoryUtil.getItemHotbar(Items.DIAMOND_SWORD);
            InstantMine.mc.playerController.updateController();
            InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
            InstantMine.mc.player.inventory.currentItem = n;
            InstantMine.mc.playerController.updateController();
            return null;
        }
        InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
        return null;
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (!InstantMine.mc.player.isCreative()) {
            if (InstantMine.breakPos2 != null) {
                final AxisAlignedBB axisAlignedBB2 = InstantMine.mc.world.getBlockState(InstantMine.breakPos2).getSelectedBoundingBox((World)InstantMine.mc.world, InstantMine.breakPos2);
                final double d6 = axisAlignedBB2.minX + (axisAlignedBB2.maxX - axisAlignedBB2.minX) / 2.0;
                final double d7 = axisAlignedBB2.minY + (axisAlignedBB2.maxY - axisAlignedBB2.minY) / 2.0;
                final double d8 = axisAlignedBB2.minZ + (axisAlignedBB2.maxZ - axisAlignedBB2.minZ) / 2.0;
                final double d9 = this.secondFade.easeOutQuad() * (axisAlignedBB2.maxX - d6);
                final double d10 = this.secondFade.easeOutQuad() * (axisAlignedBB2.maxY - d7);
                final double d11 = this.secondFade.easeOutQuad() * (axisAlignedBB2.maxZ - d8);
                final AxisAlignedBB axisAlignedBB3 = new AxisAlignedBB(d6 - d9, d7 - d10, d8 - d11, d6 + d9, d7 + d10, d8 + d11);
                if (InstantMine.breakPos != null) {
                    if (!InstantMine.breakPos2.equals((Object)InstantMine.breakPos)) {
                        RenderUtil2.drawBBBox(axisAlignedBB3, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.alpha.getValue());
                        RenderUtil2.drawBBFill(axisAlignedBB3, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha2.getValue()), this.alpha2.getValue());
                    }
                }
                else {
                    RenderUtil2.drawBBBox(axisAlignedBB3, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.alpha.getValue());
                    RenderUtil2.drawBBFill(axisAlignedBB3, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha2.getValue()), this.alpha2.getValue());
                }
            }
            if (this.cancelStart && InstantMine.breakPos != null) {
                if (InstantMine.godBlocks.contains(InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock())) {
                    this.empty = true;
                }
                final AxisAlignedBB axisAlignedBB2 = InstantMine.mc.world.getBlockState(InstantMine.breakPos).getSelectedBoundingBox((World)InstantMine.mc.world, InstantMine.breakPos);
                final double d6 = axisAlignedBB2.minX + (axisAlignedBB2.maxX - axisAlignedBB2.minX) / 2.0;
                final double d7 = axisAlignedBB2.minY + (axisAlignedBB2.maxY - axisAlignedBB2.minY) / 2.0;
                final double d8 = axisAlignedBB2.minZ + (axisAlignedBB2.maxZ - axisAlignedBB2.minZ) / 2.0;
                final double d9 = this.firstFade.easeOutQuad() * (axisAlignedBB2.maxX - d6);
                final double d10 = this.firstFade.easeOutQuad() * (axisAlignedBB2.maxY - d7);
                final double d11 = this.firstFade.easeOutQuad() * (axisAlignedBB2.maxZ - d8);
                final AxisAlignedBB axisAlignedBB3 = new AxisAlignedBB(d6 - d9, d7 - d10, d8 - d11, d6 + d9, d7 + d10, d8 + d11);
                if (this.render.getValue()) {
                    RenderUtil2.drawBBFill(axisAlignedBB3, new Color(this.empty ? 0 : 255, this.empty ? 255 : 0, 0, 255), this.pos1FillAlpha.getValue());
                }
                if (this.render2.getValue()) {
                    RenderUtil2.drawBBBox(axisAlignedBB3, new Color(this.empty ? 0 : 255, this.empty ? 255 : 0, 0, 255), this.pos1BoxAlpha.getValue());
                }
            }
        }
    }
    
    private static Float lambda$attackcrystal$7(final Entity entity) {
        return InstantMine.mc.player.getDistance(entity);
    }
    
    private boolean lambda$new$2(final Integer n) {
        return this.render.getValue();
    }
    
    private boolean lambda$new$4(final Object object) {
        return this.placeCrystal.getValue();
    }
    
    private boolean lambda$new$5(final Boolean bl) {
        return this.placeCrystal.getValue();
    }
    
    private boolean lambda$new$1(final Boolean bl) {
        return !this.instant.getValue();
    }
    
    public InstantMine() {
        super("InstantMine", "legacy", Category.COMBAT, true, false, false);
        this.switched = false;
        this.attackCrystal = (Setting<Boolean>)this.register(new Setting("Attack Crystal", (T)Boolean.TRUE));
        this.empty = false;
        this.cancelStart = false;
        this.db = (Setting<Boolean>)this.register(new Setting("Silent Double", (T)Boolean.TRUE));
        this.health = (Setting<Float>)this.register(new Setting("Health", (T)18.0f, (T)0.0f, (T)35.9f, (Predicate<T>)this::lambda$new$0));
        this.breakSuccess = new Timer();
        this.instant = (Setting<Boolean>)this.register(new Setting("Instant", (T)true));
        this.retry = (Setting<Boolean>)this.register(new Setting("Retry", (T)true, (Predicate<T>)this::lambda$new$1));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)true));
        this.ghostHand = (Setting<Boolean>)this.register(new Setting("GhostHand", (T)Boolean.TRUE));
        this.render = (Setting<Boolean>)this.register(new Setting("Pos1Fill", (T)true));
        this.pos1FillAlpha = (Setting<Integer>)this.register(new Setting("Pos1FillAlpha", (T)30, (T)0, (T)255, (Predicate<T>)this::lambda$new$2));
        this.render2 = (Setting<Boolean>)this.register(new Setting("Pos1Box", (T)true));
        this.pos1BoxAlpha = (Setting<Integer>)this.register(new Setting("Pos1BoxAlpha", (T)100, (T)0, (T)255, (Predicate<T>)this::lambda$new$3));
        this.placeCrystal = (Setting<Boolean>)this.register(new Setting("Place Crystal", (T)Boolean.TRUE));
        this.bind = (Setting<Bind>)this.register(new Setting("ObsidianBind", (T)new Bind(-1), this::lambda$new$4));
        this.crystalOnBreak = (Setting<Boolean>)this.register(new Setting("Crystal on Break", (T)Boolean.TRUE, (Predicate<T>)this::lambda$new$5));
        this.red = (Setting<Integer>)this.register(new Setting("Pos2Red", (T)255, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Pos2Green", (T)255, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Pos2Blue", (T)255, (T)0, (T)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Pos2BoxAlpha", (T)150, (T)0, (T)255));
        this.alpha2 = (Setting<Integer>)this.register(new Setting("Pos2FillAlpha", (T)70, (T)0, (T)255));
        this.firstFade = new FadeUtils(1500L);
        this.secondFade = new FadeUtils(1500L);
        this.retryTime = new Timer();
        this.setInstance();
    }
    
    @SubscribeEvent
    public void onBlockEvent(final PlayerDamageBlockEvent playerDamageBlockEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (InstantMine.mc.player.isCreative()) {
            return;
        }
        if (!BlockUtil.canBreak(playerDamageBlockEvent.pos)) {
            return;
        }
        if (InstantMine.breakPos != null) {
            if (InstantMine.breakPos.getX() == playerDamageBlockEvent.pos.getX() && InstantMine.breakPos.getY() == playerDamageBlockEvent.pos.getY() && InstantMine.breakPos.getZ() == playerDamageBlockEvent.pos.getZ()) {
                return;
            }
            if (InstantMine.breakPos.equals((Object)InstantMine.breakPos2)) {
                this.secondFade.reset();
            }
        }
        this.firstFade.reset();
        this.empty = false;
        this.cancelStart = false;
        InstantMine.breakPos = playerDamageBlockEvent.pos;
        this.breakSuccess.reset();
        this.facing = playerDamageBlockEvent.facing;
        if (InstantMine.breakPos == null) {
            return;
        }
        InstantMine.mc.player.swingArm(EnumHand.MAIN_HAND);
        InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
        this.cancelStart = true;
        InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
        playerDamageBlockEvent.setCanceled(true);
        if (InstantMine.breakPos2 == null) {
            InstantMine.ticked = 1;
            InstantMine.breakPos2 = playerDamageBlockEvent.pos;
            this.secondFade.reset();
        }
    }
    
    private void setInstance() {
        InstantMine.INSTANCE = this;
    }
    
    private void switchToSlot(final int n) {
        InstantMine.mc.player.inventory.currentItem = n;
        InstantMine.mc.playerController.updateController();
    }
    
    private void switchMine() {
        if (InstantMine.mc.player.getHealth() + InstantMine.mc.player.getAbsorptionAmount() >= this.health.getValue()) {
            if (this.db.getValue()) {
                if (InstantMine.mc.world.getBlockState(InstantMine.breakPos2).getBlock() == Blocks.WEB) {
                    if (InventoryUtil.getItemHotbar(Items.DIAMOND_SWORD) != -1) {
                        InstantMine.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(InventoryUtil.getItemHotbars(Items.DIAMOND_SWORD)));
                        this.switched = true;
                        ++InstantMine.ticked;
                    }
                }
                else if (InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE) != -1) {
                    InstantMine.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(InventoryUtil.getItemHotbars(Items.DIAMOND_PICKAXE)));
                    this.switched = true;
                    ++InstantMine.ticked;
                }
            }
        }
        else if (this.switched) {
            InstantMine.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.slotMain2));
            this.switched = false;
        }
    }
    
    private static boolean lambda$attackcrystal$6(final Entity entity) {
        return entity instanceof EntityEnderCrystal && !entity.isDead;
    }
    
    public static void attackcrystal(final boolean bl) {
        for (final Entity entity : (List)InstantMine.mc.world.loadedEntityList.stream().filter(InstantMine::lambda$attackcrystal$6).sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)InstantMine::lambda$attackcrystal$7)).collect(Collectors.toList())) {
            if (entity instanceof EntityEnderCrystal) {
                if (entity.getDistanceSq(InstantMine.breakPos) > 2.0) {
                    continue;
                }
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
                if (!bl) {
                    continue;
                }
                RotationUtil.facePos(new BlockPos(entity.posX, entity.posY + 0.5, entity.posZ));
            }
        }
    }
    
    private void resetMine() {
        if (this.switched) {
            InstantMine.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.slotMain2));
            this.switched = false;
        }
    }
    
    public static InstantMine INSTANCE() {
        if (InstantMine.INSTANCE != null) {
            return InstantMine.INSTANCE;
        }
        return InstantMine.INSTANCE = new InstantMine();
    }
    
    private boolean lambda$new$3(final Integer n) {
        return this.render2.getValue();
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        if (InstantMine.mc.player.isCreative()) {
            return;
        }
        if (!(send.getPacket() instanceof CPacketPlayerDigging)) {
            return;
        }
        final CPacketPlayerDigging cPacketPlayerDigging = (CPacketPlayerDigging)send.getPacket();
        if (cPacketPlayerDigging.getAction() != CPacketPlayerDigging.Action.START_DESTROY_BLOCK) {
            return;
        }
        send.setCanceled(this.cancelStart);
    }
    
    static {
        godBlocks = Arrays.asList(Blocks.AIR, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER, Blocks.BEDROCK);
        InstantMine.breakPos2 = null;
        InstantMine.ticked = 0;
        InstantMine.INSTANCE = new InstantMine();
    }
}
