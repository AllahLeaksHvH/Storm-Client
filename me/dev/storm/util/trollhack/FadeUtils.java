//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\neafr\Downloads\Minecraft-Deobfuscator3000-1.2.3\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package me.dev.storm.util.trollhack;

public class FadeUtils
{
    protected long start;
    protected long length;
    
    public double getFadeOutDefault() {
        return 1.0 - Math.tanh(this.getTime() / (double)this.length * 3.0);
    }
    
    public void reset() {
        this.start = System.currentTimeMillis();
    }
    
    protected long getTime() {
        return System.currentTimeMillis() - this.start;
    }
    
    private double getFadeOne() {
        return this.isEnd() ? 1.0 : (this.getTime() / (double)this.length);
    }
    
    public double def() {
        return this.isEnd() ? 1.0 : this.getFadeOne();
    }
    
    public FadeUtils(final long l) {
        this.length = l;
        this.reset();
    }
    
    public double easeInQuad() {
        return this.getFadeOne() * this.getFadeOne();
    }
    
    public boolean isEnd() {
        return this.getTime() >= this.length;
    }
    
    public void setLength(final long l) {
        this.length = l;
    }
    
    public double getEpsEzFadeIn() {
        return 1.0 - Math.sin(1.5707963267948966 * this.getFadeOne()) * Math.sin(2.5132741228718345 * this.getFadeOne());
    }
    
    public double easeOutQuad() {
        return 1.0 - (1.0 - this.getFadeOne()) * (1.0 - this.getFadeOne());
    }
    
    public double getEpsEzFadeOut() {
        return Math.sin(1.5707963267948966 * this.getFadeOne()) * Math.sin(2.5132741228718345 * this.getFadeOne());
    }
    
    public double getFadeInDefault() {
        return Math.tanh(this.getTime() / (double)this.length * 3.0);
    }
}
