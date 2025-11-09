package yuuria.extraironfurnaces;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = ExtraIronFurnaces.MODID)
public class ExtraIronFurnacesConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static ModConfigSpec.IntValue extrafurnaceSpeed;
    public static ModConfigSpec.IntValue extrafurnaceMultiSmeltCount;
    public static ModConfigSpec.IntValue extrafurnaceOutputMultiplier;

    static final ModConfigSpec SPEC;

    static {
        extrafurnaceSpeed = BUILDER
                .comment("Number of ticks to complete one smelting operating.")
                .defineInRange("extrafurnace.speed", 1, 1, 72000);

        extrafurnaceMultiSmeltCount = BUILDER
                .comment("Blocks smelt per operation")
                .defineInRange("extrafurnace.multicount", 64, 1, Integer.MAX_VALUE);

        extrafurnaceOutputMultiplier = BUILDER
                .comment("Output Multiplier")
                .defineInRange("extrafurnace.output_multi", 2, 1, Integer.MAX_VALUE);

        SPEC = BUILDER.build();
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {}
}
