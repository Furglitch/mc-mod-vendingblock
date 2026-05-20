package com.furglitch.vendingblock.neoforge.registry;

import com.furglitch.vendingblock.blockentity.DisplayBE;
import com.furglitch.vendingblock.blockentity.VendorBE;
import com.furglitch.vendingblock.registry.BlockEntityRegistry;
import com.furglitch.vendingblock.registry.BlockRegistry;

import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntityTypes extends BlockEntityRegistry {
    public static void initBlockEntityTypes(){
        VENDOR_BE = registerBlockEntity("vendor_be", () ->
                BlockEntityType.Builder.of(VendorBE::new, BlockRegistry.VENDING_BLOCK.get()).build(null)
        );
        DISPLAY_BE = registerBlockEntity("display_be", () ->
                BlockEntityType.Builder.of(DisplayBE::new, BlockRegistry.DISPLAY_BLOCK.get()).build(null)
        );

        init();
    }
}
