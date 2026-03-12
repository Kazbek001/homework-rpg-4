package com.narxoz.rpg;

import com.narxoz.rpg.battle.RaidEngine;
import com.narxoz.rpg.battle.RaidResult;
import com.narxoz.rpg.bridge.AreaSkill;
import com.narxoz.rpg.bridge.FireEffect;
import com.narxoz.rpg.bridge.IceEffect;
import com.narxoz.rpg.bridge.SingleTargetSkill;
import com.narxoz.rpg.bridge.Skill;
import com.narxoz.rpg.composite.EnemyUnit;
import com.narxoz.rpg.composite.HeroUnit;
import com.narxoz.rpg.composite.PartyComposite;
import com.narxoz.rpg.composite.RaidGroup;

public class Main {
    public static void main(String[] args) {

        // 1. "Leaf" создать персеножы
        HeroUnit warrior = new HeroUnit("Arthas", 140, 30);
        HeroUnit mage = new HeroUnit("Jaina",90, 40);
        EnemyUnit goblin = new EnemyUnit("Goblin", 70, 20);
        EnemyUnit orc = new EnemyUnit("Orc",120, 25);

        // 2. слздать иереархию Composite
        PartyComposite heroes = new PartyComposite("Heroes");
        heroes.add(warrior);
        heroes.add(mage);

        PartyComposite frontline =new PartyComposite("Frontline");
        frontline.add(goblin);
        frontline.add(orc);

        // RaidGroup -
        RaidGroup enemies = new RaidGroup("Enemy Raid");
        enemies.add(frontline);

        System.out.println("--- Team Structures ---");
        heroes.printTree("");
        enemies.printTree("");

        // 3. "Bridge" комбинация
        Skill slashFire = new SingleTargetSkill("Slash", 20, new FireEffect());
        Skill slashIce = new SingleTargetSkill("Slash", 20, new IceEffect());
        Skill stormFire = new AreaSkill("Storm", 15, new FireEffect());

        System.out.println("\n Bridge Preview");
        System.out.println(slashFire.getSkillName() + "эффект: " + slashFire.getEffectName());
        System.out.println(slashIce.getSkillName() + "эффект: " + slashIce.getEffectName());
        System.out.println(stormFire.getSkillName() + "эффект: " + stormFire.getEffectName());


        RaidEngine engine = new RaidEngine().setRandomSeed(42L);
        RaidResult result = engine.runRaid(heroes, enemies, slashFire, stormFire);

        System.out.println("\n--- Raid Result ---");
        System.out.println("pobeditel " + result.getWinner());
        System.out.println("raunds  " + result.getRounds());

        // Шайқас барысын консольге шығару
        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println("\n=== Demo Complete ===");
    }
}