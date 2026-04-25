package com.anomaly729.achronmod.world.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class EqualCoordsTeleporter implements ITeleporter {



    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {

        //entity.moveToBlockPosAndAngles(targetPos, yaw, entity.rotationPitch);
        //entity.setMotion(Vector3d.ZERO);

        //not changing coords to any degree in here
        //nor doing anything to the destination or current worlds, I'll make something else do that.

        return repositionEntity.apply(false);
    }
}
