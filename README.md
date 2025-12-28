Pokémon Battle Game
A Java-based Pokémon battle simulator that focuses exclusively on the combat phase of Pokémon battles. Experience strategic turn-based battles with type advantages, Pokémon switching, and item usage.
Overview
This game implements the core battle mechanics of Pokémon, allowing players to engage in tactical combat scenarios. The system features authentic type matchups, move calculations, and battle strategies that make Pokémon battles engaging and strategic.
Features
Core Battle Mechanics

Turn-based Combat System: Classic Pokémon battle flow with player and opponent turns
Type Advantage System: Complete implementation of type effectiveness (super effective, not very effective, immune)
Multiple Battle Actions:

Attack with various moves
Switch between Pokémon in your party
Use items to heal or boost your Pokémon



Pokémon System

Multiple Pokémon species with unique stats
Each Pokémon has:

HP (Hit Points)
Attack and Defense stats
One or more types (Fire, Water, Grass, Electric, etc.)
A set of learnable moves



Move System

Various attack moves with different:

Power levels
Accuracy ratings
Type classifications
PP (Power Points) limits



Item System

Consumable items for battle support:

Healing items (Potions, Super Potions, Hyper Potions)
Status restoration items
Battle enhancement items



Type Effectiveness
The game implements the complete Pokémon type chart with interactions such as:

Super Effective (2x damage): Fire → Grass, Water → Fire, Grass → Water, etc.
Not Very Effective (0.5x damage): Fire → Water, Water → Grass, Grass → Fire, etc.
Immune (0x damage): Ground → Electric, Ghost → Normal, etc.
