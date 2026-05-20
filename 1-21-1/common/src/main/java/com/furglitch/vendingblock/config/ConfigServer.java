package com.furglitch.vendingblock.config;

import java.util.ArrayList;
import java.util.List;

import com.furglitch.vendingblock.VendingBlock;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = VendingBlock.MOD_ID + "-server")
public class ConfigServer implements ConfigData {

    @ConfigEntry.Category("server")
    @ConfigEntry.Gui.PrefixText()
    @ConfigEntry.Gui.TransitiveObject
    public Admin admin = new Admin();
    public static class Admin {

        enum BreakLevel {
            ALL("text.autoconfig.vendingblock.option.admin.breakLevel.all"),
            BLOCK_OWNER("text.autoconfig.vendingblock.option.admin.breakLevel.blockOwner"),
            MODERATOR("text.autoconfig.vendingblock.option.admin.breakLevel.moderator"),
            GAMEMASTER("text.autoconfig.vendingblock.option.admin.breakLevel.gamemaster"),
            ADMIN("text.autoconfig.vendingblock.option.admin.breakLevel.admin"),
            SERVER_OWNER("text.autoconfig.vendingblock.option.admin.breakLevel.serverOwner");

            private final String key;

            BreakLevel(String key) {
                this.key = key;
            }

            @Override
            public String toString() {
                return this.key;
            }
        }

        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        private BreakLevel breakLevel = BreakLevel.BLOCK_OWNER;
            public BreakLevel getBreakLevel() { return breakLevel; }
            public void setBreakLevel(BreakLevel breakLevel) { this.breakLevel = breakLevel; }

        private List<String> blacklistProduct = new ArrayList<>(List.of(
            "vendingblock:admin_key"
        ));
            public List<String> getBlacklistProduct() { return blacklistProduct; }
            public void setBlacklistProduct(List<String> blacklistProduct) { this.blacklistProduct = blacklistProduct; }

        private List<String> blacklistFacade = new ArrayList<>(List.of(
            "*copper_grate",
            "*piston",
            "#c:bookshelves",
            "#c:glass_blocks",
            "#minecraft:leaves",
            "minecraft:beacon",
            "minecraft:cobweb",
            "minecraft:glow_lichen",
            "minecraft:mangrove_roots",
            "minecraft:sculk_vein",
            "minecraft:vine"
        ));
            public List<String> getBlacklistFacade() { return blacklistFacade; }
            public void setBlacklistFacade(List<String> blacklistFacade) { this.blacklistFacade = blacklistFacade; }

        private boolean creativeKey = false;
            public boolean isCreativeKey() { return creativeKey; }
            public void setCreativeKey(boolean creativeKey) { this.creativeKey = creativeKey; }

        private Admin() {}

    }


    @ConfigEntry.Category("server")
    @ConfigEntry.Gui.PrefixText()
    @ConfigEntry.Gui.TransitiveObject
    public Animation animation = new Animation();
    public static class Animation {
        enum ItemMode {
            STILL("text.autoconfig.vendingblock.option.animation.itemMode.still"),
            BOBBING("text.autoconfig.vendingblock.option.animation.itemMode.bobbing"),
            SPINNING("text.autoconfig.vendingblock.option.animation.itemMode.spinning"),
            FACING_PLAYER("text.autoconfig.vendingblock.option.animation.itemMode.facing_player");

            private final String key;

            ItemMode(String key) {
                this.key = key;
            }

            @Override
            public String toString() {
                return this.key;
            }
        }

        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        private ItemMode itemMode = ItemMode.STILL;
            public ItemMode getItemMode() { return itemMode; }
            public void setItemMode(ItemMode itemMode) { this.itemMode = itemMode; }

        private Animation() {}
    }

}
