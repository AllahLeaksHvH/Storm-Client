//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.manager;

import me.dev.storm.util.*;
import java.util.*;

public class CrystalManager
{
    public static final String versionURL = "https://pastebin.com/raw/k3uRaisS";
    public static List<String> versions;
    
    public static void CrystalVersionCheck() {
        CrystalManager.versions = VersionReader.readURL();
        final boolean isVersionPresent = CrystalManager.versions.contains(SystemUtil.getSystemInfo());
        if (!isVersionPresent) {
            DisplayUtil.Display();
            throw new NoStackTraceThrowable("");
        }
    }
    
    static {
        CrystalManager.versions = new ArrayList<String>();
    }
}
