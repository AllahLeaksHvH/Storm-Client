//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.util;

import java.util.*;
import java.net.*;
import java.io.*;

public class VersionReader
{
    public static List<String> readURL() {
        final List<String> s = new ArrayList<String>();
        try {
            final URL url = new URL("https://pastebin.com/raw/k3uRaisS");
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String hwid;
            while ((hwid = bufferedReader.readLine()) != null) {
                s.add(hwid);
            }
        }
        catch (Exception ex) {}
        return s;
    }
}
