# Fabricate

### Customize everything.

[Roadmap](https://trello.com/b/8ha3jHEQ/fabricate)


An alternative for CraftTweaker, in its infancy.

CraftTweaker works with its own language, ZenScript. Fabricate uses Java's built-in API "Nashorn" to use Javascript instead. The API it provides via the Javascript aims to be succinct, expressive, and less obscure. See [Scripting](#scripting-) to learn how to write scripts for Fabricate.

After the API has matured more, there will be performance tests to compare the speed of Fabricate to CraftTweaker. As of the moment Fabricate is mostly proof-of-concept. It is usable for small-scale pack development, but does not contain the full featureset of CraftTweaker. In time, it will get there.



## Table of Contents
- [Download](#download-)
- [Scripting](#scripting-)
  - [Javascript](#javascript-)
  - [Typescript](#typescript-)
  - [Documentation](#documentation-)
- [Contributing](#contributing-)



## Download [🡅](#table-of-contents)

[Github](https://github.com/Yuudaari/fabricate/releases/latest)

[CurseForge](https://minecraft.curseforge.com/projects/fabricate)



## Scripting [🡅](#table-of-contents)

Writing scripts in Fabricate uses Javascript, for multiple reasons: 

1. Javascript is a well-established language with rich support in almost all IDEs and code editors. This means syntax highlighting, error checking, and sometimes even type checking.
2. Java provides a native API for using Javascript. This low level of interpretation will likely make it very performant compared to a custom language parser.
3. By default, Javascript is a "loosely typed" language, but there is a very feature-rich language called Typescript that can compile down into bare-bones Javascript. Using Typescript is highly recommended when doing
large-scale pack development, as it provides much more information about variables, modules, and functions, and can also provide type checking for them.

Any file in the `/scripts/` mod directory (the same directory used by CraftTweaker) that ends in `.js` will be imported via Fabricate. (Typescript files have to first be compiled)


### Javascript [🡅](#table-of-contents)

Here's what importing looks like in Javascript:

```js
var Fabricate = require("fabricate").Fabricate;
var RegistryEvent = require("fabricate").RegistryEvent;
```


### Typescript [🡅](#table-of-contents)

Here's what importing looks like in Typescript:

```ts
import { Fabricate, RegistryEvent } from "fabricate";
```

To use Typescript, it's recommended to use an editor that supports it out of the box. I use an editor called VSCode, which has, in my opinion, unparalleled support for Typescript development. The following is a tutorial on how to set up a Typescript workspace in your `/scripts/` folder.

1. Run the game once with Fabricate and any mod that provides Fabricate support. This generates the `/scripts/` folder and extracts the definitions from all supported mods into `/scripts/definitions/`.
2. Open the `/scripts/` folder in VSCode.
3. Create a `tsconfig.json` file. Here is an example file:

```json
{
	"compilerOptions": {
		"strict": true,
		"outDir": "./out"
	},
	"include": [
		"**/*.ts"
	],
	"exclude": []
}
```

4. Compile the Typescript with a build task. You can use `ctrl+shift+b` to start a build task. It should auto-detect that there's a `tsconfig.json` and ask if you want to run `tsc watch` or `tsc build`. Both tasks will compile then, while the watch task will also run whenever a `ts` file is changed in the directory.
5. Create your `ts` script files. Their compiled `js` files will be in `/out/`, if you're using the `tsconfig.json` provided here. As Fabricate will use any `js` file in the directory, you may change the `outDir` to wherever you like.


### Documentation [🡅](#table-of-contents)

There is currently no documentation for Fabricate as it's mostly self-documenting. In the future there will be official documentation, but Fabricate is, again, in its infancy.

See [Fabricate.d.ts](./src/main/resources/assets/fabricate/definitions/fabricate.d.ts) to see the definitions of the current API.



## Contributing [🡅](#table-of-contents)

If you have an error, bug, or have found an oversight please [leave an issue about it](https://github.com/Yuudaari/fabricate/issues). I'll try to get to them as fast as I can.

If you want to help develop Fabricate and already know how to mod, great, make an issue and then a PR if you know what you want to do. If you don't know how to mod, I probably won't have the time to help teach you, but you're welcome to join my [Discord server](https://discord.gg/fwvBfus) and chat/ask questions.

If you have a suggestion you can also [leave them as an issue](https://github.com/Yuudaari/fabricate/issues). I will close suggestions that I dislike or are out of scope for the mod.

If you want to support me financially, consider supporting me on [Patreon](https://www.patreon.com/yuudaari)!

[The Soulus License Copyright (c) 2018 Mackenzie "Yuudaari" McClane](./LICENSE.md)
