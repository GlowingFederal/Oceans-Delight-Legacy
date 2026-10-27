Ocean's Delight Legacy

Native ocean cooking for Minecraft Forge 1.7.10 and 1.12.2, requiring our Farmer's Delight Legacy 1.0.2 or newer on 1.7.10. On 1.12.2, choose our port (with its normal Mixin bootstrap) or ukmojb's Farmer's Delight Legacy 1.1.7 or newer: https://github.com/ukmojb/FarmersDelightLegacy. Each target is an independent optional addon.

Use a Farmer's Delight knife on squid to obtain tentacles, then use the Cutting Board, Cooking Pot and grill for seafood dishes. Pufferfish retain their exact legacy fish metadata and provide fugu slices. On 1.12.2, guardians provide the guardian ingredient and knife kills on elder guardians provide slabs. Guardian Soup is a four-serving feast with a leftover bowl.

The original twenty foods, guardian ingredient and soup block keep their identities. Kelp, seagrass, sea pickles and honey recipes remain deferred. Stuffed-cod cooking is also deferred while its kelp-dependent input has no acquisition. Guardians do not exist in vanilla 1.7.10, so their whole recipe chain remains deferred there. Future UAB integration must provide the intended prerequisites; no substitute recipes or extra aquatic mobs are added.

config/oceansdelight.cfg exposes general.mobDrops (default true). Other gameplay values follow the original source. Modern conditional recipe wrappers requiring Farmer's Delight are resolved through the mandatory legacy dependency. Smoker food paths use the native furnace and campfire food paths use FD grilling.

Build

Build the adjacent FarmersDelight-Legacy checkout first. Run gradlew.bat buildAll using JDK 21 for the coordinator/1.12.2 and JDK 25 for the existing GTNH 1.7.10 toolchain; the game uses Java 8. JAVA21_HOME and JAVA25_HOME are supported by scripts/run-gradle.ps1. Development FD JARs are compile-only and are not bundled.

Native source: versions/<minecraft>/src. Original source: src/. Original modern build: reference/. Shared food definitions: common/. Original recipes, tags, textures, models and translations are assembled during resource processing.

Distributions: build/release/oceansdelight-1.0.1+mc1.7.10.jar and oceansdelight-1.0.1+mc1.12.2.jar.

Original author: Scouter456. Existing LICENSE.txt and credits are preserved. The original repository's MIT metadata conflicts with its LGPL license text; publication and license claims require clarification from the original rights holder. Local builds do not resolve that discrepancy.

1.12.2 compatibility

Install exactly one Farmer's Delight implementation. Both use the Forge mod ID farmersdelight and cannot coexist. One addon JAR supports either implementation, and Corn Delight and Ocean's Delight can be installed together. Farmer's Delight remains mandatory; UAB is not required. The 1.7.10 implementation is unchanged.

The adapter is selected from the installed mod's entry-point class, not its version or display name. Our implementation retains its existing recipe/tag and food APIs. The ukmojb adapter registers cooking and cutting through CookingPotRecipeApi and CuttingBoardRecipeApi; Stove/Skillet food uses CampfireCookingRecipeManager. Containerless pot meals use the public CookingPotRecipeManager registration overload because the API otherwise adds a default bowl. Its native managers also supply the existing JEI categories. Addon food values, effects, eating times, container returns and registry names are preserved. Missing modern ingredients and unsupported Create/Immersive Engineering serializers stay deferred instead of receiving substitute recipes. A change of Farmer's Delight implementation is not a world-save migration; retain backups and the original implementation for existing worlds.

The 1.12.2 build resolves the pinned, compile-only ukmojb 1.1.7 artifact from CurseMaven (project 1517486, file 8884540), remapped to the native development mappings; it is never bundled. The upstream development workspace lists many optional integrations, which this addon does not inherit. Offline builds require this module in the Gradle cache. The usual adjacent development JAR for our port is still required.
