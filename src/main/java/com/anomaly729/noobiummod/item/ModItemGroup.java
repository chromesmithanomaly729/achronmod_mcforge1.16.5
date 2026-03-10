package com.anomaly729.noobiummod.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
//import com.example.examplemod.item.ModItems;

public class ModItemGroup {
    public static final ItemGroup NOOBIUM_MOD_ITEM_GROUP = new ItemGroup("NoobiumModTab") {
        @Override
        public ItemStack makeIcon() {
            //return null;
            return new ItemStack(ModItems.NOOBIUM_INGOT.get());
        }
    };
}
