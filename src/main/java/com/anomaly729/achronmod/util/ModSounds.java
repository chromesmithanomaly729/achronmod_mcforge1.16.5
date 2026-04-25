package com.anomaly729.achronmod.util;

import com.anomaly729.achronmod.AchronMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AchronMod.MODID);

    public static final RegistryObject<SoundEvent> ACHRON_CONVERTER_USAGE_SOUND = registerSound("achron_from_iron_converter");
    public static final RegistryObject<SoundEvent> RIFT_OBSIDIAN_USAGE_SOUND = registerSound("rift_obsidian_usage_sound");

    private static RegistryObject<SoundEvent> registerSound(String name){
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(AchronMod.MODID, name)));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
