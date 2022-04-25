# Purple Fabric Carpet Extension

[Back to index](readme.md)

# Scarpet Functions

## Client Options
### `client_option()`, `client_option(player, option, value)`

With no arguments, it returns all available options as a list.

Sends a custom packet to the `player` to ask its client to set an `option` to a `value`.
It requires the mod on the client too.

### Aviable options:

- `'dark_mojang_studios_background'`: `true` or `false`. Changes the Mojang Studios loading screen color from red to black.
- `'hide_lightning_flash'`: `true` or `false`. Prevents the screen from flashing when lightning strikes. The lightning bolts themselves are still visible.
- `'sensitivity'`: a real number. Changes the sensitivity of the camera when turning.
- `'render_distance'`: an integer number. The render distance controls how many chunks of the world are visible at once.
- `'simulation_distance'`: an integer number. Simulation distance controls the number of chunks in which entities are updated, and blocks and fluids are ticked.
- `'entity_distance_scaling'`: a real number. Adjusts the distance that entities can be seen as a percentage of 160 blocks.
- `'framerate_limit'`: an integer number. Limits the FPS. Limiting the framerate to a multiple of the player monitor's refresh rate can save processing power.
- `'cloud_status'`
    - `2`: **Fancy** causes the clouds to be rendered in 3D.
    - `1`: **Fast** causes the clouds to be rendered flat.
    - `0`: **OFF** causes no clouds to be rendered.
- `'graphics_mode'`
    - `2`: **Fabulous!** graphics uses screen shaders for drawing weather, clouds and particles behind translucent blocks and water.
    - `1`: **Fancy** enables higher quality graphic effects. Graphic effects include transparent leaves, increased rain particles, a black vignette around the screen in darker areas, and the world border red warning vignette effect.
    - `0`: **Fast** disables those effects for better performance.
- `'ambient_occlusion'` Lighting is smoothed across the blocks (ambient occlusion) or each block has a distinctive light level.
    - `2`: **Maximum**
    - `1`: **Minimum**
    - `0`: **Off**
- `'prioritize_chunk_updates'` Determines which chunk sections are updated synchronously during a single frame.
    - `0`: **Threaded** has nearby chunks compiled in parallel threads, which may result in visual holes when blocks are destroyed.
    - `1`: **Semi Blocking** has chunks recompile immediately when some actions occur, including placing or destroying blocks.
    - `2`: **Fully Blocking** defaults to the behavior used prior to Java Edition 1.18, in which chunks always compile immediately, which may affect performance when placing or destroying blocks. 
- `'chat_visibility'`
    - `0`: **Shown** enables the player's ability to see the chat on a server.
    - `1`: **Commands Only** allows the player to see only the output of commands in the chat.
    - `2`: **Hidden** hides the chat on all servers.
- `'chat_opacity'`: a real number. The opacity of the chat text.
- `'chat_line_spacing'`: a real number. The spacing between texts.
- `'text_background_opacity'`: a real number. The opacity of the text background.
- `'main_hand'` Switches the main hand, between left and right.
    - `1`: **Right**
    - `0`: **Left**
