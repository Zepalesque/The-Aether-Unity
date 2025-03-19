package net.zepalesque.unity.data.prov;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.zepalesque.unity.mixin.mixins.common.datagen.LangProvAccessor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

// TODO: use for some stuff
public abstract class ConcatenatedLanguageProvider<T extends LanguageProvider> implements DataProvider {
    protected final PackOutput output;
    protected final String baseModid;
    protected final BiFunction<PackOutput, String, ? extends T> factory;
    private final Map<String, T> map = new HashMap<>();
    
    public ConcatenatedLanguageProvider(PackOutput output, String baseModid, BiFunction<PackOutput, String, ? extends T> factory) {
        this.output = output;
        this.baseModid = baseModid;
        this.factory = factory;
    }
    
    public abstract void addTranslations();
    
    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        addTranslations();
        return CompletableFuture.allOf(this.map.values().stream().map(lang -> lang.run(output)).toArray(CompletableFuture[]::new));
    }
    
    public T get(String modid) {
        return this.map.computeIfAbsent(modid, s -> factory.apply(this.output, s));
    }
    
    @Override
    public String getName() {
        return String.format("Concatenated Languages for %s: %s", baseModid, map.entrySet().stream().map(entry ->
            String.format("(modid: %s, locale: %s)", entry.getKey(), ((LangProvAccessor)entry.getValue()).getLocale())
        ).collect(Collectors.toSet()));
    }
}
