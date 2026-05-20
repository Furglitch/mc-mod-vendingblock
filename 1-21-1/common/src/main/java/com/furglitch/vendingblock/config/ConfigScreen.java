package com.furglitch.vendingblock.config;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;

@Environment(EnvType.CLIENT)
public class ConfigScreen {
    public static Screen create(Screen parent) {
        return AutoConfig.getConfigScreen(ConfigClient.class, parent).get();
    }
}
