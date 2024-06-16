package com.gitgud.pieces.utility.modification;//package utility.modification;
//
//import model.mission.fight.DamageType;
//import model.mission.fight.Defence;
//
//import java.util.HashMap;
//
//
//public class DefenceModifier extends Modifier<Defence>
//{
//
//    private final HashMap<DamageType, Float> defenceModifiers;
//
//
//    private final float evasionModifier;
//
//
//    public DefenceModifier(HashMap<DamageType, Float> defenceModifiers, float evasionModifier)
//    {
//        this.defenceModifiers = defenceModifiers;
//        this.evasionModifier = evasionModifier;
//    }
//
//
//    public HashMap<DamageType, Float> getDefenceModifiers()
//    {
//        return defenceModifiers;
//    }
//
//
//    public float getEvasionModifier()
//    {
//        return evasionModifier;
//    }
//
//    @Override
//    public Defence modify(Defence defence)
//    {
//        DamageType damageType = defence.damageType();
//        int defenceValue = defence.defence();
//
//        if (defenceModifiers.containsKey(damageType))
//        {
//            defenceValue = Math.round(defenceValue * defenceModifiers.get(damageType));
//        }
//
//        int evasionChance = Math.round(evasionModifier * defence.evasionChance());
//
//        return new Defence(defenceValue, evasionChance,damageType);
//    }
//}
