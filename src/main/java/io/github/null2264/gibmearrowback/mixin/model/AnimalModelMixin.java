/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.null2264.gibmearrowback.mixin.model;

import io.github.null2264.gibmearrowback.GMABInjectedEntityModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = AnimalModel.class, priority = 1001)
public abstract class AnimalModelMixin implements GMABInjectedEntityModel
{
    @Nullable
    private List<ModelPart> parts = null;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List<ModelPart> getParts() {
        AnimalModel self = (AnimalModel) (Object) this;
        if (parts == null) {  // cache parts
            List<ModelPart> newParts = new ArrayList<>();
            self.getHeadParts().forEach(part -> {
                if (part != null) newParts.add((ModelPart) part);
            });
            self.getBodyParts().forEach(part -> {
                if (part != null) newParts.add((ModelPart) part);
            });
            parts = newParts;
        }
        return parts;
    }
}