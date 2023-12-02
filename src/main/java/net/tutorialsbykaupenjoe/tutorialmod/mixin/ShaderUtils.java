package net.tutorialsbykaupenjoe.tutorialmod.mixin;


import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import net.tutorialsbykaupenjoe.tutorialmod.gui.ShaderList;

import java.io.IOException;

public class ShaderUtils {
    public static Minecraft client = Minecraft.getInstance();
    public static ShaderGroup shader;
    public static boolean enabled = false;

    private static ShaderGroup getCurrent(boolean d) {
        ShaderList s;
        if(d)
            s = ShaderList.next();
        else
            s = ShaderList.previous();
        if(s.getId() == -1)
            return null;
        else {
            try {
                return new ShaderGroup(client.getTextureManager(), client.getResourceManager(), client.getFramebuffer(), s.getResource());
            } catch (IOException e) {
                return null;
            }
        }
    }

    public static void load(boolean d) {
        if(shader != null)
            shader.close();
        shader = getCurrent(d);
        if(shader != null) {
            shader.createBindFramebuffers(client.getMainWindow().getScaledWidth(), client.getMainWindow().getScaledHeight());
            enabled = true;
            return;
        }
        enabled = false;
    }

}
