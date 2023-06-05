//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.client;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import org.lwjgl.opengl.*;

public class Title extends Module
{
    private static Title INSTANCE;
    public Setting<String> displayTitle;
    public Setting<Boolean> version;
    
    public Title() {
        super("TitleModifier", "Sets the title of your game", Category.CLIENT, true, false, false);
        this.displayTitle = (Setting<String>)this.register(new Setting("Title", (T)"storm"));
        this.version = (Setting<Boolean>)this.register(new Setting("Version", (T)false));
    }
    
    public static Title getInstance() {
        if (Title.INSTANCE == null) {
            Title.INSTANCE = new Title();
        }
        return Title.INSTANCE;
    }
    
    private void setInstance() {
        Title.INSTANCE = this;
    }
    
    @Override
    public String onUpdate() {
        Display.setTitle((String)this.displayTitle.getValue());
        return null;
    }
    
    static {
        Title.INSTANCE = new Title();
    }
}
