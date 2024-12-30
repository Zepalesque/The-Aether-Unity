package net.zepalesque.unity.client;

import net.zepalesque.unity.block.natural.AetherShortGrassBlock;

public class UnityClient {

    public static void registerTintOverrides() {
        AetherShortGrassBlock.COLOR_OVERRIDES.add(UnityColors::unityColors);
    }
}
