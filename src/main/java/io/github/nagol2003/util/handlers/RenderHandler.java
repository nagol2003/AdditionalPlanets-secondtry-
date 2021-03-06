package io.github.nagol2003.util.handlers;

import io.github.nagol2003.entities.deniamammal.EntityMammal;
import io.github.nagol2003.entities.deniamammal.RenderMammal;
import io.github.nagol2003.entities.pigman.EntityPigman;
import io.github.nagol2003.entities.pigman.RenderPigman;
import io.github.nagol2003.entities.poluloscrab.EntityPolulosCrab;
import io.github.nagol2003.entities.poluloscrab.RenderCrab;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
	
	public static void registerEntityRenders() {
		
		RenderingRegistry.registerEntityRenderingHandler(EntityPolulosCrab.class, new IRenderFactory<EntityPolulosCrab>() {
			@Override
			public Render<? super EntityPolulosCrab> createRenderFor(RenderManager manager) {
				return new RenderCrab(manager);
			}
		});
		
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMammal.class, new IRenderFactory<EntityMammal>() {
			@Override
			public Render<? super EntityMammal> createRenderFor(RenderManager manager) {
				return new RenderMammal(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityPigman.class, new IRenderFactory<EntityPigman>() {
			@Override
			public Render<? super EntityPigman> createRenderFor(RenderManager manager) {
				return new RenderPigman(manager);
			}
		});
		
		
	}

}
