package net.zepalesque.unity.blockset.stone.type;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zepalesque.unity.blockset.util.AetherGenerationHelper;
import net.zepalesque.unity.data.prov.UnityBlockStateProvider;
import net.zepalesque.unity.data.prov.UnityDataMapProvider;
import net.zepalesque.unity.data.prov.UnityItemModelProvider;
import net.zepalesque.unity.data.prov.UnityLanguageProvider;
import net.zepalesque.unity.data.prov.UnityRecipeProvider;
import net.zepalesque.unity.data.prov.loot.UnityBlockLootProvider;
import net.zepalesque.unity.data.prov.tags.UnityBlockTagsProvider;
import net.zepalesque.unity.data.prov.tags.UnityItemTagsProvider;
import net.zepalesque.zenith.api.blockset.AbstractStoneSet;

public class BrickStoneSet<
        S extends UnityBlockStateProvider<S>, I extends UnityItemModelProvider<I>, L extends UnityLanguageProvider<L>,
        R extends UnityRecipeProvider<R>, BT extends UnityBlockTagsProvider<BT>, IT extends UnityItemTagsProvider<IT>,
        BL extends UnityBlockLootProvider<BL>, DM extends UnityDataMapProvider<DM>> extends BaseStoneSet<S, I, L, R, BT, IT, BL, DM> {


    public BrickStoneSet(String id, DeferredRegister.Blocks blocks, DeferredRegister.Items items, MapColor color, SoundType sound, float breakTime, float blastResistance, String textureFolder) {
        super(id, blocks, items, color, sound, breakTime, blastResistance, textureFolder);
    }

    @Override
    public String baseName(boolean isBaseBlock) {
        return isBaseBlock ? this.id + "s" : this.id;
    }
}
