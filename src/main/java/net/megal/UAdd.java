package net.megal;

import net.fabricmc.api.ModInitializer;

import net.megal.block.UBlocks;
import net.megal.entity.UEntities;
import net.megal.item.UItems;
import net.megal.loot.ULootTableModifications;
import net.megal.network.UNetworking;
import net.megal.particle.UParticles;
import net.megal.sound.USounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UAdd implements ModInitializer {
	public static final String ID = "uselessadditions";
    public static final Logger LOG = LoggerFactory.getLogger("Useless Additions");

	@Override
	public void onInitialize() {
		USounds.loadStuff();
		UBlocks.loadStuff();
		UItems.fillItemGroups();
		UEntities.registerSpawning();
		UNetworking.registerPayloads();
		UNetworking.registerEvents();
		ULootTableModifications.modify();
		UParticles.loadStuff();

		LOG.info("                                        ");
		LOG.info("           *@/                          ");
		LOG.info("          @@@@@,        *@*             ");
		LOG.info("         /@@(/@@*     &@@@@@*           ");
		LOG.info("         @@@  *@@#   .@@, *@@/          ");
		LOG.info("        /@@*    @@@  /@@.  (@@,         ");
		LOG.info("        @@@      @@@.#@@    &@@         ");
		LOG.info("       (@@/       ,@#&(      @@@        ");
		LOG.info("       %@@      #   .   #    .@@#       ");
		LOG.info("       @@%      #       #     ,@@,      ");
		LOG.info("      (@@   ///     v    ///   #@@      ");
		LOG.info("      &@@#.                   .%@@      ");
		LOG.info("         ,&@@@#.       ,/&@@@@@%,       ");
		LOG.info("              .%@@@@@@&(.               ");
		LOG.info("                                        ");
		LOG.info("        Useless Additions Loaded        ");
	}
}