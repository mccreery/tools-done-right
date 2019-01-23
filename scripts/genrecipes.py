#!/usr/bin/env python

import json
types = ["ruby", "topaz", "sapphire"]

recipes = {
    "sword": {
        "type": "minecraft:crafting_shaped",
        "pattern": ["x", "x", "|"],
        "key": {
            "x": { "item": "toolsdoneright:%s" },
            "|": { "item": "minecraft:stick" }
        },
        "result": { "item": "toolsdoneright:%s_sword" }
    },
    "pickaxe": {
        "type": "minecraft:crafting_shaped",
        "pattern": ["xxx", " | ", " | "],
        "key": {
            "x": { "item": "toolsdoneright:%s" },
            "|": { "item": "minecraft:stick" }
        },
        "result": { "item": "toolsdoneright:%s_pickaxe" }
    },
    "axe": {
        "type": "minecraft:crafting_shaped",
        "pattern": ["xx", "x|", " |"],
        "key": {
            "x": { "item": "toolsdoneright:%s" },
            "|": { "item": "minecraft:stick" }
        },
        "result": { "item": "toolsdoneright:%s_axe" }
    },
    "shovel": {
        "type": "minecraft:crafting_shaped",
        "pattern": ["x", "|", "|"],
        "key": {
            "x": { "item": "toolsdoneright:%s" },
            "|": { "item": "minecraft:stick" }
        },
        "result": { "item": "toolsdoneright:%s_shovel" }
    },
    "hoe": {
        "type": "minecraft:crafting_shaped",
        "pattern": ["xx", " |", " |"],
        "key": {
            "x": { "item": "toolsdoneright:%s" },
            "|": { "item": "minecraft:stick" }
        },
        "result": { "item": "toolsdoneright:%s_hoe" }
    },
    "block": {
        "type": "minecraft:crafting_shaped",
        "pattern": ["xxx"] * 3,
        "key": { "x": { "item": "toolsdoneright:%s" } },
        "result": { "item": "toolsdoneright:%s_block" }
    }
}

for t in types:
    for r in recipes:
        with open("src/main/resources/assets/toolsdoneright/recipes/%s_%s.json" % (t, r), "w") as f:
            f.write(json.dumps(recipes[r]) % (t, t))
