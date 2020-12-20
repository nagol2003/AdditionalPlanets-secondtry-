package io.github.nagol2003.celestial.moons.junsin;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import io.github.nagol2003.Const;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.client.FMLClientHandler;

public class SkyProviderJunsin extends IRenderHandler {
	
	private static final ResourceLocation sunTexture = new ResourceLocation(Const.ASSET_PREFIX, "textures/gui/celestialbodies/radonstar.png");

	public int starList;
	public int glSkyList;
	public int glSkyList2;
	private float sunSize;
	
	public SkyProviderJunsin(IGalacticraftWorldProvider JunsinProvider) {
		this.sunSize = 0.5F * JunsinProvider.getSolarSize() * 7;

		int displayLists = GLAllocation.generateDisplayLists(3);
		this.starList = displayLists;
		this.glSkyList = displayLists + 1;
		this.glSkyList2 = displayLists + 2;

		// Bind stars to display list
		GL11.glPushMatrix();
		GL11.glNewList(this.starList, GL11.GL_COMPILE);
		this.renderStars();
		GL11.glEndList();
		GL11.glPopMatrix();

		final Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder worldRenderer = tessellator.getBuffer();
		GL11.glNewList(this.glSkyList, GL11.GL_COMPILE);
		final byte byte2 = 64;
		final int i = 256 / byte2 + 2;
		float f = 16F;

		for (int j = -byte2 * i; j <= byte2 * i; j += byte2) {
			for (int l = -byte2 * i; l <= byte2 * i; l += byte2) {
				worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
				worldRenderer.pos(j, f, l).endVertex();
				worldRenderer.pos(j + byte2, f, l).endVertex();
				worldRenderer.pos(j + byte2, f, l + byte2).endVertex();
				worldRenderer.pos(j, f, l + byte2).endVertex();
				tessellator.draw();
			}
		}

		GL11.glEndList();
		GL11.glNewList(this.glSkyList2, GL11.GL_COMPILE);
		f = -16F;
		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);

		for (int k = -byte2 * i; k <= byte2 * i; k += byte2) {
			for (int i1 = -byte2 * i; i1 <= byte2 * i; i1 += byte2) {
				worldRenderer.pos(k + byte2, f, i1).endVertex();
				worldRenderer.pos(k, f, i1).endVertex();
				worldRenderer.pos(k, f, i1 + byte2).endVertex();
				worldRenderer.pos(k + byte2, f, i1 + byte2).endVertex();
			}
		}

