package net.zepalesque.unity.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zepalesque.unity.Unity;

public class UnityItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Unity.MODID);

    public static final DeferredItem<Item> VALKYRIE_CLAY_BALL = ITEMS.registerSimpleItem("valkyrie_clay_ball");
    public static final DeferredItem<Item> VALKYRIE_BRICK = ITEMS.registerSimpleItem("valkyrie_brick");
}
