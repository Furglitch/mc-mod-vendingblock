package com.furglitch.vendingblock.registry;

import java.util.function.Supplier;

import com.furglitch.vendingblock.VendingBlock;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockRegistry {

    private static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(VendingBlock.MOD_ID, Registries.BLOCK);

    // public static RegistrySupplier<Block> EXAMPLE_BLOCK;

    public static void init(){
        // EXAMPLE_BLOCK = registerBlock("example_block", () -> new Block(baseProperties("example_block")));

        BLOCKS.register();
    }

    public static RegistrySupplier<Block> registerBlock(String name, Supplier<Block> block){
        return BLOCKS.register(ResourceLocation.fromNamespaceAndPath(VendingBlock.MOD_ID, name), block);
    }

    public static BlockBehaviour.Properties baseProperties(String name){
        return BlockBehaviour.Properties.of();
    }

}
