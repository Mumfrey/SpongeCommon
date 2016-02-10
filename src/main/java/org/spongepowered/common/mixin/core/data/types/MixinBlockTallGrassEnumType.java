/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.common.mixin.core.data.types;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.BlockTallGrass;
import org.spongepowered.api.data.type.ShrubType;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.interfaces.translatable.NativeTranslatable;

import java.util.Map;

@Mixin(BlockTallGrass.EnumType.class)
@Implements(@Interface(iface = ShrubType.class, prefix = "shadow$"))
public abstract class MixinBlockTallGrassEnumType implements NativeTranslatable {

    @Shadow public abstract String getName();
    
    private static final Map<String, String> TRANSLATIONS = ImmutableMap.<String, String>of(
        "dead_bush", "tallgrass.shrub",
        "tall_grass", "tallgrass.grass",
        "fern", "tallgrass.fern"
    );
    
    private static final String DEFAULT_TRANSLATION = "tallgrass";

    public String shadow$getId() {
        return getName();
    }

    @Override
    public String getLocalisationPrefix() {
        return "tile";
    }
    
    @Override
    public String getLocalisationStub() {
        String id = MixinBlockTallGrassEnumType.TRANSLATIONS.get(this.getName());
        return id != null ? id : MixinBlockTallGrassEnumType.DEFAULT_TRANSLATION;
    }

}
