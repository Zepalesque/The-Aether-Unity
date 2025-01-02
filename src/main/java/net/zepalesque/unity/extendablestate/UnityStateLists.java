package net.zepalesque.unity.extendablestate;

import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zepalesque.unity.Unity;
import net.zepalesque.unity.block.UnityBlocks;
import net.zepalesque.unity.data.resource.builders.base.BaseFeatureBuilders;
import net.zepalesque.zenith.api.extendablestate.ExtendableStateList;
import net.zepalesque.zenith.core.Zenith;

public class UnityStateLists {

    public static final DeferredRegister<ExtendableStateList> STATE_LISTS = DeferredRegister.create(Zenith.Keys.EXTENDABLE_STATE_LIST, Unity.MODID);

    public static final DeferredHolder<ExtendableStateList, ExtendableStateList> FLUTEMOSS = STATE_LISTS.register("flutemoss",
            () -> new ExtendableStateList(250, 150, SimpleWeightedRandomList.<BlockState>builder()
                    .add(BaseFeatureBuilders.drops(UnityBlocks.SHORT_AETHER_GRASS), 150)
                    .add(BaseFeatureBuilders.drops(UnityBlocks.FLUTEMOSS_CARPET), 75)
                    .build()));
}
