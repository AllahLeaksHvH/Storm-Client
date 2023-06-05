//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.manager;

import java.util.*;

public final class FpsManager
{
    private static int fps;
    private final LinkedList<Long> frames;
    
    public FpsManager() {
        this.frames = new LinkedList<Long>();
    }
    
    public void update() {
        final long time = System.nanoTime();
        this.frames.add(time);
        while (true) {
            final long f = this.frames.getFirst();
            final long ONE_SECOND = 1000000000L;
            if (time - f <= 1000000000L) {
                break;
            }
            this.frames.remove();
        }
        FpsManager.fps = this.frames.size();
    }
    
    public static int getFPS() {
        return FpsManager.fps;
    }
    
    public float getFrametime() {
        return 0.004166667f;
    }
}
