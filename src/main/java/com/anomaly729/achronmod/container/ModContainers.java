package com.anomaly729.achronmod.container;//package com.anomaly729.achronmod.AchronMod.container;

import com.anomaly729.achronmod.AchronMod;
//import com.anomaly729.achronmod.container.AchronicCoreContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static DeferredRegister<ContainerType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, AchronMod.MODID);

    public static final RegistryObject<ContainerType<AchronicCoreContainer>> ACHRONIC_CORE_CONTAINER
            = CONTAINERS.register("achronic_core_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.level;
                return new AchronicCoreContainer(windowId, world, pos, inv, inv.player);
            })));


    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}