package com.anomaly729.achronmod.item.custom;

import com.anomaly729.achronmod.util.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class AchronFromIronConverterItem extends Item {

    Map<String, String> conversionDictionary = new HashMap<>();
    ParticleType conversionParticleType = ParticleTypes.END_ROD;
    List<String> conversionKeys;

    RegistryObject<SoundEvent> SuccessfulConversionSound = ModSounds.ACHRON_CONVERTER_USAGE_SOUND;

    //Declare here the blocks the AchronFromIronConverter can convert from, and to.
    public void declareConversionMapping() {
        conversionDictionary.put("minecraft:iron_block", "achronmod:achron_block");
        conversionDictionary.put("minecraft:iron_ore", "achronmod:achron_ore");
        conversionDictionary.put("minecraft:obsidian", "minecraft:crying_obsidian");

        conversionKeys = new ArrayList<>(conversionDictionary.keySet());
        //conversionSet = new ArrayList<String>(conversionDictionary.entrySet());
    }
    public AchronFromIronConverterItem(Properties properties) {
        super(properties);
        declareConversionMapping();
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getLevel();
        PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());

        Boolean conversionSuccess = FALSE;

        if(isConversionPossible(context.getClickedPos(), world, playerEntity)){
            conversionSuccess = TRUE;
        }
        if(!world.isClientSide()) {
            rightClickConvert(context.getClickedPos(), world, playerEntity);
        }else{
            if(conversionSuccess) {
                addConversionParticles(world, context.getClickedPos());
            }
        }
        //playerSendMessage(playerEntity, "Achron Converter was used!");
        //playerSendMessage(playerEntity, Integer.toString(conversionDictionary.size()));

        return super.onItemUseFirst(stack, context);
    }

    public void addConversionParticles(World world, BlockPos pos){
        addParticleCube(world, 5, conversionParticleType, pos.offset(new BlockPos(0.0, 0.0, 0.0)));
    }

    public void playerSendMessage(PlayerEntity player, String text){
        player.sendMessage(new StringTextComponent(text), Util.NIL_UUID);
    }

    public void rightClickConvert(BlockPos blockPos, World world, PlayerEntity playerEntity){
        if(convertBlockAccordingToDictionary(blockPos, world, playerEntity)){
            world.playSound(null, blockPos, ModSounds.ACHRON_CONVERTER_USAGE_SOUND.get(), SoundCategory.BLOCKS,10, 1);
        }
    }

    public void addParticleLine(World world, int particleAmount, ParticleType particleType, Vector3d vectorOne, Vector3d vectorTwo){

        Vector3d vectorPerIter = (vectorTwo.subtract(vectorOne));
        vectorPerIter = new Vector3d(vectorPerIter.x/particleAmount, vectorPerIter.y/particleAmount, vectorPerIter.z/particleAmount);

        for(int i = 0; i < particleAmount; i++){
            world.addParticle((IParticleData) particleType, vectorOne.x+(vectorPerIter.x*i),
                    vectorOne.y+(vectorPerIter.y*i), vectorOne.z+(vectorPerIter.z*i),
                    0.0D, 0.0D, 0.0D);
        }
    }

    public void addParticleCube(World world, int particleAmountPerLine, ParticleType particleType, BlockPos pos) {
        Vector3d vector = Vector3d.atLowerCornerOf(pos);

        //x0 to x1 lines
        addParticleLine(world, particleAmountPerLine, particleType, vector, vector.add(new Vector3d(1.0D, 0.0D, 0.0D)));
        addParticleLine(world, particleAmountPerLine, particleType, vector.add(new Vector3d(0.0, 0.0, 1.0)), vector.add(new Vector3d(1.0D, 0.0D, 1.0D)));
        addParticleLine(world, particleAmountPerLine, particleType, vector.add(new Vector3d(0.0, 1.0, 0.0)), vector.add(new Vector3d(1.0D, 1.0D, 0.0D)));
        addParticleLine(world, particleAmountPerLine, particleType, vector.add(new Vector3d(0.0, 1.0, 1.0)), vector.add(new Vector3d(1.0D, 1.0D, 1.0D)));

        //z0 to z1 lines
        addParticleLine(world, particleAmountPerLine, particleType, vector, vector.add(new Vector3d(0.0D, 0.0D, 1.0D)));
        addParticleLine(world, particleAmountPerLine, particleType, vector.add(new Vector3d(1.0, 0.0, 0.0)), vector.add(new Vector3d(1.0D, 0.0D, 1.0D)));
        addParticleLine(world, particleAmountPerLine, particleType, vector.add(new Vector3d(1.0, 1.0, 0.0)), vector.add(new Vector3d(1.0D, 1.0D, 1.0D)));
        addParticleLine(world, particleAmountPerLine, particleType, vector.add(new Vector3d(0.0, 1.0, 0.0)), vector.add(new Vector3d(0.0D, 1.0D, 1.0D)));

        //y0 to y1 lines
        addParticleLine(world, particleAmountPerLine, particleType, vector, vector.add(new Vector3d(0.0D, 1.0D, 0.0D)));
        addParticleLine(world, particleAmountPerLine, particleType, vector.add(new Vector3d(1.0, 0.0, 0.0)), vector.add(new Vector3d(1.0D, 1.0D, 0.0D)));
        addParticleLine(world, particleAmountPerLine, particleType, vector.add(new Vector3d(1.0, 0.0, 1.0)), vector.add(new Vector3d(1.0D, 1.0D, 1.0D)));
        addParticleLine(world, particleAmountPerLine, particleType, vector.add(new Vector3d(0.0, 0.0, 1.0)), vector.add(new Vector3d(0.0D, 1.0D, 1.0D)));
    }
    public Boolean convertBlockAccordingToDictionary(BlockPos blockPos, World world, PlayerEntity playerEntity){

        BlockState block = world.getBlockState(blockPos);


        //I prob could make this more compact, but oh well
        if(!playerEntity.isShiftKeyDown()) {

            for (int i = 0; i < conversionDictionary.size(); i += 1) {
                if (block.getBlock().getRegistryName().toString().equals(conversionKeys.get(i))) {
                    convertBlock(world, blockPos, conversionDictionary.get(conversionKeys.get(i)));
                    //returns if the conversion is successful
                    return TRUE;
                }
            }
        }else{
            for (int i = 0; i < conversionDictionary.size(); i += 1) {
                if (block.getBlock().getRegistryName().toString().equals(conversionDictionary.get(conversionKeys.get(i)))) {
                    convertBlock(world, blockPos, conversionKeys.get(i));
                    //returns if the conversion is successful
                    return TRUE;
                }
            }
        }
        //returns if the conversion is NOT successful
        return FALSE;
    }

    public Boolean isConversionPossible(BlockPos blockPos, World world, PlayerEntity playerEntity){

        BlockState block = world.getBlockState(blockPos);


        //I prob could make this more compact, but oh well
        if(!playerEntity.isShiftKeyDown()) {

            for (int i = 0; i < conversionDictionary.size(); i += 1) {
                if (block.getBlock().getRegistryName().toString().equals(conversionKeys.get(i))) {
                    //convertBlock(world, blockPos, conversionDictionary.get(conversionKeys.get(i)));
                    //returns if the conversion is possible
                    return TRUE;
                }
            }
        }else{
            for (int i = 0; i < conversionDictionary.size(); i += 1) {
                if (block.getBlock().getRegistryName().toString().equals(conversionDictionary.get(conversionKeys.get(i)))) {
                    //convertBlock(world, blockPos, conversionKeys.get(i));
                    //returns if the conversion is possible
                    return TRUE;
                }
            }
        }
        //returns if the conversion is NOT possible
        return FALSE;
    }

    public void convertBlock(World world, BlockPos blockPos, String newBlockId){
        world.setBlock(blockPos, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(newBlockId)).defaultBlockState(), 3);
    }
}
