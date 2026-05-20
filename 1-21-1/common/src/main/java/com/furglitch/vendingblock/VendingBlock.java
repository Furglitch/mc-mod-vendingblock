package com.furglitch.vendingblock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.furglitch.vendingblock.config.ConfigClient;
import com.furglitch.vendingblock.config.ConfigServer;
import com.furglitch.vendingblock.registry.BlockRegistry;
import com.furglitch.vendingblock.registry.ItemRegistry;
import com.furglitch.vendingblock.registry.TabRegistry;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public final class VendingBlock {
    public static final String MOD_ID = "vendingblock";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        AutoConfig.register(ConfigClient.class, GsonConfigSerializer::new);
        AutoConfig.register(ConfigServer.class, GsonConfigSerializer::new);
        BlockRegistry.init();
        ItemRegistry.init();
        TabRegistry.init();
    }

    public static ConfigClient getConfig() {
        return AutoConfig.getConfigHolder(ConfigClient.class).getConfig();
    }

    public static ConfigServer getServerConfig() {
        return AutoConfig.getConfigHolder(ConfigServer.class).getConfig();
    }
}
