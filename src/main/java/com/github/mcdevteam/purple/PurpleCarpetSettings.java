package com.github.mcdevteam.purple;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import net.minecraft.commands.CommandSourceStack;

public class PurpleCarpetSettings {
    public static final String PURPLE = "purple";
    public static final String CLIENT = "client";

    @Rule(
            desc = "Invert controls direction",
            category = {PURPLE, CLIENT}
    )
    public static int invertControlsDirection = 0;

    private static class NoiceSensibility extends Validator<Double> {
        @Override
        public Double validate(CommandSourceStack source, ParsedRule<Double> currentRule, Double newValue, String string) {
            return newValue == -1 || newValue >= 0 && newValue <= 1? newValue : null;
        }

        @Override
        public String description() {
            return "From 0.00 to 1.00, except for -1";
        }
    }

    @Rule(
            desc = "Microphone emit vibration sensibility",
            extra = "-1 disabled, from 0.0 (whisper) up to 1.0 (scream)",
            validate = NoiceSensibility.class,
            category = {PURPLE, CLIENT}
    )
    public static double microphoneEmitVibration = -1;

    @Rule(
            desc = "Microphone noise will be shown in Actionbar",
            category = {PURPLE, CLIENT}
    )
    public static boolean microphoneNoiseLogger = false;

    @Rule(
            desc = "Entities will have a solid collision, like Shulkers and boats",
            category = {PURPLE, CLIENT}
    )
    public static boolean solidCollisionEntity = false;

    @Rule(
            desc = "All Entities will be rendered upside-down, like Dinnerbone",
            category = {PURPLE, CLIENT}
    )
    public static boolean upsideDownEntities = false;


}
