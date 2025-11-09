package yuuria.extraironfurnaces.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import yuuria.extraironfurnaces.gui.BlockExtraIronFurnaceScreen;

public class ClientSetup {
    public static void init(final RegisterMenuScreensEvent event) {
        event.register(Registration.EXTRA_IRONFURNACE_CONTAINER.get(), BlockExtraIronFurnaceScreen::new);
    }
}