		tessellator.draw();
		GL11.glEndList();
	}

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		Vec3d vec3 = world.getSkyColor(mc.getRenderViewEntity(), partialTicks);
		float f1 = (float) vec3.x;
		float f2 = (float) vec3.y;
		float f3 = (float) vec3.z;
		float f6;

		if (mc.gameSettings.anaglyph) {
			float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
			float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
			f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
			f1 = f4;
			f2 = f5;
			f3 = f6;
		}

		GL11.glColor3f(f1, f2, f3);
		Tessellator tessellator1 = Tessellator.getInstance();
		BufferBuilder worldRenderer1 = tessellator1.getBuffer();
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_FOG);
		GL11.glColor3f(f1, f2, f3);
		GL11.glCallList(this.glSkyList);
		GL11.glDisable(GL11.GL_FOG);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		RenderHelper.disableStandardItemLighting();
		float f7;
		float f8;
		float f9;
		float f10;

		float f18 = world.getStarBrightness(partialTicks);

		if (f18 > 0.0F) {
			GL11.glPushMatrix();
			GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(-19.0F, 0, 1.0F, 0);
			GL11.glColor4f(f18, f18, f18, f18);
			GL11.glCallList(this.starList);
			GL11.glPopMatrix();
		}

		float[] afloat = new float[4];
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glPushMatrix();
		GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
		afloat[0] = 255 / 255.0F;
		afloat[1] = 194 / 255.0F;
		afloat[2] = 180 / 255.0F;
		afloat[3] = 0.3F;
		f6 = afloat[0];
		f7 = afloat[1];
		f8 = afloat[2];
		float f11;

		if (mc.gameSettings.anaglyph) {
			f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
			f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
			f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
			f6 = f9;
			f7 = f10;
			f8 = f11;
		}

		f18 = 1.0F - f18;

		worldRenderer1.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
		float r = f6 * f18;
		float g = f7 * f18;
		float b = f8 * f18;
		float a = afloat[3] * 2 / f18;
		worldRenderer1.pos(0.0D, 100.0D, 0.0D).color(r, g, b, a).endVertex();
		r = afloat[0] * f18;
		g = afloat[1] * f18;
		b = afloat[2] * f18;
		a = 0.0F;

		// Render sun aura
		f10 = 20.0F;
		worldRenderer1.pos(-f10, 100.0D, -f10).color(r, g, b, a).endVertex();
		worldRenderer1.pos(0, 100.0D, (double) -f10 * 1.5F).color(r, g, b, a).endVertex();
		worldRenderer1.pos(f10, 100.0D, -f10).color(r, g, b, a).endVertex();
		worldRenderer1.pos((double) f10 * 1.5F, 100.0D, 0).color(r, g, b, a).endVertex();
		worldRenderer1.pos(f10, 100.0D, f10).color(r, g, b, a).endVertex();
		worldRenderer1.pos(0, 100.0D, (double) f10 * 1.5F).color(r, g, b, a).endVertex();
		worldRenderer1.pos(-f10, 100.0D, f10).color(r, g, b, a).endVertex();
		worldRenderer1.pos((double) -f10 * 1.5F, 100.0D, 0).color(r, g, b, a).endVertex();
		worldRenderer1.pos(-f10, 100.0D, -f10).color(r, g, b, a).endVertex();

		tessellator1.draw();
		worldRenderer1.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
		r = f6 * f18;
		g = f7 * f18;
		b = f8 * f18;
		a = afloat[3] * f18;
		worldRenderer1.pos(0.0D, 100.0D, 0.0D).color(r, g, b, a).endVertex();
		r = afloat[0] * f18;
		g = afloat[1] * f18;
		b = afloat[2] * f18;
		a = 0.0F;

		// Render larger sun aura
		f10 = 40.0F;
		worldRenderer1.pos(-f10, 100.0D, -f10).color(r, g, b, a).endVertex();
		worldRenderer1.pos(0, 100.0D, (double) -f10 * 1.5F).color(r, g, b, a).endVertex();
		worldRenderer1.pos(f10, 100.0D, -f10).color(r, g, b, a).endVertex();
		worldRenderer1.pos((double) f10 * 1.5F, 100.0D, 0).color(r, g, b, a).endVertex();
		worldRenderer1.pos(f10, 100.0D, f10).color(r, g, b, a).endVertex();
		worldRenderer1.pos(0, 100.0D, (double) f10 * 1.5F).color(r, g, b, a).endVertex();
		worldRenderer1.pos(-f10, 100.0D, f10).color(r, g, b, a).endVertex();
		worldRenderer1.pos((double) -f10 * 1.5F, 100.0D, 0).color(r, g, b, a).endVertex();
		worldRenderer1.pos(-f10, 100.0D, -f10).color(r, g, b, a).endVertex();

		tessellator1.draw();
		GL11.glPopMatrix();
		GL11.glShadeModel(GL11.GL_FLAT);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ONE, GL11.GL_ZERO);
		GL11.glPushMatrix();
		f7 = 0.0F;
		f8 = 0.0F;
		f9 = 0.0F;
		GL11.glTranslatef(f7, f8, f9);
		GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
		// Render sun
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
		// Some blanking to conceal the stars
		f10 = this.sunSize / 3.5F;
		worldRenderer1.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		worldRenderer1.pos(-f10, 99.9D, -f10).endVertex();
		worldRenderer1.pos(f10, 99.9D, -f10).endVertex();
		worldRenderer1.pos(f10, 99.9D, f10).endVertex();
		worldRenderer1.pos(-f10, 99.9D, f10).endVertex();
		tessellator1.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		f10 = this.sunSize;
		mc.renderEngine.bindTexture(SkyProviderJunsin.sunTexture);
		worldRenderer1.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		worldRenderer1.pos(-f10, 100.0D, -f10).tex(0.0D, 0.0D).endVertex();
		worldRenderer1.pos(f10, 100.0D, -f10).tex(1.0D, 0.0D).endVertex();
		worldRenderer1.pos(f10, 100.0D, f10).tex(1.0D, 1.0D).endVertex();
		worldRenderer1.pos(-f10, 100.0D, f10).tex(0.0D, 1.0D).endVertex();
		tessellator1.draw();

		GL11.glRotatef(360.0F, 1.0F, 0.0F, 0.0F);

	}

	private void renderStars() {
		final Random rand = new Random(10842L);
		final Tessellator var2 = Tessellator.getInstance();
		BufferBuilder worldRenderer = var2.getBuffer();
		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);

		for (int starIndex = 0; starIndex < (6000); ++starIndex) {
			double var4 = rand.nextFloat() * 2.0F - 1.0F;
			double var6 = rand.nextFloat() * 2.0F - 1.0F;
			double var8 = rand.nextFloat() * 2.0F - 1.0F;
			final double var10 = 0.15F + rand.nextFloat() * 0.1F;
			double var12 = var4 * var4 + var6 * var6 + var8 * var8;

			if (var12 < 1.0D && var12 > 0.01D) {
				var12 = 1.0D / Math.sqrt(var12);
				var4 *= var12;
				var6 *= var12;
				var8 *= var12;
				final double var14 = var4 * (rand.nextDouble() * 150D + 130D);
				final double var16 = var6 * (rand.nextDouble() * 150D + 130D);
				final double var18 = var8 * (rand.nextDouble() * 150D + 130D);
				final double var20 = Math.atan2(var4, var8);
				final double var22 = Math.sin(var20);
				final double var24 = Math.cos(var20);
				final double var26 = Math.atan2(Math.sqrt(var4 * var4 + var8 * var8), var6);
				final double var28 = Math.sin(var26);
				final double var30 = Math.cos(var26);
				final double var32 = rand.nextDouble() * Math.PI * 2.0D;
				final double var34 = Math.sin(var32);
				final double var36 = Math.cos(var32);

				for (int var38 = 0; var38 < 4; ++var38) {
					final double var39 = 0.0D;
					final double var41 = ((var38 & 2) - 1) * var10;
					final double var43 = ((var38 + 1 & 2) - 1) * var10;
					final double var47 = var41 * var36 - var43 * var34;
					final double var49 = var43 * var36 + var41 * var34;
					final double var53 = var47 * var28 + var39 * var30;
					final double var55 = var39 * var28 - var47 * var30;
					final double var57 = var55 * var22 - var49 * var24;
					final double var61 = var49 * var22 + var55 * var24;
					worldRenderer.pos(var14 + var57, var16 + var53, var18 + var61).endVertex();
				}
			}
		}

		var2.draw();
	}

	public float getSkyBrightness(float par1) {
		final float var2 = FMLClientHandler.instance().getClient().world.getCelestialAngle(par1);
		float var3 = 1.0F - (MathHelper.sin(var2 * Constants.twoPI) * 2.0F + 0.25F);

		if (var3 < 0.0F) {
			var3 = 0.0F;
		}

		if (var3 > 1.0F) {
			var3 = 1.0F;
		}

		return var3 * var3 * 1F;
	}

}