package com.gitgud.engine.model.gameobjects;

import com.github.ruediste.polymorphicGson.GsonPolymorph;


/**
 * All Objects that can be placed on a {@link com.gitgud.engine.model.map.GridMap}.
 * This Interface should normally not be used directly.
 * Instead, use {@link GameObject} and its respective Inheritors
 *
 * @Owner: Finn L.
 * @Author: Finn L.
 * @Since: 12.06.2024
 * @Version: 2.0
 * @see GameObject
 */
@GsonPolymorph
public interface GridMappable extends Sprite, Describable, Named
{
}
