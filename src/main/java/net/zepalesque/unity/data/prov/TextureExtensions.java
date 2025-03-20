package net.zepalesque.unity.data.prov;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public interface TextureExtensions {
    
    default ResourceLocation texture(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).withPath("block/" + name(block));
    }
    
    default ResourceLocation texture(Block block, String location) {
        return BuiltInRegistries.BLOCK.getKey(block).withPath("block/" + location + name(block));
    }
    
    default ResourceLocation texture(Block block, String location, String suffix) {
        return BuiltInRegistries.BLOCK.getKey(block).withPath("block/" + location + name(block) + suffix);
    }
    
    default String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
    
    default String nameID(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).toString();
    }
}
