package com.blueraven.envenhancements.eventhandler;

import com.blueraven.envenhancements.entity.EntityLightningBugFX;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import java.util.Random;

public class LightningBugEvent
{
    public int defaultValue = 0;  //Default value for "timebeforelightningbug"
    public int timeBeforeLightningBug = defaultValue;

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent e)
    {
        if (e.side == Side.CLIENT)  //Makes sure it's on client side only. Would be unnecessary to spawn server-side & could crash game
        {
            Random rand = new Random();

            EntityPlayer player = e.player;
            World theWorld = player.worldObj; //New worldObj

            if (this.checkNight(theWorld, (int) player.posX, (int) player.posY, (int) player.posZ, e))    //Check if night
            {
                if (timeBeforeLightningBug != 0)
                {
                    --timeBeforeLightningBug;   //Count down the time before a lightning bug can spawn.
                }

                int x = (int) player.posX + rand.nextInt(75) - rand.nextInt(75); //Locations (xyz coords) for lightiningbugs to spawn
                int y = (int) player.posY + rand.nextInt(25) - rand.nextInt(25);
                int z = (int) player.posZ + rand.nextInt(75) - rand.nextInt(75);

                boolean canSpawnGrass = (theWorld.getBlock(x, y - 1, z) == Blocks.grass || theWorld.getBlock(x, y - 2, z) == Blocks.grass || theWorld.getBlock(x, y - 3, z) == Blocks.grass || theWorld.getBlock(x, y - 4, z) == Blocks.grass);
                boolean canSpawnLeaves = ((theWorld.getBlock(x, y - 1, z) == Blocks.leaves || theWorld.getBlock(x, y - 2, z) == Blocks.leaves || theWorld.getBlock(x, y - 3, z) == Blocks.leaves || theWorld.getBlock(x, y - 4, z) == Blocks.leaves));
                boolean canSpawnLeaves2 = ((theWorld.getBlock(x, y - 1, z) == Blocks.leaves2 || theWorld.getBlock(x, y - 2, z) == Blocks.leaves2 || theWorld.getBlock(x, y - 3, z) == Blocks.leaves2 || theWorld.getBlock(x, y - 4, z) == Blocks.leaves2));
                int i = rand.nextInt(3);

                if (i == 1 && timeBeforeLightningBug == 0 && theWorld.isAirBlock(x, y, z) && (canSpawnGrass || canSpawnLeaves || canSpawnLeaves2))    //Checks a lot of things to make sure it can spawn
                {
                    System.out.println("Spawned lightningbug");
                    Minecraft mc = Minecraft.getMinecraft();
                    mc.effectRenderer.addEffect(new EntityLightningBugFX(theWorld, x + rand.nextFloat(), y + rand.nextFloat(), z + rand.nextFloat(), 0.0D, 0.0D, 0.0D));
                    timeBeforeLightningBug = defaultValue;  //Sets to default value
                }
            }
        }
    }

    private boolean checkNight(World world, int x, int y, int z, TickEvent.PlayerTickEvent event)
    {   //Copied mostly from BlockDaylightDetector.java - vanilla minecraft block
        //Probably overcomplicated, will figure out the math on this later and simplify
        if (event.side == Side.CLIENT)
        {
            int i1 = world.getSavedLightValue(EnumSkyBlock.Sky, x, y + 300, z) - world.skylightSubtracted;
            float f = world.getCelestialAngleRadians(1.0F);

            if (f < (float) Math.PI)
            {
                f += (0.0F - f) * 0.2F;
            } else
            {
                f += (((float) Math.PI * 2F) - f) * 0.2F;
            }

            i1 = Math.round((float) i1 * MathHelper.cos(f));

            if (i1 < 0)
            {
                i1 = 0;
            }

            if (i1 > 15)
            {
                i1 = 15;
            }

            return i1 < 2;
        }
        return false;
    }
}
