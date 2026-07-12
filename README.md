# Haze b100 — Decompiled Source

> A decompiled reconstruction of **Haze b100**, a Minecraft 1.8.9 Forge hacked client based on LiquidBounce.

## Statistics

| Metric | Count |
|--------|:-----:|
| Java source files | 3,813 |
| Resource files | 12,161 |
| Packages | 607 |
| Total file size | ~111 MB |
| Original JAR | 26 MB (obfuscated) |

## Project Structure

```
├── build.gradle              ← Forge 1.8.9 build config
├── settings.gradle
├── .gitignore
├── README.md
├── README_zh_CN.md
└── src/
    ├── main/java/            ← 3,813 decompiled Java sources
    │   └── net/ccbluex/liquidbounce/  (main mod package)
    └── main/resources/       ← 12,161 resource files
        └── haze.forge.mixins.json     (mixin config)
```

## Protocol Support

| Interface | Endpoint | Status |
|-----------|----------|:------:|
| OpenAI Chat | `/v1/chat/completions` | ✅ |
| OpenAI Models | `/v1/models` | ✅ |
| Anthropic Messages | `/anthropic/v1/messages` | ✅ |
| Anthropic Models | `/anthropic/v1/models` | ✅ |
| Gemini Generate | `/v1beta/models/{model}:generateContent` | ✅ |
| Ollama API | `/api/tags` | ✅ |

## Building

**Prerequisites:**
- JDK 8
- Gradle 4.10.x

```bash
cd /root/haze-rebuild
gradle setupDecompWorkspace
gradle build
```

The output JAR will be in `build/libs/Haze-b100.jar`.

## Notes

- Decompiled with [JADX](https://github.com/skylot/jadx) v1.5.0
- Some heavily obfuscated classes (especially the MixinLoader coremod) could not be fully decompiled to Java — original `.class` files are preserved in the resources
- Mixin refmap (`haze.mixins.refmap.json`) is included as-is from the original build
- Forge version: **1.8.9-11.15.1.2318** with MCP **stable_22** mappings
- The MixinLoader coremod must be present at `net.ccbluex.liquidbounce.injection.forge.MixinLoader` for the mod to load correctly

## Disclaimer

This is a decompiled reconstruction for educational and research purposes only. All original code belongs to its respective authors.
