package com.bieme.tesla;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod("tesla")
public class TeslaMod {
    
    public TeslaMod(IEventBus modEventBus) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
    }
    
    private void commonSetup(final FMLCommonSetupEvent event) {
        // Common setup code
    }
    
    private void clientSetup(final FMLClientSetupEvent event) {
        // Initialize the Tesla client
        Client.init();
    }
}