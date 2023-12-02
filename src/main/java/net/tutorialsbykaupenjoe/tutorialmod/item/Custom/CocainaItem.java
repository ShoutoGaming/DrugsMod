package net.tutorialsbykaupenjoe.tutorialmod.item.Custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class CocainaItem extends Item {
    private static final Map<UUID, Integer> playerEffects = new HashMap<>();
    private static final Map<UUID, World> playerWorlds = new HashMap<>();

    public CocainaItem(Properties group) {
        super(new Item.Properties()
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
            playerEntity.addPotionEffect(new EffectInstance(ModEffects.COCAINA_EFFECT.get(), 200, 1));  // Usar la instancia del efecto directamente
            playerEntity.addPotionEffect(new EffectInstance(Effects.SPEED, 2400, 1));  // Regeneración I durante 2 minutos
            playerEntity.addPotionEffect(new EffectInstance(Effects.HASTE, 2400, 1));  // Regeneración I durante 2 minutos
            UUID playerId = playerEntity.getUniqueID();
            playerEffects.put(playerId, 4800);  // Programar los otros efectos para dentro de 4 minutos
            playerWorlds.put(playerId, worldIn);  // Almacenar una referencia al mundo
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
                        player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 7200, 0, false, false, true));  // Fatiga de minería I durante 2 minutos, sin partículas, sin icono, pero se muestra en el inventario
                        player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 7200, 0, false, false, true));  // Lentitud I durante 2 minutos, sin partículas, sin icono, pero se muestra en el inventario
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
