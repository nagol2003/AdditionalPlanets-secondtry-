package io.github.nagol2003.init;

import io.github.nagol2003.blocks.Denia.DeniaFerminiumOre;
import io.github.nagol2003.blocks.Denia.FROZENCO2;
import io.github.nagol2003.blocks.Denia.StrangeMetallicDenia;
import io.github.nagol2003.blocks.Denia.deniastone;
import io.github.nagol2003.blocks.Ognious.MoistDirt;
import io.github.nagol2003.blocks.Ognious.OgniousGrass;
import io.github.nagol2003.blocks.Ognious.OgniousLeaves;
import io.github.nagol2003.blocks.Ognious.OgniousLog;
import io.github.nagol2003.blocks.Ognious.OgniousRock;
import io.github.nagol2003.blocks.Ognious.OgniousStone;
import io.github.nagol2003.blocks.Ognious.ReeshPlant;
import io.github.nagol2003.blocks.Polulos.PolulosDirt;
import io.github.nagol2003.blocks.Polulos.PolulosFerminiumOre;
import io.github.nagol2003.blocks.Polulos.PolulosRock;
import io.github.nagol2003.blocks.Polulos.PolulosSand;
import io.github.nagol2003.blocks.Polulos.ScorchedLeaves;
import io.github.nagol2003.blocks.Polulos.ScorchedLog;
import io.github.nagol2003.blocks.Polulos.StrangeMetallicPolulos;
import io.github.nagol2003.blocks.Tutorial.BlockCrust;
import io.github.nagol2003.blocks.Tutorial.BlockCrust.RockType;
import io.github.nagol2003.blocks.vulcan.VulcanDirt;
import io.github.nagol2003.blocks.vulcan.VulcanRockBurnt;
import io.github.nagol2003.blocks.vulcan.VulcanRockScorched;
import io.github.nagol2003.registry.APRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class InitBlocks {
	
	//tutorial blocks
	public static final Block ROCK_1 = new BlockCrust(RockType.IGNEOUS);
	public static final Block ROCK_2 = new BlockCrust(RockType.METAMORPHIC);
	public static final Block ROCK_3 = new BlockCrust(RockType.SEDIMENTARY);
	
	//Additional Planets blocks
	public static final Block FROZENCO2 = new FROZENCO2();
	public static final Block POLULOSROCK = new PolulosRock();
	public static final Block POLULOSDIRT = new PolulosDirt();
	public static final Block SCORCHEDLOG = new ScorchedLog();
	public static final Block POLULOSSAND = new PolulosSand();
	public static final Block SCORCHEDLEAVES = new ScorchedLeaves();
	public static final Block POLULOSFERMINIUMORE = new PolulosFerminiumOre();
	public static final Block STRANGEMETALLICPOLULOS = new StrangeMetallicPolulos();
	public static final Block OGNIOUSGRASS = new OgniousGrass();
	public static final Block MOISTDIRT= new MoistDirt();
	public static final Block OGNIOUSLOG= new OgniousLog();
	public static final Block OGNIOUSLEAVES= new OgniousLeaves();
	public static final Block OGNIOUSSTONE= new OgniousStone();
	public static final Block OGNIOUSROCK= new OgniousRock();
	public static final Block VULCANDIRT= new VulcanDirt();
	public static final Block VULCANROCKSCORCHED= new VulcanRockScorched();
	public static final Block VULCANROCKBURNT= new VulcanRockBurnt();
	public static final Block DENIASTONE = new deniastone();
	public static final Block DENIAFERMINIUMORE = new DeniaFerminiumOre();
	public static final Block STRANGEMETALLICDENIA = new StrangeMetallicDenia();
	public static final Block REESH_PLANT = new ReeshPlant(null);
	public static final Block REESH_PLANT = new ReeshPlant("reeshplant");
	public static void registerAll (APRegistry registry) {
		CreativeTabs toTab = AdditionalPlanetsTab.blockTab;
		CreativeTabs toTab2 = AdditionalPlanetsTab.AdditionalPlanets;
		registry.registerBlock(ROCK_1, "rock_1").setCreativeTab(toTab);
		registry.registerBlock(ROCK_2, "rock_2").setCreativeTab(toTab);
		registry.registerBlock(ROCK_3, "rock_3").setCreativeTab(toTab);
		registry.registerBlock(FROZENCO2, "frozenco2").setCreativeTab(toTab2);
		registry.registerBlock(POLULOSROCK, "polulosrock").setCreativeTab(toTab2);
		registry.registerBlock(POLULOSDIRT, "polulosdirt").setCreativeTab(toTab2);
		registry.registerBlock(SCORCHEDLOG, "schorchedlog").setCreativeTab(toTab2);
		registry.registerBlock(POLULOSSAND, "polulossand").setCreativeTab(toTab2);
		registry.registerBlock(SCORCHEDLEAVES, "schorchedleaves").setCreativeTab(toTab2);
		registry.registerBlock(POLULOSFERMINIUMORE, "polulosferminiumore").setCreativeTab(toTab2);
		registry.registerBlock(STRANGEMETALLICPOLULOS, "strangemetallicpolulos").setCreativeTab(toTab2);
		registry.registerBlock(OGNIOUSGRASS, "ogniousgrass").setCreativeTab(toTab2);
		registry.registerBlock(MOISTDIRT, "moistdirt").setCreativeTab(toTab2);
		registry.registerBlock(OGNIOUSLOG, "ogniouslog").setCreativeTab(toTab2);
		registry.registerBlock(OGNIOUSLEAVES, "ogniousleaves").setCreativeTab(toTab2);
		registry.registerBlock(OGNIOUSSTONE, "ogniousstone").setCreativeTab(toTab2);
		registry.registerBlock(OGNIOUSROCK, "ogniousrock").setCreativeTab(toTab2);
		registry.registerBlock(VULCANDIRT, "vulcandirt").setCreativeTab(toTab2);
		registry.registerBlock(VULCANROCKSCORCHED, "vulcanrockscorched").setCreativeTab(toTab2);
		registry.registerBlock(VULCANROCKBURNT, "vulcanrockburnt").setCreativeTab(toTab2);
		registry.registerBlock(DENIASTONE, "deniastone").setCreativeTab(toTab2);
		registry.registerBlock(DENIAFERMINIUMORE, "deniaferminiumore").setCreativeTab(toTab2);
		registry.registerBlock(STRANGEMETALLICDENIA, "strangemetallicdenia").setCreativeTab(toTab2);
		registry.registerBlock(REESH_PLANT, "reeshplant").setCreativeTab(toTab2);
	}
	
}
