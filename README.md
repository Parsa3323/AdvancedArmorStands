

<div align="center">

# Advanced Armor Stands <img src="https://github.com/user-attachments/assets/084a8def-16ee-4516-84b6-3471bf97448d" width="24" style="vertical-align:middle;" />

**Super lightweight, smart, ultra-efficient plugin that barely uses any server resources!**


`1.8, 1.9, 1.10, 1.11, 1.12, 1.13, 1.14, 1.15, 1.16, 1.17, 1.18, 1.19, 1.20, 1.20.6, 1.21`

[![GitHub Sponsors](https://img.shields.io/github/sponsors/Parsa3323?label=Sponsor&logo=GitHub)]()
[![GitHub contributors](https://img.shields.io/github/contributors/Parsa3323/AdvancedArmorStands?label=Contributors&logo=GitHub)
](https://github.com/Parsa3323/AdvancedArmorStands/graphs/contributors)
[![Downloads](https://img.shields.io/spiget/downloads/121022?label=Downloads&color=blue&logo=spigot)
](https://www.spigotmc.org/resources/advancedarmorstands.121022/)
[![GitHub last commit](https://img.shields.io/github/last-commit/Parsa3323/AdvancedArmorStands?label=Last%20Commit&logo=GitHub)
]()
[![GitHub issues](https://img.shields.io/github/issues/Parsa3323/AdvancedArmorStands?label=Open%20Issues&logo=GitHub)
](https://github.com/Parsa3323/AdvancedArmorStands/issues)![GitHub repo size](https://img.shields.io/github/repo-size/Parsa3323/AdvancedArmorStands?color=yellow&logo=github)
[![GitHub license](https://img.shields.io/github/license/Parsa3323/AdvancedArmorStands?color=purple&logo=github)
]()
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/Parsa3323/AdvancedArmorStands/test.yml?logo=github)
[![Forks](https://img.shields.io/github/forks/Parsa3323/AdvancedArmorStands?style=)
](https://github.com/Parsa3323/AdvancedArmorStands/issues)[![CodeFactor](https://www.codefactor.io/repository/github/parsa3323/advancedarmorstands/badge)](https://www.codefactor.io/repository/github/parsa3323/advancedarmorstands)

</div>
<div align="center">

[![Repo Stats](https://github-readme-stats.vercel.app/api/pin/?username=Parsa3323&repo=AdvancedArmorStands&bg_color=00000000&text_color=797c80&border_color=797c80)
](https://github.com/Parsa3323/AdvancedArmorStands)

</div>

<div align="center">

# Table of Content

</div>

> [!NOTE]
> By using this plugin you agree to the [Terms of Service](https://github.com/Parsa3323/AdvancedArmorStands/blob/master/TERMS_OF_SERVICE.md).

- [Requirements](#requirements)
- [Polymart](https://www.polymart.org/product/7829/advancedarmorstands)
- [Spigot](https://www.spigotmc.org/resources/advancedarmorstands.121022/)
- [Status](http://status.advancedarmorstands.ir/)
- [Website](https://advancedarmorstands.ir/)
- [FAQ](#f-a-q)
    - [What java version?](http://docs.advancedarmorstands.ir/installation#prerequisites)
    - [Docs](https://docs.advancedarmorstands.ir/)
        - [Api](https://docs.advancedarmorstands.ir/api)
            - [Introduction](https://docs.advancedarmorstands.ir/api-introduction#getting-started-with-the-api)
            - [Events](https://docs.advancedarmorstands.ir/api-events#events)
        - [Plugin usage](https://docs.advancedarmorstands.ir/plugin-usage)
            - [Commands](https://docs.advancedarmorstands.ir/plugin-usage/)
            - [Types](https://docs.advancedarmorstands.ir/plugin-usage)
- [Configs](#Configs)
    - [Main config](#Main-Configuration)
    - [Types config](#Types-Configuration)
    - [Animations config](#Animations-Configuration)
    - [Actions config](#actions-configuration)


<div align="center">

# Configs

This document contains the configuration details for AdvancedArmorStands.

## Main Configuration

The `main.yml` file is the plugin's main configuration file:

</div>

```yaml
# Main configuration

config-version: 1.0.0 # <========== Config version (don't touch)

debug: false # <========== Enable & Disable debug

shift-right-click-to-add: true # <========== Enable & Disable shift-right-click to add ArmorStand

auto-load-armor-stands: false # <========== Automatically reload armor stands on server restart
```

<div align="center">

## Types Configuration

The `types.yml` file is the configuration for types:

</div>

```yaml
default: # <====== Name of the type
  Arms: true # <====== Has arms?
  Gravity: false # <====== Has gravity?
  BasePlate: false # <====== Does it have a baseplate?
  CustomName: '&cMade with aas' # <====== Custom name for the entity
  isCustomNameVisible: false # <====== Should the custom name be visible?
  itemInHandMaterial: WOOD_SWORD # <====== Item held in the hand
  HeadPos: {} # <====== Head position (empty by default)
  rightArmPose: # <====== Right arm pose
    x: -45
    y: 0
    z: 0
  leftArmPose: # <====== Left arm pose
    x: 45
    y: 0
    z: 0
  rightLegPose: # <====== Right leg pose
    x: 45
    y: 0
    z: 0
  leftLegPose: # <====== Left leg pose
    x: -45
    y: 0
    z: 0
```
> [!IMPORTANT]  
> Modify `itemInHandMaterial` to any valid Minecraft material (on your minecraft version).

> [!NOTE]  
> Players can create as many types as they want, but they must change the names and </br> each type can be used in the game using the `create` sub-command

<div align="center">

## Animations Configuration

The `animations.yml` file is the configuration for animations:

</div>

```yaml
animations:
  wave: # <====== Animation name or type
    interval: 10 # <====== Interval between each animation frame (in ticks)
    loop: true # <====== Should the animation loop? (true or false)
    steps: # <====== List of animation steps
      - head: # <====== Head pose for this step
          x: 0   # <====== Head X rotation
          y: 0   # <====== Head Y rotation
          z: 0   # <====== Head Z rotation
        left_arm: # <====== Left arm pose for this step
          x: -30 # <====== Left arm X rotation
          y: 0   # <====== Left arm Y rotation
          z: -10 # <====== Left arm Z rotation
        right_arm: # <====== Right arm pose for this step
          x: -30 # <====== Right arm X rotation
          y: 0   # <====== Right arm Y rotation
          z: 10  # <====== Right arm Z rotation
        left_leg: # <====== Left leg pose for this step
          x: 10  # <====== Left leg X rotation
          y: 0   # <====== Left leg Y rotation
          z: 0   # <====== Left leg Z rotation
        right_leg: # <====== Right leg pose for this step
          x: -10 # <====== Right leg X rotation
          y: 0   # <====== Right leg Y rotation
          z: 0   # <====== Right leg Z rotation
      - head:
          x: 0   # <====== Head X rotation
          y: 0   # <====== Head Y rotation
          z: 0   # <====== Head Z rotation
        left_arm:
          x: -10 # <====== Left arm X rotation
          y: 0   # <====== Left arm Y rotation
          z: 30  # <====== Left arm Z rotation
        right_arm:
          x: -10 # <====== Right arm X rotation
          y: 0   # <====== Right arm Y rotation
          z: -30 # <====== Right arm Z rotation
        left_leg:
          x: -10 # <====== Left leg X rotation
          y: 0   # <====== Left leg Y rotation
          z: 0   # <====== Left leg Z rotation
        right_leg:
          x: 10  # <====== Right leg X rotation
          y: 0   # <====== Right leg Y rotation
          z: 0   # <====== Right leg Z rotation
```
>[!TIP]
> Or you can use the [in-game animation creator](https://docs.advancedarmorstands.ir/animations) or the [online editor](https://advancedarmorstands.ir/animate)

<div align="center">

## Actions Configuration

The `actions.yml` file is the configuration for actions:

</div>

```yaml
armorstand:
  SavedStand101: # <====== Name of the armor stand
    say-its-working: # <====== Command name (use '-' instead of spaces)
      type: player # <====== Command executor ('player' or 'server')
      trigger: all # <====== Interaction that triggers the action
```
> [!TIP]  
> Use the Armor Stand menu to create or delete actions easily.

> [!CAUTION]
> Don't touch cache yaml files.

<div align="center">


For more details, refer to the [official documentation](https://docs.advancedarmorstands.ir/) or community guides.


</div>

<div align="center">

# Requirements
</div>

- [PlaceHolderApi](https://www.spigotmc.org/resources/placeholderapi.6245/) (Optional)

<div align="center">

# Contributors

<a href="https://github.com/Parsa3323/AdvancedArmorStands/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Parsa3323/AdvancedArmorStands" />
</a>

</div>







