package com.furglitch.vendingblock.registry;

import java.util.function.Supplier;

import com.furglitch.vendingblock.VendingBlock;
import com.furglitch.vendingblock.block.DisplayBlock;
import com.furglitch.vendingblock.block.VendorBlock;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockRegistry {

    private static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(VendingBlock.MOD_ID, Registries.BLOCK);

    public static RegistrySupplier<Block> VENDING_BLOCK, DISPLAY_BLOCK;

    public static void init(){
        VENDING_BLOCK = registerBlock("vending_block", () -> new VendorBlock(baseProperties("vending_block")
            .strength(1.0f)
            .sound(SoundType.METAL)
            .noOcclusion()
        ));
        DISPLAY_BLOCK = registerBlock("display_block", () -> new DisplayBlock(baseProperties("display_block")
            .strength(1.0f)
            .sound(SoundType.METAL)
            .noOcclusion()
        ));

        BLOCKS.register();
    }

    public static RegistrySupplier<Block> registerBlock(String name, Supplier<Block> block){
        return BLOCKS.register(new ResourceLocation(VendingBlock.MOD_ID, name), block);
    }

    public static BlockBehaviour.Properties baseProperties(String name){
        return BlockBehaviour.Properties.of();
    }

}
