package com.gitgud.utility.modification;//package utility.modification;
//
//import model.mission.fight.Attack;
//import model.mission.fight.DamageType;
//
//import java.util.HashMap;
//
//
//public final class AttackModifier extends Modifier<Attack>
//{
//    private final HashMap<DamageType, Float> attackModifiers;
//
//
//    private final float accuracyModifier;
//
//
//    public AttackModifier(HashMap<DamageType, Float> attackModifiers, float accuracyModifier)
//    {
//        this.attackModifiers = attackModifiers;
//        this.accuracyModifier = accuracyModifier;
//    }
//
//
//    public HashMap<DamageType, Float> getAttackModifiers()
//    {
//        return attackModifiers;
//    }
//
//
//    public float getAccuracyModifier()
//    {
//        return accuracyModifier;
//    }
//
//
//    @Override
//    public Attack modify(Attack attack)
//    {
//        float accuracy = attack.accuracy()*getAccuracyModifier();
//        HashMap<DamageType, Float> attackModifiers = getAttackModifiers();
//        DamageType damageType = attack.damageType();
//        int damage = attack.damage();
//
//        if (attackModifiers.containsKey(damageType))
//        {
//            damage=Math.round(damage*attackModifiers.get(damageType));
//        }
//
//        return new Attack(damage, accuracy, attack.damageType());
//    }
//}
