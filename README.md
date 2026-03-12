# Homework 4: RPG Raid System - Bridge + Composite

## Overview
You now have heroes, enemies, and a basic battle engine from previous homework. In this assignment, you will design a raid system that supports:
- Flexible skill-effect combinations without class explosion
- Uniform handling of single fighters and nested teams

Your job is to implement:
- **Bridge Pattern** for skill + effect decoupling
- **Composite Pattern** for unit + group hierarchies

## What You Will Build
- A `CombatNode` hierarchy where leaf units and groups share one interface
- A `Skill` hierarchy that delegates damage behavior to `EffectImplementor`
- A raid engine that can run battles for single units and nested groups

## Patterns and Roles
- **Bridge**: `Skill` (abstraction) + `EffectImplementor` (implementor)
- **Composite**: `CombatNode` (component), `UnitLeaf` (leaf), `PartyComposite`/`RaidGroup` (composite)

## Connection to Previous Homework
- HW1: object creation patterns for characters and equipment
- HW2: advanced enemy creation and cloning
- HW3: singleton battle orchestration and API adapters
- HW4: structural flexibility for abilities and team hierarchies

## Requirements at a Glance
- Implement Bridge with at least 2 skill types and 3 effect implementors
- Implement Composite with leaf and nested group behavior
- Integrate both patterns in `RaidEngine`
- Demonstrate all features in `Main.java`
- Provide UML diagrams for Bridge and Composite

## Running the Project
```bash
javac -d out $(find src -name "*.java")
java -cp out com.narxoz.rpg.Main
```

## Deliverables
- Completed Java code
- UML diagrams (2)
- Clear console output from demo

Read `ASSIGNMENT.md` for full instructions and rubric.

# Homework 4: RPG Raid System - Bridge + Composite

## Patterns Implemented

### 1. Bridge Pattern
- **Abstraction:** `Skill` (e.g., `SingleTargetSkill`, `AreaSkill`)
- **Implementor:** `EffectImplementor` (e.g., `FireEffect`, `IceEffect`)
- **Logic:** Separated how a skill targets units from how the damage/effect is calculated, avoiding class explosion.

### 2. Composite Pattern
- **Component:** `CombatNode` interface.
- **Leaf:** `HeroUnit`, `EnemyUnit`.
- **Composite:** `PartyComposite`, `RaidGroup`.
- **Logic:** Allows treating individual units and groups uniformly. Damaging a `PartyComposite` automatically distributes the damage among its members.

---

## Demo Output
Example of a raid simulation with nested groups and skill effects:

```text
--- Team Structures ---
+ Heroes [HP: 230, ATK: 70]
  - Arthas [HP=140, ATK=30]
  - Jaina [HP=90, ATK=40]
+ Enemy Raid [HP: 190, ATK: 45]
  + Frontline [HP: 190, ATK: 45]
    - Goblin [HP=70, ATK=20]
    - Orc [HP=120, ATK=25]

--- Raid Result ---
Winner: Heroes
Rounds: 8

RAID STARTED: Heroes vs Enemy Raid ===
[Round 1]
Heroes use Slash!
>>> У Enemy Raid HP: 166
Enemy Raid use Storm!
>>> У Heroes HP 212
...
=== RAID COMPLETED. Winner: Heroes ===

