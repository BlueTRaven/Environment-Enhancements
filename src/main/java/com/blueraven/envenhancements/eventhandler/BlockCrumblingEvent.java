package com.blueraven.envenhancements.eventhandler;

import com.blueraven.envenhancements.ConfigurationEE;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCrumblingEvent
{
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent e)
    {
        if (ConfigurationEE.canSpawnBC)
        {
            if (e.side == Side.CLIENT)
            {
                EntityPlayer player = e.player;
                World theWorld = player.worldObj; //New worldObj

                Random rand = new Random();

                checkAndSpawn(theWorld, player, rand);
            }
        }
    }

    public void checkAndSpawn(World theWorld, EntityPlayer player, Random rand)
    {
        int x = (int) player.posX + rand.nextInt(ConfigurationEE.spawnRangeBCX) - rand.nextInt(ConfigurationEE.spawnRangeBCX);
        int y = (int) player.posY + rand.nextInt(ConfigurationEE.spawnRangeBCY) - rand.nextInt(ConfigurationEE.spawnRangeBCY);
        int z = (int) player.posZ + rand.nextInt(ConfigurationEE.spawnRangeBCZ) - rand.nextInt(ConfigurationEE.spawnRangeBCZ);

        if (theWorld.isAirBlock(x, y - 1, z) && theWorld.getBlock(x, y, z).getMaterial() == Material.rock && theWorld.getBlock(x, y, z) != Blocks.bedrock)
        {
            int i = rand.nextInt(ConfigurationEE.spawnRateBC);
            if (i == 1)
            {
                for (int j = 0; j < 10; j++)
                {
                    player.worldObj.spawnParticle("blockcrack_1_0", x + rand.nextFloat(), y - 0.1F, z + rand.nextFloat(), 0.0D, 0.0D, 0.0D);
                    player.worldObj.playSound(x, y, z, "dig.stone", rand.nextFloat() + 0.5F, 0.2F, true);
                }
            }
        }
    }
}
