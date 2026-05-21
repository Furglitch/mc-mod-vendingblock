package com.furglitch.vendingblock.neoforge;

import com.furglitch.vendingblock.VendingBlock;
import com.furglitch.vendingblock.neoforge.registry.BlockEntityTypes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(VendingBlock.MOD_ID)
public final class VendingBlockNeoForge {
    public VendingBlockNeoForge(IEventBus modEventBus) {
        VendingBlock.init();
        BlockEntityTypes.initBlockEntityTypes();

        if (FMLEnvironment.dist.isClient()) {
            VendingBlockNeoForgeClient.init(modEventBus);
        }
    }
}
