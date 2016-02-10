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
package org.spongepowered.common.mixin.core.data;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.block.BlockTallGrass;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.common.interfaces.translatable.NativeTranslatable;
import org.spongepowered.common.text.translation.SpongeTranslation;

@Mixin({
    BlockStone.EnumType.class,
    BlockStoneSlab.EnumType.class,
    BlockTallGrass.EnumType.class,
    BlockStoneSlabNew.EnumType.class,
    BlockDirt.DirtType.class,
    BlockDoublePlant.EnumPlantType.class
})
@Implements(@Interface(iface = NativeTranslatable.class, prefix = "translatable$"))
public abstract class TraitNativeTranslatable implements NativeTranslatable {

    private String name;
    private Translation translation;

    @Intrinsic
    public String translatable$getName() {
        if (this.name == null) {
            this.name = this.getTranslationAsString();
        }
        return this.name;
    }

    @Override
    public Translation getTranslation() {
        if (this.translation == null) {
            this.translation = new SpongeTranslation(this);
        }
        return this.translation;
    }

}
