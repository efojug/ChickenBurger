package net.mcreator.burger.mixin.mixins;

import com.mihoyo.game.LoginGUI;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {Minecraft.class})
public abstract class MixinMinecraft {
    @Inject(method = "init" , at = @At("HEAD"))
    public void onInit(CallbackInfo ci) {
        new Thread(() -> {
            LoginGUI gui = new LoginGUI();
            gui.launch();
        }).start();

        while (true) {
            try
            {
                Thread.sleep(2500);
                if (LoginGUI.isSuccess) break;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
