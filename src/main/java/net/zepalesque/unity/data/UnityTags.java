package net.zepalesque.unity.data;

import com.aetherteam.aether.api.AetherAdvancementSoundOverrides;
import com.aetherteam.aether.api.registers.AdvancementSoundOverride;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.zepalesque.unity.Unity;

public class UnityTags {


    public static class Blocks {
        // Blocks that should make Short Aether Grass use its enchanted state
        public static final TagKey<Block> ENCHANTED_GRASS_BLOCKS = tag("enchanted_grass_blocks");

        public static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, Unity.loc(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> AETHER_GRASS_NONREPLACING = tag("aether_grass_nonreplacing");

        public static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, Unity.loc(name));
        }
    }

    public static class Entities {

        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, Unity.loc(name));
        }
    }

    public static class Biomes {

        private static TagKey<Biome> tag(String name) {
            return TagKey.create(Registries.BIOME, Unity.loc(name));
        }
    }
    
    public static class DmgTypes {

        private static TagKey<DamageType> tag(String name) {
            return TagKey.create(Registries.DAMAGE_TYPE, Unity.loc(name));
        }
    }
    
    public static class Sounds {
        
        private static TagKey<SoundEvent> tag(String name) {
            return TagKey.create(Registries.SOUND_EVENT, Unity.loc(name));
        }
    }

    public static class AdvancementSFX {

        private static TagKey<AdvancementSoundOverride> tag(String name) {
            return TagKey.create(AetherAdvancementSoundOverrides.ADVANCEMENT_SOUND_OVERRIDE_REGISTRY_KEY, Unity.loc(name));
        }
    }
}
