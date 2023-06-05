//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.features.modules.misc;

import me.dev.storm.features.modules.*;
import me.dev.storm.features.setting.*;
import java.util.*;
import net.minecraft.entity.player.*;

public class WhisperSpam extends Module
{
    public static Setting<ChatModes> chatModes;
    public final Setting<Integer> delay;
    List<String> chants;
    Random r;
    int tick_delay;
    
    public WhisperSpam() {
        super("WhisperSpam", "creep.", Category.MISC, true, false, false);
        this.delay = (Setting<Integer>)this.register(new Setting("SpamDelay", (T)10, (T)0, (T)100));
        this.chants = new ArrayList<String>();
        this.r = new Random();
        WhisperSpam.chatModes = (Setting<ChatModes>)this.register(new Setting("TYPE", (T)ChatModes.fascist));
    }
    
    @Override
    public void onEnable() {
        this.tick_delay = 0;
        this.chants.clear();
        if (WhisperSpam.chatModes.getValue() == ChatModes.fascist) {
            this.chants.clear();
            this.chants.add("/w <player> PICK MY COTTON NIGGER");
            this.chants.add("/w <player> BLACK NIGGER COON");
            this.chants.add("/w <player> KIKE");
            this.chants.add("/w <player> I <3 THE CONFEDERACY");
            this.chants.add("/w <player> NIGGERS BELONG ON THE BACK OF THE BUS");
            this.chants.add("/w <player> KICK THAT NIGGER BITCH OFF THE PLANE!!");
            this.chants.add("/w <player> DEATH TO THE KIKES");
            this.chants.add("/w <player> HEIL HITLER");
            this.chants.add("/w <player> I <3 THE KKK");
            this.chants.add("/w <player> #FREEDEREKCHAUVIN");
            this.chants.add("/w <player> I HATE JEWS, FAGGOTS, NIGGERS, AND SPICS");
        }
        else if (WhisperSpam.chatModes.getValue() == ChatModes.groomer) {
            this.chants.clear();
            this.chants.add("/w <player> heyyyyy");
            this.chants.add("/w <player> how old are you??");
            this.chants.add("/w <player> you look like such a big boy...");
            this.chants.add("/w <player> hi cutie");
            this.chants.add("/w <player> can i fart in your mouth");
            this.chants.add("/w <player> can i be your little sussy baka??");
            this.chants.add("/w <player> whats my kitten doing talking to another man?? so sussy...");
            this.chants.add("/w <player> ill touch you inappropriately with a car exhaust pipe.");
            this.chants.add("/w <player> we can play nekopara in bachi's office together my kitten ");
            this.chants.add("/w <player> age is just a number my kitten");
        }
        else if (WhisperSpam.chatModes.getValue() == ChatModes.bomber) {
            this.chants.clear();
            this.chants.add("/w <player> ALLAHU AKBAR");
            this.chants.add("/w <player> HARAM PIGGIES DIE FOR ALLAH");
            this.chants.add("/w <player> #FREEPALESTINE");
            this.chants.add("/w <player> BISMILLAH AL RAHMAN AL RAHIM");
            this.chants.add("/w <player> MUHAMMAD PEACE BE UPON HIM");
            this.chants.add("/w <player> I PRAY TO ALLAH 5 TIMES A DAY");
            this.chants.add("/w <player> DEATH TO STINKY HARAM INFIDELS");
            this.chants.add("/w <player> PORK = HARAM");
        }
        else if (WhisperSpam.chatModes.getValue() == ChatModes.christmas) {
            this.chants.clear();
            this.chants.add("/w <player> Happy Holidays!");
            this.chants.add("/w <player> Hope you get cool presents for christmas!");
            this.chants.add("/w <player> #25");
            this.chants.add("/w <player> You should get TrollHack.win for Christmas!");
            this.chants.add("/w <player> Have a cool snowy day!");
            this.chants.add("/w <player> Don't get a frostbite!");
            this.chants.add("/w <player> So many candy canes my wrist go numb.");
            this.chants.add("/w <player> Jesus is not a god but a messanger!");
        }
        else if (WhisperSpam.chatModes.getValue() == ChatModes.newYear) {
            this.chants.clear();
            this.chants.add("/w <player> Happy New Year 2023!");
            this.chants.add("/w <player> Hope you have fun in this new era of your life !");
            this.chants.add("/w <player> #NewYear2023");
            this.chants.add("/w <player> You should get TrollHack.win to celebrate New Years!!");
            this.chants.add("/w <player> Have a cool new day!");
            this.chants.add("/w <player> Change your underware, it is a New Year you stinky fuck!");
            this.chants.add("/w <player> New Years Resolution: Quit anarchy!");
            this.chants.add("/w <player> Get up and take a shower now! It has been an year!");
        }
    }
    
    @Override
    public String onUpdate() {
        ++this.tick_delay;
        if (this.tick_delay < this.delay.get_value(1) * 10) {
            return null;
        }
        final String s = this.chants.get(this.r.nextInt(this.chants.size()));
        final String name = this.get_random_name();
        if (name.equals(WhisperSpam.mc.player.getName())) {
            return s;
        }
        WhisperSpam.mc.player.sendChatMessage(s.replace("<player>", name));
        this.tick_delay = 0;
        return s;
    }
    
    public String get_random_name() {
        final List<EntityPlayer> players = (List<EntityPlayer>)WhisperSpam.mc.world.playerEntities;
        return players.get(this.r.nextInt(players.size())).getName();
    }
    
    public enum ChatModes
    {
        fascist, 
        bomber, 
        christmas, 
        newYear, 
        groomer;
    }
}
