<div align="center">

[<img src="https://github.com/Parsa3323/AdvancedArmorStands/blob/master/.github/images/icon.png?raw=true" width="2004" style="vertical-align:middle;" >](#)

`1.8, 1.9, 1.10, 1.11, 1.12, 1.13, 1.14, 1.15, 1.16, 1.17, 1.18, 1.19, 1.20, 1.20.6, 1.21` <br> `26.1, 26.2`

---

[//]: # (**Super lightweight, smart, ultra-efficient plugin that barely uses any server resources!**)

[![Downloads](https://img.shields.io/spiget/downloads/121022?label=Downloads&color=blue&logo=spigot)](https://www.spigotmc.org/resources/advancedarmorstands.121022/)
![GitHub repo size](https://img.shields.io/github/repo-size/Parsa3323/AdvancedArmorStands?color=yellow&logo=github)
[![GitHub license](https://img.shields.io/github/license/Parsa3323/AdvancedArmorStands?color=purple&logo=github)]()
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/Parsa3323/AdvancedArmorStands/compile.yml?logo=github)

</div>

<div align="center">

[<img src="https://github.com/Parsa3323/AdvancedArmorStands/blob/master/.github/images/badge1.png?raw=true" width="204" style="vertical-align:middle;">](https://www.codefactor.io/repository/github/parsa3323/advancedarmorstands/badge)
[<img src="https://github.com/Parsa3323/AdvancedArmorStands/blob/master/.github/images/badge2.png?raw=true" width="204" style="vertical-align:middle;">](#table-of-contents)
[<img src="https://github.com/Parsa3323/AdvancedArmorStands/blob/master/.github/images/badge3.png?raw=true" width="204" style="vertical-align:middle;">](#supported-languages)

</div>

<div align="center">



**[Polymart](https://www.polymart.org/product/7829/advancedarmorstands)** •
**[Spigot](https://www.spigotmc.org/resources/advancedarmorstands.121022/)** •
**[Documentation](https://docs.advancedarmorstands.ir/)** •
**[Website](https://advancedarmorstands.ir/)** •
**[Status](http://status.advancedarmorstands.ir/)**

</div>

> [!NOTE]
> By using this plugin you agree to the [Terms of Service](https://github.com/Parsa3323/AdvancedArmorStands/blob/master/TERMS_OF_SERVICE.md).

---

## About

**AdvancedArmorStands** lets you create, customize, animate and manage armor stands on your server, with in-game menus, reusable types, animations, click actions and a developer API. It is built with [XSeries](https://github.com/CryptoMorin/XSeries)[^1] for cross-version compatibility, so one jar works from 1.8 all the way up to the latest versions.

---

<div align="center">

# Table of Contents

</div>

- [Links](#links)
- [Supported Languages](#supported-languages)
- [Requirements](#requirements)
    - [Server Requirements](#server-requirements)
    - [Optional Dependencies](#optional-dependencies)
- [FAQ](https://docs.advancedarmorstands.ir/faq)
    - [General Questions](https://docs.advancedarmorstands.ir/faq#general-questions)
- [Documentation](https://docs.advancedarmorstands.ir/)
    - [API](https://docs.advancedarmorstands.ir/api)
        - [Events](https://docs.advancedarmorstands.ir/api-events)
        - [Creating an ArmorStand](https://docs.advancedarmorstands.ir/api-creating-an-armorstand)
        - [Opening Inventories](https://docs.advancedarmorstands.ir/api-open-inventories)
        - [Adding Animations](https://docs.advancedarmorstands.ir/api-adding-animation)
    - [Getting Started](https://docs.advancedarmorstands.ir/gettingstarteds)
        - [Installation Guide](https://docs.advancedarmorstands.ir/installation)
        - [Creating an ArmorStand](https://docs.advancedarmorstands.ir/creating-as)
        - [Moving an ArmorStand](https://docs.advancedarmorstands.ir/moving-as)
        - [Renaming an ArmorStand](https://docs.advancedarmorstands.ir/renaming-as)
    - [More Documentation](https://docs.advancedarmorstands.ir/)
- [Configuration](#configs)
    - [Main Config](#main-configuration)
    - [Types Config](#types-configuration)
    - [Animations Config](#animations-configuration)
    - [Actions Config](#actions-configuration)
- [Contributors](#contributors)
- [Donate](#donate)

---

## Links

| Platform | Link |
|----------|------|
| Polymart | [advancedarmorstands](https://www.polymart.org/product/7829/advancedarmorstands) |
| Spigot | [advancedarmorstands.121022](https://www.spigotmc.org/resources/advancedarmorstands.121022/) |
| Website | [advancedarmorstands.ir](https://advancedarmorstands.ir/) |
| Documentation | [docs.advancedarmorstands.ir](https://docs.advancedarmorstands.ir/) |
| Status | [status.advancedarmorstands.ir](http://status.advancedarmorstands.ir/) |

---

## Supported Languages

English, Italian, Persian, Portuguese, Russian, Spanish, Turkish, Bangla, Indonesian, Polish, Romanian, and more. **Add your own!**

---

## Requirements

### Server Requirements

- A Bukkit-based server such as **Spigot**, **Paper**, **Purpur**, or another compatible Bukkit/Paper fork.

### Optional Dependencies

- [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/)

---

<div align="center">

# Configs

This document contains the configuration details for AdvancedArmorStands.

## Main Configuration

The `config.yml` file is the plugin's main configuration file:

</div>

```yaml
# Main configuration

config-version: 1.0.2 # <========== Config version (don't touch)

language: en # <========== Language

hide-cache-folder: true # <========== Hide the cache folder (windows only)

debug: false # <========== Enable & Disable debug

shift-right-click-to-add: true # <========== Enable & Disable shift-right-click to add ArmorStand

shift-click-to-delete: false # <========== Enable & Disable shift-right-click to delete ArmorStand

auto-load-armor-stands: false # <========== Automatically reload armor stands on server restart

ai:
  token: 'PLACE_YOUR_TOKEN_HERE' # <========== Token for AI integration (place your actual token here)

  allow-players: true # <========== Allow players to interact with the AI system
```

<div align="center">

## Types Configuration

The `types.yml` file is the configuration for types:

</div>

```yaml
default: # <====== Name of the type
  arms: true # <====== Has arms?
  basePlate: false # <====== Does it have a baseplate?
  customName: '&cMade with aas' # <====== Custom name for the entity
  isCustomNameVisible: false # <====== Should the custom name be visible?
  isVisible: true # <====== Is visible?
  isSmall: false # <====== Is small?
  itemInHandMaterial: WOOD_SWORD # <====== Item held in the hand
  headPos: {} # <====== Head position (empty by default)
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
> Modify `itemInHandMaterial` to any valid Minecraft material (on your Minecraft version).

> [!NOTE]
> Players can create as many types as they want, but each type must have a unique name. <br> Each type can be used in-game with the `create` sub-command.

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
        left_leg: #<====== Left leg X rotation
          y: 0   #  <====== Left leg pose for this step
          x: 10  # <====== Left leg Y rotation
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

> [!TIP]
> Prefer a visual approach? Use the [in-game animation creator](https://docs.advancedarmorstands.ir/animations) or the [online editor](https://advancedarmorstands.ir/animate).

<div align="center">

## Actions Configuration

The `actions.yml` file is the configuration for actions:

</div>

```yaml
armorstand:
  SavedStand101: # <====== Name of the armor stand
    say-hello-world: # <====== Command name (use '-' instead of spaces)
      type: player # <====== Command executor ('player' or 'server')
      trigger: all # <====== Interaction that triggers the action
```

> [!TIP]
> Use the Armor Stand menu to create or delete actions easily.

> [!CAUTION]
> Don't touch cache `.aas` files.

<div align="center">

For more details, refer to the [official documentation](https://docs.advancedarmorstands.ir/) or community guides.

</div>

---

<div align="center">

# Contributors

<a href="https://github.com/Parsa3323/AdvancedArmorStands/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Parsa3323/AdvancedArmorStands" />
</a>

# Donate

</div>

AdvancedArmorStands is a completely independent project that I have built and maintained entirely on my own. I've put a lot of time and effort into the plugin, even though it currently has a relatively small user base.

I'm always doing my best to improve AdvancedArmorStands, fix issues, add new features, and make the experience as smooth and enjoyable as possible for everyone who uses it.

If you find the project useful and would like to support its development, you can make a donation below. Every bit of support is greatly appreciated and helps me keep working on the project.

<div align="center">
<a href="https://plisio.net/donate/nG4Or43y" target="_blank"><img src="https://plisio.net/img/donate/donate_dark_icons_no.png" alt="Donate Crypto on Plisio" width="240" height="48" /></a>
<a href="https://nowpayments.io/donation?api_key=acb39f10-bbaa-42fc-8265-ce4016e2af7a" target="_blank" rel="noreferrer noopener"><img src="https://nowpayments.io/images/embeds/donation-button-black.svg" alt="Donate Crypto on NOWPayments" width="240" height="48" /></a>
</div>

[^1]: XSeries is a Minecraft library that makes it possible to support different Minecraft versions with the same code
