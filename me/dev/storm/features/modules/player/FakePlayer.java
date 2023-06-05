//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.player;

import me.dev.storm.features.modules.*;
import net.minecraft.client.entity.*;
import java.net.*;
import java.nio.charset.*;
import org.apache.commons.io.*;
import com.google.gson.*;
import java.util.*;
import com.mojang.authlib.*;
import net.minecraft.world.*;
import me.dev.storm.features.command.*;
import net.minecraft.entity.*;
import net.minecraft.client.multiplayer.*;

public class FakePlayer extends Module
{
    private final String name = "nigga";
    private EntityOtherPlayerMP _fakePlayer;
    
    public FakePlayer() {
        super("FakePlayer", "Spawns a FakePlayer for testing", Module.Category.PLAYER, false, false, false);
    }
    
    public static String getUuid(final String name) {
        final JsonParser parser = new JsonParser();
        final String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
        try {
            final String UUIDJson = IOUtils.toString(new URL(url), StandardCharsets.UTF_8);
            if (UUIDJson.isEmpty()) {
                return "invalid name";
            }
            final JsonObject UUIDObject = (JsonObject)parser.parse(UUIDJson);
            return reformatUuid(UUIDObject.get("id").toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    
    private static String reformatUuid(final String uuid) {
        String longUuid = "";
        longUuid = longUuid + uuid.substring(1, 9) + "-";
        longUuid = longUuid + uuid.substring(9, 13) + "-";
        longUuid = longUuid + uuid.substring(13, 17) + "-";
        longUuid = longUuid + uuid.substring(17, 21) + "-";
        longUuid += uuid.substring(21, 33);
        return longUuid;
    }
    
    public void onEnable() {
        if (fullNullCheck()) {
            this.disable();
            return;
        }
        this._fakePlayer = null;
        if (FakePlayer.mc.player != null) {
            try {
                final WorldClient world = FakePlayer.mc.world;
                this.getClass();
                final UUID fromString = UUID.fromString(getUuid("nigga"));
                this.getClass();
                this._fakePlayer = new EntityOtherPlayerMP((World)world, new GameProfile(fromString, "nigga"));
            }
            catch (Exception e) {
                final WorldClient world2 = FakePlayer.mc.world;
                final UUID fromString2 = UUID.fromString("70ee432d-0a96-4137-a2c0-37cc9df67f03");
                this.getClass();
                this._fakePlayer = new EntityOtherPlayerMP((World)world2, new GameProfile(fromString2, "nigga"));
                Command.sendMessage("Failed to load uuid, setting another one.");
            }
            final String format = "%s has been spawned.";
            final Object[] args = { null };
            final int n = 0;
            this.getClass();
            args[n] = "nigga";
            Command.sendMessage(String.format(format, args));
            this._fakePlayer.copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
            this._fakePlayer.rotationYawHead = FakePlayer.mc.player.rotationYawHead;
            FakePlayer.mc.world.addEntityToWorld(-100, (Entity)this._fakePlayer);
        }
    }
    
    public void onDisable() {
        if (FakePlayer.mc.world != null && FakePlayer.mc.player != null) {
            super.onDisable();
            FakePlayer.mc.world.removeEntity((Entity)this._fakePlayer);
        }
    }
    
    public String getDisplayInfo() {
        return "nigga";
    }
}
