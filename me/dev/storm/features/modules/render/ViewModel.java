//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.render;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;

public class ViewModel extends Module
{
    public final Setting<Integer> translateX;
    public final Setting<Integer> translateY;
    public final Setting<Integer> translateZ;
    public final Setting<Integer> rotateX;
    public final Setting<Integer> rotateY;
    public final Setting<Integer> rotateZ;
    public final Setting<Integer> scaleX;
    public final Setting<Integer> scaleY;
    public final Setting<Integer> scaleZ;
    public final Setting<Integer> alpha;
    public static ViewModel INSTANCE;
    
    public ViewModel() {
        super("ViewModel", "Modifies the items of your hands client side", Module.Category.RENDER, true, false, false);
        this.translateX = (Setting<Integer>)this.register(new Setting("TranslateX", (T)0, (T)(-200), (T)200));
        this.translateY = (Setting<Integer>)this.register(new Setting("TranslateY", (T)0, (T)(-200), (T)200));
        this.translateZ = (Setting<Integer>)this.register(new Setting("TranslateZ", (T)0, (T)(-200), (T)200));
        this.rotateX = (Setting<Integer>)this.register(new Setting("RotateX", (T)0, (T)(-200), (T)200));
        this.rotateY = (Setting<Integer>)this.register(new Setting("RotateY", (T)0, (T)(-200), (T)200));
        this.rotateZ = (Setting<Integer>)this.register(new Setting("RotateZ", (T)0, (T)(-200), (T)200));
        this.scaleX = (Setting<Integer>)this.register(new Setting("ScaleX", (T)100, (T)0, (T)200));
        this.scaleY = (Setting<Integer>)this.register(new Setting("ScaleY", (T)100, (T)0, (T)200));
        this.scaleZ = (Setting<Integer>)this.register(new Setting("ScaleZ", (T)100, (T)0, (T)200));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)100, (T)0, (T)200));
        ViewModel.INSTANCE = this;
    }
}
