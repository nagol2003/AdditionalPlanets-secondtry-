package io.github.nagol2003.world.gen.tree;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class APGenTreeWillow extends APGenTreeBase {

	public APGenTreeWillow() {
		super(true);
	}

	@Override
	public boolean generate (World world, Random rand, BlockPos pos) {

		if (!this.isGroundValid(world, pos, true)) {
			return false;
		}

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		int height       = 13;
		int leaveheight  = 5;
		int branches     = 6;
		int branchLenght = 6;

		for (int i = 0; i < height; i++) {
			this.placeLogBlock(world, new BlockPos(x, y + i, z), this.logBlock, this.generateFlag);
		}
		createLeavesAroundBranch(world, rand, x, y + height, z, 3, 2);
		createTrunk(world, rand, x, y, z);

		int     dir = rand.nextInt((int) (360f / branches));
		float   xd, yd, hd, c;
		boolean m;
		for (int b = 0; b < branches; b++) {
			c    = 0;
			hd   = height - rand.nextFloat() * leaveheight - 2f;
			dir += (int) (360f / branches);
			xd   = (float) Math.cos(dir * Math.PI / 180f);
			yd   = (float) Math.sin(dir * Math.PI / 180f);
			m    = false;

			while (c < branchLenght) {
				if (c > branchLenght / 2 && !m) {
					m = true;
					createLeavesAroundBranch(world, rand, x + (int) (c * xd), y + (int) hd, z + (int) (c * yd), 2, 1);
				}
				c++;
				hd += 0.5f;

				this.placeLogBlock(world, new BlockPos(x + (int) (c * xd), y + (int) hd, z
						+ (int) (c * yd)), this.logBlock, this.generateFlag);
			}
			createLeavesAroundBranch(world, rand, x + (int) (c * xd), y + (int) hd, z + (int) (c * yd), 2, 1);
		}

		return true;
	}

	private void createLeavesAroundBranch (World world, Random rand, int x, int y, int z, int s, int c) {

		int l;
		int t = (int) Math.pow(s, 2);
		for (int i = -s; i <= s; i++) {
			for (int j = -s; j <= s; j++) {
				for (int k = -s; k <= s; k++) {
					l = i * i + j * j + k * k;
					if (l <= t) {
						if ((l < t - c || rand.nextBoolean())) {
							if (!this.noLeaves) {

								this.placeLeavesBlock(world, new BlockPos(x + i, y + j, z
										+ k), this.leavesBlock, this.generateFlag);
								if (j < -(s - 2) && rand.nextInt(3) != 0) {
									createVine(world, rand, x + i, y + j, z + k);
								}
							}
						}
					}
				}
			}
		}
	}

	private void createVine (World world, Random rand, int x, int y, int z) {

		int r = rand.nextInt(3) + 5;
		for (int i = -1; i > -r; i--) {
			this.placeLeavesBlock(world, new BlockPos(x, y + i, z), this.leavesBlock, this.generateFlag);
		}
	}

	private void createTrunk (World world, Random rand, int x, int y, int z) {

		int[] pos = new int[] { 0, 0, 1, 0, 0, 1, -1, 0, 0, -1 };
		int   sh;

		for (int t = 0; t < 5; t++) {
			sh = rand.nextInt(3) + y;
			while (sh > y - 3) {
				this.placeLogBlock(world, new BlockPos(x + pos[t * 2], sh, z
						+ pos[t * 2 + 1]), this.logBlock, this.generateFlag);
				sh--;
			}
		}
	}
}
