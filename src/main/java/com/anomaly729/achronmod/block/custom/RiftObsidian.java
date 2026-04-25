package com.anomaly729.achronmod.block.custom;

import com.anomaly729.achronmod.util.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class RiftObsidian extends Block {
    public RiftObsidian(Properties properties){super(properties);}

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit){
        //on block right click
        //for now just tp to somewhere random around the block's pos
        double randomDoubleX = RANDOM.nextDouble()-0.5;
        double randomDoubleZ = RANDOM.nextDouble()-0.5;
        double randomDoubleMultiplier = 10;

        BlockPos newPlayerPos = new BlockPos(randomDoubleX*randomDoubleMultiplier+pos.getX(), pos.getY(), randomDoubleZ*randomDoubleMultiplier+pos.getZ());


        worldIn.playSound(player, pos, ModSounds.RIFT_OBSIDIAN_USAGE_SOUND.get(), SoundCategory.BLOCKS,10, 1);
        player.teleportTo(newPlayerPos.getX(), newPlayerPos.getY(), newPlayerPos.getZ());

        return ActionResultType.SUCCESS;
    }
}
