# Haze b100 — 反编译源码

> **Haze b100** 的 JADX 反编译重建版本，一个基于 LiquidBounce 的 Minecraft 1.8.9 Forge 作弊客户端。

## 统计

| 项目 | 数量 |
|------|:----:|
| Java 源文件 | 3,813 |
| 资源文件 | 12,161 |
| 包数量 | 607 |
| 原 JAR | 26 MB（混淆后） |

## 项目结构

```
├── build.gradle              ← Forge 1.8.9 构建配置
├── settings.gradle
├── .gitignore
├── README.md
├── README_zh_CN.md
└── src/
    ├── main/java/            ← 3,813 个反编译 Java 源码
    │   └── net/ccbluex/liquidbounce/  (主模组包)
    └── main/resources/       ← 12,161 个资源文件
        └── haze.forge.mixins.json     (mixin 配置)
```

## 构建方法

**前置要求：**
- JDK 8
- Gradle 4.10.x

```bash
gradle setupDecompWorkspace
gradle build
```

编译产出的 JAR 文件位于 `build/libs/Haze-b100.jar`。

## 说明

- 使用 [JADX](https://github.com/skylot/jadx) v1.5.0 反编译
- 部分高度混淆的类（特别是 MixinLoader coremod）无法完整反编译为 Java 源码，原始 `.class` 文件已保留在 resources 中
- Mixin refmap（`haze.mixins.refmap.json`）直接取自原构建产物
- Forge 版本：**1.8.9-11.15.1.2318**，MCP 映射：**stable_22**
- MixinLoader coremod 必须位于 `net.ccbluex.liquidbounce.injection.forge.MixinLoader` 模组才能正常加载

## 免责声明

本项目为教育和研究目的的反编译重建。所有原始代码归各自作者所有。
