package com.anomaly729.achronmod.item;

import com.anomaly729.achronmod.AchronMod;
import com.anomaly729.achronmod.item.custom.AchronFromIronConverterItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.item.Item;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AchronMod.MODID);

    public static final RegistryObject<Item> ACHRON_INGOT = ITEMS.register("achron_ingot", () -> new Item(new Item.Properties().tab(ModItemGroup.ACHRON_MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> ACHRON_FROM_IRON_CONVERTER = ITEMS.register("achron_from_iron_converter", () -> new AchronFromIronConverterItem(new Item.Properties().tab(ModItemGroup.ACHRON_MOD_ITEM_GROUP)));
    //raw noobium(achron) not added 'cause 1.16.5 uses the ore blocks lol

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
