package com.sickasian.magicshitmod.ModItems;

import com.sickasian.magicshitmod.ModItems.custom.SpelBookItem;
import com.sickasian.magicshitmod.ModItems.custom.SpelEarthItem;
import com.sickasian.magicshitmod.ModItems.custom.SpelFireItem;
import com.sickasian.magicshitmod.magicshitmod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, magicshitmod.MODID);

    public static final RegistryObject<Item> SPEL1 = ITEMS.register("spel1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FIREBALL = ITEMS.register("fireball", () -> new SpelFireItem(new Item.Properties()));

    public static final RegistryObject<Item> EARTHBALL = ITEMS.register("earthspell", () -> new SpelEarthItem(new Item.Properties()));
    public static final RegistryObject<Item> SPELLBOK = ITEMS.register("spellbook", () -> new SpelBookItem(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}