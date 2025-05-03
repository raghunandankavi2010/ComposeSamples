package com.example.composesamples

// Screen.kt
sealed class Screen(val route: String) {
    object Main: Screen("main_screen")
    object EffectOrderScreen: Screen("effect_order_screen")
    object DraggablePanelScreen: Screen("draggable_panel_screen")
    object SnapShotMutationPolicyScreen: Screen("snap_shot_mutation_policy")
    object AvoidLaunchEffectScreen: Screen("avoid_launch_effect_initial")
    object ListScreen: Screen("list_screen")
    object DetailScreen: Screen("detail_screen")
    object LaunchEffectDemoScreen: Screen("launch_effect_screen")
    object TextResizeDemo: Screen("text_resize_screen")
}