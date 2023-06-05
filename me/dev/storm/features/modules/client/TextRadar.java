//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.client;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import me.dev.storm.event.events.*;
import net.minecraft.entity.player.*;
import java.text.*;
import java.math.*;
import me.dev.storm.util.*;
import net.minecraft.entity.*;
import com.mojang.realmsclient.gui.*;
import me.dev.storm.*;
import java.util.*;

public class TextRadar extends Module
{
    private final Setting<Integer> amount;
    public Setting<Integer> Y;
    public static TextRadar INSTANCE;
    private int color;
    
    public TextRadar() {
        super("TextRadar", "Shows players in render distance on hud", Category.CLIENT, false, false, false);
        this.amount = (Setting<Integer>)this.register(new Setting("PlayerCount", (T)10, (T)1, (T)100));
        this.Y = (Setting<Integer>)this.register(new Setting("Y", (T)5, (T)0, (T)550));
    }
    
    @Override
    public void onRender2D(final Render2DEvent event) {
        int i = 0;
        for (final Object o : TextRadar.mc.world.loadedEntityList) {
            if (o instanceof EntityPlayer && o != TextRadar.mc.player) {
                if (++i > this.amount.getValue()) {
                    return;
                }
                final EntityPlayer entity = (EntityPlayer)o;
                final float health = (float)(Math.round(entity.getHealth()) + Math.round(entity.getAbsorptionAmount()));
                final DecimalFormat dfDistance = new DecimalFormat("#.#");
                dfDistance.setRoundingMode(RoundingMode.CEILING);
                final StringBuilder distanceSB = new StringBuilder();
                this.color = ColorUtil.toRGBA(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue());
                String health_str = "[" + health + "]";
                health_str = health_str.replace(".0", "");
                final int distanceInt = (int)EntityUtil.mc.player.getDistance((Entity)entity);
                final String distance = dfDistance.format(distanceInt);
                if (distanceInt >= 25) {
                    distanceSB.append(ChatFormatting.GREEN);
                }
                else if (distanceInt > 10) {
                    distanceSB.append(ChatFormatting.GOLD);
                }
                else if (distanceInt >= 50) {
                    distanceSB.append(ChatFormatting.GRAY);
                }
                else {
                    distanceSB.append(ChatFormatting.RED);
                }
                distanceSB.append(distance);
                String heal;
                if (health >= 12.0) {
                    heal = " " + ChatFormatting.GREEN + health_str + "";
                }
                else if (health >= 4.0) {
                    heal = " " + ChatFormatting.YELLOW + health_str + "";
                }
                else {
                    heal = " " + ChatFormatting.RED + health_str + "";
                }
                final String name = entity.getGameProfile().getName();
                final String str = heal + " " + ChatFormatting.RESET;
                if (Storm.friendManager.isFriend(entity.getName())) {
                    Storm.textManager.drawString(str + ChatFormatting.AQUA + name + " " + distanceSB.toString() + ChatFormatting.WHITE, -2.0f, (float)(this.Y.getValue() + i * 10), this.color, true);
                }
                else if (ClickGui.getInstance().rainbow.getValue() && ClickGui.getInstance().rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                    Storm.textManager.drawString(str + name + " " + distanceSB.toString() + ChatFormatting.GRAY + "", -2.0f, (float)(this.Y.getValue() + i * 10), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                }
                else {
                    Storm.textManager.drawString(str + name + " " + distanceSB.toString() + ChatFormatting.GRAY + "", -2.0f, (float)(this.Y.getValue() + i * 10), this.color, true);
                }
            }
        }
    }
    
    static {
        TextRadar.INSTANCE = new TextRadar();
    }
}
