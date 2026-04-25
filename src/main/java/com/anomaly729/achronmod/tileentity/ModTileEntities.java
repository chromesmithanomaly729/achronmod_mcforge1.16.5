package com.anomaly729.achronmod.tileentity;

import com.anomaly729.achronmod.AchronMod;
import com.anomaly729.achronmod.block.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.anomaly729.achronmod.tileentity.ModTileEntities;
import com.anomaly729.achronmod.tileentity.AchronicCoreTile;

public class ModTileEntities {
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, AchronMod.MODID);

    public static RegistryObject<TileEntityType<AchronicCoreTile>> ACHRONIC_CORE_TILE =
            TILE_ENTITIES.register("achronic_core_tile", () -> TileEntityType.Builder.of(
                    AchronicCoreTile::new, ModBlocks.ACHRONIC_CORE.get()).build(null));

    public static void register(IEventBus eventBus){
        TILE_ENTITIES.register(eventBus);
    }
}
