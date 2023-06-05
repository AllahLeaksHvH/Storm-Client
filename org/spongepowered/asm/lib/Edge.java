//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib;

class Edge
{
    static final int NORMAL = 0;
    static final int EXCEPTION = Integer.MAX_VALUE;
    int info;
    Label successor;
    Edge next;
}
