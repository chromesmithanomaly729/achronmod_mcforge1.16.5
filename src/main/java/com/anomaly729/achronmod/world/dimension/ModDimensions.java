package com.anomaly729.achronmod.world.dimension;

import com.anomaly729.achronmod.AchronMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Dimension;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryManager;

public class ModDimensions {
    //Guess what? KaupenJoe too.
    //Remember to commit this to GitHub after dimension is "functional".
    public static ResourceLocation AchronVortexDimLocation = new ResourceLocation(AchronMod.MODID, "achronvortexdim");
    public static RegistryKey<World> AchronVortexDim = RegistryKey.create(Registry.DIMENSION_REGISTRY, AchronVortexDimLocation);
}
