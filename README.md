

<div align="center">

# Advanced Armor Stands

`1.8, 1.9, 1.10, 1.11, 1.12, 1.13, 1.14, 1.15, 1.16, 1.17, 1.18, 1.19, 1.20, 1.20.6, 1.21`

![GitHub Sponsors](https://img.shields.io/github/sponsors/Parsa3323?label=Sponsor&logo=GitHub)
![GitHub contributors](https://img.shields.io/github/contributors/Parsa3323/AdvancedArmorStands?label=Contributors&logo=GitHub)
![GitHub Releases](https://img.shields.io/github/downloads/Parsa3323/AdvancedArmorStands/total?label=Downloads&logo=GitHub)
![GitHub last commit](https://img.shields.io/github/last-commit/Parsa3323/AdvancedArmorStands?label=Last%20Commit&logo=GitHub)
![GitHub issues](https://img.shields.io/github/issues/Parsa3323/AdvancedArmorStands?label=Open%20Issues&logo=GitHub)
![GitHub repo size](https://img.shields.io/github/repo-size/Parsa3323/AdvancedArmorStands?color=yellow&logo=github)
![GitHub license](https://img.shields.io/github/license/Parsa3323/AdvancedArmorStands?color=purple&logo=github)
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/Parsa3323/AdvancedArmorStands/contributors.yml?logo=github)
![Forks](https://img.shields.io/github/forks/Parsa3323/AdvancedArmorStands?style=)
</div>

[//]: # (![Spigot Downloads]&#40;https://img.shields.io/spiget/downloads/PLUGIN_ID?color=blue&logo=spigot&#41;)
[//]: # (![GitHub Activity Graph]&#40;https://github-readme-activity-graph.vercel.app/graph?username=Parsa3323&theme=github-dark&#41;)


<div align="center">

[![Repo Stats](https://github-readme-stats.vercel.app/api/pin/?username=Parsa3323&repo=AdvancedArmorStands&theme=dark)
](https://github.com/Parsa3323/AdvancedArmorStands)
</div>



<div align="center">

# Configs

This document contains the configuration details for AAS.

## Main Configuration

The `main.yml` configuration file allows you to enable or disable debugging.

</div>

```yaml
# Main configuration

debug: false # <========== Enable & Disable debug

shift-right-click-to-add: true # <========== Enable & Disable shift-right-click to add ArmorStand
```

<div align="center">

## Types Configuration

The `types.yml` file defines different entity types with their properties.

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
> Modify `itemInHandMaterial` to any valid Minecraft material.

> [!NOTE]  
> Players can create as many types as they want (e.g., up to 80 types), but they must change the names and </br> each type can be used in the game using the `create` command

> [!CAUTION]
> Don't touch cache yaml files.

<div align="center">



For more details, refer to the official documentation or community guides.


# Contributors



