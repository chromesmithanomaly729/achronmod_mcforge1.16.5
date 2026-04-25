package com.anomaly729.achronmod.block.custom;

import com.anomaly729.achronmod.container.AchronicCoreContainer;
import com.anomaly729.achronmod.tileentity.AchronicCoreTile;
import com.anomaly729.achronmod.tileentity.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.lang.Boolean;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class AchronicCore extends Block {
    public AchronicCore(Properties properties){
        super(properties);
    }

    public boolean Jump(Vector3i destination){
        return FALSE;
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit){
        if(!worldIn.isClientSide()) {
            TileEntity tileEntity = worldIn.getBlockEntity(pos);

            if(!player.isCrouching()) {
                if(tileEntity instanceof AchronicCoreTile) {
                    INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);

                    NetworkHooks.openGui(((ServerPlayerEntity)player), containerProvider, tileEntity.getBlockPos());
                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
            } else {
                if(tileEntity instanceof AchronicCoreTile) {
                    if(worldIn.isThundering()) {
                        EntityType.LIGHTNING_BOLT.spawn(((ServerWorld) worldIn), null, player,
                                pos, SpawnReason.TRIGGERED, true, true);

                        //((AchronicCoreTile)tileEntity).lightningHasStruck();
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;

    }

    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.achronmod.achronic_core");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new AchronicCoreContainer(i, worldIn, pos, playerInventory, playerEntity);
            }
        };
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world){
        return ModTileEntities.ACHRONIC_CORE_TILE.get().create();
    }


    @Override
    public boolean hasTileEntity(BlockState state){
        return TRUE;
    }
}
