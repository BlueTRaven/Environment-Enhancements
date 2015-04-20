package com.blueraven.envenhancements;

import com.blueraven.envenhancements.eventhandler.BlockCrumblingEvent;
import com.blueraven.envenhancements.eventhandler.LightningBugEvent;
import com.blueraven.envenhancements.proxy.IProxy;
import com.blueraven.envenhancements.reference.Reference;
import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import java.sql.Ref;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class EnvEnhancements
{
    @Mod.Instance(Reference.MOD_ID)
    public static EnvEnhancements instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS,serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    public static Configuration config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new LightningBugEvent());
        MinecraftForge.EVENT_BUS.register(new BlockCrumblingEvent());
        FMLCommonHandler.instance().bus().register(new LightningBugEvent());
        FMLCommonHandler.instance().bus().register(new BlockCrumblingEvent());

        config = new Configuration(event.getSuggestedConfigurationFile());
        ConfigurationEE.syncConfig();
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {}

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {}

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equals(Reference.MOD_ID))
            ConfigurationEE.syncConfig();
    }
}
