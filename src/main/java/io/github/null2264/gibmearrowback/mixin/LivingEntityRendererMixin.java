/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.null2264.gibmearrowback.mixin;

import io.github.null2264.gibmearrowback.GMABInjectedEntityModel;
import io.github.null2264.gibmearrowback.MobStuckArrowsFeatureRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> {
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void init(EntityRendererFactory.Context context, M model, float shadowRadius, CallbackInfo ci) {
        LivingEntityRenderer self = (LivingEntityRenderer) (Object) this;
        if (!(self instanceof PlayerEntityRenderer) && model instanceof GMABInjectedEntityModel)
            self.addFeature(new MobStuckArrowsFeatureRenderer(context, self));
    }
}