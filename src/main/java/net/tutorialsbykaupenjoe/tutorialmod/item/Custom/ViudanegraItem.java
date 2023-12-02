package net.tutorialsbykaupenjoe.tutorialmod.item.Custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.tutorialsbykaupenjoe.tutorialmod.effects.Custom.ModEffects;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModItemGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ViudanegraItem extends Item {
    private static final Map<UUID, Integer> playerEffects = new HashMap<>();
    private static final Map<UUID, World> playerWorlds = new HashMap<>();

    public ViudanegraItem(Properties group) {
        super(new Item.Properties()
                .group(ModItemGroup.TUTORIAL_GROUP)
                .food(new Food.Builder()
                        .hunger(6)  // Cantidad de hambre restaurada
                        .saturation(0.6f)  // Nivel de saturación
                        .setAlwaysEdible()  // Permite comer incluso si no tienes hambre
                        .build())
        );
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;  // Duración del efecto en ticks (32 ticks ≈ 1.6 segundos)
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (!worldIn.isRemote && entityLiving instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) entityLiving;
            playerEntity.addPotionEffect(new EffectInstance(ModEffects.VIUDANEGRA_EFFECT.get(), 1200, 0));
            playerEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 1200, 0));
            UUID playerId = playerEntity.getUniqueID();
            playerEffects.put(playerId, 1200);
            playerWorlds.put(playerId, worldIn);
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ServerTickEvent event) {
        playerEffects.entrySet().removeIf(entry -> {
            UUID playerId = entry.getKey();
            int ticksLeft = entry.getValue();
            if (ticksLeft <= 0) {
                World world = playerWorlds.get(playerId);
                if (world instanceof ServerWorld) {
                    PlayerEntity player = ((ServerWorld) world).getPlayerByUuid(playerId);
                    if (player != null) {
                        player.addPotionEffect(new EffectInstance(Effects.POISON, 1200, 0, false, false, true));  // Veneno durante 1 minuto, sin partículas, sin icono, pero se muestra en el inventario
                    }
                }
                playerWorlds.remove(playerId);
                return true;
            } else {
                entry.setValue(ticksLeft - 1);
                return false;
            }
        });
    }
}

