package com.furglitch.vendingblock.config;

import com.furglitch.vendingblock.VendingBlock;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = VendingBlock.MOD_ID)
public class ConfigClient implements ConfigData {

    @ConfigEntry.Category("client")
    @ConfigEntry.Gui.PrefixText()
    @ConfigEntry.Gui.TransitiveObject
    public Animation animation = new Animation();
    public static class Animation {

        enum ItemMode {
            SERVER("text.autoconfig.vendingblock.option.animation.itemMode.server"),
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
        private ItemMode itemMode = ItemMode.SERVER;
            public ItemMode getItemMode() { return itemMode; }
            public void setItemMode(ItemMode itemMode) { this.itemMode = itemMode; }

        private Animation() {}

    }


    @ConfigEntry.Category("client")
    @ConfigEntry.Gui.PrefixText()
    @ConfigEntry.Gui.TransitiveObject
    public UserInterface userInterface = new UserInterface();
    public static class UserInterface {

        enum ScrolltipPosition {
            ITEM("text.autoconfig.vendingblock.option.userInterface.scrolltipPosition.item"),
            TOP("text.autoconfig.vendingblock.option.userInterface.scrolltipPosition.top");

            private final String key;

            ScrolltipPosition(String key) {
                this.key = key;
            }

            @Override
            public String toString() {
                return this.key;
            }
        }

        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        private ScrolltipPosition scrolltipPosition = ScrolltipPosition.TOP;
            public ScrolltipPosition getScrolltipPosition() { return scrolltipPosition; }
            public void setScrolltipPosition(ScrolltipPosition scrolltipPosition) { this.scrolltipPosition = scrolltipPosition; }

        private UserInterface() {}
    }


    @ConfigEntry.Category("client")
    @ConfigEntry.Gui.PrefixText()
    @ConfigEntry.Gui.TransitiveObject
    public Messages messages = new Messages();
    public static class Messages {

        private boolean msgPurchase = true;
            public boolean isMessagePurchase() { return msgPurchase; }
            public void setMessagePurchase(boolean msgPurchase) { this.msgPurchase = msgPurchase; }

        private boolean msgGiveaway = true;
            public boolean isMessageGiveaway() { return msgGiveaway; }
            public void setMessageGiveaway(boolean msgGiveaway) { this.msgGiveaway = msgGiveaway; }

        private boolean msgDonation = true;
            public boolean isMessageDonation() { return msgDonation; }
            public void setMessageDonation(boolean msgDonation) { this.msgDonation = msgDonation; }

        private boolean msgInvEmpty = true;
            public boolean isMessageInvEmpty() { return msgInvEmpty; }
            public void setMessageInvEmpty(boolean msgInvEmpty) { this.msgInvEmpty = msgInvEmpty; }

        private boolean msgInvFull  = true;
            public boolean isMessageInvFull() { return msgInvFull; }
            public void setMessageInvFull(boolean msgInvFull) { this.msgInvFull = msgInvFull; }

        private Messages() {}
    }



}
