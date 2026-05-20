package com.furglitch.vendingblock;

import com.furglitch.vendingblock.config.ConfigClient;
import com.furglitch.vendingblock.config.ConfigServer;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public final class VendingBlock {
    public static final String MOD_ID = "vendingblock";

    public static void init() {
        AutoConfig.register(ConfigClient.class, GsonConfigSerializer::new);
        AutoConfig.register(ConfigServer.class, GsonConfigSerializer::new);
    }

    public static ConfigClient getConfig() {
        return AutoConfig.getConfigHolder(ConfigClient.class).getConfig();
    }

    public static ConfigServer getServerConfig() {
        return AutoConfig.getConfigHolder(ConfigServer.class).getConfig();
    }
}
