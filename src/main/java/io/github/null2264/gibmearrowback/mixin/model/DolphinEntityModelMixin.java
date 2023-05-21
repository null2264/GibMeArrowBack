/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.null2264.gibmearrowback.mixin.model;

import com.google.common.collect.ImmutableList;
import io.github.null2264.gibmearrowback.GMABInjectedEntityModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.DolphinEntityModel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(DolphinEntityModel.class)
public abstract class DolphinEntityModelMixin implements GMABInjectedEntityModel
{
    @Shadow @Final private ModelPart body;
    private List<ModelPart> parts;

    @Override
    public List<ModelPart> getParts() {
        return parts;
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(ModelPart root, CallbackInfo ci) {
        if (parts == null)
            parts = this.body.traverse().filter((part) -> !part.isEmpty()).collect(ImmutableList.toImmutableList());
    }
}