package com.blueraven.envenhancements;

import com.blueraven.envenhancements.init.ModEvents;
import com.blueraven.envenhancements.proxy.IProxy;
import com.blueraven.envenhancements.reference.Reference;
import com.blueraven.envenhancements.utility.LogHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class EnvEnhancements
{
    @Mod.Instance(Reference.MOD_ID)
    public static EnvEnhancements instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS,serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {}

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event)
    {
        ModEvents.init();
        LogHelper.info("[INIT] Events have finished registering...");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {}
}
