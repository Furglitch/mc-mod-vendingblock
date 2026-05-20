package com.furglitch.vendingblock.forge;

import com.furglitch.vendingblock.VendingBlock;
import com.furglitch.vendingblock.forge.registry.BlockEntityTypes;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(VendingBlock.MOD_ID)
public final class VendingBlockForge {
    public VendingBlockForge() {
        EventBuses.registerModEventBus(VendingBlock.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        VendingBlock.init();
        BlockEntityTypes.initBlockEntityTypes();

        if (FMLEnvironment.dist.isClient()) {
            VendingBlockForgeClient.init();
        }
    }
}
