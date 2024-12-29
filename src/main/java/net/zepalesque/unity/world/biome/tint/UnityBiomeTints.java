package net.zepalesque.unity.world.biome.tint;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zepalesque.unity.Unity;
import net.zepalesque.zenith.Zenith;
import net.zepalesque.zenith.api.biometint.BiomeTint;

public class UnityBiomeTints {
    public static final DeferredRegister<BiomeTint> TINTS = DeferredRegister.create(Zenith.Keys.BIOME_TINT, Unity.MODID);

    public static final DeferredHolder<BiomeTint, BiomeTint> AETHER_GRASS = TINTS.register("aether_grass", () -> new BiomeTint(Unity.loc("aether_grass"), 0xADF9C4));
}
