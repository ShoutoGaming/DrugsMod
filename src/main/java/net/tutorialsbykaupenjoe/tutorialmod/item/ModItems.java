package net.tutorialsbykaupenjoe.tutorialmod.item;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;
import net.tutorialsbykaupenjoe.tutorialmod.item.Custom.*;


public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);


   // public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst",
       //     () -> new Item(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> CIGARETTE  = ITEMS.register("cigarette",
            () -> new CigaretteItem(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> MARIHUANA  = ITEMS.register("marihuana",
            () -> new MarihuanaItem(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> COCAINA  = ITEMS.register("cocaina",
            () -> new CocainaItem(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> METANFETAMINA  = ITEMS.register("metanfetamina",
            () -> new MetanfetaminaItem(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> FENTANILO  = ITEMS.register("fentanilo",
            () -> new FentaniloItem(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> HONGOSALUCINOGENOS  = ITEMS.register("hongosalucinogenos",
            () -> new HongosItem(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static final RegistryObject<Item> VIUDA  = ITEMS.register("viuda",
            () -> new ViudanegraItem(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
