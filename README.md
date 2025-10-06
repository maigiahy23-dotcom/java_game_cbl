````markdown
# Co-op Grid Shooter (Java · Swing · Gradle)

A tiny **multiplayer co-op** prototype where two players join the same grid world and **see each other move in real time**. The project focuses on fundamentals: Swing rendering, input handling, and a simple **host-authoritative TCP** networking model. Built with **Java 17+** and **Gradle**.

---

## ✨ Features (MVP)

- Top-down grid background with smooth 60 FPS updates (Swing `Timer`).
- Clean architecture: **Engine → Scene → Sprite**, input isolated in `InputManager`.
- **Two-player online**:
  - Host creates a lobby (TCP server).
  - Client joins by IP/port.
  - Positions are synced at ~**15 Hz**; remote player shown as a “ghost”.
- Ready to extend: bullets, enemies, HUD, win/lose.

---

## 🧰 Tech Stack

- **Language/UI:** Java 17+, Swing (`JFrame`, `JPanel`, `paintComponent`)
- **Build:** Gradle (KTS), runnable JAR manifest
- **Netcode:** Plain TCP sockets, **line-based messages** (`TYPE|arg1|arg2|...`)

---

## 🚀 Quick Start

> Requires **Java 17+**. Gradle Wrapper is recommended.

**macOS / Linux**
```bash
./gradlew run
````

**Windows**

```powershell
gradlew.bat run
```

Create a runnable JAR:

```bash
./gradlew jar
java -jar build/libs/*.jar
```

> If the wrapper is missing, generate once: `gradle wrapper --gradle-version 8.9`

---

## 🎮 How to Play

1. Start the app → click **Create Lobby (Host)** (default port **7777**).
2. Start the app again (same PC or another PC on LAN) → **Join Lobby**:

   * Same PC: `127.0.0.1 : 7777`
   * LAN: host’s local IP (e.g., `192.168.x.x : 7777`)
3. Move both players and watch them sync.

**Controls**

* Move: **WASD** or **Arrow keys**

---

## 🗂 Project Structure

```
src/
  main/
    java/
      com/cbl/game/
        app/        App.java                      # entry point
        config/     GameConfig.java               # FPS, default port, send rate
        core/       Engine.java, Scene.java, Sprite.java
        core/input/ InputManager.java
        core/math/  Vec2.java
        net/        MessageType.java, NetMessage.java,
                    NetClient.java, NetServer.java
        game/
          objects/  Player.java, PlayerGhost.java
          scenes/   LobbyScene.java, GameplayScene.java
    resources/      # put assets here later
.github/workflows/java-ci.yml     # simple CI
build.gradle.kts · settings.gradle.kts · .gitignore
```

---

## 🌐 Networking (Quick Spec)

* **Topology:** Host-authoritative TCP server; clients send local state; server broadcasts to everyone.
* **Wire format:** one line per message → `TYPE|arg1|arg2|...`

| Type      | Example  | Meaning     |                                 |                                    |                 |                 |                        |
| --------- | -------- | ----------- | ------------------------------- | ---------------------------------- | --------------- | --------------- | ---------------------- |
| `WELCOME` | `WELCOME | abcd1234`   | Server assigns your player id   |                                    |                 |                 |                        |
| `JOIN`    | `JOIN    | PlayerName` | Client announces itself         |                                    |                 |                 |                        |
| `JOINED`  | `JOINED  | id          | PlayerName`                     | Server tells others someone joined |                 |                 |                        |
| `POS`     | `POS     | id          | x                               | y`                                 | Position update |                 |                        |
| `LEAVE`   | `LEAVE   | id`         | Player left; remove their ghost |                                    |                 |                 |                        |
| `SHOT`    | `SHOT    | id          | x                               | y                                  | vx              | vy` *(planned)* | Bullet spawn/broadcast |

* **Send rate:** ~**15 Hz** for positions (`GameConfig.SEND_POS_HZ`).
* **Default port:** **7777/TCP** (`GameConfig.DEFAULT_PORT`).

> For LAN/Internet play, allow **TCP/7777** in your firewall; over the Internet, configure router **port forwarding** to the host PC.

---

## ⚙️ Configuration

Edit values in `com.cbl.game.config.GameConfig`:

```java
public final class GameConfig {
    public static final int  TICK_MS       = 16;   // ~60 FPS
    public static final int  DEFAULT_PORT  = 7777; // TCP
    public static final float SEND_POS_HZ  = 15f;  // position sync rate
}
```

---

## 🛣 Roadmap

* **Bullets**: spawn and sync (`SHOT|...`), lifetime/despawn.
* **Enemies**: server-spawned, chase nearest player, take damage, death broadcast.
* **HUD**: basic health/score; **win/lose** (e.g., survive 30 s).
* **Smoothing**: interpolation/prediction for remote players.
* **Packaging/CI**: release JAR, improve Actions, optional code quality checks.

---

## 🧪 Troubleshooting

* **No input:** click the game window to focus.
* **`Address already in use`:** another process/App instance is using the port → close it or change the port.
* **Cannot connect on LAN:** use host’s **LAN IP** (not public IP); allow **TCP/7777** in firewall.
* **Sprites not loading (later with assets):** load via classpath, e.g. `getResource("/Assets/Player.png")`.

---

## 🤝 Contributing

* Use feature branches (`feature/network-pos`, `feature/enemy-basic`, …).
* Keep PRs small; keep CI green.
* Prefer **private fields** + **semantic methods** (e.g., `applyDamage`) over public setters.

---

## 📜 License

Choose a license (e.g., **MIT**) and add it as `LICENSE` in the repo root.

```
::contentReference[oaicite:0]{index=0}
```
