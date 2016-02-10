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
package org.spongepowered.common.text.translation;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;
import net.minecraft.util.StatCollector;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.api.util.annotation.NonnullByDefault;
import org.spongepowered.common.interfaces.translatable.NativeTranslatable;

import java.util.Locale;

@NonnullByDefault
public class SpongeTranslation implements Translation {

    private final String id;

    public SpongeTranslation(NativeTranslatable translatable) {
        this(translatable.getLocalisationPrefix(), translatable.getLocalisationStub(), translatable.getLocalisationSuffix());
    }
    
    public SpongeTranslation(String prefix, String stub) {
        this(prefix, stub, "name");
    }
    
    public SpongeTranslation(String prefix, String stub, String suffix) {
        this(String.format("%s%s%s%s%s", checkNotNull(prefix), conditionalDot(prefix), checkNotNull(stub), conditionalDot(checkNotNull(suffix)), suffix));
    }

    public SpongeTranslation(String id) {
        this.id = checkNotNull(id, "id");
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String get(Locale locale) {
        return StatCollector.translateToLocal(this.id);
    }

    @Override
    public String get(Locale locale, Object... args) {
        return StatCollector.translateToLocalFormatted(this.id, args);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", this.id)
                .toString();
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SpongeTranslation other = (SpongeTranslation) obj;
        if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
    
    private static String conditionalDot(String string) {
        return string.isEmpty() ? "" : ".";
    }

}
