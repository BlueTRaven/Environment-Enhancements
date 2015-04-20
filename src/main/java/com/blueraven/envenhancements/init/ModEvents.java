package com.blueraven.envenhancements.init;

import com.blueraven.envenhancements.eventhandler.*;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class ModEvents
{
    public static void init()
    {
        MinecraftForge.EVENT_BUS.register(new LightningBugEvent());
        MinecraftForge.EVENT_BUS.register(new BlockCrumblingEvent());
        //FMLCommonHandler
        //FMLCommonHandler.instance().bus().register(new LightningBugEvent());
        //FMLCommonHandler.instance().bus().register(new BlockCrumblingEvent());
    }
}
