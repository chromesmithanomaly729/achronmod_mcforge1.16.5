package com.anomaly729.noobiummod.block;

import com.anomaly729.noobiummod.NoobiumMod;
import com.anomaly729.noobiummod.item.ModItemGroup;
import com.anomaly729.noobiummod.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, NoobiumMod.MODID);


    public static final RegistryObject<Block> NOOBIUM_ORE =
            registerBlock("noobium_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(5f)));

    public static final RegistryObject<Block> NOOBIUM_BLOCK =
            registerBlock("noobium_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(13f)));


    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ModItemGroup.NOOBIUM_MOD_ITEM_GROUP)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
