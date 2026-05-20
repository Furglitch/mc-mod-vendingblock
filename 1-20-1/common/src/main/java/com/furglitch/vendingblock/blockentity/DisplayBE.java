package com.furglitch.vendingblock.blockentity;

import com.furglitch.vendingblock.registry.BlockEntityRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class DisplayBE extends BlockEntity {

    public DisplayBE(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.DISPLAY_BE.get(), pos, state);
    }

    public static class Ticker<T extends DisplayBE> implements BlockEntityTicker<T> {
        @Override
        public void tick(Level level, BlockPos pos, BlockState state, T blockEntity) {
        }
    }

}
