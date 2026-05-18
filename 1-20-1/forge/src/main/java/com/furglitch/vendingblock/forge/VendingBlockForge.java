package com.furglitch.vendingblock.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.furglitch.vendingblock.VendingBlock;

@Mod(VendingBlock.MOD_ID)
public final class VendingBlockForge {
    public VendingBlockForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(VendingBlock.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        VendingBlock.init();
    }
}
