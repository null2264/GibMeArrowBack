/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.null2264.gibmearrowback.mixin.model;

import com.google.common.collect.ImmutableList;
import io.github.null2264.gibmearrowback.GMABInjectedEntityModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(value = SinglePartEntityModel.class, priority = 1001)
public abstract class SinglePartEntityModelMixin implements GMABInjectedEntityModel
{
    @Nullable
    private List<ModelPart> parts = null;

    @SuppressWarnings("rawtypes")
    @Override
    public List<ModelPart> getParts() {
        SinglePartEntityModel self = (SinglePartEntityModel) (Object) this;
        if (parts == null)
            parts = self.getPart().traverse().filter((part) -> !part.isEmpty()).collect(ImmutableList.toImmutableList());
        return parts;
    }
}