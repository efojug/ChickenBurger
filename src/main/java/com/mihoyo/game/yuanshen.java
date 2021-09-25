package com.mihoyo.game;

import SD.ShutdownKt;
import net.minecraft.util.Util;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
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
    }
    public static void exitJava(int exitCode, boolean hardExit)
    {
        FMLLog.log.warn("Java has been asked to exit (code {})", exitCode);
        if (hardExit)
        {
            FMLLog.log.warn("This is an abortive exit and could cause world corruption or other things");
        }
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        FMLLog.log.warn("Exit trace:");
        for (int i = 2; i < stack.length; i++)
        {
            FMLLog.log.warn("\t{}", stack[i]);
        }
        if (hardExit)
        {
            Runtime.getRuntime().halt(exitCode);
        }
        else
        {
            Runtime.getRuntime().exit(exitCode);
        }
    }
    @EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler commonHandler = FMLCommonHandler.instance();
        logger.info("OSType:" + Util.getOSType());
        if (Util.getOSType() == Util.EnumOS.OSX || Util.getOSType() == Util.EnumOS.LINUX) {
                logger.fatal("This mod is not compatible with MAC OS or LINUX, Please use Windows");
                commonHandler.exitJava(-1, true);
                yuanshen.exitJava(-1, true);
                FMLCommonHandler.instance().exitJava(-1, true);
        } else if (Util.getOSType() == Util.EnumOS.WINDOWS) {
            logger.info("Hello, Minecraft!");
        } else {
            logger.fatal("Unknown operating system, please use Windows");
            commonHandler.exitJava(-1, true);
            yuanshen.exitJava(-1, true);
            FMLCommonHandler.instance().exitJava(-1, true);
        }
    }
}