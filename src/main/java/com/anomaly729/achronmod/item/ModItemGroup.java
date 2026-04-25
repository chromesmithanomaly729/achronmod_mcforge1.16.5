package com.anomaly729.achronmod.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
//import com.example.examplemod.item.ModItems;

public class ModItemGroup {
    public static final ItemGroup ACHRON_MOD_ITEM_GROUP = new ItemGroup("AchronModTab") {
        @Override
        public ItemStack makeIcon() {
            //return null;
            return new ItemStack(ModItems.ACHRON_INGOT.get());
        }
    };
}
