package io.github.nagol2003.celestial.planets.Polulos.biome;

import io.github.nagol2003.init.InitBlocks;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorPolulosOres extends BiomeDecoratorSpace {

	private World currentWorld;
	private WorldGenerator ferminiumOre;
	private WorldGenerator strangemetallicsubstance;

	private boolean isDecorating = false;

	public BiomeDecoratorPolulosOres() {

		this.ferminiumOre = new WorldGenMinableMeta(InitBlocks.POLULOSFERMINIUMORE, 20, 0, false, InitBlocks.POLULOSROCK, 0);
		this.strangemetallicsubstance = new WorldGenMinableMeta(InitBlocks.STRANGEMETALLICPOLULOS, 20, 0, false, InitBlocks.POLULOSROCK, 0);
		// WorldGenMinableMeta(Block OreBlock, int numberOfBlocks, int OreMeta, boolean usingMetaData, Block StoneBlock, int StoneMeta);
	}

	@Override
	protected void setCurrentWorld(World world) {
		this.currentWorld = world;
	}

	@Override
	protected World getCurrentWorld() {
		return this.currentWorld;
	}

	@Override
	protected void decorate() {
		if(isDecorating) {
			return;
		}
		isDecorating = true;
		//generateOre(amountPerChunk, worldGenerator, minY, maxY);s
		this.generateOre(20, this.ferminiumOre, 15, 64);
		this.generateOre(20, this.strangemetallicsubstance, 15, 64);

		isDecorating = false;
	}

}