package yuuria.extraironfurnaces;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;
import yuuria.extraironfurnaces.init.ClientSetup;
import yuuria.extraironfurnaces.init.Registration;

@Mod(ExtraIronFurnaces.MODID)
public class ExtraIronFurnaces {
    public static final String MODID = "extraironfurnaces";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ExtraIronFurnaces(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, ExtraIronFurnacesConfig.SPEC);

        modEventBus.addListener(ClientSetup::init);

        Registration.init(modEventBus);
    }
}
