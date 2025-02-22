package com.legobmw99.allomancy.datagen;

import com.legobmw99.allomancy.Allomancy;
import com.legobmw99.allomancy.modules.combat.CombatSetup;
import com.legobmw99.allomancy.modules.consumables.ConsumeSetup;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class Advancements implements ForgeAdvancementProvider.AdvancementGenerator {


    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement.Builder
                .advancement()
                .parent(Advancement.Builder.advancement().build(new ResourceLocation("adventure/root")))
                .display(ConsumeSetup.ALLOMANTIC_GRINDER.get(), Component.translatable("advancements.local_metallurgist.title"),
                         Component.translatable("advancements.local_metallurgist.desc"), null, FrameType.TASK, true, true, false)
                .addCriterion("grinder", InventoryChangeTrigger.TriggerInstance.hasItems(ConsumeSetup.ALLOMANTIC_GRINDER.get()))
                .save(saver, "allomancy:main/metallurgist");

        Advancement.Builder
                .advancement()
                .parent(Advancement.Builder.advancement().build(new ResourceLocation(Allomancy.MODID, "main/metallurgist")))
                .display(ConsumeSetup.LERASIUM_NUGGET.get(), Component.translatable("advancements.dna_entangled.title"), Component.translatable("advancements.dna_entangled.desc"),
                         null, FrameType.TASK, true, false, true)
                .addCriterion("impossible", new ImpossibleTrigger.TriggerInstance())
                .save(saver, "allomancy:main/dna_entangled");

        Advancement.Builder
                .advancement()
                .parent(Advancement.Builder.advancement().build(new ResourceLocation(Allomancy.MODID, "main/metallurgist")))
                .display(CombatSetup.MISTCLOAK.get(), Component.translatable("advancements.become_mistborn.title"), Component.translatable("advancements.become_mistborn.desc"),
                         null, FrameType.CHALLENGE, true, true, true)
                .addCriterion("lerasium_nugget", ConsumeItemTrigger.TriggerInstance.usedItem(ConsumeSetup.LERASIUM_NUGGET.get()))
                .rewards(AdvancementRewards.Builder.experience(100))
                .save(saver, "allomancy:main/become_mistborn");

    }

}
