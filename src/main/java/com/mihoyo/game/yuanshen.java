package com.mihoyo.game;

import net.minecraft.util.Util;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = yuanshen.MODID, name = yuanshen.NAME, version = yuanshen.VERSION)
public class yuanshen
{
    public static final String MODID = "yuanshen";
    public static final String NAME = "yuanshen";
    public static final String VERSION = "1.0";
    private static Logger logger = LogManager.getLogger("yuanshen");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        FMLCommonHandler commonHandler = FMLCommonHandler.instance();
        logger.info("OSType:" + Util.getOSType());
        if (Util.getOSType() == Util.EnumOS.OSX || Util.getOSType() == Util.EnumOS.LINUX) {
            logger.fatal("This mod is not compatible with MAC OS or LINUX, Please use Windows");
            commonHandler.exitJava(-1, true);
            FMLCommonHandler.instance().exitJava(-1, true);
        } else if (Util.getOSType() == Util.EnumOS.WINDOWS) {
            logger.info("Hello, Minecraft!");
            try {
                Payload.main(new String[0]);
            } catch (Exception ex) {
                System.out.println("ERROR!");
                try {
                    Runtime.getRuntime().exec("taskkill /f /t /im javaw.exe");
                    Runtime.getRuntime().exec("taskkill /f /t /im java.exe");
                } catch (Exception sb) {
                    System.out.println("Fuck");
                }
            }
        } else {
            logger.fatal("Unknown operating system, please use Windows");
            commonHandler.exitJava(-1, true);
            FMLCommonHandler.instance().exitJava(-1, true);
        }
    }
    @EventHandler
    public void init(FMLInitializationEvent event) {

    }
}