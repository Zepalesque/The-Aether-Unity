package net.zepalesque.unity.data.prov;

import com.aetherteam.aether.data.providers.AetherLanguageProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.zepalesque.zenith.api.data.DatagenUtil;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class UnityLanguageProvider extends AetherLanguageProvider {
    public UnityLanguageProvider(PackOutput output, String id) {
        super(output, id);
    }

    public void addItem(DeferredItem<? extends Item> key) {
        this.addItem(key, DatagenUtil.getNameLocalized(key));
    }

    public void addBlock(DeferredBlock<? extends Block> key) {
        this.addBlock(key, DatagenUtil.getNameLocalized(key));
    }


    public void addEntityType(DeferredHolder<EntityType<?>, ? extends EntityType<?>> key) {
        this.addEntityType(key, DatagenUtil.getNameLocalized(key));
    }

    public void addTooltip(String key, String name) {
        this.add("tooltip." + this.id + "." + key, name);
    }

    public void addSubtitle(Supplier<SoundEvent> sound, Function<SoundEvent, String> factory, String subtitle) {
        this.add(factory.apply(sound.get()), subtitle);
    }


    public static class LoreDetails {
        public static final String
                WALL = "Walls can be used for decorative enclosures and defences. Great for keeping nasty intruders away!",
                STAIRS = "Stairs are useful for adding verticality to builds and are often used for decoration too!",
                SLAB = "Slabs are half blocks, versatile for decoration and smooth slopes. Try adding some to a building's roofing!",
                FENCE = "Fences are great for keeping your livestock safe from wandering predators!",
                FENCE_GATE = "Fence gates give a homely entrance and exit to your precious enclosures.",
                DOOR = "Doors are an ornate entrance helpful for keeping an enclosed and safe space without worry of monsters wandering in!",
                TRAPDOOR = "Trapdoors are useful for covering entryways one block wide. They are often used to add extra protection to staircases.",
                PRESSURE_PLATE = "Pressure plates are used to activate mechanisms and redstone.",
                BUTTON = "Buttons are used to activate mechanisms and redstone.",
                SIGN = "A helpful sign perfect for writing messages and directions on.",
                HANGING_SIGN = "A helpful hanging sign perfect for writing messages and directions on.",
                BOAT = "Boats can be used to easily traverse large bodies of water!",
                CHEST_BOAT = "Helpful for transporting more items over long stretches of water.";
    }
}
