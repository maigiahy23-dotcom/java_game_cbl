## Game idea
A tilebased game, where enemies use pathfinding to get to the player, the player can move around in a maze like grid of tiles. Each game has a randomly generated grid. Player has a weapon to shoot, he can only walk in the grid and shoot in 4 directions(The direction player is moving).

## Advanced topic choices
### Networking (current choice)
Create 2 players that can join the same grid world and can see each other
### Build tools (current choice)
Learn more about build tools, different types, how they work and other things.
### Git managment
How git works, explain most important things to make it work
### Enemy agents
Explore how enemy agents work
### Enemy pathfinding
Explore how enemy pathfinding works

## Todo
- [ ] Setup
    - [ ] Setup window
 - [ ] Add join/create lobby button
 - [ ] Add lobby screen and display all players
 - [ ] Make create lobby work
 - [ ] Make join lobby work
 - [ ] Make the start game work

- [ ] Game basic
 - [ ] Basic object rendering
 - [ ] Camera
 - [ ] Both players are rendered on screen
 - [ ] Both players input work and they can see each other move around
 - [ ] Players can shoot bullets and both players see the bullets

- [ ] Enemies(Enemies is an abstract class that can be extended, for example add stronger units, ranged units etc.)
 - [ ] Enemies randomly spawn around for all clients
 - [ ] Enemies can take damage
 - [ ] Enemies can die
 - [ ] Enemies move towards players
 - [ ] Enemies can attack player

- [ ] Game loop
 - [ ] Score
 - [ ] End screen
 - [ ] "pause" menu

- [ ] Enviorment
 - [ ] Grid based enviorment