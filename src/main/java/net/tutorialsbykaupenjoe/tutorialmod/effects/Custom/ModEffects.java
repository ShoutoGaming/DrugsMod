package net.tutorialsbykaupenjoe.tutorialmod.effects.Custom;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, "tutorialmod");

    public static final RegistryObject<CigaretteEffect> CIGARETTE_EFFECT = EFFECTS.register("cigarette_effect", () ->
            new CigaretteEffect(EffectType.HARMFUL, 0xFF0000));

    public static final RegistryObject<CocainaEffect> COCAINA_EFFECT = EFFECTS.register("cocaina_effect", () ->
            new CocainaEffect(EffectType.HARMFUL, 0xFF0000));

    public static final RegistryObject<FentaniloEffect> FENTANILO_EFFECT = EFFECTS.register("fentanilo_effect", () ->
            new FentaniloEffect(EffectType.HARMFUL, 0xFF69B4));

    public static final RegistryObject<HongosEffect> HONGOS_EFFECT = EFFECTS.register("hongos_effect", () ->
            new HongosEffect(EffectType.HARMFUL, 0xFF69B4));

    public static final RegistryObject<MarihuanaEffect> MARIHUANA_EFFECT = EFFECTS.register("marihuana_effect", () ->
            new MarihuanaEffect(EffectType.HARMFUL, 0xFF69B4));

    public static final RegistryObject<MetanfetaminaEffect> METANFETAMINA_EFFECT = EFFECTS.register("metanfetamina_effect", () ->
            new MetanfetaminaEffect(EffectType.HARMFUL, 0xFF69B4));

    public static final RegistryObject<ViudanegraEffect> VIUDANEGRA_EFFECT = EFFECTS.register("viudanegra_effect", () ->
            new ViudanegraEffect(EffectType.HARMFUL, 0xFF69B4));
    public static void registerEffects(IEventBus modEventBus) {
        // Corregir la llamada al método register
        EFFECTS.register(modEventBus);
    }

    private static <T extends Effect> RegistryObject<T> createEffect(String name, T effect) {
        return RegistryObject.of(new ResourceLocation("drugsmod", name), ForgeRegistries.POTIONS);
    }
}
