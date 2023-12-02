package net.tutorialsbykaupenjoe.tutorialmod.item.Custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.tutorialsbykaupenjoe.tutorialmod.effects.Custom.ModEffects;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModItemGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HongosItem extends Item {
    private static final Map<UUID, Integer> playerEffects = new HashMap<>();
    private static final Map<UUID, World> playerWorlds = new HashMap<>();

    public HongosItem(Properties group) {
        super(new Properties()
                .group(ModItemGroup.TUTORIAL_GROUP)
                .food(new Food.Builder()
                        .hunger(4)
                        .saturation(0.3f)
                        .setAlwaysEdible()
                        .build())
                .maxDamage(4));
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (!worldIn.isRemote && entityLiving instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) entityLiving;
            playerEntity.addPotionEffect(new EffectInstance(ModEffects.HONGOS_EFFECT.get(), 200, 1));
            playerEntity.addPotionEffect(new EffectInstance(Effects.LUCK, 2400, 0));  // Regeneración II durante 2 minutos
            UUID playerId = playerEntity.getUniqueID();
            playerEffects.put(playerId, 4800);  // Programar los otros efectos para dentro de 4 minutos
            playerWorlds.put(playerId, worldIn);  // Almacenar una referencia al mundo
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
