package net.tutorialsbykaupenjoe.tutorialmod.item.Custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import net.tutorialsbykaupenjoe.tutorialmod.effects.Custom.ModEffects;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModItemGroup;

public class CigaretteItem extends Item {
    private static final String USO_KEY = "Uso";

    public CigaretteItem(Properties group) {
        super(new Item.Properties()
                .group(ModItemGroup.TUTORIAL_GROUP)
                .food(new Food.Builder()
                        .hunger(4)
                        .saturation(0.3f)
                        .setAlwaysEdible()
                        .build())
                .maxDamage(4));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (!worldIn.isRemote && entityLiving instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) entityLiving;
            playerEntity.addPotionEffect(new EffectInstance(ModEffects.CIGARETTE_EFFECT.get(), 200, 1));  // Usar la instancia del efecto directamente
            int uso = getUso(stack);
            uso = (uso + 1) % 4;
            setUso(stack, uso);
            if (uso == 0) {
                return super.onItemUseFinish(stack, worldIn, entityLiving);
            }
        }
        return stack;
    }

    public static int getUso(ItemStack stack) {
        return stack.getOrCreateTag().getInt(USO_KEY);
    }

    public static void setUso(ItemStack stack, int uso) {
        stack.getOrCreateTag().putInt(USO_KEY, uso);
    }

    @Override
    public int getDamage(ItemStack stack) {
        return getUso(stack);
    }
}
