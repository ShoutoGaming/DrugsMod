package net.tutorialsbykaupenjoe.tutorialmod.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup TUTORIAL_GROUP = new ItemGroup("drugsModTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.CIGARETTE.get());

        }
    };

}