- `'chat_scale'`: a real number. How large the chat is shown.
- `'chat_width'`: a real number. The max width that the chat may appear.
- `'chat_height_unfocused'`: a real number. The max height that the chat is allowed to appear normally (chat input and history closed).
- `'chat_height_focused'`: a real number. The max height that the chat is allowed to appear when in focus (chat input and history open).
- `'chat_delay'`: a real number. Delays chat messages.
- `'mipmap_levels'`: an integer number. The higher the level, the more the textures look "smooth".
- `'attack_indicator'`
    - `1`: **Crosshair** A little gray translucent bar appears below the crosshair displaying attack power.
    - `2`: **Hotbar** A little gray sword bar appears on the side of the hotbar (on the same side as the player's main hand) displaying attack power
    - `0`: **OFF** No attack indicator renders.
- `'biome_blend_radius'`: an integer number. Sets the distance of color transitions between biomes.
- `'mouse_wheel_sensitivity'`: a real number. Changes the sensitivity of the scroll wheel in-game.
- `'raw_mouse_input'`: `true` or `false`. Takes the mouse input directly, instead of from the mouse cursor. This means that mouse movement is not affected by cursor acceleration or scaling, such as Windows' built in "Enhace pointer precision" and "Pointer speed".
- `'auto_jump'`: `true` or `false`. Enables automatically jumping when the player passes near a one block high wall.
- `'auto_suggestions'`: `true` or `false`. Toggles if command suggestions show up or not, if off the player has to press tab to bring them up.
- `'chat_colors'`: `true` allows the player to see the chat in many different colors, `false` disables this function and allows the player to see the chat only in a white color.
- `'chat_links'`: `true` allows the player to see URLs and other links in the chat, `false` disables this function and blocks all URLs and other links.
- `'chat_links_prompt'`: `true` forces a prompt to appear on the player's screen whenever they click on a URL to makes sure that they wish to exit Minecraft and head to the website, `false` turns the prompt off.
- `'enable_vsync'`: `true` or `false`. Limits the player's frames per second to the screen's refresh rate, which is usually 60 Hz, 75 Hz, or 120 to 240 Hz on some gaming-targeted screens.
- `'entity_shadows'`: `true` or `false`. Determines whether entities display simple shadows.
- `'force_unicode_font'`: `true` the game uses Unicode Font, `false` the game uses ASCII Font
- `'invert_y_mouse'`: `true` or `false`. Inverts vertical axis of the mouse when looking in-game.
- `'discrete_mouse_scroll'`: `true` or `false`. Tells the game to ignore scroll magnitude values given by the operating system and instead to act as if the operating system supplied −1 or +1.
- `'realms_notifications'`: `true` or `false`. Toggles realms notifications.
- `'allow_server_listing'`: `true` or `false`. Toggles whether the player name will show up in server listings.
- `'reduced_debug_info'`: `true` or `false`. Toggles reduced information on the debug screen.
- `'show_subtitles'`: `true` or `false`. Toggles subtitles.
- `'directional_audio'`: `true` or `false` defaults to stereo sound.
- `'background_for_chat_only'`: `true` or `false`
- `'touchscreen'`: `true`uses HRTF (head related transfer function) based directional audio to improve simulation of 3D sound, which requires HRTF-compatible hardware and is best experienced when using headphones, `false`. Toggles touchscreen mode, intended for touchscreen devices.
- `'fullscreen'`: `true` or `false`. Sets the resolution and framerate of the game when playing in fullscreen.
- `'bob_view'`: `true` or `false`. Toggles the "bobbing" motion of the camera as the player is walking. Disabling it may increase performance.
- `'toggle_crouch'`: `true` or `false`. Enables players to stay sneaked.
- `'toggle_sprint'`: `true` or `false`. Enables players to stay sprinting.
- `'hide_matched_names'`: `true` or `false`. Toggles if the game will attempt to apply chat hiding by matching the text in messages.
- `'show_autosave_indicator'`: `true` or `false`. Toggles the autosave icon.
- `'fov'`: an integer number. Changes the field of view.
- `'screen_effect_scale'`: a real number. Controls the GUI (Graphical User Interface) scale. This also controls the HUD (Heads Up Display) size.
- `'fov_effect_scale'`: a real number. Changes the FOV effects of anything affecting them.
- `'darkness_effect_scale'`: a real number. Changes how dark the Darkness effect gets when a warden or sculk shrieker gives it to the player. Fog distance remains unaffected.
- `'gamma'`: a real number. Dims down or lights up the game's surfaces, even if fully dark. It has no effect on gameplay, it is a visual effect.
- `'gui_scale'`: an integer number. Controls the GUI (Graphical User Interface) scale. This also controls the HUD (Heads Up Display) size.
- `'particles'`
    - `0`: **All** allows all particles.
    - `1`: **Decreased** reduces the amount of particles for some particle events.
    - `2`: **Minimal** reduces particles further and disables some non-essential particles.
- `'narrator'`
    - `1`: **All** narrates everything.
    - `2`: **Chat** narrates chat & system.
    - `3`: **System** narrates system.
    - `0`: **OFF** Narrates nothing.
- `'sound_device'`: the Device name. Switch audio device.
  
*Option descriptions from [Minecraft Wiki](https://minecraft.fandom.com/wiki/Options)*

## Client Shaders
### `client_shaders(player, namespace?, value)`

Sends a custom packet to the `player` to ask its client to set a specific Shader as post shader.

Shaders will be loaded from `<[namespace]|minecraft>:shaders/post/<value>.json`.

It requires the mod on the client too.
