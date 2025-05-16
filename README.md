

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

[//]: # (![FirstImg]&#40;https://biaupload.com/do.php?imgf=org-3b039f0f3c191.png&#41;)

[//]: # ()
[//]: # (![2Img]&#40;https://biaupload.com/do.php?imgf=org-02a4d92ff3c92.png&#41;)
<div align="center">

# Table of Content

</div>

- [Requirements](#requirements)
- [Website](https://aas.rabity.ir/)
- [FAQ](#f-a-q)
    - [What java version?](https://github.com/Parsa3323/AdvancedArmorStands/wiki/Java-Version)
    - [Docs](https://github.com/Parsa3323/AdvancedArmorStands/wiki)
        - [Api](https://github.com/Parsa3323/AdvancedArmorStands/wiki/Api-usage)
            - [Introduction](https://github.com/Parsa3323/AdvancedArmorStands/wiki/Api-usage#introduction)
            - [Events](https://github.com/Parsa3323/AdvancedArmorStands/wiki/Api-usage#events)
        - [Plugin usage](https://github.com/Parsa3323/AdvancedArmorStands/wiki/Plugin-usage)
            - [Commands](https://github.com/Parsa3323/AdvancedArmorStands/wiki/Plugin-usage#commands)
            - [Types](https://github.com/Parsa3323/AdvancedArmorStands/wiki/Plugin-usage#how-to-add-a-type)
- [Configs](#Configs)
    - [Main config](#Main-Configuration)
    - [Types config](#Types-Configuration)
    - [Animations config](#Animations-Configuration)


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

auto-load-armor-stands: true # <========== Automatically reload armor stands on server restart
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

## Animations Configuration

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
> Or you can use the [online editor](https://aas.rabity.ir/)


> [!CAUTION]
> Don't touch cache yaml files.

<div align="center">



For more details, refer to the [official documentation](https://github.com/Parsa3323/AdvancedArmorStands/wiki) or community guides.



</div>

<div align="center">

# Requirements
</div>

- [PlaceHolderApi](https://www.spigotmc.org/resources/placeholderapi.6245/)

<div align="center">

# Contributors

<a href="https://github.com/Parsa3323/AdvancedArmorStands/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Parsa3323/AdvancedArmorStands" />
</a>

</div>







