package net.zepalesque.unity.blockset.util;

public interface MutableLoreGeneration<T extends MutableLoreGeneration<T>> extends AetherGenerationHelper {
    T withLore(String lore);
}
