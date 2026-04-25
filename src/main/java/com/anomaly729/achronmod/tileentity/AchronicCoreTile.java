package com.anomaly729.achronmod.tileentity;

import com.anomaly729.achronmod.block.custom.AchronicCore;
import com.anomaly729.achronmod.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

//import static sun.awt.image.SunWritableRaster.markDirty;

public class AchronicCoreTile extends TileEntity{

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    public static final int NUMBER_OF_SLOTS = 1;

    public AchronicCoreTile(){
        this(ModTileEntities.ACHRONIC_CORE_TILE.get());
        //super(ModTileEntities.ACHRONIC_CORE_TILE.get());
    }

    public AchronicCoreTile(TileEntityType<?> tileEntityTypeIn){
        super(tileEntityTypeIn);
        //super();
    }

    //read method
    @Override
    public void load(BlockState state, CompoundNBT nbt){
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.load(state, nbt);
    }

    //write method
    @Override
    public CompoundNBT save(CompoundNBT compound){
        compound.put("inv", itemHandler.serializeNBT());
        return super.save(compound);
    }

    private ItemStackHandler createHandler(){
        return new ItemStackHandler(NUMBER_OF_SLOTS){
            @Override
            protected void onContentsChanged(int slot){
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack){
                if(stack.getItem() == ModItems.ACHRON_INGOT.get()){
                    return true;
                }

                return false;
                //return super.isItemValid(slot, stack);
            }

            @Override
            public int getSlotLimit(int slot){
                return 64;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate){
                if(!isItemValid(slot, stack)){
                    return stack;
                }



                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Override
    public <T>LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nonnull Direction side){
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return handler.cast();
        }


        return super.getCapability(cap, side);
    }






}
