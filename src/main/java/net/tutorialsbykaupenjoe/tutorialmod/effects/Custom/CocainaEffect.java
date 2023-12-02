package net.tutorialsbykaupenjoe.tutorialmod.effects.Custom;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "tutorialmod", value = Dist.CLIENT)
public class CocainaEffect extends Effect {
    protected CocainaEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        Minecraft.getInstance().enqueue(() -> {
            if (entityLivingBaseIn.isPotionActive(this)) {
                // Carga el shader de desenfoque si el efecto de la poción está activo
                ResourceLocation shader = new ResourceLocation("tutorialmod", "shaders/post/transparency.json");
                Minecraft.getInstance().gameRenderer.loadShader(shader);
            } else {
                // Carga el shader predeterminado si el efecto de la poción no está activo
                Minecraft.getInstance().gameRenderer.stopUseShader();
            }
        });
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
