package com.furglitch.vendingblock.fabric;

import net.fabricmc.api.ModInitializer;

import com.furglitch.vendingblock.VendingBlock;

public final class VendingBlockFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        VendingBlock.init();
    }
}
