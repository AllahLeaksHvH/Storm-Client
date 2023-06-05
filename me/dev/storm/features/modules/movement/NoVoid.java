//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.movement;

import me.dev.storm.features.modules.*;
import net.minecraft.util.math.*;

public class NoVoid extends Module
{
    public NoVoid() {
        super("NoVoid", "Glitches you up from void.", Module.Category.MOVEMENT, false, false, false);
    }
    
    public String onUpdate() {
        if (fullNullCheck()) {
            return null;
        }
        if (!NoVoid.mc.player.noClip && NoVoid.mc.player.posY <= 0.0) {
            final RayTraceResult trace = NoVoid.mc.world.rayTraceBlocks(NoVoid.mc.player.getPositionVector(), new Vec3d(NoVoid.mc.player.posX, 0.0, NoVoid.mc.player.posZ), false, false, false);
            if (trace != null && trace.typeOfHit == RayTraceResult.Type.BLOCK) {
                return null;
            }
            NoVoid.mc.player.setVelocity(0.0, 0.0, 0.0);
            if (NoVoid.mc.player.getRidingEntity() != null) {
                NoVoid.mc.player.getRidingEntity().setVelocity(0.0, 0.0, 0.0);
            }
        }
        return null;
    }
}
