package io.github.nagol2003.celestial.planets.Polulos.biome;

import java.util.Random;

import io.github.nagol2003.celestial.planets.Polulos.BiomeDecoratorPolulos;
import io.github.nagol2003.celestial.planets.Polulos.biome.gen.BiomePolulosDesert;
import io.github.nagol2003.celestial.planets.Polulos.biome.gen.BiomePolulosSavanna;
//import io.github.nagol2003.celestial.planets.Polulos.biome.gen.BiomeGenPlanetOneSea;
import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;

public class PolulosBiomes extends BiomeGenBaseGC {

	public static final Biome PolulosSavanna = new BiomePolulosSavanna(new BiomeProperties("Polulos Savanna").setBaseHeight(0.175F).setHeightVariation(0.55F).setRainfall(0.8F));
	public static final Biome PolulosDesert = new BiomePolulosDesert(new BiomeProperties("Polulos Desert").setBaseHeight(0.100F).setHeightVariation(0.62F).setRainfall(0.1F));
	//public static final Biome PolulosSea = new BiomeGenPolulosSea(new BiomeProperties("PolulosSea").setBaseHeight(0.999F).setHeightVariation(0.1F).setRainfall(0.1F));

	protected PolulosBiomes(BiomeProperties properties) {
		super(properties, true);
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new BiomeDecoratorPolulos();
	}

	protected BiomeDecoratorPolulos getBiomeDecorator() {
		return (BiomeDecoratorPolulos) this.decorator;
	}

	@Override
	public void genTerrainBlocks (World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		generateBiomeSurface(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	public void generateBiomeSurface(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z,
			double noiseVal) {
		int i = worldIn.getSeaLevel();
		//IBlockState stoneBlockState = ChunkProviderPolulos.getStoneBlockState();
		IBlockState topState = this.topBlock;
		IBlockState fillState = this.fillerBlock;
		int j = -1;
		int k = (int) ((noiseVal / 3.0D) + 3.0D + (rand.nextDouble() * 0.45D));
		int l = x & 15;
		int i1 = z & 15;

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 == 0) {
				chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
					//	} else if (iblockstate2.getBlock() == stoneBlockState.getBlock()) {
					if (j == -1) {
						if (k <= 0) {
							topState = AIR;
							fillState = STONE;
						} else if ((j1 >= (i - 4)) && (j1 <= (i + 1))) {
							topState = this.topBlock;
							fillState = this.fillerBlock;
						}

						if ((j1 < i) && ((topState == null) || (topState.getMaterial() == Material.AIR))) {
							topState = ICE;
						}

						j = k;

						if (j1 >= (i - 1)) {
							chunkPrimerIn.setBlockState(i1, j1, l, topState);
						} else if (j1 < (i - 7 - k)) {
							topState = AIR;
							fillState = STONE;
							chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
						} else {
							chunkPrimerIn.setBlockState(i1, j1, l, fillState);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, fillState);
					}
				}
			}
		}
	}
}
