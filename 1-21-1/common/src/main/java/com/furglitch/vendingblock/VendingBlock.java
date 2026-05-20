package com.furglitch.vendingblock;

import com.furglitch.vendingblock.config.ConfigClient;
import com.furglitch.vendingblock.config.ConfigServer;
import com.furglitch.vendingblock.registry.BlockEntityRegistry;
import com.furglitch.vendingblock.registry.BlockRegistry;
import com.furglitch.vendingblock.registry.ItemRegistry;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.world.level.block.entity.BlockEntity;

public final class VendingBlock {
    public static final String MOD_ID = "vendingblock";

    public static void init() {
        AutoConfig.register(ConfigClient.class, GsonConfigSerializer::new);
        AutoConfig.register(ConfigServer.class, GsonConfigSerializer::new);
        ItemRegistry.init();
        BlockRegistry.init();
        BlockEntityRegistry.init();
    }

    public static ConfigClient getConfig() {
        return AutoConfig.getConfigHolder(ConfigClient.class).getConfig();
    }

    public static ConfigServer getServerConfig() {
        return AutoConfig.getConfigHolder(ConfigServer.class).getConfig();
    }
}
