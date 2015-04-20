package com.blueraven.envenhancements;

import com.blueraven.envenhancements.reference.Reference;
import com.sun.org.apache.xerces.internal.impl.dv.dtd.ENTITYDatatypeValidator;
import cpw.mods.fml.common.FMLCommonHandler;

public class ConfigurationEE
{
    public static boolean canSpawnLB;
    public static boolean canSpawnBC;
    public static final boolean CANSPAWNLB_DEFAULT = true;
    public static final boolean CANSPAWNBC_DEFAULT = true;

    public static int spawnRangeLBX;
    public static int spawnRangeLBY;
    public static int spawnRangeLBZ;

    public static int spawnRangeBCX;
    public static int spawnRangeBCY;
    public static int spawnRangeBCZ;

    public static final int SPAWNRANGELBX_DEFAULT = 75;
    public static final int SPAWNRANGELBY_DEFAULT = 25;
    public static final int SPAWNRANGELBZ_DEFAULT = 75;

    public static final int SPAWNRANGEBCX_DEFAULT = 50;
    public static final int SPAWNRANGEBCY_DEFAULT = 25;
    public static final int SPAWNRANGEBCZ_DEFAULT = 50;

    public static int spawnRateLB;
    public static int spawnRateBC;

    public static final int SPAWNRATELB_DEFAULT = 3;
    public static final int SPAWNRATEBC_DEFAULT = 2;


    public static void syncConfig()
    {
        FMLCommonHandler.instance().bus().register(EnvEnhancements.instance);

        final String CANSPAWN = EnvEnhancements.config.CATEGORY_GENERAL + EnvEnhancements.config.CATEGORY_SPLITTER + "Spawn Toggle";
        EnvEnhancements.config.addCustomCategoryComment(CANSPAWN, "Configures whether or not certain effects will spawn.");

        canSpawnLB = EnvEnhancements.config.get(CANSPAWN, "LightningBug_Spawn", CANSPAWNLB_DEFAULT).getBoolean(CANSPAWNLB_DEFAULT);
        canSpawnBC = EnvEnhancements.config.get(CANSPAWN, "BlockCrumbling_Spawn", CANSPAWNBC_DEFAULT).getBoolean(CANSPAWNBC_DEFAULT);

        final String SPAWNRANGE = EnvEnhancements.config.CATEGORY_GENERAL + EnvEnhancements.config.CATEGORY_SPLITTER + "Spawn Range";
        EnvEnhancements.config.addCustomCategoryComment(SPAWNRANGE, "Configures the spawn ranges of the different effects. Please note that higher numbers can mean that you \nwill see less, as they are spread out.");

        spawnRangeLBX = EnvEnhancements.config.get(SPAWNRANGE, "LightningBug_SpawnRange_X", SPAWNRANGELBX_DEFAULT).getInt(SPAWNRANGEBCX_DEFAULT);
        spawnRangeLBY = EnvEnhancements.config.get(SPAWNRANGE, "LightningBug_SpawnRange_Y", SPAWNRANGELBY_DEFAULT).getInt(SPAWNRANGEBCY_DEFAULT);
        spawnRangeLBZ = EnvEnhancements.config.get(SPAWNRANGE, "LightningBug_SpawnRange_Z", SPAWNRANGELBZ_DEFAULT).getInt(SPAWNRANGEBCZ_DEFAULT);

        spawnRangeBCX = EnvEnhancements.config.get(SPAWNRANGE, "BlockCrumbling_SpawnRange_X", SPAWNRANGEBCX_DEFAULT).getInt(SPAWNRANGEBCX_DEFAULT);
        spawnRangeBCY = EnvEnhancements.config.get(SPAWNRANGE, "BlockCrumbling_SpawnRange_Y", SPAWNRANGEBCY_DEFAULT).getInt(SPAWNRANGEBCY_DEFAULT);
        spawnRangeBCZ = EnvEnhancements.config.get(SPAWNRANGE, "BlockCrumbling_SpawnRange_Z", SPAWNRANGEBCZ_DEFAULT).getInt(SPAWNRANGEBCZ_DEFAULT);

        final String SPAWNRATE = EnvEnhancements.config.CATEGORY_GENERAL + EnvEnhancements.config.CATEGORY_SPLITTER + "Spawn Rate";
        EnvEnhancements.config.addCustomCategoryComment(SPAWNRATE, "Configures the spawn rate of effects.\n Please note that even small changes to this can result in drastic differences, so make sure you know what you're doing.");

        spawnRateLB = EnvEnhancements.config.get(SPAWNRATE, "LightningBug_Spawnrate", SPAWNRATELB_DEFAULT).getInt(SPAWNRATELB_DEFAULT);
        spawnRateBC = EnvEnhancements.config.get(SPAWNRATE, "BlockCrumbling_SpawnRate", SPAWNRATEBC_DEFAULT).getInt(SPAWNRATEBC_DEFAULT);

        if (EnvEnhancements.config.hasChanged())
            EnvEnhancements.config.save();
    }
}
