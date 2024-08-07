package com.ametrin.fancy_food.event;

import com.ametrin.fancy_food.FancyFood;
import com.ametrin.fancy_food.data.provider.*;
import com.ametrin.fancy_food.registry.FFItems;
import com.ametrin.fancy_food.world.FFFoods;
import com.ametrinstudios.ametrin.data.DataProviderHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = FancyFood.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class FFModEvents {

    @SubscribeEvent
    private static void modifyComponents(ModifyDefaultComponentsEvent event) {
        event.modify(Items.SUGAR_CANE, builder -> builder.set(DataComponents.FOOD, FFFoods.SUGAR_CANE));
    }

    @SubscribeEvent
    private static void modifyCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() != CreativeModeTabs.FOOD_AND_DRINKS) return;

        event.accept(FFItems.CARROT_SALAD);
        event.accept(FFItems.CHICKEN_SALAD);
    }

    @SubscribeEvent
    private static void gatherData(GatherDataEvent event) {
        var providers = new DataProviderHelper(event);

        providers.add(FFItemModelProvider::new);
        providers.add(FFLanguageProvider::new);
        providers.add(FFRecipeProvider::new);
        providers.addBlockAndItemTags(FFBlockTagsProvider::new, FFItemTagsProvider::new);
    }
}
