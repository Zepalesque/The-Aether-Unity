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
        // Blocks that should make Short Aether Grass use its enchanted state when placed on top of
        public static final TagKey<Block> SHORT_AETHER_GRASS_STATE_ENCHANTING = tag("short_aether_grass_state_enchanting");
        // Blocks that should make Short Aether Grass use its default color rather than any biome colors when placed on top of
        public static final TagKey<Block> SHORT_AETHER_GRASS_DEFAULT_COLORING = tag("short_aether_grass_default_coloring");
        public static final TagKey<Block> AETHER_CARVER_REPLACEABLES = tag("aether_carver_replaceables");

        public static final TagKey<Block> CONVERTABLE_TO_AETHER_MUD = tag("convertable_to_aether_mud");

        public static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, Unity.loc(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> AETHER_GRASS_NONREPLACING = tag("aether_grass_nonreplacing");
        public static final TagKey<Item> PACKED_AETHER_MUD_CRAFTING = tag("packed_aether_mud_crafting");
        public static final TagKey<Item> AETHER_GRAVEL = tag("aether_gravel");

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
