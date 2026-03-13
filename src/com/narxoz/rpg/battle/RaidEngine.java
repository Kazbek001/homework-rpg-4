package com.narxoz.rpg.battle;

import com.narxoz.rpg.bridge.Skill;
import com.narxoz.rpg.composite.CombatNode;

import java.util.Random;

public class RaidEngine {
    private Random random = new Random(1L);

    public RaidEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public RaidResult runRaid(CombatNode teamA, CombatNode teamB, Skill teamASkill, Skill teamBSkill) {
        RaidResult result = new RaidResult();

        // check
        if (teamA == null || teamB == null || !teamA.isAlive() || !teamB.isAlive()) {
            result.setWinner("No Battle (Invalid teams)");
            result.setRounds(0);
            return result;
        }

        int rounds = 0;
        result.addLine("RAID STARTED:" + teamA.getName() + " vs " + teamB.getName() + " ===");

        while (teamA.isAlive() && teamB.isAlive()) {
            rounds++;
            result.addLine("\n[Раунд " + rounds + "]");

            // Team A атакует
            result.addLine(teamA.getName() + " use " + teamASkill.getSkillName() + "!");
            teamASkill.cast(teamB); // Скилл арқылы урон береміз
            result.addLine(">>> У " + teamB.getName() + " HP: " + teamB.getHealth());


            if (!teamB.isAlive()) break;

            // тиим б ответить
            result.addLine(teamB.getName() + " use " + teamBSkill.getSkillName() + "!");
            teamBSkill.cast(teamA);
            result.addLine(">>> У " + teamA.getName() + "HP " + teamA.getHealth());
        }

        // сохранить
        result.setRounds(rounds);
        if (teamA.isAlive()) {
            result.setWinner(teamA.getName());
        } else {
            result.setWinner(teamB.getName());
        }

        result.addLine("\n=== REID. Победитель: " + result.getWinner() + " ===");
        return result;
    }
}