package com.furglitch.vendingblock.neoforge;

import com.furglitch.vendingblock.VendingBlock;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(VendingBlock.MOD_ID)
public final class VendingBlockNeoForge {
    public VendingBlockNeoForge() {
        VendingBlock.init();

        if (FMLEnvironment.dist.isClient()) {
            VendingBlockNeoForgeClient.init();
        }
    }
}
