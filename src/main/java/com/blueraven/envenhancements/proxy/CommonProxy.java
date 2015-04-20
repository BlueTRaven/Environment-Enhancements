package com.blueraven.envenhancements.proxy;

import com.blueraven.envenhancements.handler.client.ConfigurationHandler;
import cpw.mods.fml.common.FMLCommonHandler;

public abstract class CommonProxy implements IProxy
{
    public void registerEventHandlers()
    {
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
    }

}
