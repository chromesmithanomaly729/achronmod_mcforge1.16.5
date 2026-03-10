package com.anomaly729.noobiummod.item;

import com.anomaly729.noobiummod.NoobiumMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.item.Item;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, NoobiumMod.MODID);

    public static final RegistryObject<Item> NOOBIUM_INGOT = ITEMS.register("noobium_ingot", () -> new Item(new Item.Properties()
            .tab(ModItemGroup.NOOBIUM_MOD_ITEM_GROUP)));
    //public static final RegistryObject<Item> NOOBIUM_ORE_RAW = ITEMS.register("noobium_ore_raw", () -> new Item(new Item.Properties()
    //        .tab(ModItemGroup.NOOBIUM_MOD_ITEM_GROUP)));

    //raw noobium not added 'cause 1.16.5 uses the ore blocks lol

    //add item to register here, ALSO this ain't initialized in the main mod file sooooo... do that yeah.
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
