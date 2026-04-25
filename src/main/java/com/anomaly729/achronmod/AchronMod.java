package com.anomaly729.achronmod;

import com.anomaly729.achronmod.block.ModBlocks;
import com.anomaly729.achronmod.container.ModContainers;
import com.anomaly729.achronmod.item.ModItems;
import com.anomaly729.achronmod.screen.AchronicCoreScreen;
import com.anomaly729.achronmod.tileentity.ModTileEntities;
import com.anomaly729.achronmod.util.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

//------------------
//GLORY TO KAUPENJOE
//------------------


// The value here should match an entry in the META-INF/mods.toml file
@Mod(AchronMod.MODID)
//public static final string MODID = "examplemod";
public class AchronMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "achronmod";
    //private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    //public static final RegistryObject<Block> ROCK_BLOCK = BLOCKS.register("rock", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));

    public AchronMod() {
        IEventBus fml_context_event_bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(fml_context_event_bus);
        ModBlocks.register(fml_context_event_bus);
        ModSounds.register(fml_context_event_bus);
        ModContainers.register(fml_context_event_bus);
        ModTileEntities.register(fml_context_event_bus);

        // Register the setup method for modloading
        fml_context_event_bus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        fml_context_event_bus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        fml_context_event_bus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        fml_context_event_bus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        //BLOCKS.register(context.getModEventBus());

    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);


        //register screens here
        ScreenManager.register(ModContainers.ACHRONIC_CORE_CONTAINER.get(),
                AchronicCoreScreen::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("achronmod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    //ADDED FUNCTION, MIGHT NOT WORK AKNMSDJKANSJDBNASJDNASNDASDNASDSADASDASDASDASDANJKXNCUASJD
    //private void addCreative(buildCreativeModeTabContentsEvent event){
    //    if(event.getTabKey() == CreativeModeTabs.INGREDIENTS)
    //}
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
