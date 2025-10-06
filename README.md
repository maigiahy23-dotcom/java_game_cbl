Got it — here’s a single, copy-ready **README.md** in plain Markdown. Just copy everything inside the block to your GitHub README:

````markdown
# Co-op Grid Shooter (Java · Swing · Gradle)

A tiny **multiplayer co-op** prototype: two players join the same grid world and **see each other move in real time**. The project focuses on the fundamentals: Swing rendering, input handling, and simple TCP networking.

## Highlights
- **Java 17 + Swing** with a clean `Engine → Scene → Sprite` architecture.
- **Host-authoritative TCP** networking (line-based messages).
- **Gradle** project (wrapper ready), clear packages, minimal protocol.

---

## Requirements
- Java **17+**
- Gradle **Wrapper** (`./gradlew` / `gradlew.bat`) included

---

## Quick Start

**macOS / Linux**
```bash
./gradlew run
````

**Windows**

```powershell
gradlew.bat run
```

> Tip: launch a second instance to test two players on the same PC.

---

## How to Play (MVP)

1. In the first window click **Create Lobby (Host)** (default port **7777**).
2. In the second window click **Join Lobby**:

   * Same PC: `127.0.0.1 : 7777`
   * LAN: host’s local IP (e.g., `192.168.x.x : 7777`)
3. You’ll see your **Player** (filled) and the other’s **PlayerGhost** (outline) moving.

**Controls:** WASD / Arrow keys to move.

---

## Project Structure

```
src/
  main/
    java/
      com/cbl/game/
        app/        App.java                      # entry point
        config/     GameConfig.java               # FPS, port, sync rate
        core/       Engine.java, Scene.java, Sprite.java
        core/input/ InputManager.java
        core/math/  Vec2.java
        net/        MessageType.java, NetMessage.java,
                    NetClient.java, NetServer.java
        game/
          objects/  Player.java, PlayerGhost.java
          scenes/   LobbyScene.java, GameplayScene.java
    resources/      # put assets here later
.github/workflows/java-ci.yml
build.gradle.kts · settings.gradle.kts · .gitignore
```

---

## Networking (Quick Spec)

* **Topology:** host-authoritative TCP server; clients send state; server broadcasts.
* **Wire format:** one line per message → `TYPE|arg1|arg2|...`

  * `WELCOME|id`
  * `JOIN|name` → server may broadcast `JOINED|id|name`
  * `POS|id|x|y`
  * `LEAVE|id`
* **Position send rate:** ~**15 Hz** (`GameConfig.SEND_POS_HZ`)
* **Default port:** **7777/TCP** (`GameConfig.DEFAULT_PORT`)

> For LAN/Internet play, allow TCP/7777 in the firewall; for Internet, configure router **port forwarding** to the host PC.

---

## Build / Package

Create a runnable JAR:

```bash
./gradlew jar
java -jar build/libs/*.jar
```

---

## Roadmap (Next Steps)

* Sync **bullets** (`SHOT|...`) so both clients see them.
* Server-driven **enemy spawns**, damage, and death events.
* Basic **HUD** and **win/lose** conditions (e.g., survive 30s).

---

## Troubleshooting

* **No input:** click the game window to focus.
* **`Address already in use`:** another instance is using the port → close the old one or change port.
* **Cannot connect on LAN:** use host’s **LAN IP** (not public IP) and allow TCP/7777 in the firewall.

---

## Contributing

Use feature branches (`feature/network-pos`, `feature/enemy-basic`, …), keep PRs small, keep CI green. Prefer private fields + semantic methods over public setters.

---

## License

Add your preferred license (e.g., **MIT**) in a `LICENSE` file.

```
::contentReference[oaicite:0]{index=0}
```
