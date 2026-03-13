package com.narxoz.rpg.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartyComposite implements CombatNode {
    private final String name;
    private final List<CombatNode> children = new ArrayList<>();

    public PartyComposite(String name) {
        this.name = name;
    }

    public void add(CombatNode node) {
        children.add(node);
    }

    public void remove(CombatNode node) {
        children.remove(node);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        // Барлық балалардың жалпы денсаулығын есептеу
        int totalHealth = 0;
        for (CombatNode child : children) {
            totalHealth += child.getHealth();
        }
        return totalHealth;
    }

    @Override
    public int getAttackPower() {
        // Тек тірі балалардың шабуыл күшін қосу
        int totalAttack = 0;
        for (CombatNode child : getAliveChildren()) {
            totalAttack += child.getAttackPower();
        }
        return totalAttack;
    }

    @Override
    public void takeDamage(int amount) {
        List<CombatNode> alive = getAliveChildren();
        if (alive.isEmpty()) return; // Ешкім тірі болмаса, урон алмайды

        // Уронды барлық тірі мүшелерге теңдей бөліп береміз
        int splitDamage = amount / alive.size();
        for (CombatNode child : alive) {
            child.takeDamage(splitDamage);
        }
    }

    @Override
    public boolean isAlive() {
        // Кем дегенде бір бала тірі болса, топ тірі саналады
        for (CombatNode child : children) {
            if (child.isAlive()) return true;
        }
        return false;
    }

    @Override
    public List<CombatNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public void printTree(String indent) {
        // Ағаш құрылымын әдемілеп шығару
        System.out.println(indent + "+ " + name + " [HP: " + getHealth() + ", ATK: " + getAttackPower() + "]");
        for (CombatNode child : children) {
            child.printTree(indent + "  "); // Ішкі элементтерге шегініс (пробел) қосамыз
        }
    }

    private List<CombatNode> getAliveChildren() {
        // Тек тірі балаларды қайтаратын көмекші әдіс
        List<CombatNode> aliveList = new ArrayList<>();
        for (CombatNode child : children) {
            if (child.isAlive()) {
                aliveList.add(child);
            }
        }
        return aliveList;
    }
}