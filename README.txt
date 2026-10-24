Ocean's Delight Legacy

Native ocean cooking for Minecraft Forge 1.7.10 and 1.12.2, requiring Farmer's Delight Legacy 1.0.2 or newer and its normal Mixin bootstrap. Each target is an independent optional addon.

Use a Farmer's Delight knife on squid to obtain tentacles, then use the Cutting Board, Cooking Pot and grill for seafood dishes. Pufferfish retain their exact legacy fish metadata and provide fugu slices. On 1.12.2, guardians provide the guardian ingredient and knife kills on elder guardians provide slabs. Guardian Soup is a four-serving feast with a leftover bowl.

The original twenty foods, guardian ingredient and soup block keep their identities. Kelp, seagrass, sea pickles and honey recipes remain deferred. Stuffed-cod cooking is also deferred while its kelp-dependent input has no acquisition. Guardians do not exist in vanilla 1.7.10, so their whole recipe chain remains deferred there. Future UAB integration must provide the intended prerequisites; no substitute recipes or extra aquatic mobs are added.

config/oceansdelight.cfg exposes general.mobDrops (default true). Other gameplay values follow the original source. Modern conditional recipe wrappers requiring Farmer's Delight are resolved through the mandatory legacy dependency. Smoker food paths use the native furnace and campfire food paths use FD grilling.

Build

Build the adjacent FarmersDelight-Legacy checkout first. Run gradlew.bat buildAll using JDK 21 for the coordinator/1.12.2 and JDK 25 for the existing GTNH 1.7.10 toolchain; the game uses Java 8. JAVA21_HOME and JAVA25_HOME are supported by scripts/run-gradle.ps1. Development FD JARs are compile-only and are not bundled.

Native source: versions/<minecraft>/src. Original source: src/. Original modern build: reference/. Shared food definitions: common/. Original recipes, tags, textures, models and translations are assembled during resource processing.

Distributions: build/release/oceansdelight-1.0.0+mc1.7.10.jar and oceansdelight-1.0.0+mc1.12.2.jar.

Original author: Scouter456. Existing LICENSE.txt and credits are preserved. The original repository's MIT metadata conflicts with its LGPL license text; publication and license claims require clarification from the original rights holder. Local builds do not resolve that discrepancy.
