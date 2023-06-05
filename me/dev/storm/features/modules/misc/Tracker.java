//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.misc;

import me.dev.storm.features.modules.*;
import net.minecraft.entity.player.*;
import me.dev.storm.util.*;
import me.dev.storm.features.modules.client.*;
import me.dev.storm.features.command.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import java.util.*;
import me.dev.storm.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Tracker extends Module
{
    private static Tracker instance;
    private EntityPlayer trackedPlayer;
    private int usedExp;
    private int usedStacks;
    
    public Tracker() {
        super("Duel", "Tracks players in 1v1s.", Category.MISC, true, false, false);
        this.usedExp = 0;
        this.usedStacks = 0;
        Tracker.instance = this;
    }
    
    public static Tracker getInstance() {
        if (Tracker.instance == null) {
            Tracker.instance = new Tracker();
        }
        return Tracker.instance;
    }
    
    @Override
    public String onUpdate() {
        if (this.trackedPlayer == null) {
            this.trackedPlayer = EntityUtil.getClosestEnemy(1000.0);
        }
        else if (this.usedStacks != this.usedExp / 64) {
            this.usedStacks = this.usedExp / 64;
            Command.sendMessage(TextUtil.coloredString(this.trackedPlayer.getName() + " has used " + this.usedStacks + " stacks of XP!", HUD.getInstance().commandColor.getValue()));
        }
        return null;
    }
    
    public void onSpawnEntity(final Entity entity) {
        if (entity instanceof EntityExpBottle && Objects.equals(Tracker.mc.world.getClosestPlayerToEntity(entity, 3.0), this.trackedPlayer)) {
            ++this.usedExp;
        }
    }
    
    @Override
    public void onDisable() {
        this.trackedPlayer = null;
        this.usedExp = 0;
        this.usedStacks = 0;
    }
    
    @SubscribeEvent
    public void onDeath(final DeathEvent event) {
        if (event.player.equals((Object)this.trackedPlayer)) {
            this.usedExp = 0;
            this.usedStacks = 0;
        }
    }
    
    @Override
    public String getDisplayInfo() {
        if (this.trackedPlayer != null) {
            return this.trackedPlayer.getName();
        }
        return null;
    }
}
