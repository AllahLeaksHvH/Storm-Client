//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.render;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import net.minecraft.entity.player.*;
import java.util.*;

public class SwingAnimation extends Module
{
    public static Setting<AnimationVersion> AnimationsVersion;
    public static Setting<Boolean> playersDisableAnimations;
    public static Setting<Boolean> changeMainhand;
    public static Setting<Float> mainhand;
    public static Setting<Boolean> changeOffhand;
    public static Setting<Float> offhand;
    public static Setting<Integer> changeSwing;
    public static Setting<Integer> swingDelay;
    
    public SwingAnimation() {
        super("SwingModify", "Allows you to change animations in your hand", Module.Category.RENDER, true, false, false);
        SwingAnimation.AnimationsVersion = (Setting<AnimationVersion>)this.register(new Setting("Version", (T)AnimationVersion.OneDotEight));
        SwingAnimation.playersDisableAnimations = (Setting<Boolean>)this.register(new Setting("Disable Animations", (T)false));
        SwingAnimation.changeMainhand = (Setting<Boolean>)this.register(new Setting("Change Mainhand", (T)true));
        SwingAnimation.mainhand = (Setting<Float>)this.register(new Setting("Mainhand", (T)Float.intBitsToFloat(Float.floatToIntBits(4.7509747f) ^ 0x7F1807FC), (T)Float.intBitsToFloat(Float.floatToIntBits(1.63819E38f) ^ 0x7EF67CC9), (T)Float.intBitsToFloat(Float.floatToIntBits(30.789412f) ^ 0x7E7650B7)));
        SwingAnimation.changeOffhand = (Setting<Boolean>)this.register(new Setting("Change Offhand", (T)true));
        SwingAnimation.offhand = (Setting<Float>)this.register(new Setting("Offhand", (T)Float.intBitsToFloat(Float.floatToIntBits(15.8065405f) ^ 0x7EFCE797), (T)Float.intBitsToFloat(Float.floatToIntBits(3.3688825E38f) ^ 0x7F7D7251), (T)Float.intBitsToFloat(Float.floatToIntBits(7.3325067f) ^ 0x7F6AA3E5)));
        SwingAnimation.changeSwing = (Setting<Integer>)this.register(new Setting("Swing Speed", (T)6, (T)0, (T)20));
        SwingAnimation.swingDelay = (Setting<Integer>)this.register(new Setting("Swing Delay", (T)6, (T)1, (T)20));
    }
    
    public String onUpdate() {
        if (SwingAnimation.playersDisableAnimations.getValue()) {
            for (final EntityPlayer player : SwingAnimation.mc.world.playerEntities) {
                player.limbSwing = Float.intBitsToFloat(Float.floatToIntBits(1.8755627E38f) ^ 0x7F0D1A06);
                player.limbSwingAmount = Float.intBitsToFloat(Float.floatToIntBits(6.103741E37f) ^ 0x7E37AD83);
                player.prevLimbSwingAmount = Float.intBitsToFloat(Float.floatToIntBits(4.8253957E37f) ^ 0x7E11357F);
            }
        }
        if (SwingAnimation.changeMainhand.getValue() && SwingAnimation.mc.entityRenderer.itemRenderer.equippedProgressMainHand != SwingAnimation.mainhand.getValue()) {
            SwingAnimation.mc.entityRenderer.itemRenderer.equippedProgressMainHand = SwingAnimation.mainhand.getValue();
            SwingAnimation.mc.entityRenderer.itemRenderer.itemStackMainHand = SwingAnimation.mc.player.getHeldItemMainhand();
        }
        if (SwingAnimation.changeOffhand.getValue() && SwingAnimation.mc.entityRenderer.itemRenderer.equippedProgressOffHand != SwingAnimation.offhand.getValue()) {
            SwingAnimation.mc.entityRenderer.itemRenderer.equippedProgressOffHand = SwingAnimation.offhand.getValue();
            SwingAnimation.mc.entityRenderer.itemRenderer.itemStackOffHand = SwingAnimation.mc.player.getHeldItemOffhand();
        }
        if (SwingAnimation.AnimationsVersion.getValue() == AnimationVersion.OneDotEight && SwingAnimation.mc.entityRenderer.itemRenderer.prevEquippedProgressMainHand >= 0.9) {
            SwingAnimation.mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
            SwingAnimation.mc.entityRenderer.itemRenderer.itemStackMainHand = SwingAnimation.mc.player.getHeldItemMainhand();
        }
        return null;
    }
    
    public enum AnimationVersion
    {
        OneDotEight, 
        OneDotTwelve;
    }
}
