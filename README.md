```markdown
# Co-op Grid Shooter (Java + Swing + Gradle)

A tiny **multiplayer co-op** game where 2 players join the same grid world and **see each other** in real time. Built with **Java + Swing** to focus on fundamentals (rendering, input, simple networking). Gradle is used for build/run/JAR/CI.

---

## Key Goals

- **Networking (current focus):** Two players join one session and see each other’s position & actions.
- **Build tools (learning):** Understand Gradle basics and how to run/package consistently.
- **Extendable enemies:** Start with a base `Enemy` class; add variants later (stronger, ranged…).

---

## Features (MVP)

- Top-down 2D grid world (simple camera).
- Local rendering with Swing (`JPanel` + `paintComponent`).
- Two players in one lobby:
  - Both players are rendered.
  - Both players’ **input** & **movement** are synchronized (≈15 Hz).
- Basic enemies *(planned for next iterations)*:
  - Spawn around the map (same for all clients).
  - Move toward players.
  - Take damage and die.
- Bullets *(planned)*: players can shoot and both clients see bullets.

> After MVP, add polish (HUD, sounds, different enemy types).

---

## Controls

- **Move:** Arrow keys or WASD  
- **Shoot:** Space *(planned)*  
- **Pause/Back:** Esc *(planned)*

---

## Project Structure (actual)

```

src/
  main/
    java/
      com/cbl/game/
        app/        App.java                      # entry point
        config/     GameConfig.java               # constants (FPS, port, sync rate)
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
build.gradle.kts, settings.gradle.kts, .gitignore


````

---

## How It Works (High Level)

### Rendering & Scenes
- `Engine` owns the window (`JFrame`) and the **current `Scene`**.
- Each `Scene` is a `JPanel`:
  - Holds drawable sprites (player, ghosts, etc.).
  - `update()` → game logic; `repaint()` → `paintComponent()` draws.

### Input
- `InputManager` tracks pressed keys.
- `Player` updates position based on key states.

### Networking (simple & robust for MVP)
- **Topology:** Host-authoritative TCP server.
- **Messages:** One text line each (e.g., `JOIN|name`, `POS|id|x|y`, `LEAVE|id`).
- **Server:**
  - Receives updates from clients.
  - Broadcasts state to all clients.
  - Keeps a `Map<playerId, position>`.
- **Client:**
  - Sends local position at a fixed rate (~15 Hz).
  - Background reader thread fills a thread-safe inbox; `Scene.update()` consumes to move `PlayerGhost`s.

> Later upgrades: UDP for smoother movement, interpolation/prediction, sequence numbers.

---

## Build & Run (Gradle Wrapper)

**macOS/Linux**
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

> Requires **Java 17+**.

---

## Configuration

* **Default port:** `7777` (TCP).
* **Local test:** Host on your machine, then start a second instance and join `127.0.0.1:7777`.
* **LAN:** Client connects to host machine’s LAN IP (e.g., `192.168.1.50:7777`).
* **Internet:** Requires **port forwarding** on the host’s router (forward TCP/7777 to the host PC).

---

## Roadmap / Todo (with acceptance criteria)

### Setup

* [ ] **Setup window**
  *AC:* Opens a `JFrame` with fixed size; closes cleanly.
* [ ] **Add join/create lobby button**
  *AC:* Lobby scene shows two buttons; clickable; keyboard focus retained for input.
* [ ] **Add lobby screen and display all players**
  *AC:* Shows a list of joined players’ IDs/nicknames; updates on join/leave.
* [ ] **Make create lobby work**
  *AC:* Clicking “Create” starts `Server` at configured port, shows lobby info (IP/port).
* [ ] **Make join lobby work**
  *AC:* Client can enter IP/port; connects; receives `WELCOME`/`YOU` messages.
* [ ] **Make the start game work**
  *AC:* Host can start; both navigate to `GameplayScene` with same seed/state.

### Game basics

* [ ] **Basic object rendering**
  *AC:* Player sprite draws at `(x,y)`; no exceptions thrown.
* [ ] **Camera**
  *AC:* World scrolls or clamps; player remains visible; no jitter.
* [ ] **Both players are rendered on screen**
  *AC:* Local `Player` + remote `PlayerGhost` visible with distinct styles/labels.
* [ ] **Both players input work and they can see each other move around**
  *AC:* Movement sync at 10–20 Hz; ≤200 ms visible delay; no desync on reconnect.
* [ ] **Players can shoot bullets and both players see the bullets** *(planned)*
  *AC:* Press Space spawns bullet on both clients; bullets despawn off-screen or on hit.

### Enemies (`Enemy` is abstract; add variants later)

* [ ] **Enemies randomly spawn around for all clients**
  *AC:* Server sends spawn msgs; all clients see the same enemies with the same IDs.
* [ ] **Enemies can take damage**
  *AC:* Bullet–enemy collision reduces HP; server authoritatively resolves.
* [ ] **Enemies can die**
  *AC:* On 0 HP, server announces death; all clients remove the enemy.
* [ ] **Enemies move towards players**
  *AC:* Basic chase behavior; tick-based position updates; smooth on clients.
* [ ] **Enemies can attack player**
  *AC:* On contact or within range, player loses HP; shared defeat/victory rules.

---

## Build Tools — What We’ll Learn

* **Gradle:** dependency management, tasks, run/debug, packaging, CI.
* *(Optional)* **Maven:** similar goals; XML-driven; widely used in enterprise.
* **Why it matters:** reproducible builds, easier CI, scalable structure as the project grows.

---

## Contributing (Team Workflow)

1. Create a feature branch (e.g., `feature/network-pos`, `feature/enemy-basic`).
2. Keep PRs small and focused (≤ 300 lines if possible).
3. Add simple test scene/manual steps in the PR description.
4. Code style: small classes, clear names; prefer private fields + semantic methods.
5. Review: one teammate approves before merge; CI green.

---

## Troubleshooting

* **Nothing moves:** Ensure the game panel has focus (`requestFocusInWindow()`).
* **High CPU or UI freeze:** Use `javax.swing.Timer` (~16 ms) or a background thread; never block the EDT.
* **`Address already in use`:** Port already bound — change port or close the previous instance.
* **Can’t connect over LAN:** Allow TCP/7777 in firewall; verify host IP with `ipconfig/ifconfig`.
* **Sprites not loading:** Use classpath resources (`getResource("/Assets/Player.png")`).

---

## License

Choose one (MIT recommended for learning projects). Add a `LICENSE` file at repo root.

---

## Credits

Built by your team as a study project on game loops, rendering, and networking with Java + Swing + Gradle.

```
::contentReference[oaicite:0]{index=0}
```
