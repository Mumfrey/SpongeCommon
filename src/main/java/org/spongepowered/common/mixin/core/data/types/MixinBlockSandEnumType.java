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

import net.minecraft.block.BlockSand;
import org.spongepowered.api.data.type.SandType;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.text.translation.SpongeTranslation;

@Mixin(BlockSand.EnumType.class)
@Implements(@Interface(iface = SandType.class, prefix = "shadow$"))
public abstract class MixinBlockSandEnumType {

    @Shadow public abstract String getName();
    @Shadow public abstract String getUnlocalizedName();

    private String name;
    private Translation translation;

    public String shadow$getId() {
        return getName();
    }

    @Intrinsic
    public String shadow$getName() {
        if (this.name == null) {
            this.name = shadow$getTranslation().get();
        }
        return this.name;
    }

    public Translation shadow$getTranslation() {
        if (this.translation == null) {
            NOCOMPILE
            this.translation = new SpongeTranslation("tile.sand." + getUnlocalizedName() + ".name");
        }
        return this.translation;
    }

}
