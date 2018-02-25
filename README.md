# Fabricate

### Customize everything.

[Roadmap](https://trello.com/b/8ha3jHEQ/fabricate)


An alternative for CraftTweaker. Very WIP.

CraftTweaker works with its own language, ZenScript. Fabricate uses Java's built-in API "Nashorn" to use Javascript instead. The API it provides via the Javascript aims to be succinct, expressive, and less obscure.

Any file in the `/scripts/` mod directory (the same directory used by CraftTweaker) that ends in `.js` will be imported via Fabricate. 

Here's an example script:

```js
var Fabricate = require("fabricate").Fabricate;
var RegistryEvent = require("fabricate").RegistryEvent;

Fabricate.on(RegistryEvent.Recipes, function (recipes) {
	
	recipes.addShapeless("minecraft:grass", ["minecraft:dirt@0"]);

	recipes.addShaped("minecraft:diamond", [
		["minecraft:stick", "minecraft:stick", "minecraft:stick"],
		["$ingotIron", "minecraft:dirt", "minecraft:stick"],
		["minecraft:stick", "minecraft:stick", "minecraft:stick"]
	]);

	recipes.withOutput(Fabricate.stack("minecraft:diamond", 9))
		.setImmediate()
		.replaceAll(recipe => recipes.createEmpty());

	recipes.addShapeless(Fabricate.stack("minecraft:diamond", 9), ["minecraft:coal_block"]);

	recipes.withOutput(Fabricate.stack("minecraft:iron_ingot", 9))
		.replaceAll(recipe => recipes.createEmpty());

	recipes.addShapeless(Fabricate.stack("minecraft:iron_ingot", 9), ["minecraft:stone"]);
});
```

In the future Fabricate will generate Typescript definitions in the scripts folder so scripts can be developed with typechecking.


## Table of Contents
- [Download](#download-)
- [Contributing](#contributing-)



## Download [🡅](#table-of-contents)

[Github](https://github.com/Yuudaari/fabricate/releases/latest)

[CurseForge](https://minecraft.curseforge.com/projects/fabricate)




## Contributing [🡅](#table-of-contents)

If you have an error, bug, or have found an oversight please [leave an issue about it](https://github.com/Yuudaari/fabricate/issues). I'll try to get to them as fast as I can.

If you want to help develop Soulus and already know how to mod, great, make an issue and then a PR if you know what you want to do. If you don't know how to mod, I probably won't have the time to help teach you, but you're welcome to join my [Discord server](https://discord.gg/fwvBfus) and chat/ask questions.

If you have a suggestion you can also [leave them as an issue](https://github.com/Yuudaari/fabricate/issues). I will close suggestions that I dislike or are out of scope for the mod.

If you want to support me financially, consider supporting me on [Patreon](https://www.patreon.com/yuudaari)!

[The Soulus License Copyright (c) 2018 Mackenzie "Yuudaari" McClane](./LICENSE.md)
