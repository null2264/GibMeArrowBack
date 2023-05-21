/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.null2264.gibmearrowback;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.MathHelper;

/**
 * Directly copied from {@link net.minecraft.client.render.entity.feature.StuckArrowsFeatureRenderer}
 */
@Environment(EnvType.CLIENT)
public class MobStuckArrowsFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends MobStuckObjectsFeatureRenderer<T, M> {
    private final EntityRenderDispatcher dispatcher;

    public MobStuckArrowsFeatureRenderer(EntityRendererFactory.Context context, LivingEntityRenderer<T, M> entityRenderer) {
        super(entityRenderer);
        this.dispatcher = context.getRenderDispatcher();
    }

    protected int getObjectCount(T entity) {
        return entity.getStuckArrowCount();
    }

    protected void renderObject(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Entity entity, float directionX, float directionY, float directionZ, float tickDelta) {
        float f = MathHelper.sqrt(directionX * directionX + directionZ * directionZ);
        ArrowEntity arrowEntity = new ArrowEntity(entity.world, entity.getX(), entity.getY(), entity.getZ());
        arrowEntity.setYaw((float)(Math.atan2(directionX, directionZ) * 57.2957763671875));
        arrowEntity.setPitch((float)(Math.atan2(directionY, f) * 57.2957763671875));
        arrowEntity.prevYaw = arrowEntity.getYaw();
        arrowEntity.prevPitch = arrowEntity.getPitch();
        this.dispatcher.render(arrowEntity, 0.0, 0.0, 0.0, 0.0F, tickDelta, matrices, vertexConsumers, light);
    }
}