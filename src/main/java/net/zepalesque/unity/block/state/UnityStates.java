package net.zepalesque.unity.block.state;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.zepalesque.unity.block.state.enums.GrassSize;

public class UnityStates {
    public static final BooleanProperty ENCHANTED = BooleanProperty.create("enchanted");
    public static final EnumProperty<GrassSize> GRASS_SIZE = EnumProperty.create("grass_size", GrassSize.class);

    public static final IntegerProperty LEAF_LAYERS = IntegerProperty.create("layers", 1, 16);
}
